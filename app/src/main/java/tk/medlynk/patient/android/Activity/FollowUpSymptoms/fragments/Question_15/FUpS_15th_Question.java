package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_15;

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

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.OnFollowUpSymptomAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8.FUpS_8th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8.FUpS_8th_VH;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.JsonConverter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFollowUpSymptomsFifteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_15th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_15th_Question extends Fragment implements
        FUpS_15th_VH.OnFUpSFifteenVHListener, OnFollowUpSymptomAnswerListener {

    public static final String TAG = "FUpS_15th_Question";

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();

    private OnFollowUpSymptomsFifteenQuestionListener mListener;
    private FUpS_15th_VH viewHolder;

    public FUpS_15th_Question() {
        // Required empty public constructor
    }


    public static FUpS_15th_Question newInstance(String param1, String param2) {
        FUpS_15th_Question fragment = new FUpS_15th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_15th__question, container, false );
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of(getActivity()).get(MedlynkViewModel.class);
        manager = new SharedPreferenceManager(getActivity());
        mMedlynkViewModel.getAnswers(manager.getAppointmentID(), Constants.FOLLOW_UP_SYMPTOMS_ROW,0, 15)
                .observe((LifecycleOwner) this, new Observer<DataBaseModel>() {
                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existsRecord = true;
                            answerDB = new Answer();
                            JsonConverter JC = JsonConverter.getInstance();
                            answerDB = JC.answerJsonToAnswers(dataBaseModel.getAnswerJson())
                                    .get(0);
                        }
                        viewHolder = new FUpS_15th_VH(view, answerDB);
                        viewHolder.setOnFUpSFifteenVHListener(FUpS_15th_Question.this);
                    }

                });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsFifteenQuestionListener) {
            mListener = (OnFollowUpSymptomsFifteenQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnFollowUpSymptomsFirstQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClick(Answer answer) {
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        MedlynkRequests.followUpSymptomAnswer(getActivity(),FUpS_15th_Question.this
                ,manager.getAppointmentID(), "15",
                answer);

        answersForDB.add(answer);
    }

    @Override
    public void onSkipClick() {
        mListener.onFifteenQuestion ();
    }

    @Override
    public void onAnswerSuccess(FollowUpSymptomResponse response) {
        JsonConverter JC = JsonConverter.getInstance ();
        if (existsRecord == false)
            mMedlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (), Constants.FOLLOW_UP_SYMPTOMS_ROW,
                    0,15, JC.answersToAnswerJson ( answersForDB ) );
        else
            mMedlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (), Constants.FOLLOW_UP_SYMPTOMS_ROW,
                    0,15, JC.answersToAnswerJson ( answersForDB ) );
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onFifteenQuestion();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("FUpS_15th_Question.onFifteenAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
    }

    public interface OnFollowUpSymptomsFifteenQuestionListener {
        void onFifteenQuestion();
    }
}
