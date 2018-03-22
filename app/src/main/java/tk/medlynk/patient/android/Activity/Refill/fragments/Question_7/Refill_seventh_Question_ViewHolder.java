package tk.medlynk.patient.android.Activity.Refill.fragments.Question_7;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.medlynk.shahab.myviewselection.ViewSelection;
import com.medlynk.shahab.myviewselection.ViewSelection.OnSingleItemSelectedListener;

import com.neweraandroid.demo.R;

public class Refill_seventh_Question_ViewHolder extends ViewHolder implements OnSingleItemSelectedListener {
    private ViewSelection choices;
    private Button next;
    private OnRefillSeventhQuestionClickListener onRefillSeventhQuestionClickListener;
    private TextView question;
    private View question_view;
    private Button skip;
    private String[] string_choices;

    private class OnNextClickListener implements OnClickListener {
        private OnNextClickListener() {
        }

        public void onClick(View view) {
            System.out.println("Refill_seventh_Question_ViewHolder");
            System.out.println("OnNextClickListener.onClick");
            Refill_seventh_Question_ViewHolder.this.onRefillSeventhQuestionClickListener.onNextClicked();
        }
    }

    public interface OnRefillSeventhQuestionClickListener {
        void onNextClicked();

        void onSkipClicked();
    }

    private class OnSkipClickListener implements OnClickListener {
        private OnSkipClickListener() {
        }

        public void onClick(View view) {
            System.out.println("Refill_seventh_Question_ViewHolder");
            System.out.println("OnSkipClickListener.onClick");
            Refill_seventh_Question_ViewHolder.this.onRefillSeventhQuestionClickListener.onSkipClicked();
        }
    }

    public Refill_seventh_Question_ViewHolder(View itemView) {
        super(itemView);
        this.question_view = itemView.findViewById(R.id.refill_seventh_question);
        question = ((TextView) this.question_view.findViewById(R.id.txtQuestion));
        this.question.setText(R.string.refill_seventh_question);
        this.next = (Button) itemView.findViewById(R.id.btnNextQuestion);
        this.next.setOnClickListener(new OnNextClickListener());
        this.skip = (Button) itemView.findViewById(R.id.btnSkipQuestion);
        this.skip.setOnClickListener(new OnSkipClickListener());
        this.choices = (ViewSelection) itemView.findViewById(R.id.viewSelectionChoices);
        this.choices.setOnSingleItemSelectedListener(this);
        this.string_choices = itemView.getContext().getResources().getStringArray(R.array.yes_no);
        for (int i = 0; i < this.choices.getNumberOfViews(); i++) {
            this.choices.setTextToButtons(this.string_choices[i], i);
        }
    }

    public void setOnRefillSeventhQuestionClickListener(OnRefillSeventhQuestionClickListener onRefillSeventhQuestionClickListener) {
        this.onRefillSeventhQuestionClickListener = onRefillSeventhQuestionClickListener;
    }

    public void onSingleItemSelected(View view, int i) {
        System.out.println("Refill_seventh_Question_ViewHolder.onSingleItemSelected");
    }
}
