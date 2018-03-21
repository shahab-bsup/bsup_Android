package tk.medlynk.patient.android.Activity.Refill.fragments.Qustion_8;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tk.medlynk.patient.android.Activity.Refill.fragments.Qustion_8.Refill_eighth_Question_ViewHolder.OnRefillEighthQuestionClickListener;

import com.neweraandroid.demo.R;

public class Refill_eighth_Question extends Fragment implements OnRefillEighthQuestionClickListener {
    public static final String TAG = Refill_eighth_Question.class.getSimpleName();
    private onRefillEighthQuestionInteractionListener mListener;
    private Refill_eighth_Question_ViewHolder viewHolder;

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
        this.viewHolder = new Refill_eighth_Question_ViewHolder(view);
        this.viewHolder.setOnRefillEighthQuestionClickListener(this);
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

    public void onNextClicked() {
        System.out.println("Refill_eighth_Question.onNextClicked");
        this.mListener.onRefillEighthQuestion();
    }

    public void onSkipClicked() {
        System.out.println("Refill_eighth_Question.onSkipClicked");
        this.mListener.onRefillEighthQuestion();
    }
}
