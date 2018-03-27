package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_16;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_13.OnThirteenFollowUpAnswerListener;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFURSixteenQuestionInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FUpR_16th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpR_16th_Question extends Fragment implements FUpR_16th_VH.OnFURSixteenVHListener, OnThirteenFollowUpAnswerListener {


    private OnFURSixteenQuestionInteractionListener mListener;
    private FUpR_16th_VH viewHolder;

    public FUpR_16th_Question() {
        // Required empty public constructor
    }

    public static FUpR_16th_Question newInstance(String param1, String param2) {
        FUpR_16th_Question fragment = new FUpR_16th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow_up_results_16th__question, container, false );
        viewHolder = new FUpR_16th_VH(view);
        viewHolder.setOnFURSixteenVHListener(this);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFURSixteenQuestionInteractionListener) {
            mListener = (OnFURSixteenQuestionInteractionListener) context;
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
        System.out.println("FUpR_16th_Question.onNextClicked");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        SharedPreferenceManager manager = new SharedPreferenceManager(getActivity());
        MedlynkRequests.followUpSymptomThirteenAnswer(getActivity(),
                manager.getAppointmentID(),
                this,
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println("FUpR_16th_Question.onSkipClicked");
        mListener.onFURSixteenQuestion();
    }

    @Override
    public void onThirteenAnswerSuccess(FollowUpSymptomResponse response) {
        System.out.println("FUpR_16th_Question.onThirteenAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onFURSixteenQuestion();
    }

    @Override
    public void onThirteenAnswerFailure() {
        System.out.println("FUpR_16th_Question.onThirteenAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
    }


    public interface OnFURSixteenQuestionInteractionListener {
        void onFURSixteenQuestion();
    }
}
