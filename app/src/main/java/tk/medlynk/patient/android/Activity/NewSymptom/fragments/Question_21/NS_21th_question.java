package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_21;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_22.NS_22th_question;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_21th_question.OnNewSymptomTwenty1QuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_21th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
@Deprecated
public class NS_21th_question extends Fragment implements
        NS_21th_VH.On21thQuestionVHListener,
        OnTwentyOneAnswerListener {

    public static final String TAG = "NS_21th_question";

    private OnNewSymptomTwenty1QuestionListener mListener;

    private NS_21th_VH viewHolder;

    public NS_21th_question() {

    }

    public static NS_21th_question newInstance() {
        NS_21th_question fragment = new NS_21th_question();
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
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new__symptom_21th_question, container, false);
        viewHolder = new NS_21th_VH(view);
        viewHolder.setOn21thQuestionVHListener(this);
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNewSymptomTwenty1QuestionListener) {
            mListener = (OnNewSymptomTwenty1QuestionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNewSymptomTwenty1QuestionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("NS_21th_question.onDetach");
        mListener = null;
    }

    @Override
    public void onNextClicked(List<Answer> answers) {
        System.out.println("NS_21th_question.onNextClicked");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        SharedPreferenceManager manager = new SharedPreferenceManager(getActivity());
        MedlynkRequests.newSymptomTwentyOneQuestionAnswer(getActivity(),
                NS_21th_question.this,
                manager.getAppointmentID(),
                answers
        );
    }

    @Override
    public void onSkipClicked() {
        System.out.println("NS_21th_question.onSkipClicked");
        mListener.onTwenty1Question();
    }

    @Override
    public void onTwentyOneAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println("NS_21th_question.onTwentyOneAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onTwenty1Question();
    }

    @Override
    public void onTwentyOneAnswerFailure() {
        System.out.println("NS_21th_question.onTwentyOneAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onTwenty1Question();
    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_21th_question.onUnauthorized" );

    }

    public interface OnNewSymptomTwenty1QuestionListener {
        void onTwenty1Question();
    }
}
