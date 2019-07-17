package com.miniproject.tourandtravels.api;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.miniproject.tourandtravels.api.model.Flight;
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
import com.miniproject.tourandtravels.api.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourAndTravelsRepository {
    TourAndTravelsApi api;

    public TourAndTravelsRepository() {
        TourAndTravelsClient.init();
        api = TourAndTravelsClient.getApi();
    }

    public void fetchAndSaveLoginToken(String username, String password, SharedPreferences sharedPreferences, ResponseCallback callback){
        if(sharedPreferences == null)
            return;
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        Call<User> call = api.login(map);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if(user != null)
                if(user.getID() != 0)
                {
                    sharedPreferences.edit()
                            .putInt("UserID", user.getID())
                            .putString("Username", user.getUsername())
                            .putString("FirstName", user.getFirstName())
                            .putString("LastName", user.getLastName())
                            .putString("Email", user.getEmail())
                            .putString("Token", user.getToken())
                            .apply();
                    callback.callback(true);
                }
                callback.callback(false);

            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                TokenResponse tokenResponse = new TokenResponse();
                tokenResponse.STATUS = 400;
                tokenResponse.TYPE = null;
                callback.callback(tokenResponse);
            }
        });
    }
    public void verifyToken(String token, ResponseCallback responseCallback){
        try {
            Map<String, String> auth = new HashMap<>();
            auth.put("Authorization", token);
            Call<TokenResponse> cal = api.verifyToken(auth);
            cal.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    TokenResponse tokenResponse = response.body();
                    responseCallback.callback(tokenResponse);
                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {

                }
            });
        }catch (Exception e){
        }
    }
    public void searchAllUsers(String token, ResponseCallback responseCallback){
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", token );
        Call<List<User>> call = api.searchUsers(map);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                responseCallback.callback(users);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                System.out.println();
            }
        });
    }
    public void searchFlight(String Source, String Destination, String token, @NonNull ResponseCallback responseCallback){
        Map<String, String> map = new HashMap<>();
        map.put("SourceName", Source);
        map.put("DestinationName", Destination);
        Call<TourAndFlights> flightsCall = api.searchFlights(map);
        flightsCall.enqueue(new Callback<TourAndFlights>() {
            @Override
            public void onResponse(Call<TourAndFlights> call, Response<TourAndFlights> response) {
                TourAndFlights tourAndFlights = response.body();
                responseCallback.callback(tourAndFlights);
                System.out.println();
            }

            @Override
            public void onFailure(Call<TourAndFlights> call, Throwable t) {
                System.out.println();
            }
        });
    }
    public void searchHotels(String cityName, @NonNull ResponseCallback responseCallback){
        Call<List<Hotel>> hotelsCall = api.searchHotels(cityName);
        hotelsCall.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                List<Hotel> hotels = response.body();
                responseCallback.callback(hotels);
                System.out.println();
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {

            }
        });
    }
    public void searchPackage(ResponseCallback responseCallback){
        Call<List<TourPackage>> tourCall = api.getTravelPackages();
        tourCall.enqueue(new Callback<List<TourPackage>>() {
            @Override
            public void onResponse(Call<List<TourPackage>> call, Response<List<TourPackage>> response) {
                List<TourPackage> tourPackages = response.body();
                responseCallback.callback(tourPackages);
            }

            @Override
            public void onFailure(Call<List<TourPackage>> call, Throwable t) {

            }
        });
    }
    public void getTravelPackageData(int travelPackageID, ResponseCallback responseCallback) {
        Call<TravelPackageData> travelPackageDataCall = api.getTravelPackageData(travelPackageID);

        travelPackageDataCall.enqueue(new Callback<TravelPackageData>() {
            @Override
            public void onResponse(Call<TravelPackageData> call, Response<TravelPackageData> response) {
                TravelPackageData travelPackageData = response.body();
                responseCallback.callback(travelPackageData);
            }

            @Override
            public void onFailure(Call<TravelPackageData> call, Throwable t) {
                System.out.println();
            }
        });
    }

    public void bookFlight(String token, int FlightID, int numPerson, String departureDate, String debitCardNumber, String cvv,  String accountHolderName,  ResponseCallback responseCallback){
            Call<Status> book = api.bookFlight(FlightID, numPerson, debitCardNumber, cvv, departureDate, accountHolderName, token);
            book.enqueue(new Callback<Status>() {
                @Override
                public void onResponse(Call<Status> call, Response<Status> response) {
                    Status status = response.body();
                    responseCallback.callback(status);
                }

                @Override
                public void onFailure(Call<Status> call, Throwable t) {
                    Status status = new Status();
                    status.Code = 400;
                    status.Message = "Transaction Failed";
                    responseCallback.callback(status);
                }
            });
    }
    public void bookHotel(String token, int HotelID, int numPerson, int numDays, String checkInDate, String checkOutDate, String debitCardNumber, String cvv,  String accountHolderName,  ResponseCallback responseCallback){

        Call<Status> book = api.bookHotel(HotelID, numPerson, debitCardNumber, cvv, numDays, checkInDate, checkOutDate, accountHolderName, token);
        book.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status status = response.body();
                responseCallback.callback(status);
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Status status = new Status();
                status.Code = 400;
                status.Message = "Transaction Failed";
                responseCallback.callback(status);
            }
        });
    }

    public void bookPackage(String token, int HotelID, int numPerson, int numDays, String checkInDate, String checkOutDate, String debitCardNumber, String cvv,  String accountHolderName,  ResponseCallback responseCallback){

        Call<Status> book = api.bookPackage(HotelID, numPerson, debitCardNumber, cvv, numDays, checkInDate, checkOutDate, accountHolderName, token);
        book.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status status = response.body();
                responseCallback.callback(status);
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Status status = new Status();
                status.Code = 400;
                status.Message = "Transaction Failed";
                responseCallback.callback(status);
            }
        });
    }
    public void getFlightInvoice(String token, ResponseCallback responseCallback){
        Call<List<FlightInvoice>> flightInvoice = api.getFlightInvoice(token);
        flightInvoice.enqueue(new Callback<List<FlightInvoice>>() {
            @Override
            public void onResponse(Call<List<FlightInvoice>> call, Response<List<FlightInvoice>> response) {
                List<FlightInvoice> flightInvoices = response.body();
                responseCallback.callback(flightInvoices);
            }

            @Override
            public void onFailure(Call<List<FlightInvoice>> call, Throwable t) {
                System.out.println();
            }
        });
    }
    public void getAllFlightInvoice(String token, ResponseCallback responseCallback){
        Call<List<FlightInvoice>> flightInvoice = api.getAllFlightInvoice(token);
        flightInvoice.enqueue(new Callback<List<FlightInvoice>>() {
            @Override
            public void onResponse(Call<List<FlightInvoice>> call, Response<List<FlightInvoice>> response) {
                List<FlightInvoice> flightInvoices = response.body();
                responseCallback.callback(flightInvoices);
            }

            @Override
            public void onFailure(Call<List<FlightInvoice>> call, Throwable t) {
                System.out.println();
            }
        });
    }
    public void getHotelInvoice(String token, ResponseCallback responseCallback){
        Call<List<HotelInvoice>> hotelInvoice = api.getHotelInvoice(token);
        hotelInvoice.enqueue(new Callback<List<HotelInvoice>>() {
            @Override
            public void onResponse(Call<List<HotelInvoice>> call, Response<List<HotelInvoice>> response) {
                List<HotelInvoice> hotelInvoices = response.body();
                responseCallback.callback(hotelInvoices);
            }

            @Override
            public void onFailure(Call<List<HotelInvoice>> call, Throwable t) {
                System.out.println();
            }
        });
    }
    public void getAllHotelInvoice(String token, ResponseCallback responseCallback){
        Call<List<HotelInvoice>> hotelInvoice = api.getAllHotelInvoice(token);
        hotelInvoice.enqueue(new Callback<List<HotelInvoice>>() {
            @Override
            public void onResponse(Call<List<HotelInvoice>> call, Response<List<HotelInvoice>> response) {
                List<HotelInvoice> hotelInvoices = response.body();
                responseCallback.callback(hotelInvoices);
            }

            @Override
            public void onFailure(Call<List<HotelInvoice>> call, Throwable t) {
                System.out.println();
            }
        });
    }

    public void getPackageInvoice(String token, ResponseCallback responseCallback){
        Call<List<PackageInvoice>> packageInvoice = api.getAllPackageInvoice(token);
        packageInvoice.enqueue(new Callback<List<PackageInvoice>>() {
            @Override
            public void onResponse(Call<List<PackageInvoice>> call, Response<List<PackageInvoice>> response) {
                List<PackageInvoice> packageInvoices = response.body();
                responseCallback.callback(packageInvoices);
            }

            @Override
            public void onFailure(Call<List<PackageInvoice>> call, Throwable t) {
                System.out.println();
            }
        });
    }
    public void getAllPackageInvoice(String token, ResponseCallback responseCallback){
        Call<List<PackageInvoice>> packageInvoice = api.getAllPackageInvoice(token);
        packageInvoice.enqueue(new Callback<List<PackageInvoice>>() {
            @Override
            public void onResponse(Call<List<PackageInvoice>> call, Response<List<PackageInvoice>> response) {
                List<PackageInvoice> packageInvoices = response.body();
                responseCallback.callback(packageInvoices);
            }
            @Override
            public void onFailure(Call<List<PackageInvoice>> call, Throwable t) {
                System.out.println();
            }
        });
    }


    public void updateUser(User user, String password, String token, ResponseCallback callback){
        Call<Status> call = api.updateUser(user.getID(), user.getFirstName(), user.getLastName(), user.getEmail(), password, token);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                callback.callback(response.body());
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {

            }
        });
    }

    public void addUser(User user, String password, ResponseCallback callback){
        Call<Status> call = api.addUser(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), password);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                callback.callback(response.body());
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {

            }
        });
    }
}
