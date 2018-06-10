package tk.medlynk.patient.android.Activity.Login;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import tk.medlynk.patient.android.Activity.SearchDoctor.SearchActivity;
import tk.medlynk.patient.android.Activity.SendResetPasswordRequestActivity.SendResetPasswordRequestActivity;
import tk.medlynk.patient.android.Activity.SignUp.SignUpActivity;
import tk.medlynk.patient.android.Activity.Welcome.WelcomeActivity;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.CustomViews.SnackController;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Essentials.Utils;
import tk.medlynk.patient.android.Model.AccessTokenResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import com.neweraandroid.demo.R;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener ,
        OnPrimaryAccessTokenListener,
        OnResendConfirmationListener
{

//    LoginViewModel loginViewModel;
    Button submit;
    EditText email, password;
    TextView create_new_account, forget_password;
    ProgressBar progressBar;
    boolean isEmailValid, isPassWordValid;
    private SharedPreferenceManager sharedPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        if( savedInstanceState == null ){
            System.out.println ("savedInstanceState in LoginActivity is null!");
            submit = findViewById ( R.id.btnLogin );
            email = findViewById ( R.id.edtUserName );
            password = findViewById ( R.id.edtPassWord );
            progressBar = findViewById ( R.id.loginProgressBar );
            create_new_account = findViewById ( R.id.txtCreateAccount );
            create_new_account.setOnClickListener ( this );
            forget_password = findViewById ( R.id.txtForgetPassword );
            forget_password.setOnClickListener ( this );
            submit.setOnClickListener ( this );
            sharedPreferenceManager = new SharedPreferenceManager ( this );
        }
        handleIntent();

    }

    private void handleIntent() {
        Intent appLinkIntent = getIntent ();
        if( appLinkIntent != null ){
            Uri appLinkData = appLinkIntent.getData ();
            String data = String.valueOf ( appLinkData );
            String[] strings = data.split ( "/" );
            System.out.println (strings.length);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId ();
        switch (id){
            case R.id.btnLogin:{
                hideSoftKeyBoard ();
                isEmailValid = email.getText ().length () == 0 ? false : Utils.isEmailValid ( email.getText ().toString () );
                isPassWordValid = password.getText ().length () == 0 ? false : Utils.isPasswordValid ( password.getText ().toString () );
                if(  isEmailValid && isPassWordValid){
                    /*TODO send the request to server!!!*/
                    sharedPreferenceManager.setEmail ( email.getText ().toString () );
                    sharedPreferenceManager.setPassWord(password.getText ().toString ());
                    MedlynkRequests.getAccessToken ( LoginActivity.this, email.getText ().toString (),
                            password.getText ().toString () );
                }else if ( !isEmailValid && !isPassWordValid ){
                    email.setError ( "Email is Not Valid!" );
                    password.setError ( "PassWord must be at least 6 characters!" );
                } else if ( !isEmailValid ){
                    email.setError ( "Email is Not Valid!" );
                } else {
                    password.setError ( "PassWord must be at least 6 characters!" );
                }
                break;
            }

            case R.id.txtCreateAccount:{
                Intent intent = new Intent ( LoginActivity.this, SignUpActivity.class );
                startActivity ( intent );

                break;
            }

            case R.id.txtForgetPassword:{
                Intent intent = new Intent ( LoginActivity.this, SendResetPasswordRequestActivity.class );
                startActivity ( intent );

                break;
            }

            case android.support.design.R.id.snackbar_action: {
                if( sharedPreferenceManager.getPrimaryToken () == null ) {
                    System.out.println ( "sharedPreferenceManager.getPrimaryToken () == null" );
                    submit.setOnClickListener ( LoginActivity.this );
                }
                break;
            }
        }
    }

    @Override
    public void onPrimaryAccessTokenSuccess(AccessTokenResponse response) {
        SharedPreferenceManager manager = new SharedPreferenceManager ( LoginActivity.this );
        manager.setPrimaryToken ( response.getAccessToken () );
        manager.setPrimaryTokenType ( response.getTokenType () );
        manager.setPrimaryExpireToken ( response.getExpiresIn () );
        manager.setRefreshToken ( response.getRefreshToken () );
        startActivity ( new Intent ( LoginActivity.this, WelcomeActivity.class ) );
        finish ();
    }

    @Override
    public void onPrimaryAccessTokenFailure(String errorMessage, Constants.EXCEPTION_TYPE exception_type) {
        if( errorMessage.equals ( "You have to confirm your account." ) ){
            SnackController.getInstance ().
                    init ( LoginActivity.this, errorMessage, Snackbar.LENGTH_INDEFINITE )
                    .setAction ( "Resend", new View.OnClickListener () {
                        @Override
                        public void onClick(View view) {
                            MedlynkRequests.resendConfirmationEmail ( LoginActivity.this,
                                    LoginActivity.this, sharedPreferenceManager.getEmail ());
                        }
                    } )
                    .showSnackBar ();
        }
        else if( exception_type == Constants.EXCEPTION_TYPE.NO_EXCEPTION ){
            SnackController.getInstance ().
                    init ( LoginActivity.this, errorMessage, Snackbar.LENGTH_LONG )
                    .showSnackBar ();
        }
    }

    @Override
    public void onPrimaryAccessTokenFailure(Constants.EXCEPTION_TYPE exception_type) {
         if( exception_type == Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION ){
            SnackController.getInstance ().
                    init ( LoginActivity.this, R.string.somethig_went_wrong, Snackbar.LENGTH_LONG )
                    .setAction ( R.string.try_again, LoginActivity.this )
                    .showSnackBar ();
        } else if( exception_type == Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION ){
            SnackController.getInstance ().
                    init ( LoginActivity.this, R.string.timeout_exception, Snackbar.LENGTH_LONG )
                    .setAction ( R.string.try_again, LoginActivity.this )
                    .showSnackBar ();
        } else if( exception_type == Constants.EXCEPTION_TYPE.ERROR_RESPONSE_PARSING_EXCEPTION ){
            SnackController.getInstance ().
                    init ( LoginActivity.this, R.string.something_bad_happened, Snackbar.LENGTH_LONG )
                    .setAction ( R.string.try_again, LoginActivity.this )
                    .showSnackBar ();
        }
    }

    @Override
    public void onResendConfirmationLinkSuccess() {
        SnackController.getInstance ().init ( LoginActivity.this,
                "Sent to " + sharedPreferenceManager.getEmail (),
                Snackbar.LENGTH_LONG)
                .showSnackBar ();
    }

    @Override
    public void onResendConfirmationLinkFailure(String errorMessage, Constants.EXCEPTION_TYPE exception_type) {

    }

    @Override
    public void onResendConfirmationLinkFailure(Constants.EXCEPTION_TYPE exception_type) {
        System.out.println ("Failure Occurred!");
    }

    private void hideSoftKeyBoard(){
        View view = this.getCurrentFocus ();
        if(view != null){
            InputMethodManager imm = (InputMethodManager) this.getSystemService ( INPUT_METHOD_SERVICE );
            imm.hideSoftInputFromWindow ( view.getWindowToken (), 0 );
        }
    }

}
