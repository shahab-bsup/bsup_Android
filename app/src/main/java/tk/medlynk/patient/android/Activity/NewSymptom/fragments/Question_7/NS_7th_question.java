package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_7;

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
 * {@link NS_7th_question.OnNewSymptomSeventhQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_7th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_7th_question extends Fragment implements
        OnNewSymptomAnswerListener, NS_7th_VH.OnSeventhNSVHListener {

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();


    public static final String TAG = "NS_7th_question";


    private OnNewSymptomSeventhQuestionListener mListener;

    private NS_7th_VH viewHolder;

    public NS_7th_question() {
        // Required empty public constructor
    }

    public static NS_7th_question newInstance(String param1, String param2) {
        NS_7th_question fragment = new NS_7th_question ();
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
        View view = inflater.inflate(R.layout.fragment_new__symptom_7th_question, container, false);
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of(getActivity()).get(MedlynkViewModel.class);
        manager = new SharedPreferenceManager(getActivity());

        mMedlynkViewModel.getAnswers(manager.getAppointmentID(), Constants.NEW_SYMPTOM_ROW,0, 7)
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

                        viewHolder = new NS_7th_VH(view, answerDB);
                        viewHolder.setOnSeventhNSVHListener(NS_7th_question.this);
                    }
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomSeventhQuestionListener) {
            mListener = (OnNewSymptomSeventhQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomSeventhQuestionListener" );
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
            mMedlynkViewModel.insertAnswersToDB(manager.getAppointmentID(), Constants.NEW_SYMPTOM_ROW,0, 7, JC.answersToAnswerJson(answersForDB));
        else
            mMedlynkViewModel.updateAnswersToDB(manager.getAppointmentID(), Constants.NEW_SYMPTOM_ROW,0, 7, JC.answersToAnswerJson(answersForDB));

        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onSeventhQuestion();
    }

    @Override
    public void onAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }

    @Override
    public void onUnauthorized() {

    }

    @Override
    public void onNextClicked(Answer answer) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        MedlynkRequests.newSymptomQuestionsAnswer ( getActivity (),
                NS_7th_question.this,
                manager.getAppointmentID (),"7",
                answer);

        answersForDB.add(answer);
    }

    @Override
    public void onSkipClicked() {
        mListener.onSeventhQuestion ();
    }

    public interface OnNewSymptomSeventhQuestionListener {
        void onSeventhQuestion();
    }
}
