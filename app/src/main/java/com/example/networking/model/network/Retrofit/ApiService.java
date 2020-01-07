package com.example.networking.model.network.Retrofit;

import com.example.networking.model.models.Profile;
import com.example.networking.model.models.Promise;
import com.example.networking.model.models.User;
import com.example.networking.model.network.Retrofit.Response.AcceptResponse;
import com.example.networking.model.network.Retrofit.Response.LoginResponse;
import com.example.networking.model.network.Retrofit.Response.NewPromiseResponce;
import com.example.networking.model.network.Retrofit.Response.RegistrationResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/signin")
    Call<ResponseBody> loginRequest(@Body LoginResponse loginResponse);

    @POST("api/signup")
    Call<ResponseBody> signUpRequest(@Body RegistrationResponse registrationResponse);

    @POST("api/set_new_promise")
    Call<ResponseBody> sendNewPromise(@Body NewPromiseResponce newPromise);

    @POST("api/solution")
    Call<ResponseBody> sendAcceptPromise(@Body AcceptResponse newAcceptResponse);

    @GET("api/get_export_promises/")
    Call<List<Promise>>  getAllExportPromises();

    @GET("api/get_import_promises/")
    Call<List<Promise>> getAllImportPromises();

    @GET("api/users_autocomplete/")
    Call<List<String>> getUsersAutocomplete();

    @GET("api/user_info/")
    Call<Profile> getAllFeedItems();

    @GET("api/logout/")
    Call<Body> logout();

//    Нужно тестить
    @POST("api/user/{id}")
    Call<User> userRequest();
    // ToDo: Change url

    @POST("api/logout")
    Call<ResponseBody> logoutRequest();
}


