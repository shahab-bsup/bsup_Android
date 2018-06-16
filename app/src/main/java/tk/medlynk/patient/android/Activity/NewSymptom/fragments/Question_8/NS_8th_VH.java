package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_8;

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

import tk.medlynk.patient.android.Essentials.DialogueBuilder;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 2/23/2018.
 */

public class NS_8th_VH extends RecyclerView.ViewHolder implements
        ViewSelection.OnSingleItemSelectedListener,
        ViewSelection.OnMultiItemSelectedListener,
        DialogueBuilder.OnOtherDialogListener,
        ViewSelection.OnClearStateListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection first, second;
    private String[] string_choices;
    private Answer choice;
    private List<Answer> choices;
    private OnEighthNSVHListener onEighthNSVHListener;

    public NS_8th_VH(View itemView) {
        super ( itemView );
        choices = new ArrayList<> ();
        choice = new Answer ();
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.new_symptom_eighth_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_eighth_question );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener () );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        string_choices = itemView.
                getContext ().
                getResources ().
                getStringArray ( R.array.question_8_9_choices );
        first = itemView.findViewById ( R.id.viewSelectionFirst );
        first.setOnSingleItemSelectedListener ( this );
        first.setOnClearStateListener ( this );
        first.setTextToButtons ( string_choices[0], 0 );
        second = itemView.findViewById ( R.id.viewSelectionSecond );
        second.setOnMultiItemSelectedListener ( this );
        second.setOnClearStateListener ( this );
        for (int i = 0; i < second.getNumberOfViews (); i++) {
            second.setTextToButtons ( string_choices[i + 1], i );
        }
    }

    public void setOnEighthNSVHListener(OnEighthNSVHListener onEighthNSVHListener) {
        this.onEighthNSVHListener = onEighthNSVHListener;
    }

    public void setProgressBarVisibilityStatus(int status) {
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        if (i == -1) {
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        } else {
            second.setClear ();
            choice.setChoice ( "a" );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println ( "NS_8th_VH.onMultiItemSelected" );
        first.setClear ();
        if (integer == 3) {
            DialogueBuilder dialogBuilder = new DialogueBuilder ( itemView.getContext (), "other", "shahab" );
            dialogBuilder.setOnDialogListener ( this );
            dialogBuilder.show ();
        } else {
            setAnswerChoices ( integer );
        }
    }

    private void setAnswerChoices(Integer integer) {
        Answer answer = new Answer ();
        switch (integer) {
            case 0: {
                answer.setChoice ( "b" );
                break;
            }
            case 1: {
                answer.setChoice ( "c" );

                break;
            }
            case 2: {
                answer.setChoice ( "d" );

                break;
            }
        }
        choices.add ( answer );
        if (choices.size () == 1) {
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {
        System.out.println ( "NS_8th_VH.onMultiItemDeselected" );
        int i = integer;
        Iterator<Answer> answerIterator = choices.iterator ();
        switch (i) {
            case 0: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null &&
                            answer.getChoice ().equals ( "b" ))
                        answerIterator.remove ();
                }

                break;
            }
            case 1: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null &&
                            answer.getChoice ().equals ( "c" )) {
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }
            case 2: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null &&
                            answer.getChoice ().equals ( "d" )) {
                        answerIterator.remove ();
                        break;
                    }
                }

            }
            case 3: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null &&
                            answer.getChoice ().equals ( "e" )) {
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }
        }
        if (choices.size () == 0) {
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }
    }

    @Override
    public void onOtherDialogDone(String otherText) {
        System.out.println ( "NS_8th_VH.onOtherDialogDone" );
        if (otherText.length () > 0) {
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

    @Override
    public void onClearState(View view) {
        System.out.println ( "NS_8th_VH.onClearState" );
        if (view.getId () == second.getId ()) {
            choices.clear ();
        }
    }

    //called once there is more than one answer for this fragment
    public void onUpdateUI(List<Answer> answers) {
        for (Answer answer : answers) {
            switch (answer.getChoice ()) {
                case "b": {
                    second.previewOfDBResult ( true, false, 0 );

                    break;
                }
                case "c": {
                    second.previewOfDBResult ( true, false, 1 );

                    break;
                }
                case "d": {
                    second.previewOfDBResult ( true, false, 2 );

                    break;
                }
                case "e": {
                    second.previewOfDBResult ( true, false, 3 );

                    break;
                }

            }
        }
    }

    //called once there is only one answer for this fragment
    public void onUpdateUI(Answer answer) {
        first.previewOfDBResult ( true, true, 0 );
    }

    public interface OnEighthNSVHListener {
        void onNextClicked(Answer answer);

        void onNextClicked(List<Answer> answers);

        void onSkipClicked();
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (choices.size () > 0) {
                onEighthNSVHListener.onNextClicked ( choices );
            } else {
                onEighthNSVHListener.onNextClicked ( choice );
            }
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_8th_VH.NS_8th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onEighthNSVHListener.onSkipClicked ();
        }
    }
}
