package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_6;

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
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_4.NS_4thVH;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_4.NS_4th_question;
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
 * {@link NS_6th_question.OnNewSymptomSixthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_6th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_6th_question extends Fragment implements
        OnNewSymptomAnswerListener,
        NS_6th_VH.OnSixthNSVHListener {

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answers = new ArrayList<>();


    public static final String TAG = "NS_6th_question";

    private OnNewSymptomSixthQuestionListener mListener;

    private NS_6th_VH viewHolder;

    public NS_6th_question() {
        // Required empty public constructor
    }

    public static NS_6th_question newInstance(String param1, String param2) {
        NS_6th_question fragment = new NS_6th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_6th_question, container, false );
        dbOperation(view);
        return view;
    }
    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of(getActivity()).get(MedlynkViewModel.class);
        manager = new SharedPreferenceManager(getActivity());

        mMedlynkViewModel.getAnswers(manager.getAppointmentID(), Constants.NEW_SYMPTOM_ROW,0, 6)
                .observe(this, new Observer<DataBaseModel>() {
                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existsRecord = true;
                            answerDB = new Answer();
                            JsonConverter JC = JsonConverter.getInstance();
                            answerDB = JC.answerJsonToAnswers(dataBaseModel.getAnswerJson())
                                    .get(0);
                            Log.d(TAG, "onChanged: " + answerDB);
                        }
                        viewHolder = new NS_6th_VH(view);
                        viewHolder.setOnSixthNSVHListener(NS_6th_question.this);
                        if (answerDB!=null){
                            viewHolder.onUpdateUI(answerDB);
                        }
                    }
                });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomSixthQuestionListener) {
            mListener = (OnNewSymptomSixthQuestionListener) context;
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
            mMedlynkViewModel.insertAnswersToDB(manager.getAppointmentID(), Constants.NEW_SYMPTOM_ROW,0, 6, JC.answersToAnswerJson(answers));
        else
            mMedlynkViewModel.updateAnswersToDB(manager.getAppointmentID(), Constants.NEW_SYMPTOM_ROW,0, 6, JC.answersToAnswerJson(answers));

        System.out.println ( "NS_6th_question.onSixthAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onSixthQuestion ();
    }

    @Override
    public void onAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        System.out.println ( "NS_6th_question.onSixthAnswerFailure" );
    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_6th_question.onUnauthorized" );

    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_6th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        MedlynkRequests.newSymptomQuestionsAnswer ( getActivity (),
                NS_6th_question.this,
                manager.getAppointmentID (),"6",
                answer);

        answers.add(answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_6th_question.onSkipClicked" );
        mListener.onSixthQuestion ();
    }

    public interface OnNewSymptomSixthQuestionListener {
        void onSixthQuestion();
    }
}
