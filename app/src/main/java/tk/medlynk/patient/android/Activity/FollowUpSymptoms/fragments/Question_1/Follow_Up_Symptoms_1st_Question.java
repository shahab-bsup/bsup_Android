package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpResultResponse;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsFirstQuestionListener} interface
 * to handle interaction events.
 * Use the {@link Follow_Up_Symptoms_1st_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Follow_Up_Symptoms_1st_Question extends Fragment implements
    Follow_Up_Symptoms_1st_Question_ViewHolder.OnFollowUpFirstQuestionViewsClickListener,
        OnFirstFollowUpAnswerListener
    {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = Follow_Up_Symptoms_1st_Question.class.getSimpleName ();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFollowUpSymptomsFirstQuestionListener mListener;
    private Follow_Up_Symptoms_1st_Question_ViewHolder viewHolder;

    public Follow_Up_Symptoms_1st_Question() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Follow_Up_Symptoms_1st_Question.
     */
    // TODO: Rename and change types and number of parameters
    public static Follow_Up_Symptoms_1st_Question newInstance(String param1, String param2) {
        Follow_Up_Symptoms_1st_Question fragment = new Follow_Up_Symptoms_1st_Question ();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_1st__question, container, false );
        viewHolder = new Follow_Up_Symptoms_1st_Question_ViewHolder ( view );
        viewHolder.setOnFollowUpFirstQuestionViewsClickListener ( this );
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsFirstQuestionListener) {
            mListener = (OnFollowUpSymptomsFirstQuestionListener) context;
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
            System.out.println ( "Follow_Up_Symptoms_1st_Question.onNextClick" );
            viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
            SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
            Answer answer = new Answer ();
            answer.setReply ( viewHolder.getAnswerInput() );
            MedlynkRequests.followUpResultFirstAnswer(getActivity (), Follow_Up_Symptoms_1st_Question.this,
                    manager.getAppointmentID (),
                    answer);
        }

        @Override
        public void onFirstAnswerSuccess(FollowUpResultResponse response) {
            System.out.println ( "Follow_Up_Symptoms_1st_Question.onFirstAnswerSuccess" );
            viewHolder.setProgressBarVisibilityStatus ( View.GONE );
            mListener.onFirstQuestion ();
        }

        @Override
        public void onFirstAnswerFailure() {
            System.out.println ( "Follow_Up_Symptoms_1st_Question.onFirstAnswerFailure" );
            viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        }

    public interface OnFollowUpSymptomsFirstQuestionListener {
        void onFirstQuestion();
    }
}
