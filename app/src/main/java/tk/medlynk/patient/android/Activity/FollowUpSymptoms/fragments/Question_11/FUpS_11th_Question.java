package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_11;

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
 * {@link OnFollowUpSymptomsEleventhQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_11th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_11th_Question extends Fragment implements FUpS_11th_VH.OnFUpSEleventhVHListener {

    public static final String TAG = FUpS_11th_Question.class.getSimpleName ();


    private OnFollowUpSymptomsEleventhQuestionListener mListener;
    private FUpS_11th_VH viewHolder;

    public FUpS_11th_Question() {
        // Required empty public constructor
    }

    public static FUpS_11th_Question newInstance() {
        FUpS_11th_Question fragment = new FUpS_11th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_11th__question, container, false );
        viewHolder = new FUpS_11th_VH( view );
        viewHolder.setOnFUpSEleventhVHListener( this );
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onEleventhQuestion ();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsEleventhQuestionListener) {
            mListener = (OnFollowUpSymptomsEleventhQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnFollowUpSymptomsFirstQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClick() {
        System.out.println ( "FUpS_11th_Question.onNextClick" );
        mListener.onEleventhQuestion ();
    }

    @Override
    public void onSkipClick() {
        System.out.println ( "FUpS_11th_Question.onSkipClick" );
        mListener.onEleventhQuestion ();
    }

    public interface OnFollowUpSymptomsEleventhQuestionListener {
        void onEleventhQuestion();
    }
}
