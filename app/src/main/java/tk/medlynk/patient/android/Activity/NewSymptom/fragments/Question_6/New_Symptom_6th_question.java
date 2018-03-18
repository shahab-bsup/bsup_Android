package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_6;

import android.content.Context;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link New_Symptom_6th_question.OnNewSymptomSixthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_6th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_6th_question extends Fragment implements View.OnClickListener, ViewSelection.OnSingleItemSelectedListener, OnSixthAnswerListener {

    public static final String TAG = New_Symptom_6th_question.class.getSimpleName ();

    private OnNewSymptomSixthQuestionListener mListener;

    private View question_view;
    private Button next, skip;
    private TextView question;
    private ViewSelection answerChoices, choice_numbers;
    private String[] string_choices;
    private int selected_choice = -1;
    private New_Symptom_6th_question_ViewHolder viewHolder;

    public New_Symptom_6th_question() {
        // Required empty public constructor
    }

    public static New_Symptom_6th_question newInstance(String param1, String param2) {
        New_Symptom_6th_question fragment = new New_Symptom_6th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_6th_question, container, false );
        viewHolder = new New_Symptom_6th_question_ViewHolder ( view );
        question_view = view.findViewById ( R.id.new_symptom_sixth_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_sixteen_question );
        next = view.findViewById ( R.id.btnNextQuestion );
        next.setOnClickListener ( this );
        skip = view.findViewById ( R.id.btnSkipQuestion );
        skip.setOnClickListener ( this );
        string_choices= getActivity ().getResources ().getStringArray ( R.array.question_6_7_choices );
        choice_numbers = view.findViewById ( R.id.viewSelectionChoiceNumbers );
        for (int i = 0; i < choice_numbers.getNumberOfViews (); i++) {
            choice_numbers.setTextToButtons ( String.valueOf ( i + 1 ), i );
        }
        answerChoices = view.findViewById ( R.id.viewSelectionChoices );
        for (int i = 0; i < answerChoices.getNumberOfViews (); i++) {
            answerChoices.setTextToButtons ( string_choices[i], i );
        }
        answerChoices.setOnSingleItemSelectedListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomSixthQuestionListener) {
            mListener = (OnNewSymptomSixthQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomEleventhQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case R.id.btnNextQuestion:{
                if ( selected_choice == -1 ){
                    Toast.makeText ( getActivity (), "You can skip this question!", Toast.LENGTH_SHORT ).show ();
                } else {
                    viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
                    Answer answer = new Answer ();
                    SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
                    answer.setRate( selected_choice );
                    MedlynkRequests.newSymptomSixthQuestionAnswer ( getActivity (),
                            New_Symptom_6th_question.this,
                            manager.getAppointmentID (),
                            answer);
                }
                break;
            }
            case R.id.btnSkipQuestion:{
                mListener.onSixthQuestion ();
                break;
            }
        }
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "i = [" + i + "]: " + string_choices[i] );
        selected_choice = i;
    }

    @Override
    public void onSixthAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "New_Symptom_6th_question.onSixthAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onSixthQuestion ();
    }

    @Override
    public void onSixthAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        System.out.println ( "New_Symptom_6th_question.onSixthAnswerFailure" );
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
    public interface OnNewSymptomSixthQuestionListener {
        // TODO: Update argument type and name
        void onSixthQuestion();
    }
}
