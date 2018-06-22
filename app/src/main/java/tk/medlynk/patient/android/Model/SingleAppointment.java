package tk.medlynk.patient.android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Shahab on 6/22/2018.
 */

public class SingleAppointment implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("provider_name")
    @Expose
    private String providerName;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("delete_in")
    @Expose
    private Integer deleteIn;
    @SerializedName("is_read")
    @Expose
    private Boolean isRead;
    @SerializedName("question_sets_count")
    @Expose
    private Integer questionSetsCount;
    @SerializedName("answers_count")
    @Expose
    private Integer answersCount;
    private final static long serialVersionUID = -5301930774109522140L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDeleteIn() {
        return deleteIn;
    }

    public void setDeleteIn(Integer deleteIn) {
        this.deleteIn = deleteIn;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Integer getQuestionSetsCount() {
        return questionSetsCount;
    }

    public void setQuestionSetsCount(Integer questionSetsCount) {
        this.questionSetsCount = questionSetsCount;
    }

    public Integer getAnswersCount() {
        return answersCount;
    }

    public void setAnswersCount(Integer answersCount) {
        this.answersCount = answersCount;
    }

}
