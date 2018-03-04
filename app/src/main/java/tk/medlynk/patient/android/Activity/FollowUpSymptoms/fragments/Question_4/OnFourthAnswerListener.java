package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_4;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnFourthAnswerListener {
    void onFourthAnswerSuccess(NewSymptomAnswerResponse response);
    void onFourthAnswerFailure();
}
