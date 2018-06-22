package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
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

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.FollowUpSymptomsActivity;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.OnFollowUpSymptomAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_6.FUpS_6th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_6.FUpS_6th_VH;
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
 * {@link OnFollowUpSymptomsSeventhQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_7th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_7th_Question extends Fragment implements
        FUpS_7th_Question_VH.OnFUpSSeventhVHListener, OnFollowUpSymptomAnswerListener {

    public static final String TAG = "FUpS_7th_Question";

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();

    private OnFollowUpSymptomsSeventhQuestionListener mListener;
    private OnFURTenthQuestionInteractionListener mListenerFUR;
    private FUpS_7th_Question_VH viewHolder;

    public FUpS_7th_Question() {
        // Required empty public constructor
    }

    public static FUpS_7th_Question newInstance(String param1, String param2) {
        FUpS_7th_Question fragment = new FUpS_7th_Question();
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
        View view = inflater.inflate(R.layout.fragment_follow__up__symptoms_7th__question, container, false);
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of(getActivity()).get(MedlynkViewModel.class);
        manager = new SharedPreferenceManager(getActivity());
        mMedlynkViewModel.getAnswers(manager.getAppointmentID(), Constants.FOLLOW_UP_SYMPTOMS_ROW,0, 7)
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
                        viewHolder = new FUpS_7th_Question_VH(view, answerDB);
                        viewHolder.setOnFUpSSeventhVHListener(FUpS_7th_Question.this);
                    }

                });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSeventhQuestion();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFollowUpSymptomsSeventhQuestionListener) {
            mListener = (OnFollowUpSymptomsSeventhQuestionListener) context;
        }
        else if(context instanceof OnFURTenthQuestionInteractionListener){
            mListenerFUR = (OnFURTenthQuestionInteractionListener) context;
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
        mListenerFUR=null;
    }

    @Override
    public void onNextClick(Answer answer) {
        System.out.println("FUpS_7th_Question.onNextClick");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        MedlynkRequests.followUpSymptomAnswer(getActivity(), FUpS_7th_Question.this,
                manager.getAppointmentID(), "7",
                answer);

        answersForDB.add(answer);
    }

    @Override
    public void onSkipClick() {
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            System.out.println("FUpS_7th_Question.onSkipClick");
            mListener.onSeventhQuestion();
        }
        else {
            mListenerFUR.onFURTenthQuestion();
        }
    }

    @Override
    public void onAnswerSuccess(FollowUpSymptomResponse response) {
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            JsonConverter JC = JsonConverter.getInstance();
            if (existsRecord == false)
                mMedlynkViewModel.insertAnswersToDB(manager.getAppointmentID(), Constants.FOLLOW_UP_SYMPTOMS_ROW,
                        0,7, JC.answersToAnswerJson(answersForDB));
            else
                mMedlynkViewModel.updateAnswersToDB(manager.getAppointmentID(), Constants.FOLLOW_UP_SYMPTOMS_ROW,
                        0,7, JC.answersToAnswerJson(answersForDB));

            System.out.println("FUpS_7th_Question.onSeventhAnswerSuccess");
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListener.onSeventhQuestion();
        }
        else {
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListenerFUR.onFURTenthQuestion();
        }
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("FUpS_7th_Question.onSeventhAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
    }

    public interface OnFollowUpSymptomsSeventhQuestionListener {
        // TODO: Update argument type and name
        void onSeventhQuestion();
    }
    public interface OnFURTenthQuestionInteractionListener{
        void onFURTenthQuestion();
    }

}
