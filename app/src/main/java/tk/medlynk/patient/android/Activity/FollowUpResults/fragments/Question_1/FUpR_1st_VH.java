package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_1;

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

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_14.ButtonAdapter;
import tk.medlynk.patient.android.Essentials.DialogueBuilder;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/22/2018.
 */

public class FUpR_1st_VH extends RecyclerView.ViewHolder implements ViewSelection.OnMultiItemSelectedListener, ViewSelection.OnClearStateListener, ViewSelection.OnSingleItemSelectedListener, ButtonAdapter.OnOptionsClickListener, DialogueBuilder.OnOtherDialogListener {

    private final String[] string_choices;
    private final Button button_next;
    private final Button button_skip;
    private final View question_view;
    private final ProgressBar progressBar;
    private final TextView first_question;
    private final ViewSelection first, second;
    private final Button cardiac_button, imaging_button;
    private final RecyclerView first_recyclerview, second_recyclerview;
    private final int white_color;
    private final Answer answer;
    private final List<Answer> answers;
    private final ButtonAdapter firstButtonAdapter;
    private final ButtonAdapter secondButtonAdapter;
    private OnFURFirstVHListener onFURFirstVHListener;
    private boolean isChoiceCExisted;
    private boolean isChoiceDExisted;

    public FUpR_1st_VH(View itemView) {
        super ( itemView );
        white_color = itemView.getContext ().getResources ().getColor ( R.color.white );
        answer = new Answer();
        answers = new ArrayList<> ();
        string_choices = itemView.getContext ().getResources ().
                getStringArray ( R.array.FUpR_1th_choices);
        this.progressBar =  itemView.findViewById(R.id.progress_bar);
        this.question_view = itemView.findViewById(R.id.follow_up_results_first_question);
        this.first_question =  this.question_view.findViewById(R.id.txtQuestion);
        this.first_question.setText(itemView.getContext().getString(R.string.FUPR_first_question));
        this.button_next =  itemView.findViewById(R.id.btnNextQuestion);
        this.button_next.setOnClickListener(new OnNextButtonClickListener ());
        this.button_next.setEnabled(false);
        this.button_skip =  itemView.findViewById(R.id.btnSkipQuestion);
        this.button_skip.setOnClickListener(new OnSkipClickListener ());
        first = itemView.findViewById ( R.id.viewSelectionFirst );
        first.setOnMultiItemSelectedListener ( this );
        first.setOnClearStateListener ( this );
        second = itemView.findViewById ( R.id.viewSelectionSecond );
        second.setOnSingleItemSelectedListener ( this );
        second.setOnClearStateListener ( this );
        cardiac_button = itemView.findViewById ( R.id.fourth_choice );
        cardiac_button.setOnClickListener ( new OnCardiacButtonClickListener () );
        imaging_button = itemView.findViewById ( R.id.third_choice );
        imaging_button.setOnClickListener ( new OnImagingClickListener () );
        for (int i = 0; i < first.getNumberOfViews (); i++) {
            first.setTextToButtons ( string_choices[i], i );
        }
        imaging_button.setText ( string_choices[2] );
        cardiac_button.setText ( string_choices[3] );
        second.setTextToButtons ( string_choices[4], 0 );
        first_recyclerview = itemView.findViewById ( R.id.firstRecyclerView );
        first_recyclerview.setNestedScrollingEnabled ( false );
        firstButtonAdapter = new
                ButtonAdapter ( itemView.getContext (), "Imaging Part" );
        firstButtonAdapter.setOnOptionsClickListener ( this );
        first_recyclerview.setAdapter ( firstButtonAdapter );
        second_recyclerview = itemView.findViewById ( R.id.secondRecyclerView );
        second_recyclerview.setNestedScrollingEnabled ( false );
        secondButtonAdapter = new
                ButtonAdapter ( itemView.getContext (), "Cardiac Part" );
        secondButtonAdapter.setOnOptionsClickListener ( this );
        second_recyclerview.setAdapter ( secondButtonAdapter );
    }

