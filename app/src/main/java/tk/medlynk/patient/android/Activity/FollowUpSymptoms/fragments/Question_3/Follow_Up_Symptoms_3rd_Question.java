package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpResultResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsThirdQuestionListener} interface
 * to handle interaction events.
 * Use the {@link Follow_Up_Symptoms_3rd_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Follow_Up_Symptoms_3rd_Question extends Fragment
        implements Follow_Up_Symptoms_3rd_Question_ViewHolder.OnFollowUpThirdQuestionViewsClickListener, OnThirdFollowUpAnswerListener {

    public static final String TAG = Follow_Up_Symptoms_3rd_Question.class.getSimpleName ();

    private OnFollowUpSymptomsThirdQuestionListener mListener;
    private Follow_Up_Symptoms_3rd_Question_ViewHolder viewHolder;

    public Follow_Up_Symptoms_3rd_Question() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Follow_Up_Symptoms_3rd_Question.
     */
    // TODO: Rename and change types and number of parameters
    public static Follow_Up_Symptoms_3rd_Question newInstance(String param1, String param2) {
        Follow_Up_Symptoms_3rd_Question fragment = new Follow_Up_Symptoms_3rd_Question ();
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
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_3rd__question, container, false );
        viewHolder = new Follow_Up_Symptoms_3rd_Question_ViewHolder ( view );
        viewHolder.setOnFollowUpThirdQuestionViewsClickListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsThirdQuestionListener) {
            mListener = (OnFollowUpSymptomsThirdQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnFollowUpSymptomsFirstQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClick(Answer answer) {
        System.out.println ( "Follow_Up_Symptoms_3rd_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpResultThirdAnswer ( getActivity (),
                manager.getAppointmentID (),
                Follow_Up_Symptoms_3rd_Question.this,
                answer);
    }

    @Override
    public void onSkipClick() {
        System.out.println ( "Follow_Up_Symptoms_3rd_Question.onSkipClick" );
        mListener.onThirdQuestion ();
    }

    @Override
    public void onThirdAnswerSuccess(FollowUpResultResponse response) {
        System.out.println ( "Follow_Up_Symptoms_3rd_Question.onThirdAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onThirdQuestion ();
    }

    @Override
    public void onThirdAnswerFailure() {
        System.out.println ( "Follow_Up_Symptoms_3rd_Question.onThirdAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }

    public interface OnFollowUpSymptomsThirdQuestionListener {
        void onThirdQuestion();
    }
}
