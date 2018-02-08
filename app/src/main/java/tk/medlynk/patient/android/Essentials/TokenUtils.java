package tk.medlynk.patient.android.Essentials;

import android.content.Context;

import java.util.Calendar;

/**
 * Created by Shahab on 1/12/2018.
 */

public class TokenUtils {
    public TokenUtils(Context context) {
        this.context = context;
        token = new SharedPreferenceManager ( context ).getInitialToken ();
    }

    private String token;
    private Context context;

    public boolean isTokenExpired(){
        SharedPreferenceManager manager = new SharedPreferenceManager ( context );
        long expireTime =  manager.getInitialExpireToken () * 1000l;
        long currentTime =  (Calendar.getInstance ()).getTimeInMillis ();
        long lastTokenSetTime = manager.getLastInitialTokenSetTime ();
        return ( currentTime - lastTokenSetTime ) >= expireTime ? true : false;
    }

}
