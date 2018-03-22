package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_11;

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
 * {@link OnFUREleventhQuestionInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FollowUpResults_11th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowUpResults_11th_Question extends Fragment implements FollowUpResults_11th_Question_ViewHolder.OnFUREleventhViewHolderListener {

    private OnFUREleventhQuestionInteractionListener mListener;
    private FollowUpResults_11th_Question_ViewHolder viewHolder;

    public FollowUpResults_11th_Question() {
        // Required empty public constructor
    }

    public static FollowUpResults_11th_Question newInstance() {
        FollowUpResults_11th_Question fragment = new FollowUpResults_11th_Question ();
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
        View view = inflater.inflate ( R.layout.fragment_follow_up_results_11th__question, container, false );
        viewHolder = new FollowUpResults_11th_Question_ViewHolder ( view );
        viewHolder.setOnFUREleventhViewHolderListener ( this );
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFUREleventhQuestion ();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFUREleventhQuestionInteractionListener) {
            mListener = (OnFUREleventhQuestionInteractionListener) context;
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
        System.out.println ( "FollowUpResults_11th_Question.onNextClicked" );
        mListener.onFUREleventhQuestion ();
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "FollowUpResults_11th_Question.onSkipClicked" );
        mListener.onFUREleventhQuestion ();
    }

    public interface OnFUREleventhQuestionInteractionListener {
        void onFUREleventhQuestion();
    }
}
