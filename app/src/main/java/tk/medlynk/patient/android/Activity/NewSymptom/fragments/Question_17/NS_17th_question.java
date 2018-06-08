package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_17;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_17th_question.OnNewSymptomSeventeenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_17th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_17th_question extends Fragment implements
        OnSeventeenAnswerListener,
        NS_17th_VH.OnSeventeenNSVHListener {

    public static final String TAG = "NS_17th_question";

    private OnNewSymptomSeventeenQuestionListener mListener;

    private NS_17th_VH viewHolder;
    private MedlynkViewModel medlynkViewModel;

    public NS_17th_question() {
        // Required empty public constructor
    }

    public static NS_17th_question newInstance() {
        NS_17th_question fragment = new NS_17th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_17th_question, container, false );
        medlynkViewModel = ViewModelProviders.of ( getActivity () )
                .get ( MedlynkViewModel.class );
        viewHolder = new NS_17th_VH ( view );
        viewHolder.setOnSeventeenNSVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomSeventeenQuestionListener) {
            mListener = (OnNewSymptomSeventeenQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomSeventeenQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onSeventeenAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_17th_question.onSeventeenAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onSeventeenQuestion ();
    }

    @Override
    public void onSeventeenAnswerFailure() {
        System.out.println ( "NS_17th_question.onSeventeenAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onSeventeenQuestion ();
    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_17th_question.onUnauthorized" );

    }

    @Override
    public void onNextClicked(List<Answer> answer) {
        System.out.println ( "NS_17th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomSeventeenQuestionAnswer ( getActivity (),
                NS_17th_question.this,
                manager.getAppointmentID (),
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_17th_question.onSkipClicked" );
        mListener.onSeventeenQuestion ();
    }

    public interface OnNewSymptomSeventeenQuestionListener {
        void onSeventeenQuestion();
    }
}
