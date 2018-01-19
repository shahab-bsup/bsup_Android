package com.neweraandroid.demo.Activity.SendResetPasswordRequestActivity;

import android.app.DatePickerDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.CustomViews.SnackController;
import com.neweraandroid.demo.Essentials.Utils;
import com.neweraandroid.demo.Networking.MedlynkRequests;
import com.neweraandroid.demo.R;
import com.neweraandroid.demo.SendResetPasswordRequestListener;


public class SendResetPasswordRequestActivity extends AppCompatActivity
    implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener,
        OnSendResetPasswordRequestListener
{

    View parent_view;
    int year, month, day;
    SendResetPasswordViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_send_reset_password_request);
        if( savedInstanceState == null ){
            parent_view = findViewById ( R.id.parent_activity_send_reset_password_request );
            viewHolder = new SendResetPasswordViewHolder ( parent_view );
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){

//            case R.id.btnSendResetPasswordReq:{
//                if(!Utils.isEmailValid ( email.getText ().toString () )){
//                    SnackController.getInstance ().init ( SendResetPasswordRequestActivity.this,
//                            "Email is not valid!", Snackbar.LENGTH_LONG).showSnackBar ();
//                }else{
//                    MedlynkRequests.sendResetPasswordRequest (email.getText ().toString ());
//                }
//                break;
//            }

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

    @Override
    public void onSendResetPasswordRequestSuccess() {
        System.out.println ("message!");
    }

    @Override
    public void onSendResetPasswordRequestFailure(String message, Constants.EXCEPTION_TYPE exception_type) {

    }

    @Override
    public void onSendResetPasswordRequestFailure(Constants.EXCEPTION_TYPE exception_type) {

    }
}