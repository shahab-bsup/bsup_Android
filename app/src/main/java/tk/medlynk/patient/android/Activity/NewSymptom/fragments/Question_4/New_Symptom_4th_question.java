package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_4;

import android.content.Context;
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

import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_3.OnThirdAnswerListener;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link New_Symptom_4th_question.OnNewSymptomFourthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_4th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_4th_question extends Fragment implements
        View.OnClickListener,
        ViewSelection.OnSingleItemSelectedListener,
        ViewSelection.OnMultiItemSelectedListener,
        OnFourthAnswerListener {

    public static final String TAG = New_Symptom_4th_question.class.getSimpleName ();

    private OnNewSymptomFourthQuestionListener mListener;

    private View question_view;
    private Button next, skip;
    private TextView question;
    private ViewSelection choices, choiceNumbers;
    private String[] answerChoices;
    private int selected_choice= -1;
    private New_Symptom_4th_question_ViewHolder viewHolder;

    public New_Symptom_4th_question() {
        // Required empty public constructor...
    }

    public static New_Symptom_4th_question newInstance(String param1, String param2) {
        New_Symptom_4th_question fragment = new New_Symptom_4th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_4th_question, container, false );
        viewHolder = new New_Symptom_4th_question_ViewHolder ( view );
        question_view = view.findViewById ( R.id.new_symptom_fourth_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_fourth_question );
        next = view.findViewById ( R.id.btnNextQuestion );
        next.setOnClickListener ( this );
        skip = view.findViewById ( R.id.btnSkipQuestion );
        skip.setOnClickListener ( this );
        choiceNumbers = view.findViewById ( R.id.viewSelectionChoiceNumbers );
        choices = view.findViewById ( R.id.viewSelectionChoices );
        choices.setOnSingleItemSelectedListener ( this );
        for (int i = 0; i < choiceNumbers.getNumberOfViews (); i++) {
            choiceNumbers.setTextToButtons ( String.valueOf ( i + 1 ), i );
        }
        answerChoices = getActivity ().getResources ().getStringArray ( R.array.question_4_choices );
        for (int i = 0; i < choices.getNumberOfViews (); i++) {
            choices.setTextToButtons ( answerChoices[i], i );
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomFourthQuestionListener) {
            mListener = (OnNewSymptomFourthQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomFourthQuestionListener" );
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
                if( selected_choice == -1 ){
                    Toast.makeText ( getActivity (), "You can skip this question!", Toast.LENGTH_SHORT ).show ();
                }else {
                    viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
                    Answer answer = new Answer ();
                    SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
                    answer.setRate ( selected_choice );
                    MedlynkRequests.newSymptomFourthQuestionAnswer ( getActivity (), New_Symptom_4th_question.this, manager.getAppointmentID (), answer );
                }

                break;
            }
            case R.id.btnSkipQuestion:{
                mListener.onFourthQuestion ();

                break;
            }
        }
    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {

    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {

    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "i = [" + i + "]: " + answerChoices[i] );
        selected_choice  = i ;
    }

    @Override
    public void onFourthAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "New_Symptom_4th_question.onFourthAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onFourthQuestion ();
    }

    @Override
    public void onFourthAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        System.out.println ( "New_Symptom_4th_question.onFourthAnswerFailure" );

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity...
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information...
     */

    public interface OnNewSymptomFourthQuestionListener {
        // TODO: Update argument type and name
        void onFourthQuestion();
    }

}
