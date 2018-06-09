package tk.medlynk.patient.android.Activity.NewSymptom;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by admin on 6/9/2018.
 */

public interface OnNewSymptomAnswerListener {
    void onAnswerSuccess(NewSymptomAnswerResponse response);
    void onAnswerFailure();
    void onUnauthorized();
}
