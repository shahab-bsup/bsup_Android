package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.OnFollowUpSymptomAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9.FUpS_9th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9.FUpS_9th_VH;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_9.NS_9th_VH;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_9.NS_9th_question;
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
 * {@link OnFollowUpSymptomsTenthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_10th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_10th_Question extends Fragment
        implements FUpS_10th_VH.OnFUpSTenthVHListener
        , OnFollowUpSymptomAnswerListener {

    public static final String TAG = "FUpS_10th_Question";

    private OnFollowUpSymptomsTenthQuestionListener mListener;
    private OnFURThirteenQuestionInteractionListener mListenerFUR;
    private FUpS_10th_VH viewHolder;
    private MedlynkViewModel medlynkViewModel;
    private SharedPreferenceManager manager;
    private boolean existRecord = false;
    private List<Answer> answersDB = new ArrayList<>();
    private int tableNumber;
    private int questionNumber;

    public FUpS_10th_Question() {
        // Required empty public constructor
    }

    public static FUpS_10th_Question newInstance(String param1, String param2) {
        FUpS_10th_Question fragment = new FUpS_10th_Question();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow__up__symptoms_10th__question, container, false);
        if (Constants.Context_Tag.equals(FollowUpSymptomsActivity.class.getSimpleName())) {
            tableNumber = Constants.FOLLOW_UP_SYMPTOMS_ROW;
            questionNumber = 10;
        } else {
            tableNumber = Constants.FOLLOW_UP_RESULTS_ROW;
            questionNumber = 13;
        }
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        medlynkViewModel = ViewModelProviders.of(getActivity()).get(MedlynkViewModel.class);
        manager = new SharedPreferenceManager(getActivity());
        medlynkViewModel.getAnswers(manager.getAppointmentID(), tableNumber, 0, questionNumber)
                .observe(FUpS_10th_Question.this, new Observer<DataBaseModel>() {
                    private List<Answer> answers;

                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existRecord = true;
                            JsonConverter jsonConverter = JsonConverter.getInstance();
                            answers = jsonConverter.answerJsonToAnswers ( dataBaseModel.getAnswerJson () );
                        }
                        viewHolder = new FUpS_10th_VH(view);
                        viewHolder.setOnFUpSTenthVHListener(FUpS_10th_Question.this);
                        if (answers != null) {
                            viewHolder.onUpdateUI(answers);
                        }
                    }
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFollowUpSymptomsTenthQuestionListener) {
            mListener = (OnFollowUpSymptomsTenthQuestionListener) context;
        } else if (context instanceof OnFURThirteenQuestionInteractionListener) {
            mListenerFUR = (OnFURThirteenQuestionInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFollowUpSymptomsFirstQuestionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mListenerFUR = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        MedlynkRequests.followUpSymptomAnswer(getActivity(),
                FUpS_10th_Question.this,
                manager.getAppointmentID(),"10",
                answer);
        answersDB.clear();
        answersDB.add(answer);
    }

    @Override
    public void onNextClicked(List<Answer> answers) {
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        MedlynkRequests.followUpSymptomAnswer(getActivity(),FUpS_10th_Question.this,
                manager.getAppointmentID(),"10",
                answers);

        answersDB.clear();
        answersDB=answers;
    }

    @Override
    public void onSkipClick() {
        if (Constants.Context_Tag.equals(FollowUpSymptomsActivity.class.getSimpleName())) {
            mListener.onTenthQuestion();
        } else {
            mListenerFUR.onFURThirteenQuestion();
        }
    }

    @Override
    public void onAnswerSuccess(FollowUpSymptomResponse response) {

        JsonConverter jsonConverter = JsonConverter.getInstance();
        if (!existRecord) {
            medlynkViewModel.insertAnswersToDB(manager.getAppointmentID(),
                    tableNumber, 0, questionNumber, jsonConverter.answersToAnswerJson(answersDB));
        } else {
            medlynkViewModel.updateAnswersToDB(manager.getAppointmentID(),
                    tableNumber, 0, questionNumber, jsonConverter.answersToAnswerJson(answersDB));
        }
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onTenthQuestion();

    }

    @Override
    public void onAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onTenthQuestion();
    }

    public interface OnFollowUpSymptomsTenthQuestionListener {
        void onTenthQuestion();
    }

    public interface OnFURThirteenQuestionInteractionListener {
        void onFURThirteenQuestion();
    }

}
