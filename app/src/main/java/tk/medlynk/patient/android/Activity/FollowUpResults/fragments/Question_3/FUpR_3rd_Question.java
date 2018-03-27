package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_3;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.MotherCallback;
import tk.medlynk.patient.android.Model.SymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFURThirdQuestionInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FUpR_3rd_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpR_3rd_Question extends Fragment implements FUpR_3rd_VH.OnFURThirdVHListener, MotherCallback {

    public static final String TAG = FUpR_3rd_Question.class.getSimpleName();
    private OnFURThirdQuestionInteractionListener mListener;
    private FUpR_3rd_VH viewHolder;

    public FUpR_3rd_Question() {
        // Required empty public constructor
    }

    public static FUpR_3rd_Question newInstance() {
        FUpR_3rd_Question fragment = new FUpR_3rd_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow_up_results_3rd__question, container, false );
        viewHolder = new FUpR_3rd_VH( view );
        viewHolder.setOnFURThirdVHListener( this );
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFURThirdQuestionInteractionListener) {
            mListener = (OnFURThirdQuestionInteractionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnFURFirstQuestionInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "FUpR_3rd_Question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        SharedPreferenceManager manager = new SharedPreferenceManager(getActivity());
        MedlynkRequests.followUpResultThirdAnswer(getActivity(),
                manager.getAppointmentID(),
                this,
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "FUpR_3rd_Question.onSkipClicked" );
        mListener.onFURThirdQuestion ();
    }

    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        System.out.println("FUpR_3rd_Question.onAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        if( response.getAnswer().getChoice().equals("b") ){
            mListener.onJumpTo17thQuestion();
        }else
            mListener.onFURThirdQuestion();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("FUpR_3rd_Question.onAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
    }


    public interface OnFURThirdQuestionInteractionListener {
        void onFURThirdQuestion();
        void onJumpTo17thQuestion();
    }
}
