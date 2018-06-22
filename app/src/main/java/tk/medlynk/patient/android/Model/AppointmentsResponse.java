package tk.medlynk.patient.android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Shahab on 6/22/2018.
 */

public class AppointmentsResponse implements Serializable
{

    @SerializedName("data")
    @Expose
    private List<SingleAppointment> data = null;

    private final static long serialVersionUID = 8859009251529085572L;

    public List<SingleAppointment> getData() {
        return data;
    }

    public void setData(List<SingleAppointment> data) {
        this.data = data;
    }

}
