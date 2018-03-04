package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnTenthAnswerListener {
    void onTenthAnswerSuccess(NewSymptomAnswerResponse response);
    void onTenthAnswerFailure();
}
