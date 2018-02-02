package com.neweraandroid.demo.Activity.NewSymptom.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.neweraandroid.demo.R;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link OnFirstQuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_1th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_1th_Question extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public final static String TAG = New_Symptom_1th_Question.class.getSimpleName ();
    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFirstQuestionListener mListener;

    private Button button;
    private AppCompatEditText answer;

    public New_Symptom_1th_Question() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment New_Symptom_1th_Question.
     */
    // TODO: Rename and change types and number of parameters
    public static New_Symptom_1th_Question newInstance(String param1, String param2) {
        New_Symptom_1th_Question fragment = new New_Symptom_1th_Question ();
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
        View view = inflater.inflate ( R.layout.fragment_first_question, container, false );
        button = view.findViewById ( R.id.btn_new_symptom_first_question );
        button.setOnClickListener ( this );
        answer = view.findViewById ( R.id.first_question_answer );
        return view;
    }

    @Override
    public void onResume() {
        super.onResume ();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFirstQuestionListener) {
            mListener = (OnFirstQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnSecondQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        mListener.onFirstQuestion ( answer.getText ().toString () );
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
    public interface OnFirstQuestionListener {
        // TODO: Update argument type and name
        void onFirstQuestion(String s);
    }

}
