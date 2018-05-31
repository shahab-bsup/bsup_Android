package tk.medlynk.patient.android.Activity.Refill.fragments.End_of_Question_Set;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 4/13/2018.
 */

public class End_of_Question_Set_VH extends RecyclerView.ViewHolder {

    private final Button new_refill;
    private final Button first_unanswered;
    private final Button new_question_set;
    private final Button progress_page;
    private final Button print;
    private OnEndOfQuestionSetVHListener onEndOfQuestionSetVHListener;

    public End_of_Question_Set_VH(View itemView) {
        super ( itemView );
        new_refill = itemView.findViewById ( R.id.btnNewRefill );
        new_refill.setId ( (int)0 );
        new_refill.setOnClickListener ( new OnButtonClickListener () );
        first_unanswered = itemView.findViewById ( R.id.btnFirstUnanswered );
        first_unanswered.setId ( (int)1 );
        first_unanswered.setOnClickListener ( new OnButtonClickListener () );
        new_question_set = itemView.findViewById ( R.id.btnNewQuestionSet );
        new_question_set.setId ( (int)2 );
        new_question_set.setOnClickListener ( new OnButtonClickListener () );
        progress_page = itemView.findViewById ( R.id.btnProgressPage );
        progress_page.setId ( (int)3 );
        progress_page.setOnClickListener ( new OnButtonClickListener () );
        print = itemView.findViewById ( R.id.btnPrint );
        print.setId ( (int)4 );
        print.setOnClickListener ( new OnButtonClickListener () );
    }

    public void setOnEndOfQuestionSetVHListener(OnEndOfQuestionSetVHListener onEndOfQuestionSetVHListener) {
        this.onEndOfQuestionSetVHListener = onEndOfQuestionSetVHListener;
    }

    public interface OnEndOfQuestionSetVHListener{
        void onButtonClicked(int buttonId);
    }

    private class OnButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            onEndOfQuestionSetVHListener.onButtonClicked ( view.getId () );
        }
    }
}
