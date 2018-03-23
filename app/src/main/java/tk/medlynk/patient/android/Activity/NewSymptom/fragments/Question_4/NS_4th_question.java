package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_4;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_4.OnFourthFollowUpAnswerListener;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpResultResponse;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_4th_question.OnNewSymptomFourthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_4th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_4th_question extends Fragment implements
        OnFourthAnswerListener,
        NS_4thVH.OnFourthNSVHListener{

    public static final String TAG = NS_4th_question.class.getSimpleName ();

    private OnNewSymptomFourthQuestionListener mListener;
    private NS_4thVH viewHolder;

    public NS_4th_question() {
        // Required empty public constructor...
    }

    public static NS_4th_question newInstance(String param1, String param2) {
        NS_4th_question fragment = new NS_4th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_4th_question, container, false );
        viewHolder = new NS_4thVH ( view );
        viewHolder.setOnFourthNSVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomFourthQuestionListener) {
            mListener = (OnNewSymptomFourthQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomFourthQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onFourthAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_4th_question.onFourthAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onFourthQuestion ();
    }

    @Override
    public void onFourthAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        System.out.println ( "NS_4th_question.onFourthAnswerFailure" );
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_4th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomFourthQuestionAnswer (getActivity (),
                NS_4th_question.this,
                manager.getAppointmentID (),
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_4th_question.onSkipClicked" );
        mListener.onFourthQuestion ();
    }

    public interface OnNewSymptomFourthQuestionListener {
        void onFourthQuestion();
    }

}
