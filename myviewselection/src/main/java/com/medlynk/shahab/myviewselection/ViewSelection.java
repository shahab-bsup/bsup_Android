package com.medlynk.shahab.myviewselection;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Shahab on 2/9/2018...
 */

public class ViewSelection extends LinearLayout {

    int numOfViews = 0;
    Boolean selectable = false;
    Boolean single_select = false;
    Boolean button_type = false;
    Boolean edittext_type = false;
    private int selected_state_background;
    private int unselected_state_background;
    private int unselectable_background;
    private int selected_text_color;
    private int unselected_text_color;
    private int unselectable_text_color;
    private OnHelpfullyOptionClickListener onHelpfullyOptionClickListener;
    private OnSingleItemSelectedListener onSingleItemSelectedListener;
    private OnClearStateListener onClearStateListener;
    private OnMultiItemSelectedListener onMultiItemSelectedListener;
    private Context context;
    private RegularAdapter regularAdapter;
    private boolean isHelpfullyAdapter = false;
    private HelpfullyAdapter helpfullyAdapter;

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

    public void setOnHelpfullyOptionClickListener(OnHelpfullyOptionClickListener
                                                          onHelpfullyOptionClickListener) {
        this.onHelpfullyOptionClickListener = onHelpfullyOptionClickListener;
        helpfullyAdapter.setOnHelpfullyClickListener(
                onHelpfullyOptionClickListener);
    }

    public void showHelpfullyError(int position, int visibility_status){
        helpfullyAdapter.presentHelpfullyError ( position, visibility_status );
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
        } else if( !isHelpfullyAdapter ) {
            regularAdapter.setOnMultiItemSelectedListener ( this, onMultiItemSelectedListener );
        } else {
            helpfullyAdapter.setOnMultiItemSelectedListener ( this, onMultiItemSelectedListener );
        }
    }

    public void setOnSingleItemSelectedListener(OnSingleItemSelectedListener
                                                        onSingleItemSelectedListener) {
        if (onSingleItemSelectedListener == null) {
            throw new RuntimeException ( context.toString () + " must implement " +
                    OnSingleItemSelectedListener.class.getSimpleName () );
        } else {
            regularAdapter.setOnSingleItemSelectedListener ( this, onSingleItemSelectedListener );
        }
    }

    public void setSelect(int numOfView) {
        regularAdapter.setSelect ( numOfView );
    }

    public void unSelect(int numOfView) {
        regularAdapter.unSelect ( numOfView );
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
        selected_text_color = typedArray.getInt ( R.styleable.ViewSelection_selected_text_color,
                R.color.selected_text_clolor );
        unselected_text_color = typedArray.getInt ( R.styleable.ViewSelection_unselected_text_color,
                R.color.unselected_text_color );
        unselectable_text_color = typedArray.getInt ( R.styleable.ViewSelection_unselectable_text_color, R.color.unselectable_text_color );
        selectable = typedArray.getBoolean ( R.styleable.ViewSelection_selectable, false );
        single_select = typedArray.getBoolean ( R.styleable.ViewSelection_single_select, false );
        isHelpfullyAdapter = typedArray.getBoolean ( R.styleable.ViewSelection_helpfully_adapter, false );
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

        if (isHelpfullyAdapter) {
            helpfullyAdapter = new
                    HelpfullyAdapter ( context, selectionPolicy );
            recyclerView.setAdapter ( helpfullyAdapter );
            linearLayout.addView ( recyclerView );
        } else {
            regularAdapter = new RegularAdapter ( context, selectionPolicy );
            recyclerView.setAdapter ( regularAdapter );
            linearLayout.addView ( recyclerView );
        }

    }

    public void previewOfDBResult(boolean selectable,
                                  boolean single_select,
                                  int numOfView) {
        regularAdapter.setSelect ( numOfView );
    }

    public void setDataSet(String[] strings) {
        if(isHelpfullyAdapter){
            helpfullyAdapter.setDataSet ( strings );
        }else{
            regularAdapter.setDataSet ( strings );
        }
    }

    public void setClear() {
        if (onClearStateListener == null) {
            throw new RuntimeException ( getCurrentContext ().toString () + "" +
                    " must implement OnClearStateListener" );
        } else if( !isHelpfullyAdapter ){
            regularAdapter.clearSelectionHistory ();
        } else{
            helpfullyAdapter.clearSelectionHistory ();
        }
        onClearStateListener.onClearState ( ViewSelection.this );
    }

    public interface OnSingleItemSelectedListener {
        void onSingleItemSelected(View view, int position);
    }

    public interface OnMultiItemSelectedListener {
        void onMultiItemSelected(View view, Integer position);
        void onMultiItemDeselected(View view, Integer position);
    }

    public interface OnHelpfullyOptionClickListener{
        void onHelpfullyClicked(int position, int helpfully_option);
    }

    public interface OnClearStateListener {
        void onClearState(View view);
    }
}
