package tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_14;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shahab on 3/29/2018.
 */

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.VH> {

    private String[] string_choices;
    private Context context;
    private int selected_color;
    private int unselected_color;
    private List<String> subChoices;
    private String kind_adapter;
    private  OnOptionsClickListener onOptionsClickListener;

    public ButtonAdapter(Context context, String TAG) {
        this.context = context;
        selected_color = context.getResources ().getColor ( R.color.white );
        unselected_color = context.getResources ().getColor ( R.color.colorPrimary );
        subChoices = new ArrayList<> (  );
        kind_adapter = TAG;
        if( kind_adapter.equals ( "Imaging Part" ) ){
            string_choices = context.getResources ().
                    getStringArray ( R.array.FUpS_14th_first_sub_choices);
        }else if( kind_adapter.equals ( "Cardiac Part" ) ){
            string_choices = context.getResources ().
                    getStringArray ( R.array.FUpS_14th_second_sub_choices);
        }
    }

    public void setOnOptionsClickListener(OnOptionsClickListener onOptionsClickListener) {
        this.onOptionsClickListener = onOptionsClickListener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.single_button,
                parent, false );
        return new VH ( view );
    }

    public void clearDataSet(){
        this.subChoices.clear ();
        notifyDataSetChanged ();
    }

    public List<String> getSubChoices(){
        return this.subChoices;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.button.setText ( string_choices[position] );
        if( subChoices.size () == 0 ){
            holder.button.setBackgroundDrawable ( context.getResources ().getDrawable ( R.drawable.unselected_state ) );
            holder.button.setTextColor ( unselected_color );
        }
    }

    @Override
    public int getItemCount() {
        if( kind_adapter.equals ( "Imaging Part" ) )
            return 6;
        else if( kind_adapter.equals ( "Cardiac Part" ) )
            return 4;
        return 0;
    }

    public class VH extends RecyclerView.ViewHolder{

        private Button button;

        public VH(View itemView) {
            super ( itemView );
            button = itemView.findViewById ( R.id.button );
            button.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    switch (getAdapterPosition ()){
                        case 0:{
                            if( !subChoices.contains ( "1" ) ){
                                subChoices.add ( "1" );
                                button.setBackgroundDrawable ( context.getResources ().getDrawable ( R.drawable.selected_state ) );
                                button.setTextColor ( selected_color );
                                optionClicked();
                            }else{
                                subChoices.remove ( "1" );
                                button.setBackgroundDrawable ( context.getResources ().getDrawable ( R.drawable.unselected_state ) );
                                button.setTextColor ( unselected_color );
                            }
                            break;
                        }
                        case 1:{
                            if( !subChoices.contains ( "2" ) ){
                                subChoices.add ( "2" );
                                button.setBackgroundDrawable ( context.getResources ().getDrawable ( R.drawable.selected_state ) );
                                button.setTextColor ( selected_color );
                                optionClicked();
                            }else{
                                subChoices.remove ( "2" );
                                button.setBackgroundDrawable ( context.getResources ().getDrawable ( R.drawable.unselected_state ) );
                                button.setTextColor ( unselected_color );
                            }

                            break;
                        }
                        case 2:{
                            if( !subChoices.contains ( "3" ) ){
                                subChoices.add ( "3" );
                                button.setBackgroundDrawable ( context.getResources ().getDrawable ( R.drawable.selected_state ) );
                                button.setTextColor ( selected_color );
                                optionClicked();

                            }else{
                                subChoices.remove ( "3" );
                                button.setBackgroundDrawable ( context.getResources ().getDrawable ( R.drawable.unselected_state ) );
                                button.setTextColor ( unselected_color );
                            }

                            break;
                        }
                        case 3:{
                            if( !subChoices.contains ( "4" ) ){
                                subChoices.add ( "4" );
                                button.setBackgroundDrawable ( context.getResources ().getDrawable ( R.drawable.selected_state ) );
                                button.setTextColor ( selected_color );
                                optionClicked();

                            }else{
                                subChoices.remove ( "4" );
                                button.setBackgroundDrawable ( context.getResources ().getDrawable ( R.drawable.unselected_state ) );
                                button.setTextColor ( unselected_color );
                            }

                            break;
                        }
                        case 4:{
                            if( !subChoices.contains ( "5" ) ){
                                subChoices.add ( "5" );
                                button.setBackgroundDrawable ( context.getResources ().getDrawable ( R.drawable.selected_state ) );
                                button.setTextColor ( selected_color );
                                optionClicked();

                            }else{
                                subChoices.remove ( "5" );
                                button.setBackgroundDrawable ( context.getResources ().getDrawable ( R.drawable.unselected_state ) );
                                button.setTextColor ( unselected_color );
                            }

                            break;
                        }
                        case 5:{
                            if( !subChoices.contains ( "6" ) ){
                                subChoices.add ( "6" );
                                button.setBackgroundDrawable ( context.getResources ().getDrawable ( R.drawable.selected_state ) );
                                button.setTextColor ( selected_color );
                                optionClicked();

                            }else{
                                subChoices.remove ( "6" );
                                button.setBackgroundDrawable ( context.getResources ().getDrawable ( R.drawable.unselected_state ) );
                                button.setTextColor ( unselected_color );
                            }

                            break;
                        }
                    }
                }
            } );
        }
    }

    private void optionClicked() {
        if( kind_adapter.equals ( "Imaging Part" ) ){
            onOptionsClickListener.onImagingOptionsClicked ();
        }else if ( kind_adapter.equals ( "Cardiac Part" ) ){
            onOptionsClickListener.onCardiacOptionsClicked ();
        }
    }

    public interface OnOptionsClickListener{
        void onImagingOptionsClicked();
        void onCardiacOptionsClicked();
    }

}
