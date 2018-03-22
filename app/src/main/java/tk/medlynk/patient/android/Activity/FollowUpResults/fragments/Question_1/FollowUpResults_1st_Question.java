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
 * Use the {@link FollowUpResults_1st_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowUpResults_1st_Question extends Fragment {

    private OnFURFirstQuestionInteractionListener mListener;
    private FollowUpResults_1st_Question_ViewHolder viewHolder;

    public FollowUpResults_1st_Question() {
        // Required empty public constructor
    }

    public static FollowUpResults_1st_Question newInstance() {
        FollowUpResults_1st_Question fragment = new FollowUpResults_1st_Question ();
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
        viewHolder = new FollowUpResults_1st_Question_ViewHolder ( view );
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

    public interface OnFURFirstQuestionInteractionListener {
        void onFURFirstQuestion();
    }
}
