package tk.medlynk.patient.android.Activity.Refill.fragments.Question_1;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neweraandroid.demo.R;

public class Refill_first_Question_ViewHolder extends ViewHolder {
    private AppCompatEditText answer_input;
    private Button button;
    private ProgressBar progressBar;
    private TextView question;
    private String question_resource;
    private View question_view;
    private OnRefillFirstQuestionClickListener refillFirstQuestionClickListener;

    public Refill_first_Question_ViewHolder(View itemView) {
        super(itemView);
        this.progressBar =  itemView.findViewById(R.id.progress_bar);
        this.question_view = itemView.findViewById(R.id.refill_first_question);
        this.question_resource = itemView.getContext().getResources().getString(R.string.refill_first_question);
        question = this.question_view.findViewById(R.id.txtQuestion);
        this.question.setText(this.question_resource);
        this.button =  itemView.findViewById(R.id.btnNextQuestion);
        this.button.setOnClickListener(new OnNextClickListener());
        this.button.setEnabled(false);
        this.answer_input = itemView.findViewById(R.id.refill_first_answer);
        this.answer_input.addTextChangedListener(new OnAnswerInputTextWatcher());
    }
    private class OnAnswerInputTextWatcher implements TextWatcher {

        private OnAnswerInputTextWatcher() {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        public void afterTextChanged(Editable editable) {
            if (editable.length() > 0) {
                Refill_first_Question_ViewHolder.this.button.setEnabled(true);
                Refill_first_Question_ViewHolder.this.button.setBackgroundResource(R.drawable.enable_next_question);
                return;
            }
            Refill_first_Question_ViewHolder.this.button.setEnabled(false);
            Refill_first_Question_ViewHolder.this.button.setBackgroundResource(R.drawable.disable_next_question);
        }

    }
    private class OnNextClickListener implements OnClickListener {
        public void onClick(View view) {
            System.out.println("Refill_first_Question_ViewHolder");
            Refill_first_Question_ViewHolder.this.refillFirstQuestionClickListener.onNextClicked();
        }

    }
    public interface OnRefillFirstQuestionClickListener {
        void onNextClicked();

    }

    public void setRefillFirstQuestionClickListener(OnRefillFirstQuestionClickListener refillFirstQuestionClickListener) {
        this.refillFirstQuestionClickListener = refillFirstQuestionClickListener;
    }
}
