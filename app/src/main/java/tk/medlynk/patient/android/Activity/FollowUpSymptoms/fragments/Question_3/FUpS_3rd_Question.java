package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_3;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsThirdQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_3rd_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_3rd_Question extends Fragment
        implements FUpS_3rd_VH.OnFUpSThirdVHListener, OnThirdFollowUpAnswerListener {

    public static final String TAG = FUpS_3rd_Question.class.getSimpleName ();

    private OnFollowUpSymptomsThirdQuestionListener mListener;
    private FUpS_3rd_VH viewHolder;

    public FUpS_3rd_Question() {
        // Required empty public constructor
    }

    public static FUpS_3rd_Question newInstance() {
        FUpS_3rd_Question fragment = new FUpS_3rd_Question();
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
        viewHolder = new FUpS_3rd_VH( view );
        viewHolder.setOnFUpSThirdVHListener( this );
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
        System.out.println ( "FUpS_3rd_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomThirdAnswer( getActivity (),
                manager.getAppointmentID (),
                FUpS_3rd_Question.this,
                answer);
    }

    @Override
    public void onSkipClick() {
        System.out.println ( "FUpS_3rd_Question.onSkipClick" );
        mListener.onThirdQuestion ();
    }

    @Override
    public void onThirdAnswerSuccess(FollowUpSymptomResponse response) {
        System.out.println ( "FUpS_3rd_Question.onThirdAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        if( response.getAnswer().getRate() == 1 ){
            mListener.onJumpToFUpsNinthQuestion();
        }else{
            mListener.onThirdQuestion ();
        }

    }

    @Override
    public void onThirdAnswerFailure() {
        System.out.println ( "FUpS_3rd_Question.onThirdAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText(getActivity(), "try again!", Toast.LENGTH_SHORT).show();
    }

    public interface OnFollowUpSymptomsThirdQuestionListener {
        void onThirdQuestion();

        void onJumpToFUpsNinthQuestion();

    }
}
