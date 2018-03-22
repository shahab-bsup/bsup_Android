package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_8;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/22/2018.
 */

public class FollowUpResults_8th_Question_ViewHolder extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView eighth_question;
    private final Button button_next;
    private final Button button_skip;
    private final ViewSelection choices;
    private final String[] string_choices;
    private final Answer answer;
    private String other;
    private OnFUREighthViewHolderListener onFUREighthViewHolderListener;

    public FollowUpResults_8th_Question_ViewHolder(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_fifth_question );
        eighth_question = question_view.findViewById ( R.id.txtQuestion );
        eighth_question.setText ( itemView.getContext ().getString ( R.string.FUPR_eighth_question ) );
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

    public void setOnFUREighthViewHolderListener(OnFUREighthViewHolderListener onFUREighthViewHolderListener) {
        this.onFUREighthViewHolderListener = onFUREighthViewHolderListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FollowUpResults_8th_Question_ViewHolder.onSingleItemSelected" );

    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FollowUpResults_8th_Question_ViewHolder" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFUREighthViewHolderListener.onNextClicked ();
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FollowUpResults_8th_Question_ViewHolder" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFUREighthViewHolderListener.onSkipClicked ();
        }
    }

    public interface OnFUREighthViewHolderListener{
        void onNextClicked();
        void onSkipClicked();
    }

}
