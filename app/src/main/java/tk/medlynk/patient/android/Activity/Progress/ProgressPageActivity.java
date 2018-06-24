package tk.medlynk.patient.android.Activity.Progress;

import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.neweraandroid.demo.R;

import java.util.List;
import java.lang.*;

import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.CustomViews.CustomTextView;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.ProgressResponse;
import tk.medlynk.patient.android.Networking.MedlynkRequests;
import tk.medlynk.patient.android.Model.QuestionSet;

public class ProgressPageActivity extends AppCompatActivity implements OnProgressResponseListener{

   // private ProgressPage_VH viewHolder;

    LinearLayout refill_Layout,newSymptoms_Layout,followUpSymptoms_Layout,followUpResult_Layout;
    SharedPreferenceManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_progress_page );

        manager= new SharedPreferenceManager(this);

        refill_Layout=(LinearLayout)findViewById(R.id.refill_Layout);
        newSymptoms_Layout=(LinearLayout)findViewById(R.id.newSymptoms_Layout);
        followUpSymptoms_Layout=(LinearLayout)findViewById(R.id.followUpSymptoms_Layout);
        followUpResult_Layout=(LinearLayout)findViewById(R.id.followUpResult_Layout);

        MedlynkRequests.getProgressResults(this,this,manager.getAppointmentID());

        //View view = findViewById(R.id.progress_page_toolbar);
        //ImageView imageView = view.findViewById(R.id.imgBackButton);
    }

    @Override
    public void onAnswerSuccess(ProgressResponse response) {
        System.out.println(response.getData().getId());

        for (QuestionSet questionSet:response.getData().getQuestionSets()) {

            switch (questionSet.getQuestionSet()){
                case "new_symptom":{
                    showNewSymptomsQuestionSet(questionSet);
                    break;
                }
                case "refill_a_medication":{

                    break;
                }
                case "follow_up_symptoms":{
                    showFollowUpSymptomsQuestionSet(questionSet);
                    break;
                }
                case "follow_up_results":{
                    showFollowUpResultQuestionSet(questionSet);
                    break;
                }
            }
        }
    }

    @Override
    public void onAnswerFailure() {

    }


    public void showNewSymptomsQuestionSet(QuestionSet questionSet){
        List<Answer> answersInQuestionSet;
        double questionSetAnswered;

        newSymptoms_Layout.setVisibility(View.VISIBLE);
        View view= findViewById(R.id.progress_NewSymptoms);

       // answersInQuestionSet= questionSet.getAnswers();


        //questionSetAnswered=  Math.floor(((double)answersInQuestionSet.size() / Constants.NEW_SYMPTOM_QUESTIONS_NUMBER) * 100);

        CustomTextView itemTitle=(CustomTextView)view.findViewById(R.id.text_progressItemTitle);
        itemTitle.setText(questionSet.getQuestionSet());

        CustomTextView itemDetail=(CustomTextView)view.findViewById(R.id.text_progressItemDetail);
        itemDetail.setText(questionSet.getCreatedAt());

    }

    public void showFollowUpSymptomsQuestionSet(QuestionSet questionSet){
        List<Answer> answersInQuestionSet;
        double questionSetAnswered;

        followUpSymptoms_Layout.setVisibility(View.VISIBLE);
        View view= findViewById(R.id.progress_FollowUpSymptoms);

        //answersInQuestionSet= questionSet.getAnswers().getAnswersInQuestionSet();

        //questionSetAnswered=  Math.floor(((double)(answersInQuestionSet.size() / Constants.FOLLOW_UP_SYMPTOMS_QUESTIONS_NUMBER)) * 100);

        CustomTextView itemTitle=(CustomTextView)view.findViewById(R.id.text_progressItemTitle);
        itemTitle.setText(questionSet.getQuestionSet());

        CustomTextView itemDetail=(CustomTextView)view.findViewById(R.id.text_progressItemDetail);
        itemDetail.setText(questionSet.getCreatedAt());
    }

    public void showFollowUpResultQuestionSet(QuestionSet questionSet){
        List<Answer> answersInQuestionSet;
        double questionSetAnswered;

        followUpResult_Layout.setVisibility(View.VISIBLE);
        View view= findViewById(R.id.progress_FollowUpResult);

        //answersInQuestionSet= questionSet.getAnswers().getAnswersInQuestionSet();

       // questionSetAnswered=  Math.floor(((double)(answersInQuestionSet.size() / Constants.FOLLOW_UP_RESULTS_QUESTIONS_NUMBER)) * 100);

        CustomTextView itemTitle=(CustomTextView)view.findViewById(R.id.text_progressItemTitle);
        itemTitle.setText(questionSet.getQuestionSet());

        CustomTextView itemDetail=(CustomTextView)view.findViewById(R.id.text_progressItemDetail);
        itemDetail.setText(questionSet.getCreatedAt());
    }



}
