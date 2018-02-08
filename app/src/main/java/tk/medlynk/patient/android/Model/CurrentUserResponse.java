package tk.medlynk.patient.android.Model;

/**
 * Created by Shahab on 2/2/2018...
 */

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentUserResponse implements Serializable {

    @SerializedName("data")
    @Expose
    private CurrentUserInfo currentUserInfo;

    private final static long serialVersionUID = -2420979164143381626L;

    public CurrentUserInfo getCurrentUserInfo() {
        return  currentUserInfo;
    }

    public void setCurrentUserInfo(CurrentUserInfo currentUserInfo) {
        CurrentUserInfo.setCurrentUserInfo ( currentUserInfo );
    }

}
