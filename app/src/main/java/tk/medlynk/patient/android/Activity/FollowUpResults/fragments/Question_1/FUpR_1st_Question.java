package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_1;

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
import android.widget.Toast;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Activity.FollowUpResults.OnFollowUpResultAnswerListener;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.JsonConverter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.SymptomResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFURFirstQuestionInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FUpR_1st_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpR_1st_Question extends Fragment
        implements FUpR_1st_VH.OnFURFirstVHListener, OnFollowUpResultAnswerListener {

    public static final String TAG = "FUpR_1st_Question";
    private OnFURFirstQuestionInteractionListener mListener;
    private FUpR_1st_VH viewHolder;

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private List<Answer> answersDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();

    public FUpR_1st_Question() {
        // Required empty public constructor...
    }

    public static FUpR_1st_Question newInstance() {
        FUpR_1st_Question fragment = new FUpR_1st_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow_up_results_1st__question, container, false );
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of ( getActivity () ).get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        mMedlynkViewModel.getAnswers ( manager.getAppointmentID (), Constants.FOLLOW_UP_RESULTS_ROW,0, 1 )
                .observe ( (LifecycleOwner) this, new Observer<DataBaseModel>() {
                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existsRecord = true;
                            answersDB = new ArrayList<>();
                            JsonConverter JC = JsonConverter.getInstance ();
                            answersDB = JC.answerJsonToAnswers ( dataBaseModel.getAnswerJson () );
                        }
                        viewHolder = new FUpR_1st_VH( view, answersDB );
                        viewHolder.setOnFURFirstVHListener ( FUpR_1st_Question.this );
                    }

                } );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFURFirstQuestionInteractionListener) {
            mListener = (OnFURFirstQuestionInteractionListener) context;
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
    public void onNextClick(Answer answer) {
        System.out.println ( "FUpR_1st_Question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        MedlynkRequests.followUpResultAnswer ( getActivity (),this,
                manager.getAppointmentID (),"1", answer );


        answersForDB.add(answer);
    }

    @Override
    public void onNextClick(List<Answer> answers) {
        System.out.println ( "FUpR_1st_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        MedlynkRequests.followUpResultAnswer ( getActivity (),this,
                manager.getAppointmentID (), "1", answers );

        answersForDB=answers;
    }

    @Override
    public void onAnswerSuccess(SymptomResponse response) {
        JsonConverter JC = JsonConverter.getInstance ();
        if (existsRecord == false)
            mMedlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (), Constants.FOLLOW_UP_RESULTS_ROW,0, 1, JC.answersToAnswerJson ( answersForDB ) );
        else
            mMedlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (), Constants.FOLLOW_UP_RESULTS_ROW,0, 1, JC.answersToAnswerJson ( answersForDB ) );

        System.out.println ( "FUpR_1st_Question.onAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onFURFirstQuestion ();

    }

    @Override
    public void onAnswerFailure() {
        System.out.println ( "FUpR_1st_Question.onAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again!", Toast.LENGTH_SHORT ).show ();

    }

    public interface OnFURFirstQuestionInteractionListener {
        void onFURFirstQuestion();
    }
}
