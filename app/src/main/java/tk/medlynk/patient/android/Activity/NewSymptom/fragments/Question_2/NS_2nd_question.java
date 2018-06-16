package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2;

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
 * {@link NS_2nd_question.OnNewSymptomSecondQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_2nd_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_2nd_question extends Fragment implements
        OnNewSymptomAnswerListener,
        NS_2nd_VH.OnSecondNSVHListener {

    public static final String TAG = "NS_2nd_question";
    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private Answer answerDB;
    private SharedPreferenceManager manager;
    private List<Answer> answersForDB = new ArrayList<> ();
    private OnNewSymptomSecondQuestionListener mListener;
    private NS_2nd_VH viewHolder;


    public NS_2nd_question() {
        // Required empty public constructor
    }

    public static NS_2nd_question newInstance() {
        NS_2nd_question fragment = new NS_2nd_question ();
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
        // Inflate the layout for this fragment...
        View view = inflater.inflate ( R.layout.fragment_new__symptom_2nd_question, container, false );
        dbOperation ( view );
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of ( getActivity () ).get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        mMedlynkViewModel.getAnswers ( manager.getAppointmentID (), Constants.NEW_SYMPTOM_ROW, 2 )
                .observe ( (LifecycleOwner) this, new Observer<DataBaseModel> () {
                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existsRecord = true;
                            answerDB = new Answer ();
                            JsonConverter JC = JsonConverter.getInstance ();
                            answerDB = JC.answerJsonToAnswers ( dataBaseModel.getAnswerJson () )
                                    .get ( 0 );
                        }
                        viewHolder = new NS_2nd_VH ( view, answerDB );
                        viewHolder.setOnSecondNSVHListener ( NS_2nd_question.this );
                    }

                } );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomSecondQuestionListener) {
            mListener = (OnNewSymptomSecondQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomSecondQuestionListener" );
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
            mMedlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (),
                    Constants.NEW_SYMPTOM_ROW, 2,
                    JC.answersToAnswerJson ( answersForDB ) );
        else
            mMedlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (),
                    Constants.NEW_SYMPTOM_ROW,
                    2, JC.answersToAnswerJson ( answersForDB ) );

        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onSecondQuestion ();

    }

    @Override
    public void onAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again!", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onUnauthorized() {
        System.out.println ( "NS_2nd_question.onUnauthorized" );

    }

    @Override
    public void onNextClicked(Answer answer) {
        System.out.println ( "NS_2nd_question.onNextClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        MedlynkRequests.newSymptomQuestionsAnswer ( getActivity (),
                NS_2nd_question.this,
                manager.getAppointmentID (), "2",
                answer );

        answersForDB.add ( answer );
    }

    @Override
    public void onSkipClicked() {
        mListener.onSecondQuestion ();
    }

    public interface OnNewSymptomSecondQuestionListener {
        // TODO: Update argument type and name
        void onSecondQuestion();
    }
}