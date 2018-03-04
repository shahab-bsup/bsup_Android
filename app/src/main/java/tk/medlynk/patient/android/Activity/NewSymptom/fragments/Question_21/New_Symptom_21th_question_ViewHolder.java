package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_21;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 3/2/2018.
 */

public class New_Symptom_21th_question_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ViewSelection.OnSingleItemSelectedListener, ViewSelection.OnMultiItemSelectedListener {

    private ProgressBar progressBar;
    private final String[] string_choices;
    private View question_view;
    private Button next, skip;
    private TextView question;
    private ViewSelection choices;
    private On21QuestionViewsClickListener listener;

    public New_Symptom_21th_question_ViewHolder(View view) {
        super ( view );
        progressBar = view.findViewById ( R.id.progress_bar );
        question_view = view.findViewById ( R.id.new_symptom_twenty1_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_twenty1_question );
        next = view.findViewById ( R.id.btnNextQuestion );
        next.setOnClickListener ( this );
        skip = view.findViewById ( R.id.btnSkipQuestion );
        skip.setOnClickListener ( this );
        choices = view.findViewById ( R.id.viewSelectionChoices );
        choices.setOnSingleItemSelectedListener ( this );
        choices.setOnMultiItemSelectedListener ( this );
        string_choices = view.getContext ().getResources ().getStringArray ( R.array.question_21_choices );
        for (int i = 0; i < choices.getNumberOfViews (); i++) {
            choices.setTextToButtons ( string_choices[i], i );
        }
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setListener(On21QuestionViewsClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        System.out.println ( "New_Symptom_21th_question_ViewHolder.onClick" );
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
        System.out.println ( "New_Symptom_21th_question_ViewHolder.onSingleItemSelected" );
        listener.onViewSelectionClicked ( view, i );
    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println ( "New_Symptom_21th_question_ViewHolder.onMultiItemSelected" );
        listener.onViewMultipleSelected ( integer );
    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {
        System.out.println ( "New_Symptom_21th_question_ViewHolder.onMultiItemDeselected" );
        listener.onViewMultipleDeselected ( integer );
    }

    public interface On21QuestionViewsClickListener{
        void onNextClicked();
        void onSkipClicked();
        void onViewSelectionClicked(View view, int i);
        void onViewMultipleSelected( Integer integer );
        void onViewMultipleDeselected( Integer integer );
    }

}
