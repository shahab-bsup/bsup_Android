package tk.medlynk.patient.android.Activity.Refill;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.Refill.fragments.End_of_Question_Set.End_of_Question_Set;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_1.Refill_first_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_2.Refill_second_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_3.Refill_third_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_4.Refill_fourth_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_5.Refill_fifth_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_6.Refill_sixth_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_7.Refill_seventh_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_8.Refill_eighth_Question;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;

public class Refill_A_Medication_Activity extends AppCompatActivity implements
        Refill_A_Medication_VH.Refill_A_Medication_ClickListener,
        Refill_first_Question.onRefillFirstQuestionInteractionListener,
        Refill_second_Question.onRefillSecondQuestionInteractionListener,
        Refill_third_Question.onRefillThirdQuestionInteractionListener,
        Refill_fourth_Question.onRefillFourthQuestionInteractionListener,
        Refill_fifth_Question.onRefillFifthQuestionInteractionListener,
        Refill_sixth_Question.onRefillSixthQuestionInteractionListener,
        Refill_seventh_Question.onRefillSeventhQuestionInteractionListener,
        Refill_eighth_Question.onRefillEighthQuestionInteractionListener,
        End_of_Question_Set.OnEndOfRefillListener{

    private static final String TAG = Refill_A_Medication_Activity.class.getSimpleName();
    private View parent;
    private Refill_A_Medication_VH viewholder;
    private SharedPreferenceManager manager;

    private String CURRENT_FRAGMENT = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refill__a__medication);
        this.parent = findViewById(R.id.parent_refill_a_medication);
        this.viewholder = new Refill_A_Medication_VH(this.parent);
        this.viewholder.setClickListener(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.refill_container, new Refill_first_Question(), Refill_first_Question.TAG)
                .commitNow();
        Constants.REFILL_A_MEDICATION_BODY.put(Constants.QUESTION_SET, "refill_a_medication");
    }

    @Override
    public void onBackButtonClicked() {
        onBackPressed();
    }

    @Override
    public void onRefillFistQuestion() {
        System.out.println("Refill_A_Medication_Activity.onRefillFistQuestion");
        CURRENT_FRAGMENT = Refill_second_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(Refill_first_Question.TAG))
                .add(R.id.refill_container, new Refill_second_Question(), Refill_second_Question.TAG)
                .commitNow();
    }

    @Override
    public void onRefillSecondQuestion() {
        Log.d(TAG, "onRefillSecondQuestion: ");
        CURRENT_FRAGMENT = Refill_third_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(Refill_second_Question.TAG))
                .add(R.id.refill_container, new Refill_third_Question(), Refill_third_Question.TAG)
                .commitNow();
    }

    @Override
    public void onRefillThirdQuestion() {
        Log.d(TAG, "onRefillThirdQuestion: ");
        CURRENT_FRAGMENT = Refill_fourth_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(Refill_third_Question.TAG))
                .add(R.id.refill_container, new Refill_fourth_Question(), Refill_fourth_Question.TAG)
                .commitNow();
    }

    @Override
    public void onRefillFourthQuestion() {
        Log.d(TAG, "onRefillFourthQuestion: ");
        CURRENT_FRAGMENT = Refill_fifth_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(Refill_fourth_Question.TAG))
                .add(R.id.refill_container, new Refill_fifth_Question(), Refill_fifth_Question.TAG)
                .commitNow();
    }

    @Override
    public void onRefillFifthQuestion() {
        Log.d(TAG, "onRefillFifthQuestion: ");
        CURRENT_FRAGMENT = Refill_sixth_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(Refill_fifth_Question.TAG))
                .add(R.id.refill_container, new Refill_sixth_Question(), Refill_sixth_Question.TAG)
                .commitNow();
    }

    @Override
    public void onRefillSixthQuestion() {
        Log.d(TAG, "onRefillSixthQuestion: ");
        CURRENT_FRAGMENT = Refill_seventh_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(Refill_sixth_Question.TAG))
                .add(R.id.refill_container, new Refill_seventh_Question(), Refill_seventh_Question.TAG)
                .commitNow();
    }

    @Override
    public void onRefillSeventhQuestion() {
        Log.d(TAG, "onRefillSeventhQuestion: ");
        CURRENT_FRAGMENT = Refill_eighth_Question.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(Refill_seventh_Question.TAG))
                .add(R.id.refill_container, new Refill_eighth_Question(), Refill_eighth_Question.TAG)
                .commitNow();
    }

    @Override
    public void onRefillEighthQuestion() {
        Log.d(TAG, "onRefillEighthQuestion: ");
        CURRENT_FRAGMENT = End_of_Question_Set.TAG;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                .remove(getSupportFragmentManager().findFragmentByTag(Refill_eighth_Question.TAG))
                .add(R.id.refill_container, new End_of_Question_Set(), End_of_Question_Set.TAG)
                .commitNow();
    }

    @Override
    public void onBackPressed() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (CURRENT_FRAGMENT) {
            case Refill_first_Question.TAG: {
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(Refill_first_Question.TAG))
                        .commitNow();
                finish();
                break;
            }
            case Refill_second_Question.TAG: {
                CURRENT_FRAGMENT = Refill_first_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(Refill_second_Question.TAG))
                        .add(R.id.refill_container, new Refill_first_Question(), Refill_first_Question.TAG)
                        .commitNow();

                break;
            }
            case Refill_third_Question.TAG: {
                CURRENT_FRAGMENT = Refill_second_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(Refill_third_Question.TAG))
                        .add(R.id.refill_container, new Refill_second_Question(), Refill_second_Question.TAG)
                        .commitNow();

                break;
            }
            case Refill_fourth_Question.TAG: {
                CURRENT_FRAGMENT = Refill_third_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(Refill_fourth_Question.TAG))
                        .add(R.id.refill_container, new Refill_third_Question(), Refill_third_Question.TAG)
                        .commitNow();

                break;
            }
            case Refill_fifth_Question.TAG: {
                CURRENT_FRAGMENT = Refill_fourth_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(Refill_fifth_Question.TAG))
                        .add(R.id.refill_container, new Refill_fourth_Question(), Refill_fourth_Question.TAG)
                        .commitNow();

                break;
            }
            case Refill_sixth_Question.TAG: {
                CURRENT_FRAGMENT = Refill_fifth_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(Refill_sixth_Question.TAG))
                        .add(R.id.refill_container, new Refill_fifth_Question(), Refill_fifth_Question.TAG)
                        .commitNow();

                break;
            }
            case Refill_seventh_Question.TAG: {
                CURRENT_FRAGMENT = Refill_sixth_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(Refill_seventh_Question.TAG))
                        .add(R.id.refill_container, new Refill_sixth_Question(), Refill_sixth_Question.TAG)
                        .commitNow();

                break;
            }
            case Refill_eighth_Question.TAG: {
                CURRENT_FRAGMENT = Refill_seventh_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(Refill_eighth_Question.TAG))
                        .add(R.id.refill_container, new Refill_seventh_Question(), Refill_seventh_Question.TAG)
                        .commitNow();

                break;
            }
            case End_of_Question_Set.TAG: {
                CURRENT_FRAGMENT = Refill_eighth_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.refill_container, new Refill_eighth_Question(), Refill_eighth_Question.TAG)
                        .commitNow();

                break;
            }
        }
    }

    @Override
    public void firstUnAnsweredQuestion(int questionNumber){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (questionNumber) {
            case 1:{
                CURRENT_FRAGMENT=Refill_first_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.refill_container, new Refill_first_Question(), Refill_first_Question.TAG)
                        .commitNow();
                break;
            }
            case 2:{
                CURRENT_FRAGMENT=Refill_second_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.refill_container, new Refill_second_Question(), Refill_second_Question.TAG)
                        .commitNow();
                break;
            }
            case 3:{
                CURRENT_FRAGMENT=Refill_third_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.refill_container, new Refill_third_Question(), Refill_third_Question.TAG)
                        .commitNow();
                break;
            }
            case 4:{
                CURRENT_FRAGMENT=Refill_fourth_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.refill_container, new Refill_fourth_Question(), Refill_fourth_Question.TAG)
                        .commitNow();
                break;
            }
            case 5:{
                CURRENT_FRAGMENT=Refill_fifth_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.refill_container, new Refill_fifth_Question(), Refill_fifth_Question.TAG)
                        .commitNow();
                break;
            }
            case 6:{
                CURRENT_FRAGMENT=Refill_sixth_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.refill_container, new Refill_sixth_Question(), Refill_sixth_Question.TAG)
                        .commitNow();
                break;
            }
            case 7:{
                CURRENT_FRAGMENT=Refill_seventh_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.refill_container, new Refill_seventh_Question(), Refill_seventh_Question.TAG)
                        .commitNow();
                break;
            }
            case 8:{
                CURRENT_FRAGMENT=Refill_eighth_Question.TAG;
                fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_right)
                        .remove(getSupportFragmentManager().findFragmentByTag(End_of_Question_Set.TAG))
                        .add(R.id.refill_container, new Refill_eighth_Question(), Refill_eighth_Question.TAG)
                        .commitNow();
                break;
            }
        }

    }
}
