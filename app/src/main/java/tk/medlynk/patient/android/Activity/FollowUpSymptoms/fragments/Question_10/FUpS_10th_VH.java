package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10;

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

import tk.medlynk.patient.android.Essentials.OtherDialogBuilder;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/4/2018.
 */

public class FUpS_10th_VH extends RecyclerView.ViewHolder implements
        ViewSelection.OnSingleItemSelectedListener,
        ViewSelection.OnMultiItemSelectedListener,
        ViewSelection.OnClearStateListener,
        OtherDialogBuilder.OnOtherDialogListener {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private OnFUpSTenthVHListener onFUpSTenthVHListener;
    private ViewSelection first, second;
    private String[] string_choices;
    private List<Answer> choices = new ArrayList<> (  );
    private Answer choice = new Answer ();

    public FUpS_10th_VH(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_tenth_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_10th_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        button_next.setEnabled ( false );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        first = itemView.findViewById ( R.id.single_select_choice );
        first.setOnSingleItemSelectedListener ( this );
        second = itemView.findViewById ( R.id.multi_select_choice );
        second.setOnMultiItemSelectedListener ( this );
        String string = itemView.getContext ()
                .getString ( R.string.first_choice_follow_up_symptom_9th_10yj_choices );
        string_choices = itemView.getContext ().getResources ().getStringArray ( R.array.follow_up_symptoms_9th_10th_choices );
        first.setTextToButtons ( string, 0 );
        first.setOnClearStateListener ( this );
        for (int i = 0; i < second.getNumberOfViews (); i++){
            second.setTextToButtons ( string_choices[i], i );
        }
        second.setOnClearStateListener ( this );
    }

    public void setOnFUpSTenthVHListener(OnFUpSTenthVHListener onFUpSTenthVHListener) {
        this.onFUpSTenthVHListener = onFUpSTenthVHListener;
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FUpS_10th_VH.onSingleItemSelected" );
        if( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else{
            second.setClear ();
            choice.setChoice ( "a" );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println ( "FUpS_10th_VH.onMultiItemSelected" );
        first.setClear ();
        if( integer == 3 ){
            OtherDialogBuilder dialogBuilder = new OtherDialogBuilder ( itemView.getContext () );
            dialogBuilder.setOnOtherDialogListener ( this );
            dialogBuilder.show ();
        }else{
            setAnswerChoices(integer);
        }
    }

    private void setAnswerChoices(Integer integer) {
        Answer answer = new Answer ();
        switch (integer){
            case 0:{
                answer.setChoice ( "b" );
                break;
            }
            case 1:{
                answer.setChoice ( "c" );

                break;
            }
            case 2:{
                answer.setChoice ( "d" );

                break;
            }
        }
        choices.add ( answer );
        if( choices.size () == 1 ){
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {
        System.out.println ( "FUpS_10th_VH.onMultiItemDeselected" );
        int i = integer;
        Iterator<Answer> answerIterator = choices.iterator ();
        switch (i) {
            case 0: {
                while (answerIterator.hasNext()) {
                    Answer answer = answerIterator.next();
                    if (answer.getChoice() != null &&
                            answer.getChoice().equals("b"))
                        answerIterator.remove();
                }

                break;
            }
            case 1:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if(  answer.getChoice () != null &&
                            answer.getChoice ().equals ( "c" ) ){
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
                            answer.getChoice ().equals ( "d" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

            }
            case 3:{
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
        }
        if( choices.size () == 0 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println ( "FUpS_10th_VH.onClearState" );
        if( view.getId () == second.getId () ){
            choices.clear ();
        }
    }

    @Override
    public void onOtherDialogDone(String otherText) {
        System.out.println ( "FUpS_10th_VH.onOtherDialogDone" );
        if( otherText.length () > 0 ){
            Answer answer = new Answer ();
            answer.setChoice ( "e" );
            answer.setOther ( otherText );
            choices.add ( answer );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        } else {
            second.getButtons ().get ( 3 ).setBackgroundResource ( R.drawable.answer_not_selected );
            second.getButtons ().get ( 3 ).setTextColor ( itemView.
                    getContext ().
                    getResources ().
                    getColor ( R.color.white ) );
        }
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_10th_VH.FUpS_10th_VH" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            if( choices.size () > 0 ){
                onFUpSTenthVHListener.onNextClicked ( choices );
            }else{
                onFUpSTenthVHListener.onNextClicked ( choice );
            }
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_10th_VH.FUpS_10th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFUpSTenthVHListener.onSkipClick ();
        }
    }

    public interface OnFUpSTenthVHListener {
        void onSkipClick();
        void onNextClicked(List<Answer> choices);
        void onNextClicked(Answer choice);
    }

}