package tk.medlynk.patient.android.Networking;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tk.medlynk.patient.android.Activity.Login.OnPrimaryAccessTokenListener;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;
import tk.medlynk.patient.android.Essentials.Utils;
import tk.medlynk.patient.android.Model.AccessTokenResponse;
import tk.medlynk.patient.android.Model.PrimaryTokenErrorResponse;
import tk.medlynk.patient.android.MyApplication;

/**
 * Created by Shahab on 1/11/2018.
 */

public class MedlynkRestAPI {

    public static final String BASE_URL = "https://medlynk.tk";
    public static final String TAG = MedlynkRequests.class.getSimpleName ();

    private static OkHttpClient okHttpClient = null;
    private static HttpLoggingInterceptor httpLoggingInterceptor = null;
    private static Retrofit retrofit = null;
    private static MedlynkAPI medLynkAPI = null;

    private static MedlynkRestAPI medlynkRestAPI = null;

    private MedlynkRestAPI() {

    }

    public static MedlynkRestAPI getInstance() {
        if (medlynkRestAPI == null)
            medlynkRestAPI = new MedlynkRestAPI ();
        MyApplication.numberOfStaticMethods++;
        return medlynkRestAPI;
    }

    public static void onPrimaryAccessTokenSuccess(AccessTokenResponse response) {

    }

    public static void onPrimaryAccessTokenFailure(String errorMessage, Constants.EXCEPTION_TYPE noException) {

    }

    public static void onPrimaryAccessTokenFailure(Constants.EXCEPTION_TYPE exception_type) {

    }

    //return a headerless retrofit object for initial token exchanges!
    public MedlynkAPI getSimpleRetrofit() {
        httpLoggingInterceptor = new HttpLoggingInterceptor ();
        httpLoggingInterceptor.setLevel ( HttpLoggingInterceptor.Level.BODY );
        okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient ()
                .addInterceptor ( httpLoggingInterceptor )
                .retryOnConnectionFailure ( false )
                .readTimeout ( 60, java.util.concurrent.TimeUnit.SECONDS )
                .writeTimeout ( 60, java.util.concurrent.TimeUnit.SECONDS )
                .connectTimeout ( 60, java.util.concurrent.TimeUnit.SECONDS )
                .build ();
        retrofit = new Retrofit.Builder ()
                .baseUrl ( BASE_URL )
                .client ( okHttpClient )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        medLynkAPI = retrofit.create ( MedlynkAPI.class );
        return medLynkAPI;
    }

