package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8;

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

public class FUpS_8th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private Answer answer;
    private ViewSelection answerChoices, choice_numbers;
    private String[] choices_strings;

    private OnFUpSEighthVHListener onFUpSEighthVHListener;


    public FUpS_8th_VH(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_eighth_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_8th_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        choice_numbers = itemView.findViewById ( R.id.viewSelectionChoiceNumbers );

        //I know this is bad! Do not blame me please:D
        String[] strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        choice_numbers.setDataSet ( strings );

        choices_strings = itemView.getContext ().getResources ()
                .getStringArray ( R.array.FUS_7th_8th_choices_FUR_10th_11th_choices );
        answerChoices = itemView.findViewById ( R.id.viewSelectionChoices );
        answerChoices.setDataSet ( choices_strings );
        answerChoices.setOnSingleItemSelectedListener ( this );
        answer = new Answer ();
    }

    public void onUpdateUI(Answer answerDB) {
        answerChoices.updateViewSelectionUI(answerDB.getRate()-1);
        answer=answerDB;
        button_next.setEnabled ( true );
        button_next.setBackgroundResource ( R.drawable.enable_next_question );
    }

    public void setOnFUpSEighthVHListener(OnFUpSEighthVHListener onFUpSEighthVHListener) {
        this.onFUpSEighthVHListener = onFUpSEighthVHListener;
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FUpS_8th_VH.onSingleItemSelected" );
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
            System.out.println ( "FUpS_8th_VH.FUpS_8th_VH" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFUpSEighthVHListener.onNextClick (answer);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_8th_VH.FUpS_8th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFUpSEighthVHListener.onSkipClick ();
        }
    }

    public interface OnFUpSEighthVHListener {
        void onNextClick(Answer answer);
        void onSkipClick();
    }
}
