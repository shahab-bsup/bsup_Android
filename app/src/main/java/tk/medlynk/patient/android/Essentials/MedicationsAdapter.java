package tk.medlynk.patient.android.Essentials;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

import tk.medlynk.patient.android.Model.Medication;

/**
 * Created by Shahab on 3/24/2018.
 */

public class MedicationsAdapter extends RecyclerView.Adapter<MedicationsAdapter.MedicationVH> {

    private Context context;
    private List<Medication> medications;
    private boolean firstViewFocus = false;
    private OnEmptyMedicationListener onEmptyMedicationListener;

    public MedicationsAdapter(Context context) {
        this.context = context;
        medications = new ArrayList<> ();
    }

    public void setOnEmptyMedicationListener(OnEmptyMedicationListener onEmptyMedicationListener) {
        this.onEmptyMedicationListener = onEmptyMedicationListener;
    }

    @Override
    public MedicationVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.medication_row, parent, false );
        return new MedicationVH ( view );
    }

    public void clearDataSet(){
        this.medications.clear ();
        notifyDataSetChanged ();
    }

    @Override
    public void onBindViewHolder(MedicationVH holder, int position) {
        int medication_numer = position + 1;
        holder.medication_number.setText ( "Medication #" + medication_numer );
        holder.medication_name.setText ( medications.get ( position ).getName () );
        if( medications.get ( position ).isNameError () ){
            holder.medication_name_error.setVisibility ( View.VISIBLE );
            setViewRequestFocus ( holder.medication_name_error, position );
        }else {
            holder.medication_name_error.setVisibility ( View.GONE );
        }

        if( medications.get ( position ).isFrequentlyError () ){
            holder.how_often_error.setVisibility ( View.VISIBLE );
            setViewRequestFocus ( holder.how_often_error, position);
        }else{
            holder.how_often_error.setVisibility ( View.GONE );
        }

        if( medications.get ( position ).isHelpfullyError () ){
            holder.how_helpful_error.setVisibility ( View.VISIBLE );
            setViewRequestFocus ( holder.how_helpful_error, position);
        }else{
            holder.how_helpful_error.setVisibility ( View.GONE );
        }

        if( medications.get ( position ).isSideEffectError () ){
            holder.side_effect_error.setVisibility ( View.VISIBLE );
            setViewRequestFocus ( holder.side_effect_error, position);
        }else{
            holder.side_effect_error.setVisibility ( View.GONE );
        }

        if(!TextUtils.isEmpty ( medications.get ( position ).getFrequently () )){
            switch (medications.get ( position ).getFrequently ()){
                case "1":{
                    holder.how_often_per_directions.setBackgroundResource ( R.drawable.selected_state );
                    holder.how_often_often.setBackgroundResource ( R.drawable.unselected_state );
                    holder.how_often_occasionally.setBackgroundResource ( R.drawable.unselected_state );
                    break;
                }
                case "2":{
                    holder.how_often_per_directions.setBackgroundResource ( R.drawable.unselected_state );
                    holder.how_often_often.setBackgroundResource ( R.drawable.selected_state );
                    holder.how_often_occasionally.setBackgroundResource ( R.drawable.unselected_state );

                    break;
                }
                case "3":{
                    holder.how_often_per_directions.setBackgroundResource ( R.drawable.unselected_state );
                    holder.how_often_often.setBackgroundResource ( R.drawable.unselected_state );
                    holder.how_often_occasionally.setBackgroundResource ( R.drawable.selected_state );

                    break;
                }
            }
        }

        if( !TextUtils.isEmpty ( medications.get ( position ).getHelpfully () ) ){
            switch (medications.get ( position ).getHelpfully ()){
                case "1":{
                    holder.how_helpful_lot.setBackgroundResource ( R.drawable.selected_state );
                    holder.how_helpful_a_little.setBackgroundResource ( R.drawable.unselected_state );
                    holder.how_helpful_not_helping.setBackgroundResource ( R.drawable.unselected_state );

                    break;
                }
                case "2":{
                    holder.how_helpful_lot.setBackgroundResource ( R.drawable.unselected_state );
                    holder.how_helpful_a_little.setBackgroundResource ( R.drawable.selected_state );
                    holder.how_helpful_not_helping.setBackgroundResource ( R.drawable.unselected_state );

                    break;
                }
                case "3":{
                    holder.how_helpful_lot.setBackgroundResource ( R.drawable.unselected_state );
                    holder.how_helpful_a_little.setBackgroundResource ( R.drawable.unselected_state );
                    holder.how_helpful_not_helping.setBackgroundResource ( R.drawable.selected_state );

                    break;
                }
            }
        }

        if( !holder.has_side_effect ){
            holder.side_effect.setText ( medications.get ( position ).getSideEffects () );
        }else{
            holder.side_effect.setText ( "" );
        }

    }

    @Override
    public void onViewAttachedToWindow(MedicationVH holder) {
        super.onViewAttachedToWindow ( holder );
    }

    private void setViewRequestFocus(TextView textView, int position) {
        if (!firstViewFocus) {
            firstViewFocus = true;
        }
    }

    public List<Medication> getDataSet() {
        return this.medications;
    }

    @Override
    public int getItemCount() {
        return medications.size ();
    }

    public void setMedications(Medication medication) {
        this.medications.add ( medication );
        notifyDataSetChanged ();
    }

    public class MedicationVH extends RecyclerView.ViewHolder {

        Button delete, no_side_effect;
        EditText medication_name, side_effect;
        Button how_often_per_directions;
        Button how_often_often;
        Button how_often_occasionally;
        Button how_helpful_lot;
        Button how_helpful_a_little;
        Button how_helpful_not_helping;
        List<Button> options = new ArrayList<> ();
        boolean has_side_effect = false;
        TextView medication_name_error;
        TextView side_effect_error;
        TextView how_often_error;
        TextView how_helpful_error;
        TextView medication_number;

        public MedicationVH(View itemView) {
            super ( itemView );
            System.out.println ( "MedicationVH.MedicationVH" );
            medication_number = itemView.findViewById ( R.id.number_of_medication );
            delete = itemView.findViewById ( R.id.btnDelete );
            delete.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    for (Button option : options) {
                        option.setBackgroundResource ( R.drawable.unselected_state );
                    }
                    medications.remove ( getAdapterPosition () );
                    if( medications.size () == 0 ){
                        onEmptyMedicationListener.onEmptyMedication ();
                    }
                    notifyDataSetChanged ();
                }
            } );
            no_side_effect = itemView.findViewById ( R.id.btnNoSideEffect );
            options.add ( no_side_effect );
            no_side_effect.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    has_side_effect = !has_side_effect;
                    if (has_side_effect) {
                        no_side_effect.setBackgroundResource ( R.drawable.selected_state );
                        medications.get ( getAdapterPosition () ).setSideEffects ( side_effect.
                                getText ().toString () );
                        medications.get ( getAdapterPosition () ).setSideEffects ( null );
                    } else {
                        no_side_effect.setBackgroundResource ( R.drawable.unselected_state );
                        medications.get ( getAdapterPosition () ).setSideEffects ( "" );
                    }
                }
            } );
            medication_name = itemView.findViewById ( R.id.medication_name );
            medication_name.addTextChangedListener ( new TextWatcher () {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length () > 0) {
                        medications.get ( getAdapterPosition () ).setName ( editable.toString () );
                    } else {
                        medications.get ( getAdapterPosition () ).setName ( "" );
                    }
                }
            } );
            side_effect = itemView.findViewById ( R.id.side_effect );
            how_often_per_directions = itemView.findViewById ( R.id.how_often_per_direction );
            options.add ( how_often_per_directions );
            how_often_per_directions.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    System.out.println ( "how_often_per_directions.setOnClickListener" );
                    if( medications.get ( getAdapterPosition () ).getFrequently ().equals ( "1" ) ){
                        how_often_per_directions.setBackgroundResource ( R.drawable.unselected_state );
                        how_often_often.setBackgroundResource ( R.drawable.unselected_state );
                        how_often_occasionally.setBackgroundResource ( R.drawable.unselected_state );
                        medications.get ( getAdapterPosition () ).setFrequently ( "" );
                    }else{
                        how_often_per_directions.setBackgroundResource ( R.drawable.selected_state );
                        how_often_often.setBackgroundResource ( R.drawable.unselected_state );
                        how_often_occasionally.setBackgroundResource ( R.drawable.unselected_state );
                        medications.get ( getAdapterPosition () ).setFrequently ( "1" );
                    }
                }
            } );
            how_often_often = itemView.findViewById ( R.id.how_often_per_often );
            options.add ( how_often_often );
            how_often_often.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    if( medications.get ( getAdapterPosition () ).getFrequently ().equals ( "2" ) ){
                        how_often_per_directions.setBackgroundResource ( R.drawable.unselected_state );
                        how_often_often.setBackgroundResource ( R.drawable.unselected_state );
                        how_often_occasionally.setBackgroundResource ( R.drawable.unselected_state );
                        medications.get ( getAdapterPosition () ).setFrequently ( "" );
                    }else{
                        how_often_per_directions.setBackgroundResource ( R.drawable.unselected_state );
                        how_often_often.setBackgroundResource ( R.drawable.selected_state );
                        how_often_occasionally.setBackgroundResource ( R.drawable.unselected_state );
                        medications.get ( getAdapterPosition () ).setFrequently ( "2" );
                    }
                }
            } );
            how_often_occasionally = itemView.findViewById ( R.id.how_often_per_occasionally );
            options.add ( how_often_occasionally );
            how_often_occasionally.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    if( medications.get ( getAdapterPosition () ).getFrequently ().equals ( "3" ) ){
                        how_often_per_directions.setBackgroundResource ( R.drawable.unselected_state );
                        how_often_often.setBackgroundResource ( R.drawable.unselected_state );
                        how_often_occasionally.setBackgroundResource ( R.drawable.unselected_state );
                        medications.get ( getAdapterPosition () ).setFrequently ( "" );
                    }else{
                        how_often_per_directions.setBackgroundResource ( R.drawable.unselected_state );
                        how_often_often.setBackgroundResource ( R.drawable.unselected_state );
                        how_often_occasionally.setBackgroundResource ( R.drawable.selected_state );
                        medications.get ( getAdapterPosition () ).setFrequently ( "3" );
                    }
                }
            } );
            how_helpful_lot = itemView.findViewById ( R.id.how_helpful_helps_a_lot );
            options.add ( how_helpful_lot );
            how_helpful_lot.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    if( medications.get ( getAdapterPosition () ).getHelpfully ().equals ( "1" ) ){
                        how_helpful_lot.setBackgroundResource ( R.drawable.unselected_state );
                        how_helpful_a_little.setBackgroundResource ( R.drawable.unselected_state );
                        how_helpful_not_helping.setBackgroundResource ( R.drawable.unselected_state );
                        medications.get ( getAdapterPosition () ).setHelpfully ( "" );
                    }else{
                        how_helpful_lot.setBackgroundResource ( R.drawable.selected_state );
                        how_helpful_a_little.setBackgroundResource ( R.drawable.unselected_state );
                        how_helpful_not_helping.setBackgroundResource ( R.drawable.unselected_state );
                        medications.get ( getAdapterPosition () ).setHelpfully ( "1" );
                    }
                }
            } );
            how_helpful_a_little = itemView.findViewById ( R.id.how_helpful_helps_a_little );
            options.add ( how_helpful_a_little );
            how_helpful_a_little.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    if( medications.get ( getAdapterPosition () ).getHelpfully ().equals ( "2" ) ){
                        how_helpful_lot.setBackgroundResource ( R.drawable.unselected_state );
                        how_helpful_a_little.setBackgroundResource ( R.drawable.unselected_state );
                        how_helpful_not_helping.setBackgroundResource ( R.drawable.unselected_state );
                        medications.get ( getAdapterPosition () ).setHelpfully ( "" );
                    }else{
                        how_helpful_lot.setBackgroundResource ( R.drawable.unselected_state );
                        how_helpful_a_little.setBackgroundResource ( R.drawable.selected_state );
                        how_helpful_not_helping.setBackgroundResource ( R.drawable.unselected_state );
                        medications.get ( getAdapterPosition () ).setHelpfully ( "2" );
                    }
                }
            } );
            how_helpful_not_helping = itemView.findViewById ( R.id.how_helpful_not_helping );
            options.add ( how_helpful_not_helping );
            how_helpful_not_helping.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    if( medications.get ( getAdapterPosition () ).getHelpfully ().equals ( "3" ) ){
                        how_helpful_lot.setBackgroundResource ( R.drawable.unselected_state );
                        how_helpful_a_little.setBackgroundResource ( R.drawable.unselected_state );
                        how_helpful_not_helping.setBackgroundResource ( R.drawable.unselected_state );
                        medications.get ( getAdapterPosition () ).setHelpfully ( "" );
                    }else{
                        how_helpful_lot.setBackgroundResource ( R.drawable.unselected_state );
                        how_helpful_a_little.setBackgroundResource ( R.drawable.unselected_state );
                        how_helpful_not_helping.setBackgroundResource ( R.drawable.selected_state );
                        medications.get ( getAdapterPosition () ).setHelpfully ( "3" );
                    }
                }
            } );
            medication_name_error = itemView.findViewById ( R.id.medication_name_error );
            side_effect_error = itemView.findViewById ( R.id.side_effect_error );
            how_often_error = itemView.findViewById ( R.id.how_often_error );
            how_helpful_error = itemView.findViewById ( R.id.how_helpful_error );

        }
    }

    public interface OnEmptyMedicationListener{
        void onEmptyMedication();
    }

}
