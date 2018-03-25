package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7;

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

public class FUpS_7th_Question_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private ViewSelection answerChoices, choice_numbers;
    private String[] choices_strings;
    private OnFUpSSeventhVHListener onFUpSSeventhVHListener;
    private Answer answer;

    public FUpS_7th_Question_VH(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_seventh_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_7th_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        choice_numbers = itemView.findViewById ( R.id.viewSelectionChoiceNumbers );
        for (int i = 0; i < choice_numbers.getNumberOfViews (); i++) {
            choice_numbers.setTextToButtons ( String.valueOf ( i + 1 ), i );
        }
        choices_strings = itemView.getContext ().getResources ().getStringArray ( R.array.FUS_7th_8th_choices_FUR_10th_11th_choices );
        answerChoices = itemView.findViewById ( R.id.viewSelectionChoices );
        for (int i = 0; i < answerChoices.getNumberOfViews (); i++) {
            answerChoices.setTextToButtons ( choices_strings[i], i );
        }
        answerChoices.setOnSingleItemSelectedListener ( this );
        answer = new Answer ();
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnFUpSSeventhVHListener(OnFUpSSeventhVHListener onFUpSSeventhVHListener) {
        this.onFUpSSeventhVHListener = onFUpSSeventhVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FUpS_7th_Question_VH.onSingleItemSelected" );
        if( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else{
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
            answer.setRate ( ++i );
        }
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_7th_Question_VH.FUpS_7th_Question_VH" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFUpSSeventhVHListener.onNextClick (answer);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_7th_Question_VH.FUpS_7th_Question_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFUpSSeventhVHListener.onSkipClick ();
        }
    }

    public interface OnFUpSSeventhVHListener {
        void onNextClick(Answer answer);
        void onSkipClick();
    }
}
