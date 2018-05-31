package tk.medlynk.patient.android.Networking;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Model.AccessTokenResponse;
import tk.medlynk.patient.android.Model.PrimaryTokenErrorResponse;

/**
 * Created by Shahab on 5/26/2018.
 */

public class NewTokenThread implements Runnable {

    private Context context;
    private MedlynkRestAPI medlynkRestAPI;

    private final static String TAG = NewTokenThread.class.getSimpleName ();

    public NewTokenThread(Context context, MedlynkRestAPI medlynkRestAPI) {
        this.context = context;
        this.medlynkRestAPI = medlynkRestAPI;
    }

    @Override
    public synchronized void run() {
        final SharedPreferenceManager manager =
                new SharedPreferenceManager ( context );
        HashMap<String, String> body = new HashMap<> ();
        body.put ( Constants.GRANT_TYPE, "refresh_token" );
        body.put ( Constants.Refresh_Token, manager.getRefreshToken () );
        body.put ( Constants.CLIENT_SECRET, "yXH6oP5FoXYk9IOnH31gCSnylwzNcAKjNGEnWJUs" );
        body.put ( Constants.CLIENT_ID, "2" );
        body.put ( Constants.SCOPE, "" );
        Call<AccessTokenResponse> call =
                MedlynkRestAPI.getInstance ().getRefreshRetrofit ( context ).
                        getPrimaryToken ( body );
        call.enqueue ( new Callback<AccessTokenResponse> () {
            @Override
            public void onResponse(Call<AccessTokenResponse> call, retrofit2.Response<AccessTokenResponse> response) {
                if (response.isSuccessful ()) {
                    Log.d ( TAG, "onResponse: " + "mammmmmad");
                    manager.setPrimaryToken ( response.body ().getAccessToken () );
                    manager.setPrimaryExpireToken ( 100 );
                    manager.setRefreshToken ( response.body ().getRefreshToken () );
                    medlynkRestAPI.notifyAll ();
                }
            }

            @Override
            public void onFailure(Call<AccessTokenResponse> call, Throwable t) {

            }
        } );
    }
}
