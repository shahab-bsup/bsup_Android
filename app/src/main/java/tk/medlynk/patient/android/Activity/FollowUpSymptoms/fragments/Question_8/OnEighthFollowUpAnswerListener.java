package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8;

import tk.medlynk.patient.android.Model.FollowUpResultResponse;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnEighthFollowUpAnswerListener {
    void onEighthAnswerSuccess(FollowUpResultResponse response);
    void onEightAnswerFailure();
}
