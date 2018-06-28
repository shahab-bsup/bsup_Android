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

public class FUpS_4th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private final String[] string_choices;
    private final ViewSelection choice_numbers;
    private final ViewSelection choices;
    private OnFUpSFourthVHListener onFUpSFourthVHListener;
    private final Answer answer;

    public FUpS_4th_VH(View itemView,Answer answerDB) {
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

        //I know this is bad! Do not blame me please:D
        String[] strings = {"1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10"};
        choice_numbers.setDataSet ( strings );

        choices = itemView.findViewById ( R.id.viewSelectionChoices );
        choices.setDataSet ( string_choices );
        choices.setOnSingleItemSelectedListener ( this );
        answer = new Answer ();

        if (answerDB!=null){
            choices.updateViewSelectionUI(answerDB.getRate()-1);
        }
    }


    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnFUpSFourthVHListener(OnFUpSFourthVHListener onFUpSFourthVHListener) {
        this.onFUpSFourthVHListener = onFUpSFourthVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FUpS_4th_VH.onSingleItemSelected" );
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
            System.out.println ( "FUpS_4th_VH.FUpS_4th_VH" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFUpSFourthVHListener.onNextClick (answer);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_4th_VH.FUpS_4th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFUpSFourthVHListener.onSkipClick ();
        }
    }

    public interface OnFUpSFourthVHListener {
        void onNextClick(Answer answer);
        void onSkipClick();
    }

}
