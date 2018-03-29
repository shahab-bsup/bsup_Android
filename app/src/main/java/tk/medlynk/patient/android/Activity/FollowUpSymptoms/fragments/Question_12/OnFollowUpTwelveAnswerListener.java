package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_12;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnFollowUpTwelveAnswerListener {
    void onTwelveAnswerSuccess(FollowUpSymptomResponse response);
    void onTwelveAnswerFailure();
}
