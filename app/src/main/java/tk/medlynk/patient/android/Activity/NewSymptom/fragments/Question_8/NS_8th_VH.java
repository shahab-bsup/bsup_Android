package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_8;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
    private TextView otherText;
    private String initial_other_text = "";

    public NS_8th_VH(View itemView) {
        super ( itemView );
        otherText = itemView.findViewById ( R.id.txtOther );
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

        //I know this is bad! Do not blame me please:D
        String[] strings = {string_choices[0]};
        first.setDataSet ( strings );

        second = itemView.findViewById ( R.id.viewSelectionSecond );
        second.setOnMultiItemSelectedListener ( this );
        second.setOnClearStateListener ( this );

        //I know this is bad! Do not blame me please:D
        String[] strings1 = {string_choices[1],
        string_choices[2], string_choices[3], string_choices[4]};
        second.setDataSet ( strings1 );
    }

    public void setOnEighthNSVHListener(OnEighthNSVHListener onEighthNSVHListener) {
        this.onEighthNSVHListener = onEighthNSVHListener;
    }

    public void setProgressBarVisibilityStatus(int status) {
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        choices.clear();
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
        first.setClear ();
        if (integer == 3) {
            DialogueBuilder dialogBuilder =
                    new DialogueBuilder ( itemView.getContext (),
                            "other",
                            initial_other_text );
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
                    setOtherTextVisibilityStatus ( View.GONE );
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
        if (otherText.length () > 0) {
            setOtherTextVisibilityStatus ( View.VISIBLE );
            setOtherText ( otherText );
            Answer answer = new Answer ();
            answer.setChoice ( "e" );
            answer.setOther ( otherText );
            choices.add ( answer );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        } else {
            second.unSelect ( 3 );
        }
    }

    @Override
    public void onClearState(View view) {
        if (view.getId () == second.getId ()) {
            choices.clear ();
        }
    }

    //called once there is more than one answer for this fragment
    public void onUpdateUI(List<Answer> answers) {
        for (Answer answer : answers) {
            switch (answer.getChoice ()) {
                case "a":{
                    first.updateViewSelectionUI(0);
                    choice=answer;
                    break;
                }
                case "b": {
                    second.updateViewSelectionUI( 0 );
                    choices.add(answer);
                    break;
                }
                case "c": {
                    second.updateViewSelectionUI(  1 );
                    choices.add(answer);
                    break;
                }
                case "d": {
                    second.updateViewSelectionUI(  2 );
                    choices.add(answer);
                    break;
                }
                case "e": {
                    choices.add(answer);
                    second.setSelect ( 3 );
                    if( answer.getOther () != null &&
                            !TextUtils.isEmpty ( answer.getOther () )){
                        setOtherTextVisibilityStatus ( View.VISIBLE );
                        setOtherText( answer.getOther () );
                        initial_other_text = answer.getOther ();
                    }

                    break;
                }
            }
        }
        button_next.setEnabled ( true );
        button_next.setBackgroundResource ( R.drawable.enable_next_question );
    }

    private void setOtherText(String otherText) {
        this.otherText.setText ( otherText );
    }

    private void setOtherTextVisibilityStatus(int status) {
        this.otherText.setVisibility ( status );
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
