package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_12;

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

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Model.Medication;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsTwelveQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_12th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_12th_Question extends Fragment implements
        FUpS_12th_VH.OnFUpSTwelveVHListener,
        OnFollowUpTwelveAnswerListener {

    public static final String TAG = "FUpS_12th_Question";


    private OnFollowUpSymptomsTwelveQuestionListener mListener;
    private FUpS_12th_VH viewHolder;

    public FUpS_12th_Question() {
        // Required empty public constructor
    }

    public static FUpS_12th_Question newInstance(String param1, String param2) {
        FUpS_12th_Question fragment = new FUpS_12th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_12th__question, container, false );
        viewHolder = new FUpS_12th_VH( view );
        viewHolder.setOnFUpSTwelveVHListener( this );
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onTwelveQuestion ();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsTwelveQuestionListener) {
            mListener = (OnFollowUpSymptomsTwelveQuestionListener) context;
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
    public void onNextClicked(Answer answer) {
        System.out.println ( "FUpS_12th_Question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new
                SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpTwelveQuestionAnswer ( getActivity (),
                this, manager.getAppointmentID (),
                answer);
    }

    @Override
    public void onNextClicked(List<Medication> answers) {
        System.out.println ( "FUpS_12th_Question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new
                SharedPreferenceManager ( getActivity () );
       MedlynkRequests.followUpTwelveQuestionAnswer ( getActivity (),
               this, manager.getAppointmentID (), answers);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "FUpS_12th_Question.onSkipClicked" );
        mListener.onTwelveQuestion ();
    }

    @Override
    public void onTwelveAnswerSuccess(FollowUpSymptomResponse response) {
        System.out.println ( "FUpS_12th_Question.onTwelveAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTwelveQuestion ();
    }

    @Override
    public void onTwelveAnswerFailure() {
        System.out.println ( "FUpS_12th_Question.onTwelveAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }


    public interface OnFollowUpSymptomsTwelveQuestionListener {
        void onTwelveQuestion();
    }
}
