package com.miniproject.tourandtravels.api;

import com.miniproject.tourandtravels.api.TourAndTravelsApi;
import com.miniproject.tourandtravels.api.model.User;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

class TourAndTravelsClient {
    private static final String BASE_URL = "https://226e69fb.ngrok.io";
    private static Retrofit retrofit = null;
    private static TourAndTravelsApi tourAndTravelsApi = null;
    synchronized static void init(){
        if(retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                    .connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS))
                    .build();
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            tourAndTravelsApi = retrofit.create(TourAndTravelsApi.class);
        }
    }
    static TourAndTravelsApi getApi() {
        return tourAndTravelsApi;
    }
}
