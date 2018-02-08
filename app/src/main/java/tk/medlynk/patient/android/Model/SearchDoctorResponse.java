package tk.medlynk.patient.android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Shahab on 1/19/2018.
 */

public class SearchDoctorResponse implements Serializable {

    @SerializedName("data")
    @Expose
    private ReceivedMedicalInfo receivedMedicalInfo;
    private final static long serialVersionUID = -2420979164143381626L;

    public ReceivedMedicalInfo getReceivedMedicalInfo() {
        return receivedMedicalInfo;
    }

    public void setReceivedMedicalInfo(ReceivedMedicalInfo receivedMedicalInfo) {
        this.receivedMedicalInfo = receivedMedicalInfo;
    }
}
