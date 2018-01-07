package com.bsuperiorsystem.doctor_app.Activity.Login;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingConversion;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.bsuperiorsystem.doctor_app.Activity.ResetPassword.ResetPasswordActivity;
import com.bsuperiorsystem.doctor_app.Activity.SignUp.SignUpActivity;

/**
 * Created by Shahab on 1/6/2018.
 */

public class LoginViewModel extends BaseObservable{

    public ObservableField<String> email;
    public ObservableField<String> password;

    private ObservableBoolean isPasswordLengthEnough, isEMailCorrect, isEMailLengthEnough;

    private Context mContext;

    public LoginViewModel(Context mContext){
        this.mContext = mContext;
        email = new ObservableField<>();
        password = new ObservableField<>();
    }

    public void forgetPasswordActivity(View view){
        mContext.startActivity(new Intent(mContext, ResetPasswordActivity.class));
    }

    public void signUpActivity(View view){
        mContext.startActivity(new Intent(mContext, SignUpActivity.class));
    }

    @BindingConversion
    public String convertBindableToString(BindableString bindableString){
        return bindableString.get();
    }

    public void submit(View view){
//        if( convertBindableToString() == 0 ){
//
//        } else if ( password.get().length() == 0 ){
//
//        }
    }
}
