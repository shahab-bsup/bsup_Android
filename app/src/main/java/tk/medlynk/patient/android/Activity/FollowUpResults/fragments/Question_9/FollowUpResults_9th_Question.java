package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_9;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFURNinthQuestionInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FollowUpResults_9th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowUpResults_9th_Question extends Fragment implements FollowUpResults_9th_Question_ViewHolder.OnFURNinthViewHolderListener {

    private OnFURNinthQuestionInteractionListener mListener;
    private FollowUpResults_9th_Question_ViewHolder viewHolder;

    public FollowUpResults_9th_Question() {
        // Required empty public constructor
    }

    public static FollowUpResults_9th_Question newInstance() {
        FollowUpResults_9th_Question fragment = new FollowUpResults_9th_Question ();
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
        View view = inflater.inflate ( R.layout.fragment_follow_up_results_9th__question, container, false );
        viewHolder = new FollowUpResults_9th_Question_ViewHolder ( view );
        viewHolder.setOnFURNinthViewHolderListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFURNinthQuestionInteractionListener) {
            mListener = (OnFURNinthQuestionInteractionListener) context;
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
        System.out.println ( "FollowUpResults_9th_Question.onNextClicked" );
        mListener.onFURNinthQuestion ();
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "FollowUpResults_9th_Question.onSkipClicked" );
        mListener.onFURNinthQuestion ();
    }

    public interface OnFURNinthQuestionInteractionListener {
        void onFURNinthQuestion();
    }
}
