package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_12;

import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;

/**
 * Created by Shahab on 2/23/2018.
 */

public interface OnTwelveAnswerListener {
    void onTwelveAnswerSuccess(NewSymptomAnswerResponse response);
    void onTwelveAnswerFailure();
    void onUnauthorized();
}
