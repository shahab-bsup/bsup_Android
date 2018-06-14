package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_15;

import android.content.Context;
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
 * {@link OnFollowUpSymptomsFifteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_15th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_15th_Question extends Fragment implements
        FUpS_15th_VH.OnFUpSFifteenVHListener, OnFifteenFollowUpAnswerListener {

    public static final String TAG = "FUpS_15th_Question";


    private OnFollowUpSymptomsFifteenQuestionListener mListener;
    private FUpS_15th_VH viewHolder;

    public FUpS_15th_Question() {
        // Required empty public constructor
    }


    public static FUpS_15th_Question newInstance(String param1, String param2) {
        FUpS_15th_Question fragment = new FUpS_15th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_15th__question, container, false );
        viewHolder = new FUpS_15th_VH( view );
        viewHolder.setOnFUpSFifteenVHListener( this );
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsFifteenQuestionListener) {
            mListener = (OnFollowUpSymptomsFifteenQuestionListener) context;
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
        System.out.println ( "FUpS_15th_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        SharedPreferenceManager manager = new SharedPreferenceManager(getActivity());
        MedlynkRequests.followUpSymptomFifteenAnswer(getActivity(),
                manager.getAppointmentID(),
                this, answer);
    }

    @Override
    public void onSkipClick() {
        System.out.println ( "FUpS_15th_Question.onSkipClick" );
        mListener.onFifteenQuestion ();
    }

    @Override
    public void onFifteenAnswerResponse(FollowUpSymptomResponse response) {
        System.out.println("FUpS_15th_Question.onFifteenAnswerResponse");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onFifteenQuestion();
    }

    @Override
    public void onFifteenAnswerFailure() {
        System.out.println("FUpS_15th_Question.onFifteenAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
    }

    public interface OnFollowUpSymptomsFifteenQuestionListener {
        void onFifteenQuestion();
    }
}
