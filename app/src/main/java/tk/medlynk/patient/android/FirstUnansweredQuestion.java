package tk.medlynk.patient.android;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.util.List;

import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;

/**
 * Created by admin on 6/23/2018.
 */

public class FirstUnansweredQuestion {
    private static FirstUnansweredQuestion ourInstance ;
    private MedlynkViewModel mMedlynkViewModel;
    private boolean runFunction;

    public static FirstUnansweredQuestion getInstance() {
        if (ourInstance==null){
            ourInstance=new FirstUnansweredQuestion();

        }
        return ourInstance;
    }

    private FirstUnansweredQuestion() {
    }

    public void takeFirstUnansweredQuestion( FragmentActivity activity, final OnFirstUnansweredQuestionListener listener,
                                             int appointmentId, int tableNumber, int questionSetId,final int questionNumbers ){
        runFunction=true;
        mMedlynkViewModel = ViewModelProviders.of(activity).get ( MedlynkViewModel.class );
        mMedlynkViewModel.getAnswersList(appointmentId,tableNumber,questionSetId)
                .observe(activity, new Observer<List<DataBaseModel>>() {
                    @Override
                    public void onChanged(@Nullable List<DataBaseModel> dataBaseModels) {

                        if(dataBaseModels!=null){
                            boolean existFlag;

                            for (int questionNumber=1;questionNumber<=questionNumbers;questionNumber++){
                                existFlag=false;
                                for (DataBaseModel db:dataBaseModels) {
                                    if(questionNumber==db.getQuestionNumber()){
                                        existFlag=true;
                                        break;
                                    }
                                }

                                if(existFlag==false){
                                    if(runFunction) {
                                        runFunction=false;
                                        listener.firstUnAnsweredQuestionResponse(questionNumber);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                });
    }
}
