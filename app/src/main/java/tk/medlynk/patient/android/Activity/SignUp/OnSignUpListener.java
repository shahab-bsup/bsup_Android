package tk.medlynk.patient.android.Activity.SignUp;

import tk.medlynk.patient.android.Constants;

/**
 * Created by Shahab on 1/15/2018.
 */

public interface OnSignUpListener {
    void onSignUpSuccess();
    void onSignUpFailure(String message, Constants.EXCEPTION_TYPE exception_type);
    void onSignUpFailure(Constants.EXCEPTION_TYPE exception_type);
}
