package tk.medlynk.patient.android.Activity.FollowUpSymptoms;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_1.Follow_Up_Symptoms_1st_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10.Follow_Up_Symptoms_10th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_11.Follow_Up_Symptoms_11th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_12.Follow_Up_Symptoms_12th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_13.Follow_Up_Symptoms_13th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_14.Follow_Up_Symptoms_14th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_15.Follow_Up_Symptoms_15th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_2.Follow_Up_Symptoms_2nd_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_3.Follow_Up_Symptoms_3rd_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_4.Follow_Up_Symptoms_4th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_5.Follow_Up_Symptoms_5th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_6.Follow_Up_Symptoms_6th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7.Follow_Up_Symptoms_7th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8.Follow_Up_Symptoms_8th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9.Follow_Up_Symptoms_9th_Question;
import tk.medlynk.patient.android.Constants;

public class FollowUpSymptomsActivity extends AppCompatActivity implements
        Follow_Up_Symptoms_1st_Question.OnFollowUpSymptomsFirstQuestionListener,
        Follow_Up_Symptoms_2nd_Question.OnFollowUpSymptomsSecondQuestionListener,
        Follow_Up_Symptoms_3rd_Question.OnFollowUpSymptomsThirdQuestionListener,
        Follow_Up_Symptoms_4th_Question.OnFollowUpSymptomsFourthQuestionListener,
        Follow_Up_Symptoms_5th_Question.OnFollowUpSymptomsFifthQuestionListener,
        Follow_Up_Symptoms_6th_Question.OnFollowUpSymptomsSixthQuestionListener,
        Follow_Up_Symptoms_7th_Question.OnFollowUpSymptomsSeventhQuestionListener,
        Follow_Up_Symptoms_8th_Question.OnFollowUpSymptomsEighthQuestionListener,
        Follow_Up_Symptoms_9th_Question.OnFollowUpSymptomsNinthQuestionListener,
        Follow_Up_Symptoms_10th_Question.OnFollowUpSymptomsTenthQuestionListener,
        Follow_Up_Symptoms_11th_Question.OnFollowUpSymptomsEleventhQuestionListener,
        Follow_Up_Symptoms_12th_Question.OnFollowUpSymptomsTwelveQuestionListener,
        Follow_Up_Symptoms_13th_Question.OnFollowUpSymptomsThirteenQuestionListener,
        Follow_Up_Symptoms_14th_Question.OnFollowUpSymptomsFourteenQuestionListener,
        Follow_Up_Symptoms_15th_Question.OnFollowUpSymptomsFifteenQuestionListener
{

    View toolbar_view;
    TextView toolbar_title;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_follow_up_sysmptoms );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.QUESTION_SET, "follow_up_symptoms" );
        toolbar_view = findViewById ( R.id.follow_up_symptom_toolbar_layout );
        toolbar_title = toolbar_view.findViewById ( R.id.toolbar_title );
        toolbar_title.setText ( R.string.follow_up_symptoms_title );
        backButton = toolbar_view.findViewById ( R.id.imgBackButton );
        backButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                onBackPressed ();
            }
        } );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_1st_Question (),
                Follow_Up_Symptoms_1st_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_1st_Question.TAG );
    }

    @Override
    public void onFirstQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onFirstQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add(R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_2nd_Question (),
                Follow_Up_Symptoms_2nd_Question.TAG)
        .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_2nd_Question.TAG );
    }

    @Override
    public void onSecondQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onSecondQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_3rd_Question (),
                Follow_Up_Symptoms_3rd_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_3rd_Question.TAG );
    }


    @Override
    public void onThirdQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onThirdQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_4th_Question (),
                Follow_Up_Symptoms_4th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_4th_Question.TAG );
    }


    @Override
    public void onFourthQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onFourthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_5th_Question (),
                Follow_Up_Symptoms_5th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_5th_Question.TAG );
    }


    @Override
    public void onFifthQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onFifthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_6th_Question (),
                Follow_Up_Symptoms_6th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_6th_Question.TAG );
    }


    @Override
    public void onSixthQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onSixthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_7th_Question (),
                Follow_Up_Symptoms_7th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_7th_Question.TAG );
    }

    @Override
    public void onSeventhQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onSeventhQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_8th_Question (),
                Follow_Up_Symptoms_8th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_8th_Question.TAG );
    }

    @Override
    public void onEighthQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onEighthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_9th_Question (),
                Follow_Up_Symptoms_9th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_9th_Question.TAG );
    }

    @Override
    public void onNinthQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onNinthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_10th_Question (),
                Follow_Up_Symptoms_10th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_10th_Question.TAG );
    }


    @Override
    public void onTenthQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onTenthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_11th_Question (),
                Follow_Up_Symptoms_11th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_11th_Question.TAG );
    }

    @Override
    public void onEleventhQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onEleventhQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_12th_Question (),
                Follow_Up_Symptoms_12th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_12th_Question.TAG );
    }


    @Override
    public void onTwelveQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onTwelveQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_13th_Question (),
                Follow_Up_Symptoms_13th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_13th_Question.TAG );
    }


    @Override
    public void onThirteenQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onThirteenQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_14th_Question (),
                Follow_Up_Symptoms_14th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_14th_Question.TAG );
    }

    @Override
    public void onFourteenQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onFourteenQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new Follow_Up_Symptoms_15th_Question (),
                Follow_Up_Symptoms_15th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( Follow_Up_Symptoms_15th_Question.TAG );
    }

    @Override
    public void onFifteenQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onFifteenQuestion" );
        finish ();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed ();
        System.out.println ( "FollowUpSymptomsActivity.onBackPressed" );

    }

}
