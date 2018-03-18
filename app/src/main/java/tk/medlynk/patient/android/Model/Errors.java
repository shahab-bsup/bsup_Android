package tk.medlynk.patient.android.Model;

/**
 * Created by Shahab on 1/16/2018.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Errors implements Serializable
{


    @SerializedName("birth_date")
    @Expose
    private List<String> birthDate = null;
    @SerializedName("email")
    @Expose
    private List<String> email = null;

    @SerializedName("password")
    @Expose
    private List<String> password = null;

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }

    public List<String> getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(List<String> birthDate) {
        this.birthDate = birthDate;
    }
}