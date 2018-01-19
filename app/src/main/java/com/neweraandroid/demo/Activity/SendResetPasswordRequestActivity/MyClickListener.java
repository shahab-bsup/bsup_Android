package com.neweraandroid.demo.Activity.SendResetPasswordRequestActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.neweraandroid.demo.Activity.SignUp.SignUpViewHolder;
import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.CustomViews.SnackController;
import com.neweraandroid.demo.Essentials.Utils;
import com.neweraandroid.demo.Networking.MedlynkRequests;
import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 1/19/2018.
 */

public class MyClickListener implements OnSendResetPasswordRequestListener,

View.OnClickListener{

    private Context context;
    private String email;

    public MyClickListener(Context context) {
        this.context = context;
    }

    public void onSubmitClicked(String s){
        email = s;
        if( !Utils.isEmailValid ( s ) ){
            SnackController.getInstance ().init ( context,
                    "Email is not valid!", Snackbar.LENGTH_LONG)
                    .showSnackBar ();
        }else{
            MedlynkRequests.sendResetPasswordRequest (context, this ,s );
        }
    }

    @Override
    public void onSendResetPasswordRequestSuccess() {
        SnackController.getInstance ().init ( context,
                "An Email has been sent to your mailbox", Snackbar.LENGTH_LONG)
                .showSnackBar ();
    }

    @Override
    public void onSendResetPasswordRequestFailure(String message, Constants.EXCEPTION_TYPE exception_type) {
        if( exception_type == Constants.EXCEPTION_TYPE.NO_EXCEPTION ){
            SnackController.getInstance ().
                    init ( context, message, Snackbar.LENGTH_LONG )
                    .showSnackBar ();
        }
    }

    @Override
    public void onSendResetPasswordRequestFailure(Constants.EXCEPTION_TYPE exception_type) {
        if( exception_type == Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION ){
            SnackController.getInstance ().
                    init ( context,  R.string.timeout_exception, Snackbar.LENGTH_LONG)
                    .setAction ( R.string.try_again, MyClickListener.this )
                    .showSnackBar ();
        }else if ( exception_type == Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION || exception_type == Constants.EXCEPTION_TYPE.BAD_EXCEPTION ){
            SnackController.getInstance ().
                    init ( context,   R.string.something_bad_happened, Snackbar.LENGTH_LONG)
                    .showSnackBar ();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case android.support.design.R.id.snackbar_action:{
                onSubmitClicked ( email );
                break;
            }
        }
    }
}