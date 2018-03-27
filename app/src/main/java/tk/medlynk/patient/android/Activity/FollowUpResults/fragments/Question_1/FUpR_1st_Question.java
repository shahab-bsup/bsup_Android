package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_1;

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
 * {@link OnFURFirstQuestionInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FUpR_1st_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpR_1st_Question extends Fragment implements FUpR_1st_VH.OnFURFirstViewHolderListener {

    public static final String TAG = FUpR_1st_Question.class.getSimpleName();
    private OnFURFirstQuestionInteractionListener mListener;
    private FUpR_1st_VH viewHolder;

    public FUpR_1st_Question() {
        // Required empty public constructor...
    }

    public static FUpR_1st_Question newInstance() {
        FUpR_1st_Question fragment = new FUpR_1st_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow_up_results_1st__question, container, false );
        viewHolder = new FUpR_1st_VH( view );
        viewHolder.setOnFURFirstViewHolderListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFURFirstQuestionInteractionListener) {
            mListener = (OnFURFirstQuestionInteractionListener) context;
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
        System.out.println ( "FUpR_1st_Question.onNextClicked" );
        mListener.onFURFirstQuestion ();
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "FUpR_1st_Question.onSkipClicked" );
        mListener.onFURFirstQuestion ();
    }

    public interface OnFURFirstQuestionInteractionListener {
        void onFURFirstQuestion();
    }
}
