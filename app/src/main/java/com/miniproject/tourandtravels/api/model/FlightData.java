package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightData{
    @SerializedName("FlightID")
    @Expose
    private int ID;

    @SerializedName("DayNum")
    @Expose
    private int DayNum;

    @SerializedName("DepartureDate")
    @Expose
    private String Departure;

    @SerializedName("SourceCode")
    @Expose
    private String SourceCode;

    @SerializedName("DestinationCode")
    @Expose
    private String DestinationCode;

    public FlightData(int ID, int dayNum, String departure, String sourceCode, String destinationCode) {
        this.ID = ID;
        DayNum = dayNum;
        Departure = departure;
        SourceCode = sourceCode;
        DestinationCode = destinationCode;
    }

    public int getID() {
        return ID;
    }

    public int getDayNum() {
        return DayNum;
    }

    public String getDeparture() {
        return Departure;
    }

    public String getSourceCode() {
        return SourceCode;
    }

    public String getDestinationCode() {
        return DestinationCode;
    }
}
