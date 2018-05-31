package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_10;

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
 * {@link OnNewSymptomTenthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_10th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_10th_question extends Fragment implements
         OnTenthAnswerListener,
        NS_10th_VH.OnTenthNSVHListener {

    public static final String TAG = "NS_10th_question";


    private OnNewSymptomTenthQuestionListener mListener;

    private NS_10th_VH viewHolder;

    public NS_10th_question() {
        // Required empty public constructor
    }

    public static NS_10th_question newInstance(String param1, String param2) {
        NS_10th_question fragment = new NS_10th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_10th_question, container, false );
        viewHolder = new NS_10th_VH ( view );
        viewHolder.setOnTenthNSVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomTenthQuestionListener) {
            mListener = (OnNewSymptomTenthQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomTenthQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }


    @Override
    public void onTenthAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_10th_question.onTenthAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTenthQuestion ();
    }

    @Override
    public void onTenthAnswerFailure() {
        System.out.println ( "NS_10th_question.onTenthAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_10th_question.onUnauthorized" );

    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_10th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomTenthQuestionAnswer ( getActivity (),
                NS_10th_question.this,
                manager.getAppointmentID (),
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_10th_question.onSkipClicked" );
        mListener.onTenthQuestion ();
    }

    public interface OnNewSymptomTenthQuestionListener {
        void onTenthQuestion();
    }
}
