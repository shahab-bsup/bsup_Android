package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_8;

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
 * {@link NS_8th_question.OnNewSymptomEighthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_8th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_8th_question extends Fragment implements
        OnNewSymptomAnswerListener,
        NS_8th_VH.OnEighthNSVHListener {

    public static final String TAG = "NS_8th_question";

    private OnNewSymptomEighthQuestionListener mListener;
    private NS_8th_VH viewHolder;
    private MedlynkViewModel medlynkViewModel;
    private SharedPreferenceManager manager;
    private boolean existRecord = false;
    private List<Answer> answersForDB = new ArrayList<> ();

    public NS_8th_question() {
        // Required empty public constructor
    }

    public static NS_8th_question newInstance(String param1, String param2) {
        NS_8th_question fragment = new NS_8th_question ();
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
        final View view = inflater.inflate ( R.layout.fragment_new__symptom_8th_question, container, false );
        medlynkViewModel = ViewModelProviders.of ( getActivity () )
                .get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        medlynkViewModel.getAnswers ( manager.getAppointmentID (),
                Constants.NEW_SYMPTOM_ROW, 8 )
                .observe ( this, new Observer<DataBaseModel> () {
                    private Answer answer;
                    private List<Answer> answers;
                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existRecord = true;
                            JsonConverter jsonConverter = JsonConverter.getInstance ();
                            if (jsonConverter.answerJsonToAnswers ( dataBaseModel.getAnswerJson () ).size () > 1) {
                                answers = jsonConverter.answerJsonToAnswers ( dataBaseModel.getAnswerJson () );
                            } else {
                                answer = jsonConverter.
                                        answerJsonToAnswers ( dataBaseModel.getAnswerJson () )
                                        .get ( 0 );
                            }
                        }
                        viewHolder = new NS_8th_VH ( view );
                        viewHolder.setOnEighthNSVHListener ( NS_8th_question.this );
                        if (answers != null) {
                            viewHolder.onUpdateUI ( answers );
                        } else if (answer != null) {
                            viewHolder.onUpdateUI ( answer );
                        }
                    }
                } );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomEighthQuestionListener) {
            mListener = (OnNewSymptomEighthQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomEighthQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onAnswerSuccess(NewSymptomAnswerResponse response) {
        JsonConverter JC = JsonConverter.getInstance();
        if (existRecord == false)
            medlynkViewModel.insertAnswersToDB(manager.getAppointmentID(),
                    Constants.NEW_SYMPTOM_ROW,
                    8,
                    JC.answersToAnswerJson(answersForDB));
        else
            medlynkViewModel.updateAnswersToDB(manager.getAppointmentID(),
                    Constants.NEW_SYMPTOM_ROW,
                    8,
                    JC.answersToAnswerJson(answersForDB));

        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onEightQuestion ();
    }

    @Override
    public void onAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onEightQuestion ();
    }

    @Override
    public void onUnauthorized() {

    }

    @Override
    public void onNextClicked(Answer answer) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomEighthQuestionAnswer ( getActivity ()
                , NS_8th_question.this,
                manager.getAppointmentID ()
                , answer );

        answersForDB.add ( answer );
    }

    @Override
    public void onNextClicked(List<Answer> answers) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomEighthQuestionAnswer ( getActivity ()
                , NS_8th_question.this,
                manager.getAppointmentID ()
                , answers );

        answersForDB.addAll ( answers );
    }

    @Override
    public void onSkipClicked() {
        mListener.onEightQuestion ();
    }

    public interface OnNewSymptomEighthQuestionListener {
        void onEightQuestion();
    }
}
