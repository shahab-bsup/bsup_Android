package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.neweraandroid.demo.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import tk.medlynk.patient.android.Model.Answer;

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
    private HashMap<Integer, Integer> subChoices = new HashMap<> ();
    private List<Boolean> selections = new ArrayList<> ();
    private List<Integer> subSelections = new ArrayList<> ();
    private List<Answer> answers = new ArrayList<> ();
    private List<Boolean> isSubChoiceSelected = new ArrayList<> ();

    public ChoiceAdapter(Context context, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.strings = context.getResources ()
                .getStringArray ( R.array.question_13_choices );
        selected_background = context.getResources ()
                .getDrawable ( R.drawable.answer_selected );
        unselected_background = context.getResources ()
                .getDrawable ( R.drawable.answer_not_selected );
        selected_text_color = context.getResources ()
                .getColor ( R.color.white );
        unselected_text_color = context.getResources ()
                .getColor ( R.color.colorPrimary );
        resetSelections ();
    }

    private void resetSelections() {
        selections.clear ();
        subSelections.clear ();
        isSubChoiceSelected.clear ();
        for (int i = 0; i < 7; i++) {
            selections.add ( false );
            isSubChoiceSelected.add ( false );
            subChoices.put ( i, -1 );
        }
        notifyDataSetChanged ();
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
        holder.expandButton.setText ( strings[position] );
        if (!isSubChoiceSelected.get ( position )) {
            holder.error_text.setVisibility ( View.VISIBLE );
        } else {
            holder.error_text.setVisibility ( View.GONE );
        }

        if (selections.get ( position )) {
            holder.expandButton.setBackground ( selected_background );
            holder.expandButton.setTextColor ( selected_text_color );
            holder.expandableLayout.setExpanded ( true );
        } else {
            holder.expandButton.setBackground ( unselected_background );
            holder.expandButton.setTextColor ( unselected_text_color );
            holder.expandableLayout.setExpanded ( false );
        }

        if( subChoices.get ( position ) != -1 ){
            holder.myOwnNotifyDataSetChanged ( subChoices.get ( position ) );
        }
    }

    public void setDataSet(List<Answer> answers) {
        this.answers = answers;
        reCreateSelections ( answers );
        notifyDataSetChanged ();
    }


    private void reCreateSelections(List<Answer> answers) {
        Iterator<Answer> answerIterator = answers.iterator ();
        while (answerIterator.hasNext ()) {
            Answer answer = answerIterator.next ();
            switch (answer.getChoice ()) {
                case "a": {
                    selections.set ( 0, true );
                    isSubChoiceSelected.set ( 0, true );
                    subChoices.put ( 0, answer.getHelpfully () );
                    break;
                }
                case "b": {
                    selections.set ( 1, true );
                    isSubChoiceSelected.set ( 1, true );
                    subChoices.put ( 1, answer.getHelpfully () );

                    break;
                }
                case "c": {
                    selections.set ( 2, true );
                    isSubChoiceSelected.set ( 2, true );
                    subChoices.put ( 2, answer.getHelpfully () );
                    break;
                }
                case "d": {
                    selections.set ( 3, true );
                    isSubChoiceSelected.set ( 3, true );
                    subChoices.put ( 3, answer.getHelpfully () );

                    break;
                }
                case "e": {
                    selections.set ( 4, true );
                    isSubChoiceSelected.set ( 4, true );
                    subChoices.put ( 4, answer.getHelpfully () );

                    break;
                }
                case "f": {
                    selections.set ( 5, true );
                    isSubChoiceSelected.set ( 5, true );
                    subChoices.put ( 5, answer.getHelpfully () );

                    break;
                }
                case "g": {
                    selections.set ( 6, true );
                    isSubChoiceSelected.set ( 6, true );
                    subChoices.put ( 6, answer.getHelpfully () );

                    break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    public void clearDataSet() {
        answers.clear ();
        resetSelections ();
        onChoiceClickListener.onClearDataSet ();
    }

    public void presentHelpfullyError(int position,
                                      int visibilityStatus) {
        switch (visibilityStatus) {
            case View.GONE: {
                isSubChoiceSelected.set ( position, true );

                break;
            }
            case View.VISIBLE: {
                isSubChoiceSelected.set ( position, false );

                break;
            }
        }
    }

    public interface OnChoiceClickListener {
        void onChoiceClicked(int choice);

        void onChoiceUnClicked(int choice);

        void onSubChoiceClicked(int choice, int subChoice);

        void onClearDataSet();

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
        private TextView error_text;
        private OnSubChoiceClickListener onSubChoicesClickListener;

        public ViewHolder(View itemView) {
            super ( itemView );
            error_text = itemView.findViewById ( R.id.subChoiceError );
            expandableLayout = itemView.findViewById ( R.id.expandable_layout );
            expandableLayout.setInterpolator ( new OvershootInterpolator () );
            expandableLayout.setOnExpansionUpdateListener ( this );
            expandButton = itemView.findViewById ( R.id.expand_button );
            expandButton.setOnClickListener ( this );
            subView = itemView.findViewById ( R.id.subchoices_row );
            onSubChoicesClickListener = new OnSubChoiceClickListener ();
            not_helping = subView.findViewById ( R.id.not_helping );
            not_helping.setId ( (int) 3 );
            not_helping.setOnClickListener ( onSubChoicesClickListener );
            unsure = subView.findViewById ( R.id.unsure );
            unsure.setId ( (int) 4 );
            unsure.setOnClickListener ( onSubChoicesClickListener );
            helps_a_little = subView.findViewById ( R.id.helps_a_little );
            helps_a_little.setId ( (int) 2 );
            helps_a_little.setOnClickListener ( onSubChoicesClickListener );
            helps_a_lot = subView.findViewById ( R.id.helps_a_lot );
            helps_a_lot.setId ( (int) 1 );
            helps_a_lot.setOnClickListener ( onSubChoicesClickListener );
        }

        @Override
        public void onClick(View view) {
            if (expandableLayout.isExpanded ()) {
                expandableLayout.collapse ( true );
                selections.set ( getAdapterPosition (), false );
                expandButton.setBackground ( unselected_background );
                expandButton.setTextColor ( unselected_text_color );
                onChoiceClickListener.onChoiceUnClicked ( getAdapterPosition () );
            } else {
                onChoiceClickListener.onChoiceClicked ( getAdapterPosition () );
                expandableLayout.expand ( true );
                selections.set ( getAdapterPosition (), true );
                expandButton.setBackground ( selected_background );
                expandButton.setTextColor ( selected_text_color );
            }
        }

        @Override
        public void onExpansionUpdate(float expansionFraction,
                                      int state) {
            if (state == ExpandableLayout.State.EXPANDING) {
                recyclerView.smoothScrollToPosition ( getAdapterPosition () );
            }
        }

        public void myOwnNotifyDataSetChanged(int resId) {
            for (int i = 1; i <= 4; i++) {
                if (i == resId) {
                    ((TextView) itemView.findViewById ( i ))
                            .setTextColor ( context.getResources ()
                                    .getColor ( R.color.colorPrimaryDark ) );
                } else {
                    ((TextView) itemView.findViewById ( i ))
                            .setTextColor ( unselected_text_color );
                }
            }
        }

        private class OnSubChoiceClickListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                isSubChoiceSelected.set ( getAdapterPosition (), true );
                subChoices.put ( getAdapterPosition (), v.getId () );
                error_text.setVisibility ( View.GONE );
                myOwnNotifyDataSetChanged ( v.getId () );
                onChoiceClickListener.onSubChoiceClicked ( getAdapterPosition (),
                        v.getId () );
            }
        }
    }

}