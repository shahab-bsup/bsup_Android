package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_12;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 3/4/2018.
 */

public class FUpS_12th_VH extends RecyclerView.ViewHolder {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private OnFUpSTwelveVHListener onFUpSTwelveVHListener;

    public FUpS_12th_VH(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_twelve_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_12th_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_12th_VH.FUpS_12th_VH" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFUpSTwelveVHListener.onNextClick ();
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_12th_VH.FUpS_12th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFUpSTwelveVHListener.onSkipClick ();
        }
    }

    public void setOnFUpSTwelveVHListener(OnFUpSTwelveVHListener onFUpSTwelveVHListener) {
        this.onFUpSTwelveVHListener = onFUpSTwelveVHListener;
    }

    public interface OnFUpSTwelveVHListener {
        void onNextClick();
        void onSkipClick();
    }
}