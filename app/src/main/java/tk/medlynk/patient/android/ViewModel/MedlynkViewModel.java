package tk.medlynk.patient.android.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.Repository.MedlynkRepository;

/**
 * Created by Shahab on 6/6/2018.
 */

public class MedlynkViewModel extends AndroidViewModel {

    private MedlynkRepository mRepository;
    private LiveData<DataBaseModel> mGetAnswers;

    public MedlynkViewModel(Application application) {
        super ( application );
        mRepository = new MedlynkRepository ( application );
    }

    public LiveData<DataBaseModel> getAnswers(int appointmentId, int tableNumber, int questionNumber) {
        mGetAnswers = mRepository.GetAnswerRecord ( appointmentId, tableNumber, questionNumber );
        return mGetAnswers;
    }

    public void insertAnswersToDB(int appointmentId, int tableNumber, int questionNumber, String answersJson) {
        mRepository.InsertAnswerRecord ( appointmentId, tableNumber, questionNumber, answersJson );
    }

    public void updateAnswersToDB(int appointmentId, int tableNumber, int questionNumber, String answersJson) {
        mRepository.UpdateAnswerRecord ( appointmentId, tableNumber, questionNumber, answersJson );
    }

}