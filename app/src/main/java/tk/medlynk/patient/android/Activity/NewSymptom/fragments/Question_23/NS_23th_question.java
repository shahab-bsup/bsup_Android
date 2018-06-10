package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_23;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neweraandroid.demo.R;

import java.util.List;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.Medication;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_23th_question.OnNewSymptomTwenty3QuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_23th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
@Deprecated
public class NS_23th_question extends Fragment implements
        NS_23th_VH.On23QuestionVHListener,
        OnTwentyThreeAnswerListener {

    public static final String TAG = "NS_23th_question";

    private OnNewSymptomTwenty3QuestionListener mListener;
    private NS_23th_VH viewHolder;

    public NS_23th_question() {
        // Required empty public constructor
    }

    public static NS_23th_question newInstance() {
        NS_23th_question fragment = new NS_23th_question();
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

        View view = inflater.inflate ( R.layout.fragment_new__symptom_23th_question, container, false );
        viewHolder = new NS_23th_VH( view );
        viewHolder.setOn23QuestionVHListener ( this );
        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomTwenty3QuestionListener) {
            mListener = (OnNewSymptomTwenty3QuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomTwenty3QuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_23th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new
                SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomTwentyThreeQuestionAnswer ( getActivity (),
                 this, manager.getAppointmentID (),
                answer);
    }

    @Override
    public void onNextClicked(List<Medication> answers) {
        System.out.println ( "NS_23th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new
                SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomTwentyThreeQuestionAnswer ( getActivity (),
                this, manager.getAppointmentID (),
                answers);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_23th_question.onSkipClicked" );
        mListener.onTwenty3Question ();
    }

    @Override
    public void onTwentyThreeAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_23th_question.onTwentyThreeAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTwenty3Question ();
    }

    @Override
    public void onTwentyThreeAnswerFailure() {
        System.out.println ( "NS_23th_question.onTwentyThreeAnswerFailure" );
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_23th_question.onUnauthorized" );

    }


    public interface OnNewSymptomTwenty3QuestionListener {
        void onTwenty3Question();
    }
}
