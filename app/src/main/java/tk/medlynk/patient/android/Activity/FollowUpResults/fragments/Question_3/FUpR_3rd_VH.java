package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_3;

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

public class FUpR_3rd_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private final String[] string_choices;
    private final Button button_next;
    private final Button button_skip;
    private final View question_view;
    private final ProgressBar progressBar;
    private final TextView third_question;
    private final ViewSelection first;
    private Answer answer;
    private OnFURThirdVHListener onFURThirdVHListener;

    public FUpR_3rd_VH(View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar);
        question_view = itemView.findViewById(R.id.follow_up_results_third_question);
        third_question = question_view.findViewById(R.id.txtQuestion);
        third_question.setText(itemView.getContext().getString(R.string.FUPR_third_question));
        button_next = itemView.findViewById(R.id.btnNextQuestion);
        button_next.setOnClickListener(new OnNextButtonClickListener());
        button_next.setEnabled(false);
        button_skip = itemView.findViewById(R.id.btnSkipQuestion);
        button_skip.setOnClickListener(new OnSkipClickListener());
        first = itemView.findViewById(R.id.viewSelectionChoice);
        first.setOnSingleItemSelectedListener(this);
        string_choices = itemView.getContext().getResources().getStringArray(R.array.yes_no);
        first.setDataSet(string_choices);

        answer=new Answer();
    }

    public void onUpdateUI(Answer answerDB) {
        if (answerDB.getChoice().equals("a")){
            first.updateViewSelectionUI(0);
        }
        else if(answerDB.getChoice().equals("b")){
            first.updateViewSelectionUI(1);
        }
        answer=answerDB;
        button_next.setEnabled(true);
        button_next.setBackgroundResource(R.drawable.enable_next_question);
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility(status);
    }

    public void setOnFURThirdVHListener(OnFURThirdVHListener onFURThirdVHListener) {
        this.onFURThirdVHListener = onFURThirdVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FUpR_3rd_VH.onSingleItemSelected" );
        if( i == -1 ){
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
        }else if ( i == 0 ){
            answer.setChoice("a");
            button_next.setEnabled(true);
            button_next.setBackgroundResource(R.drawable.enable_next_question);
        }else if ( i == 1 ){
            answer.setChoice("b");
            button_next.setEnabled(true);
            button_next.setBackgroundResource(R.drawable.enable_next_question);
        }

    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("FUpR_3rd_VH.FUpR_3rd_VH");
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFURThirdVHListener.onNextClicked(answer);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "OnSkipClickListener.onClick" );
            onFURThirdVHListener.onSkipClicked();
        }
    }
    public interface OnFURThirdVHListener {
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }
}
