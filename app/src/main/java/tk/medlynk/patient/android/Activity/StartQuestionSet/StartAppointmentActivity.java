package tk.medlynk.patient.android.Activity.StartQuestionSet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.neweraandroid.demo.R;

public class StartAppointmentActivity extends AppCompatActivity {

    View parent, toolbar_view;
    StartQuestionSetViewHolder viewHolder;
    TextView toolbar_text;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_start_appointment );
        parent = findViewById ( R.id.parent );
        viewHolder = new StartQuestionSetViewHolder ( parent );
        toolbar_view = findViewById ( R.id.toolbar_start_question_set );
        toolbar_text = toolbar_view.findViewById ( R.id.toolbar_text );
        toolbar_text.setText ( "Question Set!" );
        backButton = toolbar_view.findViewById ( R.id.imgBackButton );
        backButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                onBackPressed ();
            }
        } );
    }
}