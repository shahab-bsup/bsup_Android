package tk.medlynk.patient.android.Activity.Appointments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Model.SingleAppointment;

/**
 * Created by Shahab on 6/22/2018.
 */

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentVH> {

    private Context context;
    private List<SingleAppointment> appointments = new ArrayList<> ();

    public AppointmentAdapter(Context context) {
        this.context = context;
    }

    public void setDataSet(List<SingleAppointment> appointments) {
        this.appointments = appointments;
        notifyDataSetChanged ();
    }

    @NonNull
    @Override
    public AppointmentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from ( context )
                .inflate ( R.layout.appointment_row,
                        parent, false );

        return new AppointmentVH ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentVH holder, int position) {
        SingleAppointment singleAppointment = appointments.get ( position );
        if (singleAppointment.getProviderName () != null) {
            holder.doctorName.setText ( singleAppointment.getProviderName () );
        }
        holder.numberOfAppointment.setText ( String.valueOf ( position ) );
        if (singleAppointment.getIsRead ()) {
            holder.read_by_doctor.setVisibility ( View.VISIBLE );
        } else {
            holder.read_by_doctor.setVisibility ( View.GONE );
        }
        if (singleAppointment.getCreatedAt () != null) {
            holder.startTimeOfAppointment.setText ( "Started on " + singleAppointment.getCreatedAt () );
        }
        holder.numberOfQuestionSetsStarted
                .setText ( String.valueOf ( singleAppointment.getQuestionSetsCount () ) );
        holder.deletionDeclare.setText ( "This question set will be deleted in " +
        singleAppointment.getDeleteIn () + " days!");
    }

    @Override
    public int getItemCount() {
        return appointments.size ();
    }

    public class AppointmentVH extends RecyclerView.ViewHolder {

        private TextView numberOfAppointment;
        private TextView read_by_doctor;
        private TextView doctorName;
        private TextView startTimeOfAppointment;
        private TextView numberOfQuestionSetsStarted;
        private TextView deletionDeclare;
        private ImageView print;
        private ImageView edit;
        private ImageView delete;

        public AppointmentVH(View itemView) {
            super ( itemView );
            numberOfAppointment = itemView.findViewById ( R.id.numOfAppointment );
            read_by_doctor = itemView.findViewById ( R.id.isReadByDoctor );
            doctorName = itemView.findViewById ( R.id.txtNameOfDoctor );
            startTimeOfAppointment = itemView.findViewById ( R.id.txtAppointmentStartTime );
            numberOfQuestionSetsStarted = itemView.findViewById ( R.id.txtNumOfQuestionSet );
            deletionDeclare = itemView.findViewById ( R.id.txtDeleteDeclare );
            print = itemView.findViewById ( R.id.imgPrintAppointment );
            print.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Toast.makeText ( context, "In Progress!", Toast.LENGTH_SHORT ).show ();
                }
            } );
            edit = itemView.findViewById ( R.id.imgEditAppointment );
            edit.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Toast.makeText ( context, "A technical problem occurred!", Toast.LENGTH_SHORT ).show ();
                }
            } );
            delete = itemView.findViewById ( R.id.imgEditAppointment );
            delete.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Toast.makeText ( context, "In Progress", Toast.LENGTH_SHORT ).show ();
                }
            } );
        }
    }

}
