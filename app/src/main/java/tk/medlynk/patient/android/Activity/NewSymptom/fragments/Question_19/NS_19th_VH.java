package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_19;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.DialogueBuilder;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 2/24/2018.
 */

public class NS_19th_VH extends RecyclerView.ViewHolder implements
        ViewSelection.OnSingleItemSelectedListener,
        ViewSelection.OnClearStateListener,
        DialogueBuilder.OnOtherDialogListener {

    private ProgressBar progressBar;
    private View question_view, years_view;
    private Button button_next, button_skip;
    private TextView question;
    private Spinner spinner_years;
    private ViewSelection before_years_viewselection, after_years_viewselection;
    private String[] before_years_strings, after_years_strings;
    private OnNineteenNSVHListener onNineteenNSVHListener;
    private Answer answer = new Answer ();

    public NS_19th_VH(View view) {
        super ( view );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = view.findViewById ( R.id.new_symptom_nineteen_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_nineteen_question );
        button_next = view.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_skip = view.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );

        before_years_strings = view.getContext ().getResources ().getStringArray ( R.array.question_19_before_years_choices );
        after_years_strings = view.getContext ().getResources ().getStringArray ( R.array.question_19_after_years_choices );

        before_years_viewselection = view.findViewById ( R.id.viewSelectionChoicesBeforeYears );
        before_years_viewselection.setOnSingleItemSelectedListener ( this );
        before_years_viewselection.setOnClearStateListener ( this );
        for (int i = 0; i < before_years_viewselection.getNumberOfViews (); i++) {
            before_years_viewselection.setTextToButtons ( before_years_strings[i], i );
        }
        after_years_viewselection = view.findViewById ( R.id.viewSelectionChoicesAfterYears );
        for (int i = 0; i < after_years_viewselection.getNumberOfViews (); i++) {
            after_years_viewselection.setTextToButtons ( after_years_strings[i], i );
        }
        after_years_viewselection.setOnSingleItemSelectedListener ( this );
        after_years_viewselection.setOnClearStateListener ( this );

        years_view = view.findViewById ( R.id.question_19_years_row );

        spinner_years = years_view.findViewById ( R.id.spinner_years );
        spinner_years.setOnItemSelectedListener ( new YearsItemSelectedListener() );
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnNineteenNSVHListener(OnNineteenNSVHListener onNineteenNSVHListener) {
        this.onNineteenNSVHListener = onNineteenNSVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "NS_19th_VH.onSingleItemSelected" );
        if( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else{
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
            switch (view.getId ()){
                case R.id.viewSelectionChoicesBeforeYears:{
                    after_years_viewselection.setClear ();
                    setBeforeYearsChoice(i);
                    break;
                }
                case R.id.viewSelectionChoicesAfterYears:{
                    before_years_viewselection.setClear ();
                    setAfterYearsChoice(i);
                    break;
                }
            }
        }
    }

    private void setAfterYearsChoice(int i) {
        switch (i){
            case 0:{
                answer.setChoice ( "f" );
                break;
            }
            case 1:{
                DialogueBuilder dialogBuilder = new DialogueBuilder( itemView.getContext (), "other");
                dialogBuilder.setOnDialogListener( this );
                dialogBuilder.show ();
                break;
            }
        }
    }

    private void setBeforeYearsChoice(int i) {
        answer = new Answer ();
        switch (i){
            case 0:{
                answer.setChoice ( "a" );

                break;
            }
            case 1:{
                answer.setChoice ( "b" );

                break;
            }
            case 2:{
                answer.setChoice ( "c" );

                break;
            }
            case 3:{
                answer.setChoice ( "d" );

                break;
            }
        }
    }

    public void setAdapter(ArrayAdapter<String> arrayAdapter){
        this.spinner_years.setAdapter ( arrayAdapter );
    }

    @Override
    public void onClearState(View view) {
        System.out.println ( "NS_19th_VH.onClearState" );

    }

    @Override
    public void onOtherDialogDone(String otherText) {
        System.out.println ( "NS_19th_VH.onOtherDialogDone" );
        if( otherText.length () > 0 ){
            answer.setChoice ( "g" );
            answer.setOther ( otherText );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        } else {
            after_years_viewselection.getButtons ().get ( 1 ).setBackgroundResource ( R.drawable.answer_not_selected );
            after_years_viewselection.getButtons ().get ( 1 ).setTextColor ( itemView.
                    getContext ().
                    getResources ().
                    getColor ( R.color.white ) );
        }
    }

    public interface OnNineteenNSVHListener {
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }

    private final class YearsItemSelectedListener implements
            AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            System.out.println ( "YearsItemSelectedListener.onItemSelected" );
            answer.setChoice ( "e" );
            answer.setYears(i+1);
            years_view.setBackgroundResource ( R.drawable.answer_selected );
            before_years_viewselection.setClear ();
            after_years_viewselection.setClear ();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            System.out.println ( "YearsItemSelectedListener.onNothingSelected" );
            years_view.setBackgroundResource ( R.drawable.answer_not_selected );
        }
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_19th_VH.NS_19th_VH" );
            System.out.println ( "OnNextClickListener.onClick" );
            onNineteenNSVHListener.onNextClicked ( answer );
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_19th_VH.NS_19th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onNineteenNSVHListener.onSkipClicked ();
        }
    }
}
