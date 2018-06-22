package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_3;

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
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2.NS_2nd_VH;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2.NS_2nd_question;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.JsonConverter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Model.MotherCallback;
import tk.medlynk.patient.android.Model.SymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFURThirdQuestionInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FUpR_3rd_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpR_3rd_Question extends Fragment implements
        FUpR_3rd_VH.OnFURThirdVHListener, OnFollowUpResultAnswerListener {

    public static final String TAG = "FUpR_3rd_Question";
    private OnFURThirdQuestionInteractionListener mListener;
    private FUpR_3rd_VH viewHolder;

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();

    public FUpR_3rd_Question() {
        // Required empty public constructor
    }

    public static FUpR_3rd_Question newInstance() {
        FUpR_3rd_Question fragment = new FUpR_3rd_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow_up_results_3rd__question, container, false );
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of ( getActivity () ).get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        mMedlynkViewModel.getAnswers ( manager.getAppointmentID (), Constants.FOLLOW_UP_RESULTS_ROW,0, 3 )
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
                        viewHolder = new FUpR_3rd_VH( view, answerDB );
                        viewHolder.setOnFURThirdVHListener ( FUpR_3rd_Question.this );
                    }

                } );
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFURThirdQuestionInteractionListener) {
            mListener = (OnFURThirdQuestionInteractionListener) context;
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
        System.out.println ( "FUpR_3rd_Question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus(View.VISIBLE);
        MedlynkRequests.followUpResultAnswer(getActivity(),FUpR_3rd_Question.this,
                manager.getAppointmentID(),"3",
                answer);

        answersForDB.add(answer);
    }

    @Override
    public void onSkipClicked() {
        System.out.println ( "FUpR_3rd_Question.onSkipClicked" );
        mListener.onFURThirdQuestion ();
    }

    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        JsonConverter JC = JsonConverter.getInstance ();
        if (existsRecord == false)
            mMedlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (), Constants.FOLLOW_UP_RESULTS_ROW,0, 3, JC.answersToAnswerJson ( answersForDB ) );
        else
            mMedlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (), Constants.FOLLOW_UP_RESULTS_ROW,0, 3, JC.answersToAnswerJson ( answersForDB ) );

        System.out.println("FUpR_3rd_Question.onAnswerSuccess");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
        if( response.getAnswer().getChoice().equals("b") ){
            mListener.onJumpTo17thQuestion();
        }else
            mListener.onFURThirdQuestion();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("FUpR_3rd_Question.onAnswerFailure");
        viewHolder.setProgressBarVisibilityStatus(View.GONE);
    }


    public interface OnFURThirdQuestionInteractionListener {
        void onFURThirdQuestion();
        void onJumpTo17thQuestion();
    }
}
