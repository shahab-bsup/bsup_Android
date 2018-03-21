package tk.medlynk.patient.android.Activity.Refill.fragments.Question_4;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_4.Refill_fourth_Question_ViewHolder.OnRefillFourthQuestionClickListener;
import com.neweraandroid.demo.R;

public class Refill_fourth_Question extends Fragment implements OnRefillFourthQuestionClickListener {
    public static final String TAG = Refill_fourth_Question.class.getSimpleName();
    private onRefillFourthQuestionInteractionListener mListener;
    private Refill_fourth_Question_ViewHolder viewHolder;

    public interface onRefillFourthQuestionInteractionListener {
        void onRefillFourthQuestion();
    }

    public static Refill_fourth_Question newInstance(String param1, String param2) {
        Refill_fourth_Question fragment = new Refill_fourth_Question();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refill_fourth__question, container, false);
        this.viewHolder = new Refill_fourth_Question_ViewHolder(view);
        this.viewHolder.setOnRefillFourthQuestionClickListener(this);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRefillFourthQuestionInteractionListener) {
            this.mListener = (onRefillFourthQuestionInteractionListener) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement onRefillFirstQuestionInteractionListener");
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void onNextClicked() {
        System.out.println("Refill_fourth_Question.onNextClicked");
        this.mListener.onRefillFourthQuestion();
    }

    public void onSkipClicked() {
        System.out.println("Refill_fourth_Question.onSkipClicked");
        this.mListener.onRefillFourthQuestion();
    }
}
