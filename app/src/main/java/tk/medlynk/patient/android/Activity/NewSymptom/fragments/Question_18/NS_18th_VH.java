package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_18;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 2/24/2018.
 */

public class NS_18th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection choices;
    private String[] string_choices;
    private OnEighteenNSVHListener onEighteenNSVHListener;
    private Answer answer;

    public NS_18th_VH(View itemView,Answer answerDB) {
        super ( itemView );
        answer = new Answer ();
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.new_symptom_eighteen_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_eighteen_question );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_next.setEnabled(false);
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        choices = itemView.findViewById ( R.id.viewSelectionChoices );
        choices.setOnSingleItemSelectedListener ( this );
        string_choices = itemView.getContext ().
                getResources ().
                getStringArray ( R.array.yes_no );
        for (int i = 0; i < choices.getNumberOfViews (); i++) {
            choices.setTextToButtons ( string_choices[i], i );
        }

        if (answerDB != null) {
            if (answerDB.getChoice().equals("a")){
                choices.previewOfDBResult(true,true,0);
            }
            else if(answerDB.getChoice().equals("b")){
                choices.previewOfDBResult(true,true,1);
            }
        }
    }


    public void setOnEighteenNSVHListener(OnEighteenNSVHListener onEighteenNSVHListener) {
        this.onEighteenNSVHListener = onEighteenNSVHListener;
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "NS_18th_VH.onSingleItemSelected" );
        if( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else if ( i == 0 ){
            answer = new Answer ();
            answer.setChoice ( "a" );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }else if( i == 1 ){
            answer = new Answer ();
            answer.setChoice ( "b" );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }


    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_18th_VH.NS_18th_VH" );
            System.out.println ( "OnNextClickListener.onClick" );
            onEighteenNSVHListener.onNextClicked ( answer );
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_18th_VH.NS_18th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onEighteenNSVHListener.onSkipClicked ();
        }
    }


    public interface OnEighteenNSVHListener {
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }
}
