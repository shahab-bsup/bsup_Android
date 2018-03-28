package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_21;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tk.medlynk.patient.android.Essentials.DialogueBuilder;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/2/2018.
 */

public class NS_21th_VH extends RecyclerView.ViewHolder implements
        ViewSelection.OnMultiItemSelectedListener,
        DialogueBuilder.OnOtherDialogListener {

    private ProgressBar progressBar;
    private final String[] string_choices;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection choices;
    private On21thQuestionVHListener on21thQuestionVHListener;
    private final List<Answer> answers = new ArrayList<>();

    public NS_21th_VH(View view) {
        super ( view );
        progressBar = view.findViewById ( R.id.progress_bar );
        question_view = view.findViewById ( R.id.new_symptom_twenty1_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_twenty1_question );
        button_next = view.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_skip = view.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        choices = view.findViewById ( R.id.viewSelectionChoices );
        choices.setOnMultiItemSelectedListener ( this );
        string_choices = view.getContext ().getResources ().getStringArray ( R.array.question_21_choices );
        for (int i = 0; i < choices.getNumberOfViews (); i++) {
            choices.setTextToButtons ( string_choices[i], i );
        }
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOn21thQuestionVHListener(On21thQuestionVHListener on21thQuestionVHListener) {
        this.on21thQuestionVHListener = on21thQuestionVHListener;
    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println ( "NS_21th_VH.onMultiItemSelected" );
        int i = integer;
        if( i == 5 ){
            DialogueBuilder dialogBuilder = new DialogueBuilder( itemView.getContext (), "other");
            dialogBuilder.setOnDialogListener( this );
            dialogBuilder.show ();
        }else{
            setAnswerChoices(integer);
        }

    }

    private void setAnswerChoices(int i) {
        Answer answer = new Answer ();
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

            case 4:{
                answer.setChoice ( "e" );

                break;
            }
        }
        answers.add ( answer );
        if( answers.size () == 1 ){
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {
        System.out.println ( "NS_21th_VH.onMultiItemDeselected" );
        int i = integer;
        Iterator<Answer> answerIterator = answers.iterator ();
        switch (i){
            case 0:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if(  answer.getChoice () != null &&
                            answer.getChoice ().equals ( "a" ) )
                        answerIterator.remove ();
                }

                break;
            }
            case 1:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if(  answer.getChoice () != null &&
                            answer.getChoice ().equals ( "b" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }
            case 2:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if(  answer.getChoice () != null &&
                            answer.getChoice ().equals ( "c" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

            }
            case 3:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if(  answer.getChoice () != null &&
                            answer.getChoice ().equals ( "d" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }
            case 4:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if(  answer.getChoice () != null &&
                            answer.getChoice ().equals ( "e" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }
            case 5:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if(  answer.getChoice () != null &&
                            answer.getChoice ().equals ( "f" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }

        }
        if( answers.size () == 0 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }
    }

    @Override
    public void onOtherDialogDone(String otherText) {
        System.out.println("NS_21th_VH.onOtherDialogDone");
        if( otherText.length () > 0 ){
            Answer answer = new Answer ();
            answer.setChoice ( "f" );
            answer.setOther ( otherText );
            answers.add ( answer );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        } else {
            choices.getButtons ().get ( 5 ).setBackgroundResource ( R.drawable.answer_not_selected );
            choices.getButtons ().get ( 5 ).setTextColor ( itemView.
                    getContext ().
                    getResources ().
                    getColor ( R.color.white ) );
        }
    }

    public interface On21thQuestionVHListener {
        void onNextClicked(List<Answer> answers);
        void onSkipClicked();
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("NS_21th_VH.NS_21th_VH");
            System.out.println("OnNextClickListener.onClick");
            on21thQuestionVHListener.onNextClicked(answers);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("NS_21th_VH.NS_21th_VH");
            System.out.println("OnSkipClickListener.onClick");
            on21thQuestionVHListener.onSkipClicked();
        }
    }
}
