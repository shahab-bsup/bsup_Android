package tk.medlynk.patient.android.Activity.SendResetPasswordRequestActivity;

import android.app.DatePickerDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.CustomViews.SnackController;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Essentials.Utils;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

import com.neweraandroid.demo.R;


public class SendResetPasswordRequestActivity extends AppCompatActivity
    implements
        SendResetPasswordViewHolder.OnSendResetPasswordClickListener,
        OnSendResetPasswordRequestListener,
        View.OnClickListener
{

    View parent_view;
    SendResetPasswordViewHolder viewHolder;
    private String email = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_send_reset_password_request);
        if( savedInstanceState == null ){
            parent_view = findViewById ( R.id.parent_activity_send_reset_password_request );
            viewHolder = new SendResetPasswordViewHolder ( parent_view );
            viewHolder.setOnSendResetPasswordClickListener ( this );
        }
    }

    @Override
    public void onSendResetPasswordRequestSuccess() {
        System.out.println ( "SendResetPasswordRequestActivity.onSendResetPasswordRequestSuccess" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        SnackController.getInstance ().init ( SendResetPasswordRequestActivity.this,
                "An Email has been sent to your mailbox",
                Snackbar.LENGTH_LONG)
                .showSnackBar ();
    }

    @Override
    public void onSendResetPasswordRequestFailure(String message, Constants.EXCEPTION_TYPE exception_type) {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        if( exception_type == Constants.EXCEPTION_TYPE.NO_EXCEPTION ){
            SnackController.getInstance ().
                    init ( SendResetPasswordRequestActivity.this,
                            message,
                            Snackbar.LENGTH_LONG )
                    .showSnackBar ();
        }
    }

    @Override
    public void onSendResetPasswordRequestFailure(Constants.EXCEPTION_TYPE exception_type) {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        if( exception_type == Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION ){
            SnackController.getInstance ().
                    init ( SendResetPasswordRequestActivity.this,
                            R.string.timeout_exception,
                            Snackbar.LENGTH_LONG)
                    .setAction ( R.string.try_again,
                            SendResetPasswordRequestActivity.this )
                    .showSnackBar ();
        }else if ( exception_type == Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION || exception_type == Constants.EXCEPTION_TYPE.BAD_EXCEPTION ){
            SnackController.getInstance ().
                    init ( SendResetPasswordRequestActivity.this,
                            R.string.something_bad_happened,
                            Snackbar.LENGTH_LONG)
                    .showSnackBar ();
        }
    }

    @Override
    public void onClick(String email) {
        System.out.println ( "SendResetPasswordRequestActivity.onClick" );
        System.out.println ( "email = [" + email + "]" );
        sendRequest ( email );
    }

    private void sendRequest(String email) {
        this.email = email;
        if( !Utils.isEmailValid ( email ) ){
            SnackController.getInstance ().init ( SendResetPasswordRequestActivity.this,
                    "Email is not valid!", Snackbar.LENGTH_LONG)
                    .showSnackBar ();
        }else{
            viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
            SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager ( SendResetPasswordRequestActivity.this );
            sharedPreferenceManager.setEmail ( email );
            MedlynkRequests.sendResetPasswordRequest (SendResetPasswordRequestActivity.this,
                    this,
                    email);
        }
    }

    @Override
    public void onClick(View view) {
        System.out.println ( "SendResetPasswordRequestActivity.onClick" );
        System.out.println ( "view = [" + view + "]" );
        switch (view.getId ()){
            case android.support.design.R.id.snackbar_action:{
                sendRequest ( email );
                break;
            }
        }
    }
}