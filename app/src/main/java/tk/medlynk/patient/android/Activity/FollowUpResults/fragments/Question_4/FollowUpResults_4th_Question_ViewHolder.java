package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_4;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_3.FollowUpResults_3rd_Question_ViewHolder;

/**
 * Created by Shahab on 3/22/2018.
 */

public class FollowUpResults_4th_Question_ViewHolder extends RecyclerView.ViewHolder {

    private final Button button_next;
    private final View question_view;
    private final ProgressBar progressBar;
    private final TextView fourth_question;
    private final AppCompatEditText answer_input;
    private OnFURFourthViewHolderListener onFURFourthViewHolderListener;

    public FollowUpResults_4th_Question_ViewHolder(View itemView) {
        super ( itemView );
        progressBar =  itemView.findViewById( R.id.progress_bar);
        question_view = itemView.findViewById(R.id.follow_up_results_fourth_question);
        fourth_question =  question_view.findViewById(R.id.txtQuestion);
        fourth_question.setText(itemView.getContext().getString(R.string.FUPR_fourth_question));
        button_next =  itemView.findViewById(R.id.btnNextQuestion);
        button_next.setOnClickListener(new OnNextButtonClickListener ());
        button_next.setEnabled(true);
        answer_input = itemView.findViewById ( R.id.follow_up_result_fourth_input );
        answer_input.addTextChangedListener ( new FourthAnswerTextWatcher() );
    }

    public void setOnFURFourthViewHolderListener(OnFURFourthViewHolderListener onFURFourthViewHolderListener) {
        this.onFURFourthViewHolderListener = onFURFourthViewHolderListener;
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FollowUpResults_4th_Question_ViewHolder" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            onFURFourthViewHolderListener.onNextClicked ();
        }
    }

    private class FourthAnswerTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
    public interface OnFURFourthViewHolderListener{
        void onNextClicked();
    }
}
