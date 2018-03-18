package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import java.util.List;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_14.Follow_Up_Symptoms_14th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9.Follow_Up_Symptoms_9th_Question;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpResultResponse;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsTenthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link Follow_Up_Symptoms_10th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Follow_Up_Symptoms_10th_Question extends Fragment
        implements Follow_Up_Symptoms_10th_Question_ViewHolder.OnFollowUpTenthQuestionViewsClickListener
, OnTenthFollowUpAnswerListener
{

    public static final String TAG = Follow_Up_Symptoms_10th_Question.class.getSimpleName ();

    private OnFollowUpSymptomsTenthQuestionListener mListener;
    private Follow_Up_Symptoms_10th_Question_ViewHolder viewHolder;

    public Follow_Up_Symptoms_10th_Question() {
        // Required empty public constructor
    }

    public static Follow_Up_Symptoms_10th_Question newInstance(String param1, String param2) {
        Follow_Up_Symptoms_10th_Question fragment = new Follow_Up_Symptoms_10th_Question ();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_10th__question, container, false );
        viewHolder = new Follow_Up_Symptoms_10th_Question_ViewHolder ( view );
        viewHolder.setOnFollowUpTenthQuestionViewsClickListener ( this );
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
    public void onNextClick(Answer answer) {
        System.out.println ( "Follow_Up_Symptoms_10th_Question.onNextClick" );
        mListener.onTenthQuestion ();
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpResultTenthAnswer ( getActivity (),
                manager.getAppointmentID (),
                Follow_Up_Symptoms_10th_Question.this,
                answer);
    }

    @Override
    public void onNextClick(List<Answer> answers) {
        System.out.println ( "Follow_Up_Symptoms_10th_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpResultTenthAnswer ( getActivity (),
                manager.getAppointmentID (),
                Follow_Up_Symptoms_10th_Question.this,
                answers);
    }

    @Override
    public void onSkipClick() {
        System.out.println ( "Follow_Up_Symptoms_10th_Question.onSkipClick" );
        mListener.onTenthQuestion ();
    }

    @Override
    public void onTenthAnswerSuccess(FollowUpResultResponse response) {
        System.out.println ( "Follow_Up_Symptoms_10th_Question.onTenthAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTenthQuestion ();
    }

    @Override
    public void onTenthAnswerFailure() {
        System.out.println ( "Follow_Up_Symptoms_10th_Question.onTenthAnswerFailure" );
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
    public interface OnFollowUpSymptomsTenthQuestionListener {
        // TODO: Update argument type and name
        void onTenthQuestion();
    }
}
