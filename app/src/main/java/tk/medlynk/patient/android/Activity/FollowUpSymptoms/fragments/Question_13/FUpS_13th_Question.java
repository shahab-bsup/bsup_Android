package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_13;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.FollowUpSymptomsActivity;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.JsonConverter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsThirteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_13th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_13th_Question extends Fragment implements
        FUpS_13th_VH.OnFUpSThirteenVHListener,
        OnThirteenFollowUpAnswerListener {

    public static final String TAG = "FUpS_13th_Question";

    private OnFollowUpSymptomsThirteenQuestionListener mListener;
    private OnFURSixteenQuestionInteractionListener mListenerFUR;
    private FUpS_13th_VH viewHolder;
    private SharedPreferenceManager manager;
    private MedlynkViewModel medlynkViewModel;
    private List<Answer> answersDB = new ArrayList<> ();
    private boolean existsRecord = false;

    public FUpS_13th_Question() {
        // Required empty public constructor...

    }

    public static FUpS_13th_Question newInstance(String param1, String param2) {
        FUpS_13th_Question fragment = new FUpS_13th_Question ();
        Bundle args = new Bundle ();
        fragment.setArguments ( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        if (getArguments () != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_13th__question, container, false );
        dbOperation ( view );
        return view;
    }

    private void dbOperation(final View view) {
        manager = new SharedPreferenceManager ( getActivity () );
        medlynkViewModel = ViewModelProviders.of ( getActivity () )
                .get ( MedlynkViewModel.class );
        medlynkViewModel.getAnswers ( manager.getAppointmentID (),
                Constants.FOLLOW_UP_SYMPTOMS_ROW,0,
                13 ).observe ( FUpS_13th_Question.this,
                new Observer<DataBaseModel> () {
                    private Answer answer;

                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existsRecord = true;
                            answer = new Answer ();
                            JsonConverter jsonConverter = JsonConverter.getInstance ();
                            answer = jsonConverter.
                                    answerJsonToAnswers ( dataBaseModel.getAnswerJson () )
                                    .get ( 0 );
                        }
                        viewHolder = new FUpS_13th_VH ( view );
                        viewHolder.setOnFUpSThirteenVHListener ( FUpS_13th_Question.this );
                        if (answer != null) {
                            viewHolder.onUpdateUI ( answer );
                        }
                    }
                } );
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsThirteenQuestionListener) {
            mListener = (OnFollowUpSymptomsThirteenQuestionListener) context;
        }
        else if (context instanceof OnFURSixteenQuestionInteractionListener) {
            mListenerFUR = (OnFURSixteenQuestionInteractionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnFollowUpSymptomsFirstQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
        mListenerFUR=null;
    }

    @Override
    public void onNextClick(Answer answer) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomThirteenAnswer ( getActivity (),
                manager.getAppointmentID (),
                this, answer );

        answersDB.add ( answer );
    }

    @Override
    public void onSkipClick() {
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            mListener.onThirteenQuestion();
        }
        else {
            mListenerFUR.onFURSixteenQuestion();
        }
    }

    @Override
    public void onThirteenAnswerSuccess(FollowUpSymptomResponse response) {
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            JsonConverter jsonConverter = JsonConverter.getInstance();
            if (!existsRecord) {
                medlynkViewModel.insertAnswersToDB(manager.getAppointmentID(),
                        Constants.FOLLOW_UP_SYMPTOMS_ROW,0,
                        13,
                        jsonConverter.answersToAnswerJson(answersDB));
            } else {
                medlynkViewModel.updateAnswersToDB(manager.getAppointmentID(),
                        Constants.FOLLOW_UP_SYMPTOMS_ROW,0,
                        13,
                        jsonConverter.answersToAnswerJson(answersDB));
            }
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListener.onThirteenQuestion();
        }
        else {
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListenerFUR.onFURSixteenQuestion();
        }
    }

    @Override
    public void onThirteenAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }


    public interface OnFollowUpSymptomsThirteenQuestionListener {
        void onThirteenQuestion();
    }
    public interface OnFURSixteenQuestionInteractionListener{
        void onFURSixteenQuestion();
    }
}
