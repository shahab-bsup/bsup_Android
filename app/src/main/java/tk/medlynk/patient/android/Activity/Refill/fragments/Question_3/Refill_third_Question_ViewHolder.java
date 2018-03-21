package tk.medlynk.patient.android.Activity.Refill.fragments.Question_3;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.medlynk.shahab.myviewselection.ViewSelection;
import com.medlynk.shahab.myviewselection.ViewSelection.OnClearStateListener;
import com.medlynk.shahab.myviewselection.ViewSelection.OnSingleItemSelectedListener;
import com.neweraandroid.demo.R;

public class Refill_third_Question_ViewHolder extends ViewHolder implements OnSingleItemSelectedListener, OnClearStateListener {
    private final Button button_next;
    private final Button button_skip;
    private final ViewSelection choice;
    private OnRefillThirdQuestionClickListener onRefillThirdQuestionClickListener;
    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final String string_choice;

    private class OnNextButtonClickListener implements OnClickListener {
        private OnNextButtonClickListener() {
        }

        public void onClick(View view) {
            System.out.println("Refill_third_Question_ViewHolder");
            System.out.println("OnNextButtonClickListener.onClick");
            Refill_third_Question_ViewHolder.this.onRefillThirdQuestionClickListener.onNextClicked();
        }
    }

    public interface OnRefillThirdQuestionClickListener {
        void onNextClicked();

        void onSkipClicked();
    }

    private class OnSkipClickListener implements OnClickListener {
        private OnSkipClickListener() {
        }

        public void onClick(View view) {
            System.out.println("Refill_third_Question_ViewHolder");
            System.out.println("OnSkipClickListener.onClick");
            Refill_third_Question_ViewHolder.this.onRefillThirdQuestionClickListener.onSkipClicked();
        }
    }

    public Refill_third_Question_ViewHolder(View itemView) {
        super(itemView);
        this.progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
        this.question_view = itemView.findViewById(R.id.refill_third_question);
        second_question = ((TextView) this.question_view.findViewById(R.id.txtQuestion));
        this.second_question.setText(itemView.getContext().getString(R.string.refill_third_question));
        this.button_next = (Button) itemView.findViewById(R.id.btnNextQuestion);
        this.button_next.setOnClickListener(new OnNextButtonClickListener());
        this.button_next.setEnabled(false);
        this.button_skip = (Button) itemView.findViewById(R.id.btnSkipQuestion);
        this.button_skip.setOnClickListener(new OnSkipClickListener());
        this.choice = (ViewSelection) itemView.findViewById(R.id.viewSelectionChoice);
        this.string_choice = itemView.getContext().getResources().getString(R.string.refill_third_question_choice);
        this.choice.setTextToButtons(this.string_choice, 0);
        this.choice.setOnSingleItemSelectedListener(this);
        this.choice.setOnClearStateListener(this);
    }

    public void setOnRefillThirdQuestionClickListener(OnRefillThirdQuestionClickListener onRefillThirdQuestionClickListener) {
        this.onRefillThirdQuestionClickListener = onRefillThirdQuestionClickListener;
    }

    public void onSingleItemSelected(View view, int i) {
        System.out.println("Refill_third_Question_ViewHolder.onSingleItemSelected");
    }

    public void onClearState(View view) {
        System.out.println("Refill_third_Question_ViewHolder.onClearState");
    }
}
