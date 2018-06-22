package tk.medlynk.patient.android.Activity.Progress;

import tk.medlynk.patient.android.Model.ProgressResponse;

/**
 * Created by admin on 6/22/2018.
 */

public interface OnProgressResponseListener {
    void onAnswerSuccess(ProgressResponse response);
    void onAnswerFailure();
}
