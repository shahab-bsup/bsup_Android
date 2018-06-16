package tk.medlynk.patient.android.Activity.Refill;
import tk.medlynk.patient.android.Model.SymptomResponse;

/**
 * Created by admin on 6/15/2018.
 */

public interface RefillResultAnswer {
    void onAnswerSuccess(SymptomResponse response);
    void onAnswerFailure();
}
