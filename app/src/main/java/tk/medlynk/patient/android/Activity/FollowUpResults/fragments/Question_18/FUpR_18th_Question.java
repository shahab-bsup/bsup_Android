package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_18;

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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFUREighteenQuestionInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FUpR_18th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpR_18th_Question extends Fragment implements FUpR_18th_VH.OnFUREighteenVHListener, MotherCallback {

    public static final String TAG = "FUpR_18th_Question";
    private OnFUREighteenQuestionInteractionListener mListener;
    private FUpR_18th_VH viewHolder;

    public FUpR_18th_Question() {
        // Required empty public constructor
    }

    public static FUpR_18th_Question newInstance(String param1, String param2) {
        FUpR_18th_Question fragment = new FUpR_18th_Question();
        Bundle args = new Bundle ();
        fragment.setArguments ( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        if (getArguments () != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_follow_up_results_18th__question, container, false );
        viewHolder = new FUpR_18th_VH(view);
        viewHolder.setOnFUREighteenVHListener(this);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFUREighteenQuestionInteractionListener) {
            mListener = (OnFUREighteenQuestionInteractionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnFURFirstQuestionInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println("FUpR_18th_Question.onNextClicked");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        SharedPreferenceManager manager = new SharedPreferenceManager(getActivity());
        MedlynkRequests.followUpResultEighteenAnswer(getActivity(),
                manager.getAppointmentID(),
                this,
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println("FUpR_18th_Question.onSkipClicked");
        mListener.onFUREighteenQuestion();
    }

    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        System.out.println("FUpR_18th_Question.onAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onFUREighteenQuestion();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("FUpR_18th_Question.onAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
    }

    public interface OnFUREighteenQuestionInteractionListener {
        void onFUREighteenQuestion();
    }
}
