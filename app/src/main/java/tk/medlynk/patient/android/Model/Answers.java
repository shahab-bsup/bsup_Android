
package tk.medlynk.patient.android.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answers {

    @SerializedName("answer")
    @Expose
    private List<Answer> answersInQuestionSet;

    public List<Answer> getAnswersInQuestionSet() {
        return answersInQuestionSet;
    }

    public void setAnswersInQuestionSet(List<Answer> answersInQuestionSet) {
        this.answersInQuestionSet = answersInQuestionSet;
    }
}
