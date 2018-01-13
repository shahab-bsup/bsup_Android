package com.neweraandroid.demo.Activity.Login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neweraandroid.demo.Activity.SearchDoctor.SearchActivity;
import com.neweraandroid.demo.Activity.SendResetPasswordRequestActivity.SendResetPasswordRequestActivity;
import com.neweraandroid.demo.Activity.SignUp.SignUpActivity;
import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.CustomViews.SnackController;
import com.neweraandroid.demo.Essentials.SharedPreferenceManager;
import com.neweraandroid.demo.Essentials.Utils;
import com.neweraandroid.demo.Model.PrimaryTokenResponse;
import com.neweraandroid.demo.Networking.MedlynkRequests;
import com.neweraandroid.demo.R;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener ,
        PrimaryAccessTokenListener{

//    LoginViewModel loginViewModel;
    Button submit;
    EditText email, password;
    TextView create_new_account, forget_password;
    ProgressBar progressBar;
    boolean isEmailValid, isPassWordValid;

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
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId ();
        switch (id){
            case R.id.btnLogin:{
                isEmailValid = email.getText ().length () == 0 ? false : Utils.isEmailValid ( email.getText ().toString () );
                isPassWordValid = password.getText ().length () == 0 ? false : Utils.isPasswordValid ( password.getText ().toString () );
                if(  isEmailValid && isPassWordValid){
                    /*TODO send the request to server!!!*/
                    MedlynkRequests.getPrimaryAccessToken ( LoginActivity.this, email.getText ().toString (), password.getText ().toString () );
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

            case android.support.design.R.id.snackbar_action:{
                submit.setOnClickListener ( LoginActivity.this );
                break;
            }
        }
    }

    @Override
    public void onPrimaryAccessTokenSuccess(PrimaryTokenResponse response) {
        SharedPreferenceManager manager = new SharedPreferenceManager ( LoginActivity.this );
        manager.setPrimaryToken ( response.getAccessToken () );
        manager.setPrimaryTokenType ( response.getTokenType () );
        manager.setPrimaryExpireToken ( response.getExpiresIn () );
        manager.setRefreshToken ( response.getRefreshToken () );

        startActivity ( new Intent ( LoginActivity.this, SearchActivity.class ) );
    }

    @Override
    public void onPrimaryAccessTokenFailure(String errorMessage, Constants.EXCEPTION_TYPE exception_type) {
        if( exception_type == Constants.EXCEPTION_TYPE.NO_EXCEPTION ){
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
}
