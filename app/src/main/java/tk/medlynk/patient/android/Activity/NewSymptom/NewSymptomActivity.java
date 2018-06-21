package tk.medlynk.patient.android.Activity.NewSymptom;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.NewSymptom.fragments.End_of_Question_Set.End_of_Question_Set;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.IntroFragment;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.New_Symptom_25th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_1.NS_1th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_10.NS_10th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_11.NS_11th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_12.NS_12th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13.NS_13th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_14.NS_14th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_15.NS_15th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_16.NS_16th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_17.NS_17th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_18.NS_18th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2.NS_2nd_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_3.NS_3rd_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_4.NS_4th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_5.NS_5th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_6.NS_6th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_7.NS_7th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_8.NS_8th_question;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_9.NS_9th_question;
import tk.medlynk.patient.android.Constants;

public class NewSymptomActivity extends AppCompatActivity implements
        IntroFragment.OnIntroFragmentListener,
        NS_1th_question.OnNewSymptomFirstQuestionListener,
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
        End_of_Question_Set.OnNewSymptomEndOfQuestionSetListener,
        New_Symptom_25th_question.OnNewSymptomTwenty5QuestionListener {

    private static final String TAG = NewSymptomActivity.class.getSimpleName();
    View toolbar_view;
    ImageView backButton;
    private String CURRENT_FRAGMENT = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_symptom);
        Constants.NEW_SYMPTOM_ANSWER_BODY.put(Constants.QUESTION_SET, "new_symptom");
        toolbar_view = findViewById(R.id.new_symptom_toolbar_layout);
        backButton = toolbar_view.findViewById(R.id.imgBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        CURRENT_FRAGMENT = NS_1th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .add(R.id.fragment_container, new NS_1th_question (), NS_1th_question.TAG)
                .commitNow();
    }

    @Override
    public void onIntroFragmentInteraction() {
        Log.d ( TAG, "onIntroFragmentInteraction: " );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(IntroFragment.TAG))
                .add(R.id.fragment_container, new NS_1th_question (), NS_1th_question.TAG)
                .commitNow();
    }

    @Override
    public void onFirstQuestion() {
        Log.d(TAG, "onFirstQuestion: ");
        CURRENT_FRAGMENT = NS_2nd_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_1th_question.TAG))
                .add(R.id.fragment_container, new NS_2nd_question(), NS_2nd_question.TAG)
                .commitNow();
    }

    @Override
    public void onSecondQuestion() {
        Log.d(TAG, "onSecondQuestion: ");
        CURRENT_FRAGMENT = NS_3rd_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_2nd_question.TAG))
                .add(R.id.fragment_container, new NS_3rd_question(), NS_3rd_question.TAG)
                .commitNow();
    }

    @Override
    public void onThirdQuestion() {
        Log.d(TAG, "onThirdQuestion: ");
        CURRENT_FRAGMENT = NS_4th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_3rd_question.TAG))
                .add(R.id.fragment_container, new NS_4th_question(), NS_4th_question.TAG)
                .commitNow();
    }

    @Override
    public void onFourthQuestion() {
        Log.d(TAG, "onFourthQuestion: ");
        CURRENT_FRAGMENT = NS_5th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_4th_question.TAG))
                .add(R.id.fragment_container, new NS_5th_question(), NS_5th_question.TAG)
                .commitNow();
    }

    @Override
    public void onFifthQuestion() {
        Log.d(TAG, "onFifthQuestion: ");
        CURRENT_FRAGMENT = NS_6th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_5th_question.TAG))
                .add(R.id.fragment_container, new NS_6th_question(), NS_6th_question.TAG)
                .commitNow();
    }

    @Override
    public void onSixthQuestion() {
        Log.d(TAG, "onSixthQuestion: ");
        CURRENT_FRAGMENT = NS_7th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_6th_question.TAG))
                .add(R.id.fragment_container, new NS_7th_question(), NS_7th_question.TAG)
                .commitNow();
    }

    @Override
    public void onSeventhQuestion() {
        Log.d(TAG, "onSeventhQuestion: ");
        CURRENT_FRAGMENT = NS_8th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_7th_question.TAG))
                .add(R.id.fragment_container, new NS_8th_question(), NS_8th_question.TAG)
                .commitNow();
    }

    @Override
    public void onEightQuestion() {
        Log.d(TAG, "onEightQuestion: ");
        CURRENT_FRAGMENT = NS_9th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_8th_question.TAG))
                .add(R.id.fragment_container, new NS_9th_question(), NS_9th_question.TAG)
                .commitNow();
    }

    @Override
    public void onNinthQuestion() {
        Log.d(TAG, "onNinthQuestion: ");
        CURRENT_FRAGMENT = NS_10th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_9th_question.TAG))
                .add(R.id.fragment_container, new NS_10th_question(), NS_10th_question.TAG)
                .commitNow();
    }

    @Override
    public void onTenthQuestion() {
        CURRENT_FRAGMENT = NS_11th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_10th_question.TAG))
                .add(R.id.fragment_container, new NS_11th_question(), NS_11th_question.TAG)
                .commitNow();
    }

    @Override
    public void onEleventhQuestion() {
        Log.d(TAG, "onEleventhQuestion: ");
        CURRENT_FRAGMENT = NS_12th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_11th_question.TAG))
                .add(R.id.fragment_container, new NS_12th_question(), NS_12th_question.TAG)
                .commitNow();
    }

    @Override
    public void onTwelveQuestion() {
        Log.d(TAG, "onTwelveQuestion: ");
        CURRENT_FRAGMENT = NS_13th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_12th_question.TAG))
                .add(R.id.fragment_container, new NS_13th_question(), NS_13th_question.TAG)
                .commitNow();
    }

    @Override
    public void onThirteenQuestion() {
        Log.d(TAG, "onThirteenQuestion: ");
        CURRENT_FRAGMENT = NS_14th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_13th_question.TAG))
                .add(R.id.fragment_container, new NS_14th_question(), NS_14th_question.TAG)
                .commitNow();
    }

    @Override
    public void onFourteenQuestion() {
        Log.d(TAG, "onFourteenQuestion: ");
        CURRENT_FRAGMENT = NS_15th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_14th_question.TAG))
                .add(R.id.fragment_container, new NS_15th_question(), NS_15th_question.TAG)
                .commitNow();
    }

    @Override
    public void onFifteenQuestion() {
        Log.d(TAG, "onFifteenQuestion: ");
        CURRENT_FRAGMENT = NS_16th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_15th_question.TAG))
                .add(R.id.fragment_container, new NS_16th_question(), NS_16th_question.TAG)
                .commitNow();
    }

    @Override
    public void onSixteenQuestion() {
        Log.d(TAG, "onSixteenQuestion: ");
        CURRENT_FRAGMENT = NS_17th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_16th_question.TAG))
                .add(R.id.fragment_container, new NS_17th_question(), NS_17th_question.TAG)
                .commitNow();
    }

    @Override
    public void onSeventeenQuestion() {
        Log.d(TAG, "onSeventeenQuestion: ");
        CURRENT_FRAGMENT = NS_18th_question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_17th_question.TAG))
                .add(R.id.fragment_container, new NS_18th_question(), NS_18th_question.TAG)
                .commitNow();
    }

    @Override
    public void onEighteenQuestion() {
        Log.d(TAG, "onEighteenQuestion: ");
        CURRENT_FRAGMENT = End_of_Question_Set.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(NS_18th_question.TAG))
                .add(R.id.fragment_container, new End_of_Question_Set(), End_of_Question_Set.TAG)
                .commitNow();
    }

    @Override
    public void onTwenty5Question() {
        //TODO add some code to return to the activity...
    }


    @Override
    public void onJumpToEnd() {
        CURRENT_FRAGMENT = NS_1th_question.TAG;
        Log.d(TAG, "onJumpToEnd: ");
        if (getSupportFragmentManager().findFragmentByTag(NS_4th_question.TAG) == null) {

        } else {

        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right);
        fragmentTransaction.add(R.id.fragment_container, new End_of_Question_Set(), End_of_Question_Set.TAG).
                commit();
    }


    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: ");
        Log.d(TAG, String.valueOf(getSupportFragmentManager().getFragments().size()));
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (CURRENT_FRAGMENT) {
            case NS_1th_question.TAG: {
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_1th_question.TAG))
                        .commitNow();
                finish();

                break;
            }
            case NS_2nd_question.TAG: {
                CURRENT_FRAGMENT = NS_1th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_2nd_question.TAG))
                        .add(R.id.fragment_container, new NS_1th_question(), NS_1th_question.TAG)
                        .commitNow();

                break;
            }
            case NS_3rd_question.TAG: {
                CURRENT_FRAGMENT = NS_2nd_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_3rd_question.TAG))
                        .add(R.id.fragment_container, new NS_2nd_question(), NS_2nd_question.TAG)
                        .commitNow();

                break;
            }
            case NS_4th_question.TAG: {
                CURRENT_FRAGMENT = NS_3rd_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_4th_question.TAG))
                        .add(R.id.fragment_container, new NS_3rd_question(), NS_3rd_question.TAG)
                        .commitNow();

                break;
            }
            case NS_5th_question.TAG: {
                CURRENT_FRAGMENT = NS_4th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_5th_question.TAG))
                        .add(R.id.fragment_container, new NS_4th_question(), NS_4th_question.TAG)
                        .commitNow();

                break;
            }
            case NS_6th_question.TAG: {
                CURRENT_FRAGMENT = NS_5th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_6th_question.TAG))
                        .add(R.id.fragment_container, new NS_5th_question(), NS_5th_question.TAG)
                        .commitNow();

                break;
            }
            case NS_7th_question.TAG: {
                CURRENT_FRAGMENT = NS_6th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_7th_question.TAG))
                        .add(R.id.fragment_container, new NS_6th_question(), NS_6th_question.TAG)
                        .commitNow();

                break;
            }
            case NS_8th_question.TAG: {
                CURRENT_FRAGMENT = NS_7th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_8th_question.TAG))
                        .add(R.id.fragment_container, new NS_7th_question(), NS_7th_question.TAG)
                        .commitNow();

                break;
            }
            case NS_9th_question.TAG: {
                CURRENT_FRAGMENT = NS_8th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_9th_question.TAG))
                        .add(R.id.fragment_container, new NS_8th_question(), NS_8th_question.TAG)
                        .commitNow();

                break;
            }
            case NS_10th_question.TAG: {
                CURRENT_FRAGMENT = NS_9th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_10th_question.TAG))
                        .add(R.id.fragment_container, new NS_9th_question(), NS_9th_question.TAG)
                        .commitNow();

                break;
            }
            case NS_11th_question.TAG: {
                CURRENT_FRAGMENT = NS_10th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_11th_question.TAG))
                        .add(R.id.fragment_container, new NS_10th_question(), NS_10th_question.TAG)
                        .commitNow();

                break;
            }
            case NS_12th_question.TAG: {
                CURRENT_FRAGMENT = NS_11th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_12th_question.TAG))
                        .add(R.id.fragment_container, new NS_11th_question(), NS_11th_question.TAG)
                        .commitNow();
                break;
            }
            case NS_13th_question.TAG: {
                CURRENT_FRAGMENT = NS_12th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_13th_question.TAG))
                        .add(R.id.fragment_container, new NS_12th_question(), NS_12th_question.TAG)
                        .commitNow();
                break;
            }
            case NS_14th_question.TAG: {
                CURRENT_FRAGMENT = NS_13th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_14th_question.TAG))
                        .add(R.id.fragment_container, new NS_13th_question(), NS_13th_question.TAG)
                        .commitNow();

                break;
            }
            case NS_15th_question.TAG: {
                CURRENT_FRAGMENT = NS_14th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_15th_question.TAG))
                        .add(R.id.fragment_container, new NS_14th_question(), NS_14th_question.TAG)
                        .commitNow();

                break;
            }
            case NS_16th_question.TAG: {
                CURRENT_FRAGMENT = NS_15th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_16th_question.TAG))
                        .add(R.id.fragment_container, new NS_15th_question(), NS_15th_question.TAG)
                        .commitNow();

                break;
            }
            case NS_17th_question.TAG: {
                CURRENT_FRAGMENT = NS_16th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_17th_question.TAG))
                        .add(R.id.fragment_container, new NS_16th_question(), NS_16th_question.TAG)
                        .commitNow();

                break;
            }
            case NS_18th_question.TAG: {
                CURRENT_FRAGMENT = NS_17th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(NS_18th_question.TAG))
                        .add(R.id.fragment_container, new NS_17th_question(), NS_17th_question.TAG)
                        .commitNow();

                break;
            }
            case End_of_Question_Set.TAG: {
                CURRENT_FRAGMENT = NS_18th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_18th_question(), NS_18th_question.TAG)
                        .commitNow();

                break;
            }
        }
    }

    @Override
    public void firstUnAnsweredQuestion(int questionNumber) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (questionNumber){
            case 1:{
                CURRENT_FRAGMENT=NS_1th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_1th_question(), NS_1th_question.TAG)
                        .commitNow();
                break;
            }
            case 2:{
                CURRENT_FRAGMENT=NS_2nd_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_2nd_question(), NS_2nd_question.TAG)
                        .commitNow();
                break;
            }
            case 3:{
                CURRENT_FRAGMENT=NS_3rd_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_3rd_question(), NS_3rd_question.TAG)
                        .commitNow();
                break;
            }
            case 4:{
                CURRENT_FRAGMENT=NS_4th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_4th_question(), NS_4th_question.TAG)
                        .commitNow();
                break;
            }
            case 5:{
                CURRENT_FRAGMENT=NS_5th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_5th_question(), NS_5th_question.TAG)
                        .commitNow();
                break;
            }
            case 6:{
                CURRENT_FRAGMENT=NS_6th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_6th_question(), NS_6th_question.TAG)
                        .commitNow();
                break;
            }
            case 7:{
                CURRENT_FRAGMENT=NS_7th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_7th_question(), NS_7th_question.TAG)
                        .commitNow();
                break;
            }
            case 8:{
                CURRENT_FRAGMENT=NS_8th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_8th_question(), NS_8th_question.TAG)
                        .commitNow();
                break;
            }
            case 9:{
                CURRENT_FRAGMENT=NS_9th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_9th_question(), NS_9th_question.TAG)
                        .commitNow();
                break;
            }
            case 10:{
                CURRENT_FRAGMENT=NS_10th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_10th_question(), NS_10th_question.TAG)
                        .commitNow();
                break;
            }
            case 11:{
                CURRENT_FRAGMENT=NS_11th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_11th_question(), NS_11th_question.TAG)
                        .commitNow();
                break;
            }
            case 12:{
                CURRENT_FRAGMENT=NS_12th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_12th_question(), NS_12th_question.TAG)
                        .commitNow();
                break;
            }
            case 13:{
                CURRENT_FRAGMENT=NS_13th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_13th_question(), NS_13th_question.TAG)
                        .commitNow();
                break;
            }
            case 14:{
                CURRENT_FRAGMENT=NS_14th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_14th_question(), NS_14th_question.TAG)
                        .commitNow();
                break;
            }
            case 15:{
                CURRENT_FRAGMENT=NS_15th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_15th_question(), NS_15th_question.TAG)
                        .commitNow();
                break;
            }
            case 16:{
                CURRENT_FRAGMENT=NS_16th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_16th_question(), NS_16th_question.TAG)
                        .commitNow();
                break;
            }
            case 17:{
                CURRENT_FRAGMENT=NS_17th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_17th_question(), NS_17th_question.TAG)
                        .commitNow();
                break;
            }
            case 18:{
                CURRENT_FRAGMENT=NS_18th_question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.fragment_container, new NS_18th_question(), NS_18th_question.TAG)
                        .commitNow();
                break;
            }
        }

    }
}
