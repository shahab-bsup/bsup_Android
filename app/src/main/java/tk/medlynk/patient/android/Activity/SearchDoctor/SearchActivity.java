package tk.medlynk.patient.android.Activity.SearchDoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import tk.medlynk.patient.android.Activity.Login.OnGetCurrentUserInfoListener;
import tk.medlynk.patient.android.Activity.NoDoctorIdPage.NoDoctorIdActivity;
import tk.medlynk.patient.android.Activity.SelectDoctor.SelectDoctorActivity;
import tk.medlynk.patient.android.Activity.StartQuestionSet.StartAppointmentActivity;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.CustomViews.SnackController;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.CurrentUserInfo;
import tk.medlynk.patient.android.Model.CurrentUserResponse;
import tk.medlynk.patient.android.Model.PreviuosDoctorsResponse;
import tk.medlynk.patient.android.Model.SearchDoctorResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

import com.neweraandroid.demo.R;

import java.io.Serializable;

public class SearchActivity extends AppCompatActivity implements
        OnGetCurrentUserInfoListener,
        View.OnClickListener,
        SearchActivityViewHolder.OnSearchActivityVHListener,
        OnSearchDoctorListener, PreviousDoctorsAdapter.OnPDoctorClickListener {

    View parent_view;
    SearchActivityViewHolder viewHolder;
    private PreviousDoctorsAdapter adapter;
    private int going_to_be_deleted_dr_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor);
        parent_view = findViewById ( R.id.parent_search_activity );
        viewHolder = new SearchActivityViewHolder ( parent_view );
        viewHolder.setOnSearchActivityVHListener ( this );
        MedlynkRequests.getCurrentUserInfo ( this, this );
        MedlynkRequests.getPreviousDoctors(this, this);
        adapter = new PreviousDoctorsAdapter(this);
        adapter.setOnPDoctorClickListener(this);
        viewHolder.setPreviousDoctorAdapter(adapter);
    }

    @Override
    public void onGetCurrentUserInfoSuccess(CurrentUserResponse currentUserResponse) {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager ( SearchActivity.this );
        currentUserResponse.setCurrentUserInfo ( currentUserResponse.getCurrentUserInfo () );
        sharedPreferenceManager.setCurrentUser ( currentUserResponse.getCurrentUserInfo () );
    }

    @Override
    public void onGetCurrentUserInfoFailure() {
        //TODO add some code if needed!!!
        System.out.println ( "SearchActivity.onGetCurrentUserInfoFailure" );
    }

    @Override
    public void onGetCurrentUserInfoFailure(Constants.EXCEPTION_TYPE exception_type) {
        if( exception_type == Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION ){
            SnackController.getInstance ().
                    init ( SearchActivity.this, R.string.somethig_went_wrong, Snackbar.LENGTH_LONG )
                    .setAction ( R.string.try_again, SearchActivity.this )
                    .showSnackBar ();
        } else if( exception_type == Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION ){
            SnackController.getInstance ().
                    init ( SearchActivity.this, R.string.timeout_exception, Snackbar.LENGTH_LONG )
                    .setAction ( R.string.try_again, SearchActivity.this )
                    .showSnackBar ();
        }
    }

    @Override
    public void onSearchClicked(String doctorID) {
        System.out.println ( "SearchActivity.onSearchClicked" );
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        MedlynkRequests.searchDoctor ( SearchActivity.this,
                doctorID, SearchActivity.this);
    }

    @Override
    public void onNoDoctorIDClicked() {
        System.out.println ( "SearchActivity.onNoDoctorIDClicked" );
        if( CurrentUserInfo.getInstance () != null &&
                CurrentUserInfo.getInstance ().getPreferences () != null &&
                CurrentUserInfo.getInstance ().getPreferences ().getSkipNoDoctorIdPage ()
                        .equals ( "false" ) )
            startActivity ( new Intent ( SearchActivity.this,
                    NoDoctorIdActivity.class ) );
        else if ( CurrentUserInfo.getInstance () != null &&
                CurrentUserInfo.getInstance ().getPreferences () != null &&
                CurrentUserInfo.getInstance ().getPreferences ().getSkipNoDoctorIdPage ()
                        .equals ( "true" ) ){
            startActivity ( new Intent ( SearchActivity.this,
                    StartAppointmentActivity.class ) );
        }
        else{
            startActivity ( new Intent ( SearchActivity.this,
                    NoDoctorIdActivity.class ) );
        }
    }

    @Override
    public void onDeletePreviousDoctor(Boolean aBoolean) {
        System.out.println("SearchActivity.onDeletePreviousDoctor");
        if( aBoolean ){
            adapter.deletePreviousDoctor(going_to_be_deleted_dr_position);
        }
    }

    @Override
    public void onGetPreviousDoctorSuccess(PreviuosDoctorsResponse response) {
        System.out.println("SearchActivity.onGetPreviousDoctorSuccess");
        if( response.getData().size() == 0 ){
            viewHolder.setPreviousDoctorsProgressBarVisibiltyStatus(View.GONE);
            viewHolder.setNoPreviousDcotorTextVisibilityStatus(View.VISIBLE);
        }else{
               adapter.setPreviousDoctorsList(response.getData());
               viewHolder.setPreviousDoctorListVisibilityStatus(View.VISIBLE);
               viewHolder.setPreviousDoctorsProgressBarVisibiltyStatus(View.GONE);
        }
    }

    @Override
    public void onSearchDoctorSuccess(SearchDoctorResponse response) {
        viewHolder.setProgressBarVisibilityStatus (View.GONE);
        if( response.getReceivedMedicalInfo () != null ){
            startActivity ( new Intent ( SearchActivity.this,
                    SelectDoctorActivity.class )
                    .putExtra ( Constants.SelectedDoctor, (Serializable) response ));
        }
    }

    @Override
    public void onSearchDoctorFailure(String errorMessage, Constants.EXCEPTION_TYPE exception_type) {
        viewHolder.setProgressBarVisibilityStatus (View.GONE);
        if( exception_type == Constants.EXCEPTION_TYPE.NO_EXCEPTION ){
            SnackController.getInstance ().
                    init ( SearchActivity.this,
                            errorMessage, Snackbar.LENGTH_LONG )
                    .showSnackBar ();
        }
    }

    @Override
    public void onSearchDoctorFailure(Constants.EXCEPTION_TYPE exception_type) {
        if( exception_type == Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION ){
            SnackController.getInstance ().
                    init ( SearchActivity.this,  R.string.timeout_exception, Snackbar.LENGTH_INDEFINITE)
                    .setAction ( R.string.try_again, new View.OnClickListener () {
                        @Override
                        public void onClick(View view) {
                            onSearchClicked ( viewHolder.getDoctorId () );
                        }
                    }).showSnackBar ();
        }else if ( exception_type == Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION || exception_type == Constants.EXCEPTION_TYPE.BAD_EXCEPTION ){
            SnackController.getInstance ().
                    init ( SearchActivity.this,   R.string.something_bad_happened, Snackbar.LENGTH_LONG)
                    .showSnackBar ();
        }
    }

    @Override
    public void onDelete(int adapterPosition, String doctorID) {
        System.out.println("SearchActivity.onDelete");
        going_to_be_deleted_dr_position = adapterPosition;
        MedlynkRequests.deleteAPreviousDoctor(this, this, doctorID);
    }

    @Override
    public void onClicked(String doctorID) {
        System.out.println("SearchActivity.onClicked");
        viewHolder.setProgressBarVisibilityStatus ( View.VISIBLE );
        MedlynkRequests.searchDoctor ( SearchActivity.this,
                doctorID, SearchActivity.this);
    }

    @Override
    public void onClick(View view) {

    }
}
