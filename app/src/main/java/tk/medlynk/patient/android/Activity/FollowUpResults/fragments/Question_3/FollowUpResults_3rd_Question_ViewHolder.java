package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 3/22/2018.
 */

public class FollowUpResults_3rd_Question_ViewHolder extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private final String[] string_choices;
    private final Button button_next;
    private final Button button_skip;
    private final View question_view;
    private final ProgressBar progressBar;
    private final TextView third_question;
    private final ViewSelection first;
    private OnFURThirdViewHolderListener onFURThirdViewHolderListener;

    public FollowUpResults_3rd_Question_ViewHolder(View itemView) {
        super ( itemView );
        progressBar =  itemView.findViewById( R.id.progress_bar);
        question_view = itemView.findViewById(R.id.follow_up_results_third_question);
        third_question =  question_view.findViewById(R.id.txtQuestion);
        third_question.setText(itemView.getContext().getString(R.string.FUPR_third_question));
        button_next =  itemView.findViewById(R.id.btnNextQuestion);
        button_next.setOnClickListener(new OnNextButtonClickListener ());
        button_next.setEnabled(false);
        button_skip =  itemView.findViewById(R.id.btnSkipQuestion);
        button_skip.setOnClickListener(new OnSkipClickListener ());
        first = itemView.findViewById ( R.id.viewSelectionChoice );
        first.setOnSingleItemSelectedListener ( this );
        string_choices = itemView.getContext ().getResources ().getStringArray ( R.array.yes_no );
        first.setTextToButtons ( string_choices[0], 0 );
        first.setTextToButtons ( string_choices[1], 1 );
    }

    public void setOnFURThirdViewHolderListener(OnFURThirdViewHolderListener onFURThirdViewHolderListener) {
        this.onFURThirdViewHolderListener = onFURThirdViewHolderListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FollowUpResults_3rd_Question_ViewHolder.onSingleItemSelected" );

    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "OnNextButtonClickListener.onClick" );

        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "OnSkipClickListener.onClick" );

        }
    }
    public interface OnFURThirdViewHolderListener{
        void onNextClicked();
        void onSkipClicked();
    }
}
