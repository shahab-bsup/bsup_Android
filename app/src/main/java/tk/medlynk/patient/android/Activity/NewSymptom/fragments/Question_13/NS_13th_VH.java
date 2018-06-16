package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13;

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
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 2/24/2018.
 */

public class NS_13th_VH extends RecyclerView.ViewHolder implements
        ViewSelection.OnMultiItemSelectedListener,
        ForcedDialogBuilder.OnOtherDialogListener,
        ViewSelection.OnHelpfullyOptionsClickListener,
        ViewSelection.OnClearStateListener,
        ViewSelection.OnSingleItemSelectedListener {

    private final TextView otherText;
    private String initial_other_text = "";
    private ProgressBar progressBar;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection first, second;
    private String[] string_choices;
    private Answer answer;
    private List<Answer> answers;
    private OnThirteenNSVHListener onThirteenNSVHListener;

    public NS_13th_VH(View view) {
        super ( view );
        otherText = itemView.findViewById ( R.id.txtOther );
        answer = new Answer ();
        answers = new ArrayList<> (  );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = view.findViewById ( R.id.new_symptom_thirteen_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_thirteen_question );
        button_next = view.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_next.setEnabled ( false );
        button_skip = view.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        first = view.findViewById ( R.id.viewSelectionFirst );
        second = view.findViewById ( R.id.viewSelectionSecond );
        string_choices = view.getContext ().getResources ().getStringArray ( R.array.question_13_22_choices );
        for (int i = 0; i < first.getNumberOfViews (); i++) {
            first.setTextToButtons ( string_choices[i], i );
        }
        second.setTextToButtons ( string_choices[7], 0 );
        first.setOnMultiItemSelectedListener ( this );
        first.setOnClearStateListener ( this );
        first.setOnHelpfullyOptionClickListener ( this );
        second.setOnSingleItemSelectedListener ( this );
        second.setOnClearStateListener ( this );
    }

    private void setOtherTextVisibilityStatus(int status){
        this.otherText.setVisibility ( status );
    }

    private void setOtherText(String other) {
        this.otherText.setText ( other );
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnThirteenNSVHListener(OnThirteenNSVHListener onThirteenNSVHListener) {
        this.onThirteenNSVHListener = onThirteenNSVHListener;
    }

    private void setAnswerChoice(int selected_choice) {
        Answer answer = new Answer ();
        switch (selected_choice) {
            case 0: {
                answer.setChoice ( "a" );

                answers.add ( answer );
                break;
            }
            case 1: {
                answer.setChoice ( "b" );

                answers.add ( answer );
                break;
            }
            case 2: {
                answer.setChoice ( "c" );

                answers.add ( answer );
                break;
            }
            case 3: {
                answer.setChoice ( "d" );

                answers.add ( answer );
                break;
            }
            case 4: {
                answer.setChoice ( "e" );

                answers.add ( answer );
                break;
            }
            case 5: {
                answer.setChoice ( "f" );

                answers.add ( answer );
                break;
            }
            case 6: {
                ForcedDialogBuilder dialogBuilder = new ForcedDialogBuilder ( itemView.getContext () );
                dialogBuilder.setOnOtherDialogListener ( this );
                dialogBuilder.show ();

                break;
            }
        }
        if( answers.size () == 1 ){
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println ( "NS_13th_VH.onMultiItemSelected" );
        second.setClear ();
        setAnswerChoice ( integer );
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

        if( answers.size () == 0 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }
    }

    @Override
    public void onOtherDialogDone(String otherText) {
        System.out.println ( "NS_13th_VH.onOtherDialogDone" );
        button_next.setEnabled ( true );
        button_next.setBackgroundResource ( R.drawable.enable_next_question );
        Answer answer = new Answer ();
        answer.setChoice ( "g" );
        answer.setOther ( otherText );
        answers.add ( answer );
    }

    @Override
    public void onHelpFullyClicked(int position, int helpfully_option) {
        System.out.println ( "NS_13th_VH.onHelpFullyClicked" );
        Iterator<Answer> answerIterator = answers.iterator ();
        switch (position) {
            case 0: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null && answer.getChoice ().equals ( "a" )) {
                        answer.setHelpfully ( helpfully_option );
                        break;
                    }
                }
                break;
            }
            case 1: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null && answer.getChoice ().equals ( "b" )) {
                        answer.setHelpfully ( helpfully_option );
                        break;
                    }
                }

                break;
            }
            case 2: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null && answer.getChoice ().equals ( "c" )) {
                        answer.setHelpfully ( helpfully_option );
                        break;
                    }
                }

                break;
            }

            case 3: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null && answer.getChoice ().equals ( "d" )) {
                        answer.setHelpfully ( helpfully_option );
                        break;
                    }
                }
                break;
            }

            case 4: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null && answer.getChoice ().equals ( "e" )) {
                        answer.setHelpfully ( helpfully_option );
                        break;
                    }
                }
                break;
            }

            case 5: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null && answer.getChoice ().equals ( "f" )) {
                        answer.setHelpfully ( helpfully_option );
                        break;
                    }
                }
                break;
            }

            case 6: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null && answer.getChoice ().equals ( "g" )) {
                        answer.setHelpfully ( helpfully_option );
                        break;
                    }
                }
                break;
            }
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
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "NS_13th_VH.onSingleItemSelected" );
        if( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else{
            first.setClear ();
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
            answer = new Answer ();
            answer.setChoice ( "h" );
        }
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_13th_VH.NS_13th_VH" );
            System.out.println ( "OnNextClickListener.onClick" );
            if( answers.size () > 0 ){
                boolean hasError = false;
                for (int i = 0; i < answers.size (); i++) {
                    if (answers.get ( i ).getHelpfully () == -1) {
                        first.showHelpfullyOptionError ( i, View.VISIBLE );
                        hasError = !hasError;
                        break;
                    }
                }
                if( !hasError ){
                    onThirteenNSVHListener.onNextClicked ( answers );
                }
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
