package tk.medlynk.patient.android.Activity.Splash;

import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.Model.InitialTokenResponse;

import retrofit2.Response;

/**
 * Created by Shahab on 1/11/2018.
 */

public interface InitialTokenListener {
    void onInitialTokenSuccess(Response<InitialTokenResponse> response);
    void onInitialTokenFailure(Constants.EXCEPTION_TYPE exception_type);
}
