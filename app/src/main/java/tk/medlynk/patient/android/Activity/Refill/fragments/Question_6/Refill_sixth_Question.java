package tk.medlynk.patient.android.Activity.Refill.fragments.Question_6;

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

import tk.medlynk.patient.android.Activity.Refill.fragments.Question_3.Refill_third_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_3.Refill_third_VH;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_6.Refill_sixth_VH.OnRefillSixthVHListener;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.JsonConverter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.MotherCallback;
import tk.medlynk.patient.android.Model.SymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

public class Refill_sixth_Question extends Fragment implements OnRefillSixthVHListener, MotherCallback {
    public static final String TAG = "Refill_sixth_Question";
    private onRefillSixthQuestionInteractionListener mListener;
    private Refill_sixth_VH viewHolder;

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private List<Answer> answersDB=new ArrayList<>();
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();


    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        JsonConverter JC = JsonConverter.getInstance ();
        if (existsRecord == false)
            mMedlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (), Constants.REFILL_A_MEDICATION_ROW,
                    manager.getQuestionSetID(),6, JC.answersToAnswerJson ( answersForDB ) );
        else
            mMedlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (), Constants.REFILL_A_MEDICATION_ROW,
                    manager.getQuestionSetID(),6, JC.answersToAnswerJson ( answersForDB ) );

        System.out.println("Refill_sixth_Question.onAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onRefillSixthQuestion();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("Refill_sixth_Question.onAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onRefillSixthQuestion();
    }

    public interface onRefillSixthQuestionInteractionListener {
        void onRefillSixthQuestion();
    }

    public static Refill_sixth_Question newInstance(String param1, String param2) {
        Refill_sixth_Question fragment = new Refill_sixth_Question();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refill_sixth__question, container, false);
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of ( getActivity () ).get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        mMedlynkViewModel.getAnswers ( manager.getAppointmentID (), Constants.REFILL_A_MEDICATION_ROW,
                manager.getQuestionSetID(),6 )
                .observe ( (LifecycleOwner) this, new Observer<DataBaseModel>() {
                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existsRecord = true;
                            JsonConverter JC = JsonConverter.getInstance ();
                            answersDB = JC.answerJsonToAnswers ( dataBaseModel.getAnswerJson () );
                            Log.d ( TAG, "onChanged: " + answersDB );
                        }
                        viewHolder = new Refill_sixth_VH( view );
                        viewHolder.setOnRefillSixthVHListener ( Refill_sixth_Question.this );

                        if (answersDB!=null) {
                            viewHolder.onUpdateUI(answersDB);
                        }
                    }

                } );
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRefillSixthQuestionInteractionListener) {
            this.mListener = (onRefillSixthQuestionInteractionListener) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement onRefillFirstQuestionInteractionListener");
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println("Refill_sixth_Question.onNextClicked");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        MedlynkRequests.refill_sixth_question(getActivity(),
                manager.getAppointmentID(),
                manager.getQuestionSetID(),
                this,
                answer);

        answersForDB.add(answer);
    }

    @Override
    public void onNextClicked(List<Answer> answers) {
        System.out.println("Refill_sixth_Question.onNextClicked");
        System.out.println("list of answers");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        SharedPreferenceManager manager = new SharedPreferenceManager(getActivity());
        MedlynkRequests.refill_sixth_question(getActivity(),
                manager.getAppointmentID(),
                manager.getQuestionSetID(),
                this,
                answers);

        answersForDB=answers;
    }

    public void onSkipClicked() {
        System.out.println("Refill_sixth_Question.onSkipClicked");
        this.mListener.onRefillSixthQuestion();
    }
}
