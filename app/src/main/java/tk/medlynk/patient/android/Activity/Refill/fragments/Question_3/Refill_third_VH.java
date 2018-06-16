package tk.medlynk.patient.android.Activity.Refill.fragments.Question_3;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.medlynk.shahab.myviewselection.ViewSelection;
import com.medlynk.shahab.myviewselection.ViewSelection.OnClearStateListener;
import com.medlynk.shahab.myviewselection.ViewSelection.OnSingleItemSelectedListener;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Model.Answer;

public class Refill_third_VH extends ViewHolder implements
        OnSingleItemSelectedListener,
        OnClearStateListener {
    private final Button button_next;
    private final Button button_skip;
    private final ViewSelection choice;
    private OnRefillThirdVHListener onRefillThirdVHListener;
    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final String string_choice;
    private final AppCompatEditText answer_input;
    private Answer answer;

    private class OnNextButtonClickListener implements OnClickListener {
        public void onClick(View view) {
            System.out.println("Refill_third_VH");
            System.out.println("OnNextButtonClickListener.onClick");
            Refill_third_VH.this.onRefillThirdVHListener.onNextClicked(answer);
        }
    }

    public interface OnRefillThirdVHListener {
        void onNextClicked(Answer answer);

        void onSkipClicked();
    }

    private class OnSkipClickListener implements OnClickListener {
        public void onClick(View view) {
            System.out.println("Refill_third_VH");
            System.out.println("OnSkipClickListener.onClick");
            Refill_third_VH.this.onRefillThirdVHListener.onSkipClicked();
        }
    }

    public Refill_third_VH(View itemView,Answer answerDB) {
        super(itemView);
        this.progressBar =  itemView.findViewById(R.id.progress_bar);
        this.question_view = itemView.findViewById(R.id.refill_third_question);
        second_question = ((TextView) this.question_view.findViewById(R.id.txtQuestion));
        this.second_question.setText(itemView.getContext().getString(R.string.refill_third_question));
        this.button_next = (Button) itemView.findViewById(R.id.btnNextQuestion);
        this.button_next.setOnClickListener(new OnNextButtonClickListener());
        this.button_next.setEnabled(false);
        this.button_skip = (Button) itemView.findViewById(R.id.btnSkipQuestion);
        this.button_skip.setOnClickListener(new OnSkipClickListener());
        this.choice = (ViewSelection) itemView.findViewById(R.id.viewSelectionChoice);
        this.string_choice = itemView.getContext().getResources().getString(R.string.refill_third_question_choice);
        this.choice.setTextToButtons(this.string_choice, 0);
        this.choice.setOnSingleItemSelectedListener(this);
        this.choice.setOnClearStateListener(this);
        answer_input = itemView.findViewById(R.id.refill_side_effect_input);
        answer_input.addTextChangedListener ( new OnAnswerInputTextWatcher() );
        answer_input.setOnFocusChangeListener ( new OnAnswerFocusChangeListener() );

        if(answerDB!=null){
            if (answerDB.getChoice().equals("a")){
                answer_input.setText(answerDB.getReply());
            }
            else if(answerDB.getChoice().equals("b")){
                choice.previewOfDBResult(true,true,0);
            }
        }
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnRefillThirdVHListener(OnRefillThirdVHListener onRefillThirdVHListener) {
        this.onRefillThirdVHListener = onRefillThirdVHListener;
    }

    public void onSingleItemSelected(View view, int i) {
        System.out.println("Refill_third_VH.onSingleItemSelected");
        answer_input.clearFocus ();
        if( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else if ( i == 0 ){
            answer = new Answer();
            answer.setChoice ( "b" );
            answer_input.setText ( "" );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    public void onClearState(View view) {
        System.out.println("Refill_third_VH.onClearState");
        answer = new Answer ();
        button_next.setEnabled ( false );
        button_next.setBackgroundResource ( R.drawable.disable_next_question );
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
                choice.setClear ();
            }
        }
    }
}
