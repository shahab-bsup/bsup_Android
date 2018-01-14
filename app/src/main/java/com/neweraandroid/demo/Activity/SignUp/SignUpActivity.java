package com.neweraandroid.demo.Activity.SignUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.neweraandroid.demo.Activity.Welcome.WelcomeActivity;
import com.neweraandroid.demo.R;

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
