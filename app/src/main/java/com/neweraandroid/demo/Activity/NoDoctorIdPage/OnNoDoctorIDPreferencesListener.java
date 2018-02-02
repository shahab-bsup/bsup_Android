package com.neweraandroid.demo.Activity.NoDoctorIdPage;

import com.neweraandroid.demo.Constants;

/**
 * Created by Shahab on 2/2/2018.
 */

public interface OnNoDoctorIDPreferencesListener {

    void onNoDoctorIDSuccess(Boolean body);
    void onNoDoctorIDFailure();
    void onNoDoctorIDFailure(Constants.EXCEPTION_TYPE exception_type);
}
