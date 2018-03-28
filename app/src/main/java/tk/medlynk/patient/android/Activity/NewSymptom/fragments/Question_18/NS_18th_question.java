package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_18;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_19.NS_19th_question;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_18th_question.OnNewSymptomEighteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_18th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_18th_question extends Fragment implements
        OnEighteenAnswerListener,
        NS_18th_VH.OnEighteenNSVHListener {

    public static final String TAG = NS_19th_question.class.getSimpleName ();

    private OnNewSymptomEighteenQuestionListener mListener;

    private NS_18th_VH viewHolder;

    public NS_18th_question() {
        // Required empty public constructor
    }

    public static NS_18th_question newInstance() {
        NS_18th_question fragment = new NS_18th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_18th_question, container, false );
        viewHolder = new NS_18th_VH ( view );
        viewHolder.setOnEighteenNSVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomEighteenQuestionListener) {
            mListener = (OnNewSymptomEighteenQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomEighteenQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onEighteenAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_18th_question.onEighteenAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        if( response.getAnswer ().getChoice ().equals ( "b" ) ){
            mListener.onJumpToEnd ();
        }else{
            mListener.onEighteenQuestion ();
        }
    }

    @Override
    public void onEighteenAnswerFailure() {
        System.out.println ( "NS_18th_question.onEighteenAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "tra again later!", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_18th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomEighteenQuestionAnswer ( getActivity (),
                NS_18th_question.this,
                manager.getAppointmentID (),
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_18th_question.onSkipClicked" );
        mListener.onEighteenQuestion ();
    }

    public interface OnNewSymptomEighteenQuestionListener {
        void onEighteenQuestion();
        void onJumpToEnd();
    }
}
