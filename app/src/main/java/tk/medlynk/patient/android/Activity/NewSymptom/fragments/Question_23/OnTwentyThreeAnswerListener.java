package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_23;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 3/2/2018.
 */

public interface OnTwentyThreeAnswerListener {
    void onTwentyThreeAnswerSuccess(NewSymptomAnswerResponse response);
    void onTwentyThreeAnswerFailure();
    void onUnauthorized();
}
