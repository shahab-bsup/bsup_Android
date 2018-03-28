package tk.medlynk.patient.android.Activity.SearchDoctor;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

import tk.medlynk.patient.android.Activity.NewSymptom.NewSymptomActivity;
import tk.medlynk.patient.android.Activity.NoDoctorIdPage.NoDoctorIdActivity;
import tk.medlynk.patient.android.Activity.SelectDoctor.SelectDoctorActivity;
import tk.medlynk.patient.android.Activity.StartQuestionSet.StartAppointmentActivity;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.CustomViews.SnackController;
import tk.medlynk.patient.android.Essentials.Utils;
import tk.medlynk.patient.android.Model.CurrentUserInfo;
import tk.medlynk.patient.android.Model.SearchDoctorResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import com.neweraandroid.demo.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Shahab on 1/19/2018.
 */

public abstract class SearchActivityClickListener implements OnSearchDoctorListener {

    private Context context;
    private String doctorID;

    public SearchActivityClickListener(Context context) {
        this.context = context;
    }

    public void onSearchClick(String doctor_id){
        Utils.hideSoftKeyBoard ( new View ( context ) );
//        MedlynkRequests.searchDoctor ( context, String.valueOf ( 17285001 ), this );

    }


    @Override
    public void onSearchDoctorSuccess(SearchDoctorResponse response) {

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
            context.startActivity ( new Intent ( context, StartAppointmentActivity.class ) );
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }
}
