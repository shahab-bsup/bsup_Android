package com.neweraandroid.demo;

import android.content.pm.FeatureInfo;

/**
 * Created by Shahab on 1/11/2018.
 */

public class Constants {
    public static final String INITIAL_TOKEN_TYPE = "token_type";
    public static final String Expire_INITIAL_TOKEN_INTERVAL = "expires_in";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String SHAREDPREF = "SharedPreferences";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public static final String ERROR_RESPONSE_PARSING_EXCEPTION = "error response parsing exception occurred!";
    public static final String RETROFIT_EXCEPTION = "password";
    public static final String Primary_Token = "Primary Token";
    public static final String Refresh_Token = "refresh_token";
    public static final String Initial_Token = "Initial Token";
    public static final String Expire_PRIMARY_TOKEN_INTERVAL = "Primary Token Expire Time!";
    public static final String Expire_REFRESH_TOKEN_INTERVAL = "Refresh Token Expire Time!";
    public static final String RECEIVED_PRIMARY_TOKEN_TIME_IN_MILLIS = "recieved primary token time in millis";
    public static final String RECEIVED_REFRESH_TOKEN_TIME_IN_MILLIS = "recieved refresh token time in millis";
    public static final String PRIMARY_TOKEN_TYPE = "primary token type";
    public static final String SCOPE = "scope";
    public static final String EMAIL = "email";
    public static final String CONFIRMED_PASSWORD = "password_confirmation";
    public static final String SelectedDoctor = "Selected Doctor";

    public enum EXCEPTION_TYPE{
        BAD_EXCEPTION,
        SOCKET_TIMEOUT_EXCEPTION,
        ERROR_RESPONSE_PARSING_EXCEPTION,
        RETROFIT_EXCEPTION,
        NO_EXCEPTION
    }

    public static final String TOKEN = "token";
    public static final String RECEIVED_INITIAL_TOKEN_TIME_IN_MILLIS = "recieved initial token time in millis";

}
