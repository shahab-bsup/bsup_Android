package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_15;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
 * {@link New_Symptom_15th_question.OnNewSymptomFifteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_15th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_15th_question extends Fragment implements
        View.OnClickListener, ViewSelection.OnSingleItemSelectedListener, OnFifteenAnswerListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER...
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = New_Symptom_15th_question.class.getSimpleName ();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnNewSymptomFifteenQuestionListener mListener;

    private View question_view;
    private Button next, skip;
    private TextView question;

    private ViewSelection choice;
    private New_Symptom_15th_question_ViewHolder viewHolder;
    private  int selected_choice = -1;

    public New_Symptom_15th_question() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment New_Symptom_15th_question.
     */
    // TODO: Rename and change types and number of parameters
    public static New_Symptom_15th_question newInstance(String param1, String param2) {
        New_Symptom_15th_question fragment = new New_Symptom_15th_question ();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_new__symptom_15th_question, container, false );

        question_view = view.findViewById ( R.id.new_symptom_fifteen_question );
        viewHolder = new New_Symptom_15th_question_ViewHolder ( view );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_fifteen_question );
        next = view.findViewById ( R.id.btnNextQuestion );
        next.setOnClickListener ( this );
        skip = view.findViewById ( R.id.btnSkipQuestion );
        skip.setOnClickListener ( this );

        choice = view.findViewById ( R.id.viewSelectionChoice );
        choice.setTextToButtons ( getActivity ().getResources ().getString ( R.string.no ), 0 );
        choice.setOnSingleItemSelectedListener ( this );

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomFifteenQuestionListener) {
            mListener = (OnNewSymptomFifteenQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomFifteenQuestionListener" );
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
                String answerText = viewHolder.getAnswerText ();
                if( selected_choice == -1 && TextUtils.isEmpty ( answerText )){
                    Toast.makeText ( getActivity (), "You can skip this question!", Toast.LENGTH_SHORT ).show ();
                }else if( selected_choice  == 0 ) {
                    viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
                    Answer answer = new Answer ();
                    SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
                    answer.setChoice ( "b" );
                    MedlynkRequests.newSymptomFifteenQuestionAnswer ( getActivity (),
                            New_Symptom_15th_question.this,
                            manager.getAppointmentID (),
                            answer);
                } else{
                    viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
                    Answer answer = new Answer ();
                    SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
                    answer.setChoice ( "a" );
                    answer.setReply ( answerText );
                    MedlynkRequests.newSymptomFifteenQuestionAnswer ( getActivity (),
                            New_Symptom_15th_question.this,
                            manager.getAppointmentID (),
                            answer);
                }

                break;
            }
            case R.id.btnSkipQuestion:{
                mListener.onFifteenQuestion ();

                break;
            }
        }
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println (choice.getButtons ().get ( 0 ).getText ().toString ());
        selected_choice = i;
        viewHolder.setAnswerEmpty ();
    }

    @Override
    public void onFifteenAnswerResponse(NewSymptomAnswerResponse response) {
        System.out.println ( "New_Symptom_15th_question.onFifteenAnswerResponse" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onFifteenQuestion ();
    }

    @Override
    public void onFifteenAnswerFailure() {
        System.out.println ( "New_Symptom_15th_question.onFifteenAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
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
    public interface OnNewSymptomFifteenQuestionListener {
        // TODO: Update argument type and name
        void onFifteenQuestion();
    }
}
