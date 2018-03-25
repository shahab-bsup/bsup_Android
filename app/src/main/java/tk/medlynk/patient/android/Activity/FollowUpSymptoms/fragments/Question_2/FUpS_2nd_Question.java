package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_2;

import android.content.Context;
import android.net.Uri;
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
 * {@link OnFollowUpSymptomsSecondQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_2nd_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_2nd_Question extends Fragment implements FUpS_2nd_VH.OnFUpSSecondVHListener, OnSecondFollowUpAnswerListener {

    public static final String TAG = FUpS_2nd_Question.class.getSimpleName ();


    private OnFollowUpSymptomsSecondQuestionListener mListener;
    private FUpS_2nd_VH viewHolder;

    public FUpS_2nd_Question() {
        // Required empty public constructor
    }

    public static FUpS_2nd_Question newInstance(String param1, String param2) {
        FUpS_2nd_Question fragment = new FUpS_2nd_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_2nd__question, container, false );
        viewHolder = new FUpS_2nd_VH( view );
        viewHolder.setOnFUpSSecondVHListener( this );
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSecondQuestion ();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsSecondQuestionListener) {
            mListener = (OnFollowUpSymptomsSecondQuestionListener) context;
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
        System.out.println ( "FUpS_2nd_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomSecondAnswer( getActivity (),
                FUpS_2nd_Question.this,
                manager.getAppointmentID (), answer);
    }

    @Override
    public void onSkipClick() {
        System.out.println ( "FUpS_2nd_Question.onSkipClick" );
        mListener.onSecondQuestion ();
    }

    @Override
    public void onSecondAnswerSuccess(FollowUpSymptomResponse response) {
        System.out.println ( "FUpS_2nd_Question.onSecondAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onSecondQuestion ();
    }

    @Override
    public void onSecondAnswerFailure() {
        System.out.println ( "FUpS_2nd_Question.onSecondAnswerFailure" );
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
    public interface OnFollowUpSymptomsSecondQuestionListener {
        // TODO: Update argument type and name
        void onSecondQuestion();
    }
}
