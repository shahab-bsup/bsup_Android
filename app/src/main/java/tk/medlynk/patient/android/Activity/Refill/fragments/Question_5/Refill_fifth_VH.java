package tk.medlynk.patient.android.Activity.Refill.fragments.Question_5;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.medlynk.shahab.myviewselection.ViewSelection;
import com.medlynk.shahab.myviewselection.ViewSelection.OnSingleItemSelectedListener;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Model.Answer;

public class Refill_fifth_VH extends ViewHolder implements OnSingleItemSelectedListener {
    private final ProgressBar progressBar;
    private ViewSelection first;
    private Button button_next, button_skip;
    private OnRefillFifthVHListener onRefillFifthVHListener;
    private TextView question ;
    private View question_view;
    private String[] string_choices;
    private Answer answer;

    public Refill_fifth_VH(View itemView) {
        super(itemView);
        progressBar =  itemView.findViewById( R.id.progress_bar);
        this.question_view = itemView.findViewById(R.id.refill_fifth_question);
        question = ( this.question_view.findViewById(R.id.txtQuestion));
        this.question.setText(R.string.refill_fifth_question);
        this.button_next = (Button) itemView.findViewById(R.id.btnNextQuestion);
        button_next.setEnabled ( false );
        this.button_next.setOnClickListener(new OnNextClickListener());
        this.button_skip = (Button) itemView.findViewById(R.id.btnSkipQuestion);
        this.button_skip.setOnClickListener(new OnSkipClickListener());
        this.first = (ViewSelection) itemView.findViewById(R.id.viewSelectionChoices);
        this.first.setOnSingleItemSelectedListener(this);
        this.string_choices = itemView
                .getContext()
                .getResources().getStringArray(R.array.yes_no);
        first.setDataSet ( string_choices );

        answer = new Answer();
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

    private class OnNextClickListener implements OnClickListener {
        public void onClick(View view) {
            System.out.println("Refill_fifth_VH");
            System.out.println("OnNextClickListener.onClick");
            Refill_fifth_VH.this.onRefillFifthVHListener.onNextClicked(answer);
        }

    }
    public interface OnRefillFifthVHListener {
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }
    private class OnSkipClickListener implements OnClickListener {
        public void onClick(View view) {
            System.out.println("Refill_fifth_VH");
            System.out.println("OnSkipClickListener.onClick");
            Refill_fifth_VH.this.onRefillFifthVHListener.onSkipClicked();
        }

    }

    public void onSingleItemSelected(View view, int i) {
        System.out.println("Refill_fifth_VH.onSingleItemSelected");
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

    public void setOnRefillFifthVHListener(OnRefillFifthVHListener onRefillFifthVHListener) {
        this.onRefillFifthVHListener = onRefillFifthVHListener;
    }
}
