package com.bsuperiorsystem.doctor_app.Activity.Login;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.bsuperiorsystem.doctor_app.Activity.SendResetPasswordRequest.SendResetPasswordRequest;
import com.bsuperiorsystem.doctor_app.Activity.SignUp.SignUpActivity;
import com.bsuperiorsystem.doctor_app.R;
import com.bsuperiorsystem.doctor_app.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    Button login;
    TextView create_account, forget_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginViewModel loginViewModel = new LoginViewModel(this);
        binding.setLoginvm(loginViewModel);
    }
}
