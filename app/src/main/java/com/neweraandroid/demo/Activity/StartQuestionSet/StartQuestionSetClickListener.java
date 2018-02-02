package com.neweraandroid.demo.Activity.StartQuestionSet;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.neweraandroid.demo.Activity.NewSymptom.NewSymptomActivity;
import com.neweraandroid.demo.Activity.Start_a_Refill.Start_A_New_RefillActivity;
import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 2/1/2018.
 */

public class StartQuestionSetClickListener implements View.OnClickListener {

    private Context context;

    public StartQuestionSetClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case R.id.discuss_new_sysmptom:{
                System.out.println ("discuss_new_symptom");
                context.startActivity ( new Intent ( context, NewSymptomActivity.class ) );
                break;
            }
            case R.id.review_sysmptoms:{
                System.out.println ("review_sysmptoms");
                break;
            }
            case R.id.review_test_results:{
                System.out.println ("review_test_results");
                break;
            }
            case R.id.refill_a_medication:{
                System.out.println ("refill_a_medication");
                context.startActivity ( new Intent ( context, Start_A_New_RefillActivity.class ) );
                break;
            }
        }
    }
}