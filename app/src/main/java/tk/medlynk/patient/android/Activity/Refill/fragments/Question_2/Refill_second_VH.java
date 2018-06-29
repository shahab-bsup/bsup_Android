package tk.medlynk.patient.android.Activity.Refill.fragments.Question_2;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.medlynk.shahab.myviewselection.ViewSelection;
import com.medlynk.shahab.myviewselection.ViewSelection.OnSingleItemSelectedListener;

import tk.medlynk.patient.android.Essentials.Utils;
import tk.medlynk.patient.android.Model.Answer;
import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

public class Refill_second_VH extends ViewHolder implements OnSingleItemSelectedListener, ViewSelection.OnClearStateListener {

    private final Button button_next;
    private final Button button_skip;
    private final ViewSelection first, second;
    private OnRefillSecondQuestionClickListener onRefillSecondQuestionClickListener;
    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final String[] string_choices;
    //This is so stupid way!
    private final View duration_layout;
    private final AppCompatEditText duration_input;
    private final Button days, weeks, months, years;
    private List<Button> buttons = new ArrayList<>();
    private Answer answer ;

    public Refill_second_VH(View itemView) {
        super(itemView);
        this.progressBar =  itemView.findViewById(R.id.progress_bar);
        this.question_view = itemView.findViewById(R.id.refill_second_question);
        this.second_question =  this.question_view.findViewById(R.id.txtQuestion);
        this.second_question.setText(itemView.getContext().getString(R.string.refill_second_question));
        this.button_next =  itemView.findViewById(R.id.btnNextQuestion);
        this.button_next.setOnClickListener(new OnNextButtonClickListener());
        this.button_next.setEnabled(false);
        this.button_skip =  itemView.findViewById(R.id.btnSkipQuestion);
        this.button_skip.setOnClickListener(new OnSkipClickListener());
        this.first =  itemView.findViewById(R.id.viewSelectionChoicesFirst);
        this.second =  itemView.findViewById(R.id.viewSelectionChoicesSecond);
        this.first.setOnSingleItemSelectedListener(this);
        first.setOnClearStateListener(this);
        second.setOnSingleItemSelectedListener(this);
        second.setOnClearStateListener(this);
        this.string_choices = itemView.getContext().getResources().getStringArray(R.array.refill_second_question_choices);

        //I know this is bad! Do not blame me please:D
        String[] strings = {string_choices[0],string_choices[1],
                string_choices[2],string_choices[3]};
        first.setDataSet ( strings );
        //I know this is bad! Do not blame me please:D
        String[] strings1 = {string_choices[5]};
        second.setDataSet ( strings1 );
        duration_layout = itemView.findViewById(R.id.refill_second);
        days = itemView.findViewById(R.id.days);
        days.setOnClickListener(new OnDaysClickListener());
        months = itemView.findViewById(R.id.months);
        months.setOnClickListener(new OnMonthsClickListener());
        weeks = itemView.findViewById(R.id.weeks);
        weeks.setOnClickListener(new OnWeeksClickListener());
        years = itemView.findViewById(R.id.years);
        years.setOnClickListener(new OnYearsClickListener());
        buttons.add(days);
        buttons.add(weeks);
        buttons.add(months);
        buttons.add(years);
        duration_input = itemView.findViewById(R.id.duration_input);
        duration_input.setOnFocusChangeListener(new OnDurationFocusChangedListener());

        answer = new Answer();
    }

