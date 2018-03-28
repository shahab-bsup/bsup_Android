package tk.medlynk.patient.android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by faranegar on 3/28/18.
 */

public class PreviuosDoctorsResponse implements Serializable {
    @SerializedName("data")
    @Expose
    private List<ReceivedMedicalInfo> data = null;
    private final static long serialVersionUID = 8859009251529085572L;

    public List<ReceivedMedicalInfo> getData() {
        return data;
    }

    public void setData(List<ReceivedMedicalInfo> data) {
        this.data = data;
    }
}
