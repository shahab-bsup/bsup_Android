package tk.medlynk.patient.android.Activity.SearchDoctor;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.CustomViews.SnackController;
import tk.medlynk.patient.android.Essentials.Utils;

/**
 * Created by Shahab on 1/19/2018...
 */

public class SearchActivityViewHolder extends RecyclerView.ViewHolder {

    private AppCompatEditText doctorId;
    private Button search, no_Doctor_Id;
    private ProgressBar progressBar, previousDoctorsProgressBar;
    private RecyclerView preciuosDoctor;
    private final TextView no_previous_doctor;
    private OnSearchActivityVHListener onSearchActivityVHListener;

    public SearchActivityViewHolder(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        search = itemView.findViewById ( R.id.btnSearch );
        doctorId = itemView.findViewById ( R.id.edtDoctorID );
        search.setOnClickListener ( new OnSearchDoctorCLickListener() );
        no_Doctor_Id = itemView.findViewById ( R.id.btnDo_not_know_dr_id );
        no_Doctor_Id.setOnClickListener ( new OnNoDoctorIdClickListener() );
        preciuosDoctor = itemView.findViewById(R.id.previousDoctorsList);
        previousDoctorsProgressBar = itemView.findViewById(R.id.previuosDoctorsProgressBar);
        no_previous_doctor = itemView.findViewById(R.id.no_previousDoctor);
    }

    public void setOnSearchActivityVHListener(OnSearchActivityVHListener onSearchActivityVHListener) {
        this.onSearchActivityVHListener = onSearchActivityVHListener;
    }

    public final void setNoPreviousDcotorTextVisibilityStatus(int status ){
        this.no_previous_doctor.setVisibility(status);
    }

    public void setPreviousDoctorsProgressBarVisibiltyStatus(int status){
        this.previousDoctorsProgressBar.setVisibility(status);
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }

    public String getDoctorId(){
        return doctorId.getText ().toString ();
    }

    public void setPreviousDoctorAdapter(PreviousDoctorsAdapter adapter) {
        this.preciuosDoctor.setAdapter(adapter);
    }

    public void setPreviousDoctorListVisibilityStatus(int status) {
        this.preciuosDoctor.setVisibility(status);
    }

    private class OnSearchDoctorCLickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Utils.hideSoftKeyBoard ( itemView );
            if(TextUtils.isEmpty ( getDoctorId () )){
                SnackController.getInstance ().init ( itemView.getContext (),
                        "Specify Doctor ID please!", Snackbar.LENGTH_LONG)
                        .showSnackBar ();
            } else{
                onSearchActivityVHListener.onSearchClicked ( getDoctorId () );
            }
        }
    }

    private class OnNoDoctorIdClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            onSearchActivityVHListener.onNoDoctorIDClicked ();
        }
    }

    public interface OnSearchActivityVHListener{
        void onSearchClicked(String doctorID);
        void onNoDoctorIDClicked();
    }

}
