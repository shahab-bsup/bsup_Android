package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_5;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_3.FollowUpResults_3rd_Question_ViewHolder;

/**
 * Created by Shahab on 3/22/2018.
 */

public class FollowUpResults_5th_Question_ViewHolder extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private final Button button_next;
    private final Button button_skip;
    private final View question_view;
    private final ProgressBar progressBar;
    private final TextView third_question;
    private final ViewSelection first;
    private final String string_choice;
    private OnFURFifthViewHolderListener onFURFifthViewHolderListener;

    public FollowUpResults_5th_Question_ViewHolder(View itemView) {
        super ( itemView );
        progressBar =  itemView.findViewById( R.id.progress_bar);
        question_view = itemView.findViewById(R.id.follow_up_results_fifth_question);
        third_question =  question_view.findViewById(R.id.txtQuestion);
        third_question.setText(itemView.getContext().getString(R.string.FUPR_fifth_question));
        button_next =  itemView.findViewById(R.id.btnNextQuestion);
        button_next.setOnClickListener(new OnNextButtonClickListener ());
        button_next.setEnabled(false);
        button_skip =  itemView.findViewById(R.id.btnSkipQuestion);
        button_skip.setOnClickListener(new OnSkipClickListener ());
        first = itemView.findViewById ( R.id.viewSelectionChoice );
        first.setOnSingleItemSelectedListener ( this );
        string_choice = itemView.getContext ().getResources ().getString ( R.string.none );
        first.setTextToButtons ( string_choice, 0 );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FollowUpResults_5th_Question_ViewHolder.onSingleItemSelected" );

    }

    public void setOnFURFifthViewHolderListener(OnFURFifthViewHolderListener onFURFifthViewHolderListener) {
        this.onFURFifthViewHolderListener = onFURFifthViewHolderListener;
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FollowUpResults_5th_Question_ViewHolder" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFURFifthViewHolderListener.onNextClicked ();
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FollowUpResults_5th_Question_ViewHolder" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFURFifthViewHolderListener.onSkipClicked ();
        }
    }

    public interface OnFURFifthViewHolderListener{
        void onNextClicked();
        void onSkipClicked();
    }

}
