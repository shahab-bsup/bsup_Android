package tk.medlynk.patient.android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Shahab on 2/22/2018...
 */

public class AppointmentResponse implements Serializable {
    @SerializedName("data")
    @Expose
    private Appointment data;
    private final static long serialVersionUID = -2420979164143381626L;

    public Appointment getData() {
        return data;
    }

    public void setData(Appointment data) {
        this.data = data;
    }
}
