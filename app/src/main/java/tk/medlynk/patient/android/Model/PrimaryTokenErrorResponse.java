package tk.medlynk.patient.android.Model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 * Created by Shahab on 1/12/2018.
 *
 */

public class PrimaryTokenErrorResponse implements Serializable {

    @SerializedName ( "message" )
    @Expose
    @Nullable
    private String message;

    @SerializedName ( "error" )
    @Expose
    @Nullable
    private String error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
