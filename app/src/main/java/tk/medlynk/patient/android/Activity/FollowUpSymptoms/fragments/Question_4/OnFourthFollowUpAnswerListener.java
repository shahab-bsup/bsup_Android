package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_4;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnFourthFollowUpAnswerListener {
    void onFourthAnswerSuccess(FollowUpSymptomResponse response);
    void onFourthAnswerFailure();
}
