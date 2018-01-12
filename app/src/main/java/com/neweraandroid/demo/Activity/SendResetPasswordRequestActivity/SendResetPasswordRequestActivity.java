package com.neweraandroid.demo.Activity.SendResetPasswordRequestActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.neweraandroid.demo.R;
import com.neweraandroid.demo.SendResetPasswordRequestListener;


public class SendResetPasswordRequestActivity extends AppCompatActivity {

    Button submit, birthDate;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_send_reset_password_request);
        if( savedInstanceState == null ){
            SendResetPasswordRequestListener listener = new SendResetPasswordRequestListener (this);
            submit = findViewById ( R.id.btnSendResetPasswordReq );
            listener.setOnViewClickListener ( submit );
            birthDate = findViewById ( R.id.btnBirthDate );
            listener.setOnViewClickListener ( birthDate );
            email = findViewById ( R.id.edtEmail );

        }
    }
}
