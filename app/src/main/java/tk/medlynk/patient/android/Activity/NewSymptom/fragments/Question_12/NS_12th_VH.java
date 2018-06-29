package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_12;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 2/23/2018.
 */

public class NS_12th_VH extends RecyclerView.ViewHolder implements
        ViewSelection.OnSingleItemSelectedListener,
        ViewSelection.OnClearStateListener,
        ViewSelection.OnMultiItemSelectedListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection first, second;
    private String[] string_choices;
    private Answer choice;
    private List<Answer> choices;
    private OnTwelveNSVHListener onNinthNSVHListener;

    public NS_12th_VH(View itemView) {
        super(itemView);
        choices = new ArrayList<>();
        choice = new Answer();
        progressBar = itemView.findViewById(R.id.progress_bar);
        question_view = itemView.findViewById(R.id.new_symptom_twelve_question);
        question = question_view.findViewById(R.id.txtQuestion);
        question.setText(R.string.new_symptom_twelve_question);
        button_next = itemView.findViewById(R.id.btnNextQuestion);
        button_next.setOnClickListener(new OnNextClickListener());
        button_next.setEnabled(false);
        button_skip = itemView.findViewById(R.id.btnSkipQuestion);
        button_skip.setOnClickListener(new OnSkipClickListener());
        string_choices = itemView.
                getContext().
                getResources().
                getStringArray(R.array.question_12_choices);
        first = itemView.findViewById(R.id.viewSelectionFirst);
        first.setOnSingleItemSelectedListener(this);
        first.setOnClearStateListener(this);
        second = itemView.findViewById(R.id.viewSelectionSecond);
        second.setOnMultiItemSelectedListener(this);
        second.setOnClearStateListener(this);

        //I know this is bad! Do not blame me please:D
        String[] strings = {string_choices[0],
                string_choices[1],
                string_choices[2],
                string_choices[3]};

        second.setDataSet(strings);

        //I know this is bad! Do not blame me please:D
        String[] strings1 = {string_choices[4]};
        first.setDataSet(strings1);
    }

    public void onUpdateUI(List<Answer> answersDB) {

        if (answersDB.get(0).getChoice().equals("e")) {
            first.updateViewSelectionUI(0);
            choice=answersDB.get(0);
            choices.clear();
        }
        else {
            for (Answer answer : answersDB) {
                switch (answer.getChoice()) {
                    case "a": {
                        second.updateViewSelectionUI(0);
                        choices.add(answer);
                        break;
                    }
                    case "b": {
                        second.updateViewSelectionUI(1);
                        choices.add(answer);
                        break;
                    }
                    case "c": {
                        second.updateViewSelectionUI(2);
                        choices.add(answer);
                        break;
                    }
                    case "d": {
                        second.updateViewSelectionUI(3);
                        choices.add(answer);
                        break;
                    }
                }
            }
        }
        button_next.setEnabled(true);
        button_next.setBackgroundResource(R.drawable.enable_next_question);
    }

    public void setProgressBarVisibilityStatus(int status) {
        this.progressBar.setVisibility(status);
    }

    public void setOnNinthNSVHListener(OnTwelveNSVHListener onNinthNSVHListener) {
        this.onNinthNSVHListener = onNinthNSVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println("NS_12th_VH.onSingleItemSelected");
        if (i == -1) {
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
        } else {
            second.setClear();
            choice.setChoice("e");
            button_next.setEnabled(true);
            button_next.setBackgroundResource(R.drawable.enable_next_question);
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println("NS_12th_VH.onClearState");
        if (view.getId() == second.getId()) {
            choices.clear();
        }
    }


    @Override
    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println("NS_12th_VH.onMultiItemSelected");
        first.setClear();
        setAnswerChoices(integer);
    }

    private void setAnswerChoices(Integer integer) {
        Answer answer = new Answer();
        switch (integer) {
            case 0: {
                answer.setChoice("a");
                break;
            }
            case 1: {
                answer.setChoice("b");
                break;
            }
            case 2: {
                answer.setChoice("c");
                break;
            }
            case 3: {
                answer.setChoice("d");
                break;
            }
        }
        choices.add(answer);
        if (choices.size() == 1) {
            button_next.setEnabled(true);
            button_next.setBackgroundResource(R.drawable.enable_next_question);
        }
    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {
        System.out.println("NS_12th_VH.onMultiItemDeselected");
        int i = integer;
        Iterator<Answer> answerIterator = choices.iterator();
        switch (i) {
            case 0: {
                while (answerIterator.hasNext()) {
                    Answer answer = answerIterator.next();
                    if (answer.getChoice() != null &&
                            answer.getChoice().equals("a")) {
                        answerIterator.remove();
                        break;
                    }
                }

                break;
            }
            case 1: {
                while (answerIterator.hasNext()) {
                    Answer answer = answerIterator.next();
                    if (answer.getChoice() != null &&
                            answer.getChoice().equals("b")) {
                        answerIterator.remove();
                        break;
                    }
                }

                break;
            }
            case 2: {
                while (answerIterator.hasNext()) {
                    Answer answer = answerIterator.next();
                    if (answer.getChoice() != null &&
                            answer.getChoice().equals("c")) {
                        answerIterator.remove();
                        break;
                    }
                }

                break;
            }
            case 3: {
                while (answerIterator.hasNext()) {
                    Answer answer = answerIterator.next();
                    if (answer.getChoice() != null &&
                            answer.getChoice().equals("d")) {
                        answerIterator.remove();
                        break;
                    }
                }

                break;
            }
        }
        if (choices.size() == 0) {
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
        }
    }

    public interface OnTwelveNSVHListener {
        void onNextClicked(Answer answer);
        void onNextClicked(List<Answer> answers);
        void onSkipClicked();
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("NS_12th_VH.NS_12th_VH");
            System.out.println("OnNextClickListener.onClick");
            if (choices.size() > 0) {
                onNinthNSVHListener.onNextClicked(choices);
            } else {
                onNinthNSVHListener.onNextClicked(choice);
            }
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("NS_12th_VH.NS_12th_VH");
            System.out.println("OnSkipClickListener.onClick");
            onNinthNSVHListener.onSkipClicked();
        }
    }
}
