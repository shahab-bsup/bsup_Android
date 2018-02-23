package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
 * {@link New_Symptom_2nd_question.OnNewSymptomSecondQuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_2nd_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_2nd_question extends Fragment implements
        View.OnClickListener,
        OnSecondAnswerListener, ViewSelection.OnSingleItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = New_Symptom_2nd_question.class.getSimpleName ();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnNewSymptomSecondQuestionListener mListener;

    private View question_view;
    private TextView second_question;
    private Button button_next, button_skip;
    private AppCompatEditText second_answer;
    private ProgressBar progressBar;
    private ViewSelection choice;
    private int selected_choice = -1;

    public New_Symptom_2nd_question() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment New_Symptom_2nd_question.
     */
    // TODO: Rename and change types and number of parameters
    public static New_Symptom_2nd_question newInstance(String param1, String param2) {
        New_Symptom_2nd_question fragment = new New_Symptom_2nd_question ();
        Bundle args = new Bundle ();
        args.putString ( ARG_PARAM1, param1 );
        args.putString ( ARG_PARAM2, param2 );
        fragment.setArguments ( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        if (getArguments () != null) {
            mParam1 = getArguments ().getString ( ARG_PARAM1 );
            mParam2 = getArguments ().getString ( ARG_PARAM2 );
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment...
        View view = inflater.inflate ( R.layout.fragment_new__symptom_2nd_question, container, false );;
        New_Symptom_2nd_question_ViewHolder viewHolder = new New_Symptom_2nd_question_ViewHolder ( view );
        progressBar = view.findViewById ( R.id.progress_bar );
        question_view = view.findViewById ( R.id.new_symptom_second_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        button_next = view.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( this );
        button_skip = view.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( this );
        choice = view.findViewById ( R.id.viewSelectionChoice );
        choice.setOnSingleItemSelectedListener ( this );
        choice.setTextToButtons ( getActivity ().getResources ().getString ( R.string.i_do_not_know ), 0 );
        second_question.setText ( R.string.new_symptom_second_question );
        second_answer = view.findViewById ( R.id.new_symptom_second_answer );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomSecondQuestionListener) {
            mListener = (OnNewSymptomSecondQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomSecondQuestionListener" );
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
                if(TextUtils.isEmpty ( second_answer.getText ().toString () ) && selected_choice == -1 ){
                    Toast.makeText ( getActivity (), "You can skip this question", Toast.LENGTH_LONG ).show ();
                }else if( !TextUtils.isEmpty ( second_answer.getText ().toString () ) && selected_choice != -1 ){
                    Toast.makeText ( getActivity (), "Just select one answer!", Toast.LENGTH_LONG ).show ();
                } else if( !TextUtils.isEmpty ( second_answer.getText ().toString () ) ){
                    progressBar.setVisibility ( View.VISIBLE );
                    Answer answer = new Answer ();
                    answer.setReply ( second_answer.getText ().toString () );
                    answer.setChoice ( "a" );
                    SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );;
                    MedlynkRequests.newSymptomSecondQuestionAnswer ( getActivity (), New_Symptom_2nd_question.this,manager.getAppointmentID (), answer );
                }else if ( selected_choice != -1 ){
                    progressBar.setVisibility ( View.VISIBLE );
                    Answer answer = new Answer ();
                    answer.setChoice ( "b" );
                    SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
                    MedlynkRequests.newSymptomSecondQuestionAnswer ( getActivity (), New_Symptom_2nd_question.this, manager.getAppointmentID (), answer );
                }
                break;
            }

            case R.id.btnSkipQuestion:{
                mListener.onSecondQuestion ();
                break;
            }
        }
    }

    @Override
    public void onSecondAnswerSuccess(NewSymptomAnswerResponse response) {
        mListener.onSecondQuestion ();
    }

    @Override
    public void onSecondAnswerFailure() {
        progressBar.setVisibility ( View.GONE );
    }

    @Override
    public void onSingleItemSelected(int i) {
        selected_choice = i;
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
    public interface OnNewSymptomSecondQuestionListener {
        // TODO: Update argument type and name
        void onSecondQuestion();
    }
}
