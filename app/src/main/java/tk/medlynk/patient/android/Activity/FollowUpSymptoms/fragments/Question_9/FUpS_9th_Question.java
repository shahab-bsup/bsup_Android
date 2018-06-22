package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * {@link OnFollowUpSymptomsNinthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_9th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_9th_Question extends Fragment implements
        FUpS_9th_VH.OnFUpSNinthVHListener,
        OnNinthFollowUpAnswerListener {

    public static final String TAG = "FUpS_9th_Question";

    private OnFollowUpSymptomsNinthQuestionListener mListener;
    private OnFURTwelveQuestionInteractionListener mListenerFUR;
    private FUpS_9th_VH viewHolder;
    private MedlynkViewModel medlynkViewModel;
    private SharedPreferenceManager manager;
    private boolean existRecord = false;
    private List<Answer> answersDB = new ArrayList<> ();

    public FUpS_9th_Question() {
        // Required empty public constructor
    }

    public static FUpS_9th_Question newInstance(String param1, String param2) {
        FUpS_9th_Question fragment = new FUpS_9th_Question ();
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
        // Inflate the layout for this fragment...
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_9th__question, container, false );
        dbOperation ( view );
        return view;
    }

    private void dbOperation(final View view) {
        medlynkViewModel = ViewModelProviders.of ( getActivity () )
                .get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        medlynkViewModel.getAnswers ( manager.getAppointmentID (),
                Constants.FOLLOW_UP_SYMPTOMS_ROW,0,
                9 ).observe ( FUpS_9th_Question.this,
                new Observer<DataBaseModel> () {
                    private Answer answer;
                    private List<Answer> answers;

                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existRecord = true;
                            JsonConverter jsonConverter = JsonConverter.getInstance ();
                            if (jsonConverter.answerJsonToAnswers ( dataBaseModel.getAnswerJson () ).size () > 1) {
                                answers = new ArrayList<> ();
                                answers = jsonConverter.answerJsonToAnswers ( dataBaseModel.getAnswerJson () );
                            } else {
                                answer = new Answer ();
                                answer = jsonConverter
                                        .answerJsonToAnswers ( dataBaseModel.getAnswerJson () ).get ( 0 );
                            }
                        }
                        viewHolder = new FUpS_9th_VH ( view );
                        viewHolder.setOnFUpSNinthVHListener ( FUpS_9th_Question.this );
                        if (answers != null) {
                            viewHolder.onUpdateUI ( answers );
                        } else if (answer != null) {
                            viewHolder.onUpdateUI ( answer );
                        }
                    }
                } );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsNinthQuestionListener) {
            mListener = (OnFollowUpSymptomsNinthQuestionListener) context;
        }
        else if (context instanceof OnFURTwelveQuestionInteractionListener) {
            mListenerFUR = (OnFURTwelveQuestionInteractionListener) context;
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
    public void onNextClicked(Answer answer) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomNinthAnswer ( getActivity (),
                manager.getAppointmentID (),
                FUpS_9th_Question.this,
                answer );

        answersDB.clear ();
        answersDB.add ( answer );
    }


    @Override
    public void onNextClicked(List<Answer> answers) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomNinthAnswer ( getActivity (),
                manager.getAppointmentID (),
                FUpS_9th_Question.this,
                answers );

        answersDB.clear ();
        answersDB.addAll ( answers );

    }

    @Override
    public void onSkipClick() {
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            mListener.onNinthQuestion();
        }
        else {
            mListenerFUR.onFURTwelveQuestion();
        }
    }

    @Override
    public void onNinthAnswerSuccess(FollowUpSymptomResponse response) {
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            JsonConverter jsonConverter = JsonConverter.getInstance();
            if (!existRecord) {
                medlynkViewModel.insertAnswersToDB(manager.getAppointmentID(),
                        Constants.FOLLOW_UP_SYMPTOMS_ROW,0,
                        9, jsonConverter.
                                answersToAnswerJson(answersDB));
            } else {
                medlynkViewModel.updateAnswersToDB(manager.getAppointmentID(),
                        Constants.FOLLOW_UP_SYMPTOMS_ROW,0,
                        9, jsonConverter.
                                answersToAnswerJson(answersDB));
            }
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListener.onNinthQuestion();
        }
        else {
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListenerFUR.onFURTwelveQuestion();
        }
    }

    @Override
    public void onNinthAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onNinthQuestion ();
    }

    public interface OnFollowUpSymptomsNinthQuestionListener {
        void onNinthQuestion();
    }

    public interface OnFURTwelveQuestionInteractionListener{
        void onFURTwelveQuestion();
    }

}
