package tk.medlynk.patient.android.Activity.NewSymptom.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.StartQuestionSet.StartAppointmentActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link New_Symptom_24th_question.OnNewSymptomTwenty4QuestionListener} interface
 * to handle interaction events.
 * Use the {@link New_Symptom_24th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class New_Symptom_24th_question extends Fragment {

    public static final String TAG = New_Symptom_24th_question.class.getSimpleName ();

    private OnNewSymptomTwenty4QuestionListener mListener;

    Button button;
    private View view;

    public New_Symptom_24th_question() {
        // Required empty public constructor
    }

    public static New_Symptom_24th_question newInstance() {
        New_Symptom_24th_question fragment = new New_Symptom_24th_question ();
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
        view =  inflater.inflate ( R.layout.fragment_new__symptom_24th_question, container, false );
        button = view.findViewById ( R.id.btnGoToQuestionSet );
        button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                getActivity ().startActivity ( new Intent ( getActivity (), StartAppointmentActivity.class ) );
            }
        } );

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomTwenty4QuestionListener) {
            mListener = (OnNewSymptomTwenty4QuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomTwenty4QuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
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
    public interface OnNewSymptomTwenty4QuestionListener {
        // TODO: Update argument type and name
        void onTwenty4Question();
    }
}
