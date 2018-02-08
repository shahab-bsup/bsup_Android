package tk.medlynk.patient.android.Activity.SearchDoctor;

import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.Model.SearchDoctorResponse;

/**
 * Created by Shahab on 1/19/2018.
 */

public interface OnSearchDoctorListener {
    void onSearchDoctorSuccess(SearchDoctorResponse response);
    void onSearchDoctorFailure(String errorMessage, Constants.EXCEPTION_TYPE exception_type);
    void onSearchDoctorFailure(Constants.EXCEPTION_TYPE exception_type);
}