    public void setOnFURFirstVHListener(OnFURFirstVHListener onFURFirstVHListener) {
        this.onFURFirstVHListener = onFURFirstVHListener;
    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println ( "FUpR_1st_VH.onMultiItemSelected" );
        int i = integer;
        setAnswerChoices ( i );
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
        }
        answers.add ( answer );
        if( answers.size () == 1 ){
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {
        System.out.println ( "FUpR_1st_VH.onMultiItemDeselected" );
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
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println ( "FUpR_1st_VH.onClearState" );
        answers.clear ();
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FUpR_1st_VH.onSingleItemSelected" );
        switch (i){
            case -1:{
                Iterator<Answer> answerIterator = answers.iterator ();
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if( answer.getChoice () != null && answer.getChoice ().equals ( "e" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

                if( answers.size () == 0 ){
                    button_next.setEnabled ( false );
                    button_next.setBackgroundResource ( R.drawable.disable_next_question );
                }
                break;
            }
            case 0:{
                DialogueBuilder dialogueBuilder = new DialogueBuilder ( itemView.getContext (),
                        "other");
                dialogueBuilder.setOnDialogListener ( this );
                dialogueBuilder.show ();

                break;
            }

        }
    }

    @Override
    public void onImagingOptionsClicked() {
        System.out.println ( "FUpR_1st_VH.onImagingOptionsClicked" );
        if( !isChoiceCExisted){
            imaging_button.setBackgroundResource ( R.drawable.answer_selected );
            imaging_button.setTextColor ( Color.parseColor ( "#000000" ) );
            Answer answer = new Answer ();
            answer.setChoice ( "c" );
            isChoiceCExisted = true;
            answers.add ( answer );
            if( answers.size () == 1 ){
                button_next.setEnabled ( true );
                button_next.setBackgroundResource ( R.drawable.enable_next_question );
            }
        }
    }

    @Override
    public void onCardiacOptionsClicked() {
        System.out.println ( "FUpR_1st_VH.onCardiacOptionsClicked" );
        if( !isChoiceDExisted ){
            cardiac_button.setBackgroundResource ( R.drawable.answer_selected );
            cardiac_button.setTextColor ( Color.parseColor ( "#000000" ) );
            Answer answer = new Answer ();
            answer.setChoice ( "d" );
            isChoiceDExisted = true;
            answers.add ( answer );
            if( answers.size () == 1 ){
                button_next.setEnabled ( true );
                button_next.setBackgroundResource ( R.drawable.enable_next_question );
            }
        }
    }

    @Override
    public void onOtherDialogDone(String otherText) {
        System.out.println ( "FUpR_1st_VH.onOtherDialogDone" );
        if( otherText.length () > 0 ){
            Answer answer = new Answer ();
            answer.setChoice ( "e" );
            answer.setOther ( otherText );
            answers.add ( answer );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        } else {
            second.getButtons ().get ( 0 ).setBackgroundResource ( R.drawable.answer_not_selected );
            second.getButtons ().get ( 0 ).setTextColor ( itemView.
                    getContext ().
                    getResources ().
                    getColor ( R.color.white ) );
        }
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpR_1st_VH" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            if( answers.size() > 0 ){
                Iterator<Answer> answerIterator = answers.iterator ();
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if( answer.getChoice () != null ){
                        switch (answer.getChoice ()){
                            case "c":{
                                answer.setSubChoices ( firstButtonAdapter.getSubChoices () );
                                break;
                            }
                            case "d":{
                                answer.setSubChoices ( secondButtonAdapter.getSubChoices () );
                                break;
                            }
                        }
                    }
                }
                onFURFirstVHListener.onNextClick ( answers );
            }
        }
    }



    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpR_1st_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFURFirstVHListener.onSkipClicked ();
        }
    }

    public interface OnFURFirstVHListener {
        void onNextClick(Answer answer);
        void onSkipClicked();
        void onNextClick(List<Answer> answers);
    }

    private class OnCardiacButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "OnCardiacButtonClickListener.onClick" );
            boolean isExisted = false;
            Iterator<Answer> answerIterator = answers.iterator ();
            while (answerIterator.hasNext ()){
                Answer answer = answerIterator.next ();
                if( answer.getChoice () != null &&
                        answer.getChoice ().equals ( "d" ) ){
                    answerIterator.remove ();
                    isChoiceDExisted = false;
                    cardiac_button.setBackgroundResource ( R.drawable.answer_not_selected );
                    cardiac_button.setTextColor ( white_color );
                    isExisted = !isExisted;
                    secondButtonAdapter.clearDataSet ();
                    break;
                }
            }
            if( !isExisted ){
                cardiac_button.setBackgroundResource ( R.drawable.answer_selected );
                cardiac_button.setTextColor ( Color.parseColor ( "#000000" ) );
                Answer answer = new Answer ();
                answer.setChoice ( "d" );
                isChoiceDExisted = true;
                answers.add ( answer );
                if( answers.size () == 1 ){
                    button_next.setEnabled ( true );
                    button_next.setBackgroundResource ( R.drawable.enable_next_question );
                }
            }else if ( answers.size () == 0 ){
                button_next.setEnabled ( false );
                button_next.setBackgroundResource ( R.drawable.disable_next_question );
            }
        }
    }

    private class OnImagingClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "OnImagingClickListener.onClick" );
            boolean isExisted = false;
            Iterator<Answer> answerIterator = answers.iterator ();
            while (answerIterator.hasNext ()){
                Answer answer = answerIterator.next ();
                if( answer.getChoice () != null &&
                        answer.getChoice ().equals ( "c" ) ){
                    answerIterator.remove ();
                    isChoiceCExisted = false;
                    imaging_button.setBackgroundResource ( R.drawable.answer_not_selected );
                    imaging_button.setTextColor ( white_color );
                    firstButtonAdapter.clearDataSet ();
                    isExisted = !isExisted;
                    break;
                }
            }
            if( !isExisted ){
                imaging_button.setBackgroundResource ( R.drawable.answer_selected );
                imaging_button.setTextColor ( Color.parseColor ( "#000000" ) );
                Answer answer = new Answer ();
                answer.setChoice ( "c" );
                isChoiceCExisted = true;
                answers.add ( answer );
                if( answers.size () == 1 ){
                    button_next.setEnabled ( true );
                    button_next.setBackgroundResource ( R.drawable.enable_next_question );
                }
            }else if ( answers.size () == 0 ){
                button_next.setEnabled ( false );
                button_next.setBackgroundResource ( R.drawable.disable_next_question );
            }
        }
    }
}
