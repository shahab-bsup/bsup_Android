package tk.medlynk.patient.android.Networking;

import android.support.annotation.Nullable;

import retrofit2.http.PUT;
import tk.medlynk.patient.android.Model.AppointmentsResponse;
import tk.medlynk.patient.android.Model.CurrentUserResponse;
import tk.medlynk.patient.android.Model.FollowUpResultResponse;
import tk.medlynk.patient.android.Model.InitialTokenResponse;
import tk.medlynk.patient.android.Model.InitiateResponse;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Model.PrimaryTokenResponse;
import tk.medlynk.patient.android.Model.RenewTokenResponse;
import tk.medlynk.patient.android.Model.SearchDoctorResponse;

import java.sql.CallableStatement;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    //register the new user!
    //Congrats!
    @POST("/api/register")
    Call<InitiateResponse> signUp(@Body @Nullable Map<String, Object> map);

    //send reset password request!
    //the user is not aware! :(
    @POST("/api/password/email")
    Call<InitiateResponse> sendResetPassWordRequest(@Body @Nullable Map<String, String> map);

    @POST("/api/confirmation/resend")
    Call<InitiateResponse> resendConfirmationEmail(@Body @Nullable Map<String, String> map);

    //Search for the target doctor!
    @GET("/api/search-provider/{doctorID}")
    Call<SearchDoctorResponse> searchDoctor(@Path ( "doctorID" ) String doctorID);

    @POST("/api/password/reset")
    Call<InitiateResponse> resetPassword( @Body HashMap<String, String> body );

    @PATCH("/api/user/preferences")
    Call<Boolean> setUserPreferences( @Body HashMap<String, Object> body );

    @GET("/api/user")
    Call<CurrentUserResponse> getCurrentUserInfo();

    @POST("/api/appointments")
    Call<AppointmentsResponse> getAppointments(@Body Map<String, String> body);

    @PUT("/api/appointments/{appointment_id}/answers")
    Call<NewSymptomAnswerResponse> newSymptomAnswer(@Path ( "appointment_id" ) int appointmentId, @Body HashMap<String, Object> body);

    @PUT("/api/appointments/{appointment_id}/answers")
    Call<FollowUpResultResponse> followUpResultAnswer(@Path ( "appointment_id" ) int appointmentId, @Body HashMap<String, Object> body);

}
