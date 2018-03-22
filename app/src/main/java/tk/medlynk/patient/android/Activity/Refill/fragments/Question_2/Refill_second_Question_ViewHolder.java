package tk.medlynk.patient.android.Activity.Refill.fragments.Question_2;

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

public class Refill_second_Question_ViewHolder extends ViewHolder implements OnSingleItemSelectedListener {
    private Answer answer = new Answer();
    private final Button button_next;
    private final Button button_skip;
    private final ViewSelection choices;
    private OnRefillSecondQuestionClickListener onRefillSecondQuestionClickListener;
    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final String[] string_choices;

    public Refill_second_Question_ViewHolder(View itemView) {
        super(itemView);
        this.progressBar =  itemView.findViewById(R.id.progress_bar);
        this.question_view = itemView.findViewById(R.id.refill_second_question);
        this.second_question =  this.question_view.findViewById(R.id.txtQuestion);
        this.second_question.setText(itemView.getContext().getString(R.string.refill_second_question));
        this.button_next =  itemView.findViewById(R.id.btnNextQuestion);
        this.button_next.setOnClickListener(new OnNextButtonClickListener());
        this.button_next.setEnabled(false);
        this.button_skip =  itemView.findViewById(R.id.btnSkipQuestion);
        this.button_skip.setOnClickListener(new OnSkipClickListener());
        this.choices =  itemView.findViewById(R.id.viewSelectionChoices);
        this.choices.setOnSingleItemSelectedListener(this);
        this.string_choices = itemView.getContext().getResources().getStringArray(R.array.refill_second_question_choices);
        for (int i = 0; i < this.choices.getNumberOfViews(); i++) {
            this.choices.setTextToButtons(this.string_choices[i], i);
        }
    }

    private class OnNextButtonClickListener implements OnClickListener {

        private OnNextButtonClickListener() {
        }
        public void onClick(View view) {
            System.out.println("Refill_second_Question_ViewHolder");
            System.out.println("OnNextButtonClickListener.onClick");
            onRefillSecondQuestionClickListener.onNextClicked();
        }

    }
    public interface OnRefillSecondQuestionClickListener {

        void onNextClicked();
        void onSkipClicked();

    }
    private class OnSkipClickListener implements OnClickListener {

        private OnSkipClickListener() {
        }
        public void onClick(View view) {
            System.out.println("Refill_second_Question_ViewHolder");
            System.out.println("OnSkipClickListener.onClick");
            onRefillSecondQuestionClickListener.onSkipClicked();
        }

    }

    public void setOnRefillSecondQuestionClickListener(OnRefillSecondQuestionClickListener onRefillSecondQuestionClickListener) {
        this.onRefillSecondQuestionClickListener = onRefillSecondQuestionClickListener;
    }

    public void onSingleItemSelected(View view, int i) {
        System.out.println("Refill_second_Question_ViewHolder.onSingleItemSelected");
    }
}
