package com.neweraandroid.demo.Activity.SearchDoctor;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.neweraandroid.demo.Activity.NewSymptom.NewSymptomActivity;
import com.neweraandroid.demo.Activity.NoDoctorIdPage.NoDoctorIdActivity;
import com.neweraandroid.demo.Activity.SelectDoctor.SelectDoctorActivity;
import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.CustomViews.SnackController;
import com.neweraandroid.demo.Essentials.SharedPreferenceManager;
import com.neweraandroid.demo.Model.CurrentUserInfo;
import com.neweraandroid.demo.Model.SearchDoctorResponse;
import com.neweraandroid.demo.Networking.MedlynkRequests;
import com.neweraandroid.demo.R;

import java.io.Serializable;

/**
 * Created by Shahab on 1/19/2018.
 */

public class SearchActivityClickListener implements OnSearchDoctorListener {

    private Context context;
    private String doctorID;

    public SearchActivityClickListener(Context context) {
        this.context = context;
    }

    public void onSearchClick(String doctor_id){
        MedlynkRequests.searchDoctor ( context, String.valueOf ( 17285001 ), this );
//        if(TextUtils.isEmpty ( doctor_id )){
//            SnackController.getInstance ().init ( context,
//                    "Specify Doctor ID please!", Snackbar.LENGTH_LONG)
//                    .showSnackBar ();
//        } else{
//            setDoctorID ( doctor_id );
//            MedlynkRequests.searchDoctor ( context, doctor_id, this );
//        }
    }

    @Override
    public void onSearchDoctorSuccess(SearchDoctorResponse response) {
        if( response.getReceivedMedicalInfo () != null ){
            context.startActivity ( new Intent ( context, SelectDoctorActivity.class )
            .putExtra ( Constants.SelectedDoctor, (Serializable) response ));
        }
    }

    @Override
    public void onSearchDoctorFailure(String errorMessage, Constants.EXCEPTION_TYPE exception_type) {
        if( exception_type == Constants.EXCEPTION_TYPE.NO_EXCEPTION ){
            SnackController.getInstance ().
                    init ( context, errorMessage, Snackbar.LENGTH_LONG )
                    .showSnackBar ();
        }
    }

    @Override
    public void onSearchDoctorFailure(Constants.EXCEPTION_TYPE exception_type) {
        if( exception_type == Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION ){
            SnackController.getInstance ().
                    init ( context,  R.string.timeout_exception, Snackbar.LENGTH_INDEFINITE)
                    .setAction ( R.string.try_again, new View.OnClickListener () {
                        @Override
                        public void onClick(View view) {
                            onSearchClick ( getDoctorID () );
                        }
                    } )
                    .showSnackBar ();
        }else if ( exception_type == Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION || exception_type == Constants.EXCEPTION_TYPE.BAD_EXCEPTION ){
            SnackController.getInstance ().
                    init ( context,   R.string.something_bad_happened, Snackbar.LENGTH_LONG)
                    .showSnackBar ();
        }
    }

    public void onNoDoctorIdClick() {
        if( CurrentUserInfo.getInstance ().getPreferences ().getSkipNoDoctorIdPage ().equals ( "false" ) )
            context.startActivity ( new Intent ( context, NoDoctorIdActivity.class ) );
        else
            context.startActivity ( new Intent ( context, NewSymptomActivity.class ) );
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }
}
