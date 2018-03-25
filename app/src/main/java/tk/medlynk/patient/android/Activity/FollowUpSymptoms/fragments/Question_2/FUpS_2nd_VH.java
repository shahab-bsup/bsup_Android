package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_2;

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
 * Created by Shahab on 3/4/2018.
 */

public class FUpS_2nd_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener, ViewSelection.OnClearStateListener {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private final ViewSelection none;
    private Answer answer;
    private final AppCompatEditText answer_input;
    private OnFUpSSecondVHListener onFUpSSecondVHListener;

    public FUpS_2nd_VH(View itemView) {
        super ( itemView );
        answer = new Answer ();
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_second_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_2nd_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener() );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        answer_input = itemView.findViewById ( R.id.follow_up_symptom_second_answer );
        answer_input.addTextChangedListener ( new OnAnswerInputTextWatcher() );
        answer_input.setOnFocusChangeListener ( new OnAnswerFocusChangeListener() );
        none = itemView.findViewById ( R.id.viewSelectionChoice );
        none.setTextToButtons ( itemView.getResources ().getString ( R.string.none), 0 );
        none.setOnSingleItemSelectedListener ( this );
        none.setOnClearStateListener(this);
    }

    public void setOnFUpSSecondVHListener(OnFUpSSecondVHListener onFUpSSecondVHListener) {
        this.onFUpSSecondVHListener = onFUpSSecondVHListener;
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FUpS_2nd_VH.onSingleItemSelected" );
        answer_input.clearFocus ();
        if( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else if ( i == 0 ){
            answer = new Answer ();
            answer.setChoice ( "b" );
            answer_input.setText ( "" );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println("FUpS_2nd_VH.onClearState");
        answer = new Answer ();
        button_next.setEnabled ( false );
        button_next.setBackgroundResource ( R.drawable.disable_next_question );
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_2nd_VH.FUpS_2nd_VH" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFUpSSecondVHListener.onNextClick (answer);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_2nd_VH.FUpS_2nd_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFUpSSecondVHListener.onSkipClick ();
        }
    }

    public interface OnFUpSSecondVHListener {
        void onNextClick(Answer answer);
        void onSkipClick();
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
                none.setClear ();
            }
        }
    }
}
