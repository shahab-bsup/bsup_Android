package tk.medlynk.patient.android.Activity.Refill.fragments.Question_4;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.medlynk.shahab.myviewselection.ViewSelection;
import com.medlynk.shahab.myviewselection.ViewSelection.OnSingleItemSelectedListener;

import tk.medlynk.patient.android.Essentials.PercentageDialogueBuilder;
import tk.medlynk.patient.android.Model.Answer;

import com.neweraandroid.demo.R;

public class Refill_fourth_VH extends ViewHolder implements
        OnSingleItemSelectedListener,
        PercentageDialogueBuilder.OnPercenatgeClickListener {
    private final Button button_next;
    private final Button button_skip;
    private final ViewSelection choices;
    private OnRefillFourthVHListener onRefillFourthVHListener;
    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final String[] string_choices;
    private Answer answer;

    @Override
    public void onPercentageClicked(int s) {
        System.out.println("Refill_fourth_VH.onPercentageClicked");
        answer.setBetter(s);
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility(status);
    }

    private class OnNextButtonClickListener implements OnClickListener {
        public void onClick(View view) {
            System.out.println("Refill_fourth_VH");
            System.out.println("OnNextButtonClickListener.onClick");
            onRefillFourthVHListener.onNextClicked(answer);
        }
    }

    public interface OnRefillFourthVHListener {
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }

    private class OnSkipClickListener implements OnClickListener {
        public void onClick(View view) {
            System.out.println("Refill_fourth_VH");
            System.out.println("OnSkipClickListener.onClick");
            Refill_fourth_VH.this.onRefillFourthVHListener.onSkipClicked();

        }
    }

    public Refill_fourth_VH(View itemView) {
        super(itemView);
        this.progressBar =  itemView.findViewById(R.id.progress_bar);
        this.question_view = itemView.findViewById(R.id.refill_fourth_question);
        this.second_question =  this.question_view.findViewById(R.id.txtQuestion);
        this.second_question.setText(itemView.getContext().getString(R.string.refill_fourth_question));
        this.button_next =  itemView.findViewById(R.id.btnNextQuestion);
        this.button_next.setOnClickListener(new OnNextButtonClickListener());
        this.button_next.setEnabled(false);
        this.button_skip =  itemView.findViewById(R.id.btnSkipQuestion);
        this.button_skip.setOnClickListener(new OnSkipClickListener());
        this.choices = itemView.findViewById(R.id.viewSelectionChoices);
        this.choices.setOnSingleItemSelectedListener(this);
        this.string_choices = itemView.getContext().getResources()
                .getStringArray(R.array.refill_fourth_question_choices);
        choices.setDataSet ( string_choices );

        answer = new Answer();
    }

    public void onUpdateUI(Answer answerDB) {
        switch (answerDB.getChoice()){
            case "a":{
                choices.updateViewSelectionUI(0);
                this.answer=answerDB;
                break;
            }
            case "b":{
                choices.updateViewSelectionUI(1);
                this.answer=answerDB;
                break;
            }
            case "c":{
                choices.updateViewSelectionUI(2);
                this.answer=answerDB;
                break;
            }
            case "d":{
                choices.updateViewSelectionUI(3);
                this.answer=answerDB;
                break;
            }
        }

        button_next.setEnabled(true);
        button_next.setBackgroundResource(R.drawable.enable_next_question);
    }

    public void setOnRefillFourthVHListener(OnRefillFourthVHListener onRefillFourthQuestionClickListener) {
        this.onRefillFourthVHListener = onRefillFourthQuestionClickListener;
    }

    public void onSingleItemSelected(View view, int i) {
        System.out.println("Refill_fourth_VH.onSingleItemSelected");
        if( i == -1 ){
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
        }else{
            button_next.setEnabled(true);
            button_next.setBackgroundResource(R.drawable.enable_next_question);
            setAnswerChoice(i);
        }
    }

    private void setAnswerChoice(int i) {
        switch (i){
            case 0:{
                answer.setChoice("a");

                break;
            }
            case 1:{
                answer.setChoice("b");

                break;
            }
            case 2:{
                answer.setChoice("c");
                PercentageDialogueBuilder percentageDialogueBuilder = new
                        PercentageDialogueBuilder(itemView.getContext());
                percentageDialogueBuilder.setOnPercenatgeClickListener(this);
                percentageDialogueBuilder.show();

                break;
            }
            case 3:{
                answer.setChoice("d");

                break;
            }
        }
    }
}
