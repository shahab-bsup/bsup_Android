package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_14;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.Medication;

/**
 * Created by Shahab on 3/24/2018.
 */

public class NS_14th_VH extends RecyclerView.ViewHolder implements
        ViewSelection.OnSingleItemSelectedListener {

    private View question_view;
    private Button button_next, button_skip, add_a_medication;
    private TextView question;
    private ViewSelection choice;
    private RecyclerView medications;
    private OnFourteenNSVHListener onFourteenNSVHListener;
    private MedicationsAdapter medicationAdapter;
    private RelativeLayout.LayoutParams layoutParams;
    private List<Medication> answers;
    private Answer answer;
    private ProgressBar progressBar;

    public NS_14th_VH(View itemView) {
        super ( itemView );
        progressBar = itemView.findViewById ( R.id.progress_bar );
        answers = new ArrayList<> ();
        answer = new Answer ();
        question_view = itemView.findViewById ( R.id.new_symptom_fourteen_question );
        question = question_view.findViewById ( R.id.txtQuestion );
        question.setText ( R.string.new_symptom_fourteen_question );
        button_next = itemView.findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener () );
        button_skip = itemView.findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener () );
        choice = itemView.findViewById ( R.id.viewSelectionChoices );
        choice.setTextToButtons ( itemView.getContext ().getResources ().getString ( R.string.question_14 ), 0 );
        choice.setOnSingleItemSelectedListener ( this );
        add_a_medication = itemView.findViewById ( R.id.add_medication );
        add_a_medication.setOnClickListener ( new OnAddAMedicationClicked () );
        medications = itemView.findViewById ( R.id.recycler_view_medications );
        medications.setNestedScrollingEnabled ( false );
    }

    public void setProgressBarVisibilityStatus( int status ){
        this.progressBar.setVisibility ( status );
    }

    public void setOnFourteenNSVHListener(OnFourteenNSVHListener onFourteenNSVHListener) {
        this.onFourteenNSVHListener = onFourteenNSVHListener;
    }

    public void setAdapter(MedicationsAdapter adapter) {
        this.medicationAdapter = adapter;
        this.medications.setAdapter ( adapter );
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "NS_14th_VH.onSingleItemSelected" );
        if (i == 0) {
            answer.setChoice ( "a" );
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
            NS_14th_question.medications.clear ();
            medicationAdapter.notifyDataSetChanged ();
        } else {
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }
    }

    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_14th_VH.NS_14th_VH" );
            System.out.println ( "OnNextClickListener.onClick" );
            if (NS_14th_question.medications.size () > 0) {
                onFourteenNSVHListener.onNextClicked ( NS_14th_question.medications );
            } else {
                onFourteenNSVHListener.onNextClicked ( answer );
            }
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "NS_14th_VH.NS_14th_VH" );
            System.out.println ( "OnSkipClickListener.onClick" );
            onFourteenNSVHListener.onSkipClicked ();
        }
    }

    public interface OnFourteenNSVHListener {
        void onNextClicked(Answer answer);

        void onNextClicked(List<Medication> answers);

        void onSkipClicked();
    }

    private class OnAddAMedicationClicked implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "OnAddAMedicationClicked.onClick" );
            Medication medication = new Medication ();
            NS_14th_question.medications.add ( medication );
            medicationAdapter.notifyDataSetChanged ();
        }
    }
}
