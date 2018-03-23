package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_7;

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
 * {@link NS_7th_question.OnNewSymptomSeventhQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_7th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_7th_question extends Fragment implements
        OnSeventhAnswerListener, NS_7th_VH.OnSeventhNSVHListener {

    public static final String TAG = NS_7th_question.class.getSimpleName ();


    private OnNewSymptomSeventhQuestionListener mListener;

    private NS_7th_VH viewHolder;

    public NS_7th_question() {
        // Required empty public constructor
    }

    public static NS_7th_question newInstance(String param1, String param2) {
        NS_7th_question fragment = new NS_7th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_7th_question, container, false );
        viewHolder = new NS_7th_VH ( view );
        viewHolder.setOnSeventhNSVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomSeventhQuestionListener) {
            mListener = (OnNewSymptomSeventhQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomSeventhQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onSeventhAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_7th_question.onSeventhAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onSeventhQuestion ();
    }

    @Override
    public void onSeventhAnswerFailure() {
        System.out.println ( "NS_7th_question.onSeventhAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_7th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomSeventhQuestionAnswer ( getActivity (),
                NS_7th_question.this,
                manager.getAppointmentID (),
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_7th_question.onSkipClicked" );
        mListener.onSeventhQuestion ();
    }

    public interface OnNewSymptomSeventhQuestionListener {

        void onSeventhQuestion();
    }
}