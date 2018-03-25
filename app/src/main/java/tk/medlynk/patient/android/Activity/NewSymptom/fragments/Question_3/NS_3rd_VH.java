package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_3;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 2/23/2018.
 */

public class NS_3rd_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener, ViewSelection.OnClearStateListener {

    private View question_view, lasting_row;
    private TextView question;
    private Button button_next, button_skip;
    private ViewSelection choices;
    private String[] string_choices;
    private AppCompatSpinner timeSpinner;
    private AppCompatEditText lasting_input;
    private Answer answer;
    private int duration;
    private OnThirdNSVHListener onThirdNSVHListener;

    ProgressBar progressBar;

    public NS_3rd_VH(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.new_symptom_third_question );
        lasting_row = itemView.findViewById ( R.id.new_symptom_third_question_lasting );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_third_question );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_next.setEnabled(false);
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        choices = itemView.findViewById ( R.id.viewSelectionChoices );
        choices.setOnSingleItemSelectedListener ( this );
        choices.setOnClearStateListener ( this );
        string_choices = itemView.getContext ().getResources ().getStringArray ( R.array.question_3_choices );
        for (int i = 0; i < choices.getNumberOfViews (); i++) {
            choices.setTextToButtons ( string_choices[i], i );
        }
        lasting_input = lasting_row.findViewById ( R.id.edtAnswerDuration );
        lasting_input.addTextChangedListener ( new LastingInoutTextWatcher() );
        timeSpinner = lasting_row.findViewById ( R.id.spinnerTime );
        TimeLastingAdapter timeAdapter = new TimeLastingAdapter ( itemView.getContext (), NS_3rd_question.TAG);
        timeSpinner.setAdapter ( timeAdapter );
        timeSpinner.setOnItemSelectedListener ( new OnLastingItemSelectedListener ());

    }

    public void setOnThirdNSVHListener(OnThirdNSVHListener onThirdNSVHListener) {
        this.onThirdNSVHListener = onThirdNSVHListener;
    }

    public void setProgressBarVisibilityStatus(int status){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "NS_3rd_VH.onSingleItemSelected" );
        if( i != -1 ){
            lasting_input.clearFocus ();
            lasting_input.setText ( "" );
            timeSpinner.setSelection ( 0 );
            answer = new Answer ();
            duration = 0;
            if( i == 0 ){
                answer.setChoice ( "e" );
            }else if ( i == 1 ){
                answer.setChoice ( "f" );
            }
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }else if ( i == -1 ){
            lasting_input.clearFocus ();
            lasting_input.setText ( "" );
            timeSpinner.setSelection ( 0 );
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println ( "NS_3rd_VH.onClearState" );

    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_3rd_VH.NS_3rd_VH" );
            System.out.println ( "OnNextClickListener.onClick" );
            if( !answer.getChoice ().equals ( "e" ) && !answer.getChoice ().equals ( "f" ) ){
                if( duration == 0 ){
                    lasting_input.setError("Specify the duration!");
                }else{
                    answer.setDuration ( duration );
                    onThirdNSVHListener.onNextClicked ( answer );
                }
            }else{
                onThirdNSVHListener.onNextClicked ( answer );
            }
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_3rd_VH.NS_3rd_VH" );
            System.out.println ( "view = [" + view + "]" );
            onThirdNSVHListener.onSkipClicked ();
        }
    }

    private class LastingInoutTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if( editable.length () != 0 ){
                duration = Integer.parseInt ( lasting_input.getText ().toString () );
                button_next.setEnabled ( true );
                button_next.setBackgroundResource ( R.drawable.enable_next_question );
            }else{
                button_next.setEnabled ( false );
                button_next.setBackgroundResource ( R.drawable.disable_next_question );
            }
        }
    }

    public interface OnThirdNSVHListener{
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }

    private class OnLastingItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            answer = new Answer ();
            choices.setClear ();
            switch (i){
                case 1:{
                    answer.setChoice ( "a" );

                    break;
                }
                case 2:{
                    answer.setChoice ( "b" );

                    break;
                }
                case 3:{
                    answer.setChoice ( "c" );

                    break;
                }
                case 4:{
                    answer.setChoice ( "d" );

                    break;
                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            System.out.println ( "OnLastingItemSelectedListener.onNothingSelected" );
        }
    }
}
