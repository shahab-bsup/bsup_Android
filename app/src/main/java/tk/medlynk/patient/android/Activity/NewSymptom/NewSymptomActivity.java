package tk.medlynk.patient.android.Activity.NewSymptom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_10.NS_10th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_11.NS_11th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_12.NS_12th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13.NS_13th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_14.NS_14th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_15.NS_15th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_16.NS_16th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_17.NS_17th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_18.NS_18th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_19.NS_19th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_1.New_Symptom_1th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2.NS_2nd_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_20.NS_20th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_21.New_Symptom_21th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_22.New_Symptom_22th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_23.New_Symptom_23th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.New_Symptom_24th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.New_Symptom_25th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_3.NS_3rd_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_4.NS_4th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_5.NS_5th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_6.NS_6th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_7.NS_7th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_8.NS_8th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_9.NS_9th_question;
import tk.medlynk.patient.android.Constants;

import com.neweraandroid.demo.R;

import java.util.List;

public class NewSymptomActivity extends AppCompatActivity implements
        New_Symptom_1th_question.OnNewSymptomFirstQuestionListener,
        NS_2nd_question.OnNewSymptomSecondQuestionListener,
        NS_3rd_question.OnNewSymptomThirdQuestionListener,
        NS_4th_question.OnNewSymptomFourthQuestionListener,
        NS_5th_question.OnNewSymptomFifthQuestionListener,
        NS_6th_question.OnNewSymptomSixthQuestionListener,
        NS_7th_question.OnNewSymptomSeventhQuestionListener,
        NS_8th_question.OnNewSymptomEighthQuestionListener,
        NS_9th_question.OnNewSymptomNinthQuestionListener,
        NS_10th_question.OnNewSymptomTenthQuestionListener,
        NS_11th_question.OnNewSymptomEleventhQuestionListener,
        NS_12th_question.OnNewSymptomTwelveQuestionListener,
        NS_13th_question.OnNewSymptomThirteenQuestionListener,
        NS_14th_question.OnNewSymptomFourteenQuestionListener,
        NS_15th_question.OnNewSymptomFifteenQuestionListener,
        NS_16th_question.OnNewSymptomSixteenQuestionListener,
        NS_17th_question.OnNewSymptomSeventeenQuestionListener,
        NS_18th_question.OnNewSymptomEighteenQuestionListener,
        NS_19th_question.OnNewSymptomNineteenQuestionListener,
        NS_20th_question.OnNewSymptomTwentyQuestionListener,
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
        fragmentTransaction.add ( R.id.fragment_container,
                new New_Symptom_1th_question (),
                New_Symptom_1th_question.TAG ).commit ();
    }

    @Override
    public void onFirstQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.add ( R.id.fragment_container, new NS_2nd_question (), NS_2nd_question.TAG ).commit ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.addToBackStack ( NS_2nd_question.TAG );
    }

    @Override
    public void onSecondQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_3rd_question (), NS_3rd_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_3rd_question.TAG );
    }

    @Override
    public void onThirdQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_4th_question (), NS_4th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_4th_question.TAG );
    }

    @Override
    public void onFourthQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_5th_question (), NS_5th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_5th_question.TAG );
    }

    @Override
    public void onFifthQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_6th_question (), NS_6th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_6th_question.TAG );
    }

    @Override
    public void onSixthQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_7th_question (), NS_7th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_7th_question.TAG );
    }

    @Override
    public void onTenthQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_11th_question (), NS_11th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_11th_question.TAG );
    }

    @Override
    public void onTwenty5Question() {
        //TODO add some code to return to the activity...
    }

    @Override
    public void onNinthQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_10th_question (), NS_10th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_10th_question.TAG );
    }

    @Override
    public void onEleventhQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_12th_question (), NS_12th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_12th_question.TAG );
    }

    @Override
    public void onEightQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_9th_question (), NS_9th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_9th_question.TAG );
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
        fragmentTransaction.add ( R.id.fragment_container, new NS_16th_question (), NS_16th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_16th_question.TAG );
    }

    @Override
    public void onSixteenQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_17th_question (), NS_17th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_17th_question.TAG );
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
        fragmentTransaction.add ( R.id.fragment_container, new NS_15th_question (), NS_15th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_15th_question.TAG );
    }

    @Override
    public void onEighteenQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_19th_question (), NS_19th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_19th_question.TAG );
    }

    @Override
    public void onJumpToEnd() {
        System.out.println ( "NewSymptomActivity.onJumpToEnd" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container,
                new New_Symptom_24th_question (),
                New_Symptom_24th_question.TAG ).
                commit ();
        fragmentTransaction.addToBackStack ( New_Symptom_24th_question.TAG );
    }

    @Override
    public void onNineteenQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_20th_question(), NS_20th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_20th_question.TAG );
    }
    @Override
    public void onSeventeenQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_18th_question (), NS_18th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_18th_question.TAG );
    }

    @Override
    public void onSeventhQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_8th_question (), NS_8th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_8th_question.TAG );
    }

    @Override
    public void onTwelveQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_13th_question (), NS_13th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_13th_question.TAG );
    }

    @Override
    public void onThirteenQuestion() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left );
        fragmentTransaction.add ( R.id.fragment_container, new NS_14th_question (), NS_14th_question.TAG ).commit ();
        fragmentTransaction.addToBackStack ( NS_14th_question.TAG );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed ();
        List<Fragment> fragments = getSupportFragmentManager ().getFragments ();

    }
}
