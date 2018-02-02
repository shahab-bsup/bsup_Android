package com.neweraandroid.demo.Activity.ResetPassword;

import com.neweraandroid.demo.Constants;

/**
 * Created by Shahab on 1/26/2018.
 */

public interface OnResetPasswordListener {

    void onResetPasswordSuccess(String message);
    void onResetPasswordFailure(String message, Constants.EXCEPTION_TYPE exception_type);
    void onResetPasswordFailure(Constants.EXCEPTION_TYPE exception_type);

}
