package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TourAndFlights {
    @SerializedName("Tour")
    @Expose
    private Tour tour;

    @SerializedName("Flights")
    @Expose
    private ArrayList<Flight> Flights;

    public TourAndFlights(Tour tour, ArrayList<Flight> flights) {
        this.tour = tour;
        Flights = flights;
    }

    public Tour getTour() {
        return tour;
    }

    public ArrayList<Flight> getFlights() {
        return Flights;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public void setFlights(ArrayList<Flight> flights) {
        Flights = flights;
    }
}
