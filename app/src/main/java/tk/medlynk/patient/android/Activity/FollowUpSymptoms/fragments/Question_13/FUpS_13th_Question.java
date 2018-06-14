package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_13;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8.OnEighthFollowUpAnswerListener;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsThirteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_13th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_13th_Question extends Fragment implements
        FUpS_13th_VH.OnFUpSThirteenVHListener,
        OnThirteenFollowUpAnswerListener {

    public static final String TAG = "FUpS_13th_Question";


    private OnFollowUpSymptomsThirteenQuestionListener mListener;
    private FUpS_13th_VH viewHolder;

    public FUpS_13th_Question() {
        // Required empty public constructor...

    }

    public static FUpS_13th_Question newInstance(String param1, String param2) {
        FUpS_13th_Question fragment = new FUpS_13th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_13th__question, container, false );
        viewHolder = new FUpS_13th_VH( view );
        viewHolder.setOnFUpSThirteenVHListener( this );
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsThirteenQuestionListener) {
            mListener = (OnFollowUpSymptomsThirteenQuestionListener) context;
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
        System.out.println ( "FUpS_13th_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        SharedPreferenceManager manager = new SharedPreferenceManager(getActivity());
        MedlynkRequests.followUpSymptomThirteenAnswer(getActivity(),
                manager.getAppointmentID(),
                this, answer);
    }

    @Override
    public void onSkipClick() {
        System.out.println ( "FUpS_13th_Question.onSkipClick" );
        mListener.onThirteenQuestion ();
    }

    @Override
    public void onThirteenAnswerSuccess(FollowUpSymptomResponse response) {
        System.out.println("FUpS_13th_Question.onEighthAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onThirteenQuestion();
    }

    @Override
    public void onThirteenAnswerFailure() {
        System.out.println("FUpS_13th_Question.onEightAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
    }


    public interface OnFollowUpSymptomsThirteenQuestionListener {
        void onThirteenQuestion();
    }
}
