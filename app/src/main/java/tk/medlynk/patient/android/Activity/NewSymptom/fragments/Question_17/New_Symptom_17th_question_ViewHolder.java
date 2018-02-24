package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_17;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 2/24/2018.
 */

public class New_Symptom_17th_question_ViewHolder extends RecyclerView.ViewHolder {

    private ProgressBar progressBar;

    public New_Symptom_17th_question_ViewHolder(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }


}
