package tk.medlynk.patient.android.Activity.Refill.fragments.Question_1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.Refill.fragments.Question_1.Refill_first_VH.OnRefillFirstQuestionClickListener;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.MotherCallback;
import tk.medlynk.patient.android.Model.SymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;


public class Refill_first_Question extends Fragment implements
        OnRefillFirstQuestionClickListener,
        MotherCallback {
    public static final String TAG = "Refill_first_Question";
    private onRefillFirstQuestionInteractionListener mListener;
    private Refill_first_VH viewHolder;

    public static Refill_first_Question newInstance(String param1, String param2) {
        Refill_first_Question fragment = new Refill_first_Question ();
        fragment.setArguments ( new Bundle () );
        return fragment;
    }

    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        System.out.println ( "Refill_first_Question.onAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        new SharedPreferenceManager ( getActivity () ).setQuestionSetID ( response.getQuestionSetId () );
        mListener.onRefillFistQuestion ();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println ( "Refill_first_Question.onAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        if (getArguments () == null) {

        }
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.fragment_refill_first__question, container, false );
        this.viewHolder = new Refill_first_VH ( view );
        this.viewHolder.setRefillFirstQuestionClickListener ( this );
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof onRefillFirstQuestionInteractionListener) {
            this.mListener = (onRefillFirstQuestionInteractionListener) context;
            return;
        }
        throw new RuntimeException ( context.toString () + " must implement onRefillFirstQuestionInteractionListener" );
    }

    public void onDetach() {
        super.onDetach ();
        this.mListener = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "Refill_first_Question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.refill_first_question ( getActivity (), manager.getAppointmentID (),
                this, answer );
    }

    public interface onRefillFirstQuestionInteractionListener {
        void onRefillFistQuestion();
    }
}
