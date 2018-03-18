package tk.medlynk.patient.android;

import java.util.HashMap;

/**
 * Created by Shahab on 1/11/2018.
 */

public class Constants {

    public static final HashMap<String, Object> NEW_SYMPTOM_ANSWER_BODY = new HashMap<> (  );
    public static final HashMap<String, Object> FOLLOW_UP_RESULT_BODY = new HashMap<> (  );

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
    public static final String Reset_Token = "Reset Token";
    public static final String No_Doctor_ID_Preferences = "No DoctorID Preference";
    public static final String Current_User_Info = "Current User Info";
    public static final String PROVIDER_ID = "provider_id";
    public static final String ANSWER = "answer";
    public static final String APPOINTMENT_ID = "appointment_id";

    public enum EXCEPTION_TYPE{
        BAD_EXCEPTION,
        SOCKET_TIMEOUT_EXCEPTION,
        ERROR_RESPONSE_PARSING_EXCEPTION,
        RETROFIT_EXCEPTION,
        NO_EXCEPTION
    }

    public static final String TOKEN = "token";
    public static final String RECEIVED_INITIAL_TOKEN_TIME_IN_MILLIS = "recieved initial token time in millis";
    public static final String QUESTION_SET = "question_set";
    public static final String QUESTION_NUMBER = "question_number";
    public static final String NEW_SYMPTOM = "new_symptom";

}
