package com.bsuperiorsystem.doctor_app.Activity.SignUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bsuperiorsystem.doctor_app.Activity.Welcome.WelcomeActivity;
import com.bsuperiorsystem.doctor_app.R;

public class SignUpActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        button = findViewById(R.id.btnSignUp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, WelcomeActivity.class));
            }
        });
    }
}
