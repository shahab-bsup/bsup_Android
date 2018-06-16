package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_3;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 2/23/2018.
 */

public class NS_3rd_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener, ViewSelection.OnClearStateListener {

    private View question_view, lasting_row;
    private TextView question;
    private Button button_next, button_skip;
    private ViewSelection choice;
    private String[] string_choices;
    private final CheckBox first_checkbox, second_checkbox, third_checkbox, fourth_checkbox;
    private final AppCompatEditText first_input, second_input, third_input, fourth_input;
    private List<AppCompatEditText> inputs = new ArrayList<>();
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private Answer answer;
    private int duration;
    private OnThirdNSVHListener onThirdNSVHListener;

    ProgressBar progressBar;

    public NS_3rd_VH(View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar);
        question_view = itemView.findViewById(R.id.new_symptom_third_question);
        question = question_view.findViewById(R.id.txtQuestion);
        question.setText(R.string.new_symptom_third_question);
        button_next = itemView.findViewById(R.id.btnNextQuestion);
        button_next.setOnClickListener(new OnNextClickListener());
        button_next.setEnabled(false);
        button_skip = itemView.findViewById(R.id.btnSkipQuestion);
        button_skip.setOnClickListener(new OnSkipClickListener());
        choice = itemView.findViewById(R.id.viewSelectionChoices);
        choice.setOnSingleItemSelectedListener(this);
        choice.setOnClearStateListener(this);
        string_choices = itemView.getContext().getResources().getStringArray(R.array.question_3_choices);
        for (int i = 0; i < choice.getNumberOfViews(); i++) {
            choice.setTextToButtons(string_choices[i], i);
        }
        first_input = itemView.findViewById(R.id.years_ago_input);
        second_input = itemView.findViewById(R.id.month_ago_input);
        third_input = itemView.findViewById(R.id.weeks_ago_input);
        fourth_input = itemView.findViewById(R.id.days_ago_input);

        inputs.add(first_input);
        inputs.add(second_input);
        inputs.add(third_input);
        inputs.add(fourth_input);

        first_checkbox = itemView.findViewById(R.id.first);
        first_checkbox.setOnCheckedChangeListener(new OnYearsCheckedChangeListener ());
        second_checkbox = itemView.findViewById(R.id.second);
        second_checkbox.setOnCheckedChangeListener(new OnMonthCheckedChangeListener ());
        third_checkbox = itemView.findViewById(R.id.third);
        third_checkbox.setOnCheckedChangeListener(new OnWeeksCheckedChangeListener ());
        fourth_checkbox = itemView.findViewById(R.id.fourth);
        fourth_checkbox.setOnCheckedChangeListener(new OnDaysCheckedChangeListener ());

