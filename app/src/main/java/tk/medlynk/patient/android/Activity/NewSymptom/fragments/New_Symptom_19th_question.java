package tk.medlynk.patient.android.Activity.NewSymptom.fragments;

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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link New_Symptom_19th_question.OnNewSymptomNineteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_19th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_19th_question extends Fragment implements View.OnClickListener, ViewSelection.OnSingleItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = New_Symptom_19th_question.class.getSimpleName ();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnNewSymptomNineteenQuestionListener mListener;

    private View question_view, years_view;
    private Button next, skip;
    private TextView question;
    private Spinner spinner_years;
    private ViewSelection before_years_viewselection, after_years_viewselection;
    private String[] before_years_strings, after_years_strings;
    private String[] years_strings ={"1", "2", "3", "4", "5"};

    public New_Symptom_19th_question() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment New_Symptom_19th_question.
     */
    // TODO: Rename and change types and number of parameters
    public static New_Symptom_19th_question newInstance(String param1, String param2) {
        New_Symptom_19th_question fragment = new New_Symptom_19th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_19th_question, container, false );

        question_view = view.findViewById ( R.id.new_symptom_nineteen_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_nineteen_question );
        next = view.findViewById ( R.id.btnNextQuestion );
        next.setOnClickListener ( this );
        skip = view.findViewById ( R.id.btnSkipQuestion );
        skip.setOnClickListener ( this );

        //fetching string choices from string resources
        before_years_strings = getActivity ().getResources ().getStringArray ( R.array.question_19_before_years_choices );
        after_years_strings = getActivity ().getResources ().getStringArray ( R.array.question_19_after_years_choices );

        before_years_viewselection = view.findViewById ( R.id.viewSelectionChoicesBeforeYears );
        before_years_viewselection.setOnSingleItemSelectedListener ( this );
        for (int i = 0; i < before_years_viewselection.getNumberOfViews (); i++) {
            before_years_viewselection.setTextToButtons ( before_years_strings[i], i );
        }
        after_years_viewselection = view.findViewById ( R.id.viewSelectionChoicesAfterYears );
        for (int i = 0; i < after_years_viewselection.getNumberOfViews (); i++) {
            after_years_viewselection.setTextToButtons ( after_years_strings[i], i );
        }
        after_years_viewselection.setOnSingleItemSelectedListener ( this );

        years_view = view.findViewById ( R.id.question_19_years_row );
        spinner_years = years_view.findViewById ( R.id.spinner_years );
        ArrayAdapter<String> years_adapter = new ArrayAdapter<String> ( getActivity (),
                android.R.layout.simple_spinner_dropdown_item, years_strings);
        spinner_years.setAdapter ( years_adapter );

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
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case R.id.btnNextQuestion:{
                mListener.onNineteenQuestion ();

                break;
            }
            case R.id.btnSkipQuestion:{
                mListener.onNineteenQuestion ();

                break;
            }
        }
    }

    @Override
    public void onSingleItemSelected(int i) {

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
    public interface OnNewSymptomNineteenQuestionListener {
        // TODO: Update argument type and name
        void onNineteenQuestion();
    }
}
