package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_14;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/24/2018.
 */

public interface OnFourteenAnswerListener {
    void onThirteenAnswerSuccess(NewSymptomAnswerResponse response);
    void onThirteenAnswerFailure();
    void onUnauthorized();
}
