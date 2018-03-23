package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_21;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import java.util.ArrayList;

import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_22.New_Symptom_22th_question;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link New_Symptom_21th_question.OnNewSymptomTwenty1QuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_21th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_21th_question extends Fragment implements
        New_Symptom_21th_question_ViewHolder.On21QuestionViewsClickListener,
        OnTwentyOneAnswerListener {
    public static final String TAG = New_Symptom_22th_question.class.getSimpleName ();

    private OnNewSymptomTwenty1QuestionListener mListener;

    private New_Symptom_21th_question_ViewHolder viewHolder;

    private ArrayList<Answer> selected_choices = new ArrayList<> (  );

    public New_Symptom_21th_question() {
        // Required empty public constructor
    }

    public static New_Symptom_21th_question newInstance() {
        New_Symptom_21th_question fragment = new New_Symptom_21th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_21th_question, container, false );
        viewHolder = new New_Symptom_21th_question_ViewHolder ( view );
        viewHolder.setListener ( this );
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomTwenty1QuestionListener) {
            mListener = (OnNewSymptomTwenty1QuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomTwenty1QuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        System.out.println ( "New_Symptom_21th_question.onDetach" );
        mListener = null;
    }

    @Override
    public void onNextClicked() {
        System.out.println ( "New_Symptom_21th_question.onNextClicked" );
        if( selected_choices.size () != 0 ){
            viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
            SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
            MedlynkRequests.newSymptomTwentyOneQuestionAnswer ( getActivity (),
                    New_Symptom_21th_question.this,
                    manager.getAppointmentID (),
                    selected_choices
            );
        }
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "New_Symptom_21th_question.onSkipClicked" );
        mListener.onTwenty1Question ();
    }

    @Override
    public void onViewSelectionClicked(View view, int i) {
        System.out.println ( "New_Symptom_21th_question.onViewSelectionClicked" );
    }

    @Override
    public void onViewMultipleSelected(Integer integer) {
        System.out.println ( "New_Symptom_21th_question.onViewMultipleSelected" );
        Answer answer = new Answer ();
        switch (integer){
            case 0:{
                answer.setChoice ( "a" );
                break;
            }
            case 1:{
                answer.setChoice ( "b" );

                break;
            }
            case 2:{
                answer.setChoice ( "c" );

                break;
            }
            case 3:{
                answer.setChoice ( "d" );

                break;
            }
            case 4:{
                answer.setChoice ( "e" );

                break;
            }
            case 5:{
                answer.setChoice ( "f" );
                answer.setOther ( "nothing but everything!" );

                break;
            }
        }
        selected_choices.add ( answer );
    }

    @Override
    public void onViewMultipleDeselected(Integer integer) {
        System.out.println ( "New_Symptom_21th_question.onViewMultipleDeselected" );
        int i = integer;
        selected_choices.remove ( i );
    }

    @Override
    public void onTwentyOneAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "New_Symptom_21th_question.onTwentyOneAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTwenty1Question ();
    }

    @Override
    public void onTwentyOneAnswerFailure() {
        System.out.println ( "New_Symptom_21th_question.onTwentyOneAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTwenty1Question ();
    }

    public interface OnNewSymptomTwenty1QuestionListener {
        void onTwenty1Question();
    }
}
