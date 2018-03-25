package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_2;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;

/**
 * Created by Shahab on 2/22/2018.
 */

public interface OnSecondFollowUpAnswerListener {
    void onSecondAnswerSuccess(FollowUpSymptomResponse response);
    void onSecondAnswerFailure();
}
