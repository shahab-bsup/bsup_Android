package tk.medlynk.patient.android.Networking;

import android.support.annotation.Nullable;

import retrofit2.http.DELETE;
import retrofit2.http.PUT;
import tk.medlynk.patient.android.Model.AppointmentsResponse;
import tk.medlynk.patient.android.Model.CurrentUserResponse;
import tk.medlynk.patient.android.Model.FollowUpSymptomResponse;
import tk.medlynk.patient.android.Model.InitialTokenResponse;
import tk.medlynk.patient.android.Model.InitiateResponse;
import tk.medlynk.patient.android.Model.NewSymptomAnswerResponse;
import tk.medlynk.patient.android.Model.PreviuosDoctorsResponse;
import tk.medlynk.patient.android.Model.AccessTokenResponse;
import tk.medlynk.patient.android.Model.ProgressResponse;
import tk.medlynk.patient.android.Model.RenewTokenResponse;
import tk.medlynk.patient.android.Model.SearchDoctorResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tk.medlynk.patient.android.Model.SymptomResponse;

/**
 * Created by Shahab on 1/11/2018.
 */

public interface MedlynkAPI {

    //get an initial token for just registered user!
    @POST("/oauth/token")
    retrofit2.Call<InitialTokenResponse> getInitialToken(@Body @Nullable Map<String, String> map);

    //actually this is the login route which sends the username and password of the user!
    @POST("/oauth/token")
    Call<AccessTokenResponse> getPrimaryToken(@Body @Nullable Map<String, String> map);

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
    Call<FollowUpSymptomResponse> followUpSymptomtAnswer(@Path ( "appointment_id" ) int appointmentId, @Body HashMap<String, Object> body);

    @PUT("/api/appointments/{appointment_id}/answers")
    Call<SymptomResponse> followUpResultAnswer(@Path ( "appointment_id" ) int appointmentId, @Body HashMap<String, Object> body);

    @PUT("/api/appointments/{appointment_id}/answers")
    Call<SymptomResponse> refillAnswer(@Path ( "appointment_id" ) int appointmentId, @Body HashMap<String, Object> body);

    @GET("/api/appointments/{appointment_id}")
    Call<ProgressResponse> getCurrentAppointmentProgressData(@Path ( "appointment_id" ) int appointmentId);

    @GET("/api/search-provider")
    Call<PreviuosDoctorsResponse> getPreviousDoctors( );

    @DELETE("/api/search-provider/{doctor_id}")
    Call<Boolean> deletePreviousDoctor(@Path("doctor_id") String doctor_id);


}
