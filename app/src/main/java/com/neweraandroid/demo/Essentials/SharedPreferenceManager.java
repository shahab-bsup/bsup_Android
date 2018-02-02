package com.neweraandroid.demo.Essentials;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.Model.CurrentUserInfo;

import java.util.Calendar;

/**
 *
 * Created by Shahab on 1/11/2018.
 *
 */

public class SharedPreferenceManager{
    private  SharedPreferences sharedPreferences;
    public SharedPreferenceManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences ( Constants.SHAREDPREF, Context.MODE_PRIVATE );
    }
    public void setPrimaryToken(String primaryToken){
        this.sharedPreferences.edit ().putLong ( Constants.RECEIVED_PRIMARY_TOKEN_TIME_IN_MILLIS, (Calendar.getInstance ()).getTimeInMillis () ).apply ();
        this.sharedPreferences.edit ().putString ( Constants.Primary_Token, primaryToken ).apply ();
    }
    public void setPrimaryExpireToken(int expireToken){
        this.sharedPreferences.edit ().putInt ( Constants.Expire_PRIMARY_TOKEN_INTERVAL, expireToken ).apply ();
    }
    public void setPrimaryTokenType(String tokenType){
        this.sharedPreferences.edit ().putString ( Constants.PRIMARY_TOKEN_TYPE, tokenType ).apply ();
    }
    public String getPrimaryTokenType(){
        return this.sharedPreferences.getString ( Constants.PRIMARY_TOKEN_TYPE, null );
    }
    public String getPrimaryToken( ){
        return this.sharedPreferences.getString ( Constants.Primary_Token, null );
    }
    public int getPrimaryExpireToken(){
        return this.sharedPreferences.getInt ( Constants.Expire_PRIMARY_TOKEN_INTERVAL, 0 );
    }
    public long getLastPrimaryTokenSetTime(){
        return this.sharedPreferences.getLong ( Constants.RECEIVED_PRIMARY_TOKEN_TIME_IN_MILLIS, 0 );
    }
    public void setInitialTokenType(String tokenType) {
        this.sharedPreferences.edit ().putString ( Constants.INITIAL_TOKEN_TYPE, tokenType ).apply ();
    }
    public void setInitialToken(String initialToken){
        this.sharedPreferences.edit ().putLong ( Constants.RECEIVED_INITIAL_TOKEN_TIME_IN_MILLIS, (Calendar.getInstance ()).getTimeInMillis () ).apply ();
        this.sharedPreferences.edit ().putString ( Constants.Initial_Token, initialToken ).apply ();
    }
    public void setInitialExpireToken(int expireToken){
        this.sharedPreferences.edit ().putInt ( Constants.Expire_INITIAL_TOKEN_INTERVAL, expireToken ).apply ();
    }
    public String getInitialToken(){
        return this.sharedPreferences.getString ( Constants.Initial_Token, null );
    }
    public long getLastInitialTokenSetTime(){
        return this.sharedPreferences.getLong ( Constants.RECEIVED_INITIAL_TOKEN_TIME_IN_MILLIS, 0 );
    }
    public int getInitialExpireToken(){
        return this.sharedPreferences.getInt ( Constants.Expire_INITIAL_TOKEN_INTERVAL, 0 );
    }
    public String getInitialTokenType(){
        return this.sharedPreferences.getString ( Constants.INITIAL_TOKEN_TYPE, null );
    }
    public void setRefreshToken( String refreshToken ){
        this.sharedPreferences.edit ().putLong ( Constants.RECEIVED_REFRESH_TOKEN_TIME_IN_MILLIS, (Calendar.getInstance ()).getTimeInMillis () ).apply ();
        this.sharedPreferences.edit ().putString ( Constants.Refresh_Token, refreshToken ).apply ();
    }
    public String getRefreshToken(){
        return this.sharedPreferences.getString ( Constants.Refresh_Token, null );
    }
    public void setRefreshExpireToken(int expireToken){
        this.sharedPreferences.edit ().putInt ( Constants.Expire_REFRESH_TOKEN_INTERVAL, expireToken ).apply ();
    }
    public int getRefreshExpireToken(){
        return this.sharedPreferences.getInt ( Constants.Expire_REFRESH_TOKEN_INTERVAL, 0 );
    }
    public long getLastRefreshTokenSetTime(){
        return this.sharedPreferences.getLong ( Constants.RECEIVED_REFRESH_TOKEN_TIME_IN_MILLIS, 0 );
    }
    public void setEmail(String email){
        this.sharedPreferences.edit ().putString ( Constants.EMAIL, email ).apply ();
    }
    public String getEmail(){
        return this.sharedPreferences.getString ( Constants.EMAIL, null );
    }

    public void setUserPreferencesNoDoctorID(Boolean noDoctorIDPreference){
        this.sharedPreferences.edit ().putBoolean ( Constants.No_Doctor_ID_Preferences, noDoctorIDPreference ).apply ();
    }
    public Boolean getUserPreferencesNoDoctorID(){
        return this.sharedPreferences.getBoolean ( Constants.No_Doctor_ID_Preferences, false );
    }

    public void setCurrentUser(CurrentUserInfo currentUserInfo){
        Gson gson = new Gson ();
        String currentUserInfoString = gson.toJson ( currentUserInfo );
        this.sharedPreferences.edit ().putString ( Constants.Current_User_Info, currentUserInfoString ).apply ();
    }

    public String getCurrentUserInfo(){
        return this.sharedPreferences.getString ( Constants.Current_User_Info, null );
    }
}