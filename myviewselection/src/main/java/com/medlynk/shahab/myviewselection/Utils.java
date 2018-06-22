package com.medlynk.shahab.myviewselection;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Calendar;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by Shahab on 1/11/2018.
 */

public class Utils {

    private static Typeface boldTypeFace;
    private static Typeface lightTypeFace;
    private static Typeface regularTypeFace;

    @Nullable
    public static Typeface getBoldTypeFace(Context context) {
        if( boldTypeFace == null ){
            boldTypeFace =  Typeface.createFromAsset ( context.getAssets (), "fonts/proximanova-bold-webfont.ttf" );
        }
        return boldTypeFace;
    }

    @Nullable
    public static Typeface getLightTypeFace(Context context) {
        if( lightTypeFace == null ){
            lightTypeFace = Typeface.createFromAsset ( context.getAssets (), "fonts/proximanova-light-webfont.ttf" );
        }
        return lightTypeFace;
    }

    @Nullable
    public static Typeface getRegularTypeFace(Context context) {
        if( regularTypeFace == null ){
            regularTypeFace =  Typeface.createFromAsset ( context.getAssets (), "fonts/proximanova-reg-webfont.ttf" );
        }
        return regularTypeFace;
    }
}
