package com.bsuperiorsystem.doctor_app.Activity.ResetPassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bsuperiorsystem.doctor_app.Activity.SearchDoctor.SearchActivity;
import com.bsuperiorsystem.doctor_app.R;

public class ResetPasswordActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        button = findViewById(R.id.btnResetPassword);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResetPasswordActivity.this, SearchActivity.class));
            }
        });

    }
}
