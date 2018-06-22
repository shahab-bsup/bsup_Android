package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_17;

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

import tk.medlynk.patient.android.Activity.FollowUpResults.OnFollowUpResultAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_3.FUpR_3rd_Question;
import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_3.FUpR_3rd_VH;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.JsonConverter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.MotherCallback;
import tk.medlynk.patient.android.Model.SymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFURSeventeenQuestionInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FUpR_17th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpR_17th_Question extends Fragment implements
        FUpR_17th_VH.OnFURSeventeenVHListener, OnFollowUpResultAnswerListener {

    public static final String TAG = "FUpR_17th_Question";
    private OnFURSeventeenQuestionInteractionListener mListener;
    private FUpR_17th_VH viewHolder;

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();


    public FUpR_17th_Question() {
        // Required empty public constructor
    }

    public static FUpR_17th_Question newInstance(String param1, String param2) {
        FUpR_17th_Question fragment = new FUpR_17th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow_up_results_17th__question, container, false );
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of ( getActivity () ).get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        mMedlynkViewModel.getAnswers ( manager.getAppointmentID (), Constants.FOLLOW_UP_RESULTS_ROW,0, 17 )
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
                        viewHolder = new FUpR_17th_VH( view, answerDB );
                        viewHolder.setOnFURSeventeenVHListener ( FUpR_17th_Question.this );
                    }

                } );
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFURSeventeenQuestionInteractionListener) {
            mListener = (OnFURSeventeenQuestionInteractionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnFURFirstQuestionInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println("FUpR_17th_Question.onNextClicked");
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        MedlynkRequests.followUpResultAnswer(getActivity(),FUpR_17th_Question.this,
                manager.getAppointmentID(),"17",
                answer);

        answersForDB.add(answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println("FUpR_17th_Question.onSkipClicked");
        mListener.onFURSeventeenQuestion();
    }

    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        JsonConverter JC = JsonConverter.getInstance ();
        if (existsRecord == false)
            mMedlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (), Constants.FOLLOW_UP_RESULTS_ROW,0,17, JC.answersToAnswerJson ( answersForDB ) );
        else
            mMedlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (), Constants.FOLLOW_UP_RESULTS_ROW,0, 17, JC.answersToAnswerJson ( answersForDB ) );

        System.out.println("FUpR_17th_Question.onAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        mListener.onFURSeventeenQuestion();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("FUpR_17th_Question.onAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
    }


    public interface OnFURSeventeenQuestionInteractionListener {
        void onFURSeventeenQuestion();
    }
}
