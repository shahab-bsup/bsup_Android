package com.medlynk.shahab.myviewselection;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Shahab on 2/9/2018...
 */

public class ViewSelection extends LinearLayout {

    List<RelativeLayout> helpfully_layouts = new ArrayList<RelativeLayout> ();
    List<TextView> helpfully_options_errors = new ArrayList<> ();
    int numOfViews = 0;
    Boolean selectable = false;
    HashMap<Integer, Integer> map = new HashMap<> ();
    Boolean single_select = false;
    Boolean button_type = false;
    Boolean edittext_type = false;
    private int selected_state_background;
    private int unselected_state_background;
    private int unselectable_background;
    private int selected_text_color;
    private int unselected_text_color;
    private int unselectable_text_color;
    private OnSingleItemSelectedListener onSingleItemSelectedListener;
    private OnClearStateListener onClearStateListener;
    private OnMultiItemSelectedListener onMultiItemSelectedListener;
    private OnHelpfullyOptionsClickListener onHelpfullyOptionClickListener;
    private Context context;
    private MyAdapter myAdapter;

    public ViewSelection(Context context) {
        super ( context );
    }

    public ViewSelection(Context context, AttributeSet attrs) {
        super ( context, attrs );
        this.context = context;
        makeView ( context, attrs );
    }

    public ViewSelection(Context context, AttributeSet attrs, int defStyleAttr) {
        super ( context, attrs, defStyleAttr );
        this.context = context;
        makeView ( context, attrs );
    }

    public void setOnClearStateListener(OnClearStateListener onClearStateListener) {
        this.onClearStateListener = onClearStateListener;
    }

    public Context getCurrentContext() {
        return context;
    }

    public void setOnMultiItemSelectedListener(OnMultiItemSelectedListener
                                                       onMultiItemSelectedListener) {
        if (onMultiItemSelectedListener == null) {
            throw new RuntimeException ( context.toString () + " must implement " +
                    ViewSelection.OnMultiItemSelectedListener.class.getSimpleName () );
        } else {
            myAdapter.setOnMultiItemSelectedListener ( this, onMultiItemSelectedListener );
        }
    }

    public void setOnSingleItemSelectedListener(OnSingleItemSelectedListener
                                                        onSingleItemSelectedListener) {
        if (onSingleItemSelectedListener == null) {
            throw new RuntimeException ( context.toString () + " must implement " +
                    OnSingleItemSelectedListener.class.getSimpleName () );
        } else {
            myAdapter.setOnSingleItemSelectedListener ( this, onSingleItemSelectedListener );
        }
    }

    public void setSelect(int numOfView) {
        myAdapter.setSelect ( numOfView );
    }

    public void unSelect(int numOfView) {
        myAdapter.unSelect ( numOfView );
    }

