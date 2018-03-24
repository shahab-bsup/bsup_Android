package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_14;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.neweraandroid.demo.R;

import tk.medlynk.patient.android.Model.Medication;

/**
 * Created by Shahab on 3/24/2018.
 */

public class MedicationsAdapter extends RecyclerView.Adapter<MedicationsAdapter.MedicationViewHolder> {

    private Context context;
    private int white_color, colorPrimary;
    private Drawable selected, not_selected;

    public MedicationsAdapter(Context context) {
        this.context = context;
        white_color = context.getResources ().getColor ( R.color.white );
        colorPrimary = context.getResources ().getColor ( R.color.colorPrimary );
        selected = context.getResources ().getDrawable ( R.drawable.answer_selected );
        not_selected = context.getResources ().getDrawable ( R.drawable.answer_not_selected );
    }

    @Override
    public MedicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.medication_row,
                parent,
                false);
        return new MedicationViewHolder ( view );
    }

    public boolean isValid(){
        for (Medication medication : NS_14th_question.medications) {
            if( TextUtils.isEmpty ( medication.getName() )){
                medication.setMedicationNameError ( true );
            }

            if( TextUtils.isEmpty ( medication.getFrequently () ) ){
                medication.setMedicationFrequentlyError ( true );
            }

            if( TextUtils.isEmpty ( medication.getHelpfully () ) ){
                medication.setMedicationHelpfullyError ( true );
            }

            if( TextUtils.isEmpty ( medication.getSide_effects () ) ){
                medication.setMedicationSideEffectError ( true );
            }

        }
        notifyDataSetChanged ();
        return false;
    }

    @Override
    public void onBindViewHolder(MedicationViewHolder holder, int position) {
        Medication medication = NS_14th_question.medications.get ( position );
        int medication_number = position + 1;
        holder.medication_number.setText ( "Medication #" + medication_number );
        if( medication.isMedicationNameError () ){
            holder.medication_name.setError ( "please fill the name!" );
        }
        if( medication.isMedicationFrequentlyError () ){
            holder.frequently_error.setVisibility ( View.VISIBLE );
        }else{
            holder.frequently_error.setVisibility ( View.GONE );
        }
        if( medication.isMedicationHelpfullyError () ){
            holder.helpfully_error.setVisibility ( View.VISIBLE );
        }else{
            holder.helpfully_error.setVisibility ( View.GONE );
        }
        if( medication.isMedicationSideEffectError () ){
            holder.side_effect.setError ( "please fill the side effect!" );
        }
        if( medication.getName () != null ){
            holder.medication_name.setText ( medication.getName () );
        }else{
            holder.medication_name.setText ( "" );
        }
        if( medication.getSide_effects () != null &&
                !medication.getSide_effects ().equals ( "" )){
            holder.side_effect.setText ( medication.getSide_effects () );
        }else{
            holder.side_effect.setText ( medication.getSide_effects () );
        }
        if( medication.getFrequently () != null &&
                !medication.getFrequently ().equals ( "" )){
            switch (medication.getFrequently ()){
                case "1":{
                    holder.how_often_per_direction.setTextColor ( white_color );
                    holder.how_often_occasionally.setTextColor ( colorPrimary );
                    holder.how_often_often.setTextColor ( colorPrimary );

                    break;
                }
                case "2":{
                    holder.how_often_per_direction.setTextColor ( colorPrimary );
                    holder.how_often_occasionally.setTextColor ( white_color );
                    holder.how_often_often.setTextColor ( colorPrimary );

                    break;
                }
                case "3":{
                    holder.how_often_per_direction.setTextColor ( colorPrimary );
                    holder.how_often_occasionally.setTextColor ( colorPrimary );
                    holder.how_often_often.setTextColor ( white_color );

                    break;
                }
            }
        }else{
            holder.how_often_per_direction.setTextColor ( colorPrimary );
            holder.how_often_occasionally.setTextColor ( colorPrimary );
            holder.how_often_often.setTextColor ( colorPrimary );
        }
        if( medication.getHelpfully () != null &&
                !medication.getHelpfully ().equals ( "" )){
            switch (medication.getHelpfully ()){
                case "1":{
                    holder.how_helpful_a_lot.setTextColor ( white_color );
                    holder.how_helpful_a_little.setTextColor ( colorPrimary );
                    holder.how_helpful_not_help.setTextColor ( colorPrimary );

                    break;
                }
                case "2":{
                    holder.how_helpful_a_lot.setTextColor ( colorPrimary );
                    holder.how_helpful_a_little.setTextColor ( white_color );
                    holder.how_helpful_not_help.setTextColor ( colorPrimary );

                    break;
                }
                case "3":{
                    holder.how_helpful_a_lot.setTextColor ( colorPrimary );
                    holder.how_helpful_a_little.setTextColor ( colorPrimary );
                    holder.how_helpful_not_help.setTextColor ( white_color );

                    break;
                }
            }
        }else{
            holder.how_helpful_a_lot.setTextColor ( colorPrimary );
            holder.how_helpful_a_little.setTextColor ( colorPrimary );
            holder.how_helpful_not_help.setTextColor ( colorPrimary );
        }
    }

    @Override
    public int getItemCount() {
        return NS_14th_question.medications.size ();
    }

    public final class MedicationViewHolder extends RecyclerView.ViewHolder{
        TextView frequently_error, helpfully_error;
        TextView medication_number;
        AppCompatEditText medication_name, side_effect;
        Button delete_medication, no_side_effect;
        Button how_often_per_direction, how_often_often, how_often_occasionally;
        Button how_helpful_a_lot, how_helpful_a_little, how_helpful_not_help;

        public MedicationViewHolder(final View itemView) {
            super ( itemView );
            frequently_error = itemView.findViewById ( R.id.medication_frequently_error );
            helpfully_error = itemView.findViewById ( R.id.medication_helpfully_error );
            medication_number = itemView.findViewById ( R.id.number_of_medication );
            delete_medication = itemView.findViewById ( R.id.delete_medication );
            delete_medication.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    NS_14th_question.medications.remove ( getAdapterPosition () );
                    notifyDataSetChanged ();
                }
            } );
            no_side_effect = itemView.findViewById ( R.id.no_side_effect );
            no_side_effect.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    no_side_effect.setBackgroundResource ( R.drawable.answer_selected );
                    side_effect.setText ( "" );
                    NS_14th_question.medications.get ( getAdapterPosition () ).setMedicationSideEffectError ( false );
                    if( no_side_effect.getBackground ().equals ( itemView
                    .getContext ().getResources ().getDrawable ( R.drawable.answer_selected )) ){
                        no_side_effect.setBackgroundResource ( R.drawable.answer_not_selected );
                    }
                }
            } );
            how_often_per_direction = itemView.findViewById ( R.id.how_often_per_directions );
            how_often_per_direction.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    NS_14th_question.medications.get ( getAdapterPosition () )
                            .setFrequently ( "1" );
                    how_often_per_direction.setTextColor ( white_color );
                    how_often_occasionally.setTextColor ( colorPrimary );
                    how_often_often.setTextColor ( colorPrimary );
                }
            } );
            how_often_often = itemView.findViewById ( R.id.how_often_often );
            how_often_often.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    NS_14th_question.medications.get ( getAdapterPosition () )
                            .setFrequently ( "2" );
                    how_often_per_direction.setTextColor ( colorPrimary );
                    how_often_occasionally.setTextColor ( colorPrimary );
                    how_often_often.setTextColor ( white_color );
                }
            } );
            how_often_occasionally = itemView.findViewById ( R.id.how_often_occasionally );
            how_often_occasionally.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    NS_14th_question.medications.get ( getAdapterPosition () )
                            .setFrequently ( "3" );
                    how_often_per_direction.setTextColor ( colorPrimary );
                    how_often_occasionally.setTextColor ( white_color );
                    how_often_often.setTextColor ( colorPrimary );
                }
            } );
            how_helpful_a_lot = itemView.findViewById ( R.id.helpful_a_lot );
            how_helpful_a_lot.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    NS_14th_question.medications.get ( getAdapterPosition () )
                            .setHelpfully ( "1" );
                    how_helpful_a_lot.setTextColor ( white_color );
                    how_helpful_a_little.setTextColor ( colorPrimary );
                    how_helpful_not_help.setTextColor ( colorPrimary );
                }
            } );
            how_helpful_a_little = itemView.findViewById ( R.id.helpful_a_little );
            how_helpful_a_little.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    NS_14th_question.medications.get ( getAdapterPosition () )
                            .setHelpfully ( "2" );
                    how_helpful_a_lot.setTextColor ( colorPrimary );
                    how_helpful_a_little.setTextColor ( white_color );
                    how_helpful_not_help.setTextColor ( colorPrimary );
                }
            } );
            how_helpful_not_help = itemView.findViewById ( R.id.helpful_not_help );
            how_helpful_not_help.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    NS_14th_question.medications.get ( getAdapterPosition () )
                            .setHelpfully ( "3" );
                    how_helpful_a_lot.setTextColor ( colorPrimary );
                    how_helpful_a_little.setTextColor ( colorPrimary );
                    how_helpful_not_help.setTextColor ( white_color );
                }
            } );
            medication_name = itemView.findViewById ( R.id.name_of_medication_input );
            medication_name.addTextChangedListener ( new TextWatcher () {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    NS_14th_question.medications.get ( getAdapterPosition () )
                            .setName ( editable.toString () );
                }
            } );
            side_effect = itemView.findViewById ( R.id.side_effects_input );
            side_effect.addTextChangedListener ( new TextWatcher () {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    NS_14th_question.medications.get ( getAdapterPosition () )
                            .setSide_effects ( editable.toString () );
                }
            } );
        }
    }

}
