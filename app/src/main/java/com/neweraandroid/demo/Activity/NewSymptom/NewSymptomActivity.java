package com.neweraandroid.demo.Activity.NewSymptom;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.neweraandroid.demo.Activity.NewSymptom.fragments.New_Symptom_1th_Question;
import com.neweraandroid.demo.Activity.NewSymptom.fragments.New_Symptom_2nd_Question;
import com.neweraandroid.demo.Activity.NewSymptom.fragments.New_Symptom_3rd_Question;
import com.neweraandroid.demo.R;

public class NewSymptomActivity extends AppCompatActivity implements
        New_Symptom_1th_Question.OnFirstQuestionListener,
        New_Symptom_2nd_Question.OnSecondQuestionListener,
        New_Symptom_3rd_Question.OnThirdQuestionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_new_symptom );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.add ( R.id.fragment_container , new New_Symptom_1th_Question (), New_Symptom_1th_Question.TAG ).commit ();
    }

    @Override
    public void onFirstQuestion(String answer) {

    }

    @Override
    public void onSecondQuestion(String s) {

    }

    @Override
    public void onThirdQuestion(String s) {

    }
}
