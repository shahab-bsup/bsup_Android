package tk.medlynk.patient.android.Activity.Start_a_Refill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.neweraandroid.demo.R;

public class Start_A_New_RefillActivity extends AppCompatActivity {

    View parent;
    Start_A_New_Refill_ViewHolder viewHolder;
    View toolbar;
    TextView toolbar_text;
    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_start_a_new_refill_ );
        parent = findViewById ( R.id.parent_refill_a_medication );
        viewHolder = new Start_A_New_Refill_ViewHolder ( parent );
        toolbar = findViewById ( R.id.refill_toolbar_layout );
        toolbar_text = toolbar.findViewById ( R.id.toolbar_text );
        toolbar_text.setText ( getString ( R.string.refill_toolbar_text ) );
        back_button = toolbar.findViewById ( R.id.imgBackButton );
        back_button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                onBackPressed ();
            }
        } );
    }

    @Override
    protected void onResume() {
        super.onResume ();

    }

    @Override
    protected void onPause() {
        super.onPause ();

    }
}