package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/22/2018.
 */

public class FUpR_2nd_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private final String[] string_choices, sub_string_choices;
    private final Button button_next;
    private final Button button_skip;
    private final View question_view, question_view_second;
    private final ProgressBar progressBar;
    private final TextView second_question, sub_question;
    private final ViewSelection first, second;
    private final LinearLayout second_question_layout;
    private OnFURSecondViewHolderListener onFURSecondViewHolderListener;
    private Answer answer = new Answer();

    public FUpR_2nd_VH(View itemView) {
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
        sub_string_choices = itemView.getContext().getResources().getStringArray(R.array.FUpR_2nd_question_sub_Choices);
        first.setTextToButtons ( string_choices[0], 0 );
        first.setTextToButtons ( string_choices[1], 1 );
        question_view_second = itemView.findViewById(R.id.follow_up_results_second_sub_question);
        sub_question = question_view_second.findViewById(R.id.txtQuestion);
        sub_question.setText(itemView.getContext().getResources().getString(R.string.FUpR_2nd_sub_question));
        second = itemView.findViewById(R.id.viewSelectionSubChoice);
        second.setOnSingleItemSelectedListener(this);
        second_question_layout = itemView.findViewById(R.id.sub_choice_layout);
        for (int i = 0; i < second.getNumberOfViews(); i++) {
            second.setTextToButtons(sub_string_choices[i], i);
        }
    }

    public void setOnFURSecondViewHolderListener(OnFURSecondViewHolderListener onFURSecondViewHolderListener) {
        this.onFURSecondViewHolderListener = onFURSecondViewHolderListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FUpR_2nd_VH.onSingleItemSelected" );
        int id = view.getId();
        switch (id){
            case R.id.viewSelectionChoice:{
                if( i == -1 ){
                    second_question_layout.setVisibility(View.GONE);
                    button_next.setEnabled(false);
                    button_next.setBackgroundResource(R.drawable.disable_next_question);
                } else if ( i == 0 ){
                    answer.setChoice("a");
                    second_question_layout.setVisibility(View.VISIBLE);
                } else if ( i == 1 ){
                    answer.setChoice("b");
                    second_question_layout.setVisibility(View.GONE);
                    button_next.setEnabled(true);
                    button_next.setBackgroundResource(R.drawable.enable_next_question);
                }
                break;
            }
            case R.id.viewSelectionSubChoice:{
                if( i == -1 ){
                    button_next.setEnabled(false);
                    button_next.setBackgroundResource(R.drawable.disable_next_question);
                }else if( i == 0 ){
                    answer.setSubChoice("1");
                    button_next.setEnabled(true);
                    button_next.setBackgroundResource(R.drawable.enable_next_question);
                }else if ( i == 1 ){
                    answer.setSubChoice("2");
                    button_next.setEnabled(true);
                    button_next.setBackgroundResource(R.drawable.enable_next_question);
                }else if ( i == 2 ){
                    answer.setSubChoice("3");
                    button_next.setEnabled(true);
                    button_next.setBackgroundResource(R.drawable.enable_next_question);
                }
                break;
            }
        }
    }

    public void setProgressBarVisibilityStatus(int status) {
        this.progressBar.setVisibility(status);
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpR_2nd_VH" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFURSecondViewHolderListener.onNextClicked (answer);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpR_2nd_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFURSecondViewHolderListener.onSkipClicked ();
        }
    }

    public interface OnFURSecondViewHolderListener{
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }

}