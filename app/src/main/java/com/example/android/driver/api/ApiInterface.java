package com.example.android.driver.api;

import com.example.android.driver.model.LoginResponse;
import com.example.android.driver.model.OrderDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("get_orders.php")
    Call<List<OrderDetails>> getOrders();

    @FormUrlEncoded
    @POST("login_driver.php")
    Call<LoginResponse> login(
            @Field("email_id") String email_id,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("forgot_password_driver.php")
    Call<LoginResponse> forgotPassword(
            @Field("email_id") String email_id);

    @FormUrlEncoded
    @POST("get_accepted_orders.php")
    Call<List<OrderDetails>> getAcceptedOrders(
            @Field("driver_id") String driver_id);

    @FormUrlEncoded
    @POST("accept_button_driver.php")
    Call<LoginResponse> accept(
            @Field("driver_id") String email_id,
            @Field("order_id") String order_id);

}