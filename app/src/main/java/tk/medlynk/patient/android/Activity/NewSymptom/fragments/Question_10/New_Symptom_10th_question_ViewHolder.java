package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_10;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 2/23/2018.
 */

public class New_Symptom_10th_question_ViewHolder extends RecyclerView.ViewHolder {

    private ProgressBar progressBar;
    private AppCompatEditText answer;

    public New_Symptom_10th_question_ViewHolder(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        answer = itemView.findViewById ( R.id.new_symptom_tenth_answer );
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }

    public String getAnswer(){
        return answer.getText ().toString ();
    }

    public void setAnswerEmpty() {
        this.answer.setText ( "" );
    }
}