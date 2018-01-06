package com.bsuperiorsystem.doctor_app.Activity.SearchDoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bsuperiorsystem.doctor_app.Activity.NoDoctorIDPage;
import com.bsuperiorsystem.doctor_app.R;

public class SearchActivity extends AppCompatActivity {

    Button searchButton, doNotKnowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor);
        searchButton = findViewById(R.id.btnSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SearchActivity.this, "Search is in progress...", Toast.LENGTH_SHORT).show();
            }
        });
        doNotKnowButton = findViewById(R.id.btnDo_not_know_dr_id);
        doNotKnowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, NoDoctorIDPage.class));
            }
        });
    }
}
