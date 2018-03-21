package tk.medlynk.patient.android.Activity.Refill.fragments.Question_7;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_2.Refill_second_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_7.Refill_seventh_Question_ViewHolder.OnRefillSeventhQuestionClickListener;

import com.neweraandroid.demo.R;

public class Refill_seventh_Question extends Fragment implements OnRefillSeventhQuestionClickListener {
    public static final String TAG = Refill_second_Question.class.getSimpleName();
    private onRefillSeventhQuestionInteractionListener mListener;
    private Refill_seventh_Question_ViewHolder viewHolder;

    public interface onRefillSeventhQuestionInteractionListener {
        void onRefillSeventhQuestion();
    }

    public static Refill_seventh_Question newInstance(String param1, String param2) {
        Refill_seventh_Question fragment = new Refill_seventh_Question();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refill_seventh__question, container, false);
        this.viewHolder = new Refill_seventh_Question_ViewHolder(view);
        this.viewHolder.setOnRefillSeventhQuestionClickListener(this);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRefillSeventhQuestionInteractionListener) {
            this.mListener = (onRefillSeventhQuestionInteractionListener) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement onRefillFirstQuestionInteractionListener");
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void onNextClicked() {
        System.out.println("Refill_seventh_Question.onNextClicked");
        this.mListener.onRefillSeventhQuestion();
    }

    public void onSkipClicked() {
        System.out.println("Refill_seventh_Question.onSkipClicked");
        this.mListener.onRefillSeventhQuestion();
    }
}
