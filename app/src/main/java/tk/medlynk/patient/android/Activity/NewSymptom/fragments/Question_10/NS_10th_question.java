package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_10;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
 * {@link OnNewSymptomTenthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_10th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_10th_question extends Fragment implements
        OnNewSymptomAnswerListener,
        NS_10th_VH.OnTenthNSVHListener {

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord ;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();

    public static final String TAG = "NS_10th_question";


    private OnNewSymptomTenthQuestionListener mListener;

    private NS_10th_VH viewHolder;

    public NS_10th_question() {
        // Required empty public constructor
    }

    public static NS_10th_question newInstance(String param1, String param2) {
        NS_10th_question fragment = new NS_10th_question ();
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
        existsRecord=false;
        // Inflate the layout for this fragment
        final View view = inflater.inflate ( R.layout.fragment_new__symptom_10th_question, container, false );
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of(getActivity()).get(MedlynkViewModel.class);
        manager = new SharedPreferenceManager(getActivity());

        mMedlynkViewModel.getAnswers(manager.getAppointmentID(), Constants.NEW_SYMPTOM_ROW, 10)
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

                        viewHolder = new NS_10th_VH(view, answerDB);
                        viewHolder.setOnTenthNSVHListener(NS_10th_question.this);
                    }
                });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomTenthQuestionListener) {
            mListener = (OnNewSymptomTenthQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomTenthQuestionListener" );
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
            mMedlynkViewModel.insertAnswersToDB(manager.getAppointmentID(), Constants.NEW_SYMPTOM_ROW, 10, JC.answersToAnswerJson(answersForDB));
        else
            mMedlynkViewModel.updateAnswersToDB(manager.getAppointmentID(), Constants.NEW_SYMPTOM_ROW, 10, JC.answersToAnswerJson(answersForDB));

        System.out.println ( "NS_10th_question.onTenthAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTenthQuestion ();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println ( "NS_10th_question.onTenthAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_10th_question.onUnauthorized" );

    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_10th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        MedlynkRequests.newSymptomQuestionsAnswer ( getActivity (),
                NS_10th_question.this,
                manager.getAppointmentID (),"10",
                answer);
        answersForDB.add(answer);

    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_10th_question.onSkipClicked" );
        mListener.onTenthQuestion ();
    }

    public interface OnNewSymptomTenthQuestionListener {
        void onTenthQuestion();
    }
}
