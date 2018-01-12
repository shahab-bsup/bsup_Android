package com.neweraandroid.demo.Activity.Splash;

import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.Model.RenewTokenResponse;

/**
 * Created by Shahab on 1/12/2018.
 */

public interface RefreshTokenListener {
    void onRefreshTokenSuccess(RenewTokenResponse renewTokenResponse);
    void onRefreshTokenFailure(Constants.EXCEPTION_TYPE exception_type);
}
