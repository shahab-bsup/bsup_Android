package com.neweraandroid.expandabledemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

public class ChoiceAdapter extends RecyclerView.Adapter<ChoiceAdapter.ViewHolder> {
    private static final int UNSELECTED = -1;

    private RecyclerView recyclerView;
    private int selectedItem = UNSELECTED;
    private Context context;
    private String[] strings;
    private Drawable selected_background;
    private Drawable unselected_background;
    private int selected_text_color;
    private int unselected_text_color;
    private OnChoiceClickListener onChoiceClickListener;
    private List<Boolean> selections = new ArrayList<> ();

    public ChoiceAdapter(RecyclerView recyclerView, Context context) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.strings = context.getResources ()
                .getStringArray ( R.array.question_13_choices );
        selected_background = context.getResources ()
                .getDrawable ( R.drawable.selected_stated );
        unselected_background = context.getResources ()
                .getDrawable ( R.drawable.unselected_state );
        selected_text_color = context.getResources ()
                .getColor ( R.color.colorPrimary );
        unselected_text_color = context.getResources ()
                .getColor ( R.color.colorAccent );
        resetSelections ();
    }

    private void resetSelections() {
        selections.clear ();
        for (int i = 0; i < 8; i++) {
            selections.add ( false );
        }
    }

    public void setOnChoiceClickListener(OnChoiceClickListener
                                                 onChoiceClickListener) {
        this.onChoiceClickListener = onChoiceClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from ( parent.getContext () )
                .inflate ( R.layout.recycler_item,
                        parent,
                        false );
        return new ViewHolder ( itemView );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,
                                 int position) {
        holder.bind ();
    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    public interface OnChoiceClickListener {
        void onChoiceClicked(int choice, int subChoice);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener,
            ExpandableLayout.OnExpansionUpdateListener {
        private ExpandableLayout expandableLayout;
        private TextView expandButton;
        private View subView;
        private TextView not_helping;
        private TextView unsure;
        private TextView helps_a_little;
        private TextView helps_a_lot;
        private OnSubChoiceClickListener onSubChoicesClickListener;

        public ViewHolder(View itemView) {
            super ( itemView );
            expandableLayout = itemView.findViewById ( R.id.expandable_layout );
            expandableLayout.setInterpolator ( new OvershootInterpolator () );
            expandableLayout.setOnExpansionUpdateListener ( this );
            expandButton = itemView.findViewById ( R.id.expand_button );
            expandButton.setOnClickListener ( this );
            subView = itemView.findViewById ( R.id.subchoices_row );
            onSubChoicesClickListener = new OnSubChoiceClickListener ();
            not_helping = subView.findViewById ( R.id.not_helping );
            not_helping.setId ( (int) 1 );
            not_helping.setOnClickListener ( onSubChoicesClickListener );
            unsure = subView.findViewById ( R.id.unsure );
            unsure.setId ( (int) 2 );
            unsure.setOnClickListener ( onSubChoicesClickListener );
            helps_a_little = subView.findViewById ( R.id.helps_a_little );
            helps_a_little.setId ( (int) 3 );
            helps_a_little.setOnClickListener ( onSubChoicesClickListener );
            helps_a_lot = subView.findViewById ( R.id.helps_a_lot );
            helps_a_lot.setId ( (int) 4 );
            helps_a_lot.setOnClickListener ( onSubChoicesClickListener );
        }

        public void bind() {
            int position = getAdapterPosition ();
            if (selections.get ( position )) {
                expandableLayout.setExpanded ( true );
            } else {
                expandableLayout.setExpanded ( false );
            }
            boolean isSelected = position == selectedItem;

            expandButton.setText ( strings[position] );
//            expandButton.setSelected ( isSelected );
//            expandableLayout.setExpanded ( isSelected, false );
        }

        @Override
        public void onClick(View view) {
            ViewHolder holder = (ViewHolder) recyclerView
                    .findViewHolderForAdapterPosition ( selectedItem );
//                if (holder != null) {
//                    holder.expandButton.setSelected ( false );
//                    holder.expandableLayout.collapse ();
//                }
//
//                int position = getAdapterPosition ();
//                if (position == selectedItem) {
//                    selectedItem = UNSELECTED;
//                } else {
//                    expandButton.setSelected ( true );
//                    expandableLayout.expand ();
//                    selectedItem = position;
//                }
            onChoiceClickListener.onChoiceClicked ( getAdapterPosition (),
                    -1 );
            if (expandableLayout.isExpanded ()) {
                expandableLayout.collapse ( true );
                selections.set ( getAdapterPosition (), false );
            } else {
                expandableLayout.expand ( true );
                selections.set ( getAdapterPosition (), false );
            }
        }

        @Override
        public void onExpansionUpdate(float expansionFraction,
                                      int state) {
            if (state == ExpandableLayout.State.EXPANDING) {
                recyclerView.smoothScrollToPosition ( getAdapterPosition () );
            }
        }

        private class OnSubChoiceClickListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                myOwnNotifyDataSetChanged ( v.getId () );
                onChoiceClickListener.onChoiceClicked ( getAdapterPosition (),
                        v.getId () );
            }

            private void myOwnNotifyDataSetChanged(int resId) {
                for (int i = 1; i <= 4; i++) {
                    if (i == resId) {
                        ((TextView) itemView.findViewById ( i ))
                                .setTextColor ( selected_text_color );
                    } else {
                        ((TextView) itemView.findViewById ( i ))
                                .setTextColor ( unselected_text_color );
                    }
                }
            }
        }
    }
}