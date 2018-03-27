package tk.medlynk.patient.android.Model;

/**
 * Created by faranegar on 3/26/18.
 */

public interface MotherCallback {
    void onAnswerSuccess(SymptomResponse response);
    void onAnswerFailure();
}
