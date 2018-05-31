package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_15;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/24/2018.
 */

public interface OnFifteenAnswerListener {
    void onFifteenAnswerResponse(NewSymptomAnswerResponse response);
    void onFifteenAnswerFailure();
    void onUnauthorized();
}
