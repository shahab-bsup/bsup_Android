package tk.medlynk.patient.android.Activity.SelectDoctor;

import android.content.Context;
import android.content.Intent;

import tk.medlynk.patient.android.Activity.StartQuestionSet.StartAppointmentActivity;

/**
 * Created by Shahab on 1/20/2018.
 */

public class SelectDoctorClickListener {

    private Context context;

    public SelectDoctorClickListener(Context context) {
        this.context = context;
    }

    public void onSelectDoctorClicked(String doctorID){
        System.out.println ("onSelectDoctorClicked");
        context.startActivity ( new Intent ( context, StartAppointmentActivity.class ).putExtra ( "Doctor ID" , doctorID) );
    }
}
