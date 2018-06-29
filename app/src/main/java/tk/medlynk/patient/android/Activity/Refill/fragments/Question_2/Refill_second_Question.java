package tk.medlynk.patient.android.Activity.Refill.fragments.Question_2;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.MessageQueue;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tk.medlynk.patient.android.Activity.Refill.RefillResultAnswer;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_2.Refill_second_VH.OnRefillSecondQuestionClickListener;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.JsonConverter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.SymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;


public class Refill_second_Question extends Fragment implements OnRefillSecondQuestionClickListener, RefillResultAnswer {
    public static final String TAG = "Refill_second_Question";
    private onRefillSecondQuestionInteractionListener mListener;
    private Refill_second_VH viewHolder;

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();

    public static Refill_second_Question newInstance(String param1, String param2) {
        Refill_second_Question fragment = new Refill_second_Question();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refill_second__question, container, false);
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of ( getActivity () ).get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        mMedlynkViewModel.getAnswers ( manager.getAppointmentID (), Constants.REFILL_A_MEDICATION_ROW,manager.getQuestionSetID(), 2 )
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
                        viewHolder = new Refill_second_VH( view );
                        viewHolder.setOnRefillSecondQuestionClickListener ( Refill_second_Question.this );

                        if(answerDB!=null){
                            viewHolder.onUpdateUI(answerDB);
                        }
                    }

                } );
    }

    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        JsonConverter JC = JsonConverter.getInstance ();
        if (existsRecord == false)
            mMedlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (), Constants.REFILL_A_MEDICATION_ROW,
                    manager.getQuestionSetID(),2, JC.answersToAnswerJson ( answersForDB ) );
        else
            mMedlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (), Constants.REFILL_A_MEDICATION_ROW,
                    manager.getQuestionSetID(),2, JC.answersToAnswerJson ( answersForDB ) );

        System.out.println("Refill_second_Question.onAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onRefillSecondQuestion();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("Refill_second_Question.onAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);

    }

    public interface onRefillSecondQuestionInteractionListener {
        void onRefillSecondQuestion();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRefillSecondQuestionInteractionListener) {
            this.mListener = (onRefillSecondQuestionInteractionListener) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement onRefillFirstQuestionInteractionListener");
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void onNextClicked(Answer answer) {
        System.out.println("Refill_second_Question.onNextClicked");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        MedlynkRequests.refill_questionAnswer(getActivity(),this,
                manager.getAppointmentID(),"2", manager.getQuestionSetID(),
                answer);

        answersForDB.add(answer);
    }

    public void onSkipClicked() {
        System.out.println("Refill_second_Question.onSkipClicked");
        mListener.onRefillSecondQuestion ();
    }
}
