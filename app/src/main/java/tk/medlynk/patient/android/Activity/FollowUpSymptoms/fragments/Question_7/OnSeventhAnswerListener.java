package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnSeventhAnswerListener {
    void onSeventhAnswerSuccess(NewSymptomAnswerResponse response);
    void onSeventhAnswerFailure();
}
