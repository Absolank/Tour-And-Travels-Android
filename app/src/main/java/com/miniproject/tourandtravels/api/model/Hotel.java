package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotel {
    @SerializedName("ID")
    @Expose
    private int ID;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Place")
    @Expose
    private String Place;

    @SerializedName("PerPersonCost")
    @Expose
    private int PerPersonCost;

    @SerializedName("ImageUrl")
    @Expose
    private String ImageUrl;

    @SerializedName("Star")
    @Expose
    private int Star;

    public Hotel(int ID, String name, String place, int perPersonCost, String imageUrl, int star) {
        this.ID = ID;
        Name = name;
        Place = place;
        PerPersonCost = perPersonCost;
        ImageUrl = imageUrl;
        Star = star;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getPlace() {
        return Place;
    }

    public int getPerPersonCost() {
        return PerPersonCost;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public int getStar() {
        return Star;
    }
}
