package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_3;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.CustomViews.CustomTextView;

/**
 * Created by Shahab on 2/23/2018.
 */

public class New_Symptom_3rd_question_ViewHolder extends RecyclerView.ViewHolder {

    LinearLayout years_row, months_row, weeks_row, days_row;
    AppCompatEditText years_row_input, months_row_input, weeks_row_input, days_row_input;
    CustomTextView years_row_text, months_row_text, weeks_row_text, days_row_text;
    ProgressBar progressBar;

    public New_Symptom_3rd_question_ViewHolder(View itemView) {
        super ( itemView );

        years_row = itemView.findViewById ( R.id.new_symptom_third_question_years_ago );
        months_row = itemView.findViewById ( R.id.new_symptom_third_question_months_ago );
        weeks_row = itemView.findViewById ( R.id.new_symptom_third_question_weeks_ago );
        days_row = itemView.findViewById ( R.id.new_symptom_third_question_days_ago );

        years_row_input = years_row.findViewById ( R.id.edtAnswer );
        years_row_input.setOnFocusChangeListener ( new Years_Input_FocusListener () );
        years_row_text = years_row.findViewById ( R.id.txtTime );
        years_row_text.setText ( R.string.year_ago );

        months_row_input = months_row.findViewById ( R.id.edtAnswer );
        months_row_input.setOnFocusChangeListener ( new Months_Input_FocusListener () );
        months_row_text = months_row.findViewById ( R.id.txtTime );
        months_row_text.setText ( R.string.months_ago );

        weeks_row_input = weeks_row.findViewById ( R.id.edtAnswer );
        weeks_row_input.setOnFocusChangeListener ( new Weeks_Input_FocusListener () );
        weeks_row_text = weeks_row.findViewById ( R.id.txtTime );
        weeks_row_text.setText ( R.string.weeks_ago );

        days_row_input = days_row.findViewById ( R.id.edtAnswer );
        days_row_input.setOnFocusChangeListener ( new Days_Input_FocusListener () );
        days_row_text = days_row.findViewById ( R.id.txtTime );
        days_row_text.setText ( R.string.days_ago );

        progressBar = itemView.findViewById ( R.id.progress_bar );
    }

    public String getYearsDuration(){
        return years_row_input.getText ().toString ();
    }

    public String getMonthsDuration(){
        return months_row_input.getText ().toString ();
    }

    public String getWeeksDuration(){
        return weeks_row_input.getText ().toString ();
    }

    public String getDaysDuration(){
        return days_row_input.getText ().toString ();
    }

    public class Years_Input_FocusListener implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View view, boolean b) {
            System.out.println ( "view = [" + view + "], b = [" + b + "]" );
            if( b ){
                months_row_input.setText ( "" );
                weeks_row_input.setText ( "" );
                days_row_input.setText ( "" );
            }
        }
    }

    public class Months_Input_FocusListener implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View view, boolean b) {
            System.out.println ( "view = [" + view + "], b = [" + b + "]" );
            if( b ){
                years_row_input.setText ( "" );
                weeks_row_input.setText ( "" );
                days_row_input.setText ( "" );
            }
        }
    }

    public class Weeks_Input_FocusListener implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View view, boolean b) {
            System.out.println ( "view = [" + view + "], b = [" + b + "]" );
            if( b ){
                months_row_input.setText ( "" );
                years_row_input.setText ( "" );
                days_row_input.setText ( "" );
            }
        }
    }

    public class Days_Input_FocusListener implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View view, boolean b) {
            System.out.println ( "view = [" + view + "], b = [" + b + "]" );
            if( b ){
                months_row_input.setText ( "" );
                weeks_row_input.setText ( "" );
                years_row_input.setText ( "" );
            }
        }
    }

    public Choice_Value getValueFilledInput(){
        if (!TextUtils.isEmpty ( years_row_input.getText () )){
            return new Choice_Value ( "a", Integer.parseInt ( years_row_input.getText ().toString () ));
        }else if ( !TextUtils.isEmpty ( months_row_input.getText () )){
            return new Choice_Value ( "b", Integer.parseInt ( months_row_input.getText ().toString () ));
        } else if ( !TextUtils.isEmpty ( weeks_row_input.getText () ) ){
            return new Choice_Value ( "c", Integer.parseInt ( weeks_row_input.getText ().toString () ));
        }else if ( !TextUtils.isEmpty ( days_row_input.getText () ) ){
            return new Choice_Value ( "d", Integer.parseInt ( days_row_input.getText ().toString () ));
        }
        return new Choice_Value ( "z", 0 );
    }

    public void setProgressBarVisibilityStatus(int status){
        this.progressBar.setVisibility ( status );
    }
}
