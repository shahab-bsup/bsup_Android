package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_1;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/4/2018.
 */

public class FUpS__1st_VH extends RecyclerView.ViewHolder {

    private View question_view;
    private TextView question;
    private String question_resource;
    private Button button;
    private ProgressBar progressBar;
    private AppCompatEditText answer_input;
    private OnFUpSFirstVHListener onFUpSFirstVHListener;

    public FUpS__1st_VH(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_first_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question_resource = itemView.getContext ().
                getResources ().getString ( R.string.follow_up_symptoms_1st_question );
        question.setText ( question_resource );
        button = itemView.findViewById ( R.id.btnNextQuestion );
        button.setOnClickListener ( new OnNextClickListener() );
        button.setEnabled ( false );
        answer_input = itemView.findViewById ( R.id.follow_up_symptom_first_answer );
        answer_input.addTextChangedListener ( new OnAnswerInputTextWatcher() );
    }

    public void onUpdateUI(Answer answerDB) {
        answer_input.setText(answerDB.getReply());
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnFUpSFirstVHListener(OnFUpSFirstVHListener onFUpSFirstVHListener) {
        this.onFUpSFirstVHListener = onFUpSFirstVHListener;
    }

    public String getAnswerInput() {
        return answer_input.getText ().toString ();
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS__1st_VH.FUpS__1st_VH" );
            System.out.println ( "OnNextClickListener.onClick" );
            onFUpSFirstVHListener.onNextClick ();
        }
    }

    public interface OnFUpSFirstVHListener {
        void onNextClick();
    }

    private class OnAnswerInputTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if( editable.length () > 0 ){
                button.setEnabled ( true );
                button.setBackgroundResource ( R.drawable.enable_next_question );
            }else{
                button.setEnabled ( false );
                button.setBackgroundResource ( R.drawable.disable_next_question );
            }
        }
    }
}
