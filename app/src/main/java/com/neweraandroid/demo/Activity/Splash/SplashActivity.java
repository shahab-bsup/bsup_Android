package com.neweraandroid.demo.Activity.Splash;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.neweraandroid.demo.Activity.Login.LoginActivity;
import com.neweraandroid.demo.Activity.SearchDoctor.SearchActivity;
import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.CustomViews.SnackController;
import com.neweraandroid.demo.Model.InitialTokenResponse;
import com.neweraandroid.demo.Model.RenewTokenResponse;
import com.neweraandroid.demo.Networking.MedlynkRequests;
import com.neweraandroid.demo.R;
import com.neweraandroid.demo.Essentials.SharedPreferenceManager;
import com.neweraandroid.demo.Essentials.Utils;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Response;

public class SplashActivity extends AppCompatActivity implements
        InitialTokenListener,
        View.OnClickListener, RefreshTokenListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if( savedInstanceState == null ){
            //Check for the connectivity of the device!
            if(!Utils.isDeviceConnected ( this )){
                SnackController.getInstance ()
                        .init ( this, R.string.no_intenet_connection, Snackbar.LENGTH_INDEFINITE )
                        .setAction ( R.string.try_again , this ).showSnackBar ();
            }else{
                //First check for nullity of the Token...
                //if token is null: means this is a new user! :) Congrats!
                //otherwise we'll check for the expiry time of the stored token!!!
                SharedPreferenceManager manager = new SharedPreferenceManager ( this );
                if( manager.getInitialToken () == null ){
                    MedlynkRequests.getInitialToken ( this );
                }else if( manager.getPrimaryToken () == null ) {
                    new Timer()
                            .schedule ( new TimerTask () {
                                @Override
                                public void run() {
                                    startActivity ( new Intent ( SplashActivity.this, LoginActivity.class ) );
                                }
                            }, 2000 );
                } else if(Utils.isPrimaryTokenExpired ( this )){
                        MedlynkRequests.getNewPrimaryToken ( this, manager.getRefreshToken () );
                } else{
                        //Elude the user that this is a Splash Screen!
                        new Timer (  )
                                .schedule ( new TimerTask () {
                                    @Override
                                    public void run() {
                                        startActivity ( new Intent ( SplashActivity.this, SearchActivity.class ) );
                                    }
                                }, 3000 );
                    }
                }
            }
        }

    @Override
    public void onInitialTokenSuccess(Response<InitialTokenResponse> response) {
        SharedPreferenceManager manager = new SharedPreferenceManager ( SplashActivity.this );
        manager.setInitialTokenType ( response.body ().getTokenType () );
        manager.setInitialToken ( response.body ().getTokenType () +" " + response.body ().getAccessToken () );
        manager.setInitialExpireToken ( response.body ().getExpiresIn () );
        startActivity ( new Intent ( SplashActivity.this, LoginActivity.class ) );
    }

    @Override
    public void onInitialTokenFailure(Constants.EXCEPTION_TYPE exception_type) {
        if( exception_type == Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION ){
            SnackController.getInstance ().
                    init ( SplashActivity.this,  R.string.timeout_exception, Snackbar.LENGTH_LONG)
                    .setAction ( R.string.try_again, SplashActivity.this )
                    .showSnackBar ();
        }else if ( exception_type == Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION || exception_type == Constants.EXCEPTION_TYPE.BAD_EXCEPTION ){
            SnackController.getInstance ().
                    init ( SplashActivity.this,  R.string.something_bad_happened, Snackbar.LENGTH_LONG)
                    .showSnackBar ();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case android.support.design.R.id.snackbar_action:{
                //Check for the connectivity of the device!
                if(!Utils.isDeviceConnected ( this )){
                    SnackController.getInstance ()
                            .init ( this, R.string.no_intenet_connection , Snackbar.LENGTH_INDEFINITE )
                            .setAction ( R.string.try_again , this ).showSnackBar ();
                }else {
                    //First check for nullity of the Token...
                    //if token is null: means this is a new user! :) Congrats!
                    //otherwise we'll check for the expiry time of the stored token!!!
                    SharedPreferenceManager manager = new SharedPreferenceManager ( this );
                    if( manager.getInitialToken () == null ){
                        MedlynkRequests.getInitialToken ( this );
                    }else{
                        if(Utils.isTokenExpired ( this )){
                            //Elude the user that this is a splash screen :)
                            new Timer (  )
                                    .schedule ( new TimerTask () {
                                        @Override
                                        public void run() {
                                            startActivity ( new Intent ( SplashActivity.this, LoginActivity.class ) );
                                        }
                                    }, 2000 );
                        } else{
                            //Elude again :)
                            new Timer (  )
                                    .schedule ( new TimerTask () {
                                        @Override
                                        public void run() {
                                            startActivity ( new Intent ( SplashActivity.this, SearchActivity.class ) );
                                        }
                                    }, 2000 );
                        }
                    }
                }
                break;
            }
        }
    }

    @Override
    public void onRefreshTokenSuccess(RenewTokenResponse renewTokenResponse) {
        SharedPreferenceManager manager = new SharedPreferenceManager ( SplashActivity.this );
        manager.setPrimaryToken ( renewTokenResponse.getAccessToken () );
        manager.setRefreshToken ( renewTokenResponse.getRefreshToken () );
        manager.setPrimaryExpireToken ( renewTokenResponse.getExpiresIn () );
        new Timer (  )
                .schedule ( new TimerTask () {
                    @Override
                    public void run() {
                        startActivity ( new Intent ( SplashActivity.this, LoginActivity.class ) );
                    }
                }, 2000 );
    }

    @Override
    public void onRefreshTokenFailure(Constants.EXCEPTION_TYPE exception_type) {
        if( exception_type == Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION ){
            SnackController.getInstance ().
                    init ( SplashActivity.this,  R.string.timeout_exception, Snackbar.LENGTH_LONG)
                    .setAction ( R.string.try_again, SplashActivity.this )
                    .showSnackBar ();
        }else if ( exception_type == Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION || exception_type == Constants.EXCEPTION_TYPE.BAD_EXCEPTION ){
            SnackController.getInstance ().
                    init ( SplashActivity.this,  R.string.something_bad_happened, Snackbar.LENGTH_LONG)
                    .showSnackBar ();
        }
    }
}
