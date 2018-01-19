package com.neweraandroid.demo.Activity.SignUp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.neweraandroid.demo.R;

//The most tranquil activity ever!!!

public class SignUpActivity extends AppCompatActivity {

    View view;
    SignUpViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        view = findViewById ( R.id.parent_sign_up );
        viewHolder = new SignUpViewHolder ( view );
    }

}
