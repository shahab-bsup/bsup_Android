package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13;

import android.graphics.Color;
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

import tk.medlynk.patient.android.Essentials.ForcedDialogBuilder;
import tk.medlynk.patient.android.Essentials.HelpfullyDialogBuilder;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 2/24/2018.
 */

public class NS_13th_VH extends RecyclerView.ViewHolder implements
        ViewSelection.OnSingleItemSelectedListener,
        HelpfullyDialogBuilder.OnHelpfullyDialogListener,
        ViewSelection.OnMultiItemSelectedListener,
        ViewSelection.OnClearStateListener,
        ForcedDialogBuilder.OnOtherDialogListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection first, helpfully_specifier, second;
    private String[] string_choices;
    private OnThirteenNSVHListener onThirteenNSVHListener;
    private int selected_choice;
    private Answer answer;
    private List<Answer> answers;

    public NS_13th_VH(View view) {
        super ( view );
        answer = new Answer ();
        answers = new ArrayList<> (  );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = view.findViewById ( R.id.new_symptom_thirteen_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_thirteen_question );
        button_next = view.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_skip = view.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        first = view.findViewById ( R.id.viewSelectionChoices );
        string_choices = view.getContext ().getResources ().getStringArray ( R.array.question_13_22_choices );
        for (int i = 0; i < first.getNumberOfViews (); i++) {
            first.setTextToButtons ( string_choices[i], i );
        }
        helpfully_specifier = itemView.findViewById ( R.id.viewSelectionHelpfully );
        for (Button button : helpfully_specifier.getButtons ()) {
            button.setVisibility ( View.INVISIBLE );
        }
        second = itemView.findViewById ( R.id.viewSelectionSecond );
        first.setOnMultiItemSelectedListener ( this );
        second.setOnSingleItemSelectedListener ( this );
        first.setOnClearStateListener ( this );
        second.setOnClearStateListener ( this );
        second.setTextToButtons ( string_choices[7], 0 );
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnThirteenNSVHListener(OnThirteenNSVHListener onThirteenNSVHListener) {
        this.onThirteenNSVHListener = onThirteenNSVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, final int i) {
        System.out.println ( "NS_13th_VH.onSingleItemSelected" );
        if( i == 0 ){
            first.setClear ();
            answer.setChoice ( "h" );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
            helpfully_specifier.setVisibility ( View.GONE );
        }else{
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }
    }

    @Override
    public void onDialogDone(int helpfully_code) {
        System.out.println ( "NS_13th_VH.onDialogDone" );
        if( helpfully_code == -1 ){
            first.getButtons ().get ( selected_choice ).
                    setBackgroundResource ( R.drawable.answer_not_selected );
            first.getButtons ().get ( selected_choice )
                    .setTextColor ( Color.parseColor ( "#ffffff" ) );
        }else{
            setAnswerChoice(selected_choice, helpfully_code);
        }
    }

    private void setAnswerChoice(int selected_choice, int helpfully_code) {
        Answer answer = new Answer ();
        switch (selected_choice) {
            case 0: {
                answer.setChoice ( "a" );
                answer.setHelpfully ( String.valueOf ( helpfully_code ) );
                helpfully_specifier.getButtons ().
                        get ( selected_choice ).
                        setVisibility ( View.VISIBLE );
                helpfully_specifier.getButtons ().get ( selected_choice )
                        .setText ( helpFullyDeterminer ( helpfully_code ) );
                break;
            }
            case 1: {
                answer.setChoice ( "b" );
                answer.setHelpfully ( String.valueOf ( helpfully_code ) );
                helpfully_specifier.getButtons ().
                        get ( selected_choice ).
                        setVisibility ( View.VISIBLE );
                helpfully_specifier.getButtons ().get ( selected_choice )
                        .setText ( helpFullyDeterminer ( helpfully_code ) );
                break;
            }
            case 2: {
                answer.setChoice ( "c" );
                answer.setHelpfully ( String.valueOf ( helpfully_code ) );
                helpfully_specifier.getButtons ().
                        get ( selected_choice ).
                        setVisibility ( View.VISIBLE );
                helpfully_specifier.getButtons ().get ( selected_choice )
                        .setText ( helpFullyDeterminer ( helpfully_code ) );

                break;
            }
            case 3: {
                answer.setChoice ( "d" );
                answer.setHelpfully ( String.valueOf ( helpfully_code ) );
                helpfully_specifier.getButtons ().
                        get ( selected_choice ).
                        setVisibility ( View.VISIBLE );
                helpfully_specifier.getButtons ().get ( selected_choice )
                        .setText ( helpFullyDeterminer ( helpfully_code ) );
                break;
            }
            case 4: {
                answer.setChoice ( "e" );
                answer.setHelpfully ( String.valueOf ( helpfully_code ) );
                helpfully_specifier.getButtons ().
                        get ( selected_choice ).
                        setVisibility ( View.VISIBLE );
                helpfully_specifier.getButtons ().get ( selected_choice )
                        .setText ( helpFullyDeterminer ( helpfully_code ) );

                break;
            }
            case 5: {
                answer.setChoice ( "f" );
                answer.setHelpfully ( String.valueOf ( helpfully_code ) );
                helpfully_specifier.getButtons ().
                        get ( selected_choice ).
                        setVisibility ( View.VISIBLE );
                helpfully_specifier.getButtons ().get ( selected_choice )
                        .setText ( helpFullyDeterminer ( helpfully_code ) );

                break;
            }
            case 6: {
                helpfully_specifier.getButtons ().
                        get ( selected_choice ).
                        setVisibility ( View.VISIBLE );
                helpfully_specifier.getButtons ().get ( selected_choice )
                        .setText ( helpFullyDeterminer ( helpfully_code ) );

                otherDialogHandler ();
                break;
            }
        }
        answers.add ( answer );
        second.setClear ();
        if( answers.size () == 1 ){
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    private void otherDialogHandler() {
        ForcedDialogBuilder dialogBuilder =
                new ForcedDialogBuilder ( itemView.getContext () );
        dialogBuilder.setOnOtherDialogListener ( this );
        dialogBuilder.show ();
    }

    private String helpFullyDeterminer(int helpfully_code){
        String s = null;
        switch (helpfully_code){
            case 1:{
                s = "Helps a lot";

                break;
            }
            case 2:{
                s = "Helps a little";

                break;
            }
            case 3:{
                s = "Not helping";

                break;
            }
        }
        return s;
    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println ( "NS_13th_VH.onMultiItemSelected" );
        selected_choice = integer;
        HelpfullyDialogBuilder dialogBuilder =
                new HelpfullyDialogBuilder ( itemView.getContext () );
        dialogBuilder.setOnHelpfullyDialogListener ( this );
        dialogBuilder.show ();
    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {
        System.out.println ( "NS_13th_VH.onMultiItemDeselected" );
        int i = integer;
        Iterator<Answer> answerIterator = answers.iterator ();
        switch (i){
            case 0:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if( answer.getChoice () != null &&
                            answer.getChoice ().equals ( "a" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }
            case 1:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if( answer.getChoice () != null &&
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
                    if( answer.getChoice () != null &&
                            answer.getChoice ().equals ( "c" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }
            case 3:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if( answer.getChoice () != null &&
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
                    if( answer.getChoice () != null &&
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
                    if( answer.getChoice () != null &&
                            answer.getChoice ().equals ( "f" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }

            case 6:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if( answer.getChoice () != null &&
                            answer.getChoice ().equals ( "g" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }

        }
        helpfully_specifier.getButtons ().get ( i )
                .setVisibility ( View.GONE );
        if( answers.size () == 0 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println ( "NS_13th_VH.onClearState" );
        if( view.getId () == first.getId () ){
            answers.clear ();
        }
    }

    @Override
    public void onOtherDialogDone(String otherText) {
        System.out.println ( "NS_13th_VH.onOtherDialogDone" );
        Answer answer = new Answer ();
        answer.setChoice ( "g" );
        answer.setOther ( otherText );
        answers.add ( answer );
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_13th_VH.NS_13th_VH" );
            System.out.println ( "OnNextClickListener.onClick" );
            if( answers.size () > 0 ){
                onThirteenNSVHListener.onNextClicked ( answers );
            }else{
                onThirteenNSVHListener.onNextClicked ( answer );
            }
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_13th_VH.NS_13th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onThirteenNSVHListener.onSkipClicked ();
        }
    }

    public interface OnThirteenNSVHListener {
        void onNextClicked(Answer answer);
        void onNextClicked(List<Answer> answers);
        void onSkipClicked();
    }
}
