package tk.medlynk.patient.android.Activity.Refill.fragments.Question_2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_2.Refill_second_VH.OnRefillSecondQuestionClickListener;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_7.Refill_seventh_Question;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.MotherCallback;
import tk.medlynk.patient.android.Model.SymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

import com.neweraandroid.demo.R;


public class Refill_second_Question extends Fragment implements OnRefillSecondQuestionClickListener, MotherCallback {
    public static final String TAG = Refill_seventh_Question.class.getSimpleName();
    private onRefillSecondQuestionInteractionListener mListener;
    private Refill_second_VH viewHolder;

    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        System.out.println("Refill_second_Question.onAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onRefillSecondQuestion();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("Refill_second_Question.onAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);

    }

    public interface onRefillSecondQuestionInteractionListener {
        void onRefillSecondQuestion();
    }

    public static Refill_second_Question newInstance(String param1, String param2) {
        Refill_second_Question fragment = new Refill_second_Question();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refill_second__question, container, false);
        this.viewHolder = new Refill_second_VH(view);
        this.viewHolder.setOnRefillSecondQuestionClickListener(this);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRefillSecondQuestionInteractionListener) {
            this.mListener = (onRefillSecondQuestionInteractionListener) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement onRefillFirstQuestionInteractionListener");
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void onNextClicked(Answer answer) {
        System.out.println("Refill_second_Question.onNextClicked");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        SharedPreferenceManager manager = new SharedPreferenceManager(getActivity());
        MedlynkRequests.refill_second_question(getActivity(), manager.getAppointmentID(),
                manager.getQuestionSetID(), this, answer);
    }

    public void onSkipClicked() {
        System.out.println("Refill_second_Question.onSkipClicked");
        mListener.onRefillSecondQuestion ();
    }
}
