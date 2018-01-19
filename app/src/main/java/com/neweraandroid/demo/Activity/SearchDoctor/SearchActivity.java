package com.neweraandroid.demo.Activity.SearchDoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.neweraandroid.demo.R;

public class SearchActivity extends AppCompatActivity {

    View parent_view;
    SearchActivityViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor);
        parent_view = findViewById ( R.id.parent_search_activity );
        viewHolder = new SearchActivityViewHolder ( parent_view );
    }
}
