package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_3;

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
 * {@link NS_3rd_question.OnNewSymptomThirdQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_3rd_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_3rd_question extends Fragment implements
        OnThirdAnswerListener,
        NS_3rd_VH.OnThirdNSVHListener {

    public static final String TAG = NS_3rd_question.class.getSimpleName ();

    private OnNewSymptomThirdQuestionListener mListener;
    private NS_3rd_VH viewHolder;

    public NS_3rd_question() {
        // Required empty public constructor
    }

    public static NS_3rd_question newInstance() {
        NS_3rd_question fragment = new NS_3rd_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_3rd_question, container, false );
        viewHolder = new NS_3rd_VH ( view );
        viewHolder.setOnThirdNSVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomThirdQuestionListener) {
            mListener = (OnNewSymptomThirdQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomThirdQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }


    @Override
    public void onThirdAnswerSuccess(NewSymptomAnswerResponse response) {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onThirdQuestion ();
    }

    @Override
    public void onThirdAnswerFailure() {
        System.out.println ( "NS_3rd_question.onThirdAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_3rd_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomThirdQuestionAnswer ( getActivity (),
                NS_3rd_question.this ,
                manager.getAppointmentID (),
                answer);
    }

    @Override
    public void onSkipClicked() {
        mListener.onThirdQuestion ();
    }


    public interface OnNewSymptomThirdQuestionListener {
        void onThirdQuestion();
    }
}