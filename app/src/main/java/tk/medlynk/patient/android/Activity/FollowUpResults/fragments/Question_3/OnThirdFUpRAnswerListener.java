package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.Question_3;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;

/**
 * Created by faranegar on 3/26/18.
 */

public interface OnThirdFUpRAnswerListener {
    void onFirstAnswerSuccess(FollowUpSymptomResponse response);
    void onFirstAnswerFailure();
}
