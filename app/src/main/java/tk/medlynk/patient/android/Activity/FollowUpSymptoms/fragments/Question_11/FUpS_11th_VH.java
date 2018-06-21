package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_11;

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
 * Created by Shahab on 3/4/2018.
 */

public class FUpS_11th_VH extends RecyclerView.ViewHolder implements
        ViewSelection.OnSingleItemSelectedListener,
        ViewSelection.OnMultiItemSelectedListener,
        ViewSelection.OnClearStateListener,
        ViewSelection.OnHelpfullyOptionsClickListener, ForcedDialogBuilder.OnOtherDialogListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection first, second;
    private String[] string_choices;
    private Answer answer = new Answer ();
    private List<Answer> answers = new ArrayList<> ();
    private OnFUpSEleventhVHListener onFUpSEleventhVHListener;

    public FUpS_11th_VH(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_eleventh_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_11th_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        first = itemView.findViewById ( R.id.viewSelectionFirst );
        second = itemView.findViewById ( R.id.viewSelectionSecond );
        first.setOnSingleItemSelectedListener ( this );
        string_choices = itemView.getContext ().getResources ().getStringArray ( R.array.question_13_22_choices );
        first.setDataSet ( string_choices );

        //I know this is bad! Do not blame me please:D
        String[] strings = {string_choices[7]};
        second.setDataSet ( strings );

        first.setOnMultiItemSelectedListener ( this );
        first.setOnClearStateListener ( this );
        first.setOnHelpfullyOptionClickListener ( this );
        second.setOnSingleItemSelectedListener ( this );
        second.setOnClearStateListener ( this );
    }

    public void setOnFUpSEleventhVHListener(OnFUpSEleventhVHListener onFUpSEleventhVHListener) {
        this.onFUpSEleventhVHListener = onFUpSEleventhVHListener;
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
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
        if (answers.size () == 1) {
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    @Override
    public void onSingleItemSelected(View view, int position) {
        System.out.println ( "FUpS_11th_VH.onSingleItemSelected" );
        if( position == -1 ){
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

    @Override
    public void onMultiItemSelected(View view, Integer position) {
        System.out.println ( "FUpS_11th_VH.onMultiItemSelected" );
        second.setClear ();
        int i = position;
        setAnswerChoice ( i );
    }

    @Override
    public void onMultiItemDeselected(View view, Integer position) {
        System.out.println ( "FUpS_11th_VH.onMultiItemDeselected" );
        int i = position;
        Iterator<Answer> answerIterator = answers.iterator ();
        switch (i) {
            case 0: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null &&
                            answer.getChoice ().equals ( "a" )) {
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }
            case 1: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null &&
                            answer.getChoice ().equals ( "b" )) {
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
                            answer.getChoice ().equals ( "c" )) {
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }
            case 3: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null &&
                            answer.getChoice ().equals ( "d" )) {
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }

            case 4: {
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

            case 5: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null &&
                            answer.getChoice ().equals ( "f" )) {
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }

            case 6: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null &&
                            answer.getChoice ().equals ( "g" )) {
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }
        }

        if (answers.size () == 0) {
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println ( "FUpS_11th_VH.onClearState" );
        if (view.getId () == first.getId ()) {
            answers.clear ();
        }
    }

    @Override
    public void onHelpFullyClicked(int position, int helpfully_option) {
        System.out.println ( "FUpS_11th_VH.onHelpFullyClicked" );
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
    public void onOtherDialogDone(String otherText) {
        System.out.println ( "FUpS_11th_VH.onOtherDialogDone" );
        button_next.setEnabled ( true );
        button_next.setBackgroundResource ( R.drawable.enable_next_question );
        Answer answer = new Answer ();
        answer.setChoice ( "g" );
        answer.setOther ( otherText );
        answers.add ( answer );
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_11th_VH.FUpS_11th_VH" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            if (answers.size () > 0) {
                boolean hasError = false;
                for (int i = 0; i < answers.size (); i++) {
                    if (answers.get ( i ).getHelpfully () == -1) {
                        first.showHelpfullyOptionError ( i, View.VISIBLE );
                        hasError = !hasError;
                        break;
                    }
                }
                if (!hasError) {
                    onFUpSEleventhVHListener.onNextClicked ( answers );
                }
            } else {
                onFUpSEleventhVHListener.onNextClicked ( answer );
            }
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_11th_VH.FUpS_11th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFUpSEleventhVHListener.onSkipClicked ();
        }
    }

    public interface OnFUpSEleventhVHListener {
        void onNextClicked(List<Answer> answers);

        void onNextClicked(Answer answer);

        void onSkipClicked();
    }

}
