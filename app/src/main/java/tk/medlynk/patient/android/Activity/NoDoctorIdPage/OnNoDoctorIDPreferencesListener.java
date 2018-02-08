package tk.medlynk.patient.android.Activity.NoDoctorIdPage;

import tk.medlynk.patient.android.Constants;

/**
 * Created by Shahab on 2/2/2018.
 */

public interface OnNoDoctorIDPreferencesListener {

    void onNoDoctorIDSuccess(Boolean body);
    void onNoDoctorIDFailure();
    void onNoDoctorIDFailure(Constants.EXCEPTION_TYPE exception_type);
}
