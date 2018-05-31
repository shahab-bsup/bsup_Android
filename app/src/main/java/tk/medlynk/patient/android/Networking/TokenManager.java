package tk.medlynk.patient.android.Networking;

import android.content.Context;
import android.util.Log;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Essentials.Utils;

/**
 * Created by Shahab on 5/25/2018.
 */

public class TokenManager {

    private final String TAG = TokenManager.class.getSimpleName ();

    private Context context;
    private Boolean mustWait;
    private static TokenManager mTokenManager = null;

    public TokenManager(Context context) {
        this.context = context;
        this.mustWait = false;
    }

    public static TokenManager getInstance(Context context){
        if( mTokenManager == null ){
            mTokenManager = new TokenManager ( context );
        }
        return mTokenManager;
    }

    public synchronized boolean isRefreshNeeded() throws InterruptedException {
        if (Utils.isPrimaryTokenExpired ( context )) {
            mustWait = true;
            refreshToken (mustWait);
        } else {
            mustWait = false;
        }
        return mustWait;
    }

    public synchronized void refreshToken(boolean mustWait) throws InterruptedException {

        if( mustWait ){
            Log.d ( TAG, "The Outer-Class Objects needs to wait!" );
            this.wait ();
        }
    }

}