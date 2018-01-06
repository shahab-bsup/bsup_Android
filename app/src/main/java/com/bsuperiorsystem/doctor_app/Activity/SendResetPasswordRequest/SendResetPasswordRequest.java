package com.bsuperiorsystem.doctor_app.Activity.SendResetPasswordRequest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bsuperiorsystem.doctor_app.Activity.ResetPassword.ResetPasswordActivity;
import com.bsuperiorsystem.doctor_app.R;

public class SendResetPasswordRequest extends AppCompatActivity{

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_reset_password_request);
        button = findViewById(R.id.btnSendResetPasswordReq);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SendResetPasswordRequest.this, ResetPasswordActivity.class));
            }
        });
    }
}
