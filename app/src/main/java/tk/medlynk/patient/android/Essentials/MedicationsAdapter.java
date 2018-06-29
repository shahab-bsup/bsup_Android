package tk.medlynk.patient.android.Essentials;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
    private int selected_text_color;
    private int unselected_text_Color;
    private Drawable selected_background;
    private Drawable unselected_background;

    public MedicationsAdapter(Context context) {
        this.context = context;
        medications = new ArrayList<> ();
        selected_background = context.getResources ().getDrawable ( R.drawable.answer_selected );
        unselected_background = context.getResources ().getDrawable ( R.drawable.answer_not_selected );
        selected_text_color = context.getResources ().getColor ( R.color.white );
        unselected_text_Color = context.getResources ().getColor ( R.color.colorPrimary );
    }

    public void setOnEmptyMedicationListener(OnEmptyMedicationListener onEmptyMedicationListener) {
        this.onEmptyMedicationListener = onEmptyMedicationListener;
    }

    public void onUpdateUI(List<Medication> medications) {
        this.medications = medications;

    }

    @Override
    public MedicationVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( context )
                .inflate ( R.layout.medication_row, parent, false );
        return new MedicationVH ( view );
    }

    public void clearDataSet() {
        this.medications.clear ();
        notifyDataSetChanged ();
    }

    @Override
    public void onBindViewHolder(MedicationVH holder, int position) {
        int medication_numer = position + 1;
        holder.medication_number.setText ( "Medication #" + medication_numer );
        holder.medication_name.setText ( medications.get ( position ).getName () );
        if (medications.get ( position ).isNameError ()) {
            holder.medication_name_error.setVisibility ( View.VISIBLE );
            setViewRequestFocus ( holder.medication_name_error, position );
        } else {
            holder.medication_name_error.setVisibility ( View.GONE );
        }

        if (medications.get ( position ).isFrequentlyError ()) {
            holder.how_often_error.setVisibility ( View.VISIBLE );
            setViewRequestFocus ( holder.how_often_error, position );
        } else {
            holder.how_often_error.setVisibility ( View.GONE );
        }

        if (medications.get ( position ).isHelpfullyError ()) {
            holder.how_helpful_error.setVisibility ( View.VISIBLE );
            setViewRequestFocus ( holder.how_helpful_error, position );
        } else {
            holder.how_helpful_error.setVisibility ( View.GONE );
        }

        if (medications.get ( position ).isSideEffectError ()) {
            holder.side_effect_error.setVisibility ( View.VISIBLE );
            setViewRequestFocus ( holder.side_effect_error, position );
        } else {
            holder.side_effect_error.setVisibility ( View.GONE );
        }

        if (medications.get ( position ).getSideEffects () == null) {
            holder.no_side_effect.setTextColor ( selected_text_color );
            holder.no_side_effect.setBackground ( selected_background );
        } else {
            holder.no_side_effect.setTextColor ( unselected_text_Color );
            holder.no_side_effect.setBackground ( unselected_background );
        }

        if (!TextUtils.isEmpty ( medications.get ( position ).getFrequently () )) {
            switch (medications.get ( position ).getFrequently ()) {
                case "1": {
                    holder.oftenNotifyDataSetChanged ( R.id.how_often_per_direction );

                    break;
                }
                case "2": {
                    holder.oftenNotifyDataSetChanged ( R.id.how_often_per_often );

                    break;
                }
                case "3": {
                    holder.oftenNotifyDataSetChanged ( R.id.how_often_per_occasionally );

                    break;
                }
            }
        }

        if (!TextUtils.isEmpty ( medications.get ( position ).getHelpfully () )) {
            switch (medications.get ( position ).getHelpfully ()) {
                case "1": {
                    holder.helpfulNotifyDataSetChanged ( R.id.how_helpful_helps_a_lot );

                    break;
                }
                case "2": {
                    holder.helpfulNotifyDataSetChanged ( R.id.how_helpful_helps_a_little );

                    break;
                }
                case "3": {
                    holder.helpfulNotifyDataSetChanged ( R.id.how_helpful_not_helping );

                    break;
                }
            }
        }

        if (!holder.has_side_effect) {
            holder.side_effect.setText ( medications.get ( position ).getSideEffects () );
        } else {
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

    public interface OnEmptyMedicationListener {
        void onEmptyMedication();
    }

    public class MedicationVH extends RecyclerView.ViewHolder {

        private final TextView how_helpful_unsure;
        Button delete;
        Button no_side_effect;
        EditText medication_name;
        EditText side_effect;
        TextView how_often_per_directions;
        TextView how_often_often;
        TextView how_often_occasionally;
        TextView how_helpful_lot;
        TextView how_helpful_a_little;
        TextView how_helpful_not_helping;
        boolean has_side_effect = false;
        TextView medication_name_error;
        TextView side_effect_error;
        TextView how_often_error;
        TextView how_helpful_error;
        TextView medication_number;

        public MedicationVH(View itemView) {
            super ( itemView );
            medication_number = itemView.findViewById ( R.id.number_of_medication );
            delete = itemView.findViewById ( R.id.btnDelete );
            delete.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    medications.remove ( getAdapterPosition () );
                    if (medications.size () == 0) {
                        onEmptyMedicationListener.onEmptyMedication ();
                    }
                    notifyDataSetChanged ();
                }
            } );
            no_side_effect = itemView.findViewById ( R.id.btnNoSideEffect );
            no_side_effect.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    has_side_effect = !has_side_effect;
                    if (has_side_effect) {
                        no_side_effect.setBackground ( selected_background );
                        no_side_effect.setTextColor ( selected_text_color );
                        medications.get ( getAdapterPosition () ).setSideEffects ( side_effect.
                                getText ().toString () );
                        medications.get ( getAdapterPosition () ).setSideEffects ( null );
                    } else {
                        no_side_effect.setBackground ( unselected_background );
                        no_side_effect.setTextColor ( unselected_text_Color );
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
            how_often_per_directions.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    if (medications.get ( getAdapterPosition () ).getFrequently ().equals ( "1" )) {
                        resetFrequentlyOptions ();
                    } else {
                        oftenNotifyDataSetChanged ( R.id.how_often_per_direction );
                        medications.get ( getAdapterPosition () ).setFrequently ( "1" );
                    }
                }
            } );
            how_often_often = itemView.findViewById ( R.id.how_often_per_often );
            how_often_often.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    if (medications.get ( getAdapterPosition () ).getFrequently ().equals ( "2" )) {
                        resetFrequentlyOptions ();
                    } else {
                        oftenNotifyDataSetChanged ( R.id.how_often_per_often );
                        medications.get ( getAdapterPosition () ).setFrequently ( "2" );
                    }
                }
            } );

            how_often_occasionally = itemView.findViewById ( R.id.how_often_per_occasionally );
            how_often_occasionally.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    if (medications.get ( getAdapterPosition () ).getFrequently ().equals ( "3" )) {
                        resetFrequentlyOptions ();
                    } else {
                        oftenNotifyDataSetChanged ( R.id.how_often_per_occasionally );
                        medications.get ( getAdapterPosition () ).setFrequently ( "3" );
                    }
                }
            } );
            how_helpful_lot = itemView.findViewById ( R.id.how_helpful_helps_a_lot );
            how_helpful_lot.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    if (medications.get ( getAdapterPosition () ).getHelpfully ().equals ( "1" )) {
                        resetHelpfullyOptions ();
                    } else {
                        helpfulNotifyDataSetChanged ( R.id.how_helpful_helps_a_lot );
                        medications.get ( getAdapterPosition () ).setHelpfully ( "1" );
                    }
                }

            } );
            how_helpful_a_little = itemView.findViewById ( R.id.how_helpful_helps_a_little );
            how_helpful_a_little.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    if (medications.get ( getAdapterPosition () ).getHelpfully ().equals ( "2" )) {
                        resetHelpfullyOptions ();
                    } else {
                        helpfulNotifyDataSetChanged ( R.id.how_helpful_helps_a_little );
                        medications.get ( getAdapterPosition () ).setHelpfully ( "2" );
                    }
                }
            } );
            how_helpful_not_helping = itemView.findViewById ( R.id.how_helpful_not_helping );
            how_helpful_not_helping.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    if (medications.get ( getAdapterPosition () ).getHelpfully ().equals ( "3" )) {
                        resetHelpfullyOptions ();
                    } else {
                        helpfulNotifyDataSetChanged ( R.id.how_helpful_not_helping );
                        medications.get ( getAdapterPosition () ).setHelpfully ( "3" );
                    }
                }
            } );
            how_helpful_unsure = itemView.findViewById ( R.id.how_helpful_unsure );
            how_helpful_unsure.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    if (medications.get ( getAdapterPosition () ).getHelpfully ().equals ( "3" )) {
                        resetHelpfullyOptions ();
                    } else {
                        helpfulNotifyDataSetChanged ( R.id.how_helpful_unsure );
                        medications.get ( getAdapterPosition () ).setHelpfully ( "4" );
                    }
                }
            } );
            medication_name_error = itemView.findViewById ( R.id.medication_name_error );
            side_effect_error = itemView.findViewById ( R.id.side_effect_error );
            how_often_error = itemView.findViewById ( R.id.how_often_error );
            how_helpful_error = itemView.findViewById ( R.id.how_helpful_error );
        }

        private void resetHelpfullyOptions() {
            how_helpful_lot.setBackground ( unselected_background );
            how_helpful_a_little.setBackground ( unselected_background );
            how_helpful_not_helping.setBackground ( unselected_background );
            how_helpful_lot.setTextColor ( unselected_text_Color );
            how_helpful_a_little.setTextColor ( unselected_text_Color );
            how_helpful_not_helping.setTextColor ( unselected_text_Color );
            medications.get ( getAdapterPosition () ).setHelpfully ( "" );
        }

        private void resetFrequentlyOptions() {
            how_often_per_directions.setBackground ( unselected_background );
            how_often_often.setBackground ( unselected_background );
            how_often_occasionally.setBackground ( unselected_background );
            how_often_occasionally.setTextColor ( unselected_text_Color );
            how_often_often.setTextColor ( unselected_text_Color );
            how_often_per_directions.setTextColor ( unselected_text_Color );
            medications.get ( getAdapterPosition () ).setFrequently ( "" );
        }

        public void oftenNotifyDataSetChanged(int resId) {
            List<Integer> integers = new ArrayList<> ();
            integers.add ( R.id.how_often_per_direction );
            integers.add ( R.id.how_often_per_often );
            integers.add ( R.id.how_often_per_occasionally );
            for (Integer integer : integers) {
                int i = integer;
                if (i == resId) {
                    ((TextView) itemView.findViewById ( resId )).setTextColor ( selected_text_color );
                    ((TextView) itemView.findViewById ( resId )).setBackground ( selected_background );
                } else {
                    ((TextView) itemView.findViewById ( integer )).setTextColor ( unselected_text_Color );
                    ((TextView) itemView.findViewById ( integer )).setBackground ( unselected_background );
                }
            }
        }


        public void helpfulNotifyDataSetChanged(int resId) {
            List<Integer> integers = new ArrayList<> ();
            integers.add ( R.id.how_helpful_helps_a_lot );
            integers.add ( R.id.how_helpful_helps_a_little );
            integers.add ( R.id.how_helpful_not_helping );
            integers.add ( R.id.how_helpful_unsure );
            for (Integer integer : integers) {
                int i = integer;
                if (i == resId) {
                    ((TextView) itemView.findViewById ( resId )).setTextColor ( selected_text_color );
                    ((TextView) itemView.findViewById ( resId )).setBackground ( selected_background );
                } else {
                    ((TextView) itemView.findViewById ( integer )).setTextColor ( unselected_text_Color );
                    ((TextView) itemView.findViewById ( integer )).setBackground ( unselected_background );
                }
            }
        }
    }
}
