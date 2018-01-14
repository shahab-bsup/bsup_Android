package com.neweraandroid.demo.Networking;

import android.content.Context;

import com.google.gson.Gson;
import com.neweraandroid.demo.Activity.SignUp.OnSignUpListener;
import com.neweraandroid.demo.Activity.Splash.InitialTokenListener;
import com.neweraandroid.demo.Activity.Login.PrimaryAccessTokenListener;
import com.neweraandroid.demo.Activity.Splash.RefreshTokenListener;
import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.Model.InitialTokenResponse;
import com.neweraandroid.demo.Model.InitiateResponse;
import com.neweraandroid.demo.Model.PrimaryTokenErrorResponse;
import com.neweraandroid.demo.Model.PrimaryTokenResponse;
import com.neweraandroid.demo.Model.RenewTokenResponse;
import com.neweraandroid.demo.Model.SignUpErrorResponse;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shahab on 1/11/2018.
 */

public class MedlynkRequests {

    public static void getInitialToken(final InitialTokenListener initialTokenListener){
        HashMap<String, String> body = new HashMap<> (  );
        body.put ( Constants.GRANT_TYPE, "client_credentials" );
        body.put ( Constants.CLIENT_ID, "1" );
        body.put ( Constants.CLIENT_SECRET,  "ULzAB1puUoTGjcxH9mutov8q3WWPyNEbtlQSZ2RR");
        Call<InitialTokenResponse> call = MedlynkRestAPI.getSimpleRetrofit ().getInitialToken ( body );
        call.enqueue ( new Callback<InitialTokenResponse> () {
            @Override
            public void onResponse(Call<InitialTokenResponse> call, Response<InitialTokenResponse> response) {
                if( response.isSuccessful () ){
                    initialTokenListener.onInitialTokenSuccess ( response );
                } else {
                    initialTokenListener.onInitialTokenFailure ( Constants.EXCEPTION_TYPE.BAD_EXCEPTION );
                }
            }
            @Override
            public void onFailure(Call<InitialTokenResponse> call, Throwable t) {
                if( t instanceof SocketTimeoutException ){
                    initialTokenListener.onInitialTokenFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                } else
                    initialTokenListener.onInitialTokenFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
        } );
    }

    public static void getPrimaryAccessToken(final PrimaryAccessTokenListener primaryAccessTokenListener, String username, String password){
        HashMap<String, String> body = new HashMap<> (  );
        body.put ( Constants.USERNAME, username );
        body.put ( Constants.PASSWORD, password );
        body.put ( Constants.GRANT_TYPE, "password" );
        body.put ( Constants.CLIENT_SECRET, "yXH6oP5FoXYk9IOnH31gCSnylwzNcAKjNGEnWJUs" );
        body.put ( Constants.CLIENT_ID, "2" );
        Call<PrimaryTokenResponse> call = MedlynkRestAPI.getSimpleRetrofit ().getPrimaryToken ( body );
        call.enqueue ( new Callback<PrimaryTokenResponse> () {
            @Override
            public void onResponse(Call<PrimaryTokenResponse> call, Response<PrimaryTokenResponse> response) {
                if( response.isSuccessful () ){
                    primaryAccessTokenListener.onPrimaryAccessTokenSuccess (response.body ());
                }else{
                    Gson gson = new Gson ();
                    try {
                        PrimaryTokenErrorResponse errorResponse = gson.fromJson ( response.errorBody ().string (), PrimaryTokenErrorResponse.class );
                        primaryAccessTokenListener.onPrimaryAccessTokenFailure (errorResponse.getMessage (), Constants.EXCEPTION_TYPE.NO_EXCEPTION);
                    } catch (IOException e) {
                        e.printStackTrace ();
                        primaryAccessTokenListener.onPrimaryAccessTokenFailure ( Constants.EXCEPTION_TYPE.ERROR_RESPONSE_PARSING_EXCEPTION );
                    }
                }
            }
            @Override
            public void onFailure(Call<PrimaryTokenResponse> call, Throwable t) {
                if( t instanceof SocketTimeoutException){
                    primaryAccessTokenListener.onPrimaryAccessTokenFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                }else {
                    primaryAccessTokenListener.onPrimaryAccessTokenFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
            }
        } );
    }

    public static void getNewPrimaryToken(final RefreshTokenListener refreshTokenListener, String refreshToken){
        HashMap<String, String> body = new HashMap<> ( );
        body.put ( Constants.GRANT_TYPE, "refresh_token" );
        body.put ( Constants.Refresh_Token, refreshToken );
        body.put ( Constants.CLIENT_SECRET, "yXH6oP5FoXYk9IOnH31gCSnylwzNcAKjNGEnWJUs" );
        body.put ( Constants.CLIENT_ID, "2" );
        body.put ( Constants.SCOPE,  "*");
        Call<RenewTokenResponse> call = MedlynkRestAPI.getSimpleRetrofit ().getNewToken ( body );
        call.enqueue ( new Callback<RenewTokenResponse> () {
            @Override
            public void onResponse(Call<RenewTokenResponse> call, Response<RenewTokenResponse> response) {
                if( response.isSuccessful () ){
                    refreshTokenListener.onRefreshTokenSuccess ( response.body () );
                }else{
                    refreshTokenListener.onRefreshTokenFailure ( Constants.EXCEPTION_TYPE.BAD_EXCEPTION );
                }
            }
            @Override
            public void onFailure(Call<RenewTokenResponse> call, Throwable t) {
                if( t instanceof SocketTimeoutException ){
                    refreshTokenListener.onRefreshTokenFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                }else{
                    refreshTokenListener.onRefreshTokenFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
            }
        } );
    }

    //verifying who is trying to reset his/her password!
    public static void sendResetPasswordRequest(){

    }

    //reset password actually!
    public static void resetPasswordRequest(String token, String email, String password, String confirmedPassword){
        HashMap<String, String> body = new HashMap<> (  );
        body.put ( Constants.EMAIL, email );
        body.put ( Constants.PASSWORD, password );
        body.put ( Constants.CONFIRMED_PASSWORD, confirmedPassword );
        body.put ( Constants.TOKEN, token);
    }

    public static void signUpNewUser(Context context, final OnSignUpListener onSignUpListener, HashMap<String, String> body){
        Call<InitiateResponse> call = MedlynkRestAPI.getRegisterRetrofit ( context )
                .signUp ( body );
        call.enqueue ( new Callback<InitiateResponse> () {
            @Override
            public void onResponse(Call<InitiateResponse> call, Response<InitiateResponse> response) {
                if (response.isSuccessful ()) {
                    onSignUpListener.onSignUpSuccess ();
                } else {
                    Gson gson = new Gson ();
                    try {
                        SignUpErrorResponse errorResponse = gson.fromJson ( response.errorBody ().string (), SignUpErrorResponse.class );
                        onSignUpListener.onSignUpFailure ( errorResponse.getMessage (), Constants.EXCEPTION_TYPE.NO_EXCEPTION );
                    } catch (IOException e) {
                        e.printStackTrace ();
                        onSignUpListener.onSignUpFailure ( Constants.EXCEPTION_TYPE.ERROR_RESPONSE_PARSING_EXCEPTION );
                    }
                }
            }
            @Override
            public void onFailure(Call<InitiateResponse> call, Throwable t) {
                if( t instanceof SocketTimeoutException){
                    onSignUpListener.onSignUpFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                }else {
                    onSignUpListener.onSignUpFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
            }
        } );
    }
}