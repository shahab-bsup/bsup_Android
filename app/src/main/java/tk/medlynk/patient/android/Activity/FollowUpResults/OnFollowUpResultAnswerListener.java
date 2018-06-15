package tk.medlynk.patient.android.Activity.FollowUpResults;

import tk.medlynk.patient.android.Model.SymptomResponse;

/**
 * Created by admin on 6/13/2018.
 */

public interface OnFollowUpResultAnswerListener {
    void onAnswerSuccess(SymptomResponse response);
    void onAnswerFailure();

}
