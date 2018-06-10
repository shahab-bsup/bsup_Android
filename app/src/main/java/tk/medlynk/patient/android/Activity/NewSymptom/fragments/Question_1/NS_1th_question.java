package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_1;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Activity.NewSymptom.OnNewSymptomAnswerListener;
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
 * {@link NS_1th_question.OnNewSymptomFirstQuestionListener} interface
 * to handle interaction events.
 * Use the {@link NS_1th_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NS_1th_question extends Fragment implements View.OnClickListener, OnNewSymptomAnswerListener {

    private MedlynkViewModel mMedlynkViewModel;
    private boolean existsRecord = false;
    private List<Answer> answers = new ArrayList<>();
    private SharedPreferenceManager manager;

    public static final String TAG = "NS_1th_question";

    private OnNewSymptomFirstQuestionListener mListener;
    private View question_view;
    private TextView first_question, see_more;
    private Button button;
    private AppCompatEditText answerInput;
    private ProgressBar progressBar;

    public NS_1th_question() {
        // Required empty public constructor...

    }

    public static NS_1th_question newInstance(String param1, String param2) {
        NS_1th_question fragment = new NS_1th_question();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("NS_1th_question.onCreate");
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mMedlynkViewModel = ViewModelProviders.of(getActivity()).
                get(MedlynkViewModel.class);

        View view = inflater.inflate(R.layout.fragment_new__symptom_1th_question, container, false);
        progressBar = view.findViewById(R.id.progress_bar);
        question_view = view.findViewById(R.id.new_symptom_first_question);
        first_question = question_view.findViewById(R.id.txtQuestion);
        button = view.findViewById(R.id.btnNextQuestion);
        button.setBackgroundResource(R.drawable.disable_next_question);
        button.setOnClickListener(this);
        button.setClickable(false);
        see_more = question_view.findViewById(R.id.txtQuestion_see_more);
        see_more.setVisibility(View.VISIBLE);
        see_more.setOnClickListener(this);
        first_question.setText(R.string.new_symptom_first_question);
        answerInput = view.findViewById(R.id.new_symptom_first_answer);
        answerInput.addTextChangedListener(new AnswerInputTextChangeListener());

        manager = new SharedPreferenceManager(getActivity());

        mMedlynkViewModel.getAnswers(manager.getAppointmentID(), 1, 1)
                .observe(this, new Observer<DataBaseModel>() {
                    @Override
                    public void onChanged(@Nullable DataBaseModel dataBaseModel) {
                        if (dataBaseModel != null) {
                            existsRecord = true;
                            JsonConverter JC = JsonConverter.getInstance();
                            answerInput.setText(JC.answerJsonToAnswers(dataBaseModel.getAnswerJson())
                                    .get(0).getReply());
                            Log.d(TAG, "onChanged: " + JC.answerJsonToAnswers(dataBaseModel.
                                    getAnswerJson()));
                        }
                    }
                });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        System.out.println("NS_1th_question.onAttach");
        super.onAttach(context);
        if (context instanceof OnNewSymptomFirstQuestionListener) {
            mListener = (OnNewSymptomFirstQuestionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNewSymptomFirstQuestionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("NS_1th_question.onDetach");
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtQuestion_see_more: {
                see_more.setText(R.string.see_more_new_symptom_first_question);
                break;
            }
            case R.id.btnNextQuestion: {
                progressBar.setVisibility(View.VISIBLE);
                Answer answer = new Answer();
                answer.setReply(answerInput.getText().toString());

                MedlynkRequests.newSymptomFirstQuestionAnswer(getActivity(),
                        NS_1th_question.this,
                        manager.getAppointmentID(),
                        "1",
                        answer);

                answers.add(answer);

                break;
            }
        }
    }

    @Override
    public void onAnswerSuccess(NewSymptomAnswerResponse response) {
        System.out.println("NS_1th_question.onFirstAnswerSuccess");

        JsonConverter JC = JsonConverter.getInstance();
        if (existsRecord == false)
            mMedlynkViewModel.insertAnswersToDB(manager.getAppointmentID(), 1, 1, JC.answersToAnswerJson(answers));
        else
            mMedlynkViewModel.updateAnswersToDB(manager.getAppointmentID(), 1, 1, JC.answersToAnswerJson(answers));

        progressBar.setVisibility(View.GONE);
        mListener.onFirstQuestion();
    }

    @Override
    public void onAnswerFailure() {
        System.out.println("NS_1th_question.onFirstAnswerFailure");
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onUnauthorized() {
        System.out.println("NS_1th_question.onUnauthorized");
    }

    public interface OnNewSymptomFirstQuestionListener {
        void onFirstQuestion();
    }

    private class AnswerInputTextChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length() == 0) {
                button.setBackgroundResource(R.drawable.disable_next_question);
                button.setClickable(false);
            } else {
                button.setBackgroundResource(R.drawable.enable_next_question);
                button.setClickable(true);
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
