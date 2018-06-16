package tk.medlynk.patient.android.Activity.Refill.fragments.Question_1;

import android.arch.lifecycle.LifecycleOwner;
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

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2.NS_2nd_VH;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2.NS_2nd_question;
import tk.medlynk.patient.android.Activity.Refill.RefillResultAnswer;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_1.Refill_first_VH.OnRefillFirstQuestionClickListener;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.JsonConverter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.MotherCallback;
import tk.medlynk.patient.android.Model.SymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;


public class Refill_first_Question extends Fragment implements
        OnRefillFirstQuestionClickListener,
        RefillResultAnswer {
    public static final String TAG = "Refill_first_Question";
    private onRefillFirstQuestionInteractionListener mListener;
    private Refill_first_VH viewHolder;

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();

    public static Refill_first_Question newInstance(String param1, String param2) {
        Refill_first_Question fragment = new Refill_first_Question ();
        fragment.setArguments ( new Bundle () );
        return fragment;
    }

    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        JsonConverter JC = JsonConverter.getInstance ();
        if (existsRecord == false)
            mMedlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (), Constants.REFILL_A_MEDICATION_ROW, 1, JC.answersToAnswerJson ( answersForDB ) );
        else
            mMedlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (), Constants.REFILL_A_MEDICATION_ROW, 1, JC.answersToAnswerJson ( answersForDB ) );

        System.out.println ( "Refill_first_Question.onAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        new SharedPreferenceManager ( getActivity () ).setQuestionSetID ( response.getQuestionSetId () );
        mListener.onRefillFistQuestion ();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println ( "Refill_first_Question.onAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        if (getArguments () == null) {

        }
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.fragment_refill_first__question, container, false );
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of ( getActivity () ).get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        mMedlynkViewModel.getAnswers ( manager.getAppointmentID (), Constants.REFILL_A_MEDICATION_ROW, 1 )
                .observe ( (LifecycleOwner) this, new Observer<DataBaseModel>() {
                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existsRecord = true;
                            answerDB = new Answer ();
                            JsonConverter JC = JsonConverter.getInstance ();
                            answerDB = JC.answerJsonToAnswers ( dataBaseModel.getAnswerJson () )
                                    .get ( 0 );
                            Log.d ( TAG, "onChanged: " + answerDB );
                        }
                        viewHolder = new Refill_first_VH( view, answerDB );
                        viewHolder.setRefillFirstQuestionClickListener ( Refill_first_Question.this );
                    }

                } );
    }


    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof onRefillFirstQuestionInteractionListener) {
            this.mListener = (onRefillFirstQuestionInteractionListener) context;
            return;
        }
        throw new RuntimeException ( context.toString () + " must implement onRefillFirstQuestionInteractionListener" );
    }

    public void onDetach() {
        super.onDetach ();
        this.mListener = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "Refill_first_Question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        MedlynkRequests.refill_questionAnswer ( getActivity (),this,
                manager.getAppointmentID (),"1",-1,
                answer );

        answersForDB.add(answer);
    }

    public interface onRefillFirstQuestionInteractionListener {
        void onRefillFistQuestion();
    }
}
