package com.neweraandroid.demo.Activity.SelectDoctor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.neweraandroid.demo.Model.SearchDoctorResponse;
import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 1/20/2018.
 */

public class SelectDoctorViewHolder extends RecyclerView.ViewHolder
implements View.OnClickListener{

    Button selectDoctor;
    TextView doctorName, doctorBio, doctorPhone, doctorId, wrongDrID;
    SelectDoctorClickListener clickListener;

    public SelectDoctorViewHolder(View itemView) {
        super ( itemView );
        clickListener = new SelectDoctorClickListener (itemView.getContext ());
        selectDoctor = itemView.findViewById ( R.id.btnSelectDoctor );
        selectDoctor.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                clickListener.onSelectDoctorClicked ();
            }
        } );
        doctorName = itemView.findViewById ( R.id.doctorName );
        doctorBio= itemView.findViewById ( R.id.doctorId );
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

    }
}