    public void onUpdateUI(Answer answerDB) {
        switch (answerDB.getChoice()){
            case "a":{
                first.updateViewSelectionUI(0);
                this.answer=answerDB;
                break;
            }
            case "b":{
                first.updateViewSelectionUI(1);
                this.answer=answerDB;
                break;
            }
            case "c":{
                first.updateViewSelectionUI(2);
                this.answer=answerDB;
                break;
            }
            case "d":{
                first.updateViewSelectionUI(3);
                this.answer=answerDB;
                break;
            }
            case "e":{
                duration_input.setText(String.valueOf(answerDB.getSince()));
                days.callOnClick();
                break;
            }
            case "f":{
                duration_input.setText(String.valueOf(answerDB.getSince()));
                weeks.callOnClick();
                break;
            }
            case "g":{
                duration_input.setText(String.valueOf(answerDB.getSince()));
                months.callOnClick();
                break;
            }
            case "h":{
                duration_input.setText(String.valueOf(answerDB.getSince()));
                years.callOnClick();
                break;
            }
            case "i":{
                second.updateViewSelectionUI(0);
                this.answer=answerDB;
                break;
            }
        }

        button_next.setEnabled(true);
        button_next.setBackgroundResource(R.drawable.enable_next_question);
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onClearState(View view) {
        System.out.println("Refill_second_VH.onClearState");

    }

    private class OnNextButtonClickListener implements OnClickListener {
        public void onClick(View view) {
            System.out.println("Refill_second_VH");
            System.out.println("OnNextButtonClickListener.onClick");
            if( answer.getChoice().equals("e") || answer.getChoice().equals("f")
                    || answer.getChoice().equals("g") || answer.getChoice().equals("h")){
                if( TextUtils.isEmpty(duration_input.getText().toString()) ){
                    duration_input.setError("Fill this field!");
                }else{
                    answer.setSince(Integer.parseInt(duration_input.getText().toString()));
                    onRefillSecondQuestionClickListener.onNextClicked(answer);
                }
            }else{
                onRefillSecondQuestionClickListener.onNextClicked(answer);
            }
        }

    }
    public interface OnRefillSecondQuestionClickListener {
        void onNextClicked(Answer answer);
        void onSkipClicked();
    }
    private class OnSkipClickListener implements OnClickListener {

        public void onClick(View view) {
            System.out.println("Refill_second_VH");
            System.out.println("OnSkipClickListener.onClick");
            onRefillSecondQuestionClickListener.onSkipClicked();
        }

    }

    public void setOnRefillSecondQuestionClickListener(OnRefillSecondQuestionClickListener onRefillSecondQuestionClickListener) {
        this.onRefillSecondQuestionClickListener = onRefillSecondQuestionClickListener;
    }

    public void onSingleItemSelected(View view, int i) {
        System.out.println("Refill_second_VH.onSingleItemSelected");
        int id = view.getId();
        switch (id){
            case R.id.viewSelectionChoicesFirst:{
                duration_input.clearFocus();
                duration_input.setText("");
                if( i == -1 ){
                    button_next.setEnabled(false);
                    button_next.setBackgroundResource(R.drawable.disable_next_question);
                }else {
                    Utils.hideSoftKeyBoard(itemView);
                    second.setClear();
                    duration_input.setText("");
                    for (Button button : buttons) {
                        button.setBackgroundResource(R.drawable.answer_not_selected);
                        button.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                    }
                    button_next.setEnabled(true);
                    button_next.setBackgroundResource(R.drawable.enable_next_question);
                    setFirstAnswerChoice(i);
                }
                break;
            }
            case R.id.viewSelectionChoicesSecond:{
                duration_input.clearFocus();
                duration_input.setText("");
                Utils.hideSoftKeyBoard(itemView);
                if( i == -1 ){
                    button_next.setEnabled(false);
                    button_next.setBackgroundResource(R.drawable.disable_next_question);
                }else {
                    first.setClear();
                    duration_input.setText("");
                    for (Button button : buttons) {
                        button.setBackgroundResource(R.drawable.answer_not_selected);
                        button.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                    }
                    answer.setChoice("i");
                    button_next.setEnabled(true);
                    button_next.setBackgroundResource(R.drawable.enable_next_question);
                }
                break;
            }
        }
    }

    private void setFirstAnswerChoice(int i) {
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
                break;
            }
            case 3:{
                answer.setChoice("d");
                break;
            }
        }
    }

    private class OnDaysClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("OnDaysClickListener.onClick");
            if( answer.getChoice() != null && answer.getChoice().equals("e") ){
                days.setBackgroundResource(R.drawable.answer_not_selected);
                days.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                button_next.setEnabled(false);
                button_next.setBackgroundResource(R.drawable.disable_next_question);
                answer = new Answer();
                duration_input.setText("");
            }else {
                answer = new Answer();
                answer.setChoice("e");
                first.setClear();
                second.setClear();
                days.setBackgroundResource(R.drawable.answer_selected);
                days.setTextColor(itemView.getContext().getResources().getColor(R.color.black));
                weeks.setBackgroundResource(R.drawable.answer_not_selected);
                weeks.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                months.setBackgroundResource(R.drawable.answer_not_selected);
                months.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                years.setBackgroundResource(R.drawable.answer_not_selected);
                years.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
            }
        }
    }

    private class OnMonthsClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("OnMonthsClickListener.onClick");
            if( answer.getChoice() != null && answer.getChoice().equals("f") ){
                months.setBackgroundResource(R.drawable.answer_not_selected);
                months.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                button_next.setEnabled(false);
                button_next.setBackgroundResource(R.drawable.disable_next_question);
                answer = new Answer();
                duration_input.setText("");

            }else {
                answer = new Answer();
                answer.setChoice("f");
                first.setClear();
                second.setClear();
                days.setBackgroundResource(R.drawable.answer_not_selected);
                days.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                weeks.setBackgroundResource(R.drawable.answer_not_selected);
                weeks.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                months.setBackgroundResource(R.drawable.answer_selected);
                months.setTextColor(itemView.getContext().getResources().getColor(R.color.black));
                years.setBackgroundResource(R.drawable.answer_not_selected);
                years.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
            }
        }
    }

    private class OnWeeksClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("OnWeeksClickListener.onClick");
            if( answer.getChoice() != null && answer.getChoice().equals("g") ){
                weeks.setBackgroundResource(R.drawable.answer_not_selected);
                weeks.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                button_next.setEnabled(false);
                button_next.setBackgroundResource(R.drawable.disable_next_question);
                answer = new Answer();
                duration_input.setText("");

            }else {
                answer = new Answer();
                answer.setChoice("g");
                first.setClear();
                second.setClear();
                days.setBackgroundResource(R.drawable.answer_not_selected);
                days.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                weeks.setBackgroundResource(R.drawable.answer_selected);
                weeks.setTextColor(itemView.getContext().getResources().getColor(R.color.black));
                months.setBackgroundResource(R.drawable.answer_not_selected);
                months.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                years.setBackgroundResource(R.drawable.answer_not_selected);
                years.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
            }
        }
    }

    private class OnYearsClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("OnYearsClickListener.onClick");
            if( answer.getChoice() != null && answer.getChoice().equals("h")){
                years.setBackgroundResource(R.drawable.answer_not_selected);
                years.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                answer = new Answer();
                button_next.setEnabled(false);
                button_next.setBackgroundResource(R.drawable.disable_next_question);
                duration_input.setText("");

            }else{
                answer = new Answer();
                answer.setChoice("h");
                first.setClear();
                second.setClear();
                days.setBackgroundResource(R.drawable.answer_not_selected);
                days.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                weeks.setBackgroundResource(R.drawable.answer_not_selected);
                weeks.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                months.setBackgroundResource(R.drawable.answer_not_selected);
                months.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
                years.setBackgroundResource(R.drawable.answer_selected);
                years.setTextColor(itemView.getContext().getResources().getColor(R.color.black));
            }
        }
    }

    private class OnDurationFocusChangedListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View view, boolean b) {
            System.out.println("OnDurationFocusChangedListener.onFocusChange");
            if( b ){
                first.setClear();
                second.setClear();
            }
        }
    }
}
