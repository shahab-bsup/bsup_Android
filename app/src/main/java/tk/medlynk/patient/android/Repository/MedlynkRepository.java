package tk.medlynk.patient.android.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import tk.medlynk.patient.android.DataBase.AnswerDao;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.DataBase.MedlynkDatabase;

/**
 * Created by Shahab on 6/6/2018.
 */

public class MedlynkRepository {

    private AnswerDao mAnswerDao;
    private LiveData<DataBaseModel> mGetAnswerRecord;

    public MedlynkRepository(Application application) {

        MedlynkDatabase db = MedlynkDatabase.getDatabase ( application );
        mAnswerDao = db.answerDao ();
    }

    public LiveData<DataBaseModel> GetAnswerRecord(int appointmentId, int tableNumber, int questionNumber) {

        mGetAnswerRecord= mAnswerDao.getAnswerRecord ( appointmentId, tableNumber, questionNumber );
        return mGetAnswerRecord;
    }

    public void InsertAnswerRecord(int appointmentId, int tableNumber, int questionNumber,String answerJson) {

        DataBaseModel answerRecord=new DataBaseModel ();
        answerRecord.setAppointmentId ( appointmentId );
        answerRecord.setTableNumber ( tableNumber );
        answerRecord.setQuestionNumber ( questionNumber );
        answerRecord.setAnswerJson ( answerJson );

        new InsertAsyncTask ( mAnswerDao ).execute ( answerRecord );
    }

    public void UpdateAnswerRecord(int appointmentId, int tableNumber, int questionNumber,String answersJson){

        DataBaseModel answerRecord=new DataBaseModel ();
        answerRecord.setAppointmentId ( appointmentId );
        answerRecord.setTableNumber ( tableNumber );
        answerRecord.setQuestionNumber ( questionNumber );
        answerRecord.setAnswerJson ( answersJson );

        new UpdateAsyncTask(mAnswerDao).execute(answerRecord);
    }

    private static class InsertAsyncTask extends AsyncTask<DataBaseModel, Void, Void> {

        private AnswerDao mAsyncTaskDao;

        InsertAsyncTask(AnswerDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final DataBaseModel... params){
            mAsyncTaskDao.insertRecord (params[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<DataBaseModel, Void, Void> {

        private AnswerDao mAsyncTaskDao;

        UpdateAsyncTask(AnswerDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final DataBaseModel... params){
            mAsyncTaskDao.updateAnswer (params[0]);
            return null;
        }
    }

}
