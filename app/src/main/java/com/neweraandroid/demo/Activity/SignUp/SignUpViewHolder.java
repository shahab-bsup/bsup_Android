package com.neweraandroid.demo.Activity.SignUp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.neweraandroid.demo.Activity.Login.LoginActivity;
import com.neweraandroid.demo.Activity.SearchDoctor.SearchActivity;
import com.neweraandroid.demo.Activity.Splash.SplashActivity;
import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.CustomViews.SnackController;
import com.neweraandroid.demo.Essentials.Utils;
import com.neweraandroid.demo.Networking.MedlynkRequests;
import com.neweraandroid.demo.R;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Shahab on 1/14/2018.
 */

public class SignUpViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener,
        RadioGroup.OnCheckedChangeListener,
        DatePickerDialog.OnDateSetListener,
        OnSignUpListener
{

    EditText firstName, lastName, middleName, email, password, confirmedPassword;
    Button submit, birthDate;
    String gender = "null";
    boolean isFirstNameValid, isLastNameValid, isEmailValid;
    boolean isPasswordValid, isPasswordConfirmed;

    int year, month, day;

    View parentView;

    RadioGroup radioGroup;

    public SignUpViewHolder(View itemView) {
        super ( itemView );
        parentView = itemView;
        firstName = itemView.findViewById ( R.id.edtFirstName );
        lastName = itemView.findViewById ( R.id.edtLastName );
        middleName = itemView.findViewById ( R.id.edtMiddleName );
        email = itemView.findViewById ( R.id.edtEmail );
        password = itemView.findViewById ( R.id.edtFirstPassword );
        confirmedPassword = itemView.findViewById ( R.id.edtRetypedPassWord );
        submit = itemView.findViewById ( R.id.btnSignUp );
        birthDate = itemView.findViewById ( R.id.btnBirthDate );
        birthDate.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance ();
                DatePickerDialog datePickerDialog = new DatePickerDialog ( parentView.getContext (),
                        SignUpViewHolder.this, calendar.get ( Calendar.YEAR ), calendar.get ( Calendar.MONTH ), calendar.get ( Calendar.DAY_OF_MONTH ));
                datePickerDialog.show ();
            }
        } );
        radioGroup = itemView.findViewById ( R.id.radio_group );
        radioGroup.setOnCheckedChangeListener ( this );
        submit.setOnClickListener ( this );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case android.support.design.R.id.snackbar_action:{
                System.out.println ("SnackBar action!");
            }
            case R.id.btnSignUp:{
                if (!isNameValid ( firstName.getText ().toString () )) {
                    firstName.setError ( "This field can not be empty!" );
                    isFirstNameValid = false;
                } else {
                    isFirstNameValid = true;
                }

                if (!isNameValid ( lastName.getText ().toString () )) {
                    lastName.setError ( "This field can not be empty!" );
                    isLastNameValid = false;
                } else {
                    isLastNameValid = true;
                }

                if (!Utils.isEmailValid ( email.getText ().toString () )) {
                    email.setError ( "Email is not Correct!" );
                    isEmailValid = false;
                } else {
                    isEmailValid = true;
                }

                if (!Utils.isPasswordValid ( password.getText ().toString () )) {
                    password.setError ( "must be at least 6 characters!" );
                    isPasswordValid = false;
                } else {
                    isPasswordValid = true;
                }

                if (!password.getText ().toString ().equals ( confirmedPassword.getText ().toString () )) {
                    confirmedPassword.setError ( "Passwords don't match!" );
                    isPasswordConfirmed = false;
                } else {
                    isPasswordConfirmed = true;
                }

                if (isFirstNameValid && isLastNameValid && isEmailValid && isPasswordValid && isPasswordConfirmed) {
                    if (!isGenderChecked ()) {
                        SnackController.getInstance ().init ( parentView.getContext (),
                                "Specify the Gender",
                                Snackbar.LENGTH_LONG )
                                .showSnackBar ();
                    } else if (!Utils.isDeviceConnected ( parentView.getContext () )) {
                        SnackController.getInstance ()
                                .init ( parentView.getContext (), R.string.no_intenet_connection, Snackbar.LENGTH_INDEFINITE )
                                .setAction ( R.string.try_again , this ).showSnackBar ();
                    }else {
                        HashMap<String, Object> body = new HashMap<> ();
//                        HashMap<String, Integer> body1 = new HashMap<> ();
                        body.put ( "as", "patient" );
                        body.put ( "first_name", firstName.getText ().toString () );
                        body.put ( "last_name", lastName.getText ().toString () );
                        body.put ( "email", email.getText ().toString () );
                        body.put ( "password", password.getText ().toString () );
                        body.put ( "password_confirmation", confirmedPassword.getText ().toString () );
                        body.put ( "birth_date_day", day );
                        body.put ( "birth_date_month", month );
                        body.put ( "birth_date_year", year );
                        body.put ( "gender", gender );
                        SignUpNetworkLayer networkLayer= new SignUpNetworkLayer ();
                        networkLayer.callSignUpRout ( parentView.getContext (), this,
                                body);
                    }
                }
                break;
            }
        }
    }

    @Nullable
    private boolean isNameValid(@Nullable String name){
        return name.length () >= 1 ? true : false;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        System.out.println (i);
        if( i % 3 == 1 ){
            gender = "male";
        }else if( i % 3 == 2 ){
            gender = "female";
        }else if ( i % 3 == 0 ){
            gender = null;
        }
    }

    public boolean isGenderChecked(){
        return !gender.equals ( "null" ) ? true : false;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        if ( i < 1900 ){
            SnackController.getInstance ().init ( parentView.getContext ()
            , "Year parameter is not valid")
                    .showSnackBar ();
        } else{
            year = i;
            month = i1 + 1;
            day = i2;
            Calendar calendar = Calendar.getInstance ();
            calendar.set ( Calendar.YEAR, year );
            calendar.set ( Calendar.MONTH, month );
            calendar.set ( Calendar.DAY_OF_MONTH, day );
            birthDate.setText ( calendar.getTime ().toString () );
        }
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText ( parentView.getContext (), parentView.getContext ().getString ( R.string.confirmation_email ), Toast.LENGTH_SHORT ).show ();
        parentView.getContext ().startActivity ( new Intent ( parentView.getContext (), SearchActivity.class ) );
    }

    @Override
    public void onSignUpFailure(String message, Constants.EXCEPTION_TYPE exception_type) {
        if( exception_type == Constants.EXCEPTION_TYPE.NO_EXCEPTION ){
            SnackController.getInstance ().
                    init ( parentView.getContext (), message, Snackbar.LENGTH_LONG )
                    .showSnackBar ();
        }
    }

    @Override
    public void onSignUpFailure(Constants.EXCEPTION_TYPE exception_type) {
        if( exception_type == Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION ){
            SnackController.getInstance ().
                    init ( parentView.getContext (),  R.string.timeout_exception, Snackbar.LENGTH_LONG)
                    .setAction ( R.string.try_again, SignUpViewHolder.this )
                    .showSnackBar ();
        }else if ( exception_type == Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION || exception_type == Constants.EXCEPTION_TYPE.BAD_EXCEPTION ){
            SnackController.getInstance ().
                    init ( parentView.getContext (),   R.string.something_bad_happened, Snackbar.LENGTH_LONG)
                    .showSnackBar ();
        }
    }

}
