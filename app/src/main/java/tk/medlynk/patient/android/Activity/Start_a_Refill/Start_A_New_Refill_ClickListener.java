package tk.medlynk.patient.android.Activity.Start_a_Refill;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.Refill.Refill_A_Medication_Activity;

/**
 * Created by Shahab on 2/1/2018.
 */

public class Start_A_New_Refill_ClickListener implements View.OnClickListener {

    private Context context;

    public Start_A_New_Refill_ClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case R.id.btnStartRefill:{
                context.startActivity ( new Intent ( context, Refill_A_Medication_Activity.class ) );
                break;
            }
        }
    }
}
