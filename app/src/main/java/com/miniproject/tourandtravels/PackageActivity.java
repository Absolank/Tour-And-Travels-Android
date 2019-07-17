package com.miniproject.tourandtravels;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.TravelPackageData;
import com.miniproject.tourandtravels.fragments.LoadingFragment;
import com.miniproject.tourandtravels.fragments.TravelPackageInfoFragment;

public class PackageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        final LoadingFragment loadingFragment = new LoadingFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, loadingFragment).commit();
        if(intent != null)
        {
            int ID = intent.getIntExtra("package-id", 0);
            if(ID != 0) {
                TourAndTravelsRepository apiRepo = new TourAndTravelsRepository();
                apiRepo.getTravelPackageData(ID, param -> {
                    TravelPackageData travelPackageData = (TravelPackageData) param;
                    TravelPackageInfoFragment travelPackageInfoFragment = new TravelPackageInfoFragment();
                    travelPackageInfoFragment.setData(travelPackageData);
                    getSupportFragmentManager().beginTransaction().remove(loadingFragment).commitNow();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, travelPackageInfoFragment).commit();
                });
            }
        }
    }
}
