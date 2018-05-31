package tk.medlynk.patient.android.Activity.Refill.fragments.Question_5;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.MotherCallback;
import tk.medlynk.patient.android.Model.SymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

public class Refill_fifth_Question extends Fragment implements Refill_fifth_VH.OnRefillFifthVHListener, MotherCallback {
    public static final String TAG = "Refill_fifth_Question";
    private onRefillFifthQuestionInteractionListener mListener;
    private Refill_fifth_VH viewHolder;

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "Refill_fifth_Question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        SharedPreferenceManager manager = new SharedPreferenceManager(getActivity());
        MedlynkRequests.refill_fifth_question(getActivity(),
                manager.getAppointmentID(),
                manager.getQuestionSetID(),
                this,
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "Refill_fifth_Question.onSkipClicked" );
        mListener.onRefillFifthQuestion ();
    }

    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        System.out.println("Refill_fifth_Question.onAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onRefillFifthQuestion();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("Refill_fifth_Question.onAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
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
        viewHolder = new Refill_fifth_VH(view);
        viewHolder.setOnRefillFifthVHListener( this );
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
