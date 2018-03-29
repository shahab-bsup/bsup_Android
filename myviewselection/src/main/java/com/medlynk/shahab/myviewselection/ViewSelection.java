package com.medlynk.shahab.myviewselection;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    List<Button> buttons = new ArrayList<> ();
    List<EditText> editTexts = new ArrayList<> ();
    List<RelativeLayout> helpfully_layouts = new ArrayList<RelativeLayout> ();
    List<TextView> helpfully_options_errors = new ArrayList<> ();
    int numOfViews = 0;
    Boolean selectable = false;
    private int currentSelection = -1;
    private int previous_selection = -1;
    HashMap<Integer, Integer> map = new HashMap<> ();

    private int selected_state_background;
    private int unselected_state_background;
    private int unselectable_background;

    private int selected_text_color, unselected_text_color, unselectable_text_color;
    private OnSingleItemSelectedListener onSingleItemSelectedListener;
    private OnClearStateListener onClearStateListener;
    private OnMultiItemSelectedListener onMultiItemSelectedListener;
    private OnHelpfullyOptionsClickListener onHelpfullyOptionClickListener;
    private Context mContext;
    Boolean show_helpfully_layout = false;
    Boolean single_select = false;
    Boolean button_type = false;
    Boolean edittext_type = false;
    private Drawable selected_state_drawable;
    private Drawable unselected_state_drawbale;
    private Drawable unselectable_drawable;

    public int getUnselectable_background() {
        return unselectable_background;
    }

    public void setUnselectable_background(int unselectable_background) {
        this.unselectable_background = unselectable_background;
    }

    public OnClearStateListener getOnClearStateListener() {
        return onClearStateListener;
    }

    public void setOnClearStateListener(OnClearStateListener onClearStateListener) {
        this.onClearStateListener = onClearStateListener;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public Boolean getSelectable() {
        return selectable;
    }

    public void setSelectable(Boolean selectable) {
        this.selectable = selectable;
    }

    public OnMultiItemSelectedListener getOnMultiItemSelectedListener() {
        return onMultiItemSelectedListener;
    }

    public void setOnMultiItemSelectedListener(OnMultiItemSelectedListener onMultiItemSelectedListener) {
        this.onMultiItemSelectedListener = onMultiItemSelectedListener;
    }

    public OnSingleItemSelectedListener getOnSingleItemSelectedListener() {
        return onSingleItemSelectedListener;
    }

    public void setOnSingleItemSelectedListener(OnSingleItemSelectedListener onSingleItemSelectedListener) {
        this.onSingleItemSelectedListener = onSingleItemSelectedListener;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public List<EditText> getEditTexts() {
        return editTexts;
    }

    public void setEditTexts(List<EditText> editTexts) {
        this.editTexts = editTexts;
    }

    public void setNumOfViews(int numOfViews) {
        this.numOfViews = numOfViews;
    }

    public Boolean getSingle_select() {
        return single_select;
    }

    public void setSingle_select(Boolean single_select) {
        this.single_select = single_select;
    }

    public void setCurrentSelection(int currentSelection) {
        this.currentSelection = currentSelection;
    }

    public int getSelected_state_background() {
        return selected_state_background;
    }

    public void setSelected_state_background(int selected_state_background) {
        this.selected_state_background = selected_state_background;
    }

    public int getUnselected_state_background() {
        return unselected_state_background;
    }

    public void setUnselected_state_background(int unselected_state_background) {
        this.unselected_state_background = unselected_state_background;
    }

    private OnClickListener button_click_listener = new OnClickListener () {
        @Override
        public void onClick(View view) {
            currentSelection = view.getId ();
            if (selectable) {
                if (single_select) {
                    if (onSingleItemSelectedListener == null) {
                        throw new RuntimeException ( getmContext ().toString () + "must implement " + OnSingleItemSelectedListener.class.getSimpleName () );
                    } else if (buttons.get ( currentSelection ).getBackground ().equals ( selected_state_drawable )) {
                        buttons.get ( currentSelection ).setTextColor ( unselected_text_color );
                        buttons.get ( currentSelection ).setBackgroundDrawable ( unselected_state_drawbale );
                        onSingleItemSelectedListener.onSingleItemSelected ( ViewSelection.this, -1 );
                    } else {
                        buttons.get ( currentSelection ).setTextColor ( selected_text_color );
                        buttons.get ( currentSelection ).setBackgroundDrawable ( selected_state_drawable );
                        onSingleItemSelectedListener.onSingleItemSelected ( ViewSelection.this,
                                currentSelection );
                        for (Button button : buttons) {
                            if (button.getId () == currentSelection) {
                                button.setTextColor ( selected_text_color );
                                button.setBackgroundDrawable ( selected_state_drawable );
                            } else if (button.getId () == previous_selection) {
                                button.setTextColor ( unselected_text_color );
                                button.setBackgroundDrawable ( unselected_state_drawbale );
                            }
                        }
                        previous_selection = currentSelection;
                    }
                } else {
                    if (buttons.get ( currentSelection ).getBackground ().equals ( selected_state_drawable )) {
                        buttons.get ( currentSelection ).setBackgroundDrawable ( unselected_state_drawbale );
                        buttons.get ( currentSelection ).setTextColor ( unselected_text_color );
                        if (show_helpfully_layout)
                            helpfully_layouts.get ( currentSelection ).setVisibility ( View.GONE );
                        if (onMultiItemSelectedListener == null) {
                            throw new RuntimeException ( getmContext ().toString () + "must implement " + OnMultiItemSelectedListener.class.getSimpleName () );
                        } else {
                            onMultiItemSelectedListener.onMultiItemDeselected ( ViewSelection.this, currentSelection );
                        }
                    } else {
                        if (onMultiItemSelectedListener == null) {
                            throw new RuntimeException ( getmContext ().toString () + " must implement " + OnMultiItemSelectedListener.class.getSimpleName () );
                        } else {
                            onMultiItemSelectedListener.onMultiItemSelected ( ViewSelection.this, currentSelection );
                        }
                        if (show_helpfully_layout)
                            helpfully_layouts.get ( currentSelection ).setVisibility ( View.VISIBLE );
                        buttons.get ( currentSelection ).setBackgroundDrawable ( selected_state_drawable );
                        buttons.get ( currentSelection ).setTextColor ( selected_text_color );
                    }
                }
            } else {
                for (Button button : buttons) {
                    button.setBackgroundDrawable ( unselectable_drawable );
                    button.setTextColor ( unselectable_text_color );
                }
            }
        }
    };

    public ViewSelection(Context context) {
        super ( context );
    }

    public ViewSelection(Context context, AttributeSet attrs) {
        super ( context, attrs );
        this.mContext = context;
        makeView ( context, attrs );
    }

    public ViewSelection(Context context, AttributeSet attrs, int defStyleAttr) {
        super ( context, attrs, defStyleAttr );
        this.mContext = context;
        makeView ( context, attrs );
    }

    private void makeView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.viewselection_parent_view, this, true );
        LinearLayout linearLayout = view.findViewById ( R.id.parent );
        TypedArray typedArray = context.
                obtainStyledAttributes ( attrs, R.styleable.ViewSelection, 0, 0 );
        selected_state_background = typedArray.getResourceId ( R.styleable.ViewSelection_selected_background, R.drawable.selected_stated );
        selected_state_drawable = context.getResources ().getDrawable ( selected_state_background );
        unselected_state_background = typedArray.getResourceId ( R.styleable.ViewSelection_unselected_background, R.drawable.unselected_state );
        unselected_state_drawbale = context.getResources ().getDrawable ( unselected_state_background );
        unselectable_background = typedArray.getResourceId ( R.styleable.ViewSelection_unselectable_background, R.drawable.unselected_state );
        unselectable_drawable = context.getResources ().getDrawable ( unselectable_background );
        selected_text_color = typedArray.getInt ( R.styleable.ViewSelection_selected_text_color, R.color.selected_text_clolor );
        unselected_text_color = typedArray.getInt ( R.styleable.ViewSelection_unselected_text_color, R.color.unselected_text_color );
        unselectable_text_color = typedArray.getInt ( R.styleable.ViewSelection_unselectable_text_color, R.color.unselectable_text_color );
        selectable = typedArray.getBoolean ( R.styleable.ViewSelection_selectable, false );
        single_select = typedArray.getBoolean ( R.styleable.ViewSelection_single_select, false );
        numOfViews = typedArray.getInt ( R.styleable.ViewSelection_number_of_views, 1 );
        button_type = typedArray.getBoolean ( R.styleable.ViewSelection_button_type, false );
        edittext_type = typedArray.getBoolean ( R.styleable.ViewSelection_edittext_type, false );
        show_helpfully_layout = typedArray.getBoolean ( R.styleable.ViewSelection_show_helpfully_layout, false );
        if (!typedArray.hasValue ( R.styleable.ViewSelection_button_type ) &&
                !typedArray.hasValue ( R.styleable.ViewSelection_edittext_type )) {
            throw new RuntimeException ( ViewSelection.class.getSimpleName () + " must have a view type!" );
        } else if (button_type && edittext_type) {
            throw new RuntimeException ( ViewSelection.class.getSimpleName () + " can only have a single view type\nCurrently has both button type and EditText type!" );
        } else if (button_type)
            for (int i = 0; i < numOfViews; i++) {
                Button button = new Button ( context );
                button.setPadding ( 10, 10, 10, 10 );
                if (selectable) {
                    button.setBackgroundResource ( unselected_state_background );
                    button.setTextColor ( unselected_text_color );
                } else {
                    button.setBackgroundResource ( unselectable_background );
                    button.setTextColor ( unselectable_text_color );
                }
                LayoutParams layoutParams = new LayoutParams ( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT );
                layoutParams.setMargins ( 10, 10, 10, 10 );
                button.setLayoutParams ( layoutParams );
                button.setId ( i );
                button.setOnClickListener ( button_click_listener );
                buttons.add ( button );
                linearLayout.addView ( button );
                if (show_helpfully_layout) {
                    View view1 = LayoutInflater.from ( context ).inflate ( R.layout.helpfully_layout,
                            this, false );
                    TextView help_a_lot = view1.findViewById ( R.id.help_a_lot );
                    help_a_lot.setId ( (int) 0 );
                    help_a_lot.setOnClickListener ( new OnHelpfullyOptionClickListener ( i ) );
                    TextView help_a_little = view1.findViewById ( R.id.help_a_little );
                    help_a_little.setId ( (int) 1 );
                    help_a_little.setOnClickListener ( new OnHelpfullyOptionClickListener ( i ) );
                    TextView not_helping = view1.findViewById ( R.id.not_helping );
                    not_helping.setId ( (int) 2 );
                    not_helping.setOnClickListener ( new OnHelpfullyOptionClickListener ( i ) );
                    TextView error = view1.findViewById ( R.id.choose_error );
                    helpfully_options_errors.add ( error );
                    RelativeLayout helpfully_layout = view1.findViewById ( R.id.helpfully_layout );
                    helpfully_layouts.add ( helpfully_layout );
                    helpfully_layout.setVisibility ( View.GONE );
                    linearLayout.addView ( helpfully_layout );
                }
            }
        else if (edittext_type) {
            for (int i = 0; i < numOfViews; i++) {
                EditText editText = new EditText ( context );
                editText.setPadding ( 10, 10, 10, 10 );
                LayoutParams layoutParams = new LayoutParams ( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );
                layoutParams.setMargins ( 10, 10, 10, 10 );
                editText.setLayoutParams ( layoutParams );
                editTexts.add ( editText );
                linearLayout.addView ( editText );
            }
        }
    }

    public void showHelpfullyOptionError(int position, int visibility_status) {
        helpfully_options_errors.get ( position ).setVisibility ( visibility_status );
    }

    public void setTextToButtons(String text, int position) {
        buttons.get ( position ).setText ( text );
    }

    public void setClear() {
        for (Button button : buttons) {
            button.setTextColor ( unselected_text_color );
            button.setBackgroundDrawable ( unselected_state_drawbale );
        }
        if( show_helpfully_layout ){
            for (RelativeLayout helpfully_layout : helpfully_layouts) {
                helpfully_layout.setVisibility ( View.GONE );
            }
        }
        if (onClearStateListener == null) {
            throw new RuntimeException ( getmContext ().toString () + "" +
                    " must implement OnClearStateListener" );
        } else {
            onClearStateListener.onClearState ( ViewSelection.this );
        }
    }

    public void setTextToEditTexts(String text, int position) {
        editTexts.get ( position ).setText ( text );
    }

    public int getNumberOfViews() {
        return numOfViews;
    }

    public int getCurrentSelection() {
        return currentSelection;
    }

    public void setOnHelpfullyOptionClickListener(OnHelpfullyOptionsClickListener onHelpfullyOptionClickListener) {
        this.onHelpfullyOptionClickListener = onHelpfullyOptionClickListener;
    }

    public interface OnSingleItemSelectedListener {
        void onSingleItemSelected(View view, int position);
    }

    public interface OnMultiItemSelectedListener {
        void onMultiItemSelected(View view, Integer position);

        void onMultiItemDeselected(View view, Integer position);
    }

    public void hideAllHelpfullyOptions(){
        for (RelativeLayout helpfully_layout : helpfully_layouts) {
            helpfully_layout.setVerticalGravity ( View.GONE );
        }
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
                        throw new RuntimeException ( getmContext ().toString () + " must implement OnHelpfullyOptionsClickListener" );
                    } else {
                        if (map.get ( position ) == null) {
                            map.put ( position, 0 );
                            onHelpfullyOptionClickListener.onHelpFullyClicked ( position, 1 );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( 0 )))
                                    .setTextColor ( mContext.getResources ().getColor ( R.color.colorPrimary ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                    .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                    .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                        } else if (map.get ( position ) != 0) {
                            onHelpfullyOptionClickListener.onHelpFullyClicked ( position, 1 );
                            map.clear ();
                            map.put ( position, 0 );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( 0 )))
                                    .setTextColor ( mContext.getResources ().getColor ( R.color.colorPrimary ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                    .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                    .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                        } else {
                            onHelpfullyOptionClickListener.onHelpFullyClicked ( position, -1 );
                            if (map.containsValue ( 0 )) {
                                ((TextView) (helpfully_layouts.get ( position ).findViewById ( 0 )))
                                        .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                                ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                        .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                                ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                        .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                                map.clear ();
                            }
                        }
                    }

                    break;
                }
                case 1: {
                    if (onHelpfullyOptionClickListener == null) {
                        throw new RuntimeException ( getmContext ().toString () + " must implement OnHelpfullyOptionsClickListener" );
                    } else if (map.get ( position ) == null) {
                        onHelpfullyOptionClickListener.onHelpFullyClicked ( position, 2 );
                        map.put ( position, 1 );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                .setTextColor ( mContext.getResources ().getColor ( R.color.colorPrimary ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 0 )))
                                .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                    } else if (map.get ( position ) != 1) {
                        onHelpfullyOptionClickListener.onHelpFullyClicked ( position, 2 );
                        map.clear ();
                        map.put ( position, 1 );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                .setTextColor ( mContext.getResources ().getColor ( R.color.colorPrimary ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 0 )))
                                .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                    } else {
                        onHelpfullyOptionClickListener.onHelpFullyClicked ( position, -1 );
                        if (map.containsValue ( 1 )) {
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( 0 )))
                                    .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                    .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                    .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                            map.clear ();
                        }
                    }
                    break;
                }
                case 2: {
                    if (onHelpfullyOptionClickListener == null) {
                        throw new RuntimeException ( getmContext ().toString () + " must implement OnHelpfullyOptionsClickListener" );
                    } else if (map.get ( position ) == null) {
                        onHelpfullyOptionClickListener.onHelpFullyClicked ( position, 3 );
                        map.put ( position, 2 );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                .setTextColor ( mContext.getResources ().getColor ( R.color.colorPrimary ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 0 )))
                                .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                    } else if (map.get ( position ) != 2) {
                        onHelpfullyOptionClickListener.onHelpFullyClicked ( position, 3 );
                        map.clear ();
                        map.put ( position, 2 );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                .setTextColor ( mContext.getResources ().getColor ( R.color.colorPrimary ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 0 )))
                                .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                        ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                    } else {
                        onHelpfullyOptionClickListener.onHelpFullyClicked ( position, -1 );
                        if (map.containsValue ( 2 )) {
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( 0 )))
                                    .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 1 )))
                                    .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                            ((TextView) (helpfully_layouts.get ( position ).findViewById ( (int) 2 )))
                                    .setTextColor ( mContext.getResources ().getColor ( R.color.colorAccent ) );
                            map.clear ();
                        }
                    }
                    break;
                }
            }
        }
    }

    private void hideHelpfullyOptionError(int position) {
        helpfully_options_errors.get ( position ).setVisibility ( View.GONE );
    }
}
