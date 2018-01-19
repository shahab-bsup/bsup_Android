package com.neweraandroid.demo.Activity.SendResetPasswordRequestActivity;

import com.neweraandroid.demo.Constants;

/**
 * Created by Shahab on 1/19/2018.
 */

public interface OnSendResetPasswordRequestListener {
    void onSendResetPasswordRequestSuccess();
    void onSendResetPasswordRequestFailure(String message, Constants.EXCEPTION_TYPE exception_type);
    void onSendResetPasswordRequestFailure(Constants.EXCEPTION_TYPE exception_type);
}
