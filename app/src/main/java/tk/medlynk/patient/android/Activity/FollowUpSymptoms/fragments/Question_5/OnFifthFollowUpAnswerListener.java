package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_5;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnFifthFollowUpAnswerListener {
    void onFifthAnswerSuccess(FollowUpSymptomResponse response);
    void onFifthAnswerFailure();
}
