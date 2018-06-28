package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_11;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Activity.NewSymptom.OnNewSymptomAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_7.NS_7th_VH;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_7.NS_7th_question;
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
 * {@link OnNewSymptomEleventhQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_11th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_11th_question extends Fragment implements
        OnNewSymptomAnswerListener,
        NS_11th_VH.OnEleventhNSVHListener {

    public static final String TAG = "NS_11th_question";

    private OnNewSymptomEleventhQuestionListener mListener;

    private NS_11th_VH viewHolder;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<> ();
    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;

    public NS_11th_question() {
        // Required empty public constructor
    }

    public static NS_11th_question newInstance(String param1, String param2) {
        NS_11th_question fragment = new NS_11th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_11th_question, container, false );
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of(getActivity()).get(MedlynkViewModel.class);
        manager = new SharedPreferenceManager(getActivity());

        mMedlynkViewModel.getAnswers(manager.getAppointmentID(),
                Constants.NEW_SYMPTOM_ROW,0,
                11)
                .observe(this, new Observer<DataBaseModel> () {
                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        Answer answerDB = null;
                        if (dataBaseModel != null) {
                            existsRecord = true;
                            answerDB = new Answer();
                            JsonConverter JC = JsonConverter.getInstance();
                            answerDB = JC.answerJsonToAnswers(dataBaseModel.getAnswerJson())
                                    .get(0);
                        }
                        viewHolder = new NS_11th_VH (view);
                        viewHolder.setOnEleventhNSVHListener (NS_11th_question.this);
                        if( answerDB != null ) {
                            viewHolder.onUpdateUI(answerDB);
                        }
                    }
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomEleventhQuestionListener) {
            mListener = (OnNewSymptomEleventhQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomEleventhQuestionListener" );
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
        if (existsRecord == false)
            mMedlynkViewModel.insertAnswersToDB(manager.getAppointmentID(),
                    Constants.NEW_SYMPTOM_ROW,0,
                    11,
                    JC.answersToAnswerJson(answersForDB));
        else
            mMedlynkViewModel.updateAnswersToDB(manager.getAppointmentID(),
                    Constants.NEW_SYMPTOM_ROW,0,
                    11,
                    JC.answersToAnswerJson(answersForDB));

        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onEleventhQuestion ();
    }

    @Override
    public void onAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onUnauthorized() {

    }

    @Override
    public void onNextClicked(Answer answer) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        MedlynkRequests.newSymptomQuestionsAnswer ( getActivity (), NS_11th_question.this,
                manager.getAppointmentID (),"11"
                ,answer);

        answersForDB.clear ();
        answersForDB.add ( answer );
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_11th_question.onSkipClicked" );
        mListener.onEleventhQuestion ();
    }


    public interface OnNewSymptomEleventhQuestionListener {
        void onEleventhQuestion();
    }
}
