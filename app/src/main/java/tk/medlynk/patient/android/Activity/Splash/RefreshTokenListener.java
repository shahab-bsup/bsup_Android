package tk.medlynk.patient.android.Activity.Splash;

import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.Model.RenewTokenResponse;

/**
 * Created by Shahab on 1/12/2018.
 */

public interface RefreshTokenListener {
    void onRefreshTokenSuccess(RenewTokenResponse renewTokenResponse);
    void onRefreshTokenFailure(Constants.EXCEPTION_TYPE exception_type);
}
