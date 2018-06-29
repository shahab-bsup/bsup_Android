package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13;

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

import tk.medlynk.patient.android.Activity.NewSymptom.OnNewSymptomAnswerListener;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.JsonConverter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnNewSymptomThirteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_13th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_13th_question extends Fragment implements
        NS_13th_VH.OnThirteenNSVHListener, OnNewSymptomAnswerListener {

    public final static String TAG = "NS_13th_question";

    private OnNewSymptomThirteenQuestionListener mListener;
    private NS_13th_VH viewHolder;
    private SharedPreferenceManager manager;
    private boolean existRecord = false;
    private List<Answer> answerDB;
    private List<Answer> answersForDB = new ArrayList<> ();
    private MedlynkViewModel medlynkViewModel;

    public NS_13th_question() {
        // Required empty public constructor...
    }

    public static NS_13th_question newInstance() {
        NS_13th_question fragment = new NS_13th_question ();
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

        View view = inflater.inflate ( R.layout.fragment_new__symptom_13th_question, container, false );
        manager = new SharedPreferenceManager ( getActivity () );
        dbOperation ( view );
        return view;
    }

    private void dbOperation(final View view) {
        medlynkViewModel = ViewModelProviders.of ( getActivity () )
                .get ( MedlynkViewModel.class );
        medlynkViewModel.getAnswers ( manager.getAppointmentID (),
                Constants.NEW_SYMPTOM_ROW,
                0, 13 )
                .observe ( this, new Observer<DataBaseModel> () {
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
                        viewHolder = new NS_13th_VH ( view );
                        viewHolder.setOnThirteenNSVHListener ( NS_13th_question.this );
                        if (answerDB != null) {
                            viewHolder.onUpdateUI ( answerDB );
                        }
                    }
                } );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomThirteenQuestionListener) {
            mListener = (OnNewSymptomThirteenQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomThirteenQuestionListener" );
        }
    }

    @Override
    public void onResume() {
        super.onResume ();
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        MedlynkRequests.newSymptomQuestionsAnswer ( getActivity (), NS_13th_question.this,
                manager.getAppointmentID (), "13",
                answer );
        answersForDB.clear ();
        answersForDB.add ( answer );
    }

    @Override
    public void onNextClicked(List<Answer> answers) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        MedlynkRequests.newSymptomQuestionsAnswer ( getActivity (),
                NS_13th_question.this,
                manager.getAppointmentID (),
                "13",
                answers );
        answersForDB.clear ();
        answersForDB.addAll ( answers );
    }

    @Override
    public void onSkipClicked() {
        mListener.onThirteenQuestion ();
    }

    @Override
    public void onAnswerSuccess(NewSymptomAnswerResponse response) {
        JsonConverter jsonConverter = JsonConverter.getInstance ();
        if (existRecord) {
            medlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (),
                    Constants.NEW_SYMPTOM_ROW, 0,
                    13,
                    jsonConverter.answersToAnswerJson ( answersForDB ) );
        } else {
            medlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (),
                    Constants.NEW_SYMPTOM_ROW, 0,
                    13,
                    jsonConverter.answersToAnswerJson ( answersForDB ) );
        }
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onThirteenQuestion ();
    }

    @Override
    public void onAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onThirteenQuestion ();
    }

    @Override
    public void onUnauthorized() {

    }

    public interface OnNewSymptomThirteenQuestionListener {
        void onThirteenQuestion();
    }
}