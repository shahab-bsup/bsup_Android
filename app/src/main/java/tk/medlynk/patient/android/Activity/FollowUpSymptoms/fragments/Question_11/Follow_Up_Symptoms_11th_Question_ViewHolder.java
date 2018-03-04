package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_11;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10.Follow_Up_Symptoms_10th_Question_ViewHolder;

/**
 * Created by Shahab on 3/4/2018.
 */

public class Follow_Up_Symptoms_11th_Question_ViewHolder extends RecyclerView.ViewHolder {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private OnFollowUpEleventhQuestionViewsClickListener onFollowUpEleventhQuestionViewsClickListener;

    public Follow_Up_Symptoms_11th_Question_ViewHolder(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_eleventh_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_11th_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
    }

    public void setOnFollowUpEleventhQuestionViewsClickListener(OnFollowUpEleventhQuestionViewsClickListener onFollowUpEleventhQuestionViewsClickListener) {
        this.onFollowUpEleventhQuestionViewsClickListener = onFollowUpEleventhQuestionViewsClickListener;
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "Follow_Up_Symptoms_11th_Question_ViewHolder.Follow_Up_Symptoms_11th_Question_ViewHolder" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFollowUpEleventhQuestionViewsClickListener.onNextClick ();
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "Follow_Up_Symptoms_11th_Question_ViewHolder.Follow_Up_Symptoms_11th_Question_ViewHolder" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFollowUpEleventhQuestionViewsClickListener.onSkipClick ();
        }
    }

    public interface OnFollowUpEleventhQuestionViewsClickListener{
        void onNextClick();
        void onSkipClick();
    }

}
