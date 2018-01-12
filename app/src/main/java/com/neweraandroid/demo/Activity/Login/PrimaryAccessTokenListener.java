package com.neweraandroid.demo.Activity.Login;

import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.Model.PrimaryTokenResponse;

/**
 * Created by Shahab on 1/12/2018.
 */

public interface PrimaryAccessTokenListener {
    void onPrimaryAccessTokenSuccess(PrimaryTokenResponse response);
    void onPrimaryAccessTokenFailure(String errorMessage, Constants.EXCEPTION_TYPE noException);
    void onPrimaryAccessTokenFailure(Constants.EXCEPTION_TYPE exception_type);
}
