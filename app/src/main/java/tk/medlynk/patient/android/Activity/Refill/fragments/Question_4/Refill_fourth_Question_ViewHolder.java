package tk.medlynk.patient.android.Activity.Refill.fragments.Question_4;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.medlynk.shahab.myviewselection.ViewSelection;
import com.medlynk.shahab.myviewselection.ViewSelection.OnSingleItemSelectedListener;
import tk.medlynk.patient.android.Model.Answer;

import com.neweraandroid.demo.R;

public class Refill_fourth_Question_ViewHolder extends ViewHolder implements OnSingleItemSelectedListener {
    private Answer answer = new Answer();
    private final Button button_next;
    private final Button button_skip;
    private final ViewSelection choices;
    private OnRefillFourthQuestionClickListener onRefillFourthQuestionClickListener;
    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final String[] string_choices;

    private class OnNextButtonClickListener implements OnClickListener {
        private OnNextButtonClickListener() {
        }

        public void onClick(View view) {
            System.out.println("Refill_fourth_Question_ViewHolder");
            System.out.println("OnNextButtonClickListener.onClick");
            Refill_fourth_Question_ViewHolder.this.onRefillFourthQuestionClickListener.onNextClicked();
        }
    }

    public interface OnRefillFourthQuestionClickListener {
        void onNextClicked();

        void onSkipClicked();
    }

    private class OnSkipClickListener implements OnClickListener {
        private OnSkipClickListener() {
        }

        public void onClick(View view) {
            System.out.println("Refill_fourth_Question_ViewHolder");
            System.out.println("OnSkipClickListener.onClick");
            Refill_fourth_Question_ViewHolder.this.onRefillFourthQuestionClickListener.onSkipClicked();
        }
    }

    public Refill_fourth_Question_ViewHolder(View itemView) {
        super(itemView);
        this.progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
        this.question_view = itemView.findViewById(R.id.refill_fourth_question);
        this.second_question = (TextView) this.question_view.findViewById(R.id.txtQuestion);
        this.second_question.setText(itemView.getContext().getString(R.string.refill_fourth_question));
        this.button_next = (Button) itemView.findViewById(R.id.btnNextQuestion);
        this.button_next.setOnClickListener(new OnNextButtonClickListener());
        this.button_next.setEnabled(false);
        this.button_skip = (Button) itemView.findViewById(R.id.btnSkipQuestion);
        this.button_skip.setOnClickListener(new OnSkipClickListener());
        this.choices = (ViewSelection) itemView.findViewById(R.id.viewSelectionChoices);
        this.choices.setOnSingleItemSelectedListener(this);
        this.string_choices = itemView.getContext().getResources().getStringArray(R.array.refill_fourth_question_choices);
        for (int i = 0; i < this.choices.getNumberOfViews(); i++) {
            this.choices.setTextToButtons(this.string_choices[i], i);
        }
    }

    public void setOnRefillFourthQuestionClickListener(OnRefillFourthQuestionClickListener onRefillFourthQuestionClickListener) {
        this.onRefillFourthQuestionClickListener = onRefillFourthQuestionClickListener;
    }

    public void onSingleItemSelected(View view, int i) {
        System.out.println("Refill_fourth_Question_ViewHolder.onSingleItemSelected");
    }
}
