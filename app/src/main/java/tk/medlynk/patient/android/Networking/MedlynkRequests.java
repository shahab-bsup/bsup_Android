package tk.medlynk.patient.android.Networking;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.gson.Gson;

import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_1.OnFirstFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10.Follow_Up_Symptoms_10th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10.OnTenthFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_2.Follow_Up_Symptoms_2nd_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_2.OnSecondFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_3.Follow_Up_Symptoms_3rd_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_3.OnThirdFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_4.Follow_Up_Symptoms_4th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_4.OnFourthFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_5.Follow_Up_Symptoms_5th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_5.OnFifthFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_6.Follow_Up_Symptoms_6th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_6.OnSixthFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7.Follow_Up_Symptoms_7th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7.OnSeventhFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8.Follow_Up_Symptoms_8th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8.OnEighthFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9.Follow_Up_Symptoms_9th_Question;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9.OnNinthFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.Login.OnGetCurrentUserInfoListener;
import tk.medlynk.patient.android.Activity.Login.OnResendConfirmationListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_1.OnFirstAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_10.OnTenthAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_11.OnEleventhAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_12.OnTwelveAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13.OnThirteenAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_15.OnFifteenAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_16.OnSixteenAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_17.OnSeventeenAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_18.OnEighteenAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_19.OnNineteenAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_2.OnSecondAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_20.OnTwentyAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_21.OnTwentyOneAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_22.OnTwentyTwoAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_3.OnThirdAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_4.OnFourthAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_5.OnFifthAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_6.OnSixthAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_7.OnSeventhAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_8.OnEighthAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_9.OnNinthAnswerListener;
import tk.medlynk.patient.android.Activity.NoDoctorIdPage.OnNoDoctorIDPreferencesListener;
import tk.medlynk.patient.android.Activity.ResetPassword.OnResetPasswordListener;
import tk.medlynk.patient.android.Activity.SearchDoctor.OnSearchDoctorListener;
import tk.medlynk.patient.android.Activity.SelectDoctor.OnGetAppointmentsListener;
import tk.medlynk.patient.android.Activity.SendResetPasswordRequestActivity.OnSendResetPasswordRequestListener;
import tk.medlynk.patient.android.Activity.SignUp.OnSignUpListener;
import tk.medlynk.patient.android.Activity.Splash.InitialTokenListener;
import tk.medlynk.patient.android.Activity.Login.OnPrimaryAccessTokenListener;
import tk.medlynk.patient.android.Activity.Splash.RefreshTokenListener;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.CustomViews.SnackController;
import tk.medlynk.patient.android.Essentials.Utils;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.AppointmentsResponse;
import tk.medlynk.patient.android.Model.CurrentUserResponse;
import tk.medlynk.patient.android.Model.Errors;
import tk.medlynk.patient.android.Model.FollowUpResultResponse;
import tk.medlynk.patient.android.Model.InitialTokenResponse;
import tk.medlynk.patient.android.Model.InitiateResponse;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Model.PrimaryTokenErrorResponse;
import tk.medlynk.patient.android.Model.PrimaryTokenResponse;
import tk.medlynk.patient.android.Model.RenewTokenResponse;
import tk.medlynk.patient.android.Model.SearchDoctorResponse;
import tk.medlynk.patient.android.Model.SignUpErrorResponse;
import com.neweraandroid.demo.R;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shahab on 1/11/2018.
 */

public class MedlynkRequests {

