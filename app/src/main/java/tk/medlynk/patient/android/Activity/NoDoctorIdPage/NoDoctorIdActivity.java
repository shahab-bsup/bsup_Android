package tk.medlynk.patient.android.Activity.NoDoctorIdPage;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import tk.medlynk.patient.android.Activity.NewSymptom.NewSymptomActivity;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.CustomViews.SnackController;
import tk.medlynk.patient.android.Model.CurrentUserInfo;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import com.neweraandroid.demo.R;

import java.util.HashMap;

public class NoDoctorIdActivity extends AppCompatActivity
implements OnNoDoctorIDPreferencesListener {

    View toolbar_layout;
    TextView toolbar_text;
    ImageView backButton;
    CheckBox got_the_process_preference;
    Button button;
    Boolean no_doctor_ID_user_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_no_doctor_id );
        button = findViewById ( R.id.btnGoToQuestionsNoDoctorId );
        button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> body = new HashMap<> (  );
                body.put ( "key", "skip_no_doctor_id_page" );
                body.put ( "value", no_doctor_ID_user_preferences );
                MedlynkRequests.setUserPreferencesNoDoctorID ( NoDoctorIdActivity.this,
                        NoDoctorIdActivity.this, body);
            }
        } );
        toolbar_layout = findViewById ( R.id.toolbar_no_doctor_id );
        toolbar_text = toolbar_layout.findViewById ( R.id.toolbar_text );
        toolbar_text.setText ( getString ( R.string.no_doctor_id ) );
        backButton = findViewById ( R.id.imgBackButton );
        backButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                onBackPressed ();
            }
        } );
        got_the_process_preference = findViewById ( R.id.chkbox_no_drid );
        got_the_process_preference.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                no_doctor_ID_user_preferences= b;
            }
        } );
    }

    @Override
    public void onNoDoctorIDSuccess(Boolean body) {
        CurrentUserInfo.getInstance ().getPreferences ().setSkipNoDoctorIdPage ( String.valueOf ( no_doctor_ID_user_preferences ) );
        startActivity ( new Intent ( NoDoctorIdActivity.this, NewSymptomActivity.class ) );
    }

    @Override
    public void onNoDoctorIDFailure() {
        SnackController.getInstance ().init ( NoDoctorIdActivity.this,
                getString ( R.string.somethig_went_wrong ), Snackbar.LENGTH_LONG)
                .showSnackBar ();
    }

    @Override
    public void onNoDoctorIDFailure(Constants.EXCEPTION_TYPE exception_type) {
        SnackController.getInstance ().init ( NoDoctorIdActivity.this,
                getString ( R.string.timeout_exception ), Snackbar.LENGTH_LONG)
                .showSnackBar ();
    }

}
