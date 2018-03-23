package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_7;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 2/23/2018.
 */

public class NS_7th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection answerChoices, choice_numbers;
    private String[] choices_strings;
    private OnSeventhNSVHListener onSeventhNSVHListener;
    private Answer answer;

    public NS_7th_VH(View itemView) {
        super ( itemView );
        answer = new Answer ();
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.new_symptom_seventh_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_seventh_question );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener () );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        choice_numbers = itemView.findViewById ( R.id.viewSelectionChoiceNumbers );
        for (int i = 0; i < choice_numbers.getNumberOfViews (); i++) {
            choice_numbers.setTextToButtons ( String.valueOf ( i + 1 ), i );
        }
        choices_strings = itemView.getContext ().getResources ().getStringArray ( R.array.question_6_7_choices );
        answerChoices = itemView.findViewById ( R.id.viewSelectionChoices );
        for (int i = 0; i < answerChoices.getNumberOfViews (); i++) {
            answerChoices.setTextToButtons ( choices_strings[i], i );
        }
        answerChoices.setOnSingleItemSelectedListener ( this );
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnSeventhNSVHListener(OnSeventhNSVHListener onSeventhNSVHListener) {
        this.onSeventhNSVHListener = onSeventhNSVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "NS_7th_VH.onSingleItemSelected" );
        if( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else{
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
            answer.setRate ( ++i );
        }
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_7th_VH.NS_7th_VH" );
            System.out.println ( "OnNextClickListener.onClick" );
            onSeventhNSVHListener.onNextClicked ( answer );
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_7th_VH.NS_7th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onSeventhNSVHListener.onSkipClicked ();
        }
    }


    public interface OnSeventhNSVHListener{
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }

}