    private static NewSymptomAnswerResponse twelveResponse;
    private static NewSymptomAnswerResponse fourteenAnswer;

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
    public static void sendResetPasswordRequest(Context context,
                                                final OnSendResetPasswordRequestListener listener,
                                                String s){
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
//                        listener.onSendResetPasswordRequestFailure ( errors.getMessage ( ), Constants.EXCEPTION_TYPE.NO_EXCEPTION );
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
    public static void resetPassword(Context context, String reset_token, String email, String password, final OnResetPasswordListener listener){
        HashMap<String, String> body = new HashMap<> (  );
        body.put ( Constants.EMAIL, email );
        body.put ( Constants.PASSWORD, password );
        body.put ( Constants.CONFIRMED_PASSWORD, password );
        body.put ( Constants.TOKEN, reset_token);

        Call<InitiateResponse> call = MedlynkRestAPI.getRegisterRetrofit ( context ).resetPassword ( body );
        call.enqueue ( new Callback<InitiateResponse> () {
            @Override
            public void onResponse(Call<InitiateResponse> call, Response<InitiateResponse> response) {
                if( response.isSuccessful () ){
                    listener.onResetPasswordSuccess (response.body ().getMessage ());
                }else{
                    Gson gson = new Gson ();
                    try {
                        Errors errorResponse = gson.fromJson ( response.errorBody ().string (), Errors.class );
//                        listener.onResetPasswordFailure ( errorResponse.getMessage ().toString (), Constants.EXCEPTION_TYPE.NO_EXCEPTION );
                    } catch (IOException e) {
                        e.printStackTrace ();
                        listener.onResetPasswordFailure ( Constants.EXCEPTION_TYPE.ERROR_RESPONSE_PARSING_EXCEPTION );
                    }
                }
            }

            @Override
            public void onFailure(Call<InitiateResponse> call, Throwable t) {
                if( t instanceof SocketTimeoutException){
                    listener.onResetPasswordFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                }else {
                    listener.onResetPasswordFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
            }
        } );
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
                        onSignUpListener.onSignUpFailure ( errorResponse, Constants.EXCEPTION_TYPE.NO_EXCEPTION );
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
//                        listener.onSearchDoctorFailure ( errors.getMessage (), Constants.EXCEPTION_TYPE.NO_EXCEPTION );
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

    public static void setUserPreferencesNoDoctorID(final Context context, final OnNoDoctorIDPreferencesListener listener, final HashMap<String, Object> body){
        if(!Utils.isDeviceConnected ( context )){
            SnackController.getInstance ().init ( context, context.getString ( R.string.no_intenet_connection ) )
                    .setAction ( context.getString ( R.string.try_again ), new View.OnClickListener () {
                        @Override
                        public void onClick(View view) {
                            setUserPreferencesNoDoctorID ( context, listener, body );
                        }
                    } );
        }
        Call<Boolean> call = MedlynkRestAPI.getMainRetrofit ( context ).setUserPreferences ( body );
        call.enqueue ( new Callback<Boolean> () {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if( response.isSuccessful () ){
                    listener.onNoDoctorIDSuccess (response.body ());
                }else{
                    listener.onNoDoctorIDFailure ();
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                if( t instanceof SocketTimeoutException ){
                    listener.onNoDoctorIDFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                }else{
                    listener.onNoDoctorIDFailure ();
                }
            }
        } );
    }

    public static void getCurrentUserInfo(Context context,
                                          final OnGetCurrentUserInfoListener listener){
        Call<CurrentUserResponse> call = MedlynkRestAPI.getMainRetrofit ( context ).getCurrentUserInfo ();
        call.enqueue ( new Callback<CurrentUserResponse> () {
            @Override
            public void onResponse(Call<CurrentUserResponse> call, Response<CurrentUserResponse> response) {
                if( response.isSuccessful () ){
                    listener.onGetCurrentUserInfoSuccess (response.body ());
                }else{
                    listener.onGetCurrentUserInfoFailure ( Constants.EXCEPTION_TYPE.NO_EXCEPTION );
                }
            }
            @Override
            public void onFailure(Call<CurrentUserResponse> call, Throwable t) {
                if( t instanceof SocketTimeoutException ){
                    listener.onGetCurrentUserInfoFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                }else{
                    listener.onGetCurrentUserInfoFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
            }
        } );
    }

    public static void getAppointments(Context context, final OnGetAppointmentsListener listener, String doctorId){
        HashMap<String, String> body = new HashMap<> (  );
        body.put ( Constants.PROVIDER_ID, doctorId );
        Call<AppointmentsResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .getAppointments ( body );
        call.enqueue ( new Callback<AppointmentsResponse> () {
            @Override
            public void onResponse(Call<AppointmentsResponse> call, Response<AppointmentsResponse> response) {
                if( response.isSuccessful () ){
                    listener.onGetAppointmentSuccess (response.body ());
                }else{

                }
            }

            @Override
            public void onFailure(Call<AppointmentsResponse> call, Throwable t) {

            }
        } );
    }

    public static void newSymptomFirstQuestionAnswer(Context context, final OnFirstAnswerListener listener, int appointmentID, Answer answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "1" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if( response.isSuccessful () ){
                    listener.onFirstAnswerSuccess ( response.body () );
                }else{

                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onFirstAnswerFailure ();
            }
        } );
    }

    public static void newSymptomSecondQuestionAnswer(Context context, final OnSecondAnswerListener listener, int appointmentID, Answer answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "2" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onSecondAnswerSuccess ( response.body () );

                }else{
                    listener.onSecondAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onSecondAnswerFailure ();
            }
        } );
    }

    public static void newSymptomThirdQuestionAnswer(Context context, final OnThirdAnswerListener listener, int appointmentID, Answer answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "3" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onThirdAnswerSuccess ( response.body () );

                }else{
                    listener.onThirdAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onThirdAnswerFailure ();
            }
        } );
    }

    public static void newSymptomFourthQuestionAnswer(Context context, final OnFourthAnswerListener listener, int appointmentID, Answer answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "4" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onFourthAnswerSuccess ( response.body () );

                }else{
                    listener.onFourthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onFourthAnswerFailure ();
            }
        } );
    }

    public static void newSymptomFifthQuestionAnswer(Context context, final OnFifthAnswerListener listener, int appointmentID, Answer answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "5" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onFifthAnswerSuccess ( response.body () );

                }else{
                    listener.onFifthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onFifthAnswerFailure ();
            }
        } );
    }

    public static void newSymptomSixthQuestionAnswer(Context context, final OnSixthAnswerListener listener, int appointmentID, Answer answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "6" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onSixthAnswerSuccess ( response.body () );

                }else{
                    listener.onSixthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onSixthAnswerFailure ();
            }
        } );
    }

    public static void newSymptomSeventhQuestionAnswer(Context context, final OnSeventhAnswerListener listener, int appointmentID, Answer answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "7" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onSeventhAnswerSuccess ( response.body () );

                }else{
                    listener.onSeventhAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onSeventhAnswerFailure ();
            }
        } );
    }

    public static void newSymptomEighthQuestionAnswer(Context context, final OnEighthAnswerListener listener, int appointmentID, List<Answer> answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "8" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onEighthAnswerSuccess ( response.body () );

                }else{
                    listener.onEightAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onEightAnswerFailure ();
            }
        } );
    }

    public static void newSymptomNinthQuestionAnswer(Context context, final OnNinthAnswerListener listener, int appointmentID, List<Answer> answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "9" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onNinthAnswerSuccess ( response.body () );
                }else{
                    listener.onNinthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onNinthAnswerFailure ();
            }
        } );
    }

    public static void newSymptomTenthQuestionAnswer(Context context, final OnTenthAnswerListener listener, int appointmentID, Answer answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "10" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onTenthAnswerSuccess ( response.body () );
                }else{
                    listener.onTenthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onTenthAnswerFailure ();
            }
        } );
    }

    public static void newSymptomEleventhQuestionAnswer(Context context, final OnEleventhAnswerListener listener, int appointmentID, Answer answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "11" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onEleventhAnswerSuccess ( response.body () );
                }else{
                    listener.onEleventhAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onEleventhAnswerFailure ();
            }
        } );
    }

    public static void newSymptomTwelveQuestionAnswer(Context context,
                                                      final OnTwelveAnswerListener listener, int appointmentID,
                                                      List<Answer> answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "12" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                twelveResponse = response.body ();
                if ( response.isSuccessful () ){
                    listener.onTwelveAnswerSuccess ( response.body () );
                }else{
                    listener.onTwelveAnswerFailure ();
                }
            }
            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
