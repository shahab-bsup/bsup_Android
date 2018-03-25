package tk.medlynk.patient.android.Activity.FollowUpSymptoms;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_1.FUpS_1st_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10.FUpS_10th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_11.FUpS_11th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_12.FUpS_12th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_13.FUpS_13th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_14.FUpS_14th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_15.FUpS_15th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_2.FUpS_2nd_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_3.FUpS_3rd_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_4.FUpS_4th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_5.FUpS_5th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_6.FUpS_6th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7.FUpS_7th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8.FUpS_8th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9.FUpS_9th_Question;
import tk.medlynk.patient.android.Constants;

public class FollowUpSymptomsActivity extends AppCompatActivity implements
        FUpS_1st_Question.OnFollowUpSymptomsFirstQuestionListener,
        FUpS_2nd_Question.OnFollowUpSymptomsSecondQuestionListener,
        FUpS_3rd_Question.OnFollowUpSymptomsThirdQuestionListener,
        FUpS_4th_Question.OnFollowUpSymptomsFourthQuestionListener,
        FUpS_5th_Question.OnFollowUpSymptomsFifthQuestionListener,
        FUpS_6th_Question.OnFollowUpSymptomsSixthQuestionListener,
        FUpS_7th_Question.OnFollowUpSymptomsSeventhQuestionListener,
        FUpS_8th_Question.OnFollowUpSymptomsEighthQuestionListener,
        FUpS_9th_Question.OnFollowUpSymptomsNinthQuestionListener,
        FUpS_10th_Question.OnFollowUpSymptomsTenthQuestionListener,
        FUpS_11th_Question.OnFollowUpSymptomsEleventhQuestionListener,
        FUpS_12th_Question.OnFollowUpSymptomsTwelveQuestionListener,
        FUpS_13th_Question.OnFollowUpSymptomsThirteenQuestionListener,
        FUpS_14th_Question.OnFollowUpSymptomsFourteenQuestionListener,
        FUpS_15th_Question.OnFollowUpSymptomsFifteenQuestionListener
{

    View toolbar_view;
    TextView toolbar_title;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_follow_up_sysmptoms );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_SET, "follow_up_symptoms" );
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
                new FUpS_1st_Question(),
                FUpS_1st_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_1st_Question.TAG );
    }

    @Override
    public void onFirstQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onFirstQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add(R.id.followUpFragmentsContainer,
                new FUpS_2nd_Question(),
                FUpS_2nd_Question.TAG)
        .commit ();
        fragmentTransaction.addToBackStack ( FUpS_2nd_Question.TAG );
    }

    @Override
    public void onSecondQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onSecondQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new FUpS_3rd_Question(),
                FUpS_3rd_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_3rd_Question.TAG );
    }


    @Override
    public void onThirdQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onThirdQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new FUpS_4th_Question(),
                FUpS_4th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_4th_Question.TAG );
    }

    @Override
    public void onJumpToFUpsNinthQuestion() {
        System.out.println("FollowUpSymptomsActivity.onJumpToFUpsNinthQuestion");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                        new FUpS_9th_Question(),
                        FUpS_9th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_9th_Question.TAG );
    }


    @Override
    public void onFourthQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onFourthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new FUpS_5th_Question(),
                FUpS_5th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_5th_Question.TAG );
    }


    @Override
    public void onFifthQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onFifthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new FUpS_6th_Question(),
                FUpS_6th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_6th_Question.TAG );
    }


    @Override
    public void onSixthQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onSixthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new FUpS_7th_Question(),
                FUpS_7th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_7th_Question.TAG );
    }

    @Override
    public void onSeventhQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onSeventhQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new FUpS_8th_Question(),
                FUpS_8th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_8th_Question.TAG );
    }

    @Override
    public void onEighthQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onEighthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new FUpS_9th_Question(),
                FUpS_9th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_9th_Question.TAG );
    }

    @Override
    public void onNinthQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onNinthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new FUpS_10th_Question(),
                FUpS_10th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_10th_Question.TAG );
    }


    @Override
    public void onTenthQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onTenthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new FUpS_11th_Question(),
                FUpS_11th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_11th_Question.TAG );
    }

    @Override
    public void onEleventhQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onEleventhQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new FUpS_12th_Question(),
                FUpS_12th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_12th_Question.TAG );
    }


    @Override
    public void onTwelveQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onTwelveQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new FUpS_13th_Question(),
                FUpS_13th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_13th_Question.TAG );
    }


    @Override
    public void onThirteenQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onThirteenQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new FUpS_14th_Question(),
                FUpS_14th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_14th_Question.TAG );
    }

    @Override
    public void onFourteenQuestion() {
        System.out.println ( "FollowUpSymptomsActivity.onFourteenQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.followUpFragmentsContainer,
                new FUpS_15th_Question(),
                FUpS_15th_Question.TAG)
                .commit ();
        fragmentTransaction.addToBackStack ( FUpS_15th_Question.TAG );
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
