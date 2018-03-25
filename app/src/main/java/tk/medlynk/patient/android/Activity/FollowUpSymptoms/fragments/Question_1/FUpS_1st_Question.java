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
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsFirstQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_1st_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_1st_Question extends Fragment implements
        FUpS__1st_VH.OnFUpSFirstVHListener,
        OnFirstFollowUpAnswerListener
    {

        public static final String TAG = FUpS_1st_Question.class.getSimpleName ();
        private OnFollowUpSymptomsFirstQuestionListener mListener;
    private FUpS__1st_VH viewHolder;

    public FUpS_1st_Question() {
        // Required empty public constructor
    }

    public static FUpS_1st_Question newInstance() {
        FUpS_1st_Question fragment = new FUpS_1st_Question();
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
        viewHolder = new FUpS__1st_VH( view );
        viewHolder.setOnFUpSFirstVHListener( this );
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
            System.out.println ( "FUpS_1st_Question.onNextClick" );
            viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
            SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
            Answer answer = new Answer ();
            answer.setReply ( viewHolder.getAnswerInput() );
            MedlynkRequests.followUpSymptomFirstAnswer(getActivity (), FUpS_1st_Question.this,
                    manager.getAppointmentID (),
                    answer);
        }

        @Override
        public void onFirstAnswerSuccess(FollowUpSymptomResponse response) {
            System.out.println ( "FUpS_1st_Question.onFirstAnswerSuccess" );
            viewHolder.setProgressBarVisibilityStatus ( View.GONE );
            mListener.onFirstQuestion ();
        }

        @Override
        public void onFirstAnswerFailure() {
            System.out.println ( "FUpS_1st_Question.onFirstAnswerFailure" );
            viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        }

    public interface OnFollowUpSymptomsFirstQuestionListener {
        void onFirstQuestion();
    }
}
