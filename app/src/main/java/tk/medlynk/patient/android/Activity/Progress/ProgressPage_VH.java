package tk.medlynk.patient.android.Activity.Progress;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 6/10/2018.
 */

public class ProgressPage_VH extends RecyclerView.ViewHolder {

    private final ImageView backButton;
    private final ImageView three_dotted;
    private final TextView toolbar_title;
    private final TextView startedApointments;
    private OnProgressPageListener onProgressPageListener;

    public ProgressPage_VH(View itemView) {
        super ( itemView );
        backButton = itemView.findViewById ( R.id.imgBackButton );
        backButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                onProgressPageListener.onBackButtonClicked ();
            }
        } );
        three_dotted = itemView.findViewById ( R.id.imageThreeDotted );
        three_dotted.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                onProgressPageListener.onThreeDottedClicked ();
            }
        } );
        toolbar_title = itemView.findViewById ( R.id.toolbar_title );
        startedApointments = itemView.findViewById ( R.id.txtStartedAppointments );
    }

    public void setStartedAppointmentsText(String appointments){
        this.startedApointments.setText ( appointments );
    }

    public void setOnProgressPageListener(OnProgressPageListener onProgressPageListener) {
        this.onProgressPageListener = onProgressPageListener;
    }

    public interface OnProgressPageListener{
        void onBackButtonClicked();
        void onThreeDottedClicked();
    }

}
