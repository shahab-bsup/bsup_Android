package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_6;

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

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_6th_question.OnNewSymptomSixthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_6th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_6th_question extends Fragment implements
        OnSixthAnswerListener,
        NS_6th_VH.OnSixthNSVHListener {

    public static final String TAG = "NS_6th_question";

    private OnNewSymptomSixthQuestionListener mListener;

    private NS_6th_VH viewHolder;

    public NS_6th_question() {
        // Required empty public constructor
    }

    public static NS_6th_question newInstance(String param1, String param2) {
        NS_6th_question fragment = new NS_6th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_6th_question, container, false );
        viewHolder = new NS_6th_VH ( view );
        viewHolder.setOnSixthNSVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomSixthQuestionListener) {
            mListener = (OnNewSymptomSixthQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomEleventhQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }
    
    @Override
    public void onSixthAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_6th_question.onSixthAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onSixthQuestion ();
    }

    @Override
    public void onSixthAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        System.out.println ( "NS_6th_question.onSixthAnswerFailure" );
    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_6th_question.onUnauthorized" );

    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_6th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomSixthQuestionAnswer ( getActivity (),
                NS_6th_question.this,
                manager.getAppointmentID (),
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_6th_question.onSkipClicked" );
        mListener.onSixthQuestion ();
    }

    public interface OnNewSymptomSixthQuestionListener {
        void onSixthQuestion();
    }
}
