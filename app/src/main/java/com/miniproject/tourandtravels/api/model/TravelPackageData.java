package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TravelPackageData {
    @SerializedName("TourPackage")
    @Expose
    private TourPackage tourPackage;

    @SerializedName("Flights")
    @Expose
    private List<FlightData> flights;

    @SerializedName("Hotels")
    @Expose
    private List<HotelData> hotels;

    @SerializedName("Sights")
    @Expose
    private  List<Sight> sights;

    public TravelPackageData(TourPackage tourPackage, List<FlightData> flights, List<HotelData> hotels, List<Sight> sights) {
        this.tourPackage = tourPackage;
        this.flights = flights;
        this.hotels = hotels;
        this.sights = sights;
    }

    public TourPackage getTourPackage() {
        return tourPackage;
    }

    public List<FlightData> getFlights() {
        return flights;
    }

    public List<HotelData> getHotels() {
        return hotels;
    }

    public List<Sight> getSights() {
        return sights;
    }
}
