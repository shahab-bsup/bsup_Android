package tk.medlynk.patient.android.Activity.Refill;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Activity.Refill.fragments.Question_1.Refill_first_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_2.Refill_second_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_3.Refill_third_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_4.Refill_fourth_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_5.Refill_fifth_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_6.Refill_sixth_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Question_7.Refill_seventh_Question;
import tk.medlynk.patient.android.Activity.Refill.fragments.Qustion_8.Refill_eighth_Question;

public class Refill_A_Medication_Activity extends AppCompatActivity implements
        Refill_A_Medication_Activity_ViewHolder.Refill_A_Medication_ClickListener,
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
    private Refill_A_Medication_Activity_ViewHolder viewholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_refill__a__medication );
        this.parent = findViewById(R.id.parent_refill_a_medication);
        this.viewholder = new Refill_A_Medication_Activity_ViewHolder(this.parent);
        this.viewholder.setClickListener(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.add ( R.id.refill_container,
                new Refill_first_Question (),
                Refill_first_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_first_Question.TAG );
    }

    @Override
    public void OnStartRefillClicked() {
        onBackPressed ();
    }

    @Override
    public void onRefillFistQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillFistQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.add ( R.id.refill_container,
                new Refill_second_Question (),
                Refill_second_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_second_Question.TAG );
    }

    @Override
    public void onRefillSecondQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillSecondQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.add ( R.id.refill_container,
                new Refill_third_Question (),
                Refill_third_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_third_Question.TAG );
    }

    @Override
    public void onRefillThirdQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillThirdQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.add ( R.id.refill_container,
                new Refill_fourth_Question (),
                Refill_fourth_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_fourth_Question.TAG );
    }

    @Override
    public void onRefillFourthQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillFourthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.add ( R.id.refill_container,
                new Refill_fifth_Question (),
                Refill_fifth_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_fifth_Question.TAG );
    }

    @Override
    public void onRefillFifthQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillFifthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.add ( R.id.refill_container,
                new Refill_sixth_Question (),
                Refill_sixth_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_sixth_Question.TAG );
    }

    @Override
    public void onRefillSixthQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillSixthQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.add ( R.id.refill_container,
                new Refill_seventh_Question (),
                Refill_seventh_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_seventh_Question.TAG );
    }

    @Override
    public void onRefillSeventhQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillSeventhQuestion" );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.add ( R.id.refill_container,
                new Refill_first_Question (),
                Refill_eighth_Question.TAG).commit ();
        fragmentTransaction.addToBackStack ( Refill_eighth_Question.TAG );
    }

    @Override
    public void onRefillEighthQuestion() {
        System.out.println ( "Refill_A_Medication_Activity.onRefillEighthQuestion" );

    }
}
