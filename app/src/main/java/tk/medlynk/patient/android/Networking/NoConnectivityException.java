package tk.medlynk.patient.android.Networking;

import java.io.IOException;

/**
 * Created by Shahab on 2/23/2018.
 */

class NoConnectivityException extends Throwable {

    @Override
    public String getMessage() {
        return "No Internet Connection!";
    }
}
