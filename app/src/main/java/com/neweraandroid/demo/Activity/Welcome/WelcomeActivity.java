package com.neweraandroid.demo.Activity.Welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.neweraandroid.demo.Activity.SearchDoctor.SearchActivity;
import com.neweraandroid.demo.R;

public class WelcomeActivity extends AppCompatActivity {

    Button startButton, goToQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_welcome);
        startButton = (Button) findViewById(R.id.btnStart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(WelcomeActivity.this, SearchActivity.class));
            }
        });

        goToQuestionButton = (Button) findViewById(R.id.btnGoToQuestions);
        goToQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WelcomeActivity.this, "is in progress...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}