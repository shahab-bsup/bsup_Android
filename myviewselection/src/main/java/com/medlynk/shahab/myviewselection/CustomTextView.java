package com.medlynk.shahab.myviewselection;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Shahab on 1/19/2018.
 */

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {

    public CustomTextView(Context context) {
        super ( context );
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super ( context, attrs );
        init(context, whatStyle(attrs));
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super ( context, attrs, defStyleAttr );
        init ( context, whatStyle ( attrs ) );
    }

    private void init(Context context, int style) {
        switch (style){
            case 0:{
                this.setTypeface ( Utils.getRegularTypeFace ( context ) );
                break;
            }
            case 1:{
                this.setTypeface ( Utils.getBoldTypeFace ( context ) );
                break;
            }
            case 2:{
                this.setTypeface ( Utils.getLightTypeFace ( context ) );
                break;
            }
        }
    }

    private int whatStyle(AttributeSet attrs) {
        if( attrs != null ){
            return attrs.getAttributeIntValue ( "http://schemas.android.com/apk/res/android"
            ,"textStyle", Typeface.NORMAL);
        }
        return 0;
    }

}
