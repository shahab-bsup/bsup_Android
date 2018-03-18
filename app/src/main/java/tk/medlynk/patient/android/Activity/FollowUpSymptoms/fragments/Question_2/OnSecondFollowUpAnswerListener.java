package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_2;

import tk.medlynk.patient.android.Model.FollowUpResultResponse;

/**
 * Created by Shahab on 2/22/2018.
 */

public interface OnSecondFollowUpAnswerListener {
    void onSecondAnswerSuccess(FollowUpResultResponse response);
    void onSecondAnswerFailure();
}
