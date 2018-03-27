package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_14;

import android.graphics.drawable.Drawable;
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

import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/4/2018.
 */

public class FUpS_14th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnMultiItemSelectedListener, ViewSelection.OnClearStateListener, ViewSelection.OnSingleItemSelectedListener {

    private final ViewSelection first, sub_first, second, sub_second, third, fourth;
    private final String[] string_choices;
    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private final Answer choice;
    private final List<Answer> choices;
    private OnFUpSFourteenVHListener onFUpSFourteenVHListener;

    public FUpS_14th_VH(View itemView) {
        super ( itemView );
        choice = new Answer();
        choices = new ArrayList<>();
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_fourteen_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_14th_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        first = itemView.findViewById ( R.id.viewSelectionFirst );
        first.setOnMultiItemSelectedListener ( this );
        first.setOnClearStateListener ( this );
        sub_first = itemView.findViewById ( R.id.viewSelectionSubFirst );
        sub_first.setOnMultiItemSelectedListener ( this );
        sub_first.setOnClearStateListener ( this );
        second = itemView.findViewById ( R.id.viewSelectionSecond );
        second.setOnSingleItemSelectedListener ( this );
        second.setOnClearStateListener ( this );
        sub_second = itemView.findViewById ( R.id.viewSelectionSubSecond );
        sub_second.setOnMultiItemSelectedListener ( this );
        sub_second.setOnClearStateListener( this );
        third = itemView.findViewById ( R.id.viewSelectionThird );
        third.setOnSingleItemSelectedListener ( this );
        third.setOnClearStateListener(this);
        fourth = itemView.findViewById(R.id.viewSelectionFourth);
        fourth.setOnSingleItemSelectedListener(this);
        fourth.setOnClearStateListener( this );
        string_choices = itemView.getContext ().
                getResources ().
                getStringArray ( R.array.FUpS_14th_choices);

        for (int i = 0; i < first.getNumberOfViews (); i++) {
            first.setTextToButtons ( string_choices[i], i );
        }

        for (int i = 0; i < sub_first.getNumberOfViews (); i++) {
            sub_first.setTextToButtons ( string_choices[i+3], i );
        }

        second.setTextToButtons ( string_choices[9], 0 );
        for (int i = 0; i < sub_second.getNumberOfViews (); i++) {
            sub_second.setTextToButtons ( string_choices[i + 10], i );
        }
        third.setTextToButtons ( string_choices[14], 0 );
        fourth.setTextToButtons(string_choices[15], 0 );
    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println("FUpS_14th_VH.onMultiItemSelected");
        int i = integer;
        if( view.getId() == first.getId() ){
            if( integer == 2 ){
                boolean isExisted = false;
                Answer answer = new Answer();
                answer.setChoice("c");
                for (Answer choice1 : choices) {
                    if( choice1.getChoice().equals("c") ){
                        isExisted = true;
                        break;
                    }
                }
                if( isExisted ){
                    first.getButtons().get(2).setBackgroundDrawable(itemView.getContext().getResources().getDrawable(R.drawable.answer_not_selected));
                    first.getButtons().get(2).setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                    onMultiItemDeselected(view, integer);
                }else{
                    setFirstAnswerChoices(i);
                }
            }else{
                setFirstAnswerChoices(i);
            }
        } else if( view.getId() == sub_first.getId() ){
            boolean isExisted = false;
            Answer answer = new Answer();
            answer.setChoice("c");
            for (Answer choice1 : choices) {
                if( choice1.getChoice().equals("c") ){
                    isExisted = true;
                    break;
                }
            }
            if( !isExisted ){
                List<String> strings = new ArrayList<>();
                answer.setSubChoices(strings);
                choices.add(answer);
                Drawable drawable = itemView.
                        getResources().
                        getDrawable(R.drawable.answer_selected);
                first.getButtons().get(2).setBackgroundDrawable(drawable);
                first.getButtons().get(2).setTextColor(itemView.getContext().getResources().getColor(R.color.white));
            }
             setSubFirstAnswerChoices(i);
        }else if ( view.getId() == sub_second.getId() ){
            boolean isExisted = false;
            Answer answer = new Answer();
            answer.setChoice("d");
            for (Answer choice : choices) {
                if( choice.getChoice()!= null && choice.getChoice().equals("d") ){
                    isExisted = true;
                    break;
                }
            }
            if( !isExisted ){
                List<String> strings = new ArrayList<>();
                answer.setSubChoices(strings);
                choices.add(answer);
                second.getButtons().get(0).setBackgroundDrawable(itemView.getContext().getResources().getDrawable(R.drawable.answer_selected));
                second.getButtons().get(0).setTextColor(itemView.getContext().getResources().getColor(R.color.white));
            }
            setSubSecondAnswerChoices(i);
        }
    }

    private void setSubSecondAnswerChoices(int i) {
        System.out.println("FUpS_14th_VH.setSubSecondAnswerChoices");
        Iterator<Answer> answerIterator = choices.iterator();
        while (answerIterator.hasNext()){
            Answer answer = answerIterator.next();
            if( answer.getChoice() != null && answer.getChoice().equals("d") ){
                switch (i){
                    case 0:{
                        answer.getSubChoices().add("1");

                        break;
                    }
                    case 1:{
                        answer.getSubChoices().add("2");

                        break;
                    }
                    case 2:{
                        answer.getSubChoices().add("3");

                        break;
                    }
                    case 3:{
                        answer.getSubChoices().add("4");

                        break;
                    }
                }
                break;
            }
        }
    }

