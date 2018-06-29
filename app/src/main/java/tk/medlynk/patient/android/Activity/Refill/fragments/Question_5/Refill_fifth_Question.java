package tk.medlynk.patient.android.Activity.Refill.fragments.Question_5;

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

import tk.medlynk.patient.android.Activity.Refill.RefillResultAnswer;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_3.Refill_third_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_3.Refill_third_VH;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.JsonConverter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.MotherCallback;
import tk.medlynk.patient.android.Model.SymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;

public class Refill_fifth_Question extends Fragment implements
        Refill_fifth_VH.OnRefillFifthVHListener, RefillResultAnswer {

    public static final String TAG = "Refill_fifth_Question";
    private onRefillFifthQuestionInteractionListener mListener;
    private Refill_fifth_VH viewHolder;

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "Refill_fifth_Question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);

        MedlynkRequests.refill_questionAnswer(getActivity(),this,
                manager.getAppointmentID(),"5", manager.getQuestionSetID(),
                answer);

        answersForDB.add(answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "Refill_fifth_Question.onSkipClicked" );
        mListener.onRefillFifthQuestion ();
    }

    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        JsonConverter JC = JsonConverter.getInstance ();
        if (existsRecord == false)
            mMedlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (), Constants.REFILL_A_MEDICATION_ROW,
                    manager.getQuestionSetID(),5, JC.answersToAnswerJson ( answersForDB ) );
        else
            mMedlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (), Constants.REFILL_A_MEDICATION_ROW,
                    manager.getQuestionSetID(), 5, JC.answersToAnswerJson ( answersForDB ) );

        System.out.println("Refill_fifth_Question.onAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onRefillFifthQuestion();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("Refill_fifth_Question.onAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
    }

    public interface onRefillFifthQuestionInteractionListener {
        void onRefillFifthQuestion();
    }

    public static Refill_fifth_Question newInstance(String param1, String param2) {
        Refill_fifth_Question fragment = new Refill_fifth_Question();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {

        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refill_fifth__question, container, false);
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of ( getActivity () ).get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        mMedlynkViewModel.getAnswers ( manager.getAppointmentID (), Constants.REFILL_A_MEDICATION_ROW,
                manager.getQuestionSetID(), 5 )
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
                        viewHolder = new Refill_fifth_VH( view );
                        viewHolder.setOnRefillFifthVHListener ( Refill_fifth_Question.this );

                        if (answerDB!=null){
                            viewHolder.onUpdateUI(answerDB);
                        }
                    }

                } );
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRefillFifthQuestionInteractionListener) {
            this.mListener = (onRefillFifthQuestionInteractionListener) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement onRefillFirstQuestionInteractionListener");
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}
