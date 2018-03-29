package tk.medlynk.patient.android.Activity.SignUp;

import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.Model.ErrorResponse;

/**
 *
 * Created by Shahab on 1/15/2018...
 *
 */

public interface OnSignUpListener {
    void onSignUpSuccess();
    void onSignUpFailure(ErrorResponse errorResponse, Constants.EXCEPTION_TYPE exception_type);
    void onSignUpFailure(Constants.EXCEPTION_TYPE exception_type);
}
