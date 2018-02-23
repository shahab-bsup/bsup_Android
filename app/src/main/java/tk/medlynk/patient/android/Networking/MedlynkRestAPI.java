package tk.medlynk.patient.android.Networking;

import android.content.Context;

import tk.medlynk.patient.android.Essentials.SharedPreferenceManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shahab on 1/11/2018.
 */

public class MedlynkRestAPI {

    public static final String BASE_URL = "https://medlynk.tk";

    private static OkHttpClient okHttpClient = null;
    private static HttpLoggingInterceptor httpLoggingInterceptor = null;
    private static Retrofit retrofit = null;
    private static MedlynkAPI medLynkAPI = null;

    //return a headerless retrofit object for initial token exchanges!
    public static MedlynkAPI getSimpleRetrofit(){
            httpLoggingInterceptor = new HttpLoggingInterceptor (  );
            httpLoggingInterceptor.setLevel ( HttpLoggingInterceptor.Level.BODY );
            okHttpClient = new OkHttpClient.Builder ()
                    .addInterceptor ( httpLoggingInterceptor )
                    .retryOnConnectionFailure ( false )
                    .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
                    .writeTimeout( 60, java.util.concurrent.TimeUnit.SECONDS )
                    .connectTimeout ( 60, java.util.concurrent.TimeUnit.SECONDS )
                    .build ();
            retrofit = new Retrofit.Builder()
                    .baseUrl ( BASE_URL )
                    .client ( okHttpClient )
                    .addConverterFactory ( GsonConverterFactory.create () )
            .build ();
                medLynkAPI= retrofit.create ( MedlynkAPI.class );
        return medLynkAPI;
    }

    public static MedlynkAPI getMainRetrofit(final Context context){
        httpLoggingInterceptor = new HttpLoggingInterceptor (  );
        httpLoggingInterceptor.setLevel ( HttpLoggingInterceptor.Level.BODY );
        okHttpClient = new OkHttpClient.Builder ()
                .addInterceptor ( new Interceptor () {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder request = chain.request ().newBuilder ();
                        SharedPreferenceManager manager = new SharedPreferenceManager ( context );
                        request.addHeader ( "Authorization",  manager.getPrimaryTokenType () + " " + manager.getPrimaryToken ());
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
        retrofit = new Retrofit.Builder()
                .baseUrl ( BASE_URL )
                .client ( okHttpClient )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
            medLynkAPI= retrofit.create ( MedlynkAPI.class );
        return medLynkAPI;
    }

    public static MedlynkAPI getRegisterRetrofit(final Context context){
        httpLoggingInterceptor = new HttpLoggingInterceptor (  );
        httpLoggingInterceptor.setLevel ( HttpLoggingInterceptor.Level.BODY );
        okHttpClient = new OkHttpClient.Builder ()
                .addInterceptor ( new Interceptor () {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder request = chain.request ().newBuilder ();
                        SharedPreferenceManager manager = new SharedPreferenceManager ( context );
                        request.addHeader ( "Authorization", manager.getInitialToken ());
                        request.addHeader ( "Accept", "application/json" );
                        return chain.proceed ( request.build () );
                    }
                } ).addInterceptor ( httpLoggingInterceptor )
                .retryOnConnectionFailure ( false )
                .readTimeout ( 60, TimeUnit.SECONDS )
                .writeTimeout ( 60, TimeUnit.SECONDS )
                .connectTimeout ( 60, TimeUnit.SECONDS )
                .build ();
        retrofit = new Retrofit.Builder()
                .baseUrl ( BASE_URL )
                .client ( okHttpClient )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
            medLynkAPI = retrofit.create ( MedlynkAPI.class );
        return medLynkAPI;
    }
}