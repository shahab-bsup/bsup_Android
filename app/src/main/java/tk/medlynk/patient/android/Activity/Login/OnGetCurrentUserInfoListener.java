package tk.medlynk.patient.android.Activity.Login;

import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.Model.CurrentUserResponse;

/**
 * Created by Shahab on 2/2/2018.
 */

public interface OnGetCurrentUserInfoListener {
    void onGetCurrentUserInfoSuccess(CurrentUserResponse currentUserResponse);
    void onGetCurrentUserInfoFailure();
    void onGetCurrentUserInfoFailure(Constants.EXCEPTION_TYPE exception_type);
}
