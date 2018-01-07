package com.bsuperiorsystem.doctor_app.Activity.Login;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Shahab on 1/6/2018.
 */

public class Login {
//    public ObservableField<String> email;
//    public ObservableField<String> password;
    public String email;
    public String password;
    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            System.out.println(editable);
        }
    };

}
