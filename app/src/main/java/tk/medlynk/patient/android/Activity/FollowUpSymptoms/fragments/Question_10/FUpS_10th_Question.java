package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10;

import android.content.Context;
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
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsTenthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_10th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_10th_Question extends Fragment
        implements FUpS_10th_VH.OnFUpSTenthVHListener
, OnTenthFollowUpAnswerListener
{

    public static final String TAG = FUpS_10th_Question.class.getSimpleName ();

    private OnFollowUpSymptomsTenthQuestionListener mListener;
    private FUpS_10th_VH viewHolder;

    public FUpS_10th_Question() {
        // Required empty public constructor
    }

    public static FUpS_10th_Question newInstance(String param1, String param2) {
        FUpS_10th_Question fragment = new FUpS_10th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_10th__question,
                container,
                false );

        viewHolder = new FUpS_10th_VH( view );
        viewHolder.setOnFUpSTenthVHListener( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsTenthQuestionListener) {
            mListener = (OnFollowUpSymptomsTenthQuestionListener) context;
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
        System.out.println ( "FUpS_10th_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomTenthAnswer( getActivity (),
                manager.getAppointmentID (),
                FUpS_10th_Question.this,
                answer);
    }

    @Override
    public void onNextClicked(List<Answer> answers) {
        System.out.println ( "FUpS_10th_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomTenthAnswer( getActivity (),
                manager.getAppointmentID (),
                FUpS_10th_Question.this,
                answers);
    }

    @Override
    public void onSkipClick() {
        System.out.println ( "FUpS_10th_Question.onSkipClick" );
        mListener.onTenthQuestion ();
    }

    @Override
    public void onTenthAnswerSuccess(FollowUpSymptomResponse response) {
        System.out.println ( "FUpS_10th_Question.onTenthAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTenthQuestion ();
    }

    @Override
    public void onTenthAnswerFailure() {
        System.out.println ( "FUpS_10th_Question.onTenthAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTenthQuestion();
    }

    public interface OnFollowUpSymptomsTenthQuestionListener {
        void onTenthQuestion();
    }
}
