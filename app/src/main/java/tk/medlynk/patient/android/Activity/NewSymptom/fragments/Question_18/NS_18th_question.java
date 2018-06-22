package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_18;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Activity.NewSymptom.OnNewSymptomAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_16.NS_16th_VH;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_16.NS_16th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_19.NS_19th_question;
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
 * {@link NS_18th_question.OnNewSymptomEighteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_18th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_18th_question extends Fragment implements
        OnNewSymptomAnswerListener,
        NS_18th_VH.OnEighteenNSVHListener {

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();


    public static final String TAG = "NS_18th_question";

    private OnNewSymptomEighteenQuestionListener mListener;

    private NS_18th_VH viewHolder;

    public NS_18th_question() {
        // Required empty public constructor
    }

    public static NS_18th_question newInstance() {
        NS_18th_question fragment = new NS_18th_question ();
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
        View view = inflater.inflate ( R.layout.fragment_new__symptom_18th_question, container, false );
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of ( getActivity () ).get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        mMedlynkViewModel.getAnswers ( manager.getAppointmentID (), Constants.NEW_SYMPTOM_ROW,0, 18 )
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
                        viewHolder = new NS_18th_VH( view ,answerDB);
                        viewHolder.setOnEighteenNSVHListener ( NS_18th_question.this );
                    }

                } );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomEighteenQuestionListener) {
            mListener = (OnNewSymptomEighteenQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomEighteenQuestionListener" );
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
            mMedlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (), Constants.NEW_SYMPTOM_ROW,0, 18, JC.answersToAnswerJson ( answersForDB ) );
        else
            mMedlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (), Constants.NEW_SYMPTOM_ROW,0,18, JC.answersToAnswerJson ( answersForDB ) );

        System.out.println ( "NS_18th_question.onEighteenAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        if( response.getAnswer ().getChoice ().equals ( "b" ) ){
            mListener.onJumpToEnd ();
        }else{
            mListener.onEighteenQuestion ();
        }
    }

    @Override
    public void onAnswerFailure() {
        System.out.println ( "NS_18th_question.onEighteenAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "tra again later!", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_18th_question.onUnauthorized" );

    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_18th_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomQuestionsAnswer ( getActivity (), NS_18th_question.this,
                manager.getAppointmentID (),"18",
                answer);

        answersForDB.add(answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "NS_18th_question.onSkipClicked" );
        mListener.onEighteenQuestion ();
    }

    public interface OnNewSymptomEighteenQuestionListener {
        void onEighteenQuestion();
        void onJumpToEnd();
    }
}
