package tk.medlynk.patient.android.Model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Appointment implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("question_sets")
    @Expose
    private List<QuestionSet> questionSets = null;
    private final static long serialVersionUID = 2340174238861800696L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<QuestionSet> getQuestionSets() {
        return questionSets;
    }

    public void setQuestionSets(List<QuestionSet> questionSets) {
        this.questionSets = questionSets;
    }
}
