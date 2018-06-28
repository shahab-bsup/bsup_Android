package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_11;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neweraandroid.demo.R;

import java.util.List;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.FollowUpSymptomsActivity;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsEleventhQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_11th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_11th_Question extends Fragment implements
        FUpS_11th_VH.OnFUpSEleventhVHListener, OnEleventFollowUphAnswerListener {

    public static final String TAG = "FUpS_11th_Question";

    private OnFollowUpSymptomsEleventhQuestionListener mListener;
    private OnFURFourteenQuestionInteractionListener mListenerFUR;
    private FUpS_11th_VH viewHolder;

    public FUpS_11th_Question() {
        // Required empty public constructor
    }

    public static FUpS_11th_Question newInstance() {
        FUpS_11th_Question fragment = new FUpS_11th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_11th__question, container, false );
        viewHolder = new FUpS_11th_VH( view );
        viewHolder.setOnFUpSEleventhVHListener( this );
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onEleventhQuestion ();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsEleventhQuestionListener) {
            mListener = (OnFollowUpSymptomsEleventhQuestionListener) context;
        }
        else if (context instanceof OnFURFourteenQuestionInteractionListener) {
            mListenerFUR = (OnFURFourteenQuestionInteractionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnFollowUpSymptomsFirstQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
        mListenerFUR=null;
    }

    @Override
    public void onNextClicked(List<Answer> answers) {
        System.out.println ( "FUpS_11th_Question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpEleventhQuestionAnswer ( getActivity (),
                FUpS_11th_Question.this,
                manager.getAppointmentID (),
                answers
        );
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "FUpS_11th_Question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpEleventhQuestionAnswer ( getActivity (),
                FUpS_11th_Question.this,
                manager.getAppointmentID (),
                answer
        );
    }

    @Override
    public void onSkipClicked() {
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            System.out.println("FUpS_11th_Question.onSkipClicked");
            mListener.onEleventhQuestion();
        }
        else {
            mListenerFUR.onFURFourteenQuestion();
        }
    }

    @Override
    public void onEleventhAnswerSuccess(FollowUpSymptomResponse response) {
        if(Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            System.out.println("FUpS_11th_Question.onEleventhAnswerSuccess");
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListener.onEleventhQuestion();
        }
        else {
            viewHolder.setProgressBarVisibilityStatus(View.GONE);
            mListenerFUR.onFURFourteenQuestion();
        }
    }

    @Override
    public void onEleventhAnswerFailure() {

        System.out.println("FUpS_11th_Question.onEleventhAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }


    public interface OnFollowUpSymptomsEleventhQuestionListener {
        void onEleventhQuestion();
    }
    public interface OnFURFourteenQuestionInteractionListener{
        void onFURFourteenQuestion();
    }
}
