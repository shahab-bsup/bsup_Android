package tk.medlynk.patient.android.Activity.ResetPassword;

import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import tk.medlynk.patient.android.Activity.Login.LoginActivity;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.CustomViews.SnackController;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import com.neweraandroid.demo.R;


public class ResetPasswordActivity extends AppCompatActivity
implements ResetPasswordViewHolder.OnButtonClickListener,
OnResetPasswordListener
{

    View view;
    String reset_token;
    private ResetPasswordViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_reset_password );
        if (savedInstanceState == null) {
            view = findViewById ( R.id.activity_reset_password );
            viewHolder = new ResetPasswordViewHolder ( view );
            viewHolder.setOnButtonClickListener ( this );
        }

        // ATTENTION: This was auto-generated to handle app links.
        handleIntent ();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState ( outState, outPersistentState );
        outState.putString ( Constants.Reset_Token, reset_token );
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState ( savedInstanceState );
        reset_token = savedInstanceState.getString ( Constants.Reset_Token );
    }

    private void handleIntent() {
        Intent appLinkIntent = getIntent ();
        Uri appLinkData = appLinkIntent.getData ();
        reset_token = String.valueOf ( appLinkData ).substring ( 34 );
    }



    @Override
    public void onClick(String password, String confirmedEmail) {
        if( password.length () < 6 ){
            SnackController.getInstance ().
                    init ( ResetPasswordActivity.this,
                            "Password must be at least 6 characters!")
                    .showSnackBar ();
        } else if( !password.equals ( confirmedEmail ) ){
            SnackController.getInstance ().
                    init ( ResetPasswordActivity.this,
                            "Passwords does not match!")
                    .showSnackBar ();
        } else{
            SharedPreferenceManager manager = new SharedPreferenceManager ( ResetPasswordActivity.this );
            MedlynkRequests.resetPassword ( ResetPasswordActivity.this, reset_token, manager.getEmail (), password , ResetPasswordActivity.this);
        }
    }

    @Override
    public void onResetPasswordSuccess(String message) {
        Toast.makeText ( this, message + "\nLogin Now!", Toast.LENGTH_SHORT ).show ();
        startActivity ( new Intent ( ResetPasswordActivity.this, LoginActivity.class ) );
    }

    @Override
    public void onResetPasswordFailure(String message, Constants.EXCEPTION_TYPE exception_type) {

    }

    @Override
    public void onResetPasswordFailure(Constants.EXCEPTION_TYPE exception_type) {

    }
}