package com.miniproject.tourandtravels.api;

import com.miniproject.tourandtravels.api.model.FlightInvoice;
import com.miniproject.tourandtravels.api.model.Hotel;
import com.miniproject.tourandtravels.api.model.HotelInvoice;
import com.miniproject.tourandtravels.api.model.PackageInvoice;
import com.miniproject.tourandtravels.api.model.Status;
import com.miniproject.tourandtravels.api.model.TokenResponse;
import com.miniproject.tourandtravels.api.model.Tour;
import com.miniproject.tourandtravels.api.model.TourAndFlights;
import com.miniproject.tourandtravels.api.model.TourPackage;
import com.miniproject.tourandtravels.api.model.TravelPackageData;
import com.miniproject.tourandtravels.api.model.UserLogin;
import com.miniproject.tourandtravels.api.model.User;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

interface TourAndTravelsApi {
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("login")
    Call<User> login(@Body UserLogin userLogin);

    @FormUrlEncoded
    @POST("login")
    Call<User> login(@Field("username") String username, @Field("password") String password);
    @FormUrlEncoded
    @POST("login")
    Call<User> login(@FieldMap Map<String, String> map);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/search/user")
    Call<List<User>> searchUsers(@HeaderMap Map<String, String> map);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/search/flight")
    Call<TourAndFlights> searchFlights(@QueryMap Map<String, String> map);

    @GET("api/search/user")
    Call<List<User>> searchUsers(@Header("Authorization") String auth);

    @GET("api/search/hotels")
    Call<List<Hotel>> searchHotels(@Query("CityName") String cityName);

    @GET("api/verify")
    Call<TokenResponse> verifyToken(@Header("Authorization") String auth);

    @GET("verify")
    Call<TokenResponse> verifyToken(@HeaderMap Map<String, String> auth);

    @GET("api/search/package")
    Call<List<TourPackage>> getTravelPackages();

    @GET("api/search/package_data")
    Call<TravelPackageData> getTravelPackageData(@Query("TravelPackageID") int travelPackageData);

    @FormUrlEncoded
    @POST("api/book/flight")
    Call<Status> bookFlight(
            @Field("FlightID") int FlightID,
            @Field("NumPerson") int NumPerson,
            @Field("CardNumber") String CardNumber,
            @Field("CVV") String CVV,
            @Field("DepartureDate") String DepartureDate,
            @Field("AccountHolderName") String AccountHolderName,
            @Header("Authorization") String auth
    );
    @FormUrlEncoded
    @POST("api/book/hotel")
    Call<Status> bookHotel(
            @Field("HotelID") int HotelID,
            @Field("NumPerson") int NumPerson,
            @Field("CardNumber") String CardNumber,
            @Field("CVV") String CVV,
            @Field("NumDays") int NumDays,
            @Field("CheckInDate") String CheckInDate,
            @Field("CheckOutDate") String CheckOutDate,
            @Field("AccountHolderName") String AccountHolderName,
            @Header("Authorization") String auth
    );
    @FormUrlEncoded
    @POST("api/book/package")
    Call<Status> bookPackage(
            @Field("PackageID") int PackageID,
            @Field("NumPerson") int NumPerson,
            @Field("CardNumber") String CardNumber,
            @Field("CVV") String CVV,
            @Field("NumDays") int NumDays,
            @Field("DepartureDate") String DepartureDate,
            @Field("ReturnDate") String ReturnDate,
            @Field("AccountHolderName") String AccountHolderName,
            @Header("Authorization") String auth
    );
    @GET("api/search/flight_invoice")
    Call<List<FlightInvoice>> getFlightInvoice(@Header("Authorization") String auth);

    @GET("api/search/hotel_invoice")
    Call<List<HotelInvoice>> getHotelInvoice(@Header("Authorization") String auth);

    @GET("api/search/package_invoice")
    Call<List<PackageInvoice>> getPackageInvoice(@Header("Authorization") String auth);

    @GET("api/search/all_flight_invoice")
    Call<List<FlightInvoice>> getAllFlightInvoice(@Header("Authorization") String auth);

    @GET("api/search/all_hotel_invoice")
    Call<List<HotelInvoice>> getAllHotelInvoice(@Header("Authorization") String auth);

    @GET("api/search/all_package_invoice")
    Call<List<PackageInvoice>> getAllPackageInvoice(@Header("Authorization") String auth);

    @FormUrlEncoded
    @POST("api/update/user")
    Call<Status> updateUser(
            @Field("ID") int ID,
            @Field("FirstName") String FirstName,
            @Field("LastName") String LastName,
            @Field("Email") String Email,
            @Field("Password") String Password,
            @Header("Authorization") String auth
    );
    @FormUrlEncoded
    @POST("api/add/user")
    Call<Status> addUser(
            @Field("Username") String Username,
            @Field("FirstName") String FirstName,
            @Field("LastName") String LastName,
            @Field("Email") String Email,
            @Field("Password") String Password
    );
}
