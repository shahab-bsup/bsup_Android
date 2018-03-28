package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_11;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.DialogueBuilder;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 2/23/2018.
 */

public class NS_11th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener, DialogueBuilder.OnOtherDialogListener, ViewSelection.OnClearStateListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection choices;
    private String[] string_choices;
    private OnEleventhNSVHListener onEleventhNSVHListener;
    private Answer answer;

    public NS_11th_VH(View itemView) {
        super ( itemView );
        answer = new Answer ();
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.new_symptom_eleventh_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_eleventh_question );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        choices = itemView.findViewById ( R.id.viewSelectionChoices );
        choices.setOnSingleItemSelectedListener ( this );
        choices.setOnClearStateListener ( this );
        string_choices = itemView.getContext ().getResources ().getStringArray ( R.array.question_11_choices );
        for (int i = 0; i < choices.getNumberOfViews (); i++) {
            choices.setTextToButtons ( string_choices[i], i );
        }
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnEleventhNSVHListener(OnEleventhNSVHListener onEleventhNSVHListener) {
        this.onEleventhNSVHListener = onEleventhNSVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "NS_11th_VH.onSingleItemSelected" );
        if( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else{
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
            if( i == 4 ){
                DialogueBuilder dialogBuilder =
                        new DialogueBuilder( itemView.getContext (), "other");
                dialogBuilder.setOnDialogListener( this );
                dialogBuilder.show ();
            }else{
                setAnswerChoice ( i );
            }
        }
    }

    @Override
    public void onOtherDialogDone(String otherText) {
        System.out.println ( "NS_11th_VH.onOtherDialogDone" );
        if( otherText.length () > 0 ){
            answer.setChoice ( "e" );
            answer.setOther ( otherText );
        }else{
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
            choices.setClear ();
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println ( "NS_11th_VH.onClearState" );

    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_11th_VH.NS_11th_VH" );
            System.out.println ( "OnNextClickListener.onClick" );
            onEleventhNSVHListener.onNextClicked ( answer );
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_11th_VH.NS_11th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onEleventhNSVHListener.onSkipClicked ();
        }
    }

    private void setAnswerChoice(int selected_choice) {
        switch (selected_choice) {
            case 0: {
                answer.setChoice ( "a" );
                break;
            }
            case 1: {
                answer.setChoice ( "b" );

                break;
            }
            case 2: {
                answer.setChoice ( "c" );

                break;
            }
            case 3: {
                answer.setChoice ( "d" );

                break;
            }
        }
    }

    public interface OnEleventhNSVHListener{
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }

}
