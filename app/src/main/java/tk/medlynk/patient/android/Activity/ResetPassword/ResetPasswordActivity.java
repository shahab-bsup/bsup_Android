package tk.medlynk.patient.android.Activity.ResetPassword;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import tk.medlynk.patient.android.Activity.Login.LoginActivity;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.CustomViews.SnackController;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.ErrorResponse;
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

    private void handleIntent() {
        Intent appLinkIntent = getIntent ();
        Uri appLinkData = appLinkIntent.getData ();
        String data = String.valueOf ( appLinkData );
        String[] strings = data.split ( "/" );
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
            viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
            SharedPreferenceManager manager = new SharedPreferenceManager ( ResetPasswordActivity.this );
            MedlynkRequests.resetPassword ( ResetPasswordActivity.this,
                    reset_token,
                    manager.getEmail (),
                    password ,
                    ResetPasswordActivity.this);
        }
    }
    
    @Override
    public void onResetPasswordSuccess(String message) {
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        startActivity ( new Intent ( ResetPasswordActivity.this, LoginActivity.class ) );
        finish ();
    }

    @Override
    public void onResetPasswordFailure(ErrorResponse errorResponse, Constants.EXCEPTION_TYPE exception_type) {
        System.out.println ( "ResetPasswordActivity.onResetPasswordFailure" );
        System.out.println ( "message = [" + errorResponse + "], exception_type = [" + exception_type + "]" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
        if( errorResponse != null && errorResponse.getErrors () != null ){
            if( errorResponse.getErrors ().getPassword () != null ){
                viewHolder.setPassWordError (  errorResponse.getErrors ().getPassword ().get ( 0 )  );
            }
        }else{
            Toast.makeText ( this, "something bad happened!", Toast.LENGTH_SHORT ).show ();
        }
    }

    @Override
    public void onResetPasswordFailure(Constants.EXCEPTION_TYPE exception_type) {
        System.out.println ( "ResetPasswordActivity.onResetPasswordFailure" );
        System.out.println ( "exception_type = [" + exception_type + "]" );
        viewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }
}