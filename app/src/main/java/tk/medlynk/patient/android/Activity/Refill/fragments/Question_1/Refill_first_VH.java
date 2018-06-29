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

import tk.medlynk.patient.android.Model.Answer;

public class Refill_first_VH extends ViewHolder {

    private AppCompatEditText answer_input;
    private Button button;
    private ProgressBar progressBar;
    private String question_resource;
    private TextView question;
    private View question_view;
    private OnRefillFirstQuestionClickListener refillFirstQuestionClickListener;
    private Answer answer;

    public Refill_first_VH(View itemView) {
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

        answer = new Answer();
    }

    public void onUpdateUI(Answer answerDB) {
        if(!answerDB.getReply().equals("")){
            answer_input.setText(answerDB.getReply());
        }
        answer=answerDB;
        button.setEnabled(true);
        button.setBackgroundResource(R.drawable.enable_next_question);
    }

    public void setFirstRefillAnswer(String answer){
        this.answer_input.setText ( answer );
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    private class OnAnswerInputTextWatcher implements TextWatcher {

        private OnAnswerInputTextWatcher() {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        public void afterTextChanged(Editable editable) {
            if( editable.length () > 0 ){
                answer.setReply(editable.toString());
                button.setEnabled ( true );
                button.setBackgroundResource ( R.drawable.enable_next_question );
            }else{
                answer.setReply("");
                button.setEnabled ( false );
                button.setBackgroundResource ( R.drawable.disable_next_question );
            }
        }

    }
    private class OnNextClickListener implements OnClickListener {
        public void onClick(View view) {
            System.out.println("Refill_first_VH");
            refillFirstQuestionClickListener.onNextClicked(answer);
        }

    }
    public interface OnRefillFirstQuestionClickListener {
        void onNextClicked(Answer answer);

    }

    public void setRefillFirstQuestionClickListener(OnRefillFirstQuestionClickListener refillFirstQuestionClickListener) {
        this.refillFirstQuestionClickListener = refillFirstQuestionClickListener;
    }
}
