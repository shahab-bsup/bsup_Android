package tk.medlynk.patient.android.Activity.Refill.fragments.Question_6;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_6.Refill_sixth_VH.OnRefillSixthVHListener;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.MotherCallback;
import tk.medlynk.patient.android.Model.SymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

import com.neweraandroid.demo.R;

import java.util.List;

public class Refill_sixth_Question extends Fragment implements OnRefillSixthVHListener, MotherCallback {
    public static final String TAG = "Refill_sixth_Question";
    private onRefillSixthQuestionInteractionListener mListener;
    private Refill_sixth_VH viewHolder;

    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        System.out.println("Refill_sixth_Question.onAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onRefillSixthQuestion();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("Refill_sixth_Question.onAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onRefillSixthQuestion();
    }

    public interface onRefillSixthQuestionInteractionListener {
        void onRefillSixthQuestion();
    }

    public static Refill_sixth_Question newInstance(String param1, String param2) {
        Refill_sixth_Question fragment = new Refill_sixth_Question();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refill_sixth__question, container, false);
        this.viewHolder = new Refill_sixth_VH(view);
        this.viewHolder.setOnRefillSixthVHListener(this);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRefillSixthQuestionInteractionListener) {
            this.mListener = (onRefillSixthQuestionInteractionListener) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement onRefillFirstQuestionInteractionListener");
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println("Refill_sixth_Question.onNextClicked");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        SharedPreferenceManager manager = new SharedPreferenceManager(getActivity());
        MedlynkRequests.refill_sixth_question(getActivity(),
                manager.getAppointmentID(),
                manager.getQuestionSetID(),
                this,
                answer);
    }

    @Override
    public void onNextClicked(List<Answer> answers) {
        System.out.println("Refill_sixth_Question.onNextClicked");
        System.out.println("list of answers");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        SharedPreferenceManager manager = new SharedPreferenceManager(getActivity());
        MedlynkRequests.refill_sixth_question(getActivity(),
                manager.getAppointmentID(),
                manager.getQuestionSetID(),
                this,
                answers);
    }

    public void onSkipClicked() {
        System.out.println("Refill_sixth_Question.onSkipClicked");
        this.mListener.onRefillSixthQuestion();
    }
}
