package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_5;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.OtherDialogBuilder;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/4/2018.
 */

public class Follow_Up_Symptoms_5th_Question_ViewHolder extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener, OtherDialogBuilder.OnOtherDialogListener {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private final ViewSelection choices;
    private final String[] string_choices;
    private OnFollowUpFifthQuestionViewsClickListener onFollowUpFifthQuestionViewsClickListener;
    private final Answer answer;
    private String other;

    public Follow_Up_Symptoms_5th_Question_ViewHolder(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_fifth_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_5th_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        choices = itemView.findViewById ( R.id.viewSelectionChoices );
        choices.setOnSingleItemSelectedListener ( this );
        string_choices = itemView.getContext ().getResources ().getStringArray ( R.array.FUS_5th_choices_FUR_eighth_choices );
        for (int i = 0; i < choices.getNumberOfViews (); i++) {
            choices.setTextToButtons ( string_choices[i], i );
        }
        answer = new Answer ();
    }

    public void setOnFollowUpFifthQuestionViewsClickListener(OnFollowUpFifthQuestionViewsClickListener onFollowUpFifthQuestionViewsClickListener) {
        this.onFollowUpFifthQuestionViewsClickListener = onFollowUpFifthQuestionViewsClickListener;
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "Follow_Up_Symptoms_5th_Question_ViewHolder.onSingleItemSelected" );
        button_next.setEnabled ( true );
        button_next.setBackgroundResource ( R.drawable.enable_next_question );
        switch (i){

            case 0:{
                answer.setChoice ( "a" );
                break;
            }

            case 1:{
                answer.setChoice ( "b" );

                break;
            }

            case 2:{
                answer.setChoice ( "c" );

                break;
            }

            case 3:{
                answer.setChoice ( "d" );

                break;
            }

            case 4:{
                answer.setChoice ( "e" );

                break;
            }

            case 5:{
                answer.setChoice ( "f" );

                break;
            }

            case 6:{
                answer.setChoice ( "g" );
                OtherDialogBuilder dialogBuilder =
                        new OtherDialogBuilder ( itemView.getContext () );
                dialogBuilder.setOnOtherDialogListener ( this );
                dialogBuilder.show ();
                break;
            }
        }
    }

    @Override
    public void onOtherDialogDone(String otherText) {
        System.out.println ( "Follow_Up_Symptoms_5th_Question_ViewHolder.onOtherDialogDone" );
        other = otherText;
        answer.setOther ( other );
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "Follow_Up_Symptoms_5th_Question_ViewHolder.Follow_Up_Symptoms_5th_Question_ViewHolder" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFollowUpFifthQuestionViewsClickListener.onNextClick (answer);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "Follow_Up_Symptoms_5th_Question_ViewHolder.Follow_Up_Symptoms_5th_Question_ViewHolder" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFollowUpFifthQuestionViewsClickListener.onSkipClick ();
        }
    }

    public interface OnFollowUpFifthQuestionViewsClickListener{
        void onNextClick(Answer answer);
        void onSkipClick();
    }
}
