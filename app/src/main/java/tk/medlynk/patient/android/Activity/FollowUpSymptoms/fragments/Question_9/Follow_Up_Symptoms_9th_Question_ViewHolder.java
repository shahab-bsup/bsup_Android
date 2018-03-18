package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8.Follow_Up_Symptoms_8th_Question_ViewHolder;
import tk.medlynk.patient.android.Essentials.OtherDialogBuilder;
import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 3/4/2018.
 */

public class Follow_Up_Symptoms_9th_Question_ViewHolder extends
        RecyclerView.ViewHolder implements
        ViewSelection.OnSingleItemSelectedListener,
        ViewSelection.OnMultiItemSelectedListener,
        ViewSelection.OnClearStateListener, OtherDialogBuilder.OnOtherDialogListener {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private ViewSelection none, other;
    private String[] string_choices;
    private List<Answer> answers = new ArrayList<> (  );
    private Answer answer = new Answer ();
    private OnFollowUpNinthQuestionViewsClickListener onFollowUpNinthQuestionViewsClickListener;
    private String otherString;
    private int single_select = -1;
    private int multi_select = -1;

    public Follow_Up_Symptoms_9th_Question_ViewHolder(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_ninth_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_9th_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        none = itemView.findViewById ( R.id.single_select_choice );
        none.setOnSingleItemSelectedListener ( this );
        other = itemView.findViewById ( R.id.multi_select_choice );
        other.setOnMultiItemSelectedListener ( this );
        String string = itemView.getContext ()
                .getString ( R.string.first_choice_follow_up_symptom_9th_10yj_choices );
        string_choices = itemView.getContext ().getResources ().getStringArray ( R.array.follow_up_symptoms_9th_10th_choices );
        none.setTextToButtons ( string, 0 );
        none.setOnClearStateListener ( this );
        for (int i = 0; i < other.getNumberOfViews (); i++){
            other.setTextToButtons ( string_choices[i], i );
        }
        other.setOnClearStateListener ( this );
    }

    public void setOnFollowUpNinthQuestionViewsClickListener(OnFollowUpNinthQuestionViewsClickListener onFollowUpNinthQuestionViewsClickListener) {
        this.onFollowUpNinthQuestionViewsClickListener = onFollowUpNinthQuestionViewsClickListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "Follow_Up_Symptoms_9th_Question_ViewHolder.onSingleItemSelected" );
        other.setClear ();
        answer.setChoice ( "a" );
        answers.clear ();
        if( single_select == -1 ){
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
            single_select = i;
        } else{
            single_select = -1;
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }
    }

    @Override
    public void onMultiItemSelected(View view, Integer integer) {
        System.out.println ( "Follow_Up_Symptoms_9th_Question_ViewHolder.onMultiItemSelected" );
        button_next.setEnabled ( true );
        button_next.setBackgroundResource ( R.drawable.enable_next_question );
        multi_select = integer;
        none.setClear ();
        Answer answer = new Answer ();
        switch (integer){
            case 0:{
                answer.setChoice ( "b" );
                break;
            }

            case 1:{
                answer.setChoice ( "c" );

                break;
            }

            case 2:{
                answer.setChoice ( "d" );
                break;
            }

            case 3:{
                OtherDialogBuilder builder = new OtherDialogBuilder ( itemView.getContext () );
                builder.show ();
                builder.setOnOtherDialogListener ( this );
                answer.setChoice ( "e" );
                break;
            }
        }
        if( otherString != null ){
            answer.setOther ( otherString );
        }
        answers.add ( answer );
    }

    @Override
    public void onMultiItemDeselected(View view, Integer integer) {
        System.out.println ( "Follow_Up_Symptoms_9th_Question_ViewHolder.onMultiItemDeselected" );
        int i = integer;
        answers.remove ( i );
        if( answers.size () == 0 ){
            multi_select = -1;
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println ( "Follow_Up_Symptoms_9th_Question_ViewHolder.onClearState" );
    }

    @Override
    public void onOtherDialogDone(String otherText) {
        System.out.println ( "Follow_Up_Symptoms_9th_Question_ViewHolder.onOtherDialogDone" );
        otherString = otherText;
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "Follow_Up_Symptoms_9th_Question_ViewHolder.Follow_Up_Symptoms_9th_Question_ViewHolder" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            if( single_select != -1 )
                onFollowUpNinthQuestionViewsClickListener.onNextClick (answer);
            else
                onFollowUpNinthQuestionViewsClickListener.onNextClick ( answers );
        }
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "Follow_Up_Symptoms_9th_Question_ViewHolder.Follow_Up_Symptoms_9th_Question_ViewHolder" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFollowUpNinthQuestionViewsClickListener.onSkipClick ();
        }
    }

    public interface OnFollowUpNinthQuestionViewsClickListener{
        void onNextClick(Answer answer);
        void onNextClick(List<Answer> answers);
        void onSkipClick();
    }
}
