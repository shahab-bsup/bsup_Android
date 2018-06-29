package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_11;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
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
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13.NS_13th_VH;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13.NS_13th_question;
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
 * {@link OnFollowUpSymptomsEleventhQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_11th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_11th_Question extends Fragment implements
        FUpS_11th_VH.OnFUpSEleventhVHListener, OnEleventFollowUphAnswerListener {

    public static final String TAG = "FUpS_11th_Question";

    private OnFollowUpSymptomsEleventhQuestionListener mListener;
    private OnFURFourteenQuestionInteractionListener mListenerFUR;
    private FUpS_11th_VH viewHolder;
    private SharedPreferenceManager manager;
    private MedlynkViewModel medlynkViewModel;
    private boolean existRecord = false;
    private List<Answer> answersForDB = new ArrayList<> (  );
    private int tableNumber;
    private int questionNumber;

    public FUpS_11th_Question() {
        // Required empty public constructor
    }

    public static FUpS_11th_Question newInstance() {
        FUpS_11th_Question fragment = new FUpS_11th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_11th__question, container, false );
        manager = new SharedPreferenceManager ( getActivity () );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            tableNumber=Constants.FOLLOW_UP_SYMPTOMS_ROW;
            questionNumber=11;
        }
        else {
            tableNumber=Constants.FOLLOW_UP_RESULTS_ROW;
            questionNumber=14;
        }
        dbOperation ( view );
        viewHolder = new FUpS_11th_VH( view );
        viewHolder.setOnFUpSEleventhVHListener( this );
        return view;
    }

    private void dbOperation(final View view) {
        medlynkViewModel = ViewModelProviders.of ( getActivity () )
                .get ( MedlynkViewModel.class );
        medlynkViewModel.getAnswers ( manager.getAppointmentID (),
                tableNumber,
                0, questionNumber )
                .observe ( this, new Observer<DataBaseModel> () {
                    public List<Answer> answerDB;

                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existRecord = true;
                            JsonConverter jsonConverter =
                                    JsonConverter.getInstance ();
                            answerDB = new ArrayList<> ();
                            answerDB = jsonConverter
                                    .answerJsonToAnswers ( dataBaseModel
                                            .getAnswerJson () );
                        }
                        viewHolder = new FUpS_11th_VH ( view );
                        viewHolder.setOnFUpSEleventhVHListener ( FUpS_11th_Question.this );
                        if (answerDB != null) {
                            viewHolder.onUpdateUI ( answerDB );
                        }
                    }
                } );
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsEleventhQuestionListener) {
            mListener = (OnFollowUpSymptomsEleventhQuestionListener) context;
        }
        else if (context instanceof OnFURFourteenQuestionInteractionListener) {
            mListenerFUR = (OnFURFourteenQuestionInteractionListener) context;
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
    public void onNextClicked(List<Answer> answers) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpEleventhQuestionAnswer ( getActivity (),
                FUpS_11th_Question.this,
                manager.getAppointmentID (),
                answers
        );
        answersForDB.clear ();
        answersForDB.addAll ( answers );
    }

    @Override
    public void onNextClicked(Answer answer) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpEleventhQuestionAnswer ( getActivity (),
                FUpS_11th_Question.this,
                manager.getAppointmentID (),
                answer
        );
        answersForDB.clear ();
        answersForDB.add ( answer );
    }

    @Override
    public void onSkipClicked() {
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            mListener.onEleventhQuestion();
        }
        else {
            mListenerFUR.onFURFourteenQuestion();
        }
    }

    @Override
    public void onEleventhAnswerSuccess(FollowUpSymptomResponse response) {
        JsonConverter jsonConverter = JsonConverter.getInstance ();
        if (existRecord) {
            medlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (),
                    tableNumber,
                    0,
                    questionNumber,
                    jsonConverter.answersToAnswerJson ( answersForDB ) );
        } else {
            medlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (),
                    tableNumber,
                    0,
                    questionNumber,
                    jsonConverter.answersToAnswerJson ( answersForDB ) );
        }
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListener.onEleventhQuestion();
        }
        else {
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListenerFUR.onFURFourteenQuestion();
        }
    }

    @Override
    public void onEleventhAnswerFailure() {
        System.out.println("FUpS_11th_Question.onEleventhAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }


    public interface OnFollowUpSymptomsEleventhQuestionListener {
        void onEleventhQuestion();
    }
    public interface OnFURFourteenQuestionInteractionListener{
        void onFURFourteenQuestion();
    }
}
