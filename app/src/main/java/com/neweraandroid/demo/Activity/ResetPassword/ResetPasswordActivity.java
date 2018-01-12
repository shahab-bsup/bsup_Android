package com.neweraandroid.demo.Activity.ResetPassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.neweraandroid.demo.Activity.SearchDoctor.SearchActivity;
import com.neweraandroid.demo.R;


public class ResetPasswordActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_reset_password);
        button = (Button) findViewById ( R.id.btnResetPassword );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResetPasswordActivity.this, SearchActivity.class));
            }
        });

    }
}
