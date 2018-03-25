package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_1;

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

public class FollowUpResults_1st_Question_ViewHolder extends RecyclerView.ViewHolder implements ViewSelection.OnMultiItemSelectedListener, ViewSelection.OnClearStateListener, ViewSelection.OnSingleItemSelectedListener {

    private final ViewSelection first, sub_first, second, sub_second, third;
    private final String[] string_choices;
    private final Button button_next;
    private final Button button_skip;
    private final View question_view;
    private final ProgressBar progressBar;
    private final TextView first_question;
    private OnFURFirstViewHolderListener onFURFirstViewHolderListener;

    public FollowUpResults_1st_Question_ViewHolder(View itemView) {
        super ( itemView );
        first = itemView.findViewById ( R.id.viewSelectionFirst );
        first.setOnMultiItemSelectedListener ( this );
        first.setOnClearStateListener ( this );
        sub_first = itemView.findViewById ( R.id.viewSelectionSubFirst );
        sub_first.setOnMultiItemSelectedListener ( this );
        sub_first.setOnClearStateListener ( this );
        second = itemView.findViewById ( R.id.viewSelectionSecond );
        second.setOnSingleItemSelectedListener ( this );
        sub_second = itemView.findViewById ( R.id.viewSelectionSubSecond );
        sub_second.setOnMultiItemSelectedListener ( this );
        third = itemView.findViewById ( R.id.viewSelectionThird );
        third.setOnSingleItemSelectedListener ( this );
        string_choices = itemView.getContext ().getResources ().getStringArray ( R.array.FUR_1th_choices);
        for (int i = 0; i < first.getNumberOfViews (); i++) {
            first.setTextToButtons ( string_choices[i], i );
        }
        for (int i = 0; i < sub_first.getNumberOfViews (); i++) {
            sub_first.setTextToButtons ( string_choices[i+3], i );
        }
        second.setTextToButtons ( string_choices[10], 0 );
        for (int i = 0; i < sub_second.getNumberOfViews (); i++) {
            sub_second.setTextToButtons ( string_choices[i + 11], i );
        }
        third.setTextToButtons ( string_choices[14], 0 );
        this.progressBar =  itemView.findViewById(R.id.progress_bar);
        this.question_view = itemView.findViewById(R.id.follow_up_results_first_question);
        this.first_question =  this.question_view.findViewById(R.id.txtQuestion);
        this.first_question.setText(itemView.getContext().getString(R.string.FUPR_first_question));
        this.button_next =  itemView.findViewById(R.id.btnNextQuestion);
        this.button_next.setOnClickListener(new OnNextButtonClickListener ());
        this.button_next.setEnabled(false);
        this.button_skip =  itemView.findViewById(R.id.btnSkipQuestion);
        this.button_skip.setOnClickListener(new OnSkipClickListener ());
    }

    public void setOnFURFirstViewHolderListener(OnFURFirstViewHolderListener onFURFirstViewHolderListener) {
        this.onFURFirstViewHolderListener = onFURFirstViewHolderListener;
    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println ( "FollowUpResults_1st_Question_ViewHolder.onMultiItemSelected" );

    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {
        System.out.println ( "FollowUpResults_1st_Question_ViewHolder.onMultiItemDeselected" );

    }

    @Override
    public void onClearState(View view) {
        System.out.println ( "FollowUpResults_1st_Question_ViewHolder.onClearState" );

    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FollowUpResults_1st_Question_ViewHolder.onSingleItemSelected" );

    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FollowUpResults_1st_Question_ViewHolder" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFURFirstViewHolderListener.onNextClicked ();
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FollowUpResults_1st_Question_ViewHolder" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFURFirstViewHolderListener.onSkipClicked ();
        }
    }

    public interface OnFURFirstViewHolderListener{
        void onNextClicked();
        void onSkipClicked();
    }
}
