package tk.medlynk.patient.android.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


/**
 * Created by Shahab on 6/3/2018.
 */

@Dao
public interface AnswerDao {

    @Query("SELECT * FROM tbl_AnswersDataBase WHERE " +
            "(appointmentId IN (:appointmentId)) AND (tableNumber IN (:tableNumber)) AND (questionNumber IN (:questionNumber))")
    LiveData<DataBaseModel> getAnswerRecord(int appointmentId, int tableNumber, int questionNumber);

    @Insert
    void insertRecord(DataBaseModel... answerRecord);

    @Update
    void updateAnswer(DataBaseModel... answerRecord);

}
