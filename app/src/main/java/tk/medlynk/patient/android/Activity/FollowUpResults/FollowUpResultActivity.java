package tk.medlynk.patient.android.Activity.FollowUpResults;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_1.FUpR_1st_Question;
import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_17.FUpR_17th_Question;
import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_18.FUpR_18th_Question;
import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_2.FUpR_2nd_Question;
import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_3.FUpR_3rd_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_1.FUpS_1st_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10.FUpS_10th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_11.FUpS_11th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_12.FUpS_12th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_13.FUpS_13th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_14.FUpS_14th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_4.FUpS_4th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_5.FUpS_5th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_6.FUpS_6th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7.FUpS_7th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8.FUpS_8th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9.FUpS_9th_Question;
import tk.medlynk.patient.android.Constants;

public class FollowUpResultActivity extends AppCompatActivity implements
        FUpR_1st_Question.OnFURFirstQuestionInteractionListener,
        FUpR_2nd_Question.OnFURSecondQuestionInteractionListener,
        FUpR_3rd_Question.OnFURThirdQuestionInteractionListener,
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
        FUpR_17th_Question.OnFURSeventeenQuestionInteractionListener,
        FUpR_18th_Question.OnFUREighteenQuestionInteractionListener {

    View toolbar_view;
    TextView toolbar_title;
    ImageView backButton;
    private String CURRENT_FRAGMENT = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_up_result);
        Constants.Context_Tag = FollowUpResultActivity.class.getSimpleName();
        Constants.FOLLOW_UP_SYMPTOM_BODY.put(Constants.QUESTION_SET, "follow_up_results");
        toolbar_view = findViewById(R.id.follow_up_results_toolbar_layout);
        toolbar_title = toolbar_view.findViewById(R.id.toolbar_title);
        toolbar_title.setText(R.string.follow_up_result_title);
        backButton = toolbar_view.findViewById(R.id.imgBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        CURRENT_FRAGMENT = FUpR_1st_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .add(R.id.followUpFragmentsContainer, new FUpR_1st_Question(), FUpR_1st_Question.TAG)
                .commitNow();
    }


    @Override
    public void onFURFirstQuestion() {
        System.out.println("FollowUpResultActivity.onFURFirstQuestion");
        CURRENT_FRAGMENT = FUpR_2nd_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpR_1st_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpR_2nd_Question(), FUpR_2nd_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFURSecondQuestion() {
        System.out.println("FollowUpResultActivity.onFURSecondQuestion");
        CURRENT_FRAGMENT = FUpR_3rd_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpR_2nd_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpR_3rd_Question(), FUpR_3rd_Question.TAG)
                .commitNow();
    }


    @Override
    public void onFURThirdQuestion() {
        System.out.println("FollowUpResultActivity.onFURThirdQuestion");
        CURRENT_FRAGMENT = FUpS_4th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpR_3rd_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_4th_Question(), FUpS_4th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFourthQuestion() {
        System.out.println("FollowUpResultActivity.onFourthQuestion");
        CURRENT_FRAGMENT = FUpS_5th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_4th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_5th_Question(), FUpS_5th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFifthQuestion() {
        System.out.println("FollowUpResultActivity.onFifthQuestion");
        CURRENT_FRAGMENT = FUpS_6th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_5th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_6th_Question(), FUpS_6th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onSixthQuestion() {
        System.out.println("FollowUpResultActivity.onSixthQuestion");
        CURRENT_FRAGMENT = FUpS_7th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_6th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_7th_Question(), FUpS_7th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onSeventhQuestion() {
        System.out.println("FollowUpResultActivity.onSeventhQuestion");
        CURRENT_FRAGMENT = FUpS_8th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_7th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_8th_Question(), FUpS_8th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onEighthQuestion() {
        System.out.println("FollowUpResultActivity.onEighthQuestion");
        CURRENT_FRAGMENT = FUpS_9th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_8th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_9th_Question(), FUpS_9th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onNinthQuestion() {
        System.out.println("FollowUpResultActivity.onNinthQuestion");
        CURRENT_FRAGMENT = FUpS_10th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_9th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_10th_Question(), FUpS_10th_Question.TAG)
                .commitNow();
    }


    @Override
    public void onTenthQuestion() {
        System.out.println("FollowUpResultActivity.onTenthQuestion");
        CURRENT_FRAGMENT = FUpS_11th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_10th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_11th_Question(), FUpS_11th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onEleventhQuestion() {
        System.out.println("FollowUpResultActivity.onEleventhQuestion");
        CURRENT_FRAGMENT = FUpS_12th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_11th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_12th_Question(), FUpS_12th_Question.TAG)
                .commitNow();
    }


    @Override
    public void onTwelveQuestion() {
        System.out.println("FollowUpResultActivity.onTwelveQuestion");
        CURRENT_FRAGMENT = FUpS_13th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_12th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_13th_Question(), FUpS_13th_Question.TAG)
                .commitNow();
    }


    @Override
    public void onThirteenQuestion() {
        System.out.println("FollowUpResultActivity.onThirteenQuestion");
        CURRENT_FRAGMENT = FUpR_17th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_13th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpR_17th_Question(), FUpR_17th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFURSeventeenQuestion() {
        System.out.println("FollowUpResultActivity.onFURSeventeenQuestion");
        CURRENT_FRAGMENT = FUpR_18th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpR_17th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpR_18th_Question(), FUpR_18th_Question.TAG)
                .commitNow();

    }

    @Override
    public void onFUREighteenQuestion() {
        System.out.println("FollowUpResultActivity.onFUREighteenQuestion");
        Toast.makeText(this, "This Question Set is done!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onJumpTo17thQuestion() {
        System.out.println("FollowUpResultActivity.onJumpTo17thQuestion");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpR_3rd_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpR_17th_Question(), FUpR_17th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onBackPressed() {
        System.out.println("FollowUpResultActivity.onBackPressed");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (CURRENT_FRAGMENT) {
            case FUpR_1st_Question.TAG:{
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpR_1st_Question.TAG))
                        .commitNow();
                finish();
                break;
            }
            case FUpR_2nd_Question.TAG:{
                CURRENT_FRAGMENT =FUpR_1st_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpR_2nd_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpR_1st_Question(), FUpR_1st_Question.TAG)
                        .commitNow();
                break;
            }
            case FUpR_3rd_Question.TAG:{
                CURRENT_FRAGMENT =FUpR_2nd_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpR_3rd_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpR_2nd_Question(), FUpR_2nd_Question.TAG)
                        .commitNow();
                break;
            }

            case FUpS_4th_Question.TAG:{
                CURRENT_FRAGMENT =FUpR_3rd_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_4th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpR_3rd_Question(), FUpR_3rd_Question.TAG)
                        .commitNow();
                break;
            }
            case FUpS_5th_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_4th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_5th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_4th_Question(), FUpS_4th_Question.TAG)
                        .commitNow();
                break;
            }
            case FUpS_6th_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_5th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_6th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_5th_Question(), FUpS_5th_Question.TAG)
                        .commitNow();
                break;
            }
            case FUpS_7th_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_6th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_7th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_6th_Question(), FUpS_6th_Question.TAG)
                        .commitNow();
                break;
            }
            case FUpS_8th_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_7th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_8th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_7th_Question(), FUpS_7th_Question.TAG)
                        .commitNow();
                break;
            }
            case FUpS_9th_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_8th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_9th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_8th_Question(), FUpS_8th_Question.TAG)
                        .commitNow();
                break;
            }
            case FUpS_10th_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_9th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_10th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_9th_Question(), FUpS_9th_Question.TAG)
                        .commitNow();
                break;
            }
            case FUpS_11th_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_10th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_11th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_10th_Question(), FUpS_10th_Question.TAG)
                        .commitNow();
                break;
            }
            case FUpS_12th_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_11th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_12th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_11th_Question(), FUpS_11th_Question.TAG)
                        .commitNow();
                break;
            }
            case FUpS_13th_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_12th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_13th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_12th_Question(), FUpS_12th_Question.TAG)
                        .commitNow();
                break;
            }
            case FUpR_17th_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_13th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpR_17th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_13th_Question(), FUpS_13th_Question.TAG)
                        .commitNow();
                break;
            }
            case FUpR_18th_Question.TAG:{
                CURRENT_FRAGMENT =FUpR_17th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpR_18th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpR_17th_Question(), FUpR_17th_Question.TAG)
                        .commitNow();
                break;
            }

        }
    }
}
