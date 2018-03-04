package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnNinthAnswerListener {
    void onNinthAnswerSuccess(NewSymptomAnswerResponse response);
    void onNinthAnswerFailure();
}
