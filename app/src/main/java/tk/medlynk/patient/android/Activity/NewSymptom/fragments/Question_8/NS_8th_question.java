package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_8;

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

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Activity.NewSymptom.OnNewSymptomAnswerListener;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_8th_question.OnNewSymptomEighthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_8th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_8th_question extends Fragment implements
        OnNewSymptomAnswerListener,
        NS_8th_VH.OnEighthNSVHListener {

    public static final String TAG = "NS_8th_question";

    private OnNewSymptomEighthQuestionListener mListener;
    private NS_8th_VH viewHolder;

    public NS_8th_question() {
        // Required empty public constructor
    }

    public static NS_8th_question newInstance(String param1, String param2) {
        NS_8th_question fragment = new NS_8th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_8th_question, container, false );
        viewHolder = new NS_8th_VH ( view );
        viewHolder.setOnEighthNSVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomEighthQuestionListener) {
            mListener = (OnNewSymptomEighthQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomEighthQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_8th_question.onEighthAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onEightQuestion ();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println ( "NS_8th_question.onEightAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onEightQuestion ();
    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_8th_question.onUnauthorized" );
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_8th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomEighthQuestionAnswer ( getActivity ()
                , NS_8th_question.this,
                manager.getAppointmentID ()
                ,answer);
    }

    @Override
    public void onNextClicked(List<Answer> answers) {
        System.out.println ( "NS_8th_question.onNextClicked" );
        System.out.println ("list of answers!");
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomEighthQuestionAnswer ( getActivity ()
                , NS_8th_question.this,
                manager.getAppointmentID ()
                ,answers);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_8th_question.onSkipClicked" );
        mListener.onEightQuestion ();
    }

    public interface OnNewSymptomEighthQuestionListener {
        void onEightQuestion();
    }
}
