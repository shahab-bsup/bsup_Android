package tk.medlynk.patient.android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private String side_effects = "";

    private boolean medicationNameError;
    private boolean medicationFrequentlyError;
    private boolean medicationHelpfullyError;
    private boolean medicationSideEffectError;

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

    public String getSide_effects() {
        return side_effects;
    }

    public void setSide_effects(String side_effects) {
        this.side_effects = side_effects;
    }

    public void setMedicationNameError(boolean isError){
        this.medicationNameError = isError;
    }

    public boolean isMedicationNameError() {
        return medicationNameError;
    }

    public boolean isMedicationFrequentlyError() {
        return medicationFrequentlyError;
    }

    public void setMedicationFrequentlyError(boolean medicationFrequentlyError) {
        this.medicationFrequentlyError = medicationFrequentlyError;
    }

    public boolean isMedicationHelpfullyError() {
        return medicationHelpfullyError;
    }

    public void setMedicationHelpfullyError(boolean medicationHelpfullyError) {
        this.medicationHelpfullyError = medicationHelpfullyError;
    }

    public boolean isMedicationSideEffectError() {
        return medicationSideEffectError;
    }

    public void setMedicationSideEffectError(boolean medicationSideEffectError) {
        this.medicationSideEffectError = medicationSideEffectError;
    }
}
