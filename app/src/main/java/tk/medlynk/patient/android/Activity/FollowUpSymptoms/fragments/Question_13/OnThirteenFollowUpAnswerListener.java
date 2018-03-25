package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_13;

import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/24/2018.
 */

public interface OnThirteenFollowUpAnswerListener {
    void onThirteenAnswerSuccess(FollowUpSymptomResponse response);
    void onThirteenAnswerFailure();
}
