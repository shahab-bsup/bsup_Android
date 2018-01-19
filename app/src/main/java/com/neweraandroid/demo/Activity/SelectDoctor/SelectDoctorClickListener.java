package com.neweraandroid.demo.Activity.SelectDoctor;

import android.content.Context;
import android.view.View;

/**
 * Created by Shahab on 1/20/2018.
 */

public class SelectDoctorClickListener {

    private Context context;

    public SelectDoctorClickListener(Context context) {
        this.context = context;
    }

    public void onSelectDoctorClicked(){
        System.out.println ("onSelectDoctorClicked");
    }

    public void onWrongDoctorIdClicked() {
        System.out.println ("onWrongDoctorIdClicked");
    }
}
