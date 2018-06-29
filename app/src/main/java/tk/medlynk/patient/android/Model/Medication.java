package tk.medlynk.patient.android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.Transient;

/**
 * Created by Shahab on 3/24/2018.
 */

public class Medication implements Serializable {

    @SerializedName ( "name" )
    @Expose
    private String name = "";

    @SerializedName ( "frequently" )
    @Expose
    private String frequently = "";

    @SerializedName ( "helpfully" )
    @Expose
    private String helpfully = "";

    @SerializedName ( "side_effects" )
    @Expose
    private String sideEffects = "";

    @Transient
    private boolean frequentlyError;
    @Transient
    private boolean helpfullyError;
    @Transient
    private boolean sideEffectError;
    @Transient
    private boolean nameError ;

    public boolean isFrequentlyError() {
        return frequentlyError;
    }

    public void setFrequentlyError(boolean frequentlyError) {
        this.frequentlyError = frequentlyError;
    }

    public boolean isHelpfullyError() {
        return helpfullyError;
    }

    public void setHelpfullyError(boolean helpfullyError) {
        this.helpfullyError = helpfullyError;
    }

    public boolean isSideEffectError() {
        return sideEffectError;
    }

    public void setSideEffectError(boolean sideEffectError) {
        this.sideEffectError = sideEffectError;
    }

    public boolean isNameError() {
        return nameError;
    }

    public void setNameError(boolean nameError) {
        this.nameError = nameError;
    }

    private final static long serialVersionUID = -7094302714953607464L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrequently() {
        return frequently;
    }

    public void setFrequently(String frequently) {
        this.frequently = frequently;
    }

    public String getHelpfully() {
        return helpfully;
    }

    public void setHelpfully(String helpfully) {
        this.helpfully = helpfully;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

}
