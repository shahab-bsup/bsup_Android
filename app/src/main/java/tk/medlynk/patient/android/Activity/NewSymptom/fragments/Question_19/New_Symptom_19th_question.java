package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_19;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_18.New_Symptom_18th_question;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link New_Symptom_19th_question.OnNewSymptomNineteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_19th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_19th_question extends Fragment implements
        New_Symptom_19th_question_ViewHolder.On19QuestionViewsClickListener,
        OnNineteenAnswerListener
{
    public static final String TAG = New_Symptom_19th_question.class.getSimpleName ();


    private OnNewSymptomNineteenQuestionListener mListener;

    private String[] years_strings ={"1", "2", "3", "4", "5"};
    private New_Symptom_19th_question_ViewHolder viewHolder;
    private int selected_choice = -1;
    private Answer answer;

    public New_Symptom_19th_question() {
        // Required empty public constructor

    }


    public static New_Symptom_19th_question newInstance(String param1, String param2) {
        New_Symptom_19th_question fragment = new New_Symptom_19th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_19th_question, container, false );
        viewHolder = new New_Symptom_19th_question_ViewHolder ( view );
        viewHolder.setListener ( this );
        ArrayAdapter<String> years_adapter = new ArrayAdapter<String> ( getActivity (),
                android.R.layout.simple_spinner_dropdown_item, years_strings);
        viewHolder.setAdapter ( years_adapter );

        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomNineteenQuestionListener) {
            mListener = (OnNewSymptomNineteenQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomNineteenQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        System.out.println ( "New_Symptom_19th_question.onDetach" );
        mListener = null;
    }

    @Override
    public void onNextClicked() {
        System.out.println ( "New_Symptom_19th_question.onNextClicked" );
        if( selected_choice != -1 ){
            viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
            SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
            MedlynkRequests.newSymptomNineteenQuestionAnswer ( getActivity (),
                    New_Symptom_19th_question.this,
                    manager.getAppointmentID (),
                    answer);
        }
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "New_Symptom_19th_question.onSkipClicked" );
        mListener.onNineteenQuestion ();
    }

    @Override
    public void onViewSelectionsClicked(View view, int i) {
        selected_choice = i;
        System.out.println ( "New_Symptom_19th_question.onViewSelectionsClicked" );
        if( view.getId () == R.id.viewSelectionChoicesBeforeYears ){
            System.out.println ("R.id.viewSelectionChoicesBeforeYears");
            answer = new Answer ();
            switch (i){
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
            }
        }else if ( view.getId () == R.id.viewSelectionChoicesAfterYears ){
            System.out.println ("R.id.viewSelectionChoicesAfterYears");
            answer = new Answer ();
            switch (i){
                case 0:{
                    answer.setChoice ( "f" );
                    break;
                }
                case 1:{
                    answer.setChoice ( "g" );
                    answer.setOther ( "nothing but everything!" );
                    break;
                }
            }
        }
    }

    @Override
    public void onSpinnerItemSelected(int position) {
        System.out.println ( "New_Symptom_19th_question.onSpinnerItemSelected" );
        answer = new Answer ();
        answer.setChoice ( "e" );
        answer.setYears ( Integer.parseInt ( years_strings[position] ) );
    }

    @Override
    public void onNineteenAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "New_Symptom_19th_question.onNineteenAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onNineteenQuestion ();
    }

    @Override
    public void onNineteenAnswerFailure() {
        System.out.println ( "New_Symptom_19th_question.onNineteenAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus (View.GONE);
    }

    public interface OnNewSymptomNineteenQuestionListener {
        void onNineteenQuestion();
    }
}
