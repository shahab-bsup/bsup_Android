package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_4;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 2/23/2018.
 */

public class NS_4thVH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection choices, choiceNumbers;
    private String[] answerChoices;
    private OnFourthNSVHListener onFourthNSVHListener;
    private Answer answer;

    public NS_4thVH(View itemView) {
        super(itemView);
        answer = new Answer();
        progressBar = itemView.findViewById(R.id.progress_bar);
        question_view = itemView.findViewById(R.id.new_symptom_fourth_question);
        question = question_view.findViewById(R.id.txtQuestion);
        question.setText(R.string.new_symptom_fourth_question);
        button_next = itemView.findViewById(R.id.btnNextQuestion);
        button_next.setOnClickListener(new OnNextClickListener());
        button_next.setEnabled(false);
        button_skip = itemView.findViewById(R.id.btnSkipQuestion);
        button_skip.setOnClickListener(new OnSkipClickListener());
        choiceNumbers = itemView.findViewById(R.id.viewSelectionChoiceNumbers);
        choices = itemView.findViewById(R.id.viewSelectionChoices);
        choices.setOnSingleItemSelectedListener(this);

        //I know this is bad! Do not blame me please:D
        String[] strings = {"1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10"};
        choiceNumbers.setDataSet ( strings );

        answerChoices = itemView
                .getContext()
                .getResources()
                .getStringArray(R.array.question_4_choices);
        choices.setDataSet ( answerChoices );
    }

    public void onUpdateUI(Answer answerDB) {
        choices.updateViewSelectionUI(answerDB.getRate() - 1);
        answer=answerDB;
        button_next.setEnabled(true);
        button_next.setBackgroundResource(R.drawable.enable_next_question);
    }

    public void setProgressBarVisibilityStatus(int status) {
        this.progressBar.setVisibility(status);
    }

    @Override
    public void onSingleItemSelected(View view, int i) {

        System.out.println("NS_4thVH.onSingleItemSelected");
        if (i == -1) {
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
        } else {
            button_next.setEnabled(true);
            button_next.setBackgroundResource(R.drawable.enable_next_question);
            answer.setRate(++i);
        }
    }


    public void setOnFourthNSVHListener(OnFourthNSVHListener onFourthNSVHListener) {
        this.onFourthNSVHListener = onFourthNSVHListener;
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("NS_4thVH.NS_4thVH");
            System.out.println("OnNextClickListener.onClick");
            onFourthNSVHListener.onNextClicked(answer);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("NS_4thVH.NS_4thVH");
            System.out.println("OnSkipClickListener.onClick");
            onFourthNSVHListener.onSkipClicked();
        }
    }

    public interface OnFourthNSVHListener {
        void onNextClicked(Answer answer);

        void onSkipClicked();
    }

}
