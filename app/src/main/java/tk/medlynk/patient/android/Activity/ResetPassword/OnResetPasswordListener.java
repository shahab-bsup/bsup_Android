package tk.medlynk.patient.android.Activity.ResetPassword;

import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.Model.ErrorResponse;

/**
 * Created by Shahab on 1/26/2018.
 */

public interface OnResetPasswordListener {

    void onResetPasswordSuccess(String message);
    void onResetPasswordFailure(ErrorResponse errorResponse, Constants.EXCEPTION_TYPE exception_type);
    void onResetPasswordFailure(Constants.EXCEPTION_TYPE exception_type);

}
