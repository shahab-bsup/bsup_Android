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
 * Created by Shahab on 6/21/2018.
 */

public class RegularAdapter extends RecyclerView.Adapter<RegularAdapter.MyViewHolder> {

    private final static String TAG = RegularAdapter.class.getSimpleName ();
    private Context context;
    private List<String> strings = new LinkedList<> ();
    private SelectionPolicy selectionPolicy;
    private OnViewSingleSelectListener onViewSingleSelectListener;
    private OnViewMultiSelectListener onViewMultiSelectListener;
    private ViewSelection.OnSingleItemSelectedListener onSingleItemSelectedListener;
    private ViewSelection.OnMultiItemSelectedListener onMultiItemSelectedListener;
    private List<Boolean> selections = new LinkedList<> ();
    private ViewSelection viewSelection;

    public RegularAdapter(Context context, SelectionPolicy selectionPolicy) {
        this.context = context;
        this.selectionPolicy = selectionPolicy;
        if (selectionPolicy.isSelectable && selectionPolicy.isSingleSelect) {
            onViewSingleSelectListener = new OnViewSingleSelectListener ( -1 );
        } else if (selectionPolicy.isSelectable && !selectionPolicy.isSingleSelect) {
            onViewMultiSelectListener = new OnViewMultiSelectListener ( -1 );
        }
    }

    public void setDataSet(String[] strings) {
        for (String string : strings) {
            this.strings.add ( string );
        }
        initializeSelections ( strings.length );
        notifyDataSetChanged ();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from ( context )
                .inflate ( R.layout.textview_row, parent, false );

        return new MyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,
                                 int position) {
        holder.textView.setText ( strings.get ( position ) );
        if (selections.get ( position )) {
            holder.textView.setBackgroundResource ( selectionPolicy.selected_state_resId );
            holder.textView.setTextColor ( selectionPolicy.selected_text_color_resId );
        } else {
            holder.textView.setBackgroundResource ( selectionPolicy.unselected_state_resId );
            holder.textView.setTextColor ( selectionPolicy.unselected_text_color_resId );
        }

        if (selectionPolicy.isSelectable) {
            if (selectionPolicy.isSingleSelect) {
                holder.textView.setOnClickListener ( new OnViewSingleSelectListener (
                        position ) );
            } else {
                holder.textView.setOnClickListener ( new OnViewMultiSelectListener (
                        position ) );
            }
        } else {

        }

    }

    public void setSelect(int numOfView) {
        this.selections.set ( numOfView, true );
        notifyDataSetChanged ();
    }

    public void unSelect(int numOfView) {
        this.selections.set ( numOfView, false );
        notifyDataSetChanged ();
    }

    @Override
    public int getItemCount() {
        return strings.size ();
    }

    public void setOnSingleItemSelectedListener(ViewSelection viewSelection,
                                                ViewSelection.OnSingleItemSelectedListener onSingleItemSelectedListener) {
        this.viewSelection = viewSelection;
        this.onSingleItemSelectedListener = onSingleItemSelectedListener;
    }

    public void setOnMultiItemSelectedListener(ViewSelection viewSelection,
                                               ViewSelection.OnMultiItemSelectedListener onMultiItemSelectedListener) {
        this.viewSelection = viewSelection;
        this.onMultiItemSelectedListener = onMultiItemSelectedListener;
    }

    private void initializeSelections(int numOfViews) {
        for (int i = 0; i < numOfViews; i++) {
            selections.add ( false );
        }
    }

    public void clearSelectionHistory() {
        int size = selections.size ();
        for (int i = 0; i < size; i++) {
            selections.set ( i, false );
        }
        notifyDataSetChanged ();
    }

    public void refreshSelections(int adapterPosition,
                                  boolean single_select) {
        if (single_select) {
            int size = selections.size ();
            for (int i = 0; i < size; i++) {
                if (i != adapterPosition)
                    selections.set ( i, false );
            }
        } else {
            this.selections.set ( adapterPosition, true );
        }
        notifyDataSetChanged ();
    }

    public final class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public MyViewHolder(View itemView) {
            super ( itemView );
            textView = itemView.findViewById ( R.id.textview_row );
            textView.setId ( getAdapterPosition () );
        }
    }

    private class OnViewSingleSelectListener implements View.OnClickListener {

        int adapterPosition;

        public OnViewSingleSelectListener(int position) {
            this.adapterPosition = position;
        }

        @Override
        public void onClick(View v) {
            if (!selections.get ( adapterPosition )) {
                selections.set ( adapterPosition, true );
                onSingleItemSelectedListener
                        .onSingleItemSelected ( viewSelection, adapterPosition );
            } else {
                onSingleItemSelectedListener
                        .onSingleItemSelected ( viewSelection, -1 );
                selections.set ( adapterPosition, false );
            }
            refreshSelections ( adapterPosition, selectionPolicy.isSingleSelect );
        }

    }

    private class OnViewMultiSelectListener implements View.OnClickListener {

        private final int adapterPosition;

        public OnViewMultiSelectListener(int position) {
            this.adapterPosition = position;
        }

        @Override
        public void onClick(View v) {
            if (selections.get ( adapterPosition )) {
                selections.set ( adapterPosition, false );
                onMultiItemSelectedListener.onMultiItemDeselected ( viewSelection,
                        adapterPosition );
            } else {
                selections.set ( adapterPosition, true );
                onMultiItemSelectedListener.onMultiItemSelected ( viewSelection,
                        adapterPosition );
            }
            notifyDataSetChanged ();
        }
    }
}
