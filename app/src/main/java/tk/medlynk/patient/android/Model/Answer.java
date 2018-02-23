package tk.medlynk.patient.android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Shahab on 2/22/2018.
 */

public class Answer implements Serializable {

    @SerializedName("reply")
    @Expose
    private String reply;
    @SerializedName("choice")
    @Expose
    private String choice;
    @SerializedName("rate")
    @Expose
    private String rate;

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
