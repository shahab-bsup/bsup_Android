package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10;

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

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9.FUpS_9th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9.FUpS_9th_VH;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_9.NS_9th_VH;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_9.NS_9th_question;
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
 * {@link OnFollowUpSymptomsTenthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_10th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_10th_Question extends Fragment
        implements FUpS_10th_VH.OnFUpSTenthVHListener
, OnTenthFollowUpAnswerListener
{

    public static final String TAG = "FUpS_10th_Question";

    private OnFollowUpSymptomsTenthQuestionListener mListener;
    private FUpS_10th_VH viewHolder;
    private MedlynkViewModel medlynkViewModel;
    private SharedPreferenceManager manager;
    private boolean existRecord = false;
    private List<Answer> answersDB = new ArrayList<> (  );

    public FUpS_10th_Question() {
        // Required empty public constructor
    }

    public static FUpS_10th_Question newInstance(String param1, String param2) {
        FUpS_10th_Question fragment = new FUpS_10th_Question();
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
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_10th__question,
                container,
                false );
        dbOperation ( view );
        return view;
    }

    private void dbOperation(final View view) {
        medlynkViewModel = ViewModelProviders.of ( getActivity () )
                .get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        medlynkViewModel.getAnswers ( manager.getAppointmentID (),
                Constants.FOLLOW_UP_SYMPTOMS_ROW,
                10 ).observe ( FUpS_10th_Question.this,
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
                        viewHolder = new FUpS_10th_VH( view );
                        viewHolder.setOnFUpSTenthVHListener( FUpS_10th_Question.this );
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
        if (context instanceof OnFollowUpSymptomsTenthQuestionListener) {
            mListener = (OnFollowUpSymptomsTenthQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnFollowUpSymptomsFirstQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomTenthAnswer( getActivity (),
                manager.getAppointmentID (),
                FUpS_10th_Question.this,
                answer);
        answersDB.clear ();
        answersDB.add ( answer );
    }

    @Override
    public void onNextClicked(List<Answer> answers) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomTenthAnswer( getActivity (),
                manager.getAppointmentID (),
                FUpS_10th_Question.this,
                answers);

        answersDB.clear ();
        answersDB.addAll ( answers );
    }

    @Override
    public void onSkipClick() {
        mListener.onTenthQuestion ();
    }

    @Override
    public void onTenthAnswerSuccess(FollowUpSymptomResponse response) {
        JsonConverter jsonConverter = JsonConverter.getInstance ();
        if (!existRecord) {
            medlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (),
                    Constants.FOLLOW_UP_SYMPTOMS_ROW,
                    10, jsonConverter.
                            answersToAnswerJson ( answersDB ) );
        } else {
            medlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (),
                    Constants.FOLLOW_UP_SYMPTOMS_ROW,
                    10, jsonConverter.
                            answersToAnswerJson ( answersDB ) );
        }
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTenthQuestion ();
    }

    @Override
    public void onTenthAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTenthQuestion();
    }

    public interface OnFollowUpSymptomsTenthQuestionListener {
        void onTenthQuestion();
    }
}
