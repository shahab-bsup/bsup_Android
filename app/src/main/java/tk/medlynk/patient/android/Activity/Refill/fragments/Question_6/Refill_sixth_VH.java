package tk.medlynk.patient.android.Activity.Refill.fragments.Question_6;

import android.app.DatePickerDialog;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.medlynk.shahab.myviewselection.ViewSelection.OnClearStateListener;
import com.medlynk.shahab.myviewselection.ViewSelection.OnMultiItemSelectedListener;
import com.medlynk.shahab.myviewselection.ViewSelection.OnSingleItemSelectedListener;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import tk.medlynk.patient.android.Essentials.DialogueBuilder;
import tk.medlynk.patient.android.Model.Answer;

public class Refill_sixth_VH extends ViewHolder implements OnSingleItemSelectedListener, OnClearStateListener, OnMultiItemSelectedListener, DatePickerDialog.OnDateSetListener, DialogueBuilder.OnOtherDialogListener {

    private final ProgressBar progressBar;
    private ViewSelection first, second;
    private Button button_next;
    private OnRefillSixthVHListener onRefillSixthVHListener;
    private TextView question;
    private View question_view;
    private Button button_skip;
    private String[] string_choices;
    private Answer answer = new Answer();
    private List<Answer> answers = new ArrayList<>();

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility(status);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        System.out.println("Refill_sixth_VH.onDateSet");
        Iterator<Answer> answerIterator = answers.iterator();
        while (answerIterator.hasNext()){
            Answer answer = answerIterator.next();
            if( answer.getChoice() != null && answer.getChoice().equals("b") ){
                answer.setReading(i + "/" + i1 + "/" + i2);
                break;
            }
        }
    }

    @Override
    public void onOtherDialogDone(String bloodPressure) {
        System.out.println("Refill_sixth_VH.onOtherDialogDone");
        if( bloodPressure.length() > 0 ){
            Iterator<Answer> answerIterator = answers.iterator();
            while (answerIterator.hasNext()){
                Answer answer = answerIterator.next();
                if( answer.getChoice() != null && answer.getChoice().equals("c") ){
                    answer.setReading(bloodPressure);
                }
            }
        }else{
            second.getButtons().get(1).setBackgroundResource(R.drawable.answer_not_selected);
            second.getButtons().get(1).setTextColor(itemView.getContext().getResources().getColor(R.color.white));
        }
    }

    private class OnNextClickListener implements OnClickListener {
        public void onClick(View view) {
            System.out.println("Refill_sixth_VH");
            System.out.println("OnNextClickListener.onClick");
            if( answers.size() > 0 ){
                onRefillSixthVHListener.onNextClicked(answers);
            }else{
                onRefillSixthVHListener.onNextClicked(answer);
            }
        }
    }

    public interface OnRefillSixthVHListener {
        void onNextClicked(Answer answer);
        void onNextClicked(List<Answer> answers);
        void onSkipClicked();
    }

    private class OnSkipClickListener implements OnClickListener {
        public void onClick(View view) {
            System.out.println("Refill_sixth_VH");
            System.out.println("OnSkipClickListener.onClick");
            onRefillSixthVHListener.onSkipClicked();
        }
    }

    public Refill_sixth_VH(View itemView) {
        super(itemView);
        progressBar =  itemView.findViewById( R.id.progress_bar);
        this.question_view = itemView.findViewById(R.id.refill_sixth_question);
        question = ((TextView) this.question_view.findViewById(R.id.txtQuestion));
        this.question.setText(R.string.refill_sixth_question);
        this.button_next = (Button) itemView.findViewById(R.id.btnNextQuestion);
        this.button_next.setOnClickListener(new OnNextClickListener());
        this.button_skip = (Button) itemView.findViewById(R.id.btnSkipQuestion);
        this.button_skip.setOnClickListener(new OnSkipClickListener());
        this.first = (ViewSelection) itemView.findViewById(R.id.viewSelectionChoicesFirst);
        this.first.setOnSingleItemSelectedListener(this);
        this.string_choices = itemView.getContext().getResources().getStringArray(R.array.refill_sixth_question_choices);
        this.first.setTextToButtons(this.string_choices[0], 0);
        this.second = (ViewSelection) itemView.findViewById(R.id.viewSelectionChoicesSecond);
        this.second.setTextToButtons(this.string_choices[1], 0);
        this.second.setTextToButtons(this.string_choices[2], 1);
        this.second.setOnMultiItemSelectedListener(this);
        this.second.setOnClearStateListener(this);
        this.first.setOnClearStateListener(this);
    }

    public void setOnRefillSixthVHListener(OnRefillSixthVHListener onRefillSixthVHListener) {
        this.onRefillSixthVHListener = onRefillSixthVHListener;
    }

    public void onSingleItemSelected(View view, int i) {
        System.out.println("Refill_sixth_VH.onSingleItemSelected");
        if (i == -1) {
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
        } else {
            answer.setChoice("a");
            button_next.setEnabled(true);
            button_next.setBackgroundResource(R.drawable.enable_next_question);
            second.setClear();
        }
    }

    public void onClearState(View view) {
        System.out.println("Refill_sixth_VH.onClearState");
        answers.clear();
    }

    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println("Refill_sixth_VH.onMultiItemSelected");
        int i = integer;
        Answer answer = new Answer();
        first.setClear();
        switch (i){
            case 0:{
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(itemView.getContext(),
                        this, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH) );
                answer.setChoice("b");
                datePickerDialog.show();
                break;
            }
            case 1:{
                answer.setChoice("c");
                DialogueBuilder dialogueBuilder = new DialogueBuilder(itemView.getContext(), "blood_pressure", "shahab" );
                dialogueBuilder.setOnDialogListener(this);
                dialogueBuilder.show();

                break;
            }
        }
        answers.add(answer);
        if( answers.size() == 1 ){
            button_next.setEnabled(true);
            button_next.setBackgroundResource(R.drawable.enable_next_question);
        }
    }

    public void onMultiItemDeselected(View view, Integer integer) {
        System.out.println("Refill_sixth_VH.onMultiItemDeselected");
        int i = integer;
        Iterator<Answer> answerIterator = answers.iterator ();
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
            case 1: {
                while (answerIterator.hasNext()) {
                    Answer answer = answerIterator.next();
                    if (answer.getChoice() != null &&
                            answer.getChoice().equals("c")) {
                        answerIterator.remove();
                        break;
                    }
                }

                break;
            }
        }
        if( answers.size() == 0 ){
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
        }
    }
}
