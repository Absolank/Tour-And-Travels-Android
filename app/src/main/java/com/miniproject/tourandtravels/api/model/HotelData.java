package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelData {

    @SerializedName("DayNum")
    @Expose
    private int DayNum;

    @SerializedName("ID")
    @Expose
    private int ID;

    @SerializedName("ImageUrl")
    @Expose
    private String ImageUrl;

    @SerializedName("HotelName")
    @Expose
    private String Name;

    @SerializedName("Star")
    @Expose
    private int Star;

    @SerializedName("PlaceName")
    @Expose
    private String PlaceName;

    public HotelData(int dayNum, int ID, String imageUrl, String name, int star, String placeName) {
        DayNum = dayNum;
        this.ID = ID;
        ImageUrl = imageUrl;
        Name = name;
        Star = star;
        PlaceName = placeName;
    }

    public int getDayNum() {
        return DayNum;
    }

    public int getID() {
        return ID;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getName() {
        return Name;
    }

    public int getStar() {
        return Star;
    }

    public String getPlaceName() {
        return PlaceName;
    }
}
