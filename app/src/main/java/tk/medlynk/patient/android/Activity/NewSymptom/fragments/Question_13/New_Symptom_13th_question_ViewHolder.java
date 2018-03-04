package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13;

import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 2/24/2018.
 */

public class New_Symptom_13th_question_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ViewSelection.OnSingleItemSelectedListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button next, skip;
    private TextView question;
    private ViewSelection choices;
    private String[] string_choices;
    private On13QuestionViewClickListener listener;

    public New_Symptom_13th_question_ViewHolder(View view) {
        super ( view );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = view.findViewById ( R.id.new_symptom_thirteen_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_thirteen_question );
        next = view.findViewById ( R.id.btnNextQuestion );
        next.setOnClickListener ( this );
        skip = view.findViewById ( R.id.btnSkipQuestion );
        skip.setOnClickListener ( this );
        choices = view.findViewById ( R.id.viewSelectionChoices );
        choices.setOnSingleItemSelectedListener ( this );
        string_choices = view.getContext ().getResources ().getStringArray ( R.array.question_13_22_choices );
        for (int i = 0; i < choices.getNumberOfViews (); i++) {
            choices.setTextToButtons ( string_choices[i], i );
        }
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setListener(On13QuestionViewClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        System.out.println ( "New_Symptom_13th_question_ViewHolder.onClick" );
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
    public void onSingleItemSelected(View view, final int i) {
        System.out.println ( "New_Symptom_13th_question_ViewHolder.onSingleItemSelected" );
        final AppCompatDialog dialog = new AppCompatDialog ( view.getContext () );
        dialog.setContentView ( R.layout.treatment_intensity_dialog );
        TextView help_a_lot, help_a_little, not_helping;
        help_a_lot = dialog.findViewById ( R.id.treatment_help_a_lot );
        help_a_little = dialog.findViewById ( R.id.treatment_help_a_little );
        not_helping = dialog.findViewById ( R.id.treatment_not_helping );
        help_a_lot.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                listener.onTreatmentClicked ( i, 1 );
                dialog.dismiss ();
            }
        } );
        help_a_little.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                listener.onTreatmentClicked ( i, 2 );
                dialog.dismiss ();
            }
        } );
        not_helping.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                listener.onTreatmentClicked ( i, 3 );
                dialog.dismiss ();
            }
        } );
        dialog.show ();
    }

    public interface On13QuestionViewClickListener{
        void onNextClicked();
        void onSkipClicked();
        void onTreatmentClicked(int position, int treatmentID);
    }

}
