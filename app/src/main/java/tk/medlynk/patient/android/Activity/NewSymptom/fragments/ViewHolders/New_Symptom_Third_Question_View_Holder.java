package tk.medlynk.patient.android.Activity.NewSymptom.fragments.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 2/9/2018.
 */

public class New_Symptom_Third_Question_View_Holder extends RecyclerView.ViewHolder {

    View years_ago_layout, months_ago_layout, weeks_ago_layout, days_ago_layout;
    TextView years_ago_layout_text, months_ago_layout_text, weeks_ago_layout_text, days_ago_layout_text;
    EditText years_ago_layout_answer, months_ago_layout_answer, weeks_ago_layout_answer, days_ago_layout_answer;
    Button today, i_do_not_know;

    public New_Symptom_Third_Question_View_Holder(View itemView) {
            super ( itemView );
            years_ago_layout = itemView.findViewById ( R.id.new_symptom_third_question_years_ago );
            months_ago_layout = itemView.findViewById ( R.id.new_symptom_third_question_months_ago );
            weeks_ago_layout = itemView.findViewById ( R.id.new_symptom_third_question_weeks_ago );
            days_ago_layout = itemView.findViewById ( R.id.new_symptom_third_question_days_ago );
            years_ago_layout_text = years_ago_layout.findViewById ( R.id.txtTime );
            years_ago_layout_text.setText ( R.string.year_ago );
            months_ago_layout_text = months_ago_layout.findViewById ( R.id.txtTime );
            months_ago_layout_text.setText ( R.string.months_ago );
            weeks_ago_layout_text = weeks_ago_layout.findViewById ( R.id.txtTime );
            weeks_ago_layout_text.setText ( R.string.weeks_ago );
            days_ago_layout_text = days_ago_layout.findViewById ( R.id.txtTime );
            days_ago_layout_text.setText ( R.string.days_ago );
            years_ago_layout_answer = years_ago_layout.findViewById ( R.id.edtAnswer );
            years_ago_layout_answer.setOnFocusChangeListener ( new View.OnFocusChangeListener () {
                @Override
                public void onFocusChange(View view, boolean b) {
                    System.out.println ("years_ago_layout_answer");
                }
            } );
            months_ago_layout_answer = months_ago_layout.findViewById ( R.id.edtAnswer );
            months_ago_layout_answer.setOnFocusChangeListener ( new View.OnFocusChangeListener () {
                @Override
                public void onFocusChange(View view, boolean b) {
                    System.out.println ("months_ago_layout_answer");
                }
            } );
            weeks_ago_layout_answer = weeks_ago_layout.findViewById ( R.id.edtAnswer );
            weeks_ago_layout_answer.setOnFocusChangeListener ( new View.OnFocusChangeListener () {
                @Override
                public void onFocusChange(View view, boolean b) {
                    System.out.println ("weeks_ago_layout_answer");
                    months_ago_layout_answer.setClickable ( false );
                }
            } );
            days_ago_layout_answer = days_ago_layout.findViewById ( R.id.edtAnswer );

        
    }


}
