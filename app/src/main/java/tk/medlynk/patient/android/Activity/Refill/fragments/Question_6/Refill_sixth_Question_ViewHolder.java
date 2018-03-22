package tk.medlynk.patient.android.Activity.Refill.fragments.Question_6;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.medlynk.shahab.myviewselection.ViewSelection;
import com.medlynk.shahab.myviewselection.ViewSelection.OnClearStateListener;
import com.medlynk.shahab.myviewselection.ViewSelection.OnMultiItemSelectedListener;
import com.medlynk.shahab.myviewselection.ViewSelection.OnSingleItemSelectedListener;

import com.neweraandroid.demo.R;

public class Refill_sixth_Question_ViewHolder extends ViewHolder implements OnSingleItemSelectedListener, OnClearStateListener, OnMultiItemSelectedListener {
    private ViewSelection first_choice;
    private Button next;
    private OnRefillSixthQuestionClickListener onRefillSixthQuestionClickListener;
    private TextView question;
    private View question_view;
    private ViewSelection second_choices;
    private Button skip;
    private String[] string_choices;

    private class OnNextClickListener implements OnClickListener {
        private OnNextClickListener() {
        }

        public void onClick(View view) {
            System.out.println("Refill_sixth_Question_ViewHolder");
            System.out.println("OnNextClickListener.onClick");
            Refill_sixth_Question_ViewHolder.this.onRefillSixthQuestionClickListener.onNextClicked();
        }
    }

    public interface OnRefillSixthQuestionClickListener {
        void onNextClicked();

        void onSkipClicked();
    }

    private class OnSkipClickListener implements OnClickListener {
        private OnSkipClickListener() {
        }

        public void onClick(View view) {
            System.out.println("Refill_sixth_Question_ViewHolder");
            System.out.println("OnSkipClickListener.onClick");
            Refill_sixth_Question_ViewHolder.this.onRefillSixthQuestionClickListener.onSkipClicked();
        }
    }

    public Refill_sixth_Question_ViewHolder(View itemView) {
        super(itemView);
        this.question_view = itemView.findViewById(R.id.refill_sixth_question);
        question = ((TextView) this.question_view.findViewById(R.id.txtQuestion));
        this.question.setText(R.string.refill_sixth_question);
        this.next = (Button) itemView.findViewById(R.id.btnNextQuestion);
        this.next.setOnClickListener(new OnNextClickListener());
        this.skip = (Button) itemView.findViewById(R.id.btnSkipQuestion);
        this.skip.setOnClickListener(new OnSkipClickListener());
        this.first_choice = (ViewSelection) itemView.findViewById(R.id.viewSelectionChoicesFirst);
        this.first_choice.setOnSingleItemSelectedListener(this);
        this.string_choices = itemView.getContext().getResources().getStringArray(R.array.refill_sixth_question_choices);
        this.first_choice.setTextToButtons(this.string_choices[0], 0);
        this.second_choices = (ViewSelection) itemView.findViewById(R.id.viewSelectionChoicesSecond);
        this.second_choices.setTextToButtons(this.string_choices[1], 0);
        this.second_choices.setTextToButtons(this.string_choices[2], 1);
        this.second_choices.setOnMultiItemSelectedListener(this);
        this.second_choices.setOnClearStateListener(this);
        this.first_choice.setOnClearStateListener(this);
    }

    public void setOnRefillSixthQuestionClickListener(OnRefillSixthQuestionClickListener onRefillSixthQuestionClickListener) {
        this.onRefillSixthQuestionClickListener = onRefillSixthQuestionClickListener;
    }

    public void onSingleItemSelected(View view, int i) {
        System.out.println("Refill_sixth_Question_ViewHolder.onSingleItemSelected");
    }

    public void onClearState(View view) {
        System.out.println("Refill_sixth_Question_ViewHolder.onClearState");
    }

    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println("Refill_sixth_Question_ViewHolder.onMultiItemSelected");
    }

    public void onMultiItemDeselected(View view, Integer integer) {
        System.out.println("Refill_sixth_Question_ViewHolder.onMultiItemDeselected");
    }
}