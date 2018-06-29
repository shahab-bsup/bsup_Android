package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_14;

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

import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_1.FUpR_1st_VH;
import tk.medlynk.patient.android.Essentials.DialogueBuilder;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/4/2018.
 */

public class FUpS_14th_VH extends RecyclerView.ViewHolder implements
        ViewSelection.OnMultiItemSelectedListener,
        ViewSelection.OnClearStateListener,
        ButtonAdapter.OnOptionsClickListener {

    private final ProgressBar progressBar;
    private final String[] string_choices;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private final ViewSelection first;
    private final Button cardiac_button, imaging_button;
    private final Button other_button,noneOfTheAbove;
    private final RecyclerView first_recyclerview, second_recyclerview;
    private final int white_color;

    private  List<Answer> answers;
    private Answer answer0;
    private OnFUpSFourteenVHListener onFUpSFourteenVHListener;
    private final ButtonAdapter firstButtonAdapter;
    private final ButtonAdapter secondButtonAdapter;
    private boolean isChoiceCExisted;
    private boolean isChoiceDExisted;

    public FUpS_14th_VH(View itemView) {
        super ( itemView );
        white_color = itemView.getContext ().getResources ().getColor ( R.color.white );
        answers = new ArrayList<>();
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_fourteen_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_14th_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );

        string_choices = itemView.getContext ().getResources ().
                getStringArray ( R.array.FUpS_14th_choices);
        first = itemView.findViewById ( R.id.viewSelectionFirst );
        first.setOnMultiItemSelectedListener ( this );
        first.setOnClearStateListener ( this );
        first.setDataSet(string_choices);

        cardiac_button = itemView.findViewById ( R.id.fourth_choice );
        cardiac_button.setOnClickListener ( new OnCardiacButtonClickListener() );
        cardiac_button.setText("Cardiac investigations");

        imaging_button = itemView.findViewById ( R.id.third_choice );
        imaging_button.setOnClickListener ( new OnImagingClickListener() );
        imaging_button.setText("Imaging");

        other_button = itemView.findViewById(R.id.fifth_choice);
        other_button.setOnClickListener(new FUpS_14th_VH.OnOtherButtonClickListener());
        other_button.setText("Other");

        noneOfTheAbove=itemView.findViewById(R.id.sixth_choice);
        noneOfTheAbove.setOnClickListener(new OnNoneOfTheAboveClickListener());
        noneOfTheAbove.setText("None of the above");

        //I know this is bad! Do not blame me please:D
        //String[] strings = {string_choices[0], string_choices[1],string_choices[2],string_choices[3]};
        //String[] strings1 = {string_choices[4],string_choices[5]};

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

    public void onUpdateUI(List<Answer> answersDB) {
        for (Answer answer : answersDB) {
            switch (answer.getChoice()) {
                case "a": {
                    first.updateViewSelectionUI(0);
                    answers.add(answer);
                    break;
                }
                case "b": {
                    first.updateViewSelectionUI(1);
                    answers.add(answer);
                    break;
                }
                case "c": {
                    imaging_button.setBackgroundResource(R.drawable.answer_selected);
                    imaging_button.setTextColor(Color.parseColor("#000000"));
                    isChoiceCExisted = true;
                    answers.add(answer);
                    for (String subChoice : answer.getSubChoices()) {
                        firstButtonAdapter.getSubChoices().add(subChoice);
                    }
                    break;
                }
                case "d": {
                    cardiac_button.setBackgroundResource(R.drawable.answer_selected);
                    cardiac_button.setTextColor(Color.parseColor("#000000"));
                    isChoiceDExisted = true;
                    answers.add(answer);
                    for (String subChoice : answer.getSubChoices()) {
                        secondButtonAdapter.getSubChoices().add(subChoice);
                    }
                    break;
                }
                case "e": {
                    other_button.setBackgroundResource(R.drawable.answer_selected);
                    answers.add(answer);
                    break;
                }
                case "f":{
                    noneOfTheAbove.setBackgroundResource(R.drawable.answer_selected);
                    answer0=new Answer();
                    answer0=answer;
                    break;
                }
            }
        }
        button_next.setEnabled(true);
        button_next.setBackgroundResource(R.drawable.enable_next_question);
    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println("FUpS_14th_VH.onMultiItemSelected");

        if(noneOfTheAboveButton_isSelected()){
            noneOfTheAboveButton_deselect();
        }
        int i = integer;
        setAnswerChoices ( i );
    }

    private void setAnswerChoices(int i) {
        Answer answer = new Answer();
        switch (i) {
            case 0: {
                answer.setChoice("a");
                break;
            }
            case 1: {
                answer.setChoice("b");
                break;
            }
        }
        answers.add(answer);
        if (answers.size() == 1) {
            button_next.setEnabled(true);
            button_next.setBackgroundResource(R.drawable.enable_next_question);
        }
    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {
        int i = integer;
        Iterator<Answer> answerIterator = answers.iterator();
        switch (i) {
            case 0: {
                while (answerIterator.hasNext()) {
                    Answer answer = answerIterator.next();
                    if (answer.getChoice() != null &&
                            answer.getChoice().equals("a"))
                        answerIterator.remove();
                }

                break;
            }
            case 1: {
                while (answerIterator.hasNext()) {
                    Answer answer = answerIterator.next();
                    if (answer.getChoice() != null &&
                            answer.getChoice().equals("b")) {
                        answerIterator.remove();
                        break;
                    }
                }

                break;
            }
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println("FUpS_14th_VH.onClearState");
        answers.clear ();
    }

    @Override
    public void onImagingOptionsClicked() {
        if (!isChoiceCExisted) {
            imaging_button.setBackgroundResource(R.drawable.answer_selected);
            imaging_button.setTextColor(Color.parseColor("#000000"));
            Answer answer = new Answer();
            answer.setChoice("c");
            isChoiceCExisted = true;
            answers.add(answer);
            if (answers.size() == 1) {
                button_next.setEnabled(true);
                button_next.setBackgroundResource(R.drawable.enable_next_question);
            }
        }
    }

    @Override
    public void onCardiacOptionsClicked() {
        if (!isChoiceDExisted) {
            cardiac_button.setBackgroundResource(R.drawable.answer_selected);
            cardiac_button.setTextColor(Color.parseColor("#000000"));
            Answer answer = new Answer();
            answer.setChoice("d");
            isChoiceDExisted = true;
            answers.add(answer);
            if (answers.size() == 1) {
                button_next.setEnabled(true);
                button_next.setBackgroundResource(R.drawable.enable_next_question);
            }
        }
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if( answers.size() > 0 ){
                Iterator<Answer> answerIterator = answers.iterator ();
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next();
                    if (answer.getChoice() != null) {
                        switch (answer.getChoice()) {
                            case "c": {
                                answer.setSubChoices(firstButtonAdapter.getSubChoices());
                                break;
                            }
                            case "d": {
                                answer.setSubChoices(secondButtonAdapter.getSubChoices());
                                break;
                            }
                        }
                    }
                }
                onFUpSFourteenVHListener.onNextClick ( answers );
            }else{
                onFUpSFourteenVHListener.onNextClick ( answer0 );
            }
        }
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnFUpSFourteenVHListener(OnFUpSFourteenVHListener onFUpSFourteenVHListener) {
        this.onFUpSFourteenVHListener = onFUpSFourteenVHListener;
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_14th_VH.FUpS_14th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFUpSFourteenVHListener.onSkipClick ();
        }
    }

    public interface OnFUpSFourteenVHListener {
        void onNextClick(List<Answer> choices);
        void onSkipClick();
        void onNextClick(Answer choice);
    }

    private class OnCardiacButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "OnCardiacButtonClickListener.onClick" );

            if(noneOfTheAboveButton_isSelected()){
                noneOfTheAboveButton_deselect();
            }

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

            if(noneOfTheAboveButton_isSelected()){
                noneOfTheAboveButton_deselect();
            }

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

    private class OnOtherButtonClickListener implements View.OnClickListener, DialogueBuilder.OnOtherDialogListener {
        @Override
        public void onClick(View view) {

            if(noneOfTheAboveButton_isSelected()){
                noneOfTheAboveButton_deselect();
            }

            Iterator<Answer> answerIterator = answers.iterator();
            while (answerIterator.hasNext()) {
                Answer answer = answerIterator.next();
                if (answer.getChoice() != null && answer.getChoice().equals("e")) {
                    answerIterator.remove();
                    break;
                }
            }

            other_button.setBackgroundResource(R.drawable.answer_selected);

            DialogueBuilder dialogueBuilder = new DialogueBuilder(itemView.getContext(),
                    "other", "shahab");
            dialogueBuilder.setOnDialogListener(this);
            dialogueBuilder.show();

            if (answers.size() == 0) {
                button_next.setEnabled(false);
                button_next.setBackgroundResource(R.drawable.disable_next_question);
            }
        }

        @Override
        public void onOtherDialogDone(String otherText) {
            System.out.println("FUpR_1st_VH.onOtherDialogDone");
            if (otherText.length() > 0) {
                Answer answer = new Answer();
                answer.setChoice("e");
                answer.setOther(otherText);
                answers.add(answer);
                button_next.setEnabled(true);
                button_next.setBackgroundResource(R.drawable.enable_next_question);
            } else {
                other_button.setBackgroundResource(R.drawable.answer_not_selected);
            }

        }
    }

   private class OnNoneOfTheAboveClickListener implements View.OnClickListener {
       @Override
       public void onClick(View view){
           if (noneOfTheAboveButton_isSelected()){
               noneOfTheAboveButton_deselect();
               button_next.setEnabled(false);
               button_next.setBackgroundResource(R.drawable.disable_next_question);
           }
           else {
               answer0=new Answer();
               answer0.setChoice("f");
               answers.clear();

               noneOfTheAbove.setBackgroundResource(R.drawable.answer_selected);
               button_next.setEnabled(true);
               button_next.setBackgroundResource(R.drawable.enable_next_question);

               first.setClear();
               imaging_button.setBackgroundResource(R.drawable.answer_not_selected);
               cardiac_button.setBackgroundResource(R.drawable.answer_not_selected);
               firstButtonAdapter.clearDataSet();
               secondButtonAdapter.clearDataSet();
           }

       }

    }

    private void noneOfTheAboveButton_deselect(){
        answer0=null;
        noneOfTheAbove.setBackgroundResource(R.drawable.answer_not_selected);;
    }

    private boolean noneOfTheAboveButton_isSelected(){
        if ( answer0!=null)
            return true;
        else
            return false;
    }
}