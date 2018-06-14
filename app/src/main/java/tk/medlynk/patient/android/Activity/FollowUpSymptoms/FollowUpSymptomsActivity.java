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
        FUpS_15th_Question.OnFollowUpSymptomsFifteenQuestionListener {

    View toolbar_view;
    TextView toolbar_title;
    ImageView backButton;
    private String CURRENT_FRAGMENT = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.Context_Tag = FollowUpSymptomsActivity.class.getSimpleName();
        setContentView(R.layout.activity_follow_up_sysmptoms);
        Constants.FOLLOW_UP_SYMPTOM_BODY.put(Constants.QUESTION_SET, "follow_up_symptoms");
        toolbar_view = findViewById(R.id.follow_up_symptom_toolbar_layout);
        toolbar_title = toolbar_view.findViewById(R.id.toolbar_title);
        toolbar_title.setText(R.string.follow_up_symptoms_title);
        backButton = toolbar_view.findViewById(R.id.imgBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        CURRENT_FRAGMENT = FUpS_1st_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .add(R.id.followUpFragmentsContainer, new FUpS_1st_Question(), FUpS_1st_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFirstQuestion() {
        System.out.println("FollowUpSymptomsActivity.onFirstQuestion");
        CURRENT_FRAGMENT = FUpS_2nd_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_1st_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_2nd_Question(), FUpS_2nd_Question.TAG)
                .commitNow();
    }

    @Override
    public void onSecondQuestion() {
        System.out.println("FollowUpSymptomsActivity.onSecondQuestion");
        CURRENT_FRAGMENT = FUpS_3rd_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_2nd_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_3rd_Question(), FUpS_3rd_Question.TAG)
                .commitNow();
    }


    @Override
    public void onThirdQuestion() {
        System.out.println("FollowUpSymptomsActivity.onThirdQuestion");
        CURRENT_FRAGMENT = FUpS_4th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_3rd_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_4th_Question(), FUpS_4th_Question.TAG)
                .commitNow();
    }


    @Override
    public void onFourthQuestion() {
        System.out.println("FollowUpSymptomsActivity.onFourthQuestion");
        CURRENT_FRAGMENT = FUpS_5th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_4th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_5th_Question(), FUpS_5th_Question.TAG)
                .commitNow();
    }


    @Override
    public void onFifthQuestion() {
        System.out.println("FollowUpSymptomsActivity.onFifthQuestion");
        CURRENT_FRAGMENT = FUpS_6th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_5th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_6th_Question(), FUpS_6th_Question.TAG)
                .commitNow();
    }


    @Override
    public void onSixthQuestion() {
        System.out.println("FollowUpSymptomsActivity.onSixthQuestion");
        CURRENT_FRAGMENT = FUpS_7th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_6th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_7th_Question(), FUpS_7th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onSeventhQuestion() {
        System.out.println("FollowUpSymptomsActivity.onSeventhQuestion");
        CURRENT_FRAGMENT = FUpS_8th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_7th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_8th_Question(), FUpS_8th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onEighthQuestion() {
        System.out.println("FollowUpSymptomsActivity.onEighthQuestion");
        CURRENT_FRAGMENT = FUpS_9th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_8th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_9th_Question(), FUpS_9th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onNinthQuestion() {
        System.out.println("FollowUpSymptomsActivity.onNinthQuestion");
        CURRENT_FRAGMENT = FUpS_10th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_9th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_10th_Question(), FUpS_10th_Question.TAG)
                .commitNow();
    }


    @Override
    public void onTenthQuestion() {
        System.out.println("FollowUpSymptomsActivity.onTenthQuestion");
        CURRENT_FRAGMENT = FUpS_11th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_10th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_11th_Question(), FUpS_11th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onEleventhQuestion() {
        System.out.println("FollowUpSymptomsActivity.onEleventhQuestion");
        CURRENT_FRAGMENT = FUpS_12th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_11th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_12th_Question(), FUpS_12th_Question.TAG)
                .commitNow();
    }


    @Override
    public void onTwelveQuestion() {
        System.out.println("FollowUpSymptomsActivity.onTwelveQuestion");
        CURRENT_FRAGMENT = FUpS_13th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_12th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_13th_Question(), FUpS_13th_Question.TAG)
                .commitNow();
    }


    @Override
    public void onThirteenQuestion() {
        System.out.println("FollowUpSymptomsActivity.onThirteenQuestion");
        CURRENT_FRAGMENT = FUpS_14th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_13th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_14th_Question(), FUpS_14th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFourteenQuestion() {
        System.out.println("FollowUpSymptomsActivity.onFourteenQuestion");
        CURRENT_FRAGMENT = FUpS_15th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_14th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_15th_Question(), FUpS_15th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFifteenQuestion() {
        System.out.println("FollowUpSymptomsActivity.onFifteenQuestion");
        //CURRENT_FRAGMENT =FUpS_4th_Question.TAG;
        finish();
    }

    @Override
    public void onJumpToFUpsNinthQuestion() {
        System.out.println("FollowUpSymptomsActivity.onJumpToFUpsNinthQuestion");
        CURRENT_FRAGMENT = FUpS_9th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .add(R.id.followUpFragmentsContainer, new FUpS_9th_Question(), FUpS_9th_Question.TAG)
                .commit();
    }

    @Override
    public void onBackPressed() {
        System.out.println("FollowUpSymptomsActivity.onBackPressed");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (CURRENT_FRAGMENT) {
            case FUpS_1st_Question.TAG:{
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_1st_Question.TAG))
                        .commitNow();
                finish();
                break;
            }
            case FUpS_2nd_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_1st_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_2nd_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_1st_Question(), FUpS_1st_Question.TAG)
                        .commitNow();
                break;
            }
            case FUpS_3rd_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_2nd_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_3rd_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_2nd_Question(), FUpS_2nd_Question.TAG)
                        .commitNow();

                break;
            }
            case FUpS_4th_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_3rd_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_4th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_3rd_Question(), FUpS_3rd_Question.TAG)
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
            } case FUpS_14th_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_13th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_14th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_13th_Question(), FUpS_13th_Question.TAG)
                        .commitNow();
                break;
            } case FUpS_15th_Question.TAG:{
                CURRENT_FRAGMENT =FUpS_14th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_15th_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpS_14th_Question(), FUpS_14th_Question.TAG)
                        .commitNow();
                break;
            }
        }

    }
}
