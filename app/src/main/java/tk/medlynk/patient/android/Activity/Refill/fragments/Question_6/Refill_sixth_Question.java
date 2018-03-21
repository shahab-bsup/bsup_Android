package tk.medlynk.patient.android.Activity.Refill.fragments.Question_6;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_6.Refill_sixth_Question_ViewHolder.OnRefillSixthQuestionClickListener;

import com.neweraandroid.demo.R;

public class Refill_sixth_Question extends Fragment implements OnRefillSixthQuestionClickListener {
    public static final String TAG = Refill_sixth_Question.class.getSimpleName();
    private onRefillSixthQuestionInteractionListener mListener;
    private Refill_sixth_Question_ViewHolder viewHolder;

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
        this.viewHolder = new Refill_sixth_Question_ViewHolder(view);
        this.viewHolder.setOnRefillSixthQuestionClickListener(this);
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

    public void onNextClicked() {
        System.out.println("Refill_sixth_Question.onNextClicked");
        this.mListener.onRefillSixthQuestion();
    }

    public void onSkipClicked() {
        System.out.println("Refill_sixth_Question.onSkipClicked");
        this.mListener.onRefillSixthQuestion();
    }
}
