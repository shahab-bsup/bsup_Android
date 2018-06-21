package com.medlynk.shahab.myviewselection;

/**
 * Created by Shahab on 6/21/2018.
 */

public class SelectionPolicy {

    public int selected_state_resId;
    public int unselected_state_resId;
    public int unselectable_state_resId;
    public int selected_text_color_resId;
    public int unselected_text_color_resId;
    public int unselectable_text_color_resId;
    public boolean isSelectable;
    public boolean isButtonType;
    public boolean isEditTextType;
    public boolean isTextViewType;
    public boolean isSingleSelect;

    public SelectionPolicy(boolean isSingleSelect,
                           int selected_state_resId,
                           int unselected_state_resId,
                           int unselectable_state_resId,
                           int selected_text_color_resId,
                           int unselected_text_color_resId,
                           int unselectable_text_color_resId,
                           boolean isSelectable,
                           boolean isButtonType,
                           boolean isEditTextType,
                           boolean isTextViewType) {
        this.isSingleSelect = isSingleSelect;
        this.selected_state_resId = selected_state_resId;
        this.unselected_state_resId = unselected_state_resId;
        this.unselectable_state_resId = unselectable_state_resId;
        this.selected_text_color_resId = selected_text_color_resId;
        this.unselected_text_color_resId = unselected_text_color_resId;
        this.unselectable_text_color_resId = unselectable_text_color_resId;
        this.isSelectable = isSelectable;
        this.isButtonType = isButtonType;
        this.isEditTextType = isEditTextType;
        this.isTextViewType = isTextViewType;
    }

    public static class Builder {
        private int selected_state_resId;
        private int unselected_state_resId;
        private int unselectable_state_resId;
        private int selected_text_color_resId;
        private int unselected_text_color_resId;
        private int unselectable_text_color_resId;
        private boolean isSelectable;
        private boolean isButtonType;
        private boolean isEditTextType;
        private boolean isTextViewType;
        private boolean isSingleSelect;

        public Builder setSingleSelect(boolean isSingleSelect){
            this.isSingleSelect = isSingleSelect;

            return this;
        }

        public Builder setSelectedStateResId(int resId) {
            this.selected_state_resId= resId;
            return this;
        }

        public Builder setUnselectedStateResId(int resId){
            this.unselected_state_resId = resId;
            return this;
        }

        public Builder setUnselectableStateresId(int resId){
            this.unselectable_state_resId = resId;
            return this;
        }

        public Builder setSelectedTextColor(int resId){
            this.selected_text_color_resId = resId;
            return this;
        }

        public Builder setUnselectedTextColor( int resId ){
            this.unselected_text_color_resId = resId;
            return this;
        }

        public Builder setUnselectableTextColor( int resId ){
            this.unselectable_text_color_resId = resId;
            return this;
        }

        public Builder setSelectable( boolean selectable ){
            this.isSelectable = selectable;
            return this;
        }

        public Builder setButtonType( boolean buttonType ){
            this.isButtonType = buttonType;
            return this;
        }

        public Builder setEditTextType( boolean editTextType ){
            this.isEditTextType = editTextType;
            return this;
        }

        public Builder setTextViewType( boolean textViewType ){
            this.isTextViewType = textViewType;
            return this;
        }

        public SelectionPolicy build(){
            return new SelectionPolicy ( isSingleSelect,
                    selected_state_resId,
                    unselected_state_resId,
                    unselectable_state_resId,
                    selected_text_color_resId,
                    unselected_text_color_resId,
                    unselectable_text_color_resId,
                    isSelectable,
                    isButtonType,
                    isEditTextType,
                    isTextViewType);
        }
    }
}
