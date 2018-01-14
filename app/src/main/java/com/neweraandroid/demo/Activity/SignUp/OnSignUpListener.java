package com.neweraandroid.demo.Activity.SignUp;

import com.neweraandroid.demo.Constants;

/**
 * Created by Shahab on 1/15/2018.
 */

public interface OnSignUpListener {
    void onSignUpSuccess();
    void onSignUpFailure(String message, Constants.EXCEPTION_TYPE exception_type);
    void onSignUpFailure(Constants.EXCEPTION_TYPE exception_type);
}
