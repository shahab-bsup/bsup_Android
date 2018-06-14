package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_14;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import java.util.List;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsFourteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_14th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_14th_Question extends Fragment implements
        FUpS_14th_VH.OnFUpSFourteenVHListener,
OnFourteenFollowUpAnswerListener{

    public static final String TAG = "FUpS_14th_Question";

    private OnFollowUpSymptomsFourteenQuestionListener mListener;
    private FUpS_14th_VH viewHolder;

    public FUpS_14th_Question() {
        // Required empty public constructor
    }

    public static FUpS_14th_Question newInstance(String param1, String param2) {
        FUpS_14th_Question fragment = new FUpS_14th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_14th__question, container, false );
        viewHolder = new FUpS_14th_VH( view );
        viewHolder.setOnFUpSFourteenVHListener( this );
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsFourteenQuestionListener) {
            mListener = (OnFollowUpSymptomsFourteenQuestionListener) context;
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
    public void onNextClick(List<Answer> answers) {
        System.out.println ( "FUpS_14th_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomFourteenAnswer ( getActivity (),
                manager.getAppointmentID (), this, answers);
    }

    @Override
    public void onSkipClick() {
        System.out.println ( "FUpS_14th_Question.onSkipClick" );
        mListener.onFourteenQuestion ();
    }

    @Override
    public void onNextClick(Answer answer) {
        System.out.println("FUpS_14th_Question.onNextClick");
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomFourteenAnswer ( getActivity (),
                manager.getAppointmentID (), this, answer);
    }

    @Override
    public void onThirteenAnswerSuccess(FollowUpSymptomResponse response) {
        System.out.println ( "FUpS_14th_Question.onThirteenAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onFourteenQuestion ();
    }

    @Override
    public void onThirteenAnswerFailure() {
        System.out.println ( "FUpS_14th_Question.onThirteenAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }


    public interface OnFollowUpSymptomsFourteenQuestionListener {
        void onFourteenQuestion();
    }
}
