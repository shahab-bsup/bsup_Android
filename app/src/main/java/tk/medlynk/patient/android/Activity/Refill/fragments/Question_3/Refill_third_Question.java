package tk.medlynk.patient.android.Activity.Refill.fragments.Question_3;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_3.Refill_third_Question_ViewHolder.OnRefillThirdQuestionClickListener;
import com.neweraandroid.demo.R;

public class Refill_third_Question extends Fragment implements OnRefillThirdQuestionClickListener {
    public static final String TAG = Refill_third_Question.class.getSimpleName();
    private onRefillThirdQuestionInteractionListener mListener;
    private Refill_third_Question_ViewHolder viewHolder;

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
        this.viewHolder = new Refill_third_Question_ViewHolder(view);
        this.viewHolder.setOnRefillThirdQuestionClickListener(this);
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

    public void onNextClicked() {
        System.out.println("Refill_third_Question.onNextClicked");
        this.mListener.onRefillThirdQuestion();
    }

    public void onSkipClicked() {
        System.out.println("Refill_third_Question.onSkipClicked");
        this.mListener.onRefillThirdQuestion();
    }
}
