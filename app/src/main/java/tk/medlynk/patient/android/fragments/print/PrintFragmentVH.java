package tk.medlynk.patient.android.fragments.print;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 4/13/2018.
 */

public class PrintFragmentVH extends RecyclerView.ViewHolder {

    private final Button print_this_question_set;
    private final Button print_whole_appointment;
    private final Button cancel;
    private OnPrintVH onPrintViewHolder;

    public PrintFragmentVH(View itemView) {
        super ( itemView );
        print_this_question_set = itemView.findViewById ( R.id.btnPrintThisQuestionSet );
        print_this_question_set.setId ( (int)0 );
        print_this_question_set.setOnClickListener ( new OnButtonClickListener() );
        print_whole_appointment = itemView.findViewById ( R.id.btnPrintWholeAppointment );
        print_whole_appointment.setId ( (int)1 );
        print_whole_appointment.setOnClickListener ( new OnButtonClickListener() );
        cancel = itemView.findViewById ( R.id.btnCancel );
        cancel.setId ( (int)2 );
        cancel.setOnClickListener ( new OnButtonClickListener() );
    }

    public void setOnPrintViewHolder(OnPrintVH onPrintViewHolder) {
        this.onPrintViewHolder = onPrintViewHolder;
    }

    private class OnButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            onPrintViewHolder.onButtonClicked ( view.getId () );
        }
    }

    public interface OnPrintVH{
        void onButtonClicked(int buttonId);
    }
}
