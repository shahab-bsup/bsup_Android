package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_12;

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
 * {@link FollowUpResults_12th_Question.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FollowUpResults_12th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowUpResults_12th_Question extends Fragment {

    private OnFragmentInteractionListener mListener;
    private FollowUpResults_12th_Question_ViewHolder viewHolder;

    public FollowUpResults_12th_Question() {
        // Required empty public constructor
    }

    public static FollowUpResults_12th_Question newInstance(String param1, String param2) {
        FollowUpResults_12th_Question fragment = new FollowUpResults_12th_Question ();
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
        // Inflate the layout for this fragment...
        View view = inflater.inflate ( R.layout.fragment_follow_up_results_12th__question, container, false );
        viewHolder = new FollowUpResults_12th_Question_ViewHolder ( view );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