//                listener.onTwelveAnswerFailure ();
                listener.onTwelveAnswerSuccess ( twelveResponse );
            }
        } );
    }

    public static void newSymptomFifteenQuestionAnswer(Context context, final OnFifteenAnswerListener listener, int appointmentID, Answer answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "15" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onFifteenAnswerResponse ( response.body () );
                }else{
                    listener.onFifteenAnswerFailure ();
                }
            }
            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onFifteenAnswerFailure ();
            }
        } );
    }

    public static void newSymptomSixteenQuestionAnswer(Context context, final OnSixteenAnswerListener listener, int appointmentID, Answer answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "16" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onSixteenAnswerSuccess ( response.body () );
                }else{
                    listener.onSixteenAnswerFailure ();
                }
            }
            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onSixteenAnswerFailure ();
            }
        } );
    }

    public static void newSymptomSeventeenQuestionAnswer (Context context, final OnSeventeenAnswerListener listener, int appointmentID, List<Answer> answer) {
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "17" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onSeventeenAnswerSuccess ( response.body () );
                }else{
                    listener.onSeventeenAnswerFailure ();
                }
            }
            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onSeventeenAnswerFailure ();
            }
        } );
    }

    public static void newSymptomEighteenQuestionAnswer(Context context, final OnEighteenAnswerListener listener, int appointmentID, Answer answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "18" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onEighteenAnswerSuccess ( response.body () );
                }else{
                    listener.onEighteenAnswerFailure ();
                }
            }
            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onEighteenAnswerFailure ();
            }
        } );
    }

    public static void newSymptomNineteenQuestionAnswer(Context context, final OnNineteenAnswerListener listener, int appointmentID, Answer answer){
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "19" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID,
                        Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if ( response.isSuccessful () ){
                    listener.onNineteenAnswerSuccess ( response.body () );
                }else{
                    listener.onNineteenAnswerFailure ();
                }
            }
            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onNineteenAnswerFailure ();
            }
        } );
    }

    public static void newSymptomTwentyOneQuestionAnswer(Context context,
                                                         final OnTwentyOneAnswerListener listener,
                                                         int appointmentID,
                                                         ArrayList<Answer> answers) {

        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "21" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answers );
        Call<NewSymptomAnswerResponse>  call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID,
                        Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if( response.isSuccessful () ){
                    listener.onTwentyOneAnswerSuccess ( response.body () );
                }else{
                    listener.onTwentyOneAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onTwentyOneAnswerFailure ();
            }
        } );
    }

    public static void newSymptomTwentyQuestionAnswer(Context context,
                                                      final OnTwentyAnswerListener listener,
                                                      int appointmentID,
                                                      Answer answer) {
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "20" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.
                getMainRetrofit ( context ).
                newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if( response.isSuccessful () ){
                    listener.onTwentyAnswerSuccess ( response.body () );
                }else{
                    listener.onTwentyAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onTwentyAnswerFailure ();
            }
        } );
    }

    public static void newSymptomThirteenQuestionAnswer(Context context,
                                                        final OnThirteenAnswerListener listener,
                                                        int appointmentID,
                                                        Answer answer) {
        List<Answer> answers = new ArrayList<> (  );
        answers.add ( answer );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "13" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answers );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                fourteenAnswer = response.body ();
                if( response.isSuccessful () ){
                    listener.onThirteenAnswerSuccess ( response.body () );
                }else{
                    listener.onThirteenAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onThirteenAnswerSuccess ( fourteenAnswer );
                //                listener.onThirteenAnswerFailure ();
            }
        } );
    }

    public static void newSymptomTwentyTwoQuestionAnswer(Context context, final OnTwentyTwoAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomTwentyTwoQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "22" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if( response.isSuccessful () ){
                    listener.onTwentyTwoAnswerSuccess ( response.body () );
                }else{
                    listener.onTwentyTwoAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onTwentyTwoAnswerFailure ();
            }
        } );
    }

    public static void followUpResultFirstAnswer(Context context, final OnFirstFollowUpAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.followUpResultFirstAnswer" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.QUESTION_NUMBER, "1" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpResultResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID, Constants.FOLLOW_UP_RESULT_BODY );
        call.enqueue ( new Callback<FollowUpResultResponse> () {
            @Override
            public void onResponse(Call<FollowUpResultResponse> call, Response<FollowUpResultResponse> response) {
                if( response.isSuccessful () ){
                    System.out.println ( "MedlynkRequests.onResponse" );
                    listener.onFirstAnswerSuccess ( response.body () );
                }else {
                    listener.onFirstAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpResultResponse> call, Throwable t) {
                listener.onFirstAnswerFailure ();
            }
        } );
    }

    public static void followUpResultSecondAnswer(Context context, final OnSecondFollowUpAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.followUpResultSecondAnswer" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.QUESTION_NUMBER, "2" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpResultResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID, Constants.FOLLOW_UP_RESULT_BODY );
        call.enqueue ( new Callback<FollowUpResultResponse> () {
            @Override
            public void onResponse(Call<FollowUpResultResponse> call, Response<FollowUpResultResponse> response) {
                if( response.isSuccessful () ){
                    listener.onSecondAnswerSuccess ( response.body () );
                }else{
                    listener.onSecondAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpResultResponse> call, Throwable t) {
                System.out.println ( "MedlynkRequests.onFailure" );
                listener.onSecondAnswerFailure ();
            }
        } );
    }

    public static void followUpResultThirdAnswer(Context context,
                                                 int appointmentID,
                                                 final OnThirdFollowUpAnswerListener listener,
                                                 Answer answer) {
        System.out.println ( "MedlynkRequests.followUpResultThirdAnswer" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.QUESTION_NUMBER, "3" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpResultResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID,
                        Constants.FOLLOW_UP_RESULT_BODY );
        call.enqueue ( new Callback<FollowUpResultResponse> () {
            @Override
            public void onResponse(Call<FollowUpResultResponse> call, Response<FollowUpResultResponse> response) {
                if( response.isSuccessful () ){
                    listener.onThirdAnswerSuccess ( response.body () );
                }else{
                    listener.onThirdAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpResultResponse> call, Throwable t) {
                listener.onThirdAnswerFailure ();
            }
        } );
    }

    public static void followUpResultFourthAnswer(Context context, int appointmentID, final OnFourthFollowUpAnswerListener listener, Answer answer) {
        System.out.println ( "MedlynkRequests.followUpResultFourthAnswer" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.QUESTION_NUMBER, "4" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpResultResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID,
                        Constants.FOLLOW_UP_RESULT_BODY);
        call.enqueue ( new Callback<FollowUpResultResponse> () {
            @Override
            public void onResponse(Call<FollowUpResultResponse> call, Response<FollowUpResultResponse> response) {
                if( response.isSuccessful () ){
                    listener.onFourthAnswerSuccess ( response.body () );
                }else{
                    listener.onFourthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpResultResponse> call, Throwable t) {
                listener.onFourthAnswerFailure ();
            }
        } );
    }

    public static void followUpResultFifthAnswer(Context context,
                                                 int appointmentID,
                                                 final OnFifthFollowUpAnswerListener listener,
                                                 Answer answer) {
        System.out.println ( "MedlynkRequests.followUpResultFifthAnswer" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.QUESTION_NUMBER, "5" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpResultResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID,  Constants.FOLLOW_UP_RESULT_BODY);
        call.enqueue ( new Callback<FollowUpResultResponse> () {
            @Override
            public void onResponse(Call<FollowUpResultResponse> call, Response<FollowUpResultResponse> response) {
                if( response.isSuccessful () ){
                    listener.onFifthAnswerSuccess ( response.body () );
                }else{
                    listener.onFifthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpResultResponse> call, Throwable t) {
                listener.onFifthAnswerFailure ();
            }
        } );
    }

    public static void followUpResultSixthAnswer(Context context, int appointmentID, final OnSixthFollowUpAnswerListener listener, Answer answer) {
        System.out.println ( "MedlynkRequests.followUpResultSixthAnswer" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.QUESTION_NUMBER, "6" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpResultResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID,  Constants.FOLLOW_UP_RESULT_BODY);
        call.enqueue ( new Callback<FollowUpResultResponse> () {
            @Override
            public void onResponse(Call<FollowUpResultResponse> call, Response<FollowUpResultResponse> response) {
                if( response.isSuccessful () ){
                    listener.onSixthAnswerSuccess ( response.body () );
                }else{
                    listener.onSixthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpResultResponse> call, Throwable t) {
                listener.onSixthAnswerFailure ();
            }
        } );
    }

    public static void followUpResultSeventhAnswer(Context context, int appointmentID, final OnSeventhFollowUpAnswerListener listener, Answer answer) {
        System.out.println ( "MedlynkRequests.followUpResultSeventhAnswer" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.QUESTION_NUMBER, "7" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpResultResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID, Constants.FOLLOW_UP_RESULT_BODY );
        call.enqueue ( new Callback<FollowUpResultResponse> () {
            @Override
            public void onResponse(Call<FollowUpResultResponse> call, Response<FollowUpResultResponse> response) {
                if( response.isSuccessful () ){
                    listener.onSeventhAnswerSuccess ( response.body () );
                }else{
                    listener.onSeventhAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpResultResponse> call, Throwable t) {
                listener.onSeventhAnswerFailure ();
            }
        } );
    }

    public static void followUpResultEightAnswer(Context context, int appointmentID, final OnEighthFollowUpAnswerListener listener, Answer answer) {
        System.out.println ( "MedlynkRequests.followUpResultEightAnswer" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.QUESTION_NUMBER, "8" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpResultResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID, Constants.FOLLOW_UP_RESULT_BODY );
        call.enqueue ( new Callback<FollowUpResultResponse> () {
            @Override
            public void onResponse(Call<FollowUpResultResponse> call, Response<FollowUpResultResponse> response) {
                if( response.isSuccessful () ){
                    listener.onEighthAnswerSuccess ( response.body () );
                }else{
                    listener.onEightAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpResultResponse> call, Throwable t) {
                listener.onEightAnswerFailure ();
            }
        } );

    }

    public static void followUpResultNinthAnswer(Context context, int appointmentID, final OnNinthFollowUpAnswerListener listener, Answer answer) {
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.QUESTION_NUMBER, "9" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpResultResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID, Constants.FOLLOW_UP_RESULT_BODY );
        call.enqueue ( new Callback<FollowUpResultResponse> () {
            @Override
            public void onResponse(Call<FollowUpResultResponse> call, Response<FollowUpResultResponse> response) {
                if( response.isSuccessful () ){
                    listener.onNinthAnswerSuccess ( response.body () );
                }else{
                    listener.onNinthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpResultResponse> call, Throwable t) {
                listener.onNinthAnswerFailure ();
            }
        } );
    }

    public static void followUpResultNinthAnswer(Context context, int appointmentID, final OnNinthFollowUpAnswerListener listener, List<Answer> answers) {
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.QUESTION_NUMBER, "9" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.ANSWER, answers );
        Call<FollowUpResultResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID, Constants.FOLLOW_UP_RESULT_BODY );
        call.enqueue ( new Callback<FollowUpResultResponse> () {
            @Override
            public void onResponse(Call<FollowUpResultResponse> call, Response<FollowUpResultResponse> response) {
                if( response.isSuccessful () ){
                    listener.onNinthAnswerSuccess ( response.body () );
                }else{
                    listener.onNinthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpResultResponse> call, Throwable t) {
                listener.onNinthAnswerFailure ();
            }
        } );
    }

    public static void followUpResultTenthAnswer(Context context, int appointmentID, final OnTenthFollowUpAnswerListener listener, List<Answer> answers) {
        System.out.println ( "MedlynkRequests.followUpResultTenthAnswer" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.QUESTION_NUMBER, "10" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.ANSWER, answers );
        Call<FollowUpResultResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID, Constants.FOLLOW_UP_RESULT_BODY );
        call.enqueue ( new Callback<FollowUpResultResponse> () {
            @Override
            public void onResponse(Call<FollowUpResultResponse> call, Response<FollowUpResultResponse> response) {
                if( response.isSuccessful () ){
                    listener.onTenthAnswerSuccess ( response.body () );
                }else{
                    listener.onTenthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpResultResponse> call, Throwable t) {
                listener.onTenthAnswerFailure ();
            }
        } );
    }

    public static void followUpResultTenthAnswer(Context context, int appointmentID, final OnTenthFollowUpAnswerListener listener, Answer answer) {
        System.out.println ( "MedlynkRequests.followUpResultTenthAnswer" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.QUESTION_NUMBER, "10" );
        Constants.FOLLOW_UP_RESULT_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpResultResponse> call = MedlynkRestAPI.getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID, Constants.FOLLOW_UP_RESULT_BODY );
        call.enqueue ( new Callback<FollowUpResultResponse> () {
            @Override
            public void onResponse(Call<FollowUpResultResponse> call, Response<FollowUpResultResponse> response) {
                if( response.isSuccessful () ){
                    listener.onTenthAnswerSuccess ( response.body () );
                }else{
                    listener.onTenthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpResultResponse> call, Throwable t) {
                listener.onTenthAnswerFailure ();
            }
        } );
    }
}