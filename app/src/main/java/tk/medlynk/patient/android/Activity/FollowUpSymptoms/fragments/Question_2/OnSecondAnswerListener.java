package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_2;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/22/2018.
 */

public interface OnSecondAnswerListener {
    void onSecondAnswerSuccess(NewSymptomAnswerResponse response);
    void onSecondAnswerFailure();
}
