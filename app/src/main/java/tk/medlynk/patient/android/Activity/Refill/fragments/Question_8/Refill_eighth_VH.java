package tk.medlynk.patient.android.Activity.Refill.fragments.Question_8;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.medlynk.shahab.myviewselection.ViewSelection;
import com.medlynk.shahab.myviewselection.ViewSelection.OnClearStateListener;
import com.medlynk.shahab.myviewselection.ViewSelection.OnSingleItemSelectedListener;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tk.medlynk.patient.android.Model.Answer;

public class Refill_eighth_VH extends ViewHolder implements
        OnSingleItemSelectedListener,
        OnClearStateListener {

    private ViewSelection first_choice;
    private Button button_next, button_skip;
    private OnRefillEighthVHListener onRefillEighthVHListener;
    private TextView question;
    private View question_view;
    private String[] string_choices;
    private final Answer answer = new Answer();
    private final List<Answer> answers = new ArrayList<>();
    private final CheckBox first_checkbox, second_checkbox, third_checkbox, fourth_checkbox;
    private final AppCompatEditText first_input, second_input, third_input, fourth_input;
    private List<AppCompatEditText> inputs = new ArrayList<>();
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private ProgressBar progressBar;

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility(status);
    }


    private class OnNextClickListener implements OnClickListener {
        public void onClick(View view) {
            System.out.println("Refill_eighth_VH");
            System.out.println("OnNextClickListener.onClick");
            if( answers.size() > 0 ){
                boolean hasError = false;
                Iterator<Answer> answerIterator = answers.iterator();
                while (answerIterator.hasNext()){
                    Answer answer = answerIterator.next();
//                    if( answer.getChoice () != null ){
//                        switch (answer.getChoice ()){
//                            case "c":{
//                                if( first_input.length() == 0 ){
//                                    hasError = true;
//                                    first_input.setError("Fill this field!");
//                                }else{
//                                    answer.setReading(first_input.getText().toString());
//                                }
//
//                                break;
//                            }
//                            case "d":{
//
//                                break;
//                            }
//                            case "e":{
//
//                                break;
//                            }
//                            case "f":{
//
//                                break;
//                            }
//                        }
//                    }
                    if( answer.getChoice() != null && answer.getChoice().equals("c") ){
                        if( first_input.length() == 0 ){
                            hasError = true;
                            first_input.setError("Fill this field!");
                        }else{
                            answer.setReading(first_input.getText().toString());
                        }
                    }
                    if( answer.getChoice() != null && answer.getChoice().equals("d") ){
                        if( second_input.length() == 0 ){
                            hasError = true;
                            second_input.setError("Fill this field!");
                        }else{
                            answer.setReading(second_input.getText().toString());
                        }
                    }
                    if( answer.getChoice() != null && answer.getChoice().equals("e") ){
                        if( third_input.length() == 0 ){
                            hasError = true;
                            third_input.setError("Fill this field!");
                        }else{
                            answer.setReading(third_input.getText().toString());
                        }
                    }
                    if( answer.getChoice() != null && answer.getChoice().equals("f") ){
                        if( fourth_input.length() == 0 ){
                            hasError = true;
                            fourth_input.setError("Fill this field!");
                        }else{
                            answer.setReading(fourth_input.getText().toString());
                        }
                    }
                }
                if( !hasError ){
                    onRefillEighthVHListener.onNextClicked(answers);
                }
            }else{
                onRefillEighthVHListener.onNextClicked(answer);
            }
        }
    }

    public interface OnRefillEighthVHListener {
        void onNextClicked(List<Answer> answers);
        void onSkipClicked();
        void onNextClicked(Answer answer);
    }

    private class OnSkipClickListener implements OnClickListener {
        public void onClick(View view) {
            System.out.println("Refill_eighth_VH");
            System.out.println("OnSkipClickListener.onClick");
            onRefillEighthVHListener.onSkipClicked();
        }
    }

    public Refill_eighth_VH(View itemView) {
        super(itemView);
        progressBar =  itemView.findViewById( R.id.progress_bar);
        this.question_view = itemView.findViewById(R.id.refill_eighth_question);
        question = ( this.question_view.findViewById(R.id.txtQuestion));
        this.question.setText(R.string.refill_eight_question);
        this.button_next = itemView.findViewById(R.id.btnNextQuestion);
        this.button_next.setOnClickListener(new OnNextClickListener());
        button_next.setEnabled ( false );
        this.button_skip = itemView.findViewById(R.id.btnSkipQuestion);
        this.button_skip.setOnClickListener(new OnSkipClickListener());
        this.first_choice = itemView.findViewById(R.id.viewSelectionChoicesFirst);
        this.first_choice.setOnSingleItemSelectedListener(this);
        this.string_choices = itemView.getContext().getResources().getStringArray(R.array.refill_sixth_question_choices);
        String[] strings = {string_choices[0], string_choices[1]};
        first_choice.setDataSet ( strings );
        this.first_choice.setOnClearStateListener(this);

        first_input = itemView.findViewById(R.id.highest_glucometer_input);
        second_input = itemView.findViewById(R.id.lowest_glucometer_input);
        third_input = itemView.findViewById(R.id.average_glucometer_input);
        fourth_input = itemView.findViewById(R.id.last_hemoglobin_input);

        inputs.add(first_input);
        inputs.add(second_input);
        inputs.add(third_input);
        inputs.add(fourth_input);

        first_checkbox = itemView.findViewById(R.id.first);
        first_checkbox.setOnCheckedChangeListener(new OnFirstCheckedChangeListener());
        second_checkbox = itemView.findViewById(R.id.second);
        second_checkbox.setOnCheckedChangeListener(new OnSecondCheckedChangeListener());
        third_checkbox = itemView.findViewById(R.id.third);
        third_checkbox.setOnCheckedChangeListener(new OnThirdCheckedChangeListener());
        fourth_checkbox = itemView.findViewById(R.id.fourth);
        fourth_checkbox.setOnCheckedChangeListener(new OnFourthCheckedChangeListener());

        checkBoxes.add(first_checkbox);
        checkBoxes.add(second_checkbox);
        checkBoxes.add(third_checkbox);
        checkBoxes.add(fourth_checkbox);
    }

    public void setOnRefillEighthVHListener(OnRefillEighthVHListener onRefillEighthVHListener) {
        this.onRefillEighthVHListener = onRefillEighthVHListener;
    }

    public void onSingleItemSelected(View view, int i) {
        System.out.println("Refill_eighth_VH.onSingleItemSelected");
        if( i == -1 ){
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
        }else if ( i == 0 ){
            for (AppCompatEditText input : inputs) {
                input.setText("");
                input.setError ( null );
            }
            for (CheckBox checkBox : checkBoxes) {
                checkBox.setChecked(false);
            }
            answers.clear();
            button_next.setEnabled(true);
            button_next.setBackgroundResource(R.drawable.enable_next_question);
            answer.setChoice("a");
        }else if ( i == 1 ){
            for (AppCompatEditText input : inputs) {
                input.setText("");
                input.setError ( null );
            }
            for (CheckBox checkBox : checkBoxes) {
                checkBox.setChecked(false);
            }
            answers.clear();
            button_next.setEnabled(true);
            button_next.setBackgroundResource(R.drawable.enable_next_question);
            answer.setChoice("b");
        }
    }

    public void onClearState(View view) {
        System.out.println("Refill_eighth_VH.onClearState");

    }

    private class OnFirstCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Iterator<Answer> answerIterator = answers.iterator();
            if( b ){
                first_choice.setClear();
                boolean isExisted = false;
                while (answerIterator.hasNext()){
                    Answer answer = answerIterator.next();
                    if( answer.getChoice() != null && answer.getChoice().equals("c") ){
                        isExisted = true;
                        break;
                    }
                }
                if( !isExisted ){
                    Answer answer = new Answer();
                    answer.setChoice("c");
                    answers.add(answer);
                    if( answers.size() > 0 ){
                        button_next.setEnabled(true);
                        button_next.setBackgroundResource(R.drawable.enable_next_question);
                    }
                }
            }else{
                first_input.setError ( null );
                while (answerIterator.hasNext()){
                    Answer answer = answerIterator.next();
                    if( answer.getChoice() != null && answer.getChoice().equals("c") ){
                        answerIterator.remove();
                        break;
                    }
                }
                if( answers.size() == 0 ){
                    button_next.setEnabled(false);
                    button_next.setBackgroundResource(R.drawable.disable_next_question);
                }
            }
        }
    }

    private class OnSecondCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Iterator<Answer> answerIterator = answers.iterator();
            if( b ){
                first_choice.setClear();
                boolean isExisted = false;
                while (answerIterator.hasNext()){
                    Answer answer = answerIterator.next();
                    if( answer.getChoice() != null && answer.getChoice().equals("d") ){
                        isExisted = true;
                        break;
                    }
                }
                if( !isExisted ){
                    Answer answer = new Answer();
                    answer.setChoice("d");
                    answers.add(answer);
                    if( answers.size() > 0 ){
                        button_next.setEnabled(true);
                        button_next.setBackgroundResource(R.drawable.enable_next_question);
                    }
                }
            }else{
                second_input.setError ( null );
                while (answerIterator.hasNext()){
                    Answer answer = answerIterator.next();
                    if( answer.getChoice() != null && answer.getChoice().equals("d") ){
                        answerIterator.remove();
                        break;
                    }
                }
                if( answers.size() == 0 ){
                    button_next.setEnabled(false);
                    button_next.setBackgroundResource(R.drawable.disable_next_question);
                }
            }
        }
    }

    private class OnThirdCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Iterator<Answer> answerIterator = answers.iterator();
            if( b ){
                first_choice.setClear();
                boolean isExisted = false;
                while (answerIterator.hasNext()){
                    Answer answer = answerIterator.next();
                    if( answer.getChoice() != null && answer.getChoice().equals("e") ){
                        isExisted = true;
                        break;
                    }
                }
                if( !isExisted ){
                    Answer answer = new Answer();
                    answer.setChoice("e");
                    answers.add(answer);
                    if( answers.size() > 0 ){
                        button_next.setEnabled(true);
                        button_next.setBackgroundResource(R.drawable.enable_next_question);
                    }
                }
            }else{
                third_input.setError ( null );
                while (answerIterator.hasNext()){
                    Answer answer = answerIterator.next();
                    if( answer.getChoice() != null && answer.getChoice().equals("e") ){
                        answerIterator.remove();
                        break;
                    }
                }
                if( answers.size() == 0 ){
                    button_next.setEnabled(false);
                    button_next.setBackgroundResource(R.drawable.disable_next_question);
                }
            }
        }
    }

    private class OnFourthCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Iterator<Answer> answerIterator = answers.iterator();
            if( b ){
                first_choice.setClear();
                boolean isExisted = false;
                while (answerIterator.hasNext()){
                    Answer answer = answerIterator.next();
                    if( answer.getChoice() != null && answer.getChoice().equals("f") ){
                        isExisted = true;
                        break;
                    }
                }
                if( !isExisted ){
                    Answer answer = new Answer();
                    answer.setChoice("f");
                    answers.add(answer);
                    if( answers.size() > 0 ){
                        button_next.setEnabled(true);
                        button_next.setBackgroundResource(R.drawable.enable_next_question);
                    }
                }
            }else{
                fourth_input.setError ( null );
                while (answerIterator.hasNext()){
                    Answer answer = answerIterator.next();
                    if( answer.getChoice() != null && answer.getChoice().equals("f") ){
                        answerIterator.remove();
                        break;
                    }
                }
                if( answers.size() == 0 ){
                    button_next.setEnabled(false);
                    button_next.setBackgroundResource(R.drawable.disable_next_question);
                }
            }
        }
    }
}
