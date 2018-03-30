package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_12;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import java.util.List;

import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_14.NS_14th_VH;
import tk.medlynk.patient.android.Essentials.MedicationsAdapter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.Medication;

/**
 * Created by Shahab on 3/4/2018.
 */

public class FUpS_12th_VH extends RecyclerView.ViewHolder implements ViewSelection.OnSingleItemSelectedListener, MedicationsAdapter.OnEmptyMedicationListener {

    private final ProgressBar progressBar;
    private final View question_view;
    private final TextView second_question;
    private final Button button_next;
    private final Button button_skip;
    private Button add_a_medication;
    private ViewSelection choice;
    private RecyclerView medications;
    private MedicationsAdapter medicationAdapter;
    private List<Medication> answers;
    private Answer answer;
    private OnFUpSTwelveVHListener onFUpSTwelveVHListener;

    public FUpS_12th_VH(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = itemView.findViewById ( R.id.follow_up_symptoms_twelve_question );
        second_question = question_view.findViewById ( R.id.txtQuestion );
        second_question.setText ( itemView.getContext ().getString ( R.string.follow_up_symptoms_12th_question ) );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextButtonClickListener () );
        button_next.setEnabled ( false );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        choice = itemView.findViewById ( R.id.viewSelectionChoices );
        choice.setTextToButtons ( itemView.getContext ().getResources ().getString ( R.string.question_14 ), 0 );
        choice.setOnSingleItemSelectedListener ( this );
        add_a_medication = itemView.findViewById ( R.id.add_medication );
        add_a_medication.setOnClickListener ( new OnAddAMedicationClicked () );
        medications = itemView.findViewById ( R.id.recycler_view_medications );
        medications.setNestedScrollingEnabled ( false );
        medicationAdapter = new MedicationsAdapter ( itemView.getContext () );
        medicationAdapter.setOnEmptyMedicationListener ( this );
        medications.setAdapter ( medicationAdapter );
    }

    public void setProgressBarVisibilityStatus(int status ){
        this.progressBar.setVisibility ( status );
    }

    @Override
    public void onSingleItemSelected(View view, int position) {
        System.out.println ( "FUpS_12th_VH.onSingleItemSelected" );
        if (position == 0) {
            answer.setChoice ( "a" );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
            medicationAdapter.clearDataSet ();
        } else {
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }
    }

    @Override
    public void onEmptyMedication() {
        System.out.println ( "FUpS_12th_VH.onEmptyMedication" );
        button_next.setEnabled ( false );
        button_next.setBackgroundResource ( R.drawable.enable_next_question );
    }

    private class OnNextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_12th_VH.FUpS_12th_VH" );
            System.out.println ( "OnNextButtonClickListener.onClick" );
            if (medicationAdapter.getDataSet ().size () > 0) {
                boolean hasError = false;
                for (Medication medication : medicationAdapter.getDataSet ()) {
                    if (medication.getName ().length () == 0) {
                        medication.setNameError ( true );
                        hasError = true;
                    } else {
                        medication.setNameError ( false );
                    }
                    if (TextUtils.isEmpty ( medication.getFrequently () )) {
                        medication.setFrequentlyError ( true );
                        hasError = true;
                    } else {
                        medication.setFrequentlyError ( false );
                    }
                    if (TextUtils.isEmpty ( medication.getHelpfully () )) {
                        medication.setHelpfullyError ( true );
                        hasError = true;
                    } else {
                        medication.setHelpfullyError ( false );
                    }
                    if (medication.getSideEffects () != null) {
                        medication.setSideEffectError ( true );
                        hasError = true;
                    } else {
                        medication.setSideEffectError ( false );
                    }
                }
                if (!hasError) {
                    onFUpSTwelveVHListener.onNextClicked ( medicationAdapter.getDataSet () );
                }
                medicationAdapter.notifyDataSetChanged ();
            } else {
                onFUpSTwelveVHListener.onNextClicked ( answer );
            }
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "FUpS_12th_VH.FUpS_12th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFUpSTwelveVHListener.onSkipClicked ();
        }
    }

    public void setOnFUpSTwelveVHListener(OnFUpSTwelveVHListener onFUpSTwelveVHListener) {
        this.onFUpSTwelveVHListener = onFUpSTwelveVHListener;
    }

    public interface OnFUpSTwelveVHListener {
        void onNextClicked(Answer answer);
        void onNextClicked(List<Medication> answers);
        void onSkipClicked();
    }

    private class OnAddAMedicationClicked implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "OnAddAMedicationClicked.onClick" );
            medicationAdapter.setMedications ( new Medication () );
            if( medicationAdapter.getDataSet ().size () == 1 ){
                button_next.setEnabled ( true );
                button_next.setBackgroundResource ( R.drawable.enable_next_question );
            }
        }
    }
}
