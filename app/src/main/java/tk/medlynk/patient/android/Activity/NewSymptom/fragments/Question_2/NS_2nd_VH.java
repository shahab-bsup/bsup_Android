package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2;


import android.support.annotation.Nullable;
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
 * Created by Shahab on 2/23/2018.
 */

public class NS_2nd_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener, ViewSelection.OnClearStateListener {

    private View question_view;
    private TextView second_question;
    private Button button_next, button_skip;
    private AppCompatEditText second_answer;
    private ProgressBar progressBar;
    private ViewSelection choice;
    private OnSecondNSVHListener onSecondNSVHListener;
    private Answer answer;


    public NS_2nd_VH(View itemView,Answer answerDB) {
        super ( itemView );

        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.new_symptom_second_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        choice = itemView.findViewById ( R.id.viewSelectionChoice );
        choice.setOnSingleItemSelectedListener ( this );
        choice.setOnClearStateListener ( this );
        choice.setTextToButtons ( itemView.getContext ().getResources ().getString ( R.string.i_do_not_know ), 0 );
        second_question.setText ( R.string.new_symptom_second_question );
        second_answer = itemView.findViewById ( R.id.new_symptom_second_answer );
        second_answer.addTextChangedListener ( new AnswerWatcher() );
        second_answer.setOnFocusChangeListener ( new AnswerFocusChangeListener() );
        answer = new Answer ();

        if (answerDB!=null)
        {
            if ( answerDB.getChoice ().equals ( "b" )){
                choice.preViewOfDBResult(true,true,0);
            }
            else if(answerDB.getChoice ().equals ( "a" )){
                second_answer.setText(answerDB.getReply());
            }
        }
    }

    public void setProgressBarVisibilityStatus(int status){
        this.progressBar.setVisibility ( status );
    }

    public void setOnSecondNSVHListener(OnSecondNSVHListener onSecondNSVHListener) {
        this.onSecondNSVHListener = onSecondNSVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "NS_2nd_VH.onSingleItemSelected" );
        second_answer.clearFocus ();
        if( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else if ( i == 0 ){
            answer = new Answer ();
            answer.setChoice ( "b" );
            second_answer.setText ( "" );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println ( "NS_2nd_VH.onClearState" );
        answer = new Answer ();
        button_next.setEnabled ( false );
        button_next.setBackgroundResource ( R.drawable.disable_next_question );
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_2nd_VH" );
            System.out.println ( "OnNextClickListener.onClick" );
            onSecondNSVHListener.onNextClicked (answer);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_2nd_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onSecondNSVHListener.onSkipClicked ();
        }
    }

    public interface OnSecondNSVHListener{
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }

    private class AnswerWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
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

    private class AnswerFocusChangeListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View view, boolean isFocused) {
            System.out.println ( "AnswerFocusChangeListener.onFocusChange" );
            if( isFocused ){
                choice.setClear ();
                System.out.println ( "view = [" + view + "], isFocused = [" + isFocused + "]" );
            }else{
                System.out.println ( "view = [" + view + "], isFocused = [" + isFocused + "]" );
            }
        }
    }
}