package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_14;

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

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.OnFollowUpSymptomAnswerListener;
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
 * {@link OnFollowUpSymptomsFourteenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link FUpS_14th_Question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FUpS_14th_Question extends Fragment implements
        FUpS_14th_VH.OnFUpSFourteenVHListener,
        OnFollowUpSymptomAnswerListener {

    public static final String TAG = "FUpS_14th_Question";

    private OnFollowUpSymptomsFourteenQuestionListener mListener;
    private FUpS_14th_VH viewHolder;

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private List<Answer> answersDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<>();

    public FUpS_14th_Question() {
        // Required empty public constructor
    }

    public static FUpS_14th_Question newInstance(String param1, String param2) {
        FUpS_14th_Question fragment = new FUpS_14th_Question();
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
        View view = inflater.inflate ( R.layout.fragment_follow__up__symptoms_14th__question, container, false );
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of ( getActivity () ).get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        mMedlynkViewModel.getAnswers ( manager.getAppointmentID (), Constants.FOLLOW_UP_SYMPTOMS_ROW,0, 14 )
                .observe ( (LifecycleOwner) this, new Observer<DataBaseModel>() {
                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existsRecord = true;
                            answersDB = new ArrayList<>();
                            JsonConverter JC = JsonConverter.getInstance ();
                            answersDB = JC.answerJsonToAnswers ( dataBaseModel.getAnswerJson () );
                        }
                        viewHolder = new FUpS_14th_VH( view );
                        viewHolder.setOnFUpSFourteenVHListener ( FUpS_14th_Question.this );
                        if (answersDB!=null){
                            viewHolder.onUpdateUI(answersDB);
                        }
                    }

                } );
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFollowUpSymptomsFourteenQuestionListener) {
            mListener = (OnFollowUpSymptomsFourteenQuestionListener) context;
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
    public void onNextClick(List<Answer> answers) {
        System.out.println ( "FUpS_14th_Question.onNextClick" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        MedlynkRequests.followUpSymptomAnswer ( getActivity (),this,
                manager.getAppointmentID (),"14" , answers);

        answersForDB=answers;
    }

    @Override
    public void onSkipClick() {
        System.out.println ( "FUpS_14th_Question.onSkipClick" );
        mListener.onFourteenQuestion ();
    }

    @Override
    public void onNextClick(Answer answer) {
        System.out.println("FUpS_14th_Question.onNextClick");
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        MedlynkRequests.followUpSymptomAnswer ( getActivity (),this,
                manager.getAppointmentID (), "14", answer);

        answersForDB.add(answer);
    }

    @Override
    public void onAnswerSuccess(FollowUpSymptomResponse response) {
        JsonConverter JC = JsonConverter.getInstance ();
        if (existsRecord == false)
            mMedlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (), Constants.FOLLOW_UP_SYMPTOMS_ROW,0, 14, JC.answersToAnswerJson ( answersForDB ) );
        else
            mMedlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (), Constants.FOLLOW_UP_SYMPTOMS_ROW,0, 14, JC.answersToAnswerJson ( answersForDB ) );

        System.out.println ( "FUpS_14th_Question.onThirteenAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onFourteenQuestion ();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println ( "FUpS_14th_Question.onThirteenAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }


    public interface OnFollowUpSymptomsFourteenQuestionListener {
        void onFourteenQuestion();
    }
}
