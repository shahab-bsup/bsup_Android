package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 3/4/2018.
 */

public class Follow_Up_Symptoms_1st_Question_ViewHolder extends RecyclerView.ViewHolder {

    private View question_view;
    private TextView question;
    private String question_resource;
    private Button button;
    private ProgressBar progressBar;
    private OnFollowUpFirstQuestionViewsClickListener onFollowUpFirstQuestionViewsClickListener;

    public Follow_Up_Symptoms_1st_Question_ViewHolder(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_first_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question_resource = itemView.getContext ().
                getResources ().getString ( R.string.follow_up_symptoms_1st_question );
        question.setText ( question_resource );
        button = itemView.findViewById ( R.id.btnNextQuestion );
        button.setOnClickListener ( new OnNextClickListener() );
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnFollowUpFirstQuestionViewsClickListener(OnFollowUpFirstQuestionViewsClickListener onFollowUpFirstQuestionViewsClickListener) {
        this.onFollowUpFirstQuestionViewsClickListener = onFollowUpFirstQuestionViewsClickListener;
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "Follow_Up_Symptoms_1st_Question_ViewHolder.Follow_Up_Symptoms_1st_Question_ViewHolder" );
            System.out.println ( "OnNextClickListener.onClick" );
            onFollowUpFirstQuestionViewsClickListener.onNextClick ();
        }
    }

    public interface OnFollowUpFirstQuestionViewsClickListener{
        void onNextClick();
    }
}
