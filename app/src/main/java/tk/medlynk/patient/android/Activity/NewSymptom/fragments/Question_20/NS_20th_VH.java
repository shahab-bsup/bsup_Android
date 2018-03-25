package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_20;

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

import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_3.TimeLastingAdapter;
import tk.medlynk.patient.android.Essentials.OtherDialogBuilder;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/2/2018.
 */

public class NS_20th_VH extends RecyclerView.ViewHolder implements
        ViewSelection.OnSingleItemSelectedListener,
        ViewSelection.OnClearStateListener, OtherDialogBuilder.OnOtherDialogListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private String[] string_choices;
    private OnTwentyNSVHListener onTwentyNSVHListener;
    private ViewSelection viewSelectionBeforeLastsPart, other;
    private AppCompatEditText lastsValue;
    private AppCompatSpinner lastsSpinner;
    private Answer answer;
    private int duration = 0;

    public NS_20th_VH(View view) {
        super ( view );
        answer = new Answer();
        progressBar = view.findViewById ( R.id.progress_bar );
        question_view = view.findViewById ( R.id.new_symptom_twenty_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_twelve_question );
        button_next = view.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_next.setEnabled(false);
        button_skip = view.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        lastsSpinner = view.findViewById ( R.id.spinnerLastsID );
        TimeLastingAdapter timeLastingAdapter = new TimeLastingAdapter(itemView.getContext(), NS_20th_question.TAG);
        lastsSpinner.setAdapter(timeLastingAdapter);
        lastsSpinner.setOnItemSelectedListener ( new LastIDItemSelectedListener() );
        viewSelectionBeforeLastsPart = view.findViewById ( R.id.viewSelectionChoicesBeforeLasts );
        viewSelectionBeforeLastsPart.setOnSingleItemSelectedListener ( this );
        viewSelectionBeforeLastsPart.setOnClearStateListener ( this );
        other = view.findViewById ( R.id.viewSelectionChoicesOther );
        lastsValue = view.findViewById ( R.id.edtLastsValue );
        lastsValue.addTextChangedListener(new LastingInoutTextWatcher());
        lastsValue.setOnFocusChangeListener ( new LastsValueFocusChangeListener() );
        string_choices = view.getContext ().getResources ().getStringArray ( R.array.question_20_other_chocies );
        for (int i = 0; i < viewSelectionBeforeLastsPart.getNumberOfViews (); i++) {
            viewSelectionBeforeLastsPart.setTextToButtons ( string_choices[i], i );
        }
        other.setTextToButtons ( string_choices[2], 0 );
        other.setOnSingleItemSelectedListener ( this );
        other.setOnClearStateListener ( this );

    }

    public void setOnTwentyNSVHListener(OnTwentyNSVHListener onTwentyNSVHListener) {
        this.onTwentyNSVHListener = onTwentyNSVHListener;
    }

    public void setProgressVisibilityStatus(int status){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "NS_20th_VH.onSingleItemSelected" );
        lastsValue.clearFocus ();
        switch (view.getId ()){
            case R.id.viewSelectionChoicesBeforeLasts:{
                other.setClear ();

                break;
            }
            case R.id.viewSelectionChoicesOther:{
                viewSelectionBeforeLastsPart.setClear ();
                OtherDialogBuilder dialogBuilder = new OtherDialogBuilder(itemView.getContext());
                dialogBuilder.setOnOtherDialogListener(this);
                dialogBuilder.show();
                break;
            }
        }
        if( i != -1 ){
            lastsValue.clearFocus ();
            lastsValue.setText ( "" );
            lastsSpinner.setSelection ( 0 );
            answer = new Answer ();
            duration = 0;
            if( i == 0 ){
                answer.setChoice ( "a" );
            }else if ( i == 1 ){
                answer.setChoice ( "b" );
            }
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }else if ( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }
    }


    @Override
    public void onClearState(View view) {
        System.out.println ( "NS_20th_VH.onClearState" );
        System.out.println ( "view = [" + view + "]" );
    }

    @Override
    public void onOtherDialogDone(String otherText) {
        System.out.println("NS_20th_VH.onOtherDialogDone");
        if( otherText.length() > 0 ){
            answer.setChoice("g");
            button_next.setEnabled(true);
            button_next.setBackgroundResource(R.drawable.enable_next_question);
        }else{
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
            other.getButtons().get(0).setBackgroundResource(R.drawable.answer_not_selected);
            other.getButtons().get(0).setTextColor(itemView.getContext().getResources().getColor(R.color.white));
        }

    }

    public interface OnTwentyNSVHListener {
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }

    private class LastIDItemSelectedListener implements
            AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            System.out.println ( "LastIDItemSelectedListener.onItemSelected" );
            answer = new Answer ();
            viewSelectionBeforeLastsPart.setClear ();
            other.setClear();
            switch (i){
                case 0:{
                    answer.setChoice ( "c" );

                    break;
                }
                case 1:{
                    answer.setChoice ( "d" );

                    break;
                }
                case 2:{
                    answer.setChoice ( "e" );

                    break;
                }
                case 3:{
                    answer.setChoice ( "f" );

                    break;
                }
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            System.out.println ( "LastIDItemSelectedListener.onNothingSelected" );
        }
    }

    private class LastsValueFocusChangeListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View view, boolean b) {
            System.out.println ( "LastsValueFocusChangeListener.onFocusChange" );
            viewSelectionBeforeLastsPart.setClear ();
            other.setClear ();
        }
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("OnNextClickListener.onClick");
            if( !answer.getChoice().equals("a") && !answer.getChoice().equals("b") && !answer.getChoice().equals("g") ){
                if( duration == 0 ){
                    lastsValue.setError("Specify the duration!");
                }else{
                    answer.setDuration(duration);
                    onTwentyNSVHListener.onNextClicked(answer);
                }
            }else{
                onTwentyNSVHListener.onNextClicked(answer);
            }
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("OnSkipClickListener.onClick");
            onTwentyNSVHListener.onSkipClicked();
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
                duration = Integer.parseInt ( lastsValue.getText ().toString () );
                button_next.setEnabled ( true );
                button_next.setBackgroundResource ( R.drawable.enable_next_question );
            }else{
                button_next.setEnabled ( false );
                button_next.setBackgroundResource ( R.drawable.disable_next_question );
            }
        }
    }
}