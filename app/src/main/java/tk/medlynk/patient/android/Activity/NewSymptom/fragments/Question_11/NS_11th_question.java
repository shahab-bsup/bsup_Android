package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_11;

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

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnNewSymptomEleventhQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_11th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_11th_question extends Fragment implements
        OnEleventhAnswerListener {

    public static final String TAG = NS_11th_question.class.getSimpleName ();

    private OnNewSymptomEleventhQuestionListener mListener;

    private NS_11th_VH viewHolder;

    public NS_11th_question() {
        // Required empty public constructor
    }

    public static NS_11th_question newInstance(String param1, String param2) {
        NS_11th_question fragment = new NS_11th_question ();
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
        // Inflate the layout for this fragment...
        View view = inflater.inflate ( R.layout.fragment_new__symptom_11th_question, container, false );
        viewHolder = new NS_11th_VH ( view );

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomEleventhQuestionListener) {
            mListener = (OnNewSymptomEleventhQuestionListener) context;
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
    public void onEleventhAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println ( "NS_11th_question.onEleventhAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onEleventhQuestion ();
    }

    @Override
    public void onEleventhAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        System.out.println ( "NS_11th_question.onEleventhAnswerFailure" );
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
    public interface OnNewSymptomEleventhQuestionListener {
        // TODO: Update argument type and name
        void onEleventhQuestion();
    }
}
