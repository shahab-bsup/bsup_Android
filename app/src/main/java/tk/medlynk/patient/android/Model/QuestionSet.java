package tk.medlynk.patient.android.Model;

/**
 * Created by Shahab on 3/16/2018.
 */

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionSet implements Serializable
{

    @SerializedName("question_set")
    @Expose
    private String questionSet;
    @SerializedName("question_set_id")
    @Expose
    private Object questionSetId;
    @SerializedName("answers")
    @Expose
    private Answers answers;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    private final static long serialVersionUID = -6311735964971329329L;

    public String getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(String questionSet) {
        this.questionSet = questionSet;
    }

    public Object getQuestionSetId() {
        return questionSetId;
    }

    public void setQuestionSetId(Object questionSetId) {
        this.questionSetId = questionSetId;
    }

    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
