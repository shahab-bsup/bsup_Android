package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8;

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
 * {@link OnFollowUpSymptomsEighthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_8th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_8th_Question extends Fragment implements FUpS_8th_VH.OnFUpSEighthVHListener, OnEighthFollowUpAnswerListener {

    public static final String TAG = "FUpS_8th_Question";


    private OnFollowUpSymptomsEighthQuestionListener mListener;
    private FUpS_8th_VH viewHolder;

    public FUpS_8th_Question() {
        // Required empty public constructor
    }

    public static FUpS_8th_Question newInstance(String param1, String param2) {
        FUpS_8th_Question fragment = new FUpS_8th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_8th__question, container, false );
        viewHolder = new FUpS_8th_VH( view );
        viewHolder.setOnFUpSEighthVHListener( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsEighthQuestionListener) {
            mListener = (OnFollowUpSymptomsEighthQuestionListener) context;
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
        System.out.println ( "FUpS_8th_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomEightAnswer( getActivity (),
                manager.getAppointmentID (),
                FUpS_8th_Question.this,
                answer);
    }

    @Override
    public void onSkipClick() {
        System.out.println ( "FUpS_8th_Question.onSkipClick" );
        mListener.onEighthQuestion ();
    }

    @Override
    public void onEighthAnswerSuccess(FollowUpSymptomResponse response) {
        System.out.println ( "FUpS_8th_Question.onEighthAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onEighthQuestion ();
    }

    @Override
    public void onEightAnswerFailure() {
        System.out.println ( "FUpS_8th_Question.onEightAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFollowUpSymptomsEighthQuestionListener {
        // TODO: Update argument type and name
        void onEighthQuestion();
    }
}
