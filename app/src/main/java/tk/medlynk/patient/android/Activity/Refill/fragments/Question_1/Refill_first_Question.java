package tk.medlynk.patient.android.Activity.Refill.fragments.Question_1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_1.Refill_first_Question_ViewHolder.OnRefillFirstQuestionClickListener;
import com.neweraandroid.demo.R;


public class Refill_first_Question extends Fragment implements OnRefillFirstQuestionClickListener {
    public static final String TAG = Refill_first_Question.class.getSimpleName();
    private onRefillFirstQuestionInteractionListener mListener;
    private Refill_first_Question_ViewHolder viewHolder;

    public interface onRefillFirstQuestionInteractionListener {
        void onRefillFistQuestion();
    }

    public static Refill_first_Question newInstance(String param1, String param2) {
        Refill_first_Question fragment = new Refill_first_Question();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
        }
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refill_first__question, container, false);
        this.viewHolder = new Refill_first_Question_ViewHolder(view);
        this.viewHolder.setRefillFirstQuestionClickListener(this);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRefillFirstQuestionInteractionListener) {
            this.mListener = (onRefillFirstQuestionInteractionListener) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement onRefillFirstQuestionInteractionListener");
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void onNextClicked() {
        System.out.println("Refill_first_Question.onNextClicked");
        this.mListener.onRefillFistQuestion();
    }
}
