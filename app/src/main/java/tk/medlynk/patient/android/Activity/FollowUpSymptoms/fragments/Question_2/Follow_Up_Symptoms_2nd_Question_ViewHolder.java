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

import tk.medlynk.patient.android.Essentials.Utils;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/4/2018.
 */

public class Follow_Up_Symptoms_2nd_Question_ViewHolder extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private final ViewSelection none;
    private Answer answer;
    private final AppCompatEditText answer_input;
    private int selected_choice = -1;
    private OnFollowUpSecondQuestionViewsClickListener onFollowUpSecondQuestionViewsClickListener;

    public Follow_Up_Symptoms_2nd_Question_ViewHolder(View itemView) {
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
    }

    public void setOnFollowUpSecondQuestionViewsClickListener(OnFollowUpSecondQuestionViewsClickListener onFollowUpSecondQuestionViewsClickListener) {
        this.onFollowUpSecondQuestionViewsClickListener = onFollowUpSecondQuestionViewsClickListener;
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "Follow_Up_Symptoms_2nd_Question_ViewHolder.onSingleItemSelected" );
        none.setFocusableInTouchMode ( true );
        answer_input.clearFocus ();
        Utils.hideSoftKeyBoard ( itemView );
        if( selected_choice == 1 ) {
            selected_choice = -2;
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else{
            selected_choice = 1;
            answer.setChoice ( "b" );
            answer_input.clearFocus ();
            answer_input.setText ( "" );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "Follow_Up_Symptoms_2nd_Question_ViewHolder.Follow_Up_Symptoms_2nd_Question_ViewHolder" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFollowUpSecondQuestionViewsClickListener.onNextClick (answer);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "Follow_Up_Symptoms_2nd_Question_ViewHolder.Follow_Up_Symptoms_2nd_Question_ViewHolder" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFollowUpSecondQuestionViewsClickListener.onSkipClick ();
        }
    }

    public interface OnFollowUpSecondQuestionViewsClickListener{
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
            if( editable.length () == 0 && (selected_choice == -1 || selected_choice == -2)){
                button_next.setEnabled ( false );
                button_next.setBackgroundResource ( R.drawable.disable_next_question );
                answer.setReply ( null );
                answer.setChoice ( null );
            } else if( selected_choice == 1 ){
              //Do nothing!
            } else{
                selected_choice = -2;
                answer.setChoice ( "a" );
                answer.setReply ( editable.toString () );
                button_next.setEnabled ( true );
                button_next.setBackgroundResource ( R.drawable.enable_next_question );
            }
        }
    }

    private final class OnAnswerFocusChangeListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View view, boolean b) {
            System.out.println ( "OnAnswerFocusChangeListener.onFocusChange" );
            System.out.println ( "b = [" + b + "]" );
            if( b ){
                selected_choice = -1;
            }
        }
    }
}
