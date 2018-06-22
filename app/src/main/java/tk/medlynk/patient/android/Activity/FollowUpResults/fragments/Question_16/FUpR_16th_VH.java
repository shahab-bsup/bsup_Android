package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_16;

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

public class FUpR_16th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private final String[] string_choices, sub_string_choices;
    private final Button button_next;
    private final Button button_skip;
    private final View question_view, question_view_second;
    private final ProgressBar progressBar;
    private final TextView question, sub_question;
    private final ViewSelection first, second;
    private final LinearLayout second_question_layout;
    private final Answer answer = new Answer();
    private OnFURSixteenVHListener onFURSixteenVHListener;

    public FUpR_16th_VH(View itemView) {
        super ( itemView );
        progressBar =  itemView.findViewById( R.id.progress_bar);
        this.question_view = itemView.findViewById(R.id.follow_up_results_second_question);
        this.question =  question_view.findViewById(R.id.txtQuestion);
        this.question.setText(itemView.getContext().getString(R.string.FUPR_second_question));
        this.button_next =  itemView.findViewById(R.id.btnNextQuestion);
        this.button_next.setOnClickListener(new OnNextButtonClickListener());
        this.button_next.setEnabled(false);
        this.button_skip =  itemView.findViewById(R.id.btnSkipQuestion);
        this.button_skip.setOnClickListener(new OnSkipClickListener());
        first = itemView.findViewById ( R.id.viewSelectionChoice );
        first.setOnSingleItemSelectedListener ( this );
        string_choices = itemView.getContext ()
                .getResources ()
                .getStringArray ( R.array.yes_no );
        first.setDataSet ( string_choices );
        sub_string_choices = itemView.getContext()
                .getResources()
                .getStringArray(R.array.FUpR_16th_question_sub_Choices);
        question_view_second = itemView.findViewById(R.id.follow_up_results_second_sub_question);
        sub_question = question_view_second.findViewById(R.id.txtQuestion);
        sub_question.setText(itemView.getContext().getResources().getString(R.string.FUpR_2nd_sub_question));
        second = itemView.findViewById(R.id.viewSelectionSubChoice);
        second.setOnSingleItemSelectedListener(this);
        second_question_layout = itemView.findViewById(R.id.sub_choice_layout);
        second.setDataSet ( sub_string_choices );
    }


    public void setOnFURSixteenVHListener(OnFURSixteenVHListener onFURSixteenVHListener) {
        this.onFURSixteenVHListener = onFURSixteenVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println("FUpR_16th_VH.onSingleItemSelected");
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
            System.out.println("FUpR_16th_VH.FUpR_16th_VH");
            System.out.println("OnNextButtonClickListener.onClick");
            onFURSixteenVHListener.onNextClicked(answer);

        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("FUpR_16th_VH.FUpR_16th_VH");
            System.out.println("OnSkipClickListener.onClick");
            onFURSixteenVHListener.onSkipClicked();
        }
    }

    public interface OnFURSixteenVHListener {
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }

}
