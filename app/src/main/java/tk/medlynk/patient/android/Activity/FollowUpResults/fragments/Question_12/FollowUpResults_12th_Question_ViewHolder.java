package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_12;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9.FUpS_9th_VH;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/22/2018.
 */

public class FollowUpResults_12th_Question_ViewHolder extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener, ViewSelection.OnMultiItemSelectedListener, ViewSelection.OnClearStateListener {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private ViewSelection none, other;
    private String[] string_choices;
    private List<Answer> answers = new ArrayList<> (  );
    private Answer answer = new Answer ();
    private FUpS_9th_VH.OnFUpSNinthVHListener onFollowUpNinthQuestionViewsClickListener;
    private String otherString;
    private int single_select = -1;
    private int multi_select = -1;

    public FollowUpResults_12th_Question_ViewHolder(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_results_twelve_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.FUPR_twelve_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        none = itemView.findViewById ( R.id.single_select_choice );
        none.setOnSingleItemSelectedListener ( this );
        other = itemView.findViewById ( R.id.multi_select_choice );
        other.setOnMultiItemSelectedListener ( this );
        String string = itemView.getContext ()
                .getString ( R.string.first_choice_follow_up_symptom_9th_10yj_choices );
        string_choices = itemView.getContext ().getResources ().getStringArray ( R.array.follow_up_symptoms_9th_10th_choices );
        none.setTextToButtons ( string, 0 );
        none.setOnClearStateListener ( this );
        for (int i = 0; i < other.getNumberOfViews (); i++){
            other.setTextToButtons ( string_choices[i], i );
        }
        other.setOnClearStateListener ( this );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FollowUpResults_12th_Question_ViewHolder.onSingleItemSelected" );

    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println ( "FollowUpResults_12th_Question_ViewHolder.onMultiItemSelected" );

    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {
        System.out.println ( "FollowUpResults_12th_Question_ViewHolder.onMultiItemDeselected" );

    }

    @Override
    public void onClearState(View view) {
        System.out.println ( "FollowUpResults_12th_Question_ViewHolder.onClearState" );

    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            System.out.println ( "OnNextButtonClickListener.onClick" );
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            System.out.println ( "OnSkipClickListener.onClick" );
        }
    }
}
