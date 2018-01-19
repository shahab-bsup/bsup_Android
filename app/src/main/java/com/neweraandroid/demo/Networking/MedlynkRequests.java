package com.neweraandroid.demo.Networking;

import android.content.Context;
import android.icu.text.IDNA;

import com.google.gson.Gson;
import com.neweraandroid.demo.Activity.Login.OnResendConfirmationListener;
import com.neweraandroid.demo.Activity.SearchDoctor.OnSearchDoctorListener;
import com.neweraandroid.demo.Activity.SendResetPasswordRequestActivity.OnSendResetPasswordRequestListener;
import com.neweraandroid.demo.Activity.SignUp.OnSignUpListener;
import com.neweraandroid.demo.Activity.Splash.InitialTokenListener;
import com.neweraandroid.demo.Activity.Login.OnPrimaryAccessTokenListener;
import com.neweraandroid.demo.Activity.Splash.RefreshTokenListener;
import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.Model.Errors;
import com.neweraandroid.demo.Model.InitialTokenResponse;
import com.neweraandroid.demo.Model.InitiateResponse;
import com.neweraandroid.demo.Model.PrimaryTokenErrorResponse;
import com.neweraandroid.demo.Model.PrimaryTokenResponse;
import com.neweraandroid.demo.Model.RenewTokenResponse;
import com.neweraandroid.demo.Model.SearchDoctorResponse;
import com.neweraandroid.demo.Model.SignUpErrorResponse;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

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

    public static void getPrimaryAccessToken(final OnPrimaryAccessTokenListener primaryAccessTokenListener, String username, String password){
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
    public static void sendResetPasswordRequest(Context context, final OnSendResetPasswordRequestListener listener, String s){
        Map<String, String> body = new HashMap<> (  );
        body.put ( Constants.EMAIL, s );
        Call<InitiateResponse> call = MedlynkRestAPI.getRegisterRetrofit (context).sendResetPassWordRequest ( body );
        call.enqueue ( new Callback<InitiateResponse> () {
            @Override
            public void onResponse(Call<InitiateResponse> call, Response<InitiateResponse> response) {
                if( response.isSuccessful () ){
                    listener.onSendResetPasswordRequestSuccess ();
                }else{
                    Gson gson = new Gson ();
                    try {
                        Errors errors = gson.fromJson ( response.errorBody ().string (), Errors.class );
                        listener.onSendResetPasswordRequestFailure ( errors.getMessage ( ), Constants.EXCEPTION_TYPE.NO_EXCEPTION );
                    } catch (IOException e) {
                        listener.onSendResetPasswordRequestFailure ( Constants.EXCEPTION_TYPE.ERROR_RESPONSE_PARSING_EXCEPTION );
                        e.printStackTrace ();
                    }
                }
            }

            @Override
            public void onFailure(Call<InitiateResponse> call, Throwable t) {
                if( t instanceof SocketTimeoutException){
                    listener.onSendResetPasswordRequestFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                }else {
                    listener.onSendResetPasswordRequestFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
            }
        } );
    }

    //reset password actually!!!
    public static void resetPasswordRequest(String token, String email, String password, String confirmedPassword){
        HashMap<String, String> body = new HashMap<> (  );
        body.put ( Constants.EMAIL, email );
        body.put ( Constants.PASSWORD, password );
        body.put ( Constants.CONFIRMED_PASSWORD, confirmedPassword );
        body.put ( Constants.TOKEN, token);
    }

    public static void signUp(Context context, final OnSignUpListener onSignUpListener, HashMap<String, Object> body){
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
                        onSignUpListener.onSignUpFailure ( errorResponse.getErrors ().getEmail ().get ( 0 ).toString (), Constants.EXCEPTION_TYPE.NO_EXCEPTION );
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

    public static void resendConfirmationEmail(Context context, final OnResendConfirmationListener listener, String email){
        Map<String, String> body = new HashMap<> (  );
        body.put ( Constants.EMAIL, email );
        Call<InitiateResponse> call = MedlynkRestAPI.getRegisterRetrofit ( context ).resendConfirmationEmail ( body );
        call.enqueue ( new Callback<InitiateResponse> () {
            @Override
            public void onResponse(Call<InitiateResponse> call, Response<InitiateResponse> response) {
                if( response.isSuccessful () ){
                    listener.onResendConfirmationLinkSuccess ();
                }else{
                    //TODO something for god sake!
                }
            }

            @Override
            public void onFailure(Call<InitiateResponse> call, Throwable t) {

            }
        } );
    }

    public static void searchDoctor(Context context, String doctorID, final OnSearchDoctorListener listener){
        Call<SearchDoctorResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .searchDoctor ( doctorID );
        call.enqueue ( new Callback<SearchDoctorResponse> () {
            @Override
            public void onResponse(Call<SearchDoctorResponse> call, Response<SearchDoctorResponse> response) {
                if (  response.isSuccessful ()){
                    listener.onSearchDoctorSuccess (response.body ());
                }else{
                    Gson gson = new Gson ();
                    try {
                        Errors errors = gson.fromJson ( response.errorBody ().string (), Errors.class );
                        listener.onSearchDoctorFailure ( errors.getMessage (), Constants.EXCEPTION_TYPE.NO_EXCEPTION );
                    } catch (IOException e) {
                        listener.onSearchDoctorFailure ( Constants.EXCEPTION_TYPE.ERROR_RESPONSE_PARSING_EXCEPTION );
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchDoctorResponse> call, Throwable t) {
                if( t instanceof SocketTimeoutException){
                    listener.onSearchDoctorFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                }else {
                    listener.onSearchDoctorFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
            }
        } );
    }
}