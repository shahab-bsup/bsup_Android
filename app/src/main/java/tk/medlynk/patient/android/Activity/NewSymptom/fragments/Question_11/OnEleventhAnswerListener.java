package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_11;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnEleventhAnswerListener {
    void onEleventhAnswerSuccess(NewSymptomAnswerResponse response);
    void onEleventhAnswerFailure();
    void onUnauthorized();
}
