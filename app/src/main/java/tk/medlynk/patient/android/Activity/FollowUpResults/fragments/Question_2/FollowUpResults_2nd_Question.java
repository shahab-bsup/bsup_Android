package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFURSecondQuestionInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FollowUpResults_2nd_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowUpResults_2nd_Question extends Fragment implements FollowUpResults_2nd_Question_ViewHolder.OnFURSecondViewHolderListener {

    private OnFURSecondQuestionInteractionListener mListener;
    private FollowUpResults_2nd_Question_ViewHolder viewHolder;

    public FollowUpResults_2nd_Question() {
        // Required empty public constructor
    }

    public static FollowUpResults_2nd_Question newInstance() {
        FollowUpResults_2nd_Question fragment = new FollowUpResults_2nd_Question ();
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
        View view = inflater.inflate ( R.layout.fragment_follow_up_results_2nd__question, container, false );
        viewHolder = new FollowUpResults_2nd_Question_ViewHolder ( view );
        viewHolder.setOnFURSecondViewHolderListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFURSecondQuestionInteractionListener) {
            mListener = (OnFURSecondQuestionInteractionListener) context;
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
    public void onNextClicked() {
        System.out.println ( "FollowUpResults_2nd_Question.onNextClicked" );
        mListener.onFURSecondQuestion ();
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "FollowUpResults_2nd_Question.onSkipClicked" );
        mListener.onFURSecondQuestion ();
    }

    public interface OnFURSecondQuestionInteractionListener {
        void onFURSecondQuestion();
    }
}
