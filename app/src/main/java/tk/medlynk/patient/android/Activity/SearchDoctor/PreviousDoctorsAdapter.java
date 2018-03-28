package tk.medlynk.patient.android.Activity.SearchDoctor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Model.ReceivedMedicalInfo;

/**
 * Created by faranegar on 3/28/18.
 */

public class PreviousDoctorsAdapter extends RecyclerView.Adapter<PreviousDoctorsAdapter.PDViewHolder> {

    private final Context context;
    private final List<ReceivedMedicalInfo> previousDoctors;
    private OnPDoctorClickListener onPDoctorClickListener;

    public PreviousDoctorsAdapter(Context context) {
        this.context = context;
        previousDoctors = new ArrayList<>();
    }

    public void setOnPDoctorClickListener(OnPDoctorClickListener onPDoctorClickListener) {
        this.onPDoctorClickListener = onPDoctorClickListener;
    }

    @Override
    public PDViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.previous_doctors, parent, false);
        return new PDViewHolder(view);
    }

    public void setPreviousDoctorsList(List<ReceivedMedicalInfo> doctors){
        this.previousDoctors.clear();
        this.previousDoctors.addAll(doctors);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(PDViewHolder holder, int position) {
        if( previousDoctors.get(position).getId() != null ){
            if( previousDoctors.get(position).getName() != null ){
                holder.doctor_info.setText(previousDoctors.get(position).getId() + " - " +
                previousDoctors.get(position).getName());
            }else{
                holder.doctor_info.setText(previousDoctors.get(position).getId());
            }
        }else{
            if( previousDoctors.get(position).getName() != null ){
                holder.doctor_info.setText(previousDoctors.get(position).getName());
            }else{
                //this fu..ing situation!
                holder.doctor_info.setText("---");
            }
        }
    }

    public void deletePreviousDoctor(int adapterPosition){
        this.previousDoctors.remove(adapterPosition);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return previousDoctors.size();
    }

    //PDViewHolder stands for previous doctors ViewHolder
    public class PDViewHolder extends RecyclerView.ViewHolder{

        private ImageView delete;
        private TextView doctor_info;
        private ProgressBar progressBar;

        public PDViewHolder(View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.delete_previous_doctor);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delete.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    onPDoctorClickListener.onDelete(getAdapterPosition(), previousDoctors.get(getAdapterPosition()).getId());
                }
            });
            doctor_info = itemView.findViewById(R.id.doctorId_doctorName);
            doctor_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPDoctorClickListener.onClicked(previousDoctors.get(getAdapterPosition()).getId());
                }
            });
            progressBar = itemView.findViewById(R.id.delete_progress_bar);
        }
    }

    //OnPDoctorClickListener stands for On Previous Doctor Click Listener
    public interface OnPDoctorClickListener {
        void onDelete(int adapterPosition, String doctorID);
        void onClicked(String doctorID  );
    }

}
