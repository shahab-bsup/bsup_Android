package com.neweraandroid.demo.Activity.StartQuestionSet;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 2/1/2018.
 */

public class StartQuestionSetViewHolder extends RecyclerView.ViewHolder {

    LinearLayout discuss_new_symptom, review_test_result, refill_a_medication, review_new_symptom;
    StartQuestionSetClickListener clickListener;

    public StartQuestionSetViewHolder(View itemView) {
        super ( itemView );
        clickListener = new StartQuestionSetClickListener ( itemView.getContext () );
        discuss_new_symptom = itemView.findViewById ( R.id.discuss_new_sysmptom );
        discuss_new_symptom.setOnClickListener ( clickListener );
        review_new_symptom = itemView.findViewById ( R.id.review_sysmptoms );
        review_new_symptom.setOnClickListener ( clickListener );
        review_test_result = itemView.findViewById ( R.id.review_test_results );
        review_test_result.setOnClickListener ( clickListener );
        refill_a_medication = itemView.findViewById ( R.id.refill_a_medication );
        refill_a_medication.setOnClickListener ( clickListener );
    }
}