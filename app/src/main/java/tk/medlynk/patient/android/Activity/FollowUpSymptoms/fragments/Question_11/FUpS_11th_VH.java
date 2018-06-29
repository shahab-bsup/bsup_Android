package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_11;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13.ChoiceAdapter;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13.NS_13th_VH;
import tk.medlynk.patient.android.Essentials.ForcedDialogBuilder;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/4/2018.
 */

public class FUpS_11th_VH extends RecyclerView.ViewHolder implements
        ForcedDialogBuilder.OnOtherDialogListener,
        ChoiceAdapter.OnChoiceClickListener {

    private static final String TAG = FUpS_11th_VH.class.getSimpleName ();
    private final TextView otherText;
    private final ChoiceAdapter adapter;
    private String initial_other_text = "";
    private ProgressBar progressBar;
    private View question_view;
    private Button button_next;
    private Button button_skip;
    private TextView have_not_tried_anything_button;
    private TextView question;
    private RecyclerView recyclerView;
    private Answer answer;
    private List<Answer> answers;
    private OnFUpSEleventhVHListener onFUpSEleventhVHListener;

    public FUpS_11th_VH(View itemView) {
        super ( itemView );
        have_not_tried_anything_button =
                itemView.findViewById ( R.id.not_tried_anyone_above );
        have_not_tried_anything_button
                .setOnClickListener ( new OnNotTriedClickListener () );
        otherText = itemView.findViewById ( R.id.txtOther );
        answers = new ArrayList<> ();
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.new_symptom_thirteen_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_thirteen_question );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener () );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        recyclerView = itemView.findViewById ( R.id.recycler_view_ns13 );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( itemView.getContext () ) );
        adapter = new ChoiceAdapter ( itemView.getContext (), recyclerView );
        adapter.setOnChoiceClickListener ( this );
        recyclerView.setAdapter ( adapter );
        adapter.notifyDataSetChanged ();
    }

    public void setOnFUpSEleventhVHListener(OnFUpSEleventhVHListener onFUpSEleventhVHListener) {
        this.onFUpSEleventhVHListener = onFUpSEleventhVHListener;
    }

    public void onUpdateUI(List<Answer> answers) {
        if (answers.get ( 0 ).getChoice ().equals ( "h" )) {
            this.answer = answers.get ( 0 );
            have_not_tried_anything_button
                    .setBackgroundResource ( R.drawable.answer_selected );
            have_not_tried_anything_button
                    .setTextColor ( itemView.getContext ().getResources ()
                            .getColor ( R.color.white ) );
        } else {
            adapter.setDataSet ( answers );
            this.answers = answers;
        }
        button_next.setEnabled ( true );
        button_next
                .setBackgroundResource ( R.drawable.enable_next_question );
    }

    public void setProgressBarVisibilityStatus(int status) {
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


    public void onMultiItemDeselected(Integer integer) {
        int i = integer;
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
    public void onOtherDialogDone(String otherText) {
        button_next.setEnabled ( true );
        button_next.setBackgroundResource ( R.drawable.enable_next_question );
        Answer answer = new Answer ();
        answer.setChoice ( "g" );
        answer.setOther ( otherText );
        answers.add ( answer );
        this.answer = null;
        have_not_tried_anything_button.setBackgroundColor ( itemView
                .getContext ().getResources ().getColor ( R.color.unselected_text_color ) );
        button_next
                .setBackgroundResource ( R.drawable.disable_next_question );
        have_not_tried_anything_button
                .setBackgroundResource ( R.drawable.answer_not_selected );
    }

    public void onHelpfullyClicked(int position, int helpfully_option) {
        Log.d ( TAG, "onHelpfullyClicked: " );
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
    public void onChoiceClicked(int choice) {
        Log.d ( TAG, "onChoiceClicked: " );
        setAnswerChoice ( choice );
    }

    @Override
    public void onChoiceUnClicked(int choice) {
        Log.d ( TAG, "onChoiceUnClicked: " );
        onMultiItemDeselected ( choice );
    }

    @Override
    public void onSubChoiceClicked(int choice, int subChoice) {
        Log.d ( TAG, "onSubChoiceClicked: " );
        onHelpfullyClicked ( choice, subChoice );
    }

    @Override
    public void onClearDataSet() {
        answers.clear ();
    }

    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged ();
    }



    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (answers.size () > 0) {
                boolean hasError = false;
                for (int position = 0; position < 7; position++) {
                    switch (position) {
                        case 0: {
                            Iterator<Answer> answerIterator = answers.iterator ();
                            while (answerIterator.hasNext ()) {
                                Answer answer = answerIterator.next ();
                                if (answer.getChoice () != null &&
                                        answer.getChoice ().equals ( "a" )) {
                                    if (answer.getHelpfully () == -1) {
                                        adapter.presentHelpfullyError ( 0, View.VISIBLE );
                                        hasError = true;
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                        case 1: {
                            Iterator<Answer> answerIterator = answers.iterator ();
                            while (answerIterator.hasNext ()) {
                                Answer answer = answerIterator.next ();
                                if (answer.getChoice () != null &&
                                        answer.getChoice ().equals ( "b" )) {
                                    if (answer.getHelpfully () == -1) {
                                        adapter.presentHelpfullyError ( 1, View.VISIBLE );
                                        hasError = true;
                                        break;
                                    }
                                }
                            }

                            break;
                        }
                        case 2: {
                            Iterator<Answer> answerIterator = answers.iterator ();
                            while (answerIterator.hasNext ()) {
                                Answer answer = answerIterator.next ();
                                if (answer.getChoice () != null &&
                                        answer.getChoice ().equals ( "c" )) {
                                    if (answer.getHelpfully () == -1) {
                                        adapter.presentHelpfullyError ( 2, View.VISIBLE );
                                        hasError = true;
                                        break;
                                    }
                                }
                            }

                            break;
                        }

                        case 3: {
                            Iterator<Answer> answerIterator = answers.iterator ();
                            while (answerIterator.hasNext ()) {
                                Answer answer = answerIterator.next ();
                                if (answer.getChoice () != null &&
                                        answer.getChoice ().equals ( "d" )) {
                                    if (answer.getHelpfully () == -1) {
                                        adapter.presentHelpfullyError ( 3, View.VISIBLE );
                                        hasError = true;
                                        break;
                                    }
                                }
                            }
                            break;
                        }

                        case 4: {
                            Iterator<Answer> answerIterator = answers.iterator ();
                            while (answerIterator.hasNext ()) {
                                Answer answer = answerIterator.next ();
                                if (answer.getChoice () != null &&
                                        answer.getChoice ().equals ( "e" )) {
                                    if (answer.getHelpfully () == -1) {
                                        adapter.presentHelpfullyError ( 4, View.VISIBLE );
                                        hasError = true;
                                        break;
                                    }
                                }
                            }
                            break;
                        }

                        case 5: {
                            Iterator<Answer> answerIterator = answers.iterator ();
                            while (answerIterator.hasNext ()) {
                                Answer answer = answerIterator.next ();
                                if (answer.getChoice () != null &&
                                        answer.getChoice ().equals ( "f" )) {
                                    if (answer.getHelpfully () == -1) {
                                        adapter.presentHelpfullyError ( 5, View.VISIBLE );
                                        hasError = true;
                                        break;
                                    }
                                }
                            }
                            break;
                        }

                        case 6: {
                            Iterator<Answer> answerIterator = answers.iterator ();
                            while (answerIterator.hasNext ()) {
                                Answer answer = answerIterator.next ();
                                if (answer.getChoice () != null &&
                                        answer.getChoice ().equals ( "g" )) {
                                    if (answer.getHelpfully () == -1) {
                                        adapter.presentHelpfullyError ( 6, View.VISIBLE );
                                        hasError = true;
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
                if (!hasError) {
                    onFUpSEleventhVHListener.onNextClicked ( answers );
                } else {
                    adapter.notifyDataSetChanged ();
                }
            } else {
                onFUpSEleventhVHListener.onNextClicked ( answer );
            }
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            onFUpSEleventhVHListener.onSkipClicked ();
        }
    }

    private class OnNotTriedClickListener implements
            View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (answer != null) {
                answer = null;
                button_next.setEnabled ( false );
                button_next
                        .setBackgroundResource ( R.drawable.disable_next_question );
                have_not_tried_anything_button
                        .setBackgroundResource ( R.drawable.answer_not_selected );
                have_not_tried_anything_button
                        .setTextColor ( itemView.getContext ().getResources ()
                                .getColor ( R.color.colorPrimary ) );
            } else {
                adapter.clearDataSet ();
                answer = new Answer ();
                answer.setChoice ( "h" );
                button_next.setEnabled ( true );
                button_next
                        .setBackgroundResource ( R.drawable.enable_next_question );
                have_not_tried_anything_button
                        .setBackgroundResource ( R.drawable.answer_selected );
                have_not_tried_anything_button
                        .setTextColor ( itemView.getContext ().getResources ()
                                .getColor ( R.color.white ) );
            }
        }
    }

    public interface OnFUpSEleventhVHListener {
        void onNextClicked(List<Answer> answers);

        void onNextClicked(Answer answer);

        void onSkipClicked();
    }

}
