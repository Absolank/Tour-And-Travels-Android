package com.miniproject.tourandtravels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.behavior.HideBottomViewOnScrollBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.miniproject.tourandtravels.adapters.HotelViewer;
import com.miniproject.tourandtravels.api.ResponseCallback;
import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.Hotel;

import java.util.List;

public class HotelActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_hotel);
        Intent intent = getIntent();
        String cityName = intent.getStringExtra("city-name");
        int numPerson = intent.getIntExtra("num-person", 1);
        long checkIn = intent.getLongExtra("check-in", 0);
        long checkOut = intent.getLongExtra("check-out", 0);

        if(cityName != null )
        {
            progressBar = findViewById(R.id.progressBar);
            recyclerView = findViewById(R.id.hotel_viewer);

            HotelViewer hotelViewer = new HotelViewer(numPerson, checkIn, checkOut, this);
            recyclerView.setAdapter(hotelViewer);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            new TourAndTravelsRepository().searchHotels(cityName, new ResponseCallback() {
                @Override
                public void callback(Object param) {
                    List<Hotel> hotels = (List<Hotel>)param;
                    hotelViewer.setHotels(hotels);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            });
        }
        else {
            finish();
        }
    }
}
