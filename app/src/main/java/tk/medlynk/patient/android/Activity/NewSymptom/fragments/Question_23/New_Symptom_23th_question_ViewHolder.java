package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_23;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 3/2/2018.
 */

public class New_Symptom_23th_question_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ViewSelection.OnSingleItemSelectedListener {

    private View question_view;
    private Button next, skip;
    private TextView question;
    private ViewSelection choice;
    private On23QuestionViewsClickListener listener;

    public New_Symptom_23th_question_ViewHolder(View view) {
        super ( view );
        question_view = view.findViewById ( R.id.new_symptom_twenty3_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_twenty2_question );
        next = view.findViewById ( R.id.btnNextQuestion );
        next.setOnClickListener ( this );
        skip = view.findViewById ( R.id.btnSkipQuestion );
        skip.setOnClickListener ( this );
        choice = view.findViewById ( R.id.viewSelectionChoices );
        choice.setTextToButtons ( view.getContext ().getResources ().getString ( R.string.none ), 0 );
        choice.setOnSingleItemSelectedListener ( this );
    }

    public void setListener(On23QuestionViewsClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        System.out.println ( "New_Symptom_23th_question_ViewHolder.onClick" );
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
        System.out.println ( "New_Symptom_23th_question_ViewHolder.onSingleItemSelected" );
        listener.onViewSelectionClicked ( view, i );
    }

    public interface On23QuestionViewsClickListener{
        void onNextClicked();
        void onSkipClicked();
        void onViewSelectionClicked(View view, int i);
    }
}
