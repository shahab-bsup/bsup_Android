package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_22;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link New_Symptom_22th_question.OnNewSymptomTwenty2QuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_22th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_22th_question extends Fragment implements
        New_Symptom_22th_question_ViewHolder.On22QuestionViewsClickListener, OnTwentyTwoAnswerListener {

    public static final String TAG = New_Symptom_22th_question.class.getSimpleName ();
    private List<Answer> selected_choices = new ArrayList<> (  );

    private OnNewSymptomTwenty2QuestionListener mListener;
    private New_Symptom_22th_question_ViewHolder viewHolder;
    private Answer answer;

    public New_Symptom_22th_question() {
        // Required empty public constructor
    }


    public static New_Symptom_22th_question newInstance() {
        New_Symptom_22th_question fragment = new New_Symptom_22th_question ();
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
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.fragment_new__symptom_22th_question, container, false );
        viewHolder = new New_Symptom_22th_question_ViewHolder ( view );
        viewHolder.setListener ( this );

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomTwenty2QuestionListener) {
            mListener = (OnNewSymptomTwenty2QuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomTwenty2QuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClicked() {
        System.out.println ( "New_Symptom_22th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomTwentyTwoQuestionAnswer ( getActivity (),
                New_Symptom_22th_question.this,
                manager.getAppointmentID (),
                answer
        );
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "New_Symptom_22th_question.onSkipClicked" );
        mListener.onTwenty2Question ();
    }

    @Override
    public void onTreatmentClicked(int position, int treatmentID) {
        System.out.println ( "New_Symptom_22th_question.onTreatmentClicked" );
        answer = new Answer ();
        switch (position) {
            case 0: {
                answer.setChoice ( "a" );
                answer.setHelpfully ( String.valueOf ( treatmentID ) );
                break;
            }
            case 1: {
                answer.setChoice ( "b" );
                answer.setHelpfully ( String.valueOf ( treatmentID ) );

                break;
            }
            case 2: {
                answer.setChoice ( "c" );
                answer.setHelpfully ( String.valueOf ( treatmentID ) );

                break;
            }
            case 3: {
                answer.setChoice ( "d" );
                answer.setHelpfully ( String.valueOf ( treatmentID ) );

                break;
            }
            case 4: {
                answer.setChoice ( "e" );
                answer.setHelpfully ( String.valueOf ( treatmentID ) );

                break;
            }
            case 5: {
                answer.setChoice ( "f" );
                answer.setHelpfully ( String.valueOf ( treatmentID ) );

                break;
            }
            case 6: {
                answer.setChoice ( "g" );
                answer.setHelpfully ( String.valueOf ( treatmentID ) );
                answer.setOther ( "nothing but everything!" );
                break;
            }
        }
    }

    @Override
    public void onTwentyTwoAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "New_Symptom_22th_question.onTwentyTwoAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTwenty2Question ();
    }

    @Override
    public void onTwentyTwoAnswerFailure() {
        System.out.println ( "New_Symptom_22th_question.onTwentyTwoAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }


    public interface OnNewSymptomTwenty2QuestionListener {
        void onTwenty2Question();

    }
}
