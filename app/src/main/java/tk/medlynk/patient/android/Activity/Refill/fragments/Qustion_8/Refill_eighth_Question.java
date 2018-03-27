package tk.medlynk.patient.android.Activity.Refill.fragments.Qustion_8;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tk.medlynk.patient.android.Activity.Refill.fragments.Qustion_8.Refill_eighth_VH.OnRefillEighthVHListener;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.MotherCallback;
import tk.medlynk.patient.android.Model.SymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

import com.neweraandroid.demo.R;

import java.util.List;

public class Refill_eighth_Question extends Fragment implements OnRefillEighthVHListener,
        MotherCallback{
    public static final String TAG = Refill_eighth_Question.class.getSimpleName();
    private onRefillEighthQuestionInteractionListener mListener;
    private Refill_eighth_VH viewHolder;

    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        System.out.println("Refill_eighth_Question.onAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onRefillEighthQuestion();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("Refill_eighth_Question.onAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onRefillEighthQuestion();
    }

    public interface onRefillEighthQuestionInteractionListener {
        void onRefillEighthQuestion();
    }

    public static Refill_eighth_Question newInstance(String param1, String param2) {
        Refill_eighth_Question fragment = new Refill_eighth_Question();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refill_eighth__question, container, false);
        this.viewHolder = new Refill_eighth_VH(view);
        this.viewHolder.setOnRefillEighthVHListener(this);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRefillEighthQuestionInteractionListener) {
            this.mListener = (onRefillEighthQuestionInteractionListener) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement onRefillFirstQuestionInteractionListener");
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    @Override
    public void onNextClicked(List<Answer> answers) {
        System.out.println("Refill_eighth_Question.onNextClicked");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        SharedPreferenceManager manager = new SharedPreferenceManager(getActivity());
        MedlynkRequests.refill_eighth_question(getActivity(),
                manager.getAppointmentID(),
                manager.getQuestionSetID(),
                this,
                answers);

    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println("Refill_eighth_Question.onNextClicked");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        SharedPreferenceManager manager = new SharedPreferenceManager(getActivity());
        MedlynkRequests.refill_eighth_question(getActivity(),
                manager.getAppointmentID(),
                manager.getQuestionSetID(),
                this,
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println("Refill_eighth_Question.onSkipClicked");
        this.mListener.onRefillEighthQuestion();
    }
}