        checkBoxes.add(first_checkbox);
        checkBoxes.add(second_checkbox);
        checkBoxes.add(third_checkbox);
        checkBoxes.add(fourth_checkbox);

    }

    public void onUpdateUI(Answer answerDB) {
            switch (answerDB.getChoice ()){
                case "a":{
                    first_checkbox.setChecked ( true );
                    yearsCheckedChanged ( true );
                    first_input.setText ( String.valueOf ( answerDB.getDuration () ) );
                    break;
                }
                case "b":{
                    second_checkbox.setChecked ( true );
                    monthCheckedChanged ( true );
                    second_input.setText ( String.valueOf ( answerDB.getDuration () ) );
                    break;
                }
                case "c":{
                    third_checkbox.setChecked ( true );
                    weeksCheckedChanged ( true );
                    third_input.setText ( String.valueOf ( answerDB.getDuration () ) );
                    break;
                }
                case "d":{
                    fourth_checkbox.setChecked ( true );
                    daysCheckedChanged ( true );
                    fourth_input.setText ( String.valueOf ( answerDB.getDuration () ) );
                    break;
                }
                case "e":{
                    choice.previewOfDBResult ( true, true, 0 );
                    break;
                }
                case "f":{
                    choice.previewOfDBResult ( true, true, 1 );
                    break;
                }
            }
    }

    public void setOnThirdNSVHListener(OnThirdNSVHListener onThirdNSVHListener) {
        this.onThirdNSVHListener = onThirdNSVHListener;
    }

    public void setProgressBarVisibilityStatus(int status) {
        this.progressBar.setVisibility(status);
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println("NS_3rd_VH.onSingleItemSelected");
        if (i != -1) {
            answer = new Answer();
            duration = 0;
            if (i == 0) {
                for (AppCompatEditText input : inputs) {
                    input.setText("");
                    input.setError(null);
                }
                for (CheckBox checkBox : checkBoxes) {
                    checkBox.setChecked(false);
                }
                answer.setChoice("e");
            } else if (i == 1) {
                for (AppCompatEditText input : inputs) {
                    input.setText("");
                    input.setError(null);
                }
                for (CheckBox checkBox : checkBoxes) {
                    checkBox.setChecked(false);
                }
                answer.setChoice("f");
            }
            button_next.setEnabled(true);
            button_next.setBackgroundResource(R.drawable.enable_next_question);
        } else if (i == -1) {
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println("NS_3rd_VH.onClearState");

    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            boolean hasError = false;
            if (answer.getChoice() != null) {
                if (answer.getChoice().equals("e") || answer.getChoice().equals("f")) {
                    onThirdNSVHListener.onNextClicked(answer);
                } else {
                    switch (answer.getChoice()){
                        case "a":{
                            if( TextUtils.isEmpty(first_input.getText().toString()) ){
                                hasError = true;
                                first_input.setError("Fill this field!");
                                first_input.requestFocus();
                            }

                            break;
                        }
                        case "b":{
                            if( TextUtils.isEmpty(second_input.getText().toString()) ){
                                hasError = true;
                                second_input.setError("Fill this field!");
                                second_input.requestFocus();

                            }

                            break;
                        }
                        case "c":{
                            if( TextUtils.isEmpty(third_input.getText().toString()) ){
                                hasError = true;
                                third_input.setError("Fill this field!");
                                third_input.requestFocus();

                            }

                            break;
                        }
                        case "d":{
                            if( TextUtils.isEmpty(fourth_input.getText().toString()) ){
                                hasError = true;
                                fourth_input.setError("Fill this field!");
                                fourth_input.requestFocus();

                            }

                            break;
                        }
                    }
                    if( !hasError ){
                        for (AppCompatEditText input : inputs) {
                            if( input.length() > 0 ){
                                answer.setDuration(Integer.parseInt(input.getText().toString()));
                                break;
                            }
                        }
                        onThirdNSVHListener.onNextClicked(answer);
                    }
                }
            }
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            onThirdNSVHListener.onSkipClicked();
        }
    }

    public interface OnThirdNSVHListener {
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }

    private class OnYearsCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            yearsCheckedChanged ( b );
        }
    }

    private void yearsCheckedChanged(boolean b) {
        if (b) {
            choice.setClear();
            second_checkbox.setChecked(false);
            third_checkbox.setChecked(false);
            fourth_checkbox.setChecked(false);
            second_input.setText("");
            second_input.setError(null);
            third_input.setText("");
            third_input.setError(null);
            fourth_input.setText("");
            fourth_input.setError(null);
            answer = new Answer ();
            answer.setChoice("a");
            button_next.setEnabled(true);
            button_next.setBackgroundResource( R.drawable.enable_next_question);
        } else {
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
            answer = new Answer();
        }
    }

    private class OnMonthCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            monthCheckedChanged ( b );
        }
    }

    private void monthCheckedChanged(boolean b) {
        if (b) {
            choice.setClear();
            first_checkbox.setChecked(false);
            third_checkbox.setChecked(false);
            fourth_checkbox.setChecked(false);
            first_input.setText("");
            first_input.setError(null);
            third_input.setText("");
            third_input.setError(null);
            fourth_input.setText("");
            fourth_input.setError(null);
            answer = new Answer ();
            answer.setChoice("b");
            button_next.setEnabled(true);
            button_next.setBackgroundResource( R.drawable.enable_next_question);
        } else {
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
            answer = new Answer();
        }
    }

    private class OnWeeksCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            weeksCheckedChanged ( b );
        }
    }

    private void weeksCheckedChanged(boolean b) {
        if (b) {
            choice.setClear();
            first_checkbox.setChecked(false);
            second_checkbox.setChecked(false);
            fourth_checkbox.setChecked(false);
            first_input.setText("");
            first_input.setError(null);
            second_input.setText("");
            second_input.setError(null);
            fourth_input.setText("");
            fourth_input.setError(null);
            answer = new Answer ();
            answer.setChoice("c");
            button_next.setEnabled(true);
            button_next.setBackgroundResource( R.drawable.enable_next_question);
        } else {
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
            answer = new Answer();
        }
    }

    private class OnDaysCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            daysCheckedChanged ( b );
        }
    }

    private void daysCheckedChanged(boolean b) {
        if (b) {
            choice.setClear();
            first_checkbox.setChecked(false);
            second_checkbox.setChecked(false);
            third_checkbox.setChecked(false);
            first_input.setText("");
            first_input.setError(null);
            second_input.setText("");
            second_input.setError(null);
            third_input.setText("");
            third_input.setError(null);
            answer = new Answer ();
            answer.setChoice("d");
            button_next.setEnabled(true);
            button_next.setBackgroundResource( R.drawable.enable_next_question);
        } else {
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
            answer = new Answer();
        }
    }
}
