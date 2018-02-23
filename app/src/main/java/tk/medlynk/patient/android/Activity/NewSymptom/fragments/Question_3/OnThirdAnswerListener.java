package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_3;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnThirdAnswerListener {
    void onThirdAnswerSuccess(NewSymptomAnswerResponse response);
    void onThirdAnswerFailure();
}
