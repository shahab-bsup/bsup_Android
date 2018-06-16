package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_17;

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
 * Created by Shahab on 2/24/2018.
 */

public class NS_17th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnMultiItemSelectedListener, DialogueBuilder.OnOtherDialogListener {

    private ProgressBar progressBar;
    private View question_view;
    private Button button_next, button_skip;
    private TextView question;
    private ViewSelection viewSelection;
    List<Answer> answers = new ArrayList<> (  );
    private String[] string_choices;
    private OnSeventeenNSVHListener onSeventeenNSVHListener;
    private TextView otherText;
    private String initial_other_text = "";

    public NS_17th_VH(View itemView) {
        super ( itemView );
        otherText = itemView.findViewById ( R.id.txtOther );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.new_symptom_seventeen_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_seventeen_question );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        viewSelection = itemView.findViewById ( R.id.viewSelectionChoices );
        viewSelection.setOnMultiItemSelectedListener ( this );
        string_choices = itemView.getContext ().getResources ().getStringArray ( R.array.question_17_choices );
        for (int i = 0; i < viewSelection.getNumberOfViews (); i++) {
            viewSelection.setTextToButtons ( string_choices[i], i );
        }
    }

    private void setOtherText(String other) {
        this.otherText.setText ( other );
    }

    private void setOtherTextVisibilityStatus(int status){
        this.otherText.setVisibility ( status );
    }

    public void setOnSeventeenNSVHListener(OnSeventeenNSVHListener onSeventeenNSVHListener) {
        this.onSeventeenNSVHListener = onSeventeenNSVHListener;
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {
        if( integer == 6 ){
            DialogueBuilder dialogBuilder =
                    new DialogueBuilder( itemView.getContext (),
                            "other",
                            initial_other_text );
            dialogBuilder.setOnDialogListener( this );
            dialogBuilder.show ();
        }else{
            setAnswerChoices(integer);
        }
    }

    private void setAnswerChoices(Integer integer) {
        Answer answer = new Answer ();
        switch (integer){
            case 0:{
                answer.setChoice ( "a" );
                break;
            }
            case 1:{
                answer.setChoice ( "b" );

                break;
            }
            case 2:{
                answer.setChoice ( "c" );

                break;
            }
            case 3:{
                answer.setChoice ( "d" );

                break;
            }

            case 4:{
                answer.setChoice ( "e" );

                break;
            }
            case 5:{
                answer.setChoice ( "f" );

                break;
            }
        }
        answers.add ( answer );
        if( answers.size () == 1 ){
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {
        int i = integer;
        Iterator<Answer> answerIterator = answers.iterator ();
        switch (i){
            case 0:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if(  answer.getChoice () != null &&
                            answer.getChoice ().equals ( "a" ) )
                        answerIterator.remove ();
                }

                break;
            }
            case 1:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if(  answer.getChoice () != null &&
                            answer.getChoice ().equals ( "b" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }
            case 2:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if(  answer.getChoice () != null &&
                            answer.getChoice ().equals ( "c" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

            }
            case 3:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if(  answer.getChoice () != null &&
                            answer.getChoice ().equals ( "d" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }
            case 4:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if(  answer.getChoice () != null &&
                            answer.getChoice ().equals ( "e" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }
            case 5:{
                while (answerIterator.hasNext ()){
                    Answer answer = answerIterator.next ();
                    if(  answer.getChoice () != null &&
                            answer.getChoice ().equals ( "f" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }
            case 6:{
                while (answerIterator.hasNext ()){
                    setOtherTextVisibilityStatus ( View.GONE );
                    Answer answer = answerIterator.next ();
                    if(  answer.getChoice () != null &&
                            answer.getChoice ().equals ( "g" ) ){
                        answerIterator.remove ();
                        break;
                    }
                }

                break;
            }

        }
        if( answers.size () == 0 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }
    }

    @Override
    public void onOtherDialogDone(String otherText) {
        if( otherText.length () > 0 ){
            setOtherTextVisibilityStatus ( View.VISIBLE );
            setOtherText ( otherText );
            Answer answer = new Answer ();
            answer.setChoice ( "g" );
            answer.setOther ( otherText );
            answers.add ( answer );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        } else {
            viewSelection.getButtons ().get ( 6 ).setBackgroundResource ( R.drawable.answer_not_selected );
            viewSelection.getButtons ().get ( 6 ).setTextColor ( itemView.
                    getContext ().
                    getResources ().
                    getColor ( R.color.white ) );
        }
    }

    public void onUpdateUI(List<Answer> answers) {
        for (Answer answer : answers) {
            switch (answer.getChoice ()){
                case "a":{
                    viewSelection.previewOfDBResult ( true, false,
                            0);
                    break;
                }
                case "b":{
                    viewSelection.previewOfDBResult ( true, false,
                            1);
                    break;
                }
                case "c":{
                    viewSelection.previewOfDBResult ( true, false,
                            2);
                    break;
                }
                case "d":{
                    viewSelection.previewOfDBResult ( true, false,
                            3);
                    break;
                }
                case "e":{
                    viewSelection.previewOfDBResult ( true, false,
                            4);
                    break;
                }
                case "f":{
                    viewSelection.previewOfDBResult ( true, false,
                            5);
                    break;
                }
                case "g":{
//                    viewSelection.previewOfDBResult ( true,
//                            false,
//                            6);
                    viewSelection.setSelect ( 3 );
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
    }


    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_17th_VH.NS_17th_VH" );
            System.out.println ( "OnNextClickListener.onClick" );
            onSeventeenNSVHListener.onNextClicked ( answers );
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_17th_VH.NS_17th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onSeventeenNSVHListener.onSkipClicked ();
        }
    }

    public interface OnSeventeenNSVHListener {
        void onNextClicked(List<Answer> answer);
        void onSkipClicked();
    }
}
