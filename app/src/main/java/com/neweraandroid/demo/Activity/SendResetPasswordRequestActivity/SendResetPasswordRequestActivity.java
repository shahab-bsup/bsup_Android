package com.neweraandroid.demo.Activity.SendResetPasswordRequestActivity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.neweraandroid.demo.R;
import com.neweraandroid.demo.SendResetPasswordRequestListener;


public class SendResetPasswordRequestActivity extends AppCompatActivity
    implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener
{

    Button submit, birthDate;
    EditText email;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_send_reset_password_request);
        if( savedInstanceState == null ){
            submit = findViewById ( R.id.btnSendResetPasswordReq );
            submit.setOnClickListener ( this );
            birthDate = findViewById ( R.id.btnBirthDate );
            birthDate.setOnClickListener ( this );
            email = findViewById ( R.id.edtEmail );
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case R.id.btnSendResetPasswordReq:{

                break;
            }
            case R.id.btnBirthDate:{
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog (SendResetPasswordRequestActivity.this,
                                SendResetPasswordRequestActivity.this,
                                2017,
                                1,
                                14
                        );
                datePickerDialog.show ();
                break;
            }
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        year = i;
        month = i1;
        day = i2;
        System.out.println ( year + " " + month + " " + day);
    }
}
