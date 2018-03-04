package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_1;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/22/2018.
 */

public interface OnFirstAnswerListener {
    void onFirstAnswerSuccess(NewSymptomAnswerResponse response);
    void onFirstAnswerFailure();
}
