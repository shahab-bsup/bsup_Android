package tk.medlynk.patient.android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

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
    @SerializedName ( "years" )
    @Expose
    private int years;
    @SerializedName ( "other" )
    @Expose
    private String other;
    @SerializedName ( "helpfully" )
    @Expose
    private int helpfully = -1;
    @SerializedName("sub_choices")
    @Expose
    private List<String> subChoices;
    @SerializedName("sub_choice")
    @Expose
    private String subChoice;
    @SerializedName("since")
    @Expose
    private int since;
    @SerializedName("reading")
    @Expose
    private String reading;
    @SerializedName("better")
    @Expose
    private int better;

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public void setSince(int since) {
        this.since = since;
    }

    public int getSince() {
        return since;
    }

    public String getSubChoice() {
        return subChoice;
    }

    public void setSubChoice(String subChoice) {
        this.subChoice = subChoice;
    }

    public int getHelpfully() {
        return helpfully;
    }

    public void setHelpfully(int helpfully) {
        this.helpfully = helpfully;
    }

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

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public List<String> getSubChoices() {
        return subChoices;
    }

    public void setSubChoices(List<String> subChoices) {
        this.subChoices = subChoices;
    }

    public void setBetter(int better) {
        this.better = better;
    }
    public int getBetter(){
        return this.better;
    }
}
