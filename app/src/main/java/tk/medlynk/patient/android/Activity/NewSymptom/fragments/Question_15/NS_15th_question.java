package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_15;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2.NS_2nd_VH;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2.NS_2nd_question;
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
 * {@link NS_15th_question.OnNewSymptomFifteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_15th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_15th_question extends Fragment implements
        OnNewSymptomAnswerListener,
        NS_15th_VH.OnFifteenNSVHListener {

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();

    public static final String TAG = "NS_15th_question";

    private OnNewSymptomFifteenQuestionListener mListener;

    private NS_15th_VH viewHolder;
    private  int selected_choice = -1;

    public NS_15th_question() {
        // Required empty public constructor
    }

    public static NS_15th_question newInstance() {
        NS_15th_question fragment = new NS_15th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_15th_question, container, false );
        dbOperation ( view );
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of ( getActivity () ).get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        mMedlynkViewModel.getAnswers ( manager.getAppointmentID (), Constants.NEW_SYMPTOM_ROW,0, 15 )
                .observe ( (LifecycleOwner) this, new Observer<DataBaseModel>() {
                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existsRecord = true;
                            answerDB = new Answer ();
                            JsonConverter JC = JsonConverter.getInstance ();
                            answerDB = JC.answerJsonToAnswers ( dataBaseModel.getAnswerJson () )
                                    .get ( 0 );
                        }
                        viewHolder = new NS_15th_VH ( view ,answerDB);
                        viewHolder.setOnFifteenNSVHListener ( NS_15th_question.this );
                    }

                } );
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
    public void onAnswerSuccess(NewSymptomAnswerResponse response) {
        JsonConverter JC = JsonConverter.getInstance ();
        if (existsRecord == false)
            mMedlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (), Constants.NEW_SYMPTOM_ROW,0, 15, JC.answersToAnswerJson ( answersForDB ) );
        else
            mMedlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (), Constants.NEW_SYMPTOM_ROW,0, 15, JC.answersToAnswerJson ( answersForDB ) );

        System.out.println ( "NS_15th_question.onFifteenAnswerResponse" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onFifteenQuestion ();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println ( "NS_15th_question.onFifteenAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();

    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_15th_question.onUnauthorized" );

    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_15th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomQuestionsAnswer ( getActivity (),
                NS_15th_question.this,
                manager.getAppointmentID (),"15",
                answer);

        answersForDB.add ( answer );
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_15th_question.onSkipClicked" );
        mListener.onFifteenQuestion();
    }

    public interface OnNewSymptomFifteenQuestionListener {
        void onFifteenQuestion();
    }
}
