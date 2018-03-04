package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2;

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

public class New_Symptom_2nd_question_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ViewSelection.OnSingleItemSelectedListener {

    private View question_view;
    private TextView second_question;
    private Button button_next, button_skip;
    private AppCompatEditText second_answer;
    private ProgressBar progressBar;
    private ViewSelection choice;
    private int selected_choice = -1;
    private On2QuestionViewsClickListener listener;
    private Answer answer;
    private int second_answer_selected = -1;

    public New_Symptom_2nd_question_ViewHolder(View view) {
        super ( view );
        progressBar = view.findViewById ( R.id.progress_bar );
        question_view = view.findViewById ( R.id.new_symptom_second_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        button_next = view.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( this );
        button_next.setClickable ( false );
        button_skip = view.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( this );
        choice = view.findViewById ( R.id.viewSelectionChoice );
        choice.setOnSingleItemSelectedListener ( this );
        choice.setTextToButtons ( view.getContext ().getResources ().getString ( R.string.i_do_not_know ), 0 );
        second_question.setText ( R.string.new_symptom_second_question );
        second_answer = view.findViewById ( R.id.new_symptom_second_answer );
        second_answer.addTextChangedListener ( new SecondAnswerTextWatcher() );
    }

    public void setListener(On2QuestionViewsClickListener listener) {
        this.listener = listener;
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onClick(View view) {
        System.out.println ( "New_Symptom_2nd_question_ViewHolder.onClick" );

        switch (view.getId ()){
            case R.id.btnNextQuestion:{
                if( second_answer_selected == 0 || selected_choice != -1  )
                    listener.onNextClicked (answer);
                break;
            }
            case R.id.btnSkipQuestion:{
                listener.onSkipClicked ();
                break;
            }
        }
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "New_Symptom_2nd_question_ViewHolder.onSingleItemSelected" );
        button_next.setClickable ( true );
        button_next.setBackgroundResource ( R.drawable.enable_next_question );
        second_answer.setText ( "" );
        second_answer.clearFocus ();
        choice.setFocusableInTouchMode ( true );
        selected_choice = i;
        answer = new Answer ();
        answer.setChoice ( "b" );
    }

    public interface On2QuestionViewsClickListener{
        void onNextClicked(Answer answer);
        void onSkipClicked();

    }

    private class SecondAnswerTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(  editable.length () == 0){
                button_next.setBackgroundResource ( R.drawable.disable_next_question );
                button_next.setClickable ( false );
                second_answer_selected = -1;
            }else{
                second_answer_selected = 0;
                button_next.setBackgroundResource ( R.drawable.enable_next_question );
                button_next.setClickable ( true );
                answer.setReply ( second_answer.getText ().toString () );
                answer.setChoice ( "a" );
            }
        }
    }
}
