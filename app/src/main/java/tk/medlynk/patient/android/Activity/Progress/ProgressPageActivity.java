package tk.medlynk.patient.android.Activity.Progress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.neweraandroid.demo.R;

public class ProgressPageActivity extends AppCompatActivity {

    private ProgressPage_VH viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_progress_page2 );
       // viewHolder = new ProgressPage_VH ( findViewById ( R.id.activity_progress_page ) );

    }
}
