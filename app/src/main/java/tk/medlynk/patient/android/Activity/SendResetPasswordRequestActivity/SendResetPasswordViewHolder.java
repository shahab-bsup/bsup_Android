package tk.medlynk.patient.android.Activity.SendResetPasswordRequestActivity;

import android.app.DatePickerDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 1/14/2018.
 */

public class SendResetPasswordViewHolder extends RecyclerView.ViewHolder
        implements DatePickerDialog.OnDateSetListener {

    Button submit, birthDate;
    EditText email;
    MyClickListener myClickListener;

    public SendResetPasswordViewHolder(View itemView) {
        super ( itemView );
        email = itemView.findViewById ( R.id.edtEmail );
        myClickListener = new MyClickListener (itemView.getContext ());
        submit = itemView.findViewById ( R.id.btnSendResetPasswordReq );
        submit.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
            myClickListener.onSubmitClicked (email.getText ().toString () );
            }
        } );
        birthDate = itemView.findViewById ( R.id.btnBirthDate );
        birthDate.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog ( view.getContext (),
                        SendResetPasswordViewHolder.this, 2017, 1, 1);
            }
        } );
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

    interface DateSelectedListener{
        void onDateSelected(int year, int month, int day);
    }
}
