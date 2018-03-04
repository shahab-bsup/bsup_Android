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

/**
 * Created by Shahab on 2/24/2018.
 */

public class New_Symptom_19th_question_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ViewSelection.OnSingleItemSelectedListener, ViewSelection.OnClearStateListener {

    private ProgressBar progressBar;
    private View question_view, years_view;
    private Button next, skip;
    private TextView question;
    private Spinner spinner_years;
    private ViewSelection before_years_viewselection, after_years_viewselection;
    private String[] before_years_strings, after_years_strings;
    private On19QuestionViewsClickListener listener;

    public New_Symptom_19th_question_ViewHolder(View view) {
        super ( view );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = view.findViewById ( R.id.new_symptom_nineteen_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_nineteen_question );
        next = view.findViewById ( R.id.btnNextQuestion );
        next.setOnClickListener ( this );
        skip = view.findViewById ( R.id.btnSkipQuestion );
        skip.setOnClickListener ( this );

        //fetching string choices from string resources
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

    public void setListener(On19QuestionViewsClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        System.out.println ( "New_Symptom_19th_question_ViewHolder.onClick" );
        int id = view.getId ();
        switch (id){
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
        System.out.println ( "New_Symptom_19th_question_ViewHolder.onSingleItemSelected" );
        switch (view.getId ()){
            case R.id.viewSelectionChoicesBeforeYears:{
                after_years_viewselection.setClear ();
                break;
            }
            case R.id.viewSelectionChoicesAfterYears:{
                before_years_viewselection.setClear ();
                break;
            }
        }
        listener.onViewSelectionsClicked ( view, i );
    }

    public void setAdapter(ArrayAdapter<String> arrayAdapter){
        this.spinner_years.setAdapter ( arrayAdapter );
    }

    @Override
    public void onClearState(View view) {
        System.out.println ( "New_Symptom_19th_question_ViewHolder.onClearState" );
        System.out.println ( "view = [" + view + "]" );
    }

    public interface On19QuestionViewsClickListener {
        void onNextClicked();
        void onSkipClicked();
        void onViewSelectionsClicked(View view, int i);
        void onSpinnerItemSelected(int position);
    }

    private final class YearsItemSelectedListener implements
            AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            System.out.println ( "YearsItemSelectedListener.onItemSelected" );
            listener.onSpinnerItemSelected ( i );
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            System.out.println ( "YearsItemSelectedListener.onNothingSelected" );

        }
    }
}
