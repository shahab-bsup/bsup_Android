package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_19;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/24/2018.
 */

public interface OnNineteenAnswerListener {
    void onNineteenAnswerSuccess(NewSymptomAnswerResponse response);
    void onNineteenAnswerFailure();
    void onUnauthorized();
}
