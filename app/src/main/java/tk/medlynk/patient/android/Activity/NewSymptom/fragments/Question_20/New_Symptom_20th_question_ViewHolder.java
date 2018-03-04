package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_20;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 3/2/2018.
 */

public class New_Symptom_20th_question_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ViewSelection.OnSingleItemSelectedListener, ViewSelection.OnClearStateListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button next, skip;
    private TextView question;
    private String[] string_choices;
    private On20QuestionViewsClickListener listener;
    private ViewSelection viewSelectionBeforeLastsPart, other;
    private AppCompatEditText lastsValue;
    private Spinner lastsID;

    public New_Symptom_20th_question_ViewHolder(View view) {
        super ( view );
        progressBar = view.findViewById ( R.id.progress_bar );
        question_view = view.findViewById ( R.id.new_symptom_twenty_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_twelve_question );
        next = view.findViewById ( R.id.btnNextQuestion );
        next.setOnClickListener ( this );
        skip = view.findViewById ( R.id.btnSkipQuestion );
        skip.setOnClickListener ( this );
        lastsID = view.findViewById ( R.id.spinnerLastsID );
        lastsID.setOnItemSelectedListener ( new LastIDItemSelectedListener() );
        viewSelectionBeforeLastsPart = view.findViewById ( R.id.viewSelectionChoicesBeforeLasts );
        viewSelectionBeforeLastsPart.setOnSingleItemSelectedListener ( this );
        viewSelectionBeforeLastsPart.setOnClearStateListener ( this );
        other = view.findViewById ( R.id.viewSelectionChoicesOther );
        lastsValue = view.findViewById ( R.id.edtLastsValue );
        lastsValue.setOnFocusChangeListener ( new LastsValueFocusChangeListener() );
        string_choices = view.getContext ().getResources ().getStringArray ( R.array.question_20_other_chocies );
        for (int i = 0; i < viewSelectionBeforeLastsPart.getNumberOfViews (); i++) {
            viewSelectionBeforeLastsPart.setTextToButtons ( string_choices[i], i );
        }

        other.setTextToButtons ( string_choices[2], 0 );
        other.setOnSingleItemSelectedListener ( this );
        other.setOnClearStateListener ( this );

    }

    public void setListener(On20QuestionViewsClickListener listener) {
        this.listener = listener;
    }

    public void setLastsIDAdapter( ArrayAdapter<String> adapter){
        this.lastsID.setAdapter ( adapter );
    }

    public void setLastingEmpty(){
        this.lastsValue.setText ( "" );
    }

    public void setProgressVisibilityStatus(int status){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onClick(View view) {
        System.out.println ( "New_Symptom_20th_question_ViewHolder.onClick" );
        switch (view.getId ()){
            case R.id.btnNextQuestion:{
                listener.onNextClicked ();
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
        System.out.println ( "New_Symptom_20th_question_ViewHolder.onSingleItemSelected" );
        lastsValue.clearFocus ();
        viewSelectionBeforeLastsPart.setFocusableInTouchMode ( true );
        switch (view.getId ()){
            case R.id.viewSelectionChoicesBeforeLasts:{
                other.setClear ();
                break;
            }
            case R.id.viewSelectionChoicesOther:{
                viewSelectionBeforeLastsPart.setClear ();
                break;
            }
        }
        listener.onViewSelectionsClicked ( view, i );
    }

    public int getLastsValue(){
        if( !lastsValue.getText ().toString ().isEmpty () )
            return Integer.parseInt ( lastsValue.getText ().toString ());
        return -1;
    }

    @Override
    public void onClearState(View view) {
        System.out.println ( "New_Symptom_20th_question_ViewHolder.onClearState" );
        System.out.println ( "view = [" + view + "]" );
    }

    public interface On20QuestionViewsClickListener{
        void onNextClicked();
        void onSkipClicked();
        void onSpinnerItemSelected(int position);
        void onViewSelectionsClicked(View view, int i);
    }

    private class LastIDItemSelectedListener implements
            AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            System.out.println ( "LastIDItemSelectedListener.onItemSelected" );

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
}