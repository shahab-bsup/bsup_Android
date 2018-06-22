package tk.medlynk.patient.android.Activity.SelectDoctor;

import tk.medlynk.patient.android.Model.AppointmentResponse;

/**
 * Created by Shahab on 2/22/2018.
 */

public interface OnGetAppointmentsListener {

    void onGetAppointmentSuccess(AppointmentResponse response);
    void onGetAppointmentFailure();

}
