package tk.medlynk.patient.android.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Repository.MedlynkModel;

/**
 * Created by Shahab on 6/6/2018.
 */

public class MedlynkViewModel extends AndroidViewModel {

    private MedlynkModel mRepository;
    private LiveData<DataBaseModel> mGetAnswers;
    private LiveData<List<DataBaseModel>> mGetAnswersList;

    public MedlynkViewModel(Application application) {
        super ( application );
        mRepository = new MedlynkModel ( application );
    }

    public LiveData<DataBaseModel> getAnswers(int appointmentId, int tableNumber, int questionNumber) {
        mGetAnswers = mRepository.GetAnswerRecord ( appointmentId, tableNumber, questionNumber );
        return mGetAnswers;
    }

    public LiveData<List<DataBaseModel>> getAnswersList(int appointmentId, int tableNumber){
        mGetAnswersList=mRepository.GetAnswersList(appointmentId,tableNumber);
        return mGetAnswersList;
    }

    public void insertAnswersToDB(int appointmentId, int tableNumber, int questionNumber, String answersJson) {
        mRepository.InsertAnswerRecord ( appointmentId, tableNumber, questionNumber, answersJson );
    }

    public void updateAnswersToDB(int appointmentId, int tableNumber, int questionNumber, String answersJson) {
        mRepository.UpdateAnswerRecord ( appointmentId, tableNumber, questionNumber, answersJson );
    }

}