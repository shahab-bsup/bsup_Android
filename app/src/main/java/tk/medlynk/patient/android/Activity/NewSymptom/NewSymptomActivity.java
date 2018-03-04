package tk.medlynk.patient.android.Activity.NewSymptom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_10.New_Symptom_10th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_11.New_Symptom_11th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_12.New_Symptom_12th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13.New_Symptom_13th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_14.New_Symptom_14th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_15.New_Symptom_15th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_16.New_Symptom_16th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_17.New_Symptom_17th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_18.New_Symptom_18th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_19.New_Symptom_19th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_1.New_Symptom_1th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_20.New_Symptom_20th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_21.New_Symptom_21th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_22.New_Symptom_22th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_23.New_Symptom_23th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.New_Symptom_24th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.New_Symptom_25th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2.New_Symptom_2nd_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_3.New_Symptom_3rd_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_4.New_Symptom_4th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_5.New_Symptom_5th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_6.New_Symptom_6th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_7.New_Symptom_7th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_8.New_Symptom_8th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_9.New_Symptom_9th_question;
import tk.medlynk.patient.android.Constants;

import com.neweraandroid.demo.R;

import java.util.List;

public class NewSymptomActivity extends AppCompatActivity implements
        New_Symptom_1th_question.OnNewSymptomFirstQuestionListener,
        New_Symptom_2nd_question.OnNewSymptomSecondQuestionListener,
        New_Symptom_3rd_question.OnNewSymptomThirdQuestionListener,
        New_Symptom_4th_question.OnNewSymptomFourthQuestionListener,
        New_Symptom_5th_question.OnNewSymptomFifthQuestionListener,
        New_Symptom_6th_question.OnNewSymptomSixthQuestionListener,
        New_Symptom_7th_question.OnNewSymptomSeventhQuestionListener,
        New_Symptom_8th_question.OnNewSymptomEighthQuestionListener,
        New_Symptom_9th_question.OnNewSymptomNinthQuestionListener,
        New_Symptom_10th_question.OnNewSymptomTenthQuestionListener,
        New_Symptom_11th_question.OnNewSymptomEleventhQuestionListener,
        New_Symptom_12th_question.OnNewSymptomTwelveQuestionListener,
        New_Symptom_13th_question.OnNewSymptomThirteenQuestionListener,
        New_Symptom_14th_question.OnNewSymptomFourteenQuestionListener,
        New_Symptom_15th_question.OnNewSymptomFifteenQuestionListener,
        New_Symptom_16th_question.OnNewSymptomSixteenQuestionListener,
        New_Symptom_17th_question.OnNewSymptomSeventeenQuestionListener,
        New_Symptom_18th_question.OnNewSymptomEighteenQuestionListener,
        New_Symptom_19th_question.OnNewSymptomNineteenQuestionListener,
        New_Symptom_20th_question.OnNewSymptomTwentyQuestionListener,
        New_Symptom_21th_question.OnNewSymptomTwenty1QuestionListener,
        New_Symptom_22th_question.OnNewSymptomTwenty2QuestionListener,
        New_Symptom_23th_question.OnNewSymptomTwenty3QuestionListener,
        New_Symptom_24th_question.OnNewSymptomTwenty4QuestionListener,
        New_Symptom_25th_question.OnNewSymptomTwenty5QuestionListener {


    View toolbar_view;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_new_symptom );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_SET, "new_symptom" );
        toolbar_view = findViewById ( R.id.new_symptom_toolbar_layout );
        backButton = toolbar_view.findViewById ( R.id.imgBackButton );
        backButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                onBackPressed ();
            }
        } );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_1th_question (), New_Symptom_1th_question.TAG ).commit ();
    }


    @Override
    public void onFirstQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_2nd_question (), New_Symptom_2nd_question.TAG ).commit ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
    }

    @Override
    public void onSecondQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_3rd_question (), New_Symptom_3rd_question.TAG ).commit ();
    }

    @Override
    public void onThirdQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_4th_question (), New_Symptom_4th_question.TAG ).commit ();
    }

    @Override
    public void onFourthQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_5th_question (), New_Symptom_5th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_5th_question.TAG );
    }

    @Override
    public void onFifthQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_6th_question (), New_Symptom_6th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_6th_question.TAG );
    }

    @Override
    public void onSixthQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_7th_question (), New_Symptom_7th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_7th_question.TAG );
    }

    @Override
    public void onTenthQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_11th_question (), New_Symptom_11th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_11th_question.TAG );
    }

    @Override
    public void onTwenty5Question() {
        //TODO add some code to return to the activity...
    }

    @Override
    public void onNinthQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_10th_question (), New_Symptom_10th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_10th_question.TAG );
    }

    @Override
    public void onEleventhQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_12th_question (), New_Symptom_12th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_12th_question.TAG );
    }

    @Override
    public void onEightQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_9th_question (), New_Symptom_9th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_9th_question.TAG );
    }

    @Override
    public void onTwentyQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_21th_question (), New_Symptom_21th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_21th_question.TAG );
    }

    @Override
    public void onFifteenQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_16th_question (), New_Symptom_16th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_16th_question.TAG );
    }

    @Override
    public void onSixteenQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_17th_question (), New_Symptom_17th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_17th_question.TAG );
    }

    @Override
    public void onTwenty1Question() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_22th_question (), New_Symptom_22th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_22th_question.TAG );
    }

    @Override
    public void onTwenty2Question() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_23th_question (), New_Symptom_23th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_23th_question.TAG );
    }

    @Override
    public void onTwenty3Question() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_24th_question (), New_Symptom_24th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_24th_question.TAG );
    }

    @Override
    public void onTwenty4Question() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_25th_question (), New_Symptom_25th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_25th_question.TAG );
    }

    @Override
    public void onFourteenQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_15th_question (), New_Symptom_15th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_15th_question.TAG );
    }

    @Override
    public void onEighteenQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_19th_question (), New_Symptom_19th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_19th_question.TAG );
    }

    @Override
    public void onNineteenQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_20th_question (), New_Symptom_20th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_20th_question.TAG );
    }
    @Override
    public void onSeventeenQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_18th_question (), New_Symptom_18th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_18th_question.TAG );
    }

    @Override
    public void onSeventhQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_8th_question (), New_Symptom_8th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_8th_question.TAG );
    }

    @Override
    public void onTwelveQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_13th_question (), New_Symptom_13th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_13th_question.TAG );
    }

    @Override
    public void onThirteenQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new New_Symptom_14th_question (), New_Symptom_14th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_14th_question.TAG );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed ();
        List<Fragment> fragments = getSupportFragmentManager ().getFragments ();
        
    }
}
