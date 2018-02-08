package tk.medlynk.patient.android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Shahab on 2/2/2018.
 */

public class CurrentUserInfo implements Serializable {

    private static CurrentUserInfo mainCurrentUserInfo = new CurrentUserInfo ();

    private CurrentUserInfo(){

    }

    public static CurrentUserInfo getInstance(){
        return mainCurrentUserInfo;
    }

    public static void setCurrentUserInfo ( CurrentUserInfo currentUserInfo ){
        mainCurrentUserInfo = currentUserInfo;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("preferences")
    @Expose
    private Preferences preferences;
    @SerializedName("appointment")
    @Expose
    private Object appointment;
    private final static long serialVersionUID = -4182397567756242839L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public Object getAppointment() {
        return appointment;
    }

    public void setAppointment(Object appointment) {
        this.appointment = appointment;
    }
}
