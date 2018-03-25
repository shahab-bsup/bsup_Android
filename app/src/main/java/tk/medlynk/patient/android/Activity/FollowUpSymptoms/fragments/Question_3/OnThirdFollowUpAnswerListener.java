package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_3;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnThirdFollowUpAnswerListener {
    void onThirdAnswerSuccess(FollowUpSymptomResponse response);
    void onThirdAnswerFailure();
}
