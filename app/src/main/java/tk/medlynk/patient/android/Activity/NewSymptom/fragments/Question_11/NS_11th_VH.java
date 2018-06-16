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

public class NS_11th_VH extends RecyclerView.ViewHolder implements
        ViewSelection.OnSingleItemSelectedListener,
        DialogueBuilder.OnOtherDialogListener,
        ViewSelection.OnClearStateListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection choices;
    private String[] string_choices;
    private OnEleventhNSVHListener onEleventhNSVHListener;
    private Answer answer;
    private TextView otherText;
    private String initial_other_text = "";

    public NS_11th_VH(View itemView) {
        super ( itemView );
        otherText = itemView.findViewById ( R.id.txtOther );
        answer = new Answer ();
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.new_symptom_eleventh_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_eleventh_question );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_next.setEnabled ( false );
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

    public void setOnEleventhNSVHListener(OnEleventhNSVHListener
                                                  onEleventhNSVHListener) {
        this.onEleventhNSVHListener = onEleventhNSVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        if( i == -1 ){
            setOtherTextVisibilityStatus ( View.GONE );
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else{
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
            if( i == 4 ){
                DialogueBuilder dialogBuilder =
                        new DialogueBuilder( itemView.getContext (),
                                "other",
                                initial_other_text);
                dialogBuilder.setOnDialogListener( this );
                dialogBuilder.show ();
            }else{
                setAnswerChoice ( i );
            }
        }
    }

    @Override
    public void onOtherDialogDone(String otherText) {
        choices.notifyDataSetChanged ();
        if( otherText.length () > 0 ){
            answer.setChoice ( "e" );
            answer.setOther ( otherText );
            setOtherTextVisibilityStatus ( View.VISIBLE );
            setOtherText ( otherText );
        }else{
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
            choices.setClear ();
        }
    }

    @Override
    public void onClearState(View view) {

    }

    public void onUpdateUI(Answer answerDB) {
        switch (answerDB.getChoice ()){
            case "a":{
                choices.previewOfDBResult ( true,
                        true,
                        0);
                break;
            }
            case "b":{
                choices.previewOfDBResult ( true,
                        true,
                        1);
                break;
            }
            case "c":{
                choices.previewOfDBResult ( true,
                        true,
                        2);
                break;
            }
            case "d":{
                choices.previewOfDBResult ( true,
                        true,
                        3);
                break;
            }
            case "e":{
//                choices.previewOfDBResult ( true,
//                        true,
//                        4);
                choices.setSelect ( 4 );
                setOtherTextVisibilityStatus ( View.VISIBLE );
                setOtherText( answerDB.getOther () );
                initial_other_text = answerDB.getOther ();
                break;
            }
        }
    }

    private void setOtherTextVisibilityStatus(int status){
        this.otherText.setVisibility ( status );
    }

    private void setOtherText(String other) {
        this.otherText.setText ( other );
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            onEleventhNSVHListener.onNextClicked ( answer );
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
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
