package tk.medlynk.patient.android.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


/**
 * Created by Shahab on 6/3/2018.
 */

@Dao
public interface AnswerDao {

    @Query("SELECT * FROM tbl_AnswersDataBase WHERE " +
            "(appointmentId IN (:appointmentId)) AND (tableNumber IN (:tableNumber))" +
            "AND (questionSetId IN (:questionSetId))  AND (questionNumber IN (:questionNumber))")
    LiveData<DataBaseModel> getAnswerRecord(int appointmentId, int tableNumber,int questionSetId, int questionNumber);

    @Query("SELECT * FROM tbl_AnswersDataBase WHERE "+
            "(appointmentId IN (:appointmentId)) AND (tableNumber IN (:tableNumber)) AND (questionSetId IN (:questionSetId))")
    LiveData<List<DataBaseModel>> getAnswersList(int appointmentId, int tableNumber,int questionSetId);

    @Insert
    void insertRecord(DataBaseModel... answerRecord);

    @Update
    void updateAnswer(DataBaseModel... answerRecord);

}
