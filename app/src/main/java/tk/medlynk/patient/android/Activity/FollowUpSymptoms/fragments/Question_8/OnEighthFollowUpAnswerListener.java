package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnEighthFollowUpAnswerListener {
    void onEighthAnswerSuccess(FollowUpSymptomResponse response);
    void onEightAnswerFailure();
}
