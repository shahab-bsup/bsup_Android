package com.neweraandroid.demo.Activity.Start_a_Refill;

import android.content.Context;
import android.view.View;

import com.neweraandroid.demo.R;

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
                System.out.println ("btnStartRefill");
                break;
            }
        }
    }
}
