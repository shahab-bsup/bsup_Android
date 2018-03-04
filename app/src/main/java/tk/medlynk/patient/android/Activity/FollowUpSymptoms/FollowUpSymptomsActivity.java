package tk.medlynk.patient.android.Activity.FollowUpSymptoms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.neweraandroid.demo.R;

public class FollowUpSymptomsActivity extends AppCompatActivity {

    View toolbar_view;
    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_follow_up_sysmptoms );
        toolbar_view = findViewById ( R.id.follow_up_symptom_toolbar_layout );
        toolbar_title = toolbar_view.findViewById ( R.id.toolbar_title );
        toolbar_title.setText ( R.string.follow_up_symptoms_title );
    }
}
