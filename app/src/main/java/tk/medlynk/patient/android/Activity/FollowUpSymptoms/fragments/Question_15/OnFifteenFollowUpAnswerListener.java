package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_15;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/24/2018.
 */

public interface OnFifteenFollowUpAnswerListener {
    void onFifteenAnswerResponse(FollowUpSymptomResponse response);
    void onFifteenAnswerFailure();
}
