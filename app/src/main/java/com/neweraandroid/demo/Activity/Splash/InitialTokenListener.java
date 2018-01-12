package com.neweraandroid.demo.Activity.Splash;

import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.Model.InitialTokenResponse;

import retrofit2.Response;

/**
 * Created by Shahab on 1/11/2018.
 */

public interface InitialTokenListener {
    void onInitialTokenSuccess(Response<InitialTokenResponse> response);
    void onInitialTokenFailure(Constants.EXCEPTION_TYPE exception_type);
}
