package tk.medlynk.patient.android.Activity.SignUp;

import android.content.Context;

import tk.medlynk.patient.android.Networking.MedlynkRequests;

import java.util.HashMap;

/**
 * Created by Shahab on 1/15/2018.
 */

public class SignUpNetworkLayer {

    public void callSignUpRout(Context context, OnSignUpListener onSignUpListener, HashMap<String, Object> body) {
        MedlynkRequests.signUp ( context, onSignUpListener, body );
    }
}
