package tk.medlynk.patient.android.Activity.FollowUpSymptoms;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;

/**
 * Created by admin on 6/13/2018.
 */

public interface OnFollowUpSymptomAnswerListener {
    void onAnswerSuccess(FollowUpSymptomResponse response);
    void onAnswerFailure();

}
