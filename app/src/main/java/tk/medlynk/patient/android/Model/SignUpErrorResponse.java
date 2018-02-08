package tk.medlynk.patient.android.Model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Shahab on 1/15/2018.
 */

public class SignUpErrorResponse {
    @SerializedName("message")
    @Expose
    @Nullable
    private String message;
    @SerializedName("errors")
    @Expose
    @Nullable
    private Errors errors;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Errors getErrors() {
        return errors;
    }
    public void setErrors(Errors errors) {
        this.errors = errors;
    }
}