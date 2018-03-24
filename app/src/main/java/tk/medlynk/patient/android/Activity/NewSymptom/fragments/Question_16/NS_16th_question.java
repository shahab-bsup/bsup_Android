package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_16;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_16th_question.OnNewSymptomSixteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_16th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_16th_question extends Fragment implements
        OnSixteenAnswerListener,
        NS_16th_VH.OnSixteenNSVHListener {

    public static final String TAG = NS_16th_question.class.getSimpleName ();


    private OnNewSymptomSixteenQuestionListener mListener;


    private NS_16th_VH viewHolder;
    private int selected_choice = -1;

    public NS_16th_question() {
        // Required empty public constructor
    }

    public static NS_16th_question newInstance() {
        NS_16th_question fragment = new NS_16th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_16th_question, container, false );
        viewHolder = new NS_16th_VH ( view );
        viewHolder.setOnSixteenNSVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomSixteenQuestionListener) {
            mListener = (OnNewSymptomSixteenQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomSixteenQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onSixteenAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_16th_question.onSixteenAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onSixteenQuestion ();
    }

    @Override
    public void onSixteenAnswerFailure() {
        System.out.println ( "NS_16th_question.onSixteenAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_16th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomSixteenQuestionAnswer ( getActivity (),
                NS_16th_question.this,
                manager.getAppointmentID (),
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_16th_question.onSkipClicked" );
        mListener.onSixteenQuestion ();
    }

    public interface OnNewSymptomSixteenQuestionListener {
        void onSixteenQuestion();
    }
}
