package tk.medlynk.patient.android.Activity.SelectDoctor;

import tk.medlynk.patient.android.Model.AppointmentsResponse;

/**
 * Created by Shahab on 2/22/2018.
 */

public interface OnGetAppointmentsListener {

    void onGetAppointmentSuccess(AppointmentsResponse response);
    void onGetAppointmentFailure();

}
