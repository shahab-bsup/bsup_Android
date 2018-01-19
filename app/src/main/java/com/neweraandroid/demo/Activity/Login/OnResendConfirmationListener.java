package com.neweraandroid.demo.Activity.Login;

import com.neweraandroid.demo.Constants;

/**
 * Created by Shahab on 1/19/2018.
 */

public interface OnResendConfirmationListener {
    void onResendConfirmationLinkSuccess();
    void onResendConfirmationLinkFailure(String errorMessage, Constants.EXCEPTION_TYPE exception_type);
    void onResendConfirmationLinkFailure(Constants.EXCEPTION_TYPE exception_type);
}
