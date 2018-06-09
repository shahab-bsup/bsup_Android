package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_5;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 2/23/2018.
 */

public class NS_5th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection choices;
    private Answer answer;
    private OnFifthNSVHListener onFifthNSVHListener;

    public NS_5th_VH(View itemView,Answer answerDB) {
        super ( itemView );
        answer = new Answer ();
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.new_symptom_fifth_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_fifth_question );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        choices = itemView.findViewById ( R.id.new_symptom_fifth_answers );
        choices.setOnSingleItemSelectedListener ( this );
        for (int i = 0; i < choices.getNumberOfViews (); i++) {
            choices.setTextToButtons ( String.valueOf ( i + 1 ), i );
        }

        if (answerDB != null) {
            choices.previewOfDBResult (true,true,answerDB.getRate() - 1);
        }
    }

    public void setOnFifthNSVHListener(OnFifthNSVHListener onFifthNSVHListener) {
        this.onFifthNSVHListener = onFifthNSVHListener;
    }

    public void setProgressBarVisibilityStatus(int status){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "NS_5th_VH.onSingleItemSelected" );
        if( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else{
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
            answer.setRate ( ++i );
        }
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_5th_VH.NS_5th_VH" );
            System.out.println ( "OnNextClickListener.onClick" );
            onFifthNSVHListener.onNextClicked ( answer );
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_5th_VH.NS_5th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFifthNSVHListener.onSkipClicked ();
        }
    }

    public interface OnFifthNSVHListener{
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }

}
