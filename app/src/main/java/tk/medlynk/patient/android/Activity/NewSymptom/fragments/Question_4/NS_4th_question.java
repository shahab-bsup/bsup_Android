package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_4;

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
import android.widget.Toast;

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
 * {@link NS_4th_question.OnNewSymptomFourthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_4th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_4th_question extends Fragment implements
        OnNewSymptomAnswerListener,
        NS_4thVH.OnFourthNSVHListener {

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answers = new ArrayList<>();

    public static final String TAG = "NS_4th_question";

    private OnNewSymptomFourthQuestionListener mListener;
    private NS_4thVH viewHolder;

    public NS_4th_question() {
        // Required empty public constructor...
    }

    public static NS_4th_question newInstance(String param1, String param2) {
        NS_4th_question fragment = new NS_4th_question();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new__symptom_4th_question, container, false);
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of(getActivity()).get(MedlynkViewModel.class);
        manager = new SharedPreferenceManager(getActivity());

        mMedlynkViewModel.getAnswers(manager.getAppointmentID(), Constants.NEW_SYMPTOM_ROW,0, 4)
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

                        viewHolder = new NS_4thVH(view);
                        viewHolder.setOnFourthNSVHListener(NS_4th_question.this);
                        if (answerDB!=null){
                            viewHolder.onUpdateUI(answerDB);
                        }
                    }
                });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNewSymptomFourthQuestionListener) {
            mListener = (OnNewSymptomFourthQuestionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNewSymptomFourthQuestionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAnswerSuccess(NewSymptomAnswerResponse response) {
        JsonConverter JC = JsonConverter.getInstance();
        if (existsRecord == false)
            mMedlynkViewModel.insertAnswersToDB(manager.getAppointmentID(), Constants.NEW_SYMPTOM_ROW,0, 4, JC.answersToAnswerJson(answers));
        else
            mMedlynkViewModel.updateAnswersToDB(manager.getAppointmentID(), Constants.NEW_SYMPTOM_ROW,0, 4, JC.answersToAnswerJson(answers));

        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onFourthQuestion();
    }

    @Override
    public void onAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        Toast.makeText(getActivity(), "try again later!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUnauthorized() {

    }

    @Override
    public void onNextClicked(Answer answer) {
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        MedlynkRequests.newSymptomQuestionsAnswer(getActivity(),
                NS_4th_question.this,
                manager.getAppointmentID(),"4",
                answer);

        answers.add(answer);
    }

    @Override
    public void onSkipClicked() {
        mListener.onFourthQuestion();
    }

    public interface OnNewSymptomFourthQuestionListener {
        void onFourthQuestion();
    }

}
