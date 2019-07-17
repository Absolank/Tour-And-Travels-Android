package com.miniproject.tourandtravels;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.miniproject.tourandtravels.adapters.FlightViewer;
import com.miniproject.tourandtravels.api.ResponseCallback;
import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.Flight;
import com.miniproject.tourandtravels.api.model.TourAndFlights;

public class FlightActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);
        RecyclerView recyclerView = findViewById(R.id.flight_viewer);
        FlightViewer flightViewer = new FlightViewer(false, this);
        recyclerView.setAdapter(flightViewer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProgressBar progressBar = findViewById(R.id.progressBar);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String source = bundle.getString("source-city", "");
            String destination = bundle.getString("destination-city", "");
            long departure = bundle.getLong("departure-date", 0);
            int numPerson = bundle.getInt("num-person", 1);
            flightViewer.setData(source, destination, numPerson, departure);

            new TourAndTravelsRepository().searchFlight(
                    source,
                    destination,
                    null,
                    param->{
                        TourAndFlights flights = (TourAndFlights) param;
                        flightViewer.setTourAndFlights(flights);
                        recyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
            );
        }
    }
}
