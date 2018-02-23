package tk.medlynk.patient.android.Activity.SelectDoctor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import tk.medlynk.patient.android.Model.SearchDoctorResponse;
import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 1/20/2018.
 */

public class SelectDoctorViewHolder extends RecyclerView.ViewHolder
implements View.OnClickListener{

    Button selectDoctor;
    TextView doctorName, doctorBio, doctorPhone, doctorId, wrongDrID;
    ProgressBar progressBar;
    SelectDoctorClickListener clickListener;
    private String doctorID;

    public SelectDoctorViewHolder(View itemView) {
        super ( itemView );
        clickListener = new SelectDoctorClickListener (itemView.getContext ());
        selectDoctor = itemView.findViewById ( R.id.btnSelectDoctor );
        selectDoctor.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                clickListener.onSelectDoctorClicked (doctorID);

            }
        } );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        doctorName = itemView.findViewById ( R.id.doctorName );
        doctorBio= itemView.findViewById ( R.id.doctorBio );
        doctorPhone = itemView.findViewById (R.id.doctorPhone);
        doctorId = itemView.findViewById ( R.id.doctorId );
        wrongDrID = itemView.findViewById ( R.id.tryAgain );
        wrongDrID.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                clickListener.onWrongDoctorIdClicked();
            }
        } );
    }

    @Override
    public void onClick(View view) {

    }

    public void setData(SearchDoctorResponse searchDoctorResponse) {
        doctorID = searchDoctorResponse.getReceivedMedicalInfo ().getId ();
        try{
            doctorName.setText ( searchDoctorResponse.getReceivedMedicalInfo ().getName () );
        }catch (Exception e){
            doctorName.setText ( "---" );
        }
        if( searchDoctorResponse.getReceivedMedicalInfo ().getId () != null ){
            doctorId.setText ( "#" + searchDoctorResponse.getReceivedMedicalInfo ().getId () );
        }else {
            doctorId.setText ( doctorID );
        }
        if( searchDoctorResponse.getReceivedMedicalInfo ().getBio () != null ){
            doctorBio.setText ( searchDoctorResponse.getReceivedMedicalInfo ().getBio () );
        }else{
            doctorBio.setText ( "---" );
        }
        if( searchDoctorResponse.getReceivedMedicalInfo ().getPhone () != null ) {
            doctorPhone.setText ( searchDoctorResponse.getReceivedMedicalInfo ().getPhone () );
        }
        else{
                doctorPhone.setText ( "---" );
            }

        }

        public void setProgressBarVisibilityStatus(int status){
            this.progressBar.setVisibility ( status );
        }

}
