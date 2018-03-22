package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_8;

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
 * {@link OnFUREighthQuestionInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FollowUpResults_8th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowUpResults_8th_Question extends Fragment implements FollowUpResults_8th_Question_ViewHolder.OnFUREighthViewHolderListener {

    private OnFUREighthQuestionInteractionListener mListener;
    private FollowUpResults_8th_Question_ViewHolder viewHolder;

    public FollowUpResults_8th_Question() {
        // Required empty public constructor
    }

    public static FollowUpResults_8th_Question newInstance() {
        FollowUpResults_8th_Question fragment = new FollowUpResults_8th_Question ();
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
        View view =  inflater.inflate ( R.layout.fragment_follow_up_results_8th__question, container, false );
        viewHolder = new FollowUpResults_8th_Question_ViewHolder ( view );
        viewHolder.setOnFUREighthViewHolderListener ( this );
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFUREighthQuestion ();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFUREighthQuestionInteractionListener) {
            mListener = (OnFUREighthQuestionInteractionListener) context;
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
        System.out.println ( "FollowUpResults_8th_Question.onNextClicked" );
        mListener.onFUREighthQuestion ();
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "FollowUpResults_8th_Question.onSkipClicked" );
        mListener.onFUREighthQuestion ();
    }

    public interface OnFUREighthQuestionInteractionListener {
        void onFUREighthQuestion();
    }
}
