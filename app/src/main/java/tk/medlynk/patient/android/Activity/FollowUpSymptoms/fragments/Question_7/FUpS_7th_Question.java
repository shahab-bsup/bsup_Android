package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7;

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
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsSeventhQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_7th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_7th_Question extends Fragment implements FUpS_7th_Question_VH.OnFUpSSeventhVHListener, OnSeventhFollowUpAnswerListener {

    public static final String TAG = "FUpS_7th_Question";

    private OnFollowUpSymptomsSeventhQuestionListener mListener;
    private FUpS_7th_Question_VH viewHolder;

    public FUpS_7th_Question() {
        // Required empty public constructor
    }

    public static FUpS_7th_Question newInstance(String param1, String param2) {
        FUpS_7th_Question fragment = new FUpS_7th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_7th__question, container, false );
        viewHolder = new FUpS_7th_Question_VH( view );
        viewHolder.setOnFUpSSeventhVHListener( this );
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSeventhQuestion ();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsSeventhQuestionListener) {
            mListener = (OnFollowUpSymptomsSeventhQuestionListener) context;
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
        System.out.println ( "FUpS_7th_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomSeventhAnswer( getActivity (),
                manager.getAppointmentID (),
                FUpS_7th_Question.this,
                answer);
    }

    @Override
    public void onSkipClick() {
        System.out.println ( "FUpS_7th_Question.onSkipClick" );
        mListener.onSeventhQuestion ();
    }

    @Override
    public void onSeventhAnswerSuccess(FollowUpSymptomResponse response) {
        System.out.println ( "FUpS_7th_Question.onSeventhAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onSeventhQuestion ();
    }

    @Override
    public void onSeventhAnswerFailure() {
        System.out.println ( "FUpS_7th_Question.onSeventhAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }

    public interface OnFollowUpSymptomsSeventhQuestionListener {
        // TODO: Update argument type and name
        void onSeventhQuestion();
    }
}
