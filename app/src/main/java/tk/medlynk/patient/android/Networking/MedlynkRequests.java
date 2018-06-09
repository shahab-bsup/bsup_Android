package tk.medlynk.patient.android.Networking;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.neweraandroid.demo.R;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.FollowUpSymptomsActivity;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_1.OnFirstFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_10.OnTenthFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_11.OnEleventFollowUphAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_12.OnFollowUpTwelveAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_13.OnThirteenFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_14.OnFourteenFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_15.OnFifteenFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_2.OnSecondFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_3.OnThirdFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_4.OnFourthFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_5.OnFifthFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_6.OnSixthFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_7.OnSeventhFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_8.OnEighthFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.FollowUpSymptoms.fragments.Question_9.OnNinthFollowUpAnswerListener;
import tk.medlynk.patient.android.Activity.Login.OnGetCurrentUserInfoListener;
import tk.medlynk.patient.android.Activity.Login.OnPrimaryAccessTokenListener;
import tk.medlynk.patient.android.Activity.Login.OnResendConfirmationListener;
import tk.medlynk.patient.android.Activity.NewSymptom.OnNewSymptomAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_11.OnEleventhAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_13.OnThirteenAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_14.OnFourteenAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_15.OnFifteenAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_16.OnSixteenAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_17.OnSeventeenAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_18.OnEighteenAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_19.OnNineteenAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_20.OnTwentyAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_21.OnTwentyOneAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_22.OnTwentyTwoAnswerListener;
import tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_23.OnTwentyThreeAnswerListener;
import tk.medlynk.patient.android.Activity.NoDoctorIdPage.OnNoDoctorIDPreferencesListener;
import tk.medlynk.patient.android.Activity.ResetPassword.OnResetPasswordListener;
import tk.medlynk.patient.android.Activity.SearchDoctor.OnSearchDoctorListener;
import tk.medlynk.patient.android.Activity.SelectDoctor.OnGetAppointmentsListener;
import tk.medlynk.patient.android.Activity.SendResetPasswordRequestActivity.OnSendResetPasswordRequestListener;
import tk.medlynk.patient.android.Activity.SignUp.OnSignUpListener;
import tk.medlynk.patient.android.Activity.Splash.InitialTokenListener;
import tk.medlynk.patient.android.Activity.Splash.RefreshTokenListener;
import tk.medlynk.patient.android.Constants;
import tk.medlynk.patient.android.CustomViews.SnackController;
import tk.medlynk.patient.android.Essentials.Utils;
import tk.medlynk.patient.android.Model.AccessTokenResponse;
import tk.medlynk.patient.android.Model.Answer;
import tk.medlynk.patient.android.Model.AppointmentsResponse;
import tk.medlynk.patient.android.Model.CurrentUserResponse;
import tk.medlynk.patient.android.Model.ErrorResponse;
import tk.medlynk.patient.android.Model.Errors;
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Model.InitialTokenResponse;
import tk.medlynk.patient.android.Model.InitiateResponse;
import tk.medlynk.patient.android.Model.Medication;
import tk.medlynk.patient.android.Model.MotherCallback;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Model.PreviuosDoctorsResponse;
import tk.medlynk.patient.android.Model.PrimaryTokenErrorResponse;
import tk.medlynk.patient.android.Model.RenewTokenResponse;
import tk.medlynk.patient.android.Model.SearchDoctorResponse;
import tk.medlynk.patient.android.Model.SymptomResponse;

/**
 * Created by Shahab on 1/11/2018.
 */

public class MedlynkRequests {

    private final static String TAG = MedlynkRequests.class.getSimpleName ();

    public static int numberOfMethods = 0;

