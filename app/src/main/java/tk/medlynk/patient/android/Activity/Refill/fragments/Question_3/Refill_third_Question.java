package tk.medlynk.patient.android.Activity.Refill.fragments.Question_3;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_3.Refill_third_VH.OnRefillThirdVHListener;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.MotherCallback;
import tk.medlynk.patient.android.Model.SymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

import com.neweraandroid.demo.R;

public class Refill_third_Question extends Fragment implements OnRefillThirdVHListener, MotherCallback {

    public static final String TAG = Refill_third_Question.class.getSimpleName();
    private onRefillThirdQuestionInteractionListener mListener;
    private Refill_third_VH viewHolder;

    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        System.out.println("Refill_third_Question.onAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onRefillThirdQuestion();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("Refill_third_Question.onAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
    }

    public interface onRefillThirdQuestionInteractionListener {
        void onRefillThirdQuestion();
    }

    public static Refill_third_Question newInstance(String param1, String param2) {
        Refill_third_Question fragment = new Refill_third_Question();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {

        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refill_third__question, container, false);
        this.viewHolder = new Refill_third_VH(view);
        this.viewHolder.setOnRefillThirdVHListener(this);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRefillThirdQuestionInteractionListener) {
            this.mListener = (onRefillThirdQuestionInteractionListener) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement onRefillFirstQuestionInteractionListener");
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void onNextClicked(Answer answer) {
        System.out.println("Refill_third_Question.onNextClicked");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        SharedPreferenceManager manager = new SharedPreferenceManager(getActivity());
        MedlynkRequests.refill_third_question(getActivity(),
                manager.getAppointmentID(), manager.getQuestionSetID(),
                this, answer);
    }

    public void onSkipClicked() {
        System.out.println("Refill_third_Question.onSkipClicked");
        this.mListener.onRefillThirdQuestion();
    }
}
