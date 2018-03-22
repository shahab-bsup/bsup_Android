package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpResultResponse;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsFirstQuestionListener} interface
 * to handle interaction events.
 * Use the {@link Follow_Up_Symptoms_1st_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Follow_Up_Symptoms_1st_Question extends Fragment implements
    Follow_Up_Symptoms_1st_Question_ViewHolder.OnFollowUpFirstQuestionViewsClickListener,
        OnFirstFollowUpAnswerListener
    {

    private OnFollowUpSymptomsFirstQuestionListener mListener;
    private Follow_Up_Symptoms_1st_Question_ViewHolder viewHolder;

    public Follow_Up_Symptoms_1st_Question() {
        // Required empty public constructor
    }

    public static Follow_Up_Symptoms_1st_Question newInstance() {
        Follow_Up_Symptoms_1st_Question fragment = new Follow_Up_Symptoms_1st_Question ();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_1st__question, container, false );
        viewHolder = new Follow_Up_Symptoms_1st_Question_ViewHolder ( view );
        viewHolder.setOnFollowUpFirstQuestionViewsClickListener ( this );
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsFirstQuestionListener) {
            mListener = (OnFollowUpSymptomsFirstQuestionListener) context;
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
        public void onNextClick() {
            System.out.println ( "Follow_Up_Symptoms_1st_Question.onNextClick" );
            viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
            SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
            Answer answer = new Answer ();
            answer.setReply ( viewHolder.getAnswerInput() );
            MedlynkRequests.followUpResultFirstAnswer(getActivity (), Follow_Up_Symptoms_1st_Question.this,
                    manager.getAppointmentID (),
                    answer);
        }

        @Override
        public void onFirstAnswerSuccess(FollowUpResultResponse response) {
            System.out.println ( "Follow_Up_Symptoms_1st_Question.onFirstAnswerSuccess" );
            viewHolder.setProgressBarVisibilityStatus ( View.GONE );
            mListener.onFirstQuestion ();
        }

        @Override
        public void onFirstAnswerFailure() {
            System.out.println ( "Follow_Up_Symptoms_1st_Question.onFirstAnswerFailure" );
            viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        }

    public interface OnFollowUpSymptomsFirstQuestionListener {
        void onFirstQuestion();
    }
}
