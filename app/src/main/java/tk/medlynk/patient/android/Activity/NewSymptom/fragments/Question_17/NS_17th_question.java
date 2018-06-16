package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_17;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.ObservableArrayList;
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
 * {@link NS_17th_question.OnNewSymptomSeventeenQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_17th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_17th_question extends Fragment implements
        OnSeventeenAnswerListener,
        NS_17th_VH.OnSeventeenNSVHListener {

    public static final String TAG = "NS_17th_question";

    private OnNewSymptomSeventeenQuestionListener mListener;

    private NS_17th_VH viewHolder;
    private MedlynkViewModel medlynkViewModel;
    private SharedPreferenceManager manager;
    private boolean existRecord = false;
    private List<Answer> answersDB = new ArrayList<> (  );

    public NS_17th_question() {
        // Required empty public constructor
    }

    public static NS_17th_question newInstance() {
        NS_17th_question fragment = new NS_17th_question ();
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
        final View view =
                inflater.inflate ( R.layout.fragment_new__symptom_17th_question,
                        container,
                        false );
        medlynkViewModel = ViewModelProviders.of ( getActivity () )
                .get ( MedlynkViewModel.class );
        manager = new SharedPreferenceManager ( getActivity () );
        medlynkViewModel.getAnswers ( manager.getAppointmentID (),
                Constants.NEW_SYMPTOM_ROW,
                17 ).observe ( NS_17th_question.this, new Observer<DataBaseModel> () {
            public Answer answer;
            public List<Answer> answers;

            @Override
            public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                if (dataBaseModel != null) {
                    existRecord = true;
                    JsonConverter jsonConverter = JsonConverter.getInstance ();
                    answers = new ArrayList<> ();
                    answers = jsonConverter.answerJsonToAnswers ( dataBaseModel.getAnswerJson () );
                }
                viewHolder = new NS_17th_VH ( view );
                viewHolder.setOnSeventeenNSVHListener ( NS_17th_question.this );
                if (answers != null) {
                    viewHolder.onUpdateUI ( answers );
                }
            }
        } );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomSeventeenQuestionListener) {
            mListener = (OnNewSymptomSeventeenQuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomSeventeenQuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onSeventeenAnswerSuccess(NewSymptomAnswerResponse response) {
        Log.d ( TAG, "onSeventeenAnswerSuccess: " );
        JsonConverter jsonConverter = JsonConverter.getInstance ();
        if( !existRecord ){
            medlynkViewModel.insertAnswersToDB ( manager.getAppointmentID (),
                    Constants.NEW_SYMPTOM_ROW,
                    17, jsonConverter.answersToAnswerJson ( answersDB ));
        }else{
            medlynkViewModel.updateAnswersToDB ( manager.getAppointmentID (),
                    Constants.NEW_SYMPTOM_ROW,
                    17, jsonConverter.answersToAnswerJson ( answersDB ));
        }
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onSeventeenQuestion ();
    }

    @Override
    public void onSeventeenAnswerFailure() {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        mListener.onSeventeenQuestion ();
    }

    @Override
    public void onUnauthorized() {

    }

    @Override
    public void onNextClicked(List<Answer> answer) {
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        SharedPreferenceManager manager = new SharedPreferenceManager ( getActivity () );
        MedlynkRequests.newSymptomSeventeenQuestionAnswer ( getActivity (),
                NS_17th_question.this,
                manager.getAppointmentID (),
                answer );

        answersDB.clear ();
        answersDB.addAll ( answer );
    }

    @Override
    public void onSkipClicked() {
        mListener.onSeventeenQuestion ();
    }

    public interface OnNewSymptomSeventeenQuestionListener {
        void onSeventeenQuestion();
    }
}
