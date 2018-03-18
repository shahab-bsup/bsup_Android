package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_8;

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

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link New_Symptom_8th_question.OnNewSymptomEighthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_8th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_8th_question extends Fragment implements
        View.OnClickListener, ViewSelection.OnSingleItemSelectedListener, ViewSelection.OnMultiItemSelectedListener, OnEighthAnswerListener {

    public static final String TAG = New_Symptom_8th_question.class.getSimpleName ();

    private OnNewSymptomEighthQuestionListener mListener;

    private View question_view;
    private Button next, skip;
    private TextView question;
    private ViewSelection choices;
    private String[] string_choices;
    private New_Symptom_8th_question_ViewHolder viewHolder;
    private List<Answer> selected_choices = new ArrayList<> (  );

    public New_Symptom_8th_question() {
        // Required empty public constructor
    }

    public static New_Symptom_8th_question newInstance(String param1, String param2) {
        New_Symptom_8th_question fragment = new New_Symptom_8th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_8th_question, container, false );
        viewHolder = new New_Symptom_8th_question_ViewHolder ( view );
        question_view = view.findViewById ( R.id.new_symptom_eighth_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_eighth_question );
        next = view.findViewById ( R.id.btnNextQuestion );
        next.setOnClickListener ( this );
        skip = view.findViewById ( R.id.btnSkipQuestion );
        skip.setOnClickListener ( this );
        choices = view.findViewById ( R.id.viewSelectionChoices );
        choices.setOnMultiItemSelectedListener ( this );
        string_choices = getActivity ().getResources ().getStringArray ( R.array.question_8_choices );
        for (int i = 0; i < choices.getNumberOfViews (); i++) {
            choices.setTextToButtons ( string_choices[i], i );
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomEighthQuestionListener) {
            mListener = (OnNewSymptomEighthQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomEighthQuestionListener" );
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
                if ( selected_choices.size () == 0 ){
                    Toast.makeText ( getActivity (), "You can skip this question!", Toast.LENGTH_SHORT ).show ();
                }else{
                    viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
                    SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
                    MedlynkRequests.newSymptomEighthQuestionAnswer ( getActivity ()
                    , New_Symptom_8th_question.this,
                            manager.getAppointmentID ()
                    ,selected_choices);
                }
                break;
            }
            case R.id.btnSkipQuestion:{
                mListener.onEightQuestion ();
                break;
            }
        }
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "i = [" + i + "]: " + string_choices[i] );
    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {
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
        }
        selected_choices.add ( answer );
    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {
        int i = integer;
        selected_choices.remove ( i );
    }

    @Override
    public void onEighthAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "New_Symptom_8th_question.onEighthAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onEightQuestion ();
    }

    @Override
    public void onEightAnswerFailure() {
        System.out.println ( "New_Symptom_8th_question.onEightAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onEightQuestion ();
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
    public interface OnNewSymptomEighthQuestionListener {
        // TODO: Update argument type and name
        void onEightQuestion();
    }
}
