package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_9;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_6.Follow_Up_Symptoms_6th_Question_ViewHolder;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/22/2018.
 */

public class FollowUpResults_9th_Question_ViewHolder extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private ViewSelection choices;
    private Answer answer;
    private OnFURNinthViewHolderListener onFURNinthViewHolderListener;

    public FollowUpResults_9th_Question_ViewHolder(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_results_ninth_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.FUPR_ninth_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        choices = itemView.findViewById ( R.id.viewSelectionChoices );
        choices.setOnSingleItemSelectedListener ( this );
        for (int i = 0; i < choices.getNumberOfViews (); i++) {
            choices.setTextToButtons ( String.valueOf ( i + 1 ), i );
        }
        answer = new Answer ();
    }

    public void setOnFURNinthViewHolderListener(OnFURNinthViewHolderListener onFURNinthViewHolderListener) {
        this.onFURNinthViewHolderListener = onFURNinthViewHolderListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FollowUpResults_9th_Question_ViewHolder.onSingleItemSelected" );
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FollowUpResults_9th_Question_ViewHolder" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFURNinthViewHolderListener.onNextClicked ();
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FollowUpResults_9th_Question_ViewHolder" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFURNinthViewHolderListener.onSkipClicked ();
        }
    }

    public interface OnFURNinthViewHolderListener{
        void onNextClicked();
        void onSkipClicked();
    }
}
