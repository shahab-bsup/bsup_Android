package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnSeventhFollowUpAnswerListener {
    void onSeventhAnswerSuccess(FollowUpSymptomResponse response);
    void onSeventhAnswerFailure();
}
