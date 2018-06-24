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
import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_2.FUpR_2nd_Question;
import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_3.FUpR_3rd_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_1.FUpS_1st_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_2.FUpS_2nd_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_3.FUpS_3rd_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_4.FUpS_4th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_5.FUpS_5th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_6.FUpS_6th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7.FUpS_7th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8.FUpS_8th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9.FUpS_9th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10.FUpS_10th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_11.FUpS_11th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_12.FUpS_12th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_13.FUpS_13th_Question;
import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_17.FUpR_17th_Question;
import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_18.FUpR_18th_Question;
import tk.medlynk.patient.android.Activity.FollowUpResults.fragments.End_of_Question_Set.End_of_Question_Set;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.FirstUnansweredQuestion;
import tk.medlynk.patient.android.OnFirstUnansweredQuestionListener;

public class FollowUpResultActivity extends AppCompatActivity implements
        FUpR_1st_Question.OnFURFirstQuestionInteractionListener,
        FUpR_2nd_Question.OnFURSecondQuestionInteractionListener,
        FUpR_3rd_Question.OnFURThirdQuestionInteractionListener,
        FUpS_1st_Question.OnFURFourthQuestionInteractionListener,
        FUpS_2nd_Question.OnFURFifthQuestionInteractionListener,
        FUpS_3rd_Question.OnFURSixthQuestionInteractionListener,
        FUpS_4th_Question.OnFURSeventhQuestionInteractionListener,
        FUpS_5th_Question.OnFUREighthQuestionInteractionListener,
        FUpS_6th_Question.OnFURNinthQuestionInteractionListener,
        FUpS_7th_Question.OnFURTenthQuestionInteractionListener,
        FUpS_8th_Question.OnFUREleventhQuestionInteractionListener,
        FUpS_9th_Question.OnFURTwelveQuestionInteractionListener,
        FUpS_10th_Question.OnFURThirteenQuestionInteractionListener,
        FUpS_11th_Question.OnFURFourteenQuestionInteractionListener,
        FUpS_12th_Question.OnFURFifteenQuestionInteractionListener,
        FUpS_13th_Question.OnFURSixteenQuestionInteractionListener,
        FUpR_17th_Question.OnFURSeventeenQuestionInteractionListener,
        FUpR_18th_Question.OnFUREighteenQuestionInteractionListener,
        End_of_Question_Set.OnEndOfFollowUpResultListener,
        OnFirstUnansweredQuestionListener{

    View toolbar_view;
    TextView toolbar_title;
    ImageView backButton;
    private String CURRENT_FRAGMENT = null;
    private SharedPreferenceManager manager;

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

        manager = new SharedPreferenceManager(this);
        FirstUnansweredQuestion firstUnansweredQuestion = FirstUnansweredQuestion.getInstance();

        firstUnansweredQuestion.takeFirstUnansweredQuestion(this, this,
                manager.getAppointmentID(), Constants.FOLLOW_UP_RESULTS_ROW, 0, Constants.FOLLOW_UP_RESULTS_QUESTIONS_NUMBER);

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
        CURRENT_FRAGMENT = FUpS_1st_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpR_3rd_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_1st_Question(), FUpS_1st_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFURFourthQuestion() {
        System.out.println("FollowUpResultActivity.onFourthQuestion");
        CURRENT_FRAGMENT = FUpS_2nd_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_1st_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_2nd_Question(), FUpS_2nd_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFURFifthQuestion() {
        System.out.println("FollowUpResultActivity.onFifthQuestion");
        CURRENT_FRAGMENT = FUpS_3rd_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_2nd_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_3rd_Question(), FUpS_3rd_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFURSixthQuestion() {
        System.out.println("FollowUpResultActivity.onSixthQuestion");
        CURRENT_FRAGMENT = FUpS_4th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_3rd_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_4th_Question(), FUpS_4th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFURSeventhQuestion() {
        System.out.println("FollowUpResultActivity.onSeventhQuestion");
        CURRENT_FRAGMENT = FUpS_5th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_4th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_5th_Question(), FUpS_5th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFUREighthQuestion() {
        System.out.println("FollowUpResultActivity.onEighthQuestion");
        CURRENT_FRAGMENT = FUpS_6th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_5th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_6th_Question(), FUpS_6th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFURNinthQuestion() {
        System.out.println("FollowUpResultActivity.onNinthQuestion");
        CURRENT_FRAGMENT = FUpS_7th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_6th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_7th_Question(), FUpS_7th_Question.TAG)
                .commitNow();
    }


    @Override
    public void onFURTenthQuestion() {
        System.out.println("FollowUpResultActivity.onTenthQuestion");
        CURRENT_FRAGMENT = FUpS_8th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_7th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_8th_Question(), FUpS_8th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFUREleventhQuestion() {
        System.out.println("FollowUpResultActivity.onEleventhQuestion");
        CURRENT_FRAGMENT = FUpS_9th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_8th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_9th_Question(), FUpS_9th_Question.TAG)
                .commitNow();
    }


    @Override
    public void onFURTwelveQuestion() {
        System.out.println("FollowUpResultActivity.onTwelveQuestion");
        CURRENT_FRAGMENT = FUpS_10th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_9th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_10th_Question(), FUpS_10th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFURThirteenQuestion() {
        System.out.println("FollowUpResultActivity.onThirteenQuestion");
        CURRENT_FRAGMENT = FUpS_11th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_10th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_11th_Question(), FUpS_11th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFURFourteenQuestion() {
        System.out.println("FollowUpResultActivity.onFourteenQuestion");
        CURRENT_FRAGMENT = FUpS_12th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_11th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_12th_Question(), FUpS_12th_Question.TAG)
                .commitNow();
    }
    @Override
    public void onFURFifteenQuestion() {
        System.out.println("FollowUpResultActivity.onFifteenQuestion");
        CURRENT_FRAGMENT = FUpS_13th_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpS_12th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new FUpS_13th_Question(), FUpS_13th_Question.TAG)
                .commitNow();
    }

    @Override
    public void onFURSixteenQuestion() {
        System.out.println("FollowUpResultActivity.onFifteenQuestion");
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
        CURRENT_FRAGMENT = End_of_Question_Set.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(FUpR_18th_Question.TAG))
                .add(R.id.followUpFragmentsContainer, new End_of_Question_Set(), End_of_Question_Set.TAG)
                .commitNow();
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

            case FUpS_1st_Question.TAG:{
                CURRENT_FRAGMENT =FUpR_3rd_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(FUpS_1st_Question.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpR_3rd_Question(), FUpR_3rd_Question.TAG)
                        .commitNow();
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
            case End_of_Question_Set.TAG:{
                CURRENT_FRAGMENT =FUpR_18th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.followUpFragmentsContainer, new FUpR_18th_Question(), FUpR_18th_Question.TAG)
                        .commitNow();
                break;

            }

        }
    }

    @Override
    public void firstUnAnsweredQuestionResponse(int questionNumber) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (questionNumber) {
            case 1: {
                CURRENT_FRAGMENT =FUpR_1st_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpR_1st_Question(), FUpR_1st_Question.TAG)
                        .commitNow();
                break;
            }
            case 2: {
                CURRENT_FRAGMENT =FUpR_2nd_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpR_2nd_Question(), FUpR_2nd_Question.TAG)
                        .commitNow();
                break;
            }
            case 3: {
                CURRENT_FRAGMENT =FUpR_3rd_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpR_3rd_Question(), FUpR_3rd_Question.TAG)
                        .commitNow();
                break;
            }
            case 4: {
                CURRENT_FRAGMENT =FUpS_1st_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpS_1st_Question(), FUpS_1st_Question.TAG)
                        .commitNow();
                break;
            }
            case 5: {
                CURRENT_FRAGMENT =FUpS_2nd_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpS_2nd_Question(), FUpS_2nd_Question.TAG)
                        .commitNow();
                break;
            }
            case 6: {
                CURRENT_FRAGMENT =FUpS_3rd_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpS_3rd_Question(), FUpS_3rd_Question.TAG)
                        .commitNow();
                break;
            }
            case 7: {
                CURRENT_FRAGMENT =FUpS_4th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpS_4th_Question(), FUpS_4th_Question.TAG)
                        .commitNow();
                break;
            }
            case 8: {
                CURRENT_FRAGMENT =FUpS_5th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpS_5th_Question(), FUpS_5th_Question.TAG)
                        .commitNow();
                break;
            }
            case 9: {
                CURRENT_FRAGMENT =FUpS_6th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpS_6th_Question(), FUpS_6th_Question.TAG)
                        .commitNow();
                break;
            }
            case 10: {
                CURRENT_FRAGMENT =FUpS_7th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpS_7th_Question(), FUpS_7th_Question.TAG)
                        .commitNow();
                break;
            }
            case 11: {
                CURRENT_FRAGMENT =FUpS_8th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpS_8th_Question(), FUpS_8th_Question.TAG)
                        .commitNow();
                break;
            }
            case 12: {
                CURRENT_FRAGMENT =FUpS_9th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpS_9th_Question(), FUpS_9th_Question.TAG)
                        .commitNow();
                break;
            }
            case 13: {
                CURRENT_FRAGMENT =FUpS_10th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpS_10th_Question(), FUpS_10th_Question.TAG)
                        .commitNow();
                break;
            }
            case 14: {
                CURRENT_FRAGMENT =FUpS_11th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpS_11th_Question(), FUpS_11th_Question.TAG)
                        .commitNow();
                break;
            }
            case 15: {
                CURRENT_FRAGMENT =FUpS_12th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpS_12th_Question(), FUpS_12th_Question.TAG)
                        .commitNow();
                break;
            }
            case 16: {
                CURRENT_FRAGMENT =FUpS_13th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpS_13th_Question(), FUpS_13th_Question.TAG)
                        .commitNow();
                break;
            }
            case 17: {
                CURRENT_FRAGMENT =FUpR_17th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpR_17th_Question(), FUpR_17th_Question.TAG)
                        .commitNow();
                break;
            }
            case 18: {
                CURRENT_FRAGMENT =FUpR_18th_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .add(R.id.followUpFragmentsContainer, new FUpR_18th_Question(), FUpR_18th_Question.TAG)
                        .commitNow();
                break;
            }

        }
    }
}
