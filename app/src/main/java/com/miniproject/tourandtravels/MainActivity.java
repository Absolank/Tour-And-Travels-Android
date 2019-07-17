package com.miniproject.tourandtravels;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.miniproject.tourandtravels.api.ResponseCallback;
import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.TokenResponse;
import com.miniproject.tourandtravels.fragments.Dashboard;
import com.miniproject.tourandtravels.fragments.FlightFragment;
import com.miniproject.tourandtravels.fragments.Login;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        TourAndTravelsRepository repository = new TourAndTravelsRepository();
        String token = sharedPreferences.getString("Token", "");

//        Dashboard dashboard = new Dashboard();
//        dashboard.setSharedPreferences(sharedPreferences);
//        FlightFragment flightFragment = new FlightFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, flightFragment).commit();

    }
    private void loginForm(){
        Login login = new Login();
        login.setApplication(this, new ResponseCallback() {
            @Override
            public void callback(Object param) {

            }
        });
        login.setSharedPreferences(sharedPreferences);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, login)
                .commit();
    }

}
