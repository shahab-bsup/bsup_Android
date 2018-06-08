package tk.medlynk.patient.android.DataBase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by admin on 5/31/2018.
 */

@Database(entities = {DataBaseModel.class}, version = 1, exportSchema = false)
public abstract class MedlynkDatabase extends RoomDatabase {
    public  abstract AnswerDao answerDao();

    private static MedlynkDatabase instance;

    public static MedlynkDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (MedlynkDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            MedlynkDatabase.class, "MedlynkDatabase")
                            .addCallback(sMedlynkDatabaseCallback)
                            .build();
                }
            }
        }

        return instance;
    }

    private static MedlynkDatabase.Callback sMedlynkDatabaseCallback = new MedlynkDatabase.Callback(){
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
        }
    };
}


