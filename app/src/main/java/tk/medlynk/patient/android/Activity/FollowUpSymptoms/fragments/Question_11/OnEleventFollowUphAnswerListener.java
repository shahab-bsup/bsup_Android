package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_11;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnEleventFollowUphAnswerListener {
    void onEleventhAnswerSuccess(FollowUpSymptomResponse response);
    void onEleventhAnswerFailure();
}
