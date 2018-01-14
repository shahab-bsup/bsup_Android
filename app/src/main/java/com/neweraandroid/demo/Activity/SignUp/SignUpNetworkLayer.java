package com.neweraandroid.demo.Activity.SignUp;

import android.content.Context;

import com.neweraandroid.demo.Networking.MedlynkRequests;

import java.util.HashMap;

/**
 * Created by Shahab on 1/15/2018.
 */

public class SignUpNetworkLayer {

    public void callSignUpRout(Context context, OnSignUpListener onSignUpListener, HashMap<String, String> body) {
        MedlynkRequests.signUpNewUser ( context, onSignUpListener, body );
    }
}
