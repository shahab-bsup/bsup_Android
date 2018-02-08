package tk.medlynk.patient.android.Activity.SelectDoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.Model.SearchDoctorResponse;
import com.neweraandroid.demo.R;

public class SelectDoctorActivity extends AppCompatActivity {

    SearchDoctorResponse searchDoctorResponse;
    SelectDoctorViewHolder selectDoctorViewHolder;
    View parent_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_select_doctor );
        parent_view = findViewById ( R.id.parent_select_doctor );
        selectDoctorViewHolder = new SelectDoctorViewHolder ( parent_view );
        Intent intent = getIntent ();
        if( intent != null ){
            searchDoctorResponse = (SearchDoctorResponse) intent.getSerializableExtra ( Constants.SelectedDoctor );
            selectDoctorViewHolder.setData(searchDoctorResponse);
        }
    }
}
