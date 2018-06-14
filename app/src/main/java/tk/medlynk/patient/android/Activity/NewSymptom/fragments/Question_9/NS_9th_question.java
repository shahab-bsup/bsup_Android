package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_9;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.medlynk.shahab.myviewselection.ViewSelection;
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
 * {@link NS_9th_question.OnNewSymptomNinthQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_9th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_9th_question extends Fragment implements
        OnNewSymptomAnswerListener,
        NS_9th_VH.OnNinthNSVHListener {

    public static final String TAG = "NS_9th_question";

    private OnNewSymptomNinthQuestionListener mListener;

    private NS_9th_VH viewHolder;
    //boolean for determining the existence of the record in database
    private boolean existRecord;
    private MedlynkViewModel medlynkViewModel;
    private SharedPreferenceManager manager;
    private List<Answer> answersDB = new ArrayList<> (  );

    public NS_9th_question() {
        // Required empty public constructor
    }

    public static NS_9th_question newInstance() {
        NS_9th_question fragment = new NS_9th_question ();
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
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_new__symptom_9th_question,
                container,
                false );
        dbOperation ( view );
        return view;
    }

    private void dbOperation(final View view) {
        medlynkViewModel = ViewModelProviders.of ( getActivity () )
                .get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        medlynkViewModel.getAnswers ( manager.getAppointmentID (),
                Constants.NEW_SYMPTOM_ROW,
                9).observe ( NS_9th_question.this,
                new Observer<DataBaseModel> () {
            private Answer answer;
            private List<Answer> answers;
            @Override
            public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                if( dataBaseModel != null ){
                    existRecord = true;
                    JsonConverter jsonConverter = JsonConverter.getInstance ();
                    if( jsonConverter.answerJsonToAnswers ( dataBaseModel.getAnswerJson () ).size () > 1 ){
                        answers = new ArrayList<> (  );
                        answers = jsonConverter.answerJsonToAnswers ( dataBaseModel.getAnswerJson () );
                    } else{
                        answer = new Answer ();
                        answer = jsonConverter
                                .answerJsonToAnswers ( dataBaseModel.getAnswerJson ()).get ( 0 );
                    }
                }
                viewHolder = new NS_9th_VH ( view );
                viewHolder.setOnNinthNSVHListener ( NS_9th_question.this );
                if( answers != null ){
                    viewHolder.onUpdateUI ( answers );
                }else if( answer != null ){
                    viewHolder.onUpdateUI ( answer );
                }
            }
        } );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomNinthQuestionListener) {
            mListener = (OnNewSymptomNinthQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomNinthQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onAnswerSuccess(NewSymptomAnswerResponse response) {
        Log.d ( TAG, "onAnswerSuccess " );
        JsonConverter jsonConverter = JsonConverter.getInstance ();
        if( !existRecord ){
            medlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (),
                    Constants.NEW_SYMPTOM_ROW,
                    9, jsonConverter.answersToAnswerJson ( answersDB ));
        }else{
            medlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (),
                    Constants.NEW_SYMPTOM_ROW,
                    9, jsonConverter.answersToAnswerJson ( answersDB ));
        }
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onNinthQuestion ();
    }

    @Override
    public void onAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onNinthQuestion ();
    }

    @Override
    public void onUnauthorized() {
        Log.d ( TAG, "onUnauthorized: " );
    }

    @Override
    public void onNextClicked(Answer answer) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomNinthQuestionAnswer ( getActivity (),
                NS_9th_question.this,
                manager.getAppointmentID (),
                answer);

        answersDB.clear ();
        answersDB.add ( answer );
    }

    @Override
    public void onNextClicked(List<Answer> answers) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomNinthQuestionAnswer ( getActivity (),
                NS_9th_question.this,
                manager.getAppointmentID (),
                answers);

        answersDB.clear ();
        answersDB.addAll ( answers );
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_9th_question.onSkipClicked" );
        mListener.onNinthQuestion ();
    }

    public interface OnNewSymptomNinthQuestionListener {
        void onNinthQuestion();
    }
}
