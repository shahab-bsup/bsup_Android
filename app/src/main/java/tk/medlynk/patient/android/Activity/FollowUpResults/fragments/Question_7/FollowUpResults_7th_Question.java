package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_7;

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
 * {@link OnFURSeventhQuestionInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FollowUpResults_7th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowUpResults_7th_Question extends Fragment implements FollowUpResults_7th_Question_ViewHolder.OnFURSeventhViewHolderListener {

    private OnFURSeventhQuestionInteractionListener mListener;
    private FollowUpResults_7th_Question_ViewHolder viewHolder;

    public FollowUpResults_7th_Question() {
        // Required empty public constructor
    }

    public static FollowUpResults_7th_Question newInstance() {
        FollowUpResults_7th_Question fragment = new FollowUpResults_7th_Question ();
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
        View view = inflater.inflate ( R.layout.fragment_follow_up_results_7th__question, container, false );
        viewHolder = new FollowUpResults_7th_Question_ViewHolder ( view );
        viewHolder.setOnFURSeventhViewHolderListener ( this );
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFURSeventhQuestion ();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFURSeventhQuestionInteractionListener) {
            mListener = (OnFURSeventhQuestionInteractionListener) context;
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
        System.out.println ( "FollowUpResults_7th_Question.onNextClicked" );
        mListener.onFURSeventhQuestion ();
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "FollowUpResults_7th_Question.onSkipClicked" );
        mListener.onFURSeventhQuestion ();
    }

    public interface OnFURSeventhQuestionInteractionListener {

        void onFURSeventhQuestion();
    }
}
