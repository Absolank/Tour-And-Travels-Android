package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class TourPackage {
    @SerializedName("ID")
    @Expose
    private int ID;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("NumDays")
    @Expose
    private int NumDays;
    @SerializedName("NumNights")
    @Expose
    private int NumNights;
    @SerializedName("DepartureDate")
    @Expose
    private Date DepartureDate;
    @SerializedName("ReturnDate")
    @Expose
    private Date ReturnDate;
    @SerializedName("TravelCost")
    @Expose
    private int TravelCost;
    @SerializedName("OtherCost")
    @Expose
    private int OtherCost;
    @SerializedName("Discount")
    @Expose
    private int Discount;
    @SerializedName("ImageUrl")
    @Expose
    private String ImageUrl;

    public TourPackage(int ID, String description, int numDays, int numNights, Date departureDate, Date returnDate, int travelCost, int otherCost, int discount, String imageUrl) {
        this.ID = ID;
        Description = description;
        NumDays = numDays;
        NumNights = numNights;
        DepartureDate = departureDate;
        ReturnDate = returnDate;
        TravelCost = travelCost;
        OtherCost = otherCost;
        Discount = discount;
        ImageUrl = imageUrl;
    }

    public int getID() {
        return ID;
    }

    public String getDescription() {
        return Description;
    }

    public int getNumDays() {
        return NumDays;
    }

    public int getNumNights() {
        return NumNights;
    }

    public Date getDepartureDate() {
        return DepartureDate;
    }

    public Date getReturnDate() {
        return ReturnDate;
    }

    public int getTravelCost() {
        return TravelCost;
    }

    public int getOtherCost() {
        return OtherCost;
    }

    public int getDiscount() {
        return Discount;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
}
