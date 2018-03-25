package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnTenthFollowUpAnswerListener {
    void onTenthAnswerSuccess(FollowUpSymptomResponse response);
    void onTenthAnswerFailure();
}
