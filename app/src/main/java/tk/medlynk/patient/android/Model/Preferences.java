package tk.medlynk.patient.android.Model;

/**
 * Created by Shahab on 2/2/2018.
 */

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Preferences implements Serializable
{
    @SerializedName("skip_no_doctor_id_page")
    @Expose
    private String skipNoDoctorIdPage;
    @SerializedName ( "android_app_rating" )
    @Expose
    private String android_app_rating;

    private final static long serialVersionUID = -1809957466996592122L;

    public String getSkipNoDoctorIdPage() {
        return skipNoDoctorIdPage;
    }

    public void setSkipNoDoctorIdPage(String skipNoDoctorIdPage) {
        this.skipNoDoctorIdPage = skipNoDoctorIdPage;
    }

    public String getAndroid_app_rating() {
        return android_app_rating;
    }

    public void setAndroid_app_rating(String android_app_rating) {
        this.android_app_rating = android_app_rating;
    }
}
