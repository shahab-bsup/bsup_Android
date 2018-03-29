package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_23;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Essentials.MedicationsAdapter;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.Medication;

/**
 * Created by Shahab on 3/2/2018.
 */

public class NS_23th_VH extends RecyclerView.ViewHolder implements
        ViewSelection.OnSingleItemSelectedListener,
        MedicationsAdapter.OnEmptyMedicationListener {

    private View question_view;
    private Button button_next;
    private Button button_skip;
    private Button add_a_medication;
    private TextView question;
    private ViewSelection choice;
    private RecyclerView medications;
    private MedicationsAdapter medicationAdapter;
    private List<Medication> answers;
    private Answer answer;
    private ProgressBar progressBar;
    private On23QuestionVHListener on23QuestionVHListener;

    public NS_23th_VH(View view) {
        super ( view );
        answers = new ArrayList<> ();
        answer = new Answer ();
        progressBar = itemView.findViewById ( R.id.progress_bar );
        question_view = view.findViewById ( R.id.new_symptom_twenty3_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_twenty2_question );
        button_next = view.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_skip = view.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        choice = view.findViewById ( R.id.viewSelectionChoices );
        choice.setTextToButtons ( view.getContext ().getResources ().getString ( R.string.none ), 0 );
        choice.setOnSingleItemSelectedListener ( this );
        add_a_medication = itemView.findViewById ( R.id.add_medication );
        add_a_medication.setOnClickListener ( new OnAddAMedicationClicked () );
        medications = itemView.findViewById ( R.id.recycler_view_medications );
        medications.setNestedScrollingEnabled ( false );
        medicationAdapter = new MedicationsAdapter ( itemView.getContext () );
        medicationAdapter.setOnEmptyMedicationListener ( this );
        medications.setAdapter ( medicationAdapter );
    }

    public void setOn23QuestionVHListener(On23QuestionVHListener on23QuestionVHListener) {
        this.on23QuestionVHListener = on23QuestionVHListener;
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "NS_23th_VH.onSingleItemSelected" );
        if (i == 0) {
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
        System.out.println ( "NS_23th_VH.onEmptyMedication" );
        button_next.setEnabled ( false );
        button_next.setBackgroundResource ( R.drawable.enable_next_question );
    }

    public void setProgressBarVisibilityStatus(int status) {
        this.progressBar.setVisibility ( status );
    }

    public interface On23QuestionVHListener {
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

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "OnNextClickListener.onClick" );
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
                    on23QuestionVHListener.onNextClicked ( medicationAdapter.getDataSet () );
                }
                medicationAdapter.notifyDataSetChanged ();
            } else {
                on23QuestionVHListener.onNextClicked ( answer );
            }
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "OnSkipClickListener.onClick" );
            on23QuestionVHListener.onSkipClicked ();
        }
    }
}
