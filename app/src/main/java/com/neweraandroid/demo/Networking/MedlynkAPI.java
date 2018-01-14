package com.neweraandroid.demo.Networking;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.neweraandroid.demo.Model.InitialTokenResponse;
import com.neweraandroid.demo.Model.InitiateResponse;
import com.neweraandroid.demo.Model.PrimaryTokenResponse;
import com.neweraandroid.demo.Model.RenewTokenResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Shahab on 1/11/2018.
 */

public interface MedlynkAPI {

    //get an initial token for just registered user!
    @POST("/oauth/token")
    retrofit2.Call<InitialTokenResponse> getInitialToken(@Body @Nullable Map<String, String> map);

    //actually this is the login route which sends the username and password of the user!
    @POST("/oauth/token")
    Call<PrimaryTokenResponse> getPrimaryToken(@Body @Nullable Map<String, String> map);

    //renew the expired token!
    @POST("/oauth/token")
    Call<RenewTokenResponse> getNewToken(@Body @Nullable Map<String, String> map);

    @POST("/api/register")
    Call<InitiateResponse> signUp(@Body @Nullable Map<String, String> map);
}
