package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_5;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnFifthAnswerListener {
    void onFifthAnswerSuccess(NewSymptomAnswerResponse response);
    void onFifthAnswerFailure();
    void onUnauthorized();
}
