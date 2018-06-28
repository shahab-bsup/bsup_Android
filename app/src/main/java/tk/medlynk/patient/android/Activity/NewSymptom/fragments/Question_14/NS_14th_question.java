package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_14;

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

import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.JsonConverter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.Medication;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_14th_question.OnNewSymptomFourteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_14th_question#newInstance} factory method to
 * create an instance of this fragment...
 */
public class NS_14th_question extends Fragment implements
        NS_14th_VH.OnFourteenNSVHListener,
        OnFourteenAnswerListener {

    public static final String TAG = "NS_14th_question";

    private OnNewSymptomFourteenQuestionListener mListener;
    private NS_14th_VH viewHolder;
    private List<Medication> medicationsDB = new ArrayList<> ();
    private List<Answer> answerDB = new ArrayList<> ();
    private MedlynkViewModel medlynkViewModel;
    private SharedPreferenceManager manager;
    private boolean existsRecord = false;

    public NS_14th_question() {
        // Required empty public constructor
        // Required empty public constructor
    }

    public static NS_14th_question newInstance() {
        NS_14th_question fragment = new NS_14th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_14th_question,
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
                Constants.NEW_SYMPTOM_ROW,0,
                14 ).observe ( this, new Observer<DataBaseModel> () {
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
                viewHolder = new NS_14th_VH ( view );
                viewHolder.setOnFourteenNSVHListener ( NS_14th_question.this );
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
        if (context instanceof OnNewSymptomFourteenQuestionListener) {
            mListener = (OnNewSymptomFourteenQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomFourteenQuestionListener" );
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
        SharedPreferenceManager manager = new
                SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomFourteenQuestionAnswer ( getActivity (),
                this, manager.getAppointmentID (),
                answer );
        this.answerDB.clear ();
        this.answerDB.add ( answer );
    }

    @Override
    public void onNextClicked(List<Medication> answers) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new
                SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomFourteenQuestionAnswer ( getActivity (),
                this, manager.getAppointmentID (),
                answers );
        this.medicationsDB.clear ();
        this.medicationsDB.addAll ( answers );
    }

    @Override
    public void onSkipClicked() {
        mListener.onFourteenQuestion ();
    }

    @Override
    public void onThirteenAnswerSuccess(NewSymptomAnswerResponse response) {
        JsonConverter jsonConverter = JsonConverter.getInstance ();
        if (existsRecord) {
            if (answerDB.size () > 0) {
                medlynkViewModel
                        .updateAnswersToDB ( manager.getAppointmentID (),
                        Constants.NEW_SYMPTOM_ROW, 0, 14,
                        jsonConverter.answersToAnswerJson ( answerDB ) );
            } else {
                medlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (),
                        Constants.NEW_SYMPTOM_ROW, 0, 14,
                        jsonConverter.medicationsToMedicationJson ( medicationsDB ) );
            }
        } else {
            if (answerDB.size () > 0) {
                medlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (),
                        Constants.NEW_SYMPTOM_ROW, 0, 14,
                        jsonConverter.answersToAnswerJson ( answerDB ) );
            } else {
                medlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (),
                        Constants.NEW_SYMPTOM_ROW, 0, 14,
                        jsonConverter.medicationsToMedicationJson ( medicationsDB ) );
            }
        }
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onFourteenQuestion ();
    }

    @Override
    public void onThirteenAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }

    @Override
    public void onUnauthorized() {

    }

    public interface OnNewSymptomFourteenQuestionListener {
        void onFourteenQuestion();
    }
}
