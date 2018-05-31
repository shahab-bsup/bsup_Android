package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_14;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neweraandroid.demo.R;

import java.util.List;

import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_15.NS_15th_question;
import tk.medlynk.patient.android.Essentials.MedicationsAdapter;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.Medication;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_14th_question.OnNewSymptomFourteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_14th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_14th_question extends Fragment implements
        NS_14th_VH.OnFourteenNSVHListener,
        OnFourteenAnswerListener {

    public static final String TAG = "NS_14th_question";

    private OnNewSymptomFourteenQuestionListener mListener;
    private NS_14th_VH viewHolder;
    private MedicationsAdapter medicationsAdapter;

    public NS_14th_question() {
        // Required empty public constructor
    }

    public static NS_14th_question newInstance() {
        NS_14th_question fragment = new NS_14th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_14th_question,
                container,
                false );
        viewHolder = new NS_14th_VH ( view );
        viewHolder.setOnFourteenNSVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomFourteenQuestionListener) {
            mListener = (OnNewSymptomFourteenQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomFourteenQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_14th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new
                SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomFourteenQuestionAnswer ( getActivity (),
                this, manager.getAppointmentID (),
                answer);
    }

    @Override
    public void onNextClicked(List<Medication> answers) {
        System.out.println ( "NS_14th_question.onNextClicked" );
        System.out.println ("list of answers");
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new
                SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomFourteenQuestionAnswer ( getActivity (),
                this, manager.getAppointmentID (),
                answers);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_14th_question.onSkipClicked" );
        mListener.onFourteenQuestion ();
    }

    @Override
    public void onThirteenAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_14th_question.onThirteenAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onFourteenQuestion ();
    }

    @Override
    public void onThirteenAnswerFailure() {
        System.out.println ( "NS_14th_question.onThirteenAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_14th_question.onUnauthorized" );

    }

    public interface OnNewSymptomFourteenQuestionListener {
        void onFourteenQuestion();
    }
}
