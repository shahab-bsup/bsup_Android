package com.medlynk.shahab.myviewselection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Shahab on 6/22/2018.
 */

public class HelpfullyOptionsAdapter extends
        RecyclerView.Adapter<HelpfullyOptionsAdapter.ViewHolder> {

    private String[] strings;
    private Context context;
    private SelectionPolicy selectionPolicy;
    private List<Boolean> selections = new LinkedList<> ();
    private List<Boolean> isSelectedOrNot = new LinkedList<> (  );
    private OnSingleOptionClickListener onSingleOptionClickListener;

    public void setOnSingleOptionClickListener(OnSingleOptionClickListener
                                                       onSingleOptionClickListener) {
        this.onSingleOptionClickListener = onSingleOptionClickListener;
    }

    public HelpfullyOptionsAdapter(Context context,
                                   SelectionPolicy selectionPolicy) {
        this.context = context;
        this.strings = context
                .getResources ()
                .getStringArray ( R.array.helpfully_options );
        initializeSelections ( strings.length );
        this.selectionPolicy = selectionPolicy;
    }

    private void initializeSelections(int numOfViews) {
        for (int i = 0; i < numOfViews; i++) {
            selections.add ( false );
            isSelectedOrNot.add ( false );
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( context )
                .inflate ( R.layout.helpfully_options_row,
                parent, false);
        return new ViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText ( strings[position] );
        if( selections.get ( position ) ){
            holder.textView
                    .setTextColor ( selectionPolicy.selected_text_color_resId );
        }else{
            holder.textView
                    .setTextColor ( selectionPolicy.unselected_text_color_resId );
        }
        holder.textView
                .setOnClickListener ( new OnHelpFullyOptionsClickListener(position) );
    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    public final class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;

        public ViewHolder(View itemView) {
            super ( itemView );
            textView = itemView.findViewById ( R.id.txtViewOption );
        }
    }

    public void refreshSelections(int adapterPosition,
                                  boolean single_select) {
        if( single_select ){
            int size = selections.size ();
            for (int i = 0; i < size; i++) {
                if (i != adapterPosition)
                    selections.set ( i, false );
            }
        }else{
            this.selections.set ( adapterPosition, true );
        }
        notifyDataSetChanged ();
    }

    private class OnHelpFullyOptionsClickListener implements View.OnClickListener {

        int adapterPosition;

        public OnHelpFullyOptionsClickListener(int position) {
            this.adapterPosition = position;
        }

        @Override
        public void onClick(View v) {
            selections.set ( adapterPosition, true );
            refreshSelections ( adapterPosition, true );
            onSingleOptionClickListener.onOptionClicked ( adapterPosition );
        }
    }

    public interface OnSingleOptionClickListener{
        void onOptionClicked(int position);
    }

}
