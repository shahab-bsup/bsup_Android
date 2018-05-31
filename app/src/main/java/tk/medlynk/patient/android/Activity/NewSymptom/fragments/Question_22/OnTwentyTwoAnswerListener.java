package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_22;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 3/2/2018.
 */

public interface OnTwentyTwoAnswerListener {
    void onTwentyTwoAnswerSuccess(NewSymptomAnswerResponse response);
    void onTwentyTwoAnswerFailure();
    void onUnauthorized();
}
