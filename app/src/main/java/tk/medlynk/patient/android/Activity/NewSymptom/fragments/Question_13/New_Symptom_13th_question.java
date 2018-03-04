package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_5.New_Symptom_5th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_5.OnFifthAnswerListener;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnNewSymptomThirteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_13th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_13th_question extends Fragment implements
        New_Symptom_13th_question_ViewHolder.On13QuestionViewClickListener, OnThirteenAnswerListener {

    public final static String TAG = New_Symptom_13th_question.class.getSimpleName ();

    private OnNewSymptomThirteenQuestionListener mListener;
    private New_Symptom_13th_question_ViewHolder viewHolder;
    private Answer answer;

    public New_Symptom_13th_question() {
        // Required empty public constructor...
    }

    public static New_Symptom_13th_question newInstance() {
        New_Symptom_13th_question fragment = new New_Symptom_13th_question ();
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

        View view = inflater.inflate ( R.layout.fragment_new__symptom_13th_question, container, false );
        viewHolder = new New_Symptom_13th_question_ViewHolder ( view );


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomThirteenQuestionListener) {
            mListener = (OnNewSymptomThirteenQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomThirteenQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClicked() {
        System.out.println ( "New_Symptom_13th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomThirteenQuestionAnswer ( getActivity (),
                New_Symptom_13th_question.this,
                manager.getAppointmentID (),
                answer
        );
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "New_Symptom_13th_question.onSkipClicked" );
        mListener.onThirteenQuestion ();
    }

    @Override
    public void onTreatmentClicked(int position, int treatmentID) {
        System.out.println ( "New_Symptom_13th_question.onTreatmentClicked" );
        answer = new Answer ();
        switch (position){
            case 0:{
                answer.setChoice ( "a" );
                answer.setHelpfully ( String.valueOf ( treatmentID ) );
                break;
            }
            case 1:{
                answer.setChoice ( "b" );
                answer.setHelpfully ( String.valueOf ( treatmentID ) );

                break;
            }
            case 2:{
                answer.setChoice ( "c" );
                answer.setHelpfully ( String.valueOf ( treatmentID ) );

                break;
            }
            case 3:{
                answer.setChoice ( "d" );
                answer.setHelpfully ( String.valueOf ( treatmentID ) );

                break;
            }
            case 4:{
                answer.setChoice ( "e" );
                answer.setHelpfully ( String.valueOf ( treatmentID ) );

                break;
            }
            case 5:{
                answer.setChoice ( "f" );
                answer.setHelpfully ( String.valueOf ( treatmentID ) );

                break;
            }
            case 6:{
                answer.setChoice ( "g" );
                answer.setHelpfully ( String.valueOf ( treatmentID ) );
                answer.setOther ( "nothing but everything!" );
                break;
            }

        }
    }

    @Override
    public void onThirteenAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "New_Symptom_13th_question.onThirteenAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        mListener.onThirteenQuestion ();
    }

    @Override
    public void onThirteenAnswerFailure() {
        System.out.println ( "New_Symptom_13th_question.onThirteenAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }

    public interface OnNewSymptomThirteenQuestionListener {
        void onThirteenQuestion();
    }
}