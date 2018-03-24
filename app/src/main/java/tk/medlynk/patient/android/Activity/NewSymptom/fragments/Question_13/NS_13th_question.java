package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13;

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
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnNewSymptomThirteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_13th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_13th_question extends Fragment implements
        NS_13th_VH.OnThirteenNSVHListener, OnThirteenAnswerListener {

    public final static String TAG = NS_13th_question.class.getSimpleName ();

    private OnNewSymptomThirteenQuestionListener mListener;
    private NS_13th_VH viewHolder;

    public NS_13th_question() {
        // Required empty public constructor...
    }

    public static NS_13th_question newInstance() {
        NS_13th_question fragment = new NS_13th_question ();
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

        View view = inflater.inflate ( R.layout.fragment_new__symptom_13th_question, container, false );
        viewHolder = new NS_13th_VH ( view );
        viewHolder.setOnThirteenNSVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomThirteenQuestionListener) {
            mListener = (OnNewSymptomThirteenQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomThirteenQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_13th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomThirteenQuestionAnswer ( getActivity (),
                NS_13th_question.this,
                manager.getAppointmentID (),
                answer
        );
    }

    @Override
    public void onNextClicked(List<Answer> answers) {
        System.out.println ( "NS_13th_question.onNextClicked" );
        System.out.println ("list of answers");
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomThirteenQuestionAnswer ( getActivity (),
                NS_13th_question.this,
                manager.getAppointmentID (),
                answers );
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_13th_question.onSkipClicked" );
        mListener.onThirteenQuestion ();
    }

    @Override
    public void onThirteenAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_13th_question.onThirteenAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        mListener.onThirteenQuestion ();
    }

    @Override
    public void onThirteenAnswerFailure() {
        System.out.println ( "NS_13th_question.onThirteenAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onThirteenQuestion ();
    }

    public interface OnNewSymptomThirteenQuestionListener {
        void onThirteenQuestion();
    }
}