package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_1;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;

/**
 * Created by Shahab on 2/22/2018...
 */

public interface OnFirstFollowUpAnswerListener {
    void onFirstAnswerSuccess(FollowUpSymptomResponse response);
    void onFirstAnswerFailure();
}
