package tk.medlynk.patient.android.Activity.ResetPassword;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.neweraandroid.demo.R;

/**
 *
 * Created by Shahab on 1/14/2018
 *
 */

public class ResetPasswordViewHolder extends RecyclerView.ViewHolder {

    EditText password, confirmedPassword;
    Button button;
    OnButtonClickListener onButtonClickListener;

    public ResetPasswordViewHolder(View itemView) {
        super ( itemView );
        password = itemView.findViewById ( R.id.edtNewPassword );
        confirmedPassword = itemView.findViewById ( R.id.edtConfirmedPassWord );
        button = itemView.findViewById ( R.id.btnResetPassword );
        button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                onButtonClickListener.onClick ( password.getText ().toString (),
                        confirmedPassword.getText ().toString ());
            }
        } );
    }

    public EditText getPasswordEditText(){
        return this.password;
    }

    public void setOnButtonClickListener( OnButtonClickListener onButtonClickListener ){
        if( onButtonClickListener != null ){
            this.onButtonClickListener = onButtonClickListener;
        }else{
            throw new NullPointerException ( "OnButtonClickListener cannot be null!" );
        }
    }

    interface OnButtonClickListener{
        void onClick(String password, String confirmedEmail);
    }
}
