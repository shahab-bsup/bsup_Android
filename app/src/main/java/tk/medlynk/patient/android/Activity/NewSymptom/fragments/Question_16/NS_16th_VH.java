package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_16;

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
 * Created by Shahab on 2/24/2018.
 */

public class NS_16th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener, ViewSelection.OnClearStateListener {

    private ProgressBar progressBar;
    private AppCompatEditText answer_input;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection choice;
    private OnSixteenNSVHListener onSixteenNSVHListener;
    private Answer answer;

    public NS_16th_VH(View itemView,Answer answerDB) {
        super ( itemView );
        answer = new Answer ();
        progressBar = itemView.findViewById ( R.id.progress_bar );
        answer_input = itemView.findViewById ( R.id.new_symptom_sixteen_answer );
        answer_input.addTextChangedListener ( new OnAnswerTextWatcher() );
        answer_input.setOnFocusChangeListener(new AnswerFocusChangeListener());
        question_view = itemView.findViewById ( R.id.new_symptom_sixteen_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_sixteen_question );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        choice = itemView.findViewById ( R.id.viewSelectionChoice );
        choice.setTextToButtons ( itemView.getContext ().
                getResources ().
                getString ( R.string.i_do_not_know ), 0 );
        choice.setOnSingleItemSelectedListener ( this );
        choice.setOnClearStateListener(this);

        if (answerDB != null) {
            if (answerDB.getChoice ().equals ( "b" )) {
                choice.previewOfDBResult ( true, true, 0 );
            } else if (answerDB.getChoice ().equals ( "a" )) {
                answer_input.setText ( answerDB.getReply () );
            }
        }
    }

    public void setOnSixteenNSVHListener(OnSixteenNSVHListener onSixteenNSVHListener) {
        this.onSixteenNSVHListener = onSixteenNSVHListener;
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "NS_16th_VH.onSingleItemSelected" );
        if( i == -1 ){
            answer = new Answer();
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
        System.out.println("NS_16th_VH.onClearState");
        answer = new Answer();
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_16th_VH.NS_16th_VH" );
            System.out.println ( "OnNextClickListener.onClick" );
            onSixteenNSVHListener.onNextClicked ( answer );
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_16th_VH.NS_16th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onSixteenNSVHListener.onSkipClicked ();
        }
    }

    public interface OnSixteenNSVHListener {
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }

    private class OnAnswerTextWatcher implements TextWatcher {
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
                button_next.setEnabled ( false );
                button_next.setBackgroundResource ( R.drawable.disable_next_question );
                System.out.println ( "view = [" + view + "], isFocused = [" + isFocused + "]" );
            }else{
                System.out.println ( "view = [" + view + "], isFocused = [" + isFocused + "]" );
            }
        }
    }

}
