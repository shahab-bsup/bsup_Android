package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_3;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Activity.NewSymptom.OnNewSymptomAnswerListener;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.JsonConverter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NS_3rd_question.OnNewSymptomThirdQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_3rd_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_3rd_question extends Fragment implements
        OnNewSymptomAnswerListener,
        NS_3rd_VH.OnThirdNSVHListener {

    public static final String TAG = "NS_3rd_question";

    @Nullable
    private OnNewSymptomThirdQuestionListener mListener;
    private NS_3rd_VH viewHolder;
    private MedlynkViewModel medlynkViewModel;
    @Nullable
    private SharedPreferenceManager manager;
    private boolean existsRecord = false;
    private Answer answerDB;
    @NonNull
    private List<Answer> answers = new ArrayList<> ();

    public NS_3rd_question() {
        // Required empty public constructor
    }

    @NonNull
    public static NS_3rd_question newInstance() {
        NS_3rd_question fragment = new NS_3rd_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_3rd_question, container, false );
        dbOperation ( view );
        return view;
    }

    private void dbOperation(final View view) {
        medlynkViewModel = ViewModelProviders.of ( getActivity () )
                .get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        medlynkViewModel.getAnswers ( manager.getAppointmentID (),
                Constants.NEW_SYMPTOM_ROW, 3 )
                .observe ( this, new Observer<DataBaseModel> () {
                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existsRecord = true;
                            JsonConverter jsonConverter = JsonConverter.getInstance ();
                            answerDB = jsonConverter.answerJsonToAnswers ( dataBaseModel.getAnswerJson () )
                                    .get ( 0 );
                            Log.d ( TAG, "onChanged: " + answerDB );
                        }
                        viewHolder = new NS_3rd_VH ( view, answerDB );
                        viewHolder.setOnThirdNSVHListener ( NS_3rd_question.this );
                    }
                } );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomThirdQuestionListener) {
            mListener = (OnNewSymptomThirdQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomThirdQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }


    @Override
    public void onAnswerSuccess(NewSymptomAnswerResponse response) {
        JsonConverter JC = JsonConverter.getInstance ();
        if (existsRecord == false)
            medlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (), Constants.NEW_SYMPTOM_ROW,
                    3, JC.answersToAnswerJson ( answers ) );
        else
            medlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (), Constants.NEW_SYMPTOM_ROW,
                    3, JC.answersToAnswerJson ( answers ) );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onThirdQuestion ();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println ( "NS_3rd_question.onThirdAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_3rd_question.onUnauthorized" );

    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_3rd_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomThirdQuestionAnswer ( getActivity (),
                NS_3rd_question.this,
                manager.getAppointmentID (),
                answer );
        answers.add ( answer );
    }

    @Override
    public void onSkipClicked() {
        mListener.onThirdQuestion ();
    }


    public interface OnNewSymptomThirdQuestionListener {
        void onThirdQuestion();
    }
}
