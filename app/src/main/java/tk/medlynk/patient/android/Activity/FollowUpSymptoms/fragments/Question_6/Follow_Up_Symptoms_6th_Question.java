package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_6;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_5.Follow_Up_Symptoms_5th_Question;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpResultResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsSixthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link Follow_Up_Symptoms_6th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Follow_Up_Symptoms_6th_Question extends Fragment implements Follow_Up_Symptoms_6th_Question_ViewHolder.OnFollowUpSixthQuestionViewsClickListener, OnSixthFollowUpAnswerListener {

    public static final String TAG = Follow_Up_Symptoms_6th_Question.class.getSimpleName ();

    private OnFollowUpSymptomsSixthQuestionListener mListener;
    private Follow_Up_Symptoms_6th_Question_ViewHolder viewHolder;

    public Follow_Up_Symptoms_6th_Question() {
        // Required empty public constructor
    }

    public static Follow_Up_Symptoms_6th_Question newInstance(String param1, String param2) {
        Follow_Up_Symptoms_6th_Question fragment = new Follow_Up_Symptoms_6th_Question ();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_6th__question, container, false );
        viewHolder = new Follow_Up_Symptoms_6th_Question_ViewHolder ( view );
        viewHolder.setOnFollowUpSixthQuestionViewsClickListener ( this );
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSixthQuestion ();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsSixthQuestionListener) {
            mListener = (OnFollowUpSymptomsSixthQuestionListener) context;
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
        System.out.println ( "Follow_Up_Symptoms_6th_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpResultSixthAnswer ( getActivity (),
                manager.getAppointmentID (),
                Follow_Up_Symptoms_6th_Question.this,
                answer);
    }

    @Override
    public void onSkipClick() {
        System.out.println ( "Follow_Up_Symptoms_6th_Question.onSkipClick" );
        mListener.onSixthQuestion ();
    }

    @Override
    public void onSixthAnswerSuccess(FollowUpResultResponse response) {
        System.out.println ( "Follow_Up_Symptoms_6th_Question.onSixthAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onSixthQuestion ();
    }

    @Override
    public void onSixthAnswerFailure() {
        System.out.println ( "Follow_Up_Symptoms_6th_Question.onSixthAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFollowUpSymptomsSixthQuestionListener {
        // TODO: Update argument type and name
        void onSixthQuestion();
    }
}
