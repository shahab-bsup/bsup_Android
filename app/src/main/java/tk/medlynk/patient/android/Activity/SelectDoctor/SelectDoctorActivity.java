package tk.medlynk.patient.android.Activity.SelectDoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.AppointmentResponse;
import tk.medlynk.patient.android.Model.SearchDoctorResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

import com.neweraandroid.demo.R;

public class SelectDoctorActivity extends AppCompatActivity
        implements OnGetAppointmentsListener,
        SelectDoctorViewHolder.OnWrongDoctorIdClickListener{

    SearchDoctorResponse searchDoctorResponse;
    SelectDoctorViewHolder selectDoctorViewHolder;
    View parent_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_select_doctor );
        parent_view = findViewById ( R.id.parent_select_doctor );
        selectDoctorViewHolder = new SelectDoctorViewHolder ( parent_view );
        selectDoctorViewHolder.setOnWrongDoctorIdClickListener ( this );
        Intent intent = getIntent ();
        if( intent != null ){
            searchDoctorResponse = (SearchDoctorResponse) intent.getSerializableExtra ( Constants.SelectedDoctor );
            selectDoctorViewHolder.setData(searchDoctorResponse);
        }
        getAppointments(searchDoctorResponse.getReceivedMedicalInfo ().getId ());
    }

    private void getAppointments(String id) {
        MedlynkRequests.getAppointments ( this, this, id );
    }

    @Override
    public void onGetAppointmentSuccess(AppointmentResponse response) {
        System.out.println ( "SelectDoctorActivity.onGetAppointmentSuccess" );
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager ( this );
        sharedPreferenceManager.setAppointmentID(response.getData ().getId ());
        selectDoctorViewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }

    @Override
    public void onGetAppointmentFailure() {
        System.out.println ( "SelectDoctorActivity.onGetAppointmentFailure" );
        selectDoctorViewHolder.setProgressBarVisibilityStatus ( View.GONE );
    }

    @Override
    public void onWrongDoctorIdClicked() {
        onBackPressed ();
    }
}
