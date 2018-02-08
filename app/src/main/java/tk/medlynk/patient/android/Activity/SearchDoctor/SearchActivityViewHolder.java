package tk.medlynk.patient.android.Activity.SearchDoctor;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 1/19/2018...
 */

public class SearchActivityViewHolder extends RecyclerView.ViewHolder {

    private AppCompatEditText doctorId;
    private Button search, no_Doctor_Id;
    private SearchActivityClickListener clickListener;

    public SearchActivityViewHolder(View itemView) {
        super ( itemView );
        clickListener = new SearchActivityClickListener ( itemView.getContext () );
        search = itemView.findViewById ( R.id.btnSearch );
        search.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                clickListener.onSearchClick ( doctorId.getText ().toString () );
            }
        } );
        no_Doctor_Id = itemView.findViewById ( R.id.btnDo_not_know_dr_id );
        no_Doctor_Id.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                clickListener.onNoDoctorIdClick();
            }
        } );
        doctorId = itemView.findViewById ( R.id.edtDoctorID );
    }
}
