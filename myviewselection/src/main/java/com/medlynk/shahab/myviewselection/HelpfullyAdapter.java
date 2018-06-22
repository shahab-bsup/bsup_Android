package com.medlynk.shahab.myviewselection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Shahab on 6/22/2018.
 */

public class HelpfullyAdapter extends RecyclerView.Adapter<HelpfullyAdapter.HelpfullyViewHolder> implements HelpfullyOptionsAdapter.OnSingleOptionClickListener {

    private final static String TAG = HelpfullyAdapter.class.getSimpleName ();
    private Context context;
    private List<String> strings = new LinkedList<> ();
    private SelectionPolicy selectionPolicy;
    private ViewSelection.OnMultiItemSelectedListener onMultiItemSelectedListener;
    private ViewSelection.OnHelpfullyOptionClickListener onHelpfullyOptionClickListener;
    private List<Boolean> selections = new LinkedList<> ();
    private Map<Integer, Integer> errorVisibilityStatus = new HashMap<> ();
    private ViewSelection viewSelection;
    private int adapterPosition;

    public HelpfullyAdapter(Context context,
                            SelectionPolicy selectionPolicy) {
        this.context = context;
        this.selectionPolicy = selectionPolicy;
    }

    public void setDataSet(String[] strings) {
        for (String string : strings) {
            this.strings.add ( string );
        }
        initializeSelections ( strings.length );
        notifyDataSetChanged ();
    }

    private void initializeSelections(int numOfViews) {
        for (int i = 0; i < numOfViews; i++) {
            selections.add ( false );
            errorVisibilityStatus.put ( i, View.GONE );
        }
    }

    public void clearSelectionHistory() {
        int size = selections.size ();
        for (int i = 0; i < size; i++) {
            selections.set ( i, false );
            errorVisibilityStatus.put ( i, View.GONE );
        }
        notifyDataSetChanged ();
    }

    @NonNull
    @Override
    public HelpfullyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( context )
                .inflate ( R.layout.helpfully_row, parent, false );
        return new HelpfullyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull HelpfullyViewHolder holder,
                                 int position) {
        holder.answerChoice.setText ( strings.get ( position ) );
        holder.answerChoice.setId ( position );
        if (selections.get ( position )) {
            holder.answerChoice.setBackgroundResource ( selectionPolicy.selected_state_resId );
            holder.answerChoice.setTextColor ( selectionPolicy.selected_text_color_resId );
        } else {
            holder.answerChoice.setBackgroundResource ( selectionPolicy.unselected_state_resId );
            holder.answerChoice.setTextColor ( selectionPolicy.unselected_text_color_resId );
        }

        if (errorVisibilityStatus.get ( position ) == View.VISIBLE) {
            holder.selectionError.setVisibility ( View.VISIBLE );
        } else {
            holder.selectionError.setVisibility ( View.GONE );
        }

        holder.answerChoice
                .setOnClickListener ( new OnHelpFullyClickListener ( holder,
                        position ) );

    }

    @Override
    public int getItemCount() {
        return strings.size ();
    }

    @Override
    public void onOptionClicked(int position) {
        Log.d ( TAG, "onOptionClicked choice = " + adapterPosition +
                " helpfully = " + position );
        onHelpfullyOptionClickListener.onHelpfullyClicked ( adapterPosition,
                position );
    }

    public void setOnMultiItemSelectedListener(ViewSelection viewSelection,
                                               ViewSelection.OnMultiItemSelectedListener
                                                       onMultiItemSelectedListener) {
        this.viewSelection = viewSelection;
        this.onMultiItemSelectedListener = onMultiItemSelectedListener;

    }

    public void setOnHelpfullyClickListener(ViewSelection.OnHelpfullyOptionClickListener
                                                    onHelpfullyOptionClickListener) {
        this.onHelpfullyOptionClickListener = onHelpfullyOptionClickListener;
    }

    public void presentHelpfullyError(int position, int visibility_status) {
        errorVisibilityStatus.put ( position, visibility_status );
        notifyDataSetChanged ();
    }

    public final class HelpfullyViewHolder extends RecyclerView.ViewHolder {

        private final TextView answerChoice;
        private final TextView selectionError;
        private final HelpfullyOptionsAdapter adapter;
        private RecyclerView optionsRecyclerView;

        public HelpfullyViewHolder(View itemView) {
            super ( itemView );
            selectionError = itemView.findViewById ( R.id.helpfully_error );
            answerChoice = itemView.findViewById ( R.id.txtAnswerChoice );
            optionsRecyclerView = itemView
                    .findViewById ( R.id.optionRecyclerView );
            optionsRecyclerView
                    .setLayoutManager ( new LinearLayoutManager ( context,
                            LinearLayoutManager.HORIZONTAL, false ) );
            adapter = new HelpfullyOptionsAdapter ( context, selectionPolicy );
            adapter.setOnSingleOptionClickListener ( HelpfullyAdapter.this );
            optionsRecyclerView.setAdapter ( adapter );
        }
    }

    private final class OnHelpFullyClickListener implements View.OnClickListener {

        int adapterPosition;
        private HelpfullyViewHolder holder;

        public OnHelpFullyClickListener(HelpfullyViewHolder holder, int position) {
            this.adapterPosition = position;
            this.holder = holder;
        }

        @Override
        public void onClick(View v) {
            if (selections.get ( adapterPosition )) {
                holder.optionsRecyclerView.setVisibility ( View.GONE );
                selections.set ( adapterPosition, false );
                onMultiItemSelectedListener.onMultiItemDeselected ( viewSelection,
                        adapterPosition );
            } else {
                holder.optionsRecyclerView.setVisibility ( View.VISIBLE );
                holder.adapter.notifyDataSetChanged ();
                selections.set ( adapterPosition, true );
                onMultiItemSelectedListener.onMultiItemSelected ( viewSelection,
                        adapterPosition );
            }
            notifyDataSetChanged ();
        }
    }
}
