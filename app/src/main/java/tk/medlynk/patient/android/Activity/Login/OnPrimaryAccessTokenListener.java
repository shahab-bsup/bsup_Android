package tk.medlynk.patient.android.Activity.Login;

import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.Model.AccessTokenResponse;

/**
 * Created by Shahab on 1/12/2018.
 */

public interface OnPrimaryAccessTokenListener {
    //called when the response received from server was successful!
    void onPrimaryAccessTokenSuccess(AccessTokenResponse response);
    /*call when the response received from server was unsuccessful
    and it contains a meaningful error message to show to the user!
    */
    void onPrimaryAccessTokenFailure(String errorMessage, Constants.EXCEPTION_TYPE noException);
    /*called when the response received from server was unsuccessful
     and there exists some Exceptions in error response parsing or re
     retrofit exception!
      */
    void onPrimaryAccessTokenFailure(Constants.EXCEPTION_TYPE exception_type);
}
