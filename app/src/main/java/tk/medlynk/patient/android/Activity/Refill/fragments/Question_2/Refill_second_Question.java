package tk.medlynk.patient.android.Activity.Refill.fragments.Question_2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_2.Refill_second_Question_ViewHolder.OnRefillSecondQuestionClickListener;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_7.Refill_seventh_Question;

import com.neweraandroid.demo.R;


public class Refill_second_Question extends Fragment implements OnRefillSecondQuestionClickListener {
    public static final String TAG = Refill_seventh_Question.class.getSimpleName();
    private onRefillSecondQuestionInteractionListener mListener;
    private Refill_second_Question_ViewHolder viewHolder;

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
        this.viewHolder = new Refill_second_Question_ViewHolder(view);
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

    public void onNextClicked() {
        System.out.println("Refill_second_Question.onNextClicked");
    }

    public void onSkipClicked() {
        System.out.println("Refill_second_Question.onSkipClicked");
    }
}
