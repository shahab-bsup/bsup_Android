package com.neweraandroid.demo.Activity.Login;

import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.Model.CurrentUserResponse;

/**
 * Created by Shahab on 2/2/2018.
 */

public interface OnGetCurrentUserInfoListener {
    void onGetCurrentUserInfoSuccess(CurrentUserResponse currentUserResponse);
    void onGetCurrentUserInfoFailure();
    void onGetCurrentUserInfoFailure(Constants.EXCEPTION_TYPE exception_type);
}
