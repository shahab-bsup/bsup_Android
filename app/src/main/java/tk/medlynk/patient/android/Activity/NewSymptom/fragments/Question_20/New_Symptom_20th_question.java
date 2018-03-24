package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_20;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link New_Symptom_20th_question.OnNewSymptomTwentyQuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_20th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_20th_question extends Fragment implements
        New_Symptom_20th_question_ViewHolder.On20QuestionViewsClickListener, OnTwentyAnswerListener {

    public static final String TAG = New_Symptom_20th_question.class.getSimpleName ();

    private OnNewSymptomTwentyQuestionListener mListener;
    private New_Symptom_20th_question_ViewHolder viewHolder;
    private String[] lastsIDStrings;
    private Answer answer;
    private int lastsValue;

    public New_Symptom_20th_question() {
        // Required empty public constructor
    }

    public static New_Symptom_20th_question newInstance() {
        New_Symptom_20th_question fragment = new New_Symptom_20th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_20th_question, container, false );
        viewHolder = new New_Symptom_20th_question_ViewHolder ( view );
        viewHolder.setListener ( this );
        lastsIDStrings = getActivity ().getResources ().getStringArray ( R.array.question_20_choices );
        ArrayAdapter<String> lastsIDAdapter = new ArrayAdapter<String> ( getActivity (),
                android.R.layout.simple_spinner_dropdown_item,
                lastsIDStrings);
        viewHolder.setLastsIDAdapter ( lastsIDAdapter );

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomTwentyQuestionListener) {
            mListener = (OnNewSymptomTwentyQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomTwentyQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        System.out.println ( "New_Symptom_20th_question.onDetach" );
        mListener = null;
    }

    @Override
    public void onNextClicked() {
        System.out.println ( "New_Symptom_20th_question.onNextClicked" );
        viewHolder.setProgressVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomTwentyQuestionAnswer ( getActivity (),
                New_Symptom_20th_question.this,
                manager.getAppointmentID (),
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "New_Symptom_20th_question.onSkipClicked" );
        mListener.onTwentyQuestion ();
    }

    @Override
    public void onSpinnerItemSelected(int position) {
        System.out.println ( "New_Symptom_20th_question.onSpinnerItemSelected" );
        lastsValue = viewHolder.getLastsValue ();
        answer = new Answer ();
        switch (position){
            case 0:{
                answer.setChoice ( "c" );
                answer.setDuration ( lastsValue );

                break;
            }
            case 1:{
                answer.setChoice ( "d" );
                answer.setDuration ( lastsValue );

                break;
            }
            case 2:{
                answer.setChoice ( "e" );
                answer.setDuration ( lastsValue );

                break;
            }
            case 3:{
                answer.setChoice ( "f" );
                answer.setDuration ( lastsValue );

                break;
            }
        }
    }

    @Override
    public void onViewSelectionsClicked(View view, int i) {
        if( view == null ){
            //TODO clear the ViewSelection states!!!

        }else{
            System.out.println ( "New_Symptom_20th_question.onViewSelectionsClicked" );
            viewHolder.setLastingEmpty ();
            if( view.getId () == R.id.viewSelectionChoicesBeforeLasts ){
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
                }
            }else if( view.getId () == R.id.viewSelectionChoicesOther ){
                answer = new Answer ();
                answer.setChoice ( "g" );
                answer.setOther ( "nothing but everything!" );
            }
        }
    }

    @Override
    public void onTwentyAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "New_Symptom_20th_question.onTwentyAnswerSuccess" );
        viewHolder.setProgressVisibilityStatus ( View.GONE );
        mListener.onTwentyQuestion ();
    }

    @Override
    public void onTwentyAnswerFailure() {
        System.out.println ( "New_Symptom_20th_question.onTwentyAnswerFailure" );
        viewHolder.setProgressVisibilityStatus ( View.GONE );
    }

    public interface OnNewSymptomTwentyQuestionListener {
        void onTwentyQuestion();
    }
}
