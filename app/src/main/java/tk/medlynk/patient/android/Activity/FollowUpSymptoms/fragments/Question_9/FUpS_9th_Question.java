package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import java.util.List;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsNinthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_9th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_9th_Question extends Fragment implements
        FUpS_9th_VH.OnFUpSNinthVHListener,
        OnNinthFollowUpAnswerListener
{

    public static final String TAG = FUpS_9th_Question.class.getSimpleName ();

    private OnFollowUpSymptomsNinthQuestionListener mListener;
    private FUpS_9th_VH viewHolder;

    public FUpS_9th_Question() {
        // Required empty public constructor
    }

    public static FUpS_9th_Question newInstance(String param1, String param2) {
        FUpS_9th_Question fragment = new FUpS_9th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_9th__question, container, false );
        viewHolder = new FUpS_9th_VH( view );
        viewHolder.setOnFUpSNinthVHListener( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsNinthQuestionListener) {
            mListener = (OnFollowUpSymptomsNinthQuestionListener) context;
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
    public void onNextClicked(Answer answer) {
        System.out.println ( "FUpS_9th_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomNinthAnswer( getActivity (),
                manager.getAppointmentID (),
                FUpS_9th_Question.this,
                answer);
    }


    @Override
    public void onNextClicked(List<Answer> answers) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.followUpSymptomNinthAnswer( getActivity (),
                manager.getAppointmentID (),
                FUpS_9th_Question.this,
                answers);
    }


    @Override
    public void onSkipClick() {
        System.out.println ( "FUpS_9th_Question.onSkipClick" );
        mListener.onNinthQuestion ();
    }

    @Override
    public void onNinthAnswerSuccess(FollowUpSymptomResponse response) {
        System.out.println ( "FUpS_9th_Question.onNinthAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onNinthQuestion ();
    }

    @Override
    public void onNinthAnswerFailure() {
        System.out.println ( "FUpS_9th_Question.onNinthAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onNinthQuestion ();
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
    public interface OnFollowUpSymptomsNinthQuestionListener {
        // TODO: Update argument type and name
        void onNinthQuestion();
    }
}
