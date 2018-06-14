package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_22;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_22th_question.OnNewSymptomTwenty2QuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_22th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
@Deprecated
public class NS_22th_question extends Fragment implements
        OnTwentyTwoAnswerListener,
        NS_22th_VH.On22thVHListener {

    public static final String TAG = "NS_22th_question";

    private OnNewSymptomTwenty2QuestionListener mListener;
    private NS_22th_VH viewHolder;

    public NS_22th_question() {
        // Required empty public constructor
    }


    public static NS_22th_question newInstance() {
        NS_22th_question fragment = new NS_22th_question();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_22th_question, container, false );
        viewHolder = new NS_22th_VH( view );
        viewHolder.setOn22thVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomTwenty2QuestionListener) {
            mListener = (OnNewSymptomTwenty2QuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomTwenty2QuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClicked(List<Answer> answers) {
        System.out.println ( "NS_22th_question.onNextClicked" );
        System.out.println ("list of answers");
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
       /* MedlynkRequests.newSymptomTwentyTwoQuestionAnswer ( getActivity (),
                NS_22th_question.this,
                manager.getAppointmentID (),
                answers
        );*/
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_22th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
       /* MedlynkRequests.newSymptomTwentyTwoQuestionAnswer ( getActivity (),
                NS_22th_question.this,
                manager.getAppointmentID (),
                answer
        );*/
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_22th_question.onSkipClicked" );
        mListener.onTwenty2Question ();
    }

    @Override
    public void onTwentyTwoAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_22th_question.onTwentyTwoAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTwenty2Question ();
    }

    @Override
    public void onTwentyTwoAnswerFailure() {
        System.out.println ( "NS_22th_question.onTwentyTwoAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTwenty2Question ();
    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_22th_question.onUnauthorized" );

    }


    public interface OnNewSymptomTwenty2QuestionListener {
        void onTwenty2Question();

    }
}