    //including the authorized token!!!
    public synchronized MedlynkAPI getMainRetrofit(final Context context) {
        httpLoggingInterceptor = new HttpLoggingInterceptor ();
        httpLoggingInterceptor.setLevel ( HttpLoggingInterceptor.Level.BODY );
        final SharedPreferenceManager manager =
                new SharedPreferenceManager ( context );
        if (Utils.isPrimaryTokenExpired ( context )) {
            NewTokenThread newTokenThread = new NewTokenThread ( context, this );
            new Thread ( newTokenThread ).start ();
            try {
                this.wait ();
                Log.d ( TAG, "getMainRetrofit: the loco ic going to unlock!!!" );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
            okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient ()
                    .addInterceptor ( new Interceptor () {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Log.d ( TAG, "intercept: " + Thread.currentThread ().getName () );
                            final Request.Builder request = chain.request ().newBuilder ();
                            request.addHeader ( "Authorization",
                                    manager.getPrimaryTokenType () + " " +
                                            manager.getPrimaryToken () );
                            request.addHeader ( "Accept", "application/json" );
                            return chain.proceed ( request.build () );
                        }
                    } )
                    .addInterceptor ( httpLoggingInterceptor )
                    .addInterceptor ( new ConnectivityInterceptor ( context ) )
                    .retryOnConnectionFailure ( false )
                    .readTimeout ( 60, TimeUnit.SECONDS )
                    .writeTimeout ( 60, TimeUnit.SECONDS )
                    .connectTimeout ( 60, TimeUnit.SECONDS )
                    .build ();
            retrofit = new Retrofit.Builder ()
                    .baseUrl ( BASE_URL )
                    .client ( okHttpClient )
                    .addConverterFactory ( GsonConverterFactory.create () )
                    .build ();
            medLynkAPI = retrofit.create ( MedlynkAPI.class );
        } else {
            okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient ()
                    .addInterceptor ( new Interceptor () {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Log.d ( TAG, "intercept: " + Thread.currentThread ().getName () );
                            final Request.Builder request = chain.request ().newBuilder ();
                            request.addHeader ( "Authorization",
                                    manager.getPrimaryTokenType () + " " +
                                            manager.getPrimaryToken () );
                            request.addHeader ( "Accept", "application/json" );
                            return chain.proceed ( request.build () );
                        }
                    } )
                    .addInterceptor ( httpLoggingInterceptor )
                    .addInterceptor ( new ConnectivityInterceptor ( context ) )
                    .retryOnConnectionFailure ( false )
                    .readTimeout ( 60, TimeUnit.SECONDS )
                    .writeTimeout ( 60, TimeUnit.SECONDS )
                    .connectTimeout ( 60, TimeUnit.SECONDS )
                    .build ();
            retrofit = new Retrofit.Builder ()
                    .baseUrl ( BASE_URL )
                    .client ( okHttpClient )
                    .addConverterFactory ( GsonConverterFactory.create () )
                    .build ();
            medLynkAPI = retrofit.create ( MedlynkAPI.class );
        }
        return medLynkAPI;
    }

    public MedlynkAPI getRefreshRetrofit(final Context context) {
        httpLoggingInterceptor = new HttpLoggingInterceptor ();
        httpLoggingInterceptor.setLevel ( HttpLoggingInterceptor.Level.BODY );
        okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient ()
                .addInterceptor ( new Interceptor () {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder request = chain.request ().newBuilder ();
                        SharedPreferenceManager manager = new SharedPreferenceManager ( context );
                        request.addHeader ( "Authorization",
                                manager.getPrimaryTokenType ()
                                        + " "
                                        + manager.getRefreshToken () );
                        request.addHeader ( "Accept", "application/json" );
                        return chain.proceed ( request.build () );
                    }
                } ).addInterceptor ( httpLoggingInterceptor )
                .addInterceptor ( new ConnectivityInterceptor ( context ) )
                .retryOnConnectionFailure ( false )
                .readTimeout ( 60, java.util.concurrent.TimeUnit.SECONDS )
                .writeTimeout ( 60, java.util.concurrent.TimeUnit.SECONDS )
                .connectTimeout ( 60, java.util.concurrent.TimeUnit.SECONDS )
                .build ();

        retrofit = new Retrofit.Builder ()
                .baseUrl ( BASE_URL )
                .client ( okHttpClient )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();

        medLynkAPI = retrofit.create ( MedlynkAPI.class );

        return medLynkAPI;
    }

    public MedlynkAPI getClientRetrofit(final Context context) {
        httpLoggingInterceptor = new HttpLoggingInterceptor ();
        httpLoggingInterceptor.setLevel ( HttpLoggingInterceptor.Level.BODY );
        okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient ()
                .addInterceptor ( new Interceptor () {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder request = chain.request ().newBuilder ();
                        SharedPreferenceManager manager =
                                new SharedPreferenceManager ( context );
                        request.addHeader ( "Authorization",
                                manager.getInitialToken () );
                        request.addHeader ( "Accept",
                                "application/json" );
                        return chain.proceed ( request.build () );
                    }
                } ).addInterceptor ( httpLoggingInterceptor )
                .retryOnConnectionFailure ( false )
                .readTimeout ( 60, TimeUnit.SECONDS )
                .writeTimeout ( 60, TimeUnit.SECONDS )
                .connectTimeout ( 60, TimeUnit.SECONDS )
                .build ();
        retrofit = new Retrofit.Builder ()
                .baseUrl ( BASE_URL )
                .client ( okHttpClient )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        medLynkAPI = retrofit.create ( MedlynkAPI.class );
        return medLynkAPI;
    }

    public void getAccessToken(final OnPrimaryAccessTokenListener primaryAccessTokenListener,
                               String username,
                               String password) {
        HashMap<String, String> body = new HashMap<> ();
        body.put ( Constants.USERNAME, username );
        body.put ( Constants.PASSWORD, password );
        body.put ( Constants.GRANT_TYPE, "password" );
        body.put ( Constants.CLIENT_SECRET,
                "yXH6oP5FoXYk9IOnH31gCSnylwzNcAKjNGEnWJUs" );
        body.put ( Constants.CLIENT_ID, "2" );
        Call<AccessTokenResponse> call = MedlynkRestAPI.getInstance ().getSimpleRetrofit ().
                getPrimaryToken ( body );
        call.enqueue ( new Callback<AccessTokenResponse> () {
            @Override
            public void onResponse(Call<AccessTokenResponse> call, retrofit2.Response<AccessTokenResponse> response) {
                if (response.isSuccessful ()) {
                    primaryAccessTokenListener.onPrimaryAccessTokenSuccess ( response.body () );
                } else {
                    Gson gson = new Gson ();
                    try {
                        PrimaryTokenErrorResponse errorResponse = gson.fromJson ( response.errorBody ().string (), PrimaryTokenErrorResponse.class );
                        primaryAccessTokenListener.onPrimaryAccessTokenFailure ( errorResponse.getMessage (), Constants.EXCEPTION_TYPE.NO_EXCEPTION );
                    } catch (IOException e) {
                        e.printStackTrace ();
                        primaryAccessTokenListener.onPrimaryAccessTokenFailure ( Constants.EXCEPTION_TYPE.ERROR_RESPONSE_PARSING_EXCEPTION );
                    }
                }
            }

            @Override
            public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    primaryAccessTokenListener.onPrimaryAccessTokenFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                } else {
                    primaryAccessTokenListener.onPrimaryAccessTokenFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
            }
        } );
    }
}