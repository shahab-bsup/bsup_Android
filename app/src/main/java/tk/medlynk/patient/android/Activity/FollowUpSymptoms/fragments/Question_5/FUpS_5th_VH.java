package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_5;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Essentials.DialogueBuilder;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/4/2018.
 */

public class FUpS_5th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener, DialogueBuilder.OnOtherDialogListener {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private final ViewSelection choices;
    private final String[] string_choices;
    private OnFUpSFifthVHListener onFUpSFifthVHListener;
    private final Answer answer;

    public FUpS_5th_VH(View itemView,Answer answerDB) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_fifth_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_5th_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        choices = itemView.findViewById ( R.id.viewSelectionChoices );
        choices.setOnSingleItemSelectedListener ( this );
        string_choices = itemView.getContext ().getResources ().getStringArray ( R.array.FUS_5th_choices_FUR_eighth_choices );
        for (int i = 0; i < choices.getNumberOfViews (); i++) {
            choices.setTextToButtons ( string_choices[i], i );
        }
        answer = new Answer ();

        if (answerDB!=null){
            switch (answerDB.getChoice()){
                case "a":{choices.previewOfDBResult(true,true,0);break;}
                case "b":{choices.previewOfDBResult(true,true,1);break;}
                case "c":{choices.previewOfDBResult(true,true,2);break;}
                case "d":{choices.previewOfDBResult(true,true,3);break;}
                case "e":{choices.previewOfDBResult(true,true,4);break;}
                case "f":{choices.previewOfDBResult(true,true,5);break;}
                case "g":{

                }

            }

        }
    }

    public void setOnFUpSFifthVHListener(OnFUpSFifthVHListener onFUpSFifthVHListener) {
        this.onFUpSFifthVHListener = onFUpSFifthVHListener;
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "FUpS_5th_VH.onSingleItemSelected" );
        if( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else{
            button_next.setEnabled(true);
            button_next.setBackgroundResource(R.drawable.enable_next_question);
            setAnswerChoice(i);
        }
    }

    private void setAnswerChoice(int i) {
        switch (i){

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

            case 6:{
                answer.setChoice ( "g" );
                DialogueBuilder dialogBuilder =
                        new DialogueBuilder( itemView.getContext (),"other" );
                dialogBuilder.setOnDialogListener( this );
                dialogBuilder.show ();
                break;
            }
        }
    }

    @Override
    public void onOtherDialogDone(String otherText) {
        System.out.println ( "FUpS_5th_VH.onOtherDialogDone" );
        if( otherText.length() >0  ){
            answer.setOther(otherText);
        }else{
            button_next.setEnabled(false);
            button_next.setBackgroundResource(R.drawable.disable_next_question);
            choices.getButtons().get(6).setBackgroundResource(R.drawable.answer_not_selected);
            choices.getButtons().get(6).setTextColor(itemView.getContext().getResources().getColor(R.color.white));
        }
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_5th_VH.FUpS_5th_VH" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFUpSFifthVHListener.onNextClick (answer);
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_5th_VH.FUpS_5th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFUpSFifthVHListener.onSkipClick ();
        }
    }

    public interface OnFUpSFifthVHListener {
        void onNextClick(Answer answer);
        void onSkipClick();
    }
}
