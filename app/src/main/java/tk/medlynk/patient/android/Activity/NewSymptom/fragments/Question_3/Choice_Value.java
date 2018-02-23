package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_3;

/**
 * Created by Shahab on 2/23/2018.
 */

public class Choice_Value {
    private String choice;
    private Integer value;

    public Choice_Value(String choice, Integer value) {
        this.choice = choice;
        this.value = value;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
