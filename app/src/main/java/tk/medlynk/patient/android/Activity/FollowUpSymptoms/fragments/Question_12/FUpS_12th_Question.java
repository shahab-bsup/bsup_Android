package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_12;

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
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_14.NS_14th_VH;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_14.NS_14th_question;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.JsonConverter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Model.Medication;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsTwelveQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_12th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_12th_Question extends Fragment implements
        FUpS_12th_VH.OnFUpSTwelveVHListener,
        OnFollowUpTwelveAnswerListener {

    public static final String TAG = "FUpS_12th_Question";

    private OnFollowUpSymptomsTwelveQuestionListener mListener;
    private OnFURFifteenQuestionInteractionListener mListenerFUR;
    private FUpS_12th_VH viewHolder;
    private MedlynkViewModel medlynkViewModel;
    private SharedPreferenceManager manager;
    private int tableNumber;
    private int questionNumber;
    private boolean existsRecord = false;
    private List<Answer> answerDB = new ArrayList<> ();
    private List<Medication> medicationsDB = new ArrayList<> (  );

    public FUpS_12th_Question() {
        // Required empty public constructor
    }

    public static FUpS_12th_Question newInstance(String param1, String param2) {
        FUpS_12th_Question fragment = new FUpS_12th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_12th__question, container, false );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            tableNumber=Constants.FOLLOW_UP_SYMPTOMS_ROW;
            questionNumber=12;
        }
        else {
            tableNumber=Constants.FOLLOW_UP_RESULTS_ROW;
            questionNumber=15;
        }
        dbOperation ( view );
        return view;
    }

    private void dbOperation(final View view) {
        medlynkViewModel = ViewModelProviders.of ( getActivity () )
                .get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        medlynkViewModel.getAnswers ( manager.getAppointmentID (),
                tableNumber,
                0,
                questionNumber ).observe ( this, new Observer<DataBaseModel> () {
            @Override
            public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                List<Medication> answers = null;
                Answer answer = null;
                if (dataBaseModel != null) {
                    existsRecord = true;
                    JsonConverter jsonConverter =
                            JsonConverter.getInstance ();
                    if (jsonConverter.
                            medicationJsonToMedications ( dataBaseModel
                                    .getAnswerJson () ).size () >= 1) {
                        answers = jsonConverter.
                                medicationJsonToMedications ( dataBaseModel.getAnswerJson () );
                    } else {
                        answer = jsonConverter.
                                answerJsonToAnswers ( dataBaseModel.getAnswerJson () )
                                .get ( 0 );
                    }
                }
                viewHolder = new FUpS_12th_VH( view );
                viewHolder.setOnFUpSTwelveVHListener( FUpS_12th_Question.this );
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
        if (context instanceof OnFollowUpSymptomsTwelveQuestionListener) {
            mListener = (OnFollowUpSymptomsTwelveQuestionListener) context;
        }
        else if (context instanceof OnFURFifteenQuestionInteractionListener) {
            mListenerFUR = (OnFURFifteenQuestionInteractionListener) context;
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
        MedlynkRequests.followUpTwelveQuestionAnswer ( getActivity (),
                this, manager.getAppointmentID (),
                answer);
        this.answerDB.clear ();
        this.answerDB.add ( answer );
    }

    @Override
    public void onNextClicked(List<Medication> answers) {
        System.out.println ( "FUpS_12th_Question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new
                SharedPreferenceManager ( getActivity () );
       MedlynkRequests.followUpTwelveQuestionAnswer ( getActivity (),
               this, manager.getAppointmentID (), answers);
       this.medicationsDB.clear ();
       this.medicationsDB.addAll ( answers );
    }

    @Override
    public void onSkipClicked() {
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            System.out.println("FUpS_12th_Question.onSkipClicked");
            mListener.onTwelveQuestion();
        }
        else {
            mListenerFUR.onFURFifteenQuestion();
        }
    }

    @Override
    public void onTwelveAnswerSuccess(FollowUpSymptomResponse response) {
        JsonConverter jsonConverter = JsonConverter.getInstance ();
        if (existsRecord) {
            if (answerDB.size () > 0) {
                medlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (),
                        tableNumber,
                        0,
                        questionNumber,
                        jsonConverter.answersToAnswerJson ( answerDB ) );
            } else {
                medlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (),
                        tableNumber,
                        0,
                        questionNumber,
                        jsonConverter.medicationsToMedicationJson ( medicationsDB ) );
            }
        } else {
            if (answerDB.size () > 0) {
                medlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (),
                        tableNumber,
                        0,
                        questionNumber,
                        jsonConverter.answersToAnswerJson ( answerDB ) );
            } else {
                medlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (),
                        tableNumber,
                        0,
                        questionNumber,
                        jsonConverter.medicationsToMedicationJson ( medicationsDB ) );
            }
        }
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListener.onTwelveQuestion();
        }
        else {
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListenerFUR.onFURFifteenQuestion();
        }
    }

    @Override
    public void onTwelveAnswerFailure() {
        System.out.println ( "FUpS_12th_Question.onTwelveAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }


    public interface OnFollowUpSymptomsTwelveQuestionListener {
        void onTwelveQuestion();
    }
    public interface OnFURFifteenQuestionInteractionListener{
        void onFURFifteenQuestion();
    }
}
