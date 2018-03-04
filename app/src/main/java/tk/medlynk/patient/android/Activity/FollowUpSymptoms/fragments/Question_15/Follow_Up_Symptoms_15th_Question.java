package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_15;

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
 * {@link OnFollowUpSymptomsFifteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link Follow_Up_Symptoms_15th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Follow_Up_Symptoms_15th_Question extends Fragment implements Follow_Up_Symptoms_15th_Question_ViewHolder.OnFollowUpFifteenQuestionViewsClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = Follow_Up_Symptoms_15th_Question.class.getSimpleName ();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFollowUpSymptomsFifteenQuestionListener mListener;
    private Follow_Up_Symptoms_15th_Question_ViewHolder viewHolder;

    public Follow_Up_Symptoms_15th_Question() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Follow_Up_Symptoms_15th_Question.
     */
    // TODO: Rename and change types and number of parameters
    public static Follow_Up_Symptoms_15th_Question newInstance(String param1, String param2) {
        Follow_Up_Symptoms_15th_Question fragment = new Follow_Up_Symptoms_15th_Question ();
        Bundle args = new Bundle ();
        args.putString ( ARG_PARAM1, param1 );
        args.putString ( ARG_PARAM2, param2 );
        fragment.setArguments ( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        if (getArguments () != null) {
            mParam1 = getArguments ().getString ( ARG_PARAM1 );
            mParam2 = getArguments ().getString ( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_15th__question, container, false );
        viewHolder = new Follow_Up_Symptoms_15th_Question_ViewHolder ( view );
        viewHolder.setOnFollowUpFifteenQuestionViewsClickListener ( this );
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFifteenQuestion ();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsFifteenQuestionListener) {
            mListener = (OnFollowUpSymptomsFifteenQuestionListener) context;
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
        System.out.println ( "Follow_Up_Symptoms_15th_Question.onNextClick" );
        mListener.onFifteenQuestion ();
    }

    @Override
    public void onSkipClick() {
        System.out.println ( "Follow_Up_Symptoms_15th_Question.onSkipClick" );
        mListener.onFifteenQuestion ();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFollowUpSymptomsFifteenQuestionListener {
        // TODO: Update argument type and name
        void onFifteenQuestion();
    }
}
