package com.neweraandroid.demo.Essentials;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;

import java.util.Calendar;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by Shahab on 1/11/2018.
 */

public class Utils {

    private static Typeface boldTypeFace;
    private static Typeface lightTypeFace;
    private static Typeface regularTypeFace;

    public static boolean isDeviceConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService ( CONNECTIVITY_SERVICE );
        NetworkInfo networkInfo = cm.getActiveNetworkInfo ();

        return networkInfo != null && networkInfo.isConnectedOrConnecting ();
    }

    @Nullable
    public static Typeface getBoldTypeFace(Context context) {
        if( boldTypeFace == null )
            return Typeface.createFromAsset ( context.getAssets (), "fonts/proximanova-bold-webfont.ttf" );

        return null;
    }

    @Nullable
    public static Typeface getLightTypeFace(Context context) {
        if( lightTypeFace == null )
            return Typeface.createFromAsset ( context.getAssets (), "fonts/proximanova-light-webfont.ttf" );

        return null;
    }

    @Nullable
    public static Typeface getRegularTypeFace(Context context) {
        if( regularTypeFace == null )
            return Typeface.createFromAsset ( context.getAssets (), "fonts/proximanova-reg-webfont.ttf" );

        return null;
    }

    public static boolean isTokenExpired(Context context){
        SharedPreferenceManager manager = new SharedPreferenceManager ( context );
        long expireTime =  manager.getInitialExpireToken () * 1000l;
        long currentTime =  (Calendar.getInstance ()).getTimeInMillis ();
        long lastTokenSetTime = manager.getLastInitialTokenSetTime ();
        return ( currentTime - lastTokenSetTime ) >= expireTime ? true : false;
    }

    public static boolean isPrimaryTokenExpired(Context context){
        SharedPreferenceManager manager = new SharedPreferenceManager ( context );
        long expireTime =  manager.getPrimaryExpireToken () * 1000l;
        long currentTime =  (Calendar.getInstance ()).getTimeInMillis ();
        long lastTokenSetTime = manager.getLastPrimaryTokenSetTime ();
        return ( currentTime - lastTokenSetTime ) >= expireTime ? true : false;
    }

    public static boolean isRefreshTokenExpired(Context context){
        SharedPreferenceManager manager = new SharedPreferenceManager ( context );
        long expireTime =  manager.getRefreshExpireToken () * 1000l;
        long currentTime =  (Calendar.getInstance ()).getTimeInMillis ();
        long lastTokenSetTime = manager.getLastRefreshTokenSetTime ();
        return ( currentTime - lastTokenSetTime ) >= expireTime ? true : false;
    }
}
