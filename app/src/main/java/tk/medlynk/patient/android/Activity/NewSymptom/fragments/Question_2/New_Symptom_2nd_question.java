package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neweraandroid.demo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link New_Symptom_2nd_question.OnNewSymptomSecondQuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_2nd_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_2nd_question extends Fragment implements View.OnClickListener {
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
    private Button button_next, button_skip, i_do_not_know;
    private Boolean is_i_do_not_know_selected = false;
    private ProgressBar progressBar;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_new__symptom_2nd_question, container, false );;
        progressBar = view.findViewById ( R.id.progress_bar );
        question_view = view.findViewById ( R.id.new_symptom_second_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        button_next = view.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( this );
        button_skip = view.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( this );
        i_do_not_know = view.findViewById ( R.id.btn_i_do_not_know );
        i_do_not_know.setOnClickListener ( this );
        second_question.setText ( R.string.new_symptom_second_question );
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
                //TODO add some network calls!!!
                mListener.onSecondQuestion (  );
                break;
            }

            case R.id.btnSkipQuestion:{
                mListener.onSecondQuestion ();
                break;
            }

            case R.id.btn_i_do_not_know:{
                if( !is_i_do_not_know_selected ){
                    i_do_not_know.setBackgroundResource ( R.drawable.answer_selected );
                    i_do_not_know.setTextColor ( getActivity ().getResources ().getColor ( R.color.white ) );
                    is_i_do_not_know_selected = true;
                }else{
                    i_do_not_know.setBackgroundResource ( R.drawable.answer_not_selected );
                    i_do_not_know.setTextColor ( getActivity ().getResources ().getColor ( R.color.answers_text_color ) );
                    is_i_do_not_know_selected = false;
                }

                break;
            }
        }
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
