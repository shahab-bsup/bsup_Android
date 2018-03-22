package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_4;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/4/2018.
 */

public class Follow_Up_Symptoms_4th_Question_ViewHolder extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private final String[] string_choices;
    private final ViewSelection choice_numbers;
    private final ViewSelection choices;
    private OnFollowUpFourthQuestionViewsClickListener onFollowUpFourthQuestionViewsClickListener;
    private final Answer answer;

    public Follow_Up_Symptoms_4th_Question_ViewHolder(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_fourth_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_4th_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        string_choices = itemView.getContext ().getResources ().getStringArray ( R.array.FUS_4th_choices_FUR_seventh_choices );
        choice_numbers = itemView.findViewById ( R.id.viewSelectionChoiceNumbers );
        for (int i = 0; i < choice_numbers.getNumberOfViews (); i++) {
            choice_numbers.setTextToButtons ( String.valueOf ( i + 1 ), i );
        }
        choices = itemView.findViewById ( R.id.viewSelectionChoices );
        for (int i = 0; i < choices.getNumberOfViews (); i++) {
            choices.setTextToButtons ( string_choices[i], i );
        }
        choices.setOnSingleItemSelectedListener ( this );
        answer = new Answer ();
    }


    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnFollowUpFourthQuestionViewsClickListener(OnFollowUpFourthQuestionViewsClickListener onFollowUpFourthQuestionViewsClickListener) {
        this.onFollowUpFourthQuestionViewsClickListener = onFollowUpFourthQuestionViewsClickListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "Follow_Up_Symptoms_4th_Question_ViewHolder.onSingleItemSelected" );
        button_next.setEnabled ( true );
        button_next.setBackgroundResource ( R.drawable.enable_next_question );
        answer.setRate ( i + 1 );
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "Follow_Up_Symptoms_4th_Question_ViewHolder.Follow_Up_Symptoms_4th_Question_ViewHolder" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFollowUpFourthQuestionViewsClickListener.onNextClick (answer);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "Follow_Up_Symptoms_4th_Question_ViewHolder.Follow_Up_Symptoms_4th_Question_ViewHolder" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFollowUpFourthQuestionViewsClickListener.onSkipClick ();
        }
    }

    public interface OnFollowUpFourthQuestionViewsClickListener{
        void onNextClick(Answer answer);
        void onSkipClick();
    }

}
