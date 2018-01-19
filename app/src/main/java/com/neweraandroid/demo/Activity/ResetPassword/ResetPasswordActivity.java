package com.neweraandroid.demo.Activity.ResetPassword;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.neweraandroid.demo.CustomViews.SnackController;
import com.neweraandroid.demo.Essentials.Utils;
import com.neweraandroid.demo.Networking.MedlynkRequests;
import com.neweraandroid.demo.R;


public class ResetPasswordActivity extends AppCompatActivity
implements ResetPasswordViewHolder.OnButtonClickListener{

    View view;
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent ( intent );
        System.out.println ("onNewIntent!");
    }

    private void handleIntent() {
        Intent appLinkIntent = getIntent ();
        String appLinkAction = appLinkIntent.getAction ();
        Uri appLinkData = appLinkIntent.getData ();
        System.out.println ("appLinkData = ");

    }

    @Override
    public void onClick(String email, String confirmedEmail) {
        if( !Utils.isEmailValid ( email ) ){
            viewHolder.getPasswordEditText ().setError ( "Email is not valid!" );
        } else if( !email.equals ( confirmedEmail ) ){
            SnackController.getInstance ()
                    .init ( ResetPasswordActivity.this, R.string.confirmation_did_not_match, Snackbar.LENGTH_LONG )
                    .showSnackBar ();
        } else{

        }
    }
}
