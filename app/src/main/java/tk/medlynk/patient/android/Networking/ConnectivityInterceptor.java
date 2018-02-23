package tk.medlynk.patient.android.Networking;

import android.content.Context;

import java.io.IOException;
import java.io.NotActiveException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import tk.medlynk.patient.android.Essentials.Utils;

/**
 * Created by Shahab on 2/23/2018.
 */

public class ConnectivityInterceptor implements Interceptor {

    private Context context;

    public ConnectivityInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if(!Utils.isDeviceConnected ( context )){
            try {
                throw new NoConnectivityException ();
            } catch (NoConnectivityException e) {
                e.printStackTrace ();
            }
        }
        Request.Builder builder = chain.request().newBuilder ();
        return chain.proceed ( builder.build () );
    }
}
