package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7;

import tk.medlynk.patient.android.Model.FollowUpResultResponse;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnSeventhFollowUpAnswerListener {
    void onSeventhAnswerSuccess(FollowUpResultResponse response);
    void onSeventhAnswerFailure();
}
