package com.bsuperiorsystem.doctor_app.Activity.Login;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bsuperiorsystem.doctor_app.Activity.ResetPassword.ResetPasswordActivity;
import com.bsuperiorsystem.doctor_app.Activity.SignUp.SignUpActivity;

/**
 * Created by Shahab on 1/6/2018.
 */

public class LoginViewModel implements LoginModel{

    private Context mContext;

    public LoginViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void setVisibility(View view){
        view.setVisibility(View.VISIBLE);
    }

    public void forgetPasswordActivity(View view){
        mContext.startActivity(new Intent(mContext, ResetPasswordActivity.class));
    }

    public void signUpActivity(View view){
        mContext.startActivity(new Intent(mContext, SignUpActivity.class));
    }

    @Override
    public void loginSendBasics(String email, String password) {

    }
}
