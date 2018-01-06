package com.bsuperiorsystem.doctor_app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

import com.bsuperiorsystem.doctor_app.R;

public class NoDoctorIDPage extends AppCompatActivity {

    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_doctor_idpage);
        mCheckBox = findViewById(R.id.check_box);
        mCheckBox.setText("Got it! Next Time when I click on the link\n" + "My Doctor does not have an ID yet\\34, skip\n the current page.");
    }
}
