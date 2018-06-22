package tk.medlynk.patient.android.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Shahab on 6/3/2018.
 */

@Entity (primaryKeys = {"appointmentId", "tableNumber","questionSetId","questionNumber"},tableName = "tbl_AnswersDataBase" )
public class DataBaseModel {

    @NonNull
    private Integer appointmentId;

    @NonNull
    private Integer tableNumber;

    @NonNull
    private Integer questionSetId;

    @NonNull
    private Integer questionNumber;

    @NonNull
    public Integer getQuestionSetId() {
        return questionSetId;
    }

    public void setQuestionSetId(@NonNull Integer questionSetId) {
        this.questionSetId = questionSetId;
    }

    private String answerJson;

    @NonNull
    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(@NonNull Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    @NonNull
    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(@NonNull Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    @NonNull
    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(@NonNull Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getAnswerJson() {
        return answerJson;
    }

    public void setAnswerJson(String answerJson) {
        this.answerJson = answerJson;
    }
}
