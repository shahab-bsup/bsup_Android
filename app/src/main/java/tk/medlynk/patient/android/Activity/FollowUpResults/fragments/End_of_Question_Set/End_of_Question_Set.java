package tk.medlynk.patient.android.Activity.FollowUpResults.fragments.End_of_Question_Set;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

import java.util.List;

import tk.medlynk.patient.android.Activity.StartQuestionSet.StartAppointmentActivity;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.DataBase.DataBaseModel;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.ViewModel.MedlynkViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link End_of_Question_Set.OnEndOfFollowUpResultListener} interface
 * to handle interaction events.
 * Use the {@link End_of_Question_Set#newInstance} factory method to
 * create an instance of this fragment.
 */
public class End_of_Question_Set extends Fragment implements End_of_Question_Set_VH.OnEndOfQuestionSetVHListener {

    public static final String TAG = "End_of_Question_Set";

    private MedlynkViewModel mMedlynkViewModel;
    private int UnansweredQuestion=0;
    private SharedPreferenceManager manager;

    private OnEndOfFollowUpResultListener mListener;

    private View view;
    private End_of_Question_Set_VH viewHolder;

    public End_of_Question_Set() {
        // Required empty public constructor
    }

    public static End_of_Question_Set newInstance() {
        End_of_Question_Set fragment = new End_of_Question_Set();
        Bundle args = new Bundle ();
        fragment.setArguments ( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        if (getArguments () != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate ( R.layout.fragment_new__symptom_24th_question, container, false );
        viewHolder = new End_of_Question_Set_VH( view );
        viewHolder.setOnEndOfQuestionSetVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnEndOfFollowUpResultListener) {
            mListener = (OnEndOfFollowUpResultListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnEndOfFollowUpSymptomListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onButtonClicked(int buttonId) {
        System.out.println ( "End_of_Question_Set.onButtonClicked" );
        switch (buttonId){
            case 0:{
                takeFirstUnansweredQuestion();
                break;
            }
            case 1:{

                getActivity ().startActivity ( new Intent ( getActivity (), StartAppointmentActivity.class ) );
                getActivity ().finish ();

                break;
            }
            case 2:{


                break;
            }
            case 3:{


                break;
            }
        }
    }

    private void takeFirstUnansweredQuestion(){
        manager = new SharedPreferenceManager ( getActivity () );
        mMedlynkViewModel = ViewModelProviders.of ( getActivity () ).get ( MedlynkViewModel.class );
        mMedlynkViewModel.getAnswersList(manager.getAppointmentID(), Constants.FOLLOW_UP_RESULTS_ROW,0)
                .observe(this, new Observer<List<DataBaseModel>>() {
                    @Override
                    public void onChanged(@Nullable List<DataBaseModel> dataBaseModels) {
                        if(dataBaseModels!=null){
                            boolean existFlag;

                            for (int questionNumber=1;questionNumber<=Constants.FOLLOW_UP_RESULTS_QUESTIONS_NUMBER;questionNumber++){
                                existFlag=false;
                                for (DataBaseModel db:dataBaseModels) {
                                    if(questionNumber==db.getQuestionNumber()){
                                        existFlag=true;
                                        break;
                                    }
                                }

                                if(existFlag==false){
                                    UnansweredQuestion=questionNumber;
                                    break;
                                }
                            }

                            System.out.println("firstUnAnsweredQuestion " + UnansweredQuestion);

                            mListener.firstUnAnsweredQuestion(UnansweredQuestion);
                        }
                    }
                });
    }

    public interface OnEndOfFollowUpResultListener {
        // TODO: Update argument type and name
        void firstUnAnsweredQuestion(int questionNumber);
    }
}
