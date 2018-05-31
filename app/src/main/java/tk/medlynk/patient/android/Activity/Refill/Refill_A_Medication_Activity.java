package tk.medlynk.patient.android.Activity.Refill;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Refill_A_Medication_Activity extends AppCompatActivity implements
        Refill_A_Medication_VH.Refill_A_Medication_ClickListener,
        Refill_first_Question.onRefillFirstQuestionInteractionListener,
        Refill_second_Question.onRefillSecondQuestionInteractionListener,
        Refill_third_Question.onRefillThirdQuestionInteractionListener,
        Refill_fourth_Question.onRefillFourthQuestionInteractionListener,
        Refill_fifth_Question.onRefillFifthQuestionInteractionListener,
        Refill_sixth_Question.onRefillSixthQuestionInteractionListener,
        Refill_seventh_Question.onRefillSeventhQuestionInteractionListener,
        Refill_eighth_Question.onRefillEighthQuestionInteractionListener
{

    private View parent;
    private Refill_A_Medication_VH viewholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_refill__a__medication );
        this.parent = findViewById(R.id.parent_refill_a_medication);
        this.viewholder = new Refill_A_Medication_VH(this.parent);
        this.viewholder.setClickListener(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.add ( R.id.refill_container,
                new Refill_first_Question (),
                Refill_first_Question.TAG).commit ();
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_SET, "refill_a_medication" );
    }

    @Override
    public void OnStartRefillClicked() {
        onBackPressed ();
    }

    @Override
    public void onRefillFistQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillFistQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.refill_container,
                new Refill_second_Question (),
                Refill_second_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_second_Question.TAG );
    }

    @Override
    public void onRefillSecondQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillSecondQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.refill_container,
                new Refill_third_Question (),
                Refill_third_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_third_Question.TAG );
    }

    @Override
    public void onRefillThirdQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillThirdQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.refill_container,
                new Refill_fourth_Question (),
                Refill_fourth_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_fourth_Question.TAG );
    }

    @Override
    public void onRefillFourthQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillFourthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.refill_container,
                new Refill_fifth_Question (),
                Refill_fifth_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_fifth_Question.TAG );
    }

    @Override
    public void onRefillFifthQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillFifthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.refill_container,
                new Refill_sixth_Question (),
                Refill_sixth_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_sixth_Question.TAG );
    }

    @Override
    public void onRefillSixthQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillSixthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.refill_container,
                new Refill_seventh_Question (),
                Refill_seventh_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_seventh_Question.TAG );
    }

    @Override
    public void onRefillSeventhQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillSeventhQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.refill_container,
                new Refill_eighth_Question (),
                Refill_eighth_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_eighth_Question.TAG );
    }

    @Override
    public void onRefillEighthQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillEighthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.setCustomAnimations ( R.anim.in_right, R.anim.in_left )
                .add ( R.id.refill_container,
                        new End_of_Question_Set (),
                        End_of_Question_Set.TAG).commit ();
        fragmentTransaction.addToBackStack ( End_of_Question_Set.TAG );
    }
}
