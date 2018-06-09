package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_6;

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

public class NS_6th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection answerChoices, choice_numbers;
    private String[] string_choices;
    private Answer answer;
    private OnSixthNSVHListener onSixthNSVHListener;

    public NS_6th_VH(View itemView,Answer answerDB) {
        super ( itemView );
        answer = new Answer ();
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.new_symptom_sixth_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_sixth_question );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        string_choices= itemView.getContext ().getResources ().getStringArray ( R.array.question_6_7_choices );
        choice_numbers = itemView.findViewById ( R.id.viewSelectionChoiceNumbers );
        for (int i = 0; i < choice_numbers.getNumberOfViews (); i++) {
            choice_numbers.setTextToButtons ( String.valueOf ( i + 1 ), i );
        }
        answerChoices = itemView.findViewById ( R.id.viewSelectionChoices );
        for (int i = 0; i < answerChoices.getNumberOfViews (); i++) {
            answerChoices.setTextToButtons ( string_choices[i], i );
        }
        answerChoices.setOnSingleItemSelectedListener ( this );

        if (answerDB != null) {
            answerChoices.previewOfDBResult (true,true,answerDB.getRate() - 1);
        }
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnSixthNSVHListener(OnSixthNSVHListener onSixthNSVHListener) {
        this.onSixthNSVHListener = onSixthNSVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "NS_6th_VH.onSingleItemSelected" );
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
            System.out.println ( "NS_6th_VH.NS_6th_VH" );
            System.out.println ( "OnNextClickListener.onClick" );
            onSixthNSVHListener.onNextClicked ( answer );
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_6th_VH.NS_6th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onSixthNSVHListener.onSkipClicked ();
        }
    }

    public interface OnSixthNSVHListener{
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }

}
