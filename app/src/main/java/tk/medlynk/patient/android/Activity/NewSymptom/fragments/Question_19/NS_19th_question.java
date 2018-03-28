package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_19;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_19th_question.OnNewSymptomNineteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_19th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_19th_question extends Fragment implements
        NS_19th_VH.OnNineteenNSVHListener,
        OnNineteenAnswerListener {

    public static final String TAG = NS_19th_question.class.getSimpleName ();

    private OnNewSymptomNineteenQuestionListener mListener;

    private String[] years_strings ={"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private NS_19th_VH viewHolder;

    public NS_19th_question() {
        // Required empty public constructor

    }


    public static NS_19th_question newInstance(String param1, String param2) {
        NS_19th_question fragment = new NS_19th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_19th_question, container, false );
        viewHolder = new NS_19th_VH ( view );
        viewHolder.setOnNineteenNSVHListener ( this );
        ArrayAdapter<String> years_adapter = new ArrayAdapter<String> ( getActivity (),
                android.R.layout.simple_spinner_dropdown_item, years_strings);
        viewHolder.setAdapter ( years_adapter );
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
        System.out.println ( "NS_19th_question.onDetach" );
        mListener = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println("NS_19th_question.onNextClicked");
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomNineteenQuestionAnswer ( getActivity (),
                NS_19th_question.this,
                manager.getAppointmentID (),
                answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_19th_question.onSkipClicked" );
        mListener.onNineteenQuestion ();
    }


    @Override
    public void onNineteenAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_19th_question.onNineteenAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onNineteenQuestion ();
    }

    @Override
    public void onNineteenAnswerFailure() {
        System.out.println ( "NS_19th_question.onNineteenAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus (View.GONE);
        Toast.makeText(getActivity(), "try again later!", Toast.LENGTH_SHORT).show();
    }

    public interface OnNewSymptomNineteenQuestionListener {
        void onNineteenQuestion();
    }
}