    private void makeView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.viewselection_parent_view, this, true );
        LinearLayout linearLayout = view.findViewById ( R.id.parent );
        RecyclerView recyclerView = new RecyclerView ( context );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( context ) );
        TypedArray typedArray = context.
                obtainStyledAttributes ( attrs, R.styleable.ViewSelection, 0, 0 );
        selected_state_background = typedArray.getResourceId ( R.styleable.ViewSelection_selected_background, R.drawable.selected_stated );
        unselected_state_background = typedArray.getResourceId ( R.styleable.ViewSelection_unselected_background, R.drawable.unselected_state );
        unselectable_background = typedArray.getResourceId ( R.styleable.ViewSelection_unselectable_background, R.drawable.unselected_state );
        selected_text_color = typedArray.getInt ( R.styleable.ViewSelection_selected_text_color, R.color.selected_text_clolor );
        unselected_text_color = typedArray.getInt ( R.styleable.ViewSelection_unselected_text_color, R.color.unselected_text_color );
        unselectable_text_color = typedArray.getInt ( R.styleable.ViewSelection_unselectable_text_color, R.color.unselectable_text_color );
        selectable = typedArray.getBoolean ( R.styleable.ViewSelection_selectable, false );
        single_select = typedArray.getBoolean ( R.styleable.ViewSelection_single_select, false );
        numOfViews = typedArray.getInt ( R.styleable.ViewSelection_number_of_views, 1 );
        button_type = typedArray.getBoolean ( R.styleable.ViewSelection_button_type, false );
        edittext_type = typedArray.getBoolean ( R.styleable.ViewSelection_edittext_type, false );
        SelectionPolicy.Builder builder = new SelectionPolicy.Builder ();
        builder.setSelectable ( selectable )
                .setSingleSelect ( single_select )
                .setSelectedStateResId ( selected_state_background )
                .setUnselectedStateResId ( unselected_state_background )
                .setUnselectableStateresId ( unselected_state_background )
                .setUnselectableStateresId ( unselectable_background )
                .setSelectedTextColor ( selected_text_color )
                .setUnselectedTextColor ( unselected_text_color )
                .setUnselectableTextColor ( unselectable_text_color )
                .setTextViewType ( true );
        SelectionPolicy selectionPolicy = builder.build ();

        myAdapter = new MyAdapter ( context, selectionPolicy );
        recyclerView.setAdapter ( myAdapter );
        linearLayout.addView ( recyclerView );
    }

    public void previewOfDBResult(boolean selectable,
                                  boolean single_select,
                                  int numOfView) {
        myAdapter.setSelect ( numOfView );
//        myAdapter.refreshSelections ( numOfView, single_select );
    }

    public void showHelpfullyOptionError(int position, int visibility_status) {
        helpfully_options_errors.get ( position ).setVisibility ( visibility_status );
    }

    public void setDataSet(String[] strings) {
        myAdapter.setDataSet ( strings );
    }

    public void setClear() {
        myAdapter.clearSelectionHistory ();
        if (onClearStateListener == null) {
            throw new RuntimeException ( getCurrentContext ().toString () + "" +
                    " must implement OnClearStateListener" );
        } else {
            onClearStateListener.onClearState ( ViewSelection.this );
        }
    }

    public void setOnHelpfullyOptionClickListener(OnHelpfullyOptionsClickListener
                                                          onHelpfullyOptionClickListener) {
        this.onHelpfullyOptionClickListener = onHelpfullyOptionClickListener;
    }

    private void hideHelpfullyOptionError(int position) {
        helpfully_options_errors.get ( position ).setVisibility ( View.GONE );
    }

    public interface OnSingleItemSelectedListener {
        void onSingleItemSelected(View view, int position);
    }

    public interface OnMultiItemSelectedListener {
        void onMultiItemSelected(View view, Integer position);

        void onMultiItemDeselected(View view, Integer position);
    }

    public interface OnClearStateListener {
        void onClearState(View view);
    }

    public interface OnHelpfullyOptionsClickListener {
        void onHelpFullyClicked(int position, int helpfully_option);
    }

    private class OnHelpfullyOptionClickListener implements OnClickListener {
        int position = 0;

        public OnHelpfullyOptionClickListener(int i) {
            position = i;
        }

        @Override
        public void onClick(View view) {
            int id = view.getId ();
            hideHelpfullyOptionError ( position );
            switch (id) {
                case 0: {
                    if (onHelpfullyOptionClickListener == null) {
                        throw new RuntimeException ( getCurrentContext ().toString () + " must implement OnHelpfullyOptionsClickListener" );
                    } else {
                        if (map.get ( position ) == null) {
                            map.put ( position, 0 );
                            onHelpfullyOptionClickListener.onHelpFullyClicked ( position, 0 );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( 0 )))
                                    .setTextColor ( context.getResources ().getColor ( R.color.colorPrimary ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                    .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                    .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                        } else if (map.get ( position ) != 0) {
                            onHelpfullyOptionClickListener.onHelpFullyClicked ( position, 0 );
                            map.clear ();
                            map.put ( position, 0 );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( 0 )))
                                    .setTextColor ( context.getResources ().getColor ( R.color.colorPrimary ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                    .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                    .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                        } else {
                            onHelpfullyOptionClickListener.onHelpFullyClicked ( position, -1 );
                            if (map.containsValue ( 0 )) {
                                ((TextView) (helpfully_layouts.get ( position ).findViewById ( 0 )))
                                        .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                                ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                        .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                                ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                        .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                                map.clear ();
                            }
                        }
                    }

                    break;
                }
                case 1: {
                    if (onHelpfullyOptionClickListener == null) {
                        throw new RuntimeException ( getCurrentContext ().toString () + " must implement OnHelpfullyOptionsClickListener" );
                    } else if (map.get ( position ) == null) {
                        onHelpfullyOptionClickListener.onHelpFullyClicked ( position, 1 );
                        map.put ( position, 1 );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                .setTextColor ( context.getResources ().getColor ( R.color.colorPrimary ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 0 )))
                                .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                    } else if (map.get ( position ) != 1) {
                        onHelpfullyOptionClickListener.onHelpFullyClicked ( position, 1 );
                        map.clear ();
                        map.put ( position, 1 );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                .setTextColor ( context.getResources ().getColor ( R.color.colorPrimary ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 0 )))
                                .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                    } else {
                        onHelpfullyOptionClickListener.onHelpFullyClicked ( position, -1 );
                        if (map.containsValue ( 1 )) {
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( 0 )))
                                    .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                    .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                    .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                            map.clear ();
                        }
                    }
                    break;
                }
                case 2: {
                    if (onHelpfullyOptionClickListener == null) {
                        throw new RuntimeException ( getCurrentContext ().toString () + " must implement OnHelpfullyOptionsClickListener" );
                    } else if (map.get ( position ) == null) {
                        onHelpfullyOptionClickListener.onHelpFullyClicked ( position, 2 );
                        map.put ( position, 2 );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                .setTextColor ( context.getResources ().getColor ( R.color.colorPrimary ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 0 )))
                                .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                    } else if (map.get ( position ) != 2) {
                        onHelpfullyOptionClickListener.onHelpFullyClicked ( position, 2 );
                        map.clear ();
                        map.put ( position, 2 );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                .setTextColor ( context.getResources ().getColor ( R.color.colorPrimary ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 0 )))
                                .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                    } else {
                        onHelpfullyOptionClickListener.onHelpFullyClicked ( position, -1 );
                        if (map.containsValue ( 2 )) {
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( 0 )))
                                    .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                    .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                    .setTextColor ( context.getResources ().getColor ( R.color.colorAccent ) );
                            map.clear ();
                        }
                    }
                    break;
                }
            }
        }
    }
}
