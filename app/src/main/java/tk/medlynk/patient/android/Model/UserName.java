package tk.medlynk.patient.android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Shahab on 3/16/2018.
 */

public class UserName implements Serializable
{

    @SerializedName("first")
    @Expose
    private String first;
    @SerializedName("middle")
    @Expose
    private Object middle;
    @SerializedName("last")
    @Expose
    private String last;
    private final static long serialVersionUID = -7221447330534757994L;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public Object getMiddle() {
        return middle;
    }

    public void setMiddle(Object middle) {
        this.middle = middle;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

}
