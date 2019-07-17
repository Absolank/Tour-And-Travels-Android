package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flight {
    @SerializedName("ID")
    @Expose
    private int ID;

    @SerializedName("TourID")
    @Expose
    private int TourID;

    @SerializedName("Cost")
    @Expose
    private int Cost;

    @SerializedName("Departure")
    @Expose
    private String Departure;

    @SerializedName("TravelTime")
    @Expose
    private String TravelTime;

    Flight(int ID, int tourID, int cost, String departure, String travelTime) {
        this.ID = ID;
        TourID = tourID;
        Cost = cost;
        Departure = departure;
        TravelTime = travelTime;
    }

    public int getID() {
        return ID;
    }

    public int getTourID() {
        return TourID;
    }

    public int getCost() {
        return Cost;
    }

    public String getDeparture() {
        return Departure;
    }

    public String getTravelTime() {
        return TravelTime;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTourID(int tourID) {
        TourID = tourID;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public void setDeparture(String departure) {
        Departure = departure;
    }

    public void setTravelTime(String travelTime) {
        TravelTime = travelTime;
    }
}
