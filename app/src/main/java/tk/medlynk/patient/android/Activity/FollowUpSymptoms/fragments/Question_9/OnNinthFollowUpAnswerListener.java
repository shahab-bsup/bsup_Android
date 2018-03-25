package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnNinthFollowUpAnswerListener {
    void onNinthAnswerSuccess(FollowUpSymptomResponse response);
    void onNinthAnswerFailure();
}
