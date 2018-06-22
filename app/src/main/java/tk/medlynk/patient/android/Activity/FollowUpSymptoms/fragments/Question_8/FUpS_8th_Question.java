package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8;

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

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.FollowUpSymptomsActivity;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.OnFollowUpSymptomAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7.FUpS_7th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7.FUpS_7th_Question_VH;
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
 * {@link OnFollowUpSymptomsEighthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_8th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_8th_Question extends Fragment implements
        FUpS_8th_VH.OnFUpSEighthVHListener, OnFollowUpSymptomAnswerListener {

    public static final String TAG = "FUpS_8th_Question";

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();


    private OnFollowUpSymptomsEighthQuestionListener mListener;
    private OnFUREleventhQuestionInteractionListener mListenerFUR;
    private FUpS_8th_VH viewHolder;

    public FUpS_8th_Question() {
        // Required empty public constructor
    }

    public static FUpS_8th_Question newInstance(String param1, String param2) {
        FUpS_8th_Question fragment = new FUpS_8th_Question();
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
        View view = inflater.inflate(R.layout.fragment_follow__up__symptoms_8th__question, container, false);
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of(getActivity()).get(MedlynkViewModel.class);
        manager = new SharedPreferenceManager(getActivity());
        mMedlynkViewModel.getAnswers(manager.getAppointmentID(), Constants.FOLLOW_UP_SYMPTOMS_ROW,0, 8)
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
                        viewHolder = new FUpS_8th_VH(view, answerDB);
                        viewHolder.setOnFUpSEighthVHListener(FUpS_8th_Question.this);
                    }

                });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFollowUpSymptomsEighthQuestionListener) {
            mListener = (OnFollowUpSymptomsEighthQuestionListener) context;
        }
        else if (context instanceof OnFUREleventhQuestionInteractionListener) {
            mListenerFUR = (OnFUREleventhQuestionInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFollowUpSymptomsFirstQuestionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mListenerFUR= null;
    }

    @Override
    public void onNextClick(Answer answer) {
        System.out.println("FUpS_8th_Question.onNextClick");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        MedlynkRequests.followUpSymptomAnswer(getActivity(), FUpS_8th_Question.this,
                manager.getAppointmentID(), "8",
                answer);

        answersForDB.add(answer);
    }

    @Override
    public void onSkipClick() {
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            System.out.println("FUpS_8th_Question.onSkipClick");
            mListener.onEighthQuestion();
        }
        else {
            mListenerFUR.onFUREleventhQuestion();
        }
    }

    @Override
    public void onAnswerSuccess(FollowUpSymptomResponse response) {
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            JsonConverter JC = JsonConverter.getInstance();
            if (existsRecord == false)
                mMedlynkViewModel.insertAnswersToDB(manager.getAppointmentID(), Constants.FOLLOW_UP_SYMPTOMS_ROW,
                        0,8, JC.answersToAnswerJson(answersForDB));
            else
                mMedlynkViewModel.updateAnswersToDB(manager.getAppointmentID(), Constants.FOLLOW_UP_SYMPTOMS_ROW,
                        0, 8, JC.answersToAnswerJson(answersForDB));

            System.out.println("FUpS_8th_Question.onEighthAnswerSuccess");
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListener.onEighthQuestion();
        }
        else {
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListenerFUR.onFUREleventhQuestion();
        }
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("FUpS_8th_Question.onEightAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFollowUpSymptomsEighthQuestionListener {
        // TODO: Update argument type and name
        void onEighthQuestion();
    }
    public interface OnFUREleventhQuestionInteractionListener{
        void onFUREleventhQuestion();
    }
}