    public static void getInitialToken(final InitialTokenListener initialTokenListener) {
        numberOfMethods++;
        HashMap<String, String> body = new HashMap<> ();
        body.put ( Constants.GRANT_TYPE, "client_credentials" );
        body.put ( Constants.CLIENT_ID, "1" );
        body.put ( Constants.CLIENT_SECRET, "ULzAB1puUoTGjcxH9mutov8q3WWPyNEbtlQSZ2RR" );
        Log.d ( TAG, "getInitialToken: "  + Thread.currentThread ().getName () );
        Call<InitialTokenResponse> call = MedlynkRestAPI.getInstance()
                .getSimpleRetrofit ().getInitialToken ( body );
        call.enqueue ( new Callback<InitialTokenResponse> () {
            @Override
            public void onResponse(Call<InitialTokenResponse> call, Response<InitialTokenResponse> response) {
                if (response.isSuccessful ()) {
                    Log.d ( TAG, "onResponse: " +
                            Thread.currentThread ().getName () );
                    initialTokenListener.onInitialTokenSuccess ( response );
                } else {
                    initialTokenListener.onInitialTokenFailure ( Constants.EXCEPTION_TYPE.BAD_EXCEPTION );
                }
            }

            @Override
            public void onFailure(Call<InitialTokenResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    initialTokenListener.onInitialTokenFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                } else
                    initialTokenListener.onInitialTokenFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
            }
        } );
    }

    public static void getAccessToken(final OnPrimaryAccessTokenListener primaryAccessTokenListener,
                                      String username,
                                      String password) {
        numberOfMethods++;

        HashMap<String, String> body = new HashMap<> ();
        body.put ( Constants.USERNAME, username );
        body.put ( Constants.PASSWORD, password );
        body.put ( Constants.GRANT_TYPE, "password" );
        body.put ( Constants.CLIENT_SECRET,
                "yXH6oP5FoXYk9IOnH31gCSnylwzNcAKjNGEnWJUs" );
        body.put ( Constants.CLIENT_ID, "2" );
        Call<AccessTokenResponse> call = MedlynkRestAPI.getInstance().getInstance().getSimpleRetrofit ().
                getPrimaryToken ( body );
        call.enqueue ( new Callback<AccessTokenResponse> () {
            @Override
            public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {
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

    public static void getNewPrimaryToken(final RefreshTokenListener refreshTokenListener,
                                          String refreshToken) {
        numberOfMethods++;

        HashMap<String, String> body = new HashMap<> ();
        body.put ( Constants.GRANT_TYPE, "refresh_token" );
        body.put ( Constants.Refresh_Token, refreshToken );
        body.put ( Constants.CLIENT_SECRET, "yXH6oP5FoXYk9IOnH31gCSnylwzNcAKjNGEnWJUs" );
        body.put ( Constants.CLIENT_ID, "2" );
        body.put ( Constants.SCOPE, "" );
        Call<RenewTokenResponse> call = MedlynkRestAPI.getInstance().
                getSimpleRetrofit ().
                getNewToken ( body );
        call.enqueue ( new Callback<RenewTokenResponse> () {
            @Override
            public void onResponse(Call<RenewTokenResponse> call, Response<RenewTokenResponse> response) {
                if (response.isSuccessful ()) {
                    refreshTokenListener.onRefreshTokenSuccess ( response.body () );
                } else {
                    refreshTokenListener.onRefreshTokenFailure ( Constants.EXCEPTION_TYPE.BAD_EXCEPTION );
                }
            }

            @Override
            public void onFailure(Call<RenewTokenResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    refreshTokenListener.onRefreshTokenFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                } else {
                    refreshTokenListener.onRefreshTokenFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
            }
        } );
    }

    //verifying who is trying to reset his/her password!
    public static void sendResetPasswordRequest(Context context,
                                                final OnSendResetPasswordRequestListener listener,
                                                String s) {
        numberOfMethods++;

        Map<String, String> body = new HashMap<> ();
        body.put ( Constants.EMAIL, s );
        Call<InitiateResponse> call = MedlynkRestAPI.getInstance().
                getClientRetrofit ( context ).sendResetPassWordRequest ( body );
        call.enqueue ( new Callback<InitiateResponse> () {
            @Override
            public void onResponse(Call<InitiateResponse> call, Response<InitiateResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onSendResetPasswordRequestSuccess ();
                } else {
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
                if (t instanceof SocketTimeoutException) {
                    listener.onSendResetPasswordRequestFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                } else {
                    listener.onSendResetPasswordRequestFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
            }
        } );
    }

    //reset password actually!!!
    public static void resetPassword(Context context, String reset_token, String email, String password, final OnResetPasswordListener listener) {
        numberOfMethods++;

        HashMap<String, String> body = new HashMap<> ();
        body.put ( Constants.EMAIL, email );
        body.put ( Constants.PASSWORD, password );
        body.put ( Constants.CONFIRMED_PASSWORD, password );
        body.put ( Constants.TOKEN, reset_token );

        Call<InitiateResponse> call = MedlynkRestAPI.getInstance().getClientRetrofit ( context ).resetPassword ( body );
        call.enqueue ( new Callback<InitiateResponse> () {
            @Override
            public void onResponse(Call<InitiateResponse> call, Response<InitiateResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onResetPasswordSuccess ( response.body ().getMessage () );
                } else {
                    Gson gson = new Gson ();
                    try {
                        ErrorResponse errorResponse = gson.fromJson ( response.errorBody ().string (), ErrorResponse.class );
                        listener.onResetPasswordFailure ( errorResponse, Constants.EXCEPTION_TYPE.NO_EXCEPTION );
                    } catch (IOException e) {
                        e.printStackTrace ();
                        listener.onResetPasswordFailure ( Constants.EXCEPTION_TYPE.ERROR_RESPONSE_PARSING_EXCEPTION );
                    }
                }
            }

            @Override
            public void onFailure(Call<InitiateResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    listener.onResetPasswordFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                } else {
                    listener.onResetPasswordFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
            }
        } );
    }

    public static void signUp(Context context, final OnSignUpListener onSignUpListener,
                              HashMap<String, Object> body) {
        numberOfMethods++;

        Call<InitiateResponse> call = MedlynkRestAPI.getInstance().getClientRetrofit ( context )
                .signUp ( body );
        call.enqueue ( new Callback<InitiateResponse> () {
            @Override
            public void onResponse(Call<InitiateResponse> call, Response<InitiateResponse> response) {
                if (response.isSuccessful ()) {
                    onSignUpListener.onSignUpSuccess ();
                } else {
                    Gson gson = new Gson ();
                    try {
                        ErrorResponse errorResponse = gson.fromJson ( response.errorBody ().string (), ErrorResponse.class );
                        onSignUpListener.onSignUpFailure ( errorResponse, Constants.EXCEPTION_TYPE.NO_EXCEPTION );
                    } catch (IOException e) {
                        e.printStackTrace ();
                        onSignUpListener.onSignUpFailure ( Constants.EXCEPTION_TYPE.ERROR_RESPONSE_PARSING_EXCEPTION );
                    }
                }
            }

            @Override
            public void onFailure(Call<InitiateResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    onSignUpListener.onSignUpFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                } else {
                    onSignUpListener.onSignUpFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
            }
        } );
    }

    public static void resendConfirmationEmail(Context context, final OnResendConfirmationListener listener, String email) {
        numberOfMethods++;

        Map<String, String> body = new HashMap<> ();
        body.put ( Constants.EMAIL, email );
        Call<InitiateResponse> call = MedlynkRestAPI.getInstance().getClientRetrofit ( context ).resendConfirmationEmail ( body );
        call.enqueue ( new Callback<InitiateResponse> () {
            @Override
            public void onResponse(Call<InitiateResponse> call, Response<InitiateResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onResendConfirmationLinkSuccess ();
                } else {
                    //TODO something for god sake!
                }
            }

            @Override
            public void onFailure(Call<InitiateResponse> call, Throwable t) {

            }
        } );
    }

    public static void searchDoctor(Context context, String doctorID, final OnSearchDoctorListener listener) {
            Call<SearchDoctorResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                    .searchDoctor ( doctorID );
            call.enqueue ( new Callback<SearchDoctorResponse> () {
                @Override
                public void onResponse(Call<SearchDoctorResponse> call, Response<SearchDoctorResponse> response) {
                    if (response.isSuccessful ()) {
                        listener.onSearchDoctorSuccess ( response.body () );
                    } else {
                        Gson gson = new Gson ();
                        try {
                            ErrorResponse errorResponse = gson.fromJson ( response.errorBody ().string (), ErrorResponse.class );
                            listener.onSearchDoctorFailure ( errorResponse.getMessage (), Constants.EXCEPTION_TYPE.NO_EXCEPTION );
                        } catch (IOException e) {
                            listener.onSearchDoctorFailure ( Constants.EXCEPTION_TYPE.ERROR_RESPONSE_PARSING_EXCEPTION );
                        }
                    }
                }

                @Override
                public void onFailure(Call<SearchDoctorResponse> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        listener.onSearchDoctorFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                    } else {
                        listener.onSearchDoctorFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                    }
                }
            } );
    }

    public static void setUserPreferencesNoDoctorID(final Context context, final OnNoDoctorIDPreferencesListener listener, final HashMap<String, Object> body) {
        if (!Utils.isDeviceConnected ( context )) {
            SnackController.getInstance ().init ( context, context.getString ( R.string.no_intenet_connection ) )
                    .setAction ( context.getString ( R.string.try_again ), new View.OnClickListener () {
                        @Override
                        public void onClick(View view) {
                            setUserPreferencesNoDoctorID ( context, listener, body );
                        }
                    } );
        }
        Call<Boolean> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context ).setUserPreferences ( body );
        call.enqueue ( new Callback<Boolean> () {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful ()) {
                    listener.onNoDoctorIDSuccess ( response.body () );
                } else {
                    listener.onNoDoctorIDFailure ();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    listener.onNoDoctorIDFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                } else {
                    listener.onNoDoctorIDFailure ();
                }
            }
        } );
    }

    public static void getCurrentUserInfo(Context context,
                                          final OnGetCurrentUserInfoListener listener) {
        Call<CurrentUserResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context ).getCurrentUserInfo ();
        call.enqueue ( new Callback<CurrentUserResponse> () {
            @Override
            public void onResponse(Call<CurrentUserResponse> call, Response<CurrentUserResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onGetCurrentUserInfoSuccess ( response.body () );
                } else {
                    listener.onGetCurrentUserInfoFailure ( Constants.EXCEPTION_TYPE.NO_EXCEPTION );
                }
            }

            @Override
            public void onFailure(Call<CurrentUserResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    listener.onGetCurrentUserInfoFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                } else
                    listener.onGetCurrentUserInfoFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
            }
        } );
    }

    public static void getAppointments(Context context, final OnGetAppointmentsListener listener, String doctorId) {
        HashMap<String, String> body = new HashMap<> ();
        body.put ( Constants.PROVIDER_ID, doctorId );
        Call<AppointmentsResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .getAppointments ( body );
        call.enqueue ( new Callback<AppointmentsResponse> () {
            @Override
            public void onResponse(Call<AppointmentsResponse> call, Response<AppointmentsResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onGetAppointmentSuccess ( response.body () );
                } else {

                }
            }

            @Override
            public void onFailure(Call<AppointmentsResponse> call, Throwable t) {

            }
        } );
    }

    public static void newSymptomFirstQuestionAnswer(Context context,
                                                     final OnNewSymptomAnswerListener listener,
                                                     int appointmentID,
                                                     String question_number,
                                                     Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomFirstQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, question_number );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else if (response.code () == 401) {
                    listener.onUnauthorized ();
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void newSymptomSecondQuestionAnswer(Context context, final OnNewSymptomAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomSecondQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "2" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );

                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void newSymptomThirdQuestionAnswer(Context context, final OnNewSymptomAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomThirdQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "3" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );

                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void newSymptomFourthQuestionAnswer(Context context, final OnNewSymptomAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomFourthQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "4" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );

                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void newSymptomFifthQuestionAnswer(Context context, final OnNewSymptomAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomFifthQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "5" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );

                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void newSymptomSixthQuestionAnswer(Context context, final OnNewSymptomAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomSixthQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "6" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );

                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void newSymptomSeventhQuestionAnswer(Context context, final OnNewSymptomAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomSeventhQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "7" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );

                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void newSymptomEighthQuestionAnswer(Context context, final OnNewSymptomAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomEighthQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "8" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );

                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void newSymptomEighthQuestionAnswer(Context context, final OnNewSymptomAnswerListener listener, int appointmentID, List<Answer> answer) {
        System.out.println ( "MedlynkRequests.newSymptomEighthQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "8" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );

                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void newSymptomNinthQuestionAnswer(Context context, final OnNewSymptomAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomNinthQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "9" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void newSymptomNinthQuestionAnswer(Context context, final OnNewSymptomAnswerListener listener, int appointmentID, List<Answer> answer) {
        System.out.println ( "MedlynkRequests.newSymptomNinthQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "9" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void newSymptomTenthQuestionAnswer(Context context, final OnNewSymptomAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomTenthQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "10" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void newSymptomEleventhQuestionAnswer(Context context, final OnEleventhAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomEleventhQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "11" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onEleventhAnswerSuccess ( response.body () );
                } else {
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
                                                      final tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_12.OnTwelveAnswerListener listener, int appointmentID,
                                                      List<Answer> answer) {
        System.out.println ( "MedlynkRequests.newSymptomTwelveQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "12" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onTwelveAnswerSuccess ( response.body () );
                } else {
                    listener.onTwelveAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                if (t instanceof JsonSyntaxException) {
                    listener.onTwelveAnswerSuccess ( null );
                } else {
                    listener.onTwelveAnswerFailure ();
                }
            }
        } );
    }

    public static void newSymptomTwelveQuestionAnswer(Context context,
                                                      final tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_12.OnTwelveAnswerListener listener,
                                                      int appointmentID,
                                                      Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomTwelveQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "12" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onTwelveAnswerSuccess ( response.body () );
                } else {
                    listener.onTwelveAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onTwelveAnswerFailure ();
            }
        } );
    }

    public static void newSymptomFifteenQuestionAnswer(Context context, final OnFifteenAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomFifteenQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "15" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onFifteenAnswerResponse ( response.body () );
                } else {
                    listener.onFifteenAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onFifteenAnswerFailure ();
            }
        } );
    }

    public static void newSymptomSixteenQuestionAnswer(Context context, final OnSixteenAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomSixteenQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "16" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onSixteenAnswerSuccess ( response.body () );
                } else {
                    listener.onSixteenAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onSixteenAnswerFailure ();
            }
        } );
    }

    public static void newSymptomSeventeenQuestionAnswer(Context context, final OnSeventeenAnswerListener listener, int appointmentID, List<Answer> answer) {
        System.out.println ( "MedlynkRequests.newSymptomSeventeenQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "17" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onSeventeenAnswerSuccess ( response.body () );
                } else {
                    listener.onSeventeenAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onSeventeenAnswerFailure ();
            }
        } );
    }

    public static void newSymptomEighteenQuestionAnswer(Context context, final OnEighteenAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomEighteenQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "18" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onEighteenAnswerSuccess ( response.body () );
                } else {
                    listener.onEighteenAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onEighteenAnswerFailure ();
            }
        } );
    }

    public static void newSymptomNineteenQuestionAnswer(Context context, final OnNineteenAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomNineteenQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "19" );
        JsonObject jObj = (JsonObject) new GsonBuilder ().create ().toJsonTree ( answer );
        if (!answer.getChoice ().equals ( "e" )) {
            jObj.remove ( "reply" );
            jObj.remove ( "duration" );
            jObj.remove ( "years" );
            jObj.remove ( "better" );
        }

        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, jObj );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID,
                        Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onNineteenAnswerSuccess ( response.body () );
                } else {
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
                                                         List<Answer> answers) {

        System.out.println ( "MedlynkRequests.newSymptomTwentyOneQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "21" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answers );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID,
                        Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onTwentyOneAnswerSuccess ( response.body () );
                } else {
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
        System.out.println ( "MedlynkRequests.newSymptomTwentyQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "20" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().
                getMainRetrofit ( context ).
                newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onTwentyAnswerSuccess ( response.body () );
                } else {
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
        System.out.println ( "MedlynkRequests.newSymptomThirteenQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "13" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onThirteenAnswerSuccess ( response.body () );
                } else {
                    listener.onThirteenAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onThirteenAnswerFailure ();
            }
        } );
    }

    public static void newSymptomThirteenQuestionAnswer(Context context,
                                                        final OnThirteenAnswerListener listener,
                                                        int appointmentID,
                                                        List<Answer> answers) {
        System.out.println ( "MedlynkRequests.newSymptomThirteenQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "13" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answers );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onThirteenAnswerSuccess ( response.body () );
                } else {
                    listener.onThirteenAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onThirteenAnswerFailure ();
            }
        } );
    }

    public static void newSymptomFourteenQuestionAnswer(Context context,
                                                        final OnFourteenAnswerListener listener,
                                                        int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomFourteenQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "14" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onThirteenAnswerSuccess ( response.body () );
                } else {
                    listener.onThirteenAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onThirteenAnswerFailure ();
            }
        } );
    }

    public static void newSymptomFourteenQuestionAnswer(Context context,
                                                        final OnFourteenAnswerListener listener,
                                                        int appointmentID, List<Medication> medications) {
        System.out.println ( "MedlynkRequests.newSymptomFourteenQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "14" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, medications );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onThirteenAnswerSuccess ( response.body () );
                } else {
                    listener.onThirteenAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                if (t instanceof JsonSyntaxException) {
                    listener.onThirteenAnswerSuccess ( null );
                } else {
                    listener.onThirteenAnswerFailure ();
                }
            }
        } );
    }

    public static void newSymptomTwentyTwoQuestionAnswer(Context context, final OnTwentyTwoAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomTwentyTwoQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "22" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onTwentyTwoAnswerSuccess ( response.body () );
                } else {
                    listener.onTwentyTwoAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onTwentyTwoAnswerFailure ();
            }
        } );
    }

    public static void newSymptomTwentyTwoQuestionAnswer(Context context, final OnTwentyTwoAnswerListener listener, int appointmentID, List<Answer> answers) {
        System.out.println ( "MedlynkRequests.newSymptomTwentyTwoQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "22" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answers );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onTwentyTwoAnswerSuccess ( response.body () );
                } else {
                    listener.onTwentyTwoAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onTwentyTwoAnswerFailure ();
            }
        } );
    }

    public static void newSymptomTwentyThreeQuestionAnswer(Context context,
                                                           final OnTwentyThreeAnswerListener listener,
                                                           int appointmentID,
                                                           Answer answer) {
        System.out.println ( "MedlynkRequests.newSymptomTwentyThreeQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "23" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answer );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onTwentyThreeAnswerSuccess ( response.body () );
                } else {
                    listener.onTwentyThreeAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                listener.onTwentyThreeAnswerFailure ();
            }
        } );
    }

    public static void newSymptomTwentyThreeQuestionAnswer(Context context,
                                                           final OnTwentyThreeAnswerListener listener,
                                                           int appointmentID,
                                                           List<Medication> answers) {
        System.out.println ( "MedlynkRequests.newSymptomTwentyThreeQuestionAnswer" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.QUESTION_NUMBER, "23" );
        Constants.NEW_SYMPTOM_ANSWER_BODY.put ( Constants.ANSWER, answers );
        Call<NewSymptomAnswerResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .newSymptomAnswer ( appointmentID, Constants.NEW_SYMPTOM_ANSWER_BODY );
        call.enqueue ( new Callback<NewSymptomAnswerResponse> () {
            @Override
            public void onResponse(Call<NewSymptomAnswerResponse> call, Response<NewSymptomAnswerResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onTwentyThreeAnswerSuccess ( response.body () );
                } else {
                    listener.onTwentyThreeAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<NewSymptomAnswerResponse> call, Throwable t) {
                if (t instanceof JsonSyntaxException) {
                    listener.onTwentyThreeAnswerSuccess ( null );
                } else {
                    listener.onTwentyThreeAnswerFailure ();
                }
            }
        } );
    }

    public static void followUpSymptomFirstAnswer(Context context, final OnFirstFollowUpAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomFirstAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "1" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "4" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    System.out.println ( "MedlynkRequests.onResponse" );
                    listener.onFirstAnswerSuccess ( response.body () );
                } else {
                    listener.onFirstAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onFirstAnswerFailure ();
            }
        } );
    }

    public static void followUpSymptomSecondAnswer(Context context, final OnSecondFollowUpAnswerListener listener, int appointmentID, Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomSecondAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "2" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "5" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onSecondAnswerSuccess ( response.body () );
                } else {
                    listener.onSecondAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                System.out.println ( "MedlynkRequests.onFailure" );
                listener.onSecondAnswerFailure ();
            }
        } );
    }

    public static void followUpSymptomThirdAnswer(Context context,
                                                  int appointmentID,
                                                  final OnThirdFollowUpAnswerListener listener,
                                                  Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomThirdAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "3" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "6" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID,
                        Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onThirdAnswerSuccess ( response.body () );
                } else {
                    listener.onThirdAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onThirdAnswerFailure ();
            }
        } );
    }

    public static void followUpSymptomFourthAnswer(Context context, int appointmentID, final OnFourthFollowUpAnswerListener listener, Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomFourthAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "4" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "7" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID,
                        Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onFourthAnswerSuccess ( response.body () );
                } else {
                    listener.onFourthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onFourthAnswerFailure ();
            }
        } );
    }

    public static void followUpSymptomFifthAnswer(Context context,
                                                  int appointmentID,
                                                  final OnFifthFollowUpAnswerListener listener,
                                                  Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomFifthAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "5" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "8" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onFifthAnswerSuccess ( response.body () );
                } else {
                    listener.onFifthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onFifthAnswerFailure ();
            }
        } );
    }

    public static void followUpSymptomSixthAnswer(Context context, int appointmentID, final OnSixthFollowUpAnswerListener listener, Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomSixthAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "6" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "9" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onSixthAnswerSuccess ( response.body () );
                } else {
                    listener.onSixthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onSixthAnswerFailure ();
            }
        } );
    }

    public static void followUpSymptomSeventhAnswer(Context context, int appointmentID, final OnSeventhFollowUpAnswerListener listener, Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomSeventhAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "7" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "10" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onSeventhAnswerSuccess ( response.body () );
                } else {
                    listener.onSeventhAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onSeventhAnswerFailure ();
            }
        } );
    }

    public static void followUpSymptomEightAnswer(Context context, int appointmentID, final OnEighthFollowUpAnswerListener listener, Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomEightAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "8" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "11" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onEighthAnswerSuccess ( response.body () );
                } else {
                    listener.onEightAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onEightAnswerFailure ();
            }
        } );

    }

    public static void followUpSymptomNinthAnswer(Context context, int appointmentID,
                                                  final OnNinthFollowUpAnswerListener listener,
                                                  Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomNinthAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "9" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "12" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onNinthAnswerSuccess ( response.body () );
                } else {
                    listener.onNinthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onNinthAnswerFailure ();
            }
        } );
    }

    public static void followUpSymptomNinthAnswer(Context context, int appointmentID, final OnNinthFollowUpAnswerListener listener, List<Answer> answers) {
        System.out.println ( "MedlynkRequests.followUpSymptomNinthAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "9" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "12" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answers );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onNinthAnswerSuccess ( response.body () );
                } else {
                    listener.onNinthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onNinthAnswerFailure ();
            }
        } );
    }

    public static void followUpSymptomTenthAnswer(Context context, int appointmentID, final OnTenthFollowUpAnswerListener listener, List<Answer> answers) {
        System.out.println ( "MedlynkRequests.followUpSymptomTenthAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "10" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "13" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answers );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onTenthAnswerSuccess ( response.body () );
                } else {
                    listener.onTenthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onTenthAnswerFailure ();
            }
        } );
    }

    public static void followUpSymptomTenthAnswer(Context context,
                                                  int appointmentID,
                                                  final OnTenthFollowUpAnswerListener listener,
                                                  Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomTenthAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "10" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "13" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onTenthAnswerSuccess ( response.body () );
                } else {
                    listener.onTenthAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onTenthAnswerFailure ();
            }
        } );
    }

    public static void followUpSymptomThirteenAnswer(Context context,
                                                     int appointmentID,
                                                     final OnThirteenFollowUpAnswerListener listener,
                                                     Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomThirteenAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "13" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "16" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onThirteenAnswerSuccess ( response.body () );
                } else {
                    listener.onThirteenAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onThirteenAnswerFailure ();
            }
        } );
    }

    public static void followUpSymptomFourteenAnswer(Context context,
                                                     int appointmentID,
                                                     final OnFourteenFollowUpAnswerListener listener,
                                                     Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomThirteenAnswer" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "14" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onThirteenAnswerSuccess ( response.body () );
                } else {
                    listener.onThirteenAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onThirteenAnswerFailure ();
            }
        } );
    }

    public static void followUpSymptomFourteenAnswer(Context context,
                                                     int appointmentID,
                                                     final OnFourteenFollowUpAnswerListener listener,
                                                     List<Answer> answers) {
        System.out.println ( "MedlynkRequests.followUpSymptomThirteenAnswer" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "14" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answers );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onThirteenAnswerSuccess ( response.body () );
                } else {
                    listener.onThirteenAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                if (t instanceof JsonSyntaxException) {
                    listener.onThirteenAnswerSuccess ( null );
                } else {
                    listener.onThirteenAnswerFailure ();
                }
            }
        } );
    }

    public static void followUpSymptomFifteenAnswer(Context context,
                                                    int appointmentID,
                                                    final OnFifteenFollowUpAnswerListener listener,
                                                    Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomFifteenAnswer" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "15" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onFifteenAnswerResponse ( response.body () );
                } else {
                    listener.onFifteenAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onFifteenAnswerFailure ();
            }
        } );
    }

    public static void followUpResultSeventeenAnswer(Context context,
                                                     int appointmentID,
                                                     final MotherCallback listener,
                                                     final Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomFifteenAnswer" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "17" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID,
                        Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void followUpResultEighteenAnswer(Context context,
                                                    int appointmentID,
                                                    final MotherCallback listener,
                                                    final Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomFifteenAnswer" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "18" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID,
                        Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void followUpResultFirstAnswer(Context context,
                                                 int appointmentID,
                                                 final MotherCallback listener,
                                                 final Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomFifteenAnswer" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "1" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID,
                        Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void followUpResultFirstAnswer(Context context,
                                                 int appointmentID,
                                                 final MotherCallback listener,
                                                 final List<Answer> answers) {
        System.out.println ( "MedlynkRequests.followUpSymptomFifteenAnswer" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "1" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answers );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID,
                        Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                if (t instanceof JsonSyntaxException) {
                    listener.onAnswerSuccess ( null );
                } else {
                    listener.onAnswerFailure ();
                }
            }
        } );
    }

    public static void followUpResultSecondAnswer(Context context,
                                                  int appointmentID,
                                                  final MotherCallback listener,
                                                  final Answer answer) {
        System.out.println ( "MedlynkRequests.followUpSymptomFifteenAnswer" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "2" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID,
                        Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void followUpResultThirdAnswer(Context context, int appointmentID, final MotherCallback listener, Answer answer) {
        System.out.println ( "MedlynkRequests.followUpResultThirdAnswer" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "3" );
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpResultAnswer ( appointmentID,
                        Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void refill_first_question(Context context,
                                             int appointmentID,
                                             final MotherCallback listener,
                                             Answer answer) {
        System.out.println ( "MedlynkRequests.refill_first_question" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_NUMBER, "1" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_SET_ID, "" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.ANSWER, answer );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .refillAnswer ( appointmentID, Constants.REFILL_A_MEDICATION_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void refill_second_question(Context context,
                                              int appointmentID,
                                              int question_set_id,
                                              final MotherCallback listener,
                                              Answer answer) {
        System.out.println ( "MedlynkRequests.refill_second_question" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_NUMBER, "2" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_SET_ID, String.valueOf ( question_set_id ) );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.ANSWER, answer );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .refillAnswer ( appointmentID, Constants.REFILL_A_MEDICATION_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void refill_third_question(Context context,
                                             int appointmentID,
                                             int question_set_id,
                                             final MotherCallback listener,
                                             Answer answer) {
        System.out.println ( "MedlynkRequests.refill_third_question" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_NUMBER, "3" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_SET_ID, String.valueOf ( question_set_id ) );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.ANSWER, answer );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .refillAnswer ( appointmentID, Constants.REFILL_A_MEDICATION_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void refill_fourth_question(Context context,
                                              int appointmentID,
                                              int question_set_id,
                                              final MotherCallback listener,
                                              Answer answer) {
        System.out.println ( "MedlynkRequests.refill_fourth_question" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_NUMBER, "4" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_SET_ID, String.valueOf ( question_set_id ) );
        JsonObject jObj = (JsonObject) new GsonBuilder ().create ().toJsonTree ( answer );
        if (!answer.getChoice ().equals ( "c" )) {
            jObj.remove ( "better" );
        }
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.ANSWER, jObj );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .refillAnswer ( appointmentID, Constants.REFILL_A_MEDICATION_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void refill_fifth_question(Context context,
                                             int appointmentID,
                                             int question_set_id,
                                             final MotherCallback listener,
                                             Answer answer) {
        System.out.println ( "MedlynkRequests.refill_fifth_question" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_NUMBER, "5" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_SET_ID, String.valueOf ( question_set_id ) );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.ANSWER, answer );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .refillAnswer ( appointmentID, Constants.REFILL_A_MEDICATION_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void refill_sixth_question(Context context,
                                             int appointmentID,
                                             int questionSetID,
                                             final MotherCallback listener,
                                             Answer answer) {
        System.out.println ( "MedlynkRequests.refill_sixth_question" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_NUMBER, "6" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_SET_ID, String.valueOf ( questionSetID ) );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.ANSWER, answer );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .refillAnswer ( appointmentID, Constants.REFILL_A_MEDICATION_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void refill_sixth_question(Context context,
                                             int appointmentID,
                                             int questionSetID,
                                             final MotherCallback listener,
                                             List<Answer> answers) {
        System.out.println ( "MedlynkRequests.refill_sixth_question" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_NUMBER, "6" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_SET_ID, String.valueOf ( questionSetID ) );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.ANSWER, answers );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .refillAnswer ( appointmentID, Constants.REFILL_A_MEDICATION_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void refill_seventh_question(Context context,
                                               int appointmentID,
                                               int questionSetID,
                                               final MotherCallback listener,
                                               Answer answer) {
        System.out.println ( "MedlynkRequests.refill_seventh_question" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_NUMBER, "7" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_SET_ID, String.valueOf ( questionSetID ) );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.ANSWER, answer );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .refillAnswer ( appointmentID, Constants.REFILL_A_MEDICATION_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void refill_eighth_question(Context context,
                                              int appointmentID,
                                              int questionSetID,
                                              final MotherCallback listener,
                                              List<Answer> answers) {
        System.out.println ( "MedlynkRequests.refill_eighth_question" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_NUMBER, "8" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_SET_ID, String.valueOf ( questionSetID ) );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.ANSWER, answers );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .refillAnswer ( appointmentID, Constants.REFILL_A_MEDICATION_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void refill_eighth_question(Context context,
                                              int appointmentID,
                                              int questionSetID,
                                              final MotherCallback listener,
                                              Answer answer) {
        System.out.println ( "MedlynkRequests.refill_eighth_question" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_NUMBER, "8" );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.QUESTION_SET_ID, String.valueOf ( questionSetID ) );
        Constants.REFILL_A_MEDICATION_BODY.put ( Constants.ANSWER, answer );
        Call<SymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .refillAnswer ( appointmentID, Constants.REFILL_A_MEDICATION_BODY );
        call.enqueue ( new Callback<SymptomResponse> () {
            @Override
            public void onResponse(Call<SymptomResponse> call, Response<SymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onAnswerSuccess ( response.body () );
                } else {
                    listener.onAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<SymptomResponse> call, Throwable t) {
                listener.onAnswerFailure ();
            }
        } );
    }

    public static void getPreviousDoctors(Context context, final OnSearchDoctorListener listener) {
        System.out.println ( "MedlynkRequests.getPreviousDoctors" );
        Call<PreviuosDoctorsResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context ).getPreviousDoctors ();
        call.enqueue ( new Callback<PreviuosDoctorsResponse> () {
            @Override
            public void onResponse(Call<PreviuosDoctorsResponse> call, Response<PreviuosDoctorsResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onGetPreviousDoctorSuccess ( response.body () );
                }
            }

            @Override
            public void onFailure(Call<PreviuosDoctorsResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    listener.onSearchDoctorFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                } else {
                    listener.onSearchDoctorFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
            }
        } );
    }

    public static void deleteAPreviousDoctor(Context context, final OnSearchDoctorListener listener, String doctorID) {
        System.out.println ( "MedlynkRequests.deleteAPreviousDoctor" );
        Call<Boolean> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context ).deletePreviousDoctor ( doctorID );
        call.enqueue ( new Callback<Boolean> () {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful ()) {
                    listener.onDeletePreviousDoctor ( response.body () );
                } else {

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    listener.onSearchDoctorFailure ( Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION );
                } else {
                    listener.onSearchDoctorFailure ( Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION );
                }
            }
        } );
    }

    public static void followUpEleventhQuestionAnswer(Context context,
                                                      final OnEleventFollowUphAnswerListener listener,
                                                      int appointmentID,
                                                      List<Answer> answers) {
        System.out.println ( "MedlynkRequests.followUpEleventhQuestionAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "11" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "14" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answers );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onEleventhAnswerSuccess ( response.body () );
                } else {
                    listener.onEleventhAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                if (t instanceof JsonSyntaxException) {
                    listener.onEleventhAnswerSuccess ( null );
                } else {
                    listener.onEleventhAnswerFailure ();
                }
            }
        } );
    }

    public static void followUpEleventhQuestionAnswer(Context context,
                                                      final OnEleventFollowUphAnswerListener listener,
                                                      int appointmentID,
                                                      Answer answer) {
        System.out.println ( "MedlynkRequests.followUpEleventhQuestionAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "11" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "14" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onEleventhAnswerSuccess ( response.body () );
                } else {
                    listener.onEleventhAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onEleventhAnswerFailure ();
            }
        } );
    }

    public static void followUpTwelveQuestionAnswer(Context context,
                                                    final OnFollowUpTwelveAnswerListener listener,
                                                    int appointmentID,
                                                    List<Medication> answers) {
        System.out.println ( "MedlynkRequests.followUpEleventhQuestionAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "12" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "15" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answers );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onTwelveAnswerSuccess ( response.body () );
                } else {
                    listener.onTwelveAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                if (t instanceof JsonSyntaxException) {
                    listener.onTwelveAnswerSuccess ( null );
                } else {
                    listener.onTwelveAnswerFailure ();
                }
            }
        } );
    }

    public static void followUpTwelveQuestionAnswer(Context context,
                                                    final OnFollowUpTwelveAnswerListener listener,
                                                    int appointmentID,
                                                    Answer answer) {
        System.out.println ( "MedlynkRequests.followUpEleventhQuestionAnswer" );
        if (Constants.Context_Tag.equals ( FollowUpSymptomsActivity.class.getSimpleName () )) {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "12" );
        } else {
            Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.QUESTION_NUMBER, "15" );
        }
        Constants.FOLLOW_UP_SYMPTOM_BODY.put ( Constants.ANSWER, answer );
        Call<FollowUpSymptomResponse> call = MedlynkRestAPI.getInstance().getMainRetrofit ( context )
                .followUpSymptomtAnswer ( appointmentID, Constants.FOLLOW_UP_SYMPTOM_BODY );
        call.enqueue ( new Callback<FollowUpSymptomResponse> () {
            @Override
            public void onResponse(Call<FollowUpSymptomResponse> call, Response<FollowUpSymptomResponse> response) {
                if (response.isSuccessful ()) {
                    listener.onTwelveAnswerSuccess ( response.body () );
                } else {
                    listener.onTwelveAnswerFailure ();
                }
            }

            @Override
            public void onFailure(Call<FollowUpSymptomResponse> call, Throwable t) {
                listener.onTwelveAnswerFailure ();
            }
        } );
    }

    public static void refreshToken(Context context) {

    }
}