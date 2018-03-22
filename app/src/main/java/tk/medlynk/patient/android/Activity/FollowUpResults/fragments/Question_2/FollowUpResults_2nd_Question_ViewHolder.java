package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_1.FollowUpResults_1st_Question_ViewHolder;

/**
 * Created by Shahab on 3/22/2018.
 */

public class FollowUpResults_2nd_Question_ViewHolder extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private final String[] string_choices;
    private final Button button_next;
    private final Button button_skip;
    private final View question_view;
    private final ProgressBar progressBar;
    private final TextView second_question;
    private final ViewSelection first;
    private OnFURSecondViewHolderListener onFURSecondViewHolderListener;

    public FollowUpResults_2nd_Question_ViewHolder(View itemView) {
        super ( itemView );
        progressBar =  itemView.findViewById( R.id.progress_bar);
        this.question_view = itemView.findViewById(R.id.follow_up_results_second_question);
        this.second_question =  question_view.findViewById(R.id.txtQuestion);
        this.second_question.setText(itemView.getContext().getString(R.string.FUPR_second_question));
        this.button_next =  itemView.findViewById(R.id.btnNextQuestion);
        this.button_next.setOnClickListener(new OnNextButtonClickListener ());
        this.button_next.setEnabled(false);
        this.button_skip =  itemView.findViewById(R.id.btnSkipQuestion);
        this.button_skip.setOnClickListener(new OnSkipClickListener ());
        first = itemView.findViewById ( R.id.viewSelectionChoice );
        first.setOnSingleItemSelectedListener ( this );
        string_choices = itemView.getContext ().getResources ().getStringArray ( R.array.yes_no );
        first.setTextToButtons ( string_choices[0], 0 );
        first.setTextToButtons ( string_choices[1], 1 );
    }

    public void setOnFURSecondViewHolderListener(OnFURSecondViewHolderListener onFURSecondViewHolderListener) {
        this.onFURSecondViewHolderListener = onFURSecondViewHolderListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FollowUpResults_2nd_Question_ViewHolder.onSingleItemSelected" );

    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FollowUpResults_2nd_Question_ViewHolder" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFURSecondViewHolderListener.onNextClicked ();
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FollowUpResults_2nd_Question_ViewHolder" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFURSecondViewHolderListener.onSkipClicked ();
        }
    }

    public interface OnFURSecondViewHolderListener{
        void onNextClicked();
        void onSkipClicked();
    }

}