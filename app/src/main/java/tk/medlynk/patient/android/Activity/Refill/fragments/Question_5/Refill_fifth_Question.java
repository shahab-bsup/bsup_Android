package tk.medlynk.patient.android.Activity.Refill.fragments.Question_5;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

public class Refill_fifth_Question extends Fragment implements Refill_fifth_Question_ViewHolder.OnRefillFifthQuestionClickListener {
    public static final String TAG = Refill_fifth_Question.class.getSimpleName();
    private onRefillFifthQuestionInteractionListener mListener;
    private Refill_fifth_Question_ViewHolder viewHolder;

    @Override
    public void onNextClicked() {
        System.out.println ( "Refill_fifth_Question.onNextClicked" );
        mListener.onRefillFifthQuestion ();
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "Refill_fifth_Question.onSkipClicked" );
        mListener.onRefillFifthQuestion ();
    }

    public interface onRefillFifthQuestionInteractionListener {
        void onRefillFifthQuestion();
    }

    public static Refill_fifth_Question newInstance(String param1, String param2) {
        Refill_fifth_Question fragment = new Refill_fifth_Question();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refill_fifth__question, container, false);
        viewHolder = new Refill_fifth_Question_ViewHolder(view);
        viewHolder.setOnRefillFifthQuestionClickListener ( this );
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRefillFifthQuestionInteractionListener) {
            this.mListener = (onRefillFifthQuestionInteractionListener) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement onRefillFirstQuestionInteractionListener");
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}
