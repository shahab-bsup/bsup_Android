package tk.medlynk.patient.android.Activity.SearchDoctor;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.CustomViews.SnackController;
import tk.medlynk.patient.android.Essentials.Utils;
import tk.medlynk.patient.android.Networking.MedlynkRequests;

/**
 * Created by Shahab on 1/19/2018...
 */

public class SearchActivityViewHolder extends RecyclerView.ViewHolder {

    private AppCompatEditText doctorId;
    private Button search, no_Doctor_Id;
    private SearchActivityClickListener clickListener;
    private ProgressBar progressBar;

    public void setClickListener(SearchActivityClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public SearchActivityViewHolder(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        search = itemView.findViewById ( R.id.btnSearch );
        doctorId = itemView.findViewById ( R.id.edtDoctorID );
        search.setOnClickListener ( new OnSearchDoctorCLickListener() );
//        search.setEnabled ( false );
        no_Doctor_Id = itemView.findViewById ( R.id.btnDo_not_know_dr_id );
        no_Doctor_Id.setOnClickListener ( new OnNoDoctorIdClickListener() );
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }

    public String getDoctorId(){
        return doctorId.getText ().toString ();
    }

    public interface SearchActivityClickListener{
        void onSearchClicked(String doctorID);
        void onNoDoctorIdClicked();
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
                search.setEnabled ( true );
                clickListener.onSearchClicked ( getDoctorId () );
            }
        }
    }

    private class OnNoDoctorIdClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            clickListener.onNoDoctorIdClicked ();
        }
    }
}
