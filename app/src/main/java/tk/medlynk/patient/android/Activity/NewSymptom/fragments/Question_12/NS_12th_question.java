package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_12;

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
     * {@link OnNewSymptomTwelveQuestionListener} interface
     * to handle interaction events.
     * Use the {@link NS_12th_question#newInstance} factory method to
     * create an instance of this fragment.
     */
    public class NS_12th_question extends Fragment implements
            OnNewSymptomAnswerListener,
            NS_12th_VH.OnTwelveNSVHListener {

        private MedlynkViewModel mMedlynkViewModel;
        private boolean existsRecord = false;
        private List<Answer> answersDB ;
        private List<Answer> answersForDB = new ArrayList<>();
        private SharedPreferenceManager manager;

        public static final String TAG = "NS_12th_question";


    private OnNewSymptomTwelveQuestionListener mListener;
        private NS_12th_VH viewHolder;

        public NS_12th_question() {
        // Required empty public constructor
    }

    public static NS_12th_question newInstance() {
        NS_12th_question fragment = new NS_12th_question ();
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
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_new__symptom_12th_question, container, false );
        dbOperation(view);
        return view;
    }

    private void dbOperation(final View view) {
        mMedlynkViewModel = ViewModelProviders.of(getActivity()).get(MedlynkViewModel.class);
        manager = new SharedPreferenceManager(getActivity());

        mMedlynkViewModel.getAnswers(manager.getAppointmentID(), Constants.NEW_SYMPTOM_ROW,0, 12)
                .observe(this, new Observer<DataBaseModel>() {
                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existsRecord = true;
                            answersDB=new ArrayList<>();
                            JsonConverter JC = JsonConverter.getInstance();
                            answersDB = JC.answerJsonToAnswers(dataBaseModel.getAnswerJson());
                        }

                        viewHolder = new NS_12th_VH(view);
                        viewHolder.setOnNinthNSVHListener(NS_12th_question.this);
                        if (answersDB!=null){
                            viewHolder.onUpdateUI(answersDB);
                        }
                    }
                });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomTwelveQuestionListener) {
            mListener = (OnNewSymptomTwelveQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomTwelveQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }


    @Override
    public void onAnswerSuccess(NewSymptomAnswerResponse response) {
        JsonConverter JC = JsonConverter.getInstance();
        if (existsRecord == false)
            mMedlynkViewModel.insertAnswersToDB(manager.getAppointmentID(),
                    Constants.NEW_SYMPTOM_ROW,0,
                    12,
                    JC.answersToAnswerJson(answersForDB));
        else
            mMedlynkViewModel.updateAnswersToDB(manager.getAppointmentID(),
                    Constants.NEW_SYMPTOM_ROW,0,
                    12,
                    JC.answersToAnswerJson(answersForDB));

        System.out.println ( "NS_12th_question.onTwelveAnswerSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onTwelveQuestion ();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println ( "NS_12th_question.onTwelveAnswerFailure" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        Toast.makeText ( getActivity (), "try again later!", Toast.LENGTH_SHORT ).show ();
    }

        @Override
        public void onUnauthorized() {
            System.out.println ( "NS_12th_question.onUnauthorized" );

        }

        @Override
        public void onNextClicked(Answer answer) {
            System.out.println ( "NS_12th_question.onNextClicked" );
            viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
            MedlynkRequests.newSymptomQuestionsAnswer ( getActivity (),
                    NS_12th_question.this,
                    manager.getAppointmentID (),"12",
                    answer
            );
            answersForDB.clear();
            answersForDB.add(answer);
        }

        @Override
        public void onNextClicked(List<Answer> answers) {
            System.out.println ( "NS_12th_question.onNextClicked" );
            System.out.println ("list of answers");
            viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
            MedlynkRequests.newSymptomQuestionsAnswer ( getActivity (), NS_12th_question.this,
                    manager.getAppointmentID (),"12",
                    answers
            );

            answersForDB.clear();
            answersForDB=answers;
        }

        @Override
        public void onSkipClicked() {
            System.out.println ( "NS_12th_question.onSkipClicked" );
            mListener.onTwelveQuestion ();
        }

        public interface OnNewSymptomTwelveQuestionListener {
        void onTwelveQuestion();
    }
}
