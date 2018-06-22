package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_1;

import android.arch.lifecycle.LifecycleOwner;
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

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_1.FUpR_1st_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.FollowUpSymptomsActivity;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.OnFollowUpSymptomAnswerListener;
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
 * {@link OnFollowUpSymptomsFirstQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_1st_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_1st_Question extends Fragment implements
        FUpS__1st_VH.OnFUpSFirstVHListener,
        OnFollowUpSymptomAnswerListener {

    public static final String TAG = "FUpS_1st_Question";

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();

    private OnFollowUpSymptomsFirstQuestionListener mListener;
    private OnFURFourthQuestionInteractionListener mListenerFUR;
    private FUpS__1st_VH viewHolder;

    public FUpS_1st_Question() {
        // Required empty public constructor
    }

    public static FUpS_1st_Question newInstance() {
        FUpS_1st_Question fragment = new FUpS_1st_Question();
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
        View view = inflater.inflate(R.layout.fragment_follow__up__symptoms_1st__question, container, false);
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of(getActivity()).get(MedlynkViewModel.class);
        manager = new SharedPreferenceManager(getActivity());
        mMedlynkViewModel.getAnswers(manager.getAppointmentID(), Constants.FOLLOW_UP_SYMPTOMS_ROW,0, 1)
                .observe((LifecycleOwner) this, new Observer<DataBaseModel>() {
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
                        viewHolder = new FUpS__1st_VH(view, answerDB);
                        viewHolder.setOnFUpSFirstVHListener(FUpS_1st_Question.this);
                    }

                });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFollowUpSymptomsFirstQuestionListener) {
            mListener = (OnFollowUpSymptomsFirstQuestionListener) context;
        }
        else if(context instanceof OnFURFourthQuestionInteractionListener){
            mListenerFUR = (OnFURFourthQuestionInteractionListener) context;
        }
        else {
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
    public void onNextClick() {
        System.out.println("FUpS_1st_Question.onNextClick");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        Answer answer = new Answer();
        answer.setReply(viewHolder.getAnswerInput());
        MedlynkRequests.followUpSymptomAnswer(getActivity(), FUpS_1st_Question.this,
                manager.getAppointmentID(), "1",
                answer);

        answersForDB.add(answer);
    }

    @Override
    public void onAnswerSuccess(FollowUpSymptomResponse response) {

        if (Constants.Context_Tag.equals(FollowUpSymptomsActivity.class.getSimpleName())) {
            JsonConverter JC = JsonConverter.getInstance();
            if (existsRecord == false)
                mMedlynkViewModel.insertAnswersToDB(manager.getAppointmentID(), Constants.FOLLOW_UP_SYMPTOMS_ROW,0, 1, JC.answersToAnswerJson(answersForDB));
            else
                mMedlynkViewModel.updateAnswersToDB(manager.getAppointmentID(), Constants.FOLLOW_UP_SYMPTOMS_ROW,0, 1, JC.answersToAnswerJson(answersForDB));

            System.out.println("FUpS_1st_Question.onFirstAnswerSuccess");
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListener.onFirstQuestion();
        } else {
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListenerFUR.onFURFourthQuestion();
        }
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("FUpS_1st_Question.onFirstAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
    }

    public interface OnFollowUpSymptomsFirstQuestionListener {
        void onFirstQuestion();
    }

    public interface OnFURFourthQuestionInteractionListener {
        void onFURFourthQuestion();
    }
}
