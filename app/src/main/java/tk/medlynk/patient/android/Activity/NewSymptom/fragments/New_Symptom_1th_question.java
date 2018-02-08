package tk.medlynk.patient.android.Activity.NewSymptom.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.neweraandroid.demo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link New_Symptom_1th_question.OnNewSymptomFirstQuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_1th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_1th_question extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = New_Symptom_1th_question.class.getSimpleName ();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnNewSymptomFirstQuestionListener mListener;
    private View question_view;
    private TextView first_question, see_more;
    private Button button;
    private AppCompatEditText answer;

    public New_Symptom_1th_question() {
        // Required empty public constructor...

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment New_Symptom_1th_question.
     */
    // TODO: Rename and change types and number of parameters
    public static New_Symptom_1th_question newInstance(String param1, String param2) {
        New_Symptom_1th_question fragment = new New_Symptom_1th_question ();
        Bundle args = new Bundle ();
        args.putString ( ARG_PARAM1, param1 );
        args.putString ( ARG_PARAM2, param2 );
        fragment.setArguments ( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        System.out.println ( "New_Symptom_1th_question.onCreate" );
        if (getArguments () != null) {
            mParam1 = getArguments ().getString ( ARG_PARAM1 );
            mParam2 = getArguments ().getString ( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_new__symptom_1th_question, container, false );

        question_view = view.findViewById ( R.id.new_symptom_first_question );
        first_question = question_view.findViewById ( R.id.txtQuestion );
        button = view.findViewById ( R.id.btnNextQuestion );
        button.setOnClickListener ( this );
        see_more= question_view.findViewById ( R.id.txtQuestion_see_more );
        see_more.setVisibility ( View.VISIBLE );
        see_more.setOnClickListener ( this );
        first_question.setText ( R.string.new_symptom_first_question );
        answer = view.findViewById ( R.id.new_symptom_first_answer );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        System.out.println ( "New_Symptom_1th_question.onAttach" );
        super.onAttach ( context );
        if (context instanceof OnNewSymptomFirstQuestionListener) {
            mListener = (OnNewSymptomFirstQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomFirstQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        System.out.println ( "New_Symptom_1th_question.onDetach" );
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case R.id.txtQuestion_see_more:{
                see_more.setText ( R.string.see_more_new_symptom_first_question );
                break;
            }
            case R.id.btnNextQuestion:{
                mListener.onFirstQuestion ();
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
    public interface OnNewSymptomFirstQuestionListener {
        // TODO: Update argument type and name
        void onFirstQuestion();
    }
}
