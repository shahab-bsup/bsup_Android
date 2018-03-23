package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_2nd_question.OnNewSymptomSecondQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_2nd_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_2nd_question extends Fragment implements
        OnSecondAnswerListener,
        NS_2nd_VH.OnSecondNSVHListener {

    public static final String TAG = NS_2nd_question.class.getSimpleName ();
    private OnNewSymptomSecondQuestionListener mListener;
    private NS_2nd_VH viewHolder;


    public NS_2nd_question() {
        // Required empty public constructor
    }

    public static NS_2nd_question newInstance() {
        NS_2nd_question fragment = new NS_2nd_question ();
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
        // Inflate the layout for this fragment...
        View view = inflater.inflate ( R.layout.fragment_new__symptom_2nd_question, container, false );;
        viewHolder = new NS_2nd_VH ( view );
        viewHolder.setOnSecondNSVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomSecondQuestionListener) {
            mListener = (OnNewSymptomSecondQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomSecondQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onSecondAnswerSuccess(NewSymptomAnswerResponse response) {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onSecondQuestion ();
    }

    @Override
    public void onSecondAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again!", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_2nd_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomSecondQuestionAnswer ( getActivity (),
                NS_2nd_question.this,
                manager.getAppointmentID (),
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_2nd_question.onSkipClicked" );
        mListener.onSecondQuestion ();
    }

    public interface OnNewSymptomSecondQuestionListener {
        // TODO: Update argument type and name
        void onSecondQuestion();
    }
}