package tk.medlynk.patient.android.Activity.SendResetPasswordRequestActivity;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 1/14/2018.
 */

public class SendResetPasswordViewHolder extends RecyclerView.ViewHolder {

    private Button submit;
    private EditText email;
    private ProgressBar progressBar;
    private OnSendResetPasswordClickListener onSendResetPasswordClickListener;

    public SendResetPasswordViewHolder(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        email = itemView.findViewById ( R.id.edtEmail );
        email.addTextChangedListener ( new EmailTextWatcher() );
        submit = itemView.findViewById ( R.id.btnSendResetPasswordReq );
        submit.setOnClickListener ( new SendRequestPasswordClickListener () );
        submit.setClickable ( false );
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnSendResetPasswordClickListener(OnSendResetPasswordClickListener onSendResetPasswordClickListener) {
        this.onSendResetPasswordClickListener = onSendResetPasswordClickListener;
    }

    private class SendRequestPasswordClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            System.out.println ( "SendRequestPasswordClickListener.onClick" );
            onSendResetPasswordClickListener.onClick ( email.getText ().toString () );
        }
    }

    public interface OnSendResetPasswordClickListener {
        void onClick(String email);
    }

    private class EmailTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if( editable.length () == 0 ){
                submit.setClickable ( false );
                submit.setBackgroundResource ( R.drawable.disabled_custom_button );
            }else{
                submit.setClickable ( true );
                submit.setBackgroundResource ( R.drawable.enabled_custom_button );
            }
        }
    }
}
