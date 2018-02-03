package com.neweraandroid.demo.Activity.NewSymptom;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.neweraandroid.demo.Activity.NewSymptom.fragments.New_Symptom_1th_question;
import com.neweraandroid.demo.Activity.NewSymptom.fragments.New_Symptom_2nd_question;
import com.neweraandroid.demo.Activity.NewSymptom.fragments.New_Symptom_3rd_question;
import com.neweraandroid.demo.R;

public class NewSymptomActivity extends AppCompatActivity implements
        New_Symptom_1th_question.OnNewSymptomFirstQuestionListener,
        New_Symptom_2nd_question.OnNewSymptomSecondQuestionListener,
        New_Symptom_3rd_question.OnNewSymptomThirdQuestionListener
{

    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_new_symptom );
        fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_1th_question (), New_Symptom_1th_question.TAG ).commit ();
    }

    @Override
    public void onFirstQuestion(Uri uri) {
        System.out.println (Uri.parse ( uri.toString () ).getScheme ());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.replace ( R.id.fragment_container, new New_Symptom_2nd_question (), New_Symptom_2nd_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_2nd_question.TAG );
    }

    @Override
    public void onSecondQuestion(Uri uri) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        System.out.println (Uri.parse ( uri.toString () ).getScheme ());
        fragmentTransaction.replace ( R.id.fragment_container, new New_Symptom_3rd_question (), New_Symptom_3rd_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_3rd_question.TAG );
    }

    @Override
    public void onThirdQuestion(Uri uri) {
        System.out.println (Uri.parse ( uri.toString () ).getScheme ());
    }
}
