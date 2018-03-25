package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_11;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 3/4/2018.
 */

public class FUpS_11th_VH extends RecyclerView.ViewHolder {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private OnFUpSEleventhVHListener onFUpSEleventhVHListener;

    public FUpS_11th_VH(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_eleventh_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_11th_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
    }

    public void setOnFUpSEleventhVHListener(OnFUpSEleventhVHListener onFUpSEleventhVHListener) {
        this.onFUpSEleventhVHListener = onFUpSEleventhVHListener;
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_11th_VH.FUpS_11th_VH" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFUpSEleventhVHListener.onNextClick ();
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_11th_VH.FUpS_11th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFUpSEleventhVHListener.onSkipClick ();
        }
    }

    public interface OnFUpSEleventhVHListener {
        void onNextClick();
        void onSkipClick();
    }

}