    private void setSubFirstAnswerChoices(int i) {
        System.out.println("FUpS_14th_VH.setSubFirstAnswerChoices");
        Iterator<Answer> answerIterator = choices.iterator();
        while (answerIterator.hasNext()){
            Answer answer = answerIterator.next();
            if( answer.getChoice() != null && answer.getChoice().equals("c") ){
                switch (i){
                    case 0:{
                        answer.getSubChoices().add("1");

                        break;
                    }
                    case 1:{
                        answer.getSubChoices().add("2");

                        break;
                    }
                    case 2:{
                        answer.getSubChoices().add("3");

                        break;
                    }
                    case 3:{
                        answer.getSubChoices().add("4");

                        break;
                    }
                    case 4:{
                        answer.getSubChoices().add("5");

                        break;
                    }
                    case 5:{
                        answer.getSubChoices().add("6");

                        break;
                    }
                }
                break;
            }
        }
    }

    private void setFirstAnswerChoices(int i) {
        System.out.println("FUpS_14th_VH.setFirstAnswerChoices");
        Answer answer = new Answer();
        switch (i){
            case 0:{
                answer.setChoice("a");

                break;
            }
            case 1:{
                answer.setChoice("b");

                break;
            }
            case 2:{
                answer.setChoice("c");
                List<String> strings = new ArrayList<>();
                answer.setSubChoices(strings);
                break;
            }
        }
        choices.add(answer);
        if( choices.size() == 1 ){
            button_next.setBackgroundResource( R.drawable.enable_next_question );
            button_next.setEnabled( true );
        }
    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {
        System.out.println("FUpS_14th_VH.onMultiItemDeselected");
        System.out.println("view = [" + view + "], integer = [" + integer + "]");
        Iterator<Answer> answerIterator = choices.iterator();
        int i = integer;
        if( view.getId() == first.getId() ){
            if( integer == 2 ){
                sub_first.setClear();
            }
            switch (i) {
                case 0: {
                    while (answerIterator.hasNext()) {
                        Answer answer = answerIterator.next();
                        if (answer.getChoice() != null &&
                                answer.getChoice().equals("a")){
                            answerIterator.remove();
                            break;
                        }
                    }
                    break;
                }
                case 1:{
                    while (answerIterator.hasNext()) {
                        Answer answer = answerIterator.next();
                        if (answer.getChoice() != null &&
                                answer.getChoice().equals("b")){
                            answerIterator.remove();
                            break;
                        }
                    }
                    break;
                }
                case 2:{
                    while (answerIterator.hasNext()) {
                        Answer answer = answerIterator.next();
                        if (answer.getChoice() != null &&
                                answer.getChoice().equals("c")){
                            answerIterator.remove();
                            break;
                        }
                    }
                    break;
                }
            }
        }

        if( view.getId() == sub_first.getId() ){
            while (answerIterator.hasNext()){
                Answer answer = answerIterator.next();
                if( answer.getChoice() != null && answer.getChoice().equals("c") ){
                    Iterator<String> stringIterator = answer.getSubChoices().iterator();
                    while (stringIterator.hasNext()){
                        String s = stringIterator.next();
                        if( s != null && s.equals(String.valueOf(integer + 1)) ){
                            stringIterator.remove();
                        }
                    }
                }
            }
        }
        if(view.getId() == sub_second.getId()){
            while (answerIterator.hasNext()){
                Answer answer = answerIterator.next();
                if( answer.getChoice() != null && answer.getChoice().equals("d") ){
                    Iterator<String> stringIterator = answer.getSubChoices().iterator();
                    while (stringIterator.hasNext()){
                        String s = stringIterator.next();
                        if( s != null && s.equals(String.valueOf(integer + 1)) ){
                            stringIterator.remove();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println("FUpS_14th_VH.onClearState");
        int id = view.getId();
        if( id == fourth.getId()){
            choices.clear();
        }
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println("FUpS_14th_VH.onSingleItemSelected");
        if( view.getId() == fourth.getId() ){
            if(i == 0 ){
                choice.setChoice("f");
                first.setClear();
                second.setClear();
                third.setClear();
                button_next.setEnabled(true);
                button_next.setBackgroundResource(R.drawable.enable_next_question);
            } else if ( i == -1 ){
                button_next.setEnabled(false);
                button_next.setBackgroundResource(R.drawable.disable_next_question);
            }
        }else if ( view.getId() == second.getId() ){
            boolean isExisted = false;
            Answer answer = new Answer();
            answer.setChoice("d");
            for (Answer choice1 : choices) {
                if( choice1.getChoice().equals("d") ){
                    isExisted = true;
                    break;
                }
            }
            if( isExisted ){
                second.getButtons().get(0).setBackgroundDrawable(itemView.getContext().getResources().getDrawable(R.drawable.answer_not_selected));
                second.getButtons().get(0).setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                sub_second.setClear();
                Iterator<Answer> answerIterator = choices.iterator();
                while (answerIterator.hasNext()){
                    Answer answer1 = answerIterator.next();
                    if( answer1.getChoice() != null && answer1.getChoice().equals("d") ){
                        answerIterator.remove();
                    }
                }
            }else{
                List<String> strings = new ArrayList<>();
                answer.setSubChoices(strings);
                choices.add(answer);
            }

            if( choices.size() == 1 ){
                button_next.setBackgroundResource(R.drawable.enable_next_question);
                button_next.setEnabled(true);
            }
        }
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_14th_VH.FUpS_14th_VH" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            if( choices.size() > 0 ){
                onFUpSFourteenVHListener.onNextClick (choices);
            }else{
                onFUpSFourteenVHListener.onNextClick (choice);
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
}
