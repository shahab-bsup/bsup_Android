package tk.medlynk.patient.android.Model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Shahab on 2/22/2018.
 */

public class NewSymptomAnswerResponse implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("appointment_id")
    @Expose
    private Integer appointmentId;
    @SerializedName("question_set")
    @Expose
    private String questionSet;
    @SerializedName("question_set_id")
    @Expose
    private Object questionSetId;
    @SerializedName("question_number")
    @Expose
    private Integer questionNumber;
    @SerializedName("answer")
    @Expose
    private Answer answer;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    private final static long serialVersionUID = -3992244228384317180L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

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

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
