package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_18;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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

public class FUpR_18th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener, ViewSelection.OnClearStateListener {

    private final Button button_next;
    private final Button button_skip;
    private final View question_view;
    private final ProgressBar progressBar;
    private final TextView question;
    private final ViewSelection first;
    private final AppCompatEditText answer_input;
    private final Answer answer = new Answer();
    private OnFUREighteenVHListener onFUREighteenVHListener;

    public FUpR_18th_VH(View itemView,Answer answerDB) {
        super ( itemView );
        progressBar =  itemView.findViewById( R.id.progress_bar);
        question_view = itemView.findViewById(R.id.follow_up_results_eighteen_question);
        question =  question_view.findViewById(R.id.txtQuestion);
        question.setText(itemView.getContext().getString(R.string.FUPR_eighteen_question));
        button_next =  itemView.findViewById(R.id.btnNextQuestion);
        button_next.setOnClickListener(new OnNextButtonClickListener());
        button_next.setEnabled(false);
        button_skip =  itemView.findViewById(R.id.btnSkipQuestion);
        button_skip.setOnClickListener(new OnSkipClickListener());
        first = itemView.findViewById ( R.id.viewSelectionChoice );
        first.setOnSingleItemSelectedListener ( this );
        first.setOnClearStateListener(this);
        first.setTextToButtons(itemView.getContext().getResources().getString(R.string.FUpR_18th_choice), 0);
        answer_input = itemView.findViewById ( R.id.follow_up_result_eighteen_answer );
        answer_input.addTextChangedListener ( new OnAnswerInputTextWatcher() );
        answer_input.setOnFocusChangeListener ( new OnAnswerFocusChangeListener() );

        if (answerDB!=null){
           if (answerDB.getChoice().equals("a")){
               answer_input.setText(answerDB.getReply());
           }
           else if(answerDB.getChoice().equals("b")){
               first.previewOfDBResult(true,true,0);
           }
        }
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnFUREighteenVHListener(OnFUREighteenVHListener onFUREighteenVHListener) {
        this.onFUREighteenVHListener = onFUREighteenVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println("FUpR_18th_VH.onSingleItemSelected");
        if( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else if ( i == 0 ){
            answer.setChoice ( "b" );
            answer_input.setText ( "" );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println("FUpR_18th_VH.onClearState");
        button_next.setEnabled ( false );
        button_next.setBackgroundResource ( R.drawable.disable_next_question );
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("FUpR_18th_VH.FUpR_18th_VH");
            System.out.println("OnNextButtonClickListener.onClick");
            onFUREighteenVHListener.onNextClicked(answer);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("FUpR_18th_VH.FUpR_18th_VH");
            System.out.println("OnSkipClickListener.onClick");
            onFUREighteenVHListener.onSkipClicked();
        }
    }

    private class OnAnswerInputTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable){
            if( editable.length () > 0 ){
                answer.setChoice ( "a" );
                answer.setReply ( editable.toString () );
                button_next.setEnabled ( true );
                button_next.setBackgroundResource ( R.drawable.enable_next_question );
            }else{
                button_next.setEnabled ( false );
                button_next.setBackgroundResource ( R.drawable.disable_next_question );
            }
        }
    }

    private final class OnAnswerFocusChangeListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View view, boolean b) {
            System.out.println ( "OnAnswerFocusChangeListener.onFocusChange" );
            if( b ){
                first.setClear ();
            }
        }
    }

    public interface OnFUREighteenVHListener {
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }
}
