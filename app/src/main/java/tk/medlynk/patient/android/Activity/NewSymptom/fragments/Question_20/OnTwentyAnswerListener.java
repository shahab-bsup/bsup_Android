package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_20;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 3/2/2018.
 */

public interface OnTwentyAnswerListener {
    void onTwentyAnswerSuccess(NewSymptomAnswerResponse response);
    void onTwentyAnswerFailure();
}
