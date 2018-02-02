package com.neweraandroid.demo.Activity.SendResetPasswordRequestActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 1/13/2018.
 */

public class SendResetPasswordRequestListener implements
        View.OnClickListener,
        DatePickerDialog.OnDateSetListener, TextWatcher {

    private Context context;
    private String email;

    public SendResetPasswordRequestListener(Context context) {
        this.context = context;
    }

    @NonNull
    public void setOnViewClickListener(View view){
        view.setOnClickListener ( this );
    }

    public void setTextWatcherOnEditText(EditText editText){
        editText.addTextChangedListener ( this );
    }

    @Override
    public void onClick(View view) {
        int id = view.getId ();
        switch (id){
            case R.id.btnBirthDate:{
                System.out.println ("btnBirthDate!");
                    DatePickerDialog datePickerDialog =
                            new DatePickerDialog ( this.context,
                                    SendResetPasswordRequestListener.this,
                                    2017,
                                    1,
                                    14
                                    );
                    datePickerDialog.show ();
                break;
            }
            case R.id.btnSendResetPasswordReq:{

                break;
            }
        }
    }

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                System.out.println (i);
                System.out.println (i1);
                System.out.println (i2);
            }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        email = editable.toString ();
    }
}
