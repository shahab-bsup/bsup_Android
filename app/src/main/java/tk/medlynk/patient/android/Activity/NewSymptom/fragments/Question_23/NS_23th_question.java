package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_23;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_23th_question.OnNewSymptomTwenty3QuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_23th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_23th_question extends Fragment implements
        NS_23th_VH.On23QuestionViewsClickListener{

    public static final String TAG = NS_23th_question.class.getSimpleName ();

    private OnNewSymptomTwenty3QuestionListener mListener;
    private NS_23th_VH viewHolder;

    public NS_23th_question() {
        // Required empty public constructor
    }

    public static NS_23th_question newInstance() {
        NS_23th_question fragment = new NS_23th_question();
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

        View view = inflater.inflate ( R.layout.fragment_new__symptom_23th_question, container, false );
        viewHolder = new NS_23th_VH( view );
        viewHolder.setListener ( this );
        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomTwenty3QuestionListener) {
            mListener = (OnNewSymptomTwenty3QuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomTwenty3QuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClicked() {
        System.out.println ( "NS_23th_question.onNextClicked" );
        mListener.onTwenty3Question ();
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_23th_question.onSkipClicked" );
        mListener.onTwenty3Question ();
    }

    @Override
    public void onViewSelectionClicked(View view, int i) {
        System.out.println ( "NS_23th_question.onViewSelectionClicked" );

    }

    public interface OnNewSymptomTwenty3QuestionListener {
        void onTwenty3Question();
    }
}
