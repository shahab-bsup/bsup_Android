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

import com.neweraandroid.demo.Activity.SearchDoctor.SearchActivity;
import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.CustomViews.SnackController;
import com.neweraandroid.demo.Essentials.Utils;
import com.neweraandroid.demo.Networking.MedlynkRequests;
import com.neweraandroid.demo.R;

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
                DatePickerDialog datePickerDialog = new DatePickerDialog ( parentView.getContext (),
                        SignUpViewHolder.this, 1000, 12, 23);
                datePickerDialog.show ();
            }
        } );
        radioGroup = itemView.findViewById ( R.id.radio_group );
        radioGroup.setOnCheckedChangeListener ( this );
        submit.setOnClickListener ( this );
    }

    @Override
    public void onClick(View view) {
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
            } else {
                HashMap<String, String> body = new HashMap<> ();
                body.put ( "as", "patient" );
                body.put ( "first_name", firstName.getText ().toString () );
                body.put ( "last_name", lastName.getText ().toString () );
                body.put ( "email", email.getText ().toString () );
                body.put ( "password", password.getText ().toString () );
                body.put ( "password_confirmation", confirmedPassword.getText ().toString () );
                body.put ( "birth_date_year", String.valueOf ( year ) );
                body.put ( "birth_date_month",  String.valueOf ( month ));
                body.put ( "birth_date_day ",  String.valueOf ( day ));
                body.put ( "gender", gender );
                SignUpNetworkLayer networkLayer= new SignUpNetworkLayer ();
                networkLayer.callSignUpRout ( parentView.getContext (), this,
                        body);
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
        if( i == 1 ){
            gender = "male";
        }else if( i == 2 ){
            gender = "female";
        }else if ( i == 3 ){
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
            month = i1;
            day = i2;
        }
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText ( parentView.getContext (), parentView.getContext ().getString ( R.string.confirmation_email ), Toast.LENGTH_SHORT ).show ();
        parentView.getContext ().startActivity ( new Intent ( parentView.getContext (), SearchActivity.class ) );
    }

    @Override
    public void onSignUpFailure(String message, Constants.EXCEPTION_TYPE exception_type) {

    }

    @Override
    public void onSignUpFailure(Constants.EXCEPTION_TYPE exception_type) {

    }

}
