package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_4;

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
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_3.FUpS_3rd_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_3.FUpS_3rd_VH;
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
 * {@link OnFollowUpSymptomsFourthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_4th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_4th_Question extends Fragment implements
        FUpS_4th_VH.OnFUpSFourthVHListener, OnFollowUpSymptomAnswerListener {

    public static final String TAG = "FUpS_4th_Question";

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();
    private int tableNumber;
    private int questionNumber;

    private OnFollowUpSymptomsFourthQuestionListener mListener;
    private OnFURSeventhQuestionInteractionListener mListenerFUR;
    private FUpS_4th_VH viewHolder;

    public FUpS_4th_Question() {
        // Required empty public constructor
    }

    public static FUpS_4th_Question newInstance(String param1, String param2) {
        FUpS_4th_Question fragment = new FUpS_4th_Question();
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
        View view = inflater.inflate(R.layout.fragment_follow__up__symptoms_4th__question, container, false);
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            tableNumber=Constants.FOLLOW_UP_SYMPTOMS_ROW;
            questionNumber=4;
        }
        else {
            tableNumber=Constants.FOLLOW_UP_RESULTS_ROW;
            questionNumber=7;
        }
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of(getActivity()).get(MedlynkViewModel.class);
        manager = new SharedPreferenceManager(getActivity());
        mMedlynkViewModel.getAnswers(manager.getAppointmentID(),
                tableNumber,0, questionNumber)
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
                        viewHolder = new FUpS_4th_VH(view);
                        viewHolder.setOnFUpSFourthVHListener(FUpS_4th_Question.this);
                        if (answerDB!=null){
                            viewHolder.onUpdateUI(answerDB);
                        }
                    }

                });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFourthQuestion();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFollowUpSymptomsFourthQuestionListener) {
            mListener = (OnFollowUpSymptomsFourthQuestionListener) context;
        }
        else if(context instanceof OnFURSeventhQuestionInteractionListener){
            mListenerFUR= (OnFURSeventhQuestionInteractionListener) context;
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
        System.out.println("FUpS_4th_Question.onNextClick");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        MedlynkRequests.followUpSymptomAnswer(getActivity(), FUpS_4th_Question.this,
                manager.getAppointmentID(), "4",
                answer);

        answersForDB.add(answer);
    }

    @Override
    public void onSkipClick() {
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            System.out.println("FUpS_4th_Question.onSkipClick");
            mListener.onFourthQuestion();
        }
        else {
            mListenerFUR.onFURSeventhQuestion();
        }
    }

    @Override
    public void onAnswerSuccess(FollowUpSymptomResponse response) {
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            JsonConverter JC = JsonConverter.getInstance();
            if (existsRecord == false)
                mMedlynkViewModel.insertAnswersToDB(manager.getAppointmentID(),tableNumber,
                        0, questionNumber, JC.answersToAnswerJson(answersForDB));
            else
                mMedlynkViewModel.updateAnswersToDB(manager.getAppointmentID(), tableNumber,
                        0, questionNumber, JC.answersToAnswerJson(answersForDB));

            System.out.println("FUpS_4th_Question.onFourthAnswerSuccess");
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListener.onFourthQuestion();
        }
        else {
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListenerFUR.onFURSeventhQuestion();
        }
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("FUpS_4th_Question.onFourthAnswerFailure");
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
    public interface OnFollowUpSymptomsFourthQuestionListener {
        // TODO: Update argument type and name
        void onFourthQuestion();
    }

    public interface OnFURSeventhQuestionInteractionListener{
        void onFURSeventhQuestion();
    }
}
