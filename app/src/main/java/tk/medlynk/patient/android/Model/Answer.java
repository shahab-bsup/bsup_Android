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
    @SerializedName ( "duration" )
    @Expose
    private int duration;
    @SerializedName("rate")
    @Expose
    private int rate;
    @SerializedName ( "other" )
    @Expose
    private String other;

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
