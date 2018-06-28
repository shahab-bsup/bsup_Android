package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_17;

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

public class FUpR_17th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private final String[] string_choices;
    private final Button button_next;
    private final Button button_skip;
    private final View question_view;
    private final ProgressBar progressBar;
    private final TextView third_question;
    private final ViewSelection first;
    private final Answer answer = new Answer();
    private OnFURSeventeenVHListener onFURSeventeenVHListener;


    public FUpR_17th_VH(View itemView,Answer answerDB) {
        super ( itemView );
        progressBar =  itemView.findViewById( R.id.progress_bar);
        question_view = itemView.findViewById(R.id.follow_up_results_seventeen_question);
        third_question =  question_view.findViewById(R.id.txtQuestion);
        third_question.setText(itemView.getContext().getString(R.string.FUPR_seventeen_question));
        button_next =  itemView.findViewById(R.id.btnNextQuestion);
        button_next.setOnClickListener(new OnNextButtonClickListener());
        button_next.setEnabled(false);
        button_skip =  itemView.findViewById(R.id.btnSkipQuestion);
        button_skip.setOnClickListener(new OnSkipClickListener());
        first = itemView.findViewById ( R.id.viewSelectionChoice );
        first.setOnSingleItemSelectedListener ( this );
        string_choices = itemView.getContext ()
                .getResources ()
                .getStringArray ( R.array.FUpR_17th_choices );
        first.setDataSet ( string_choices );

        if (answerDB!=null){
            switch (answerDB.getChoice()){
                case "a":{first.updateViewSelectionUI(0);break;}
                case "b":{first.updateViewSelectionUI(1);break;}
                case "c":{first.updateViewSelectionUI(2);break;}
            }

        }
    }

    public void setOnFURSeventeenVHListener(OnFURSeventeenVHListener onFURSeventeenVHListener) {
        this.onFURSeventeenVHListener = onFURSeventeenVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        if( i == -1 ){
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
        }else {
            setAnswerChoice(i);
        }
    }

    private void setAnswerChoice(int i) {
        switch (i){
            case 0:{
                answer.setChoice("a");

                break;
            }
            case 1:{
                answer.setChoice("b");

                break;
            }
            case 2:{
                answer.setChoice("c");

                break;
            }
        }
        button_next.setEnabled(true);
        button_next.setBackgroundResource(R.drawable.enable_next_question);
    }

    public void setProgressBarVisibilityStatus(int status) {
        this.progressBar.setVisibility(status);
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("FUpR_17th_VH.FUpR_17th_VH");
            System.out.println("OnNextButtonClickListener.onClick");
            onFURSeventeenVHListener.onNextClicked(answer);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("FUpR_17th_VH.FUpR_17th_VH");
            System.out.println("OnSkipClickListener.onClick");
            onFURSeventeenVHListener.onSkipClicked();
        }
    }
    public interface OnFURSeventeenVHListener {
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }
}