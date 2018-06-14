package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_20;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_20th_question.OnNewSymptomTwentyQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_20th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
@Deprecated
public class NS_20th_question extends Fragment implements
        NS_20th_VH.OnTwentyNSVHListener,
        OnTwentyAnswerListener {

    public static final String TAG = "NS_20th_question";

    private OnNewSymptomTwentyQuestionListener mListener;
    private NS_20th_VH viewHolder;
    private String[] lastsIDStrings;
    private Answer answer;
    private int lastsValue;

    public NS_20th_question() {
        // Required empty public constructor
    }

    public static NS_20th_question newInstance() {
        NS_20th_question fragment = new NS_20th_question();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_20th_question, container, false );
        viewHolder = new NS_20th_VH( view );
        viewHolder.setOnTwentyNSVHListener( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomTwentyQuestionListener) {
            mListener = (OnNewSymptomTwentyQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomTwentyQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        System.out.println ( "NS_20th_question.onDetach" );
        mListener = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_20th_question.onNextClicked" );
        viewHolder.setProgressVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        /*MedlynkRequests.newSymptomTwentyQuestionAnswer ( getActivity (),
                NS_20th_question.this,
                manager.getAppointmentID (),
                answer);*/
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_20th_question.onSkipClicked" );
        mListener.onTwentyQuestion ();
    }


    @Override
    public void onTwentyAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_20th_question.onTwentyAnswerSuccess" );
        viewHolder.setProgressVisibilityStatus ( View.GONE );
        mListener.onTwentyQuestion ();
    }

    @Override
    public void onTwentyAnswerFailure() {
        System.out.println ( "NS_20th_question.onTwentyAnswerFailure" );
        viewHolder.setProgressVisibilityStatus ( View.GONE );
        Toast.makeText(getActivity(), "try again later!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_20th_question.onUnauthorized" );

    }

    public interface OnNewSymptomTwentyQuestionListener {
        void onTwentyQuestion();
    }
}
