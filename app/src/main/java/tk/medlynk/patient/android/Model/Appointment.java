package tk.medlynk.patient.android.Model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Appointment implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("question_sets")
    @Expose
    private List<Object> questionSets = null;
    private final static long serialVersionUID = -628283601437989675L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Object> getQuestionSets() {
        return questionSets;
    }

    public void setQuestionSets(List<Object> questionSets) {
        this.questionSets = questionSets;
    }

}