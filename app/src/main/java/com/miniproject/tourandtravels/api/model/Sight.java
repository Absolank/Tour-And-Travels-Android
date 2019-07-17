package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sight {
    @SerializedName("DayNum")
    @Expose
    private int DayNum;

    @SerializedName("SightName")
    @Expose
    private String SightName;

    public Sight(int dayNum, String sightName) {
        DayNum = dayNum;
        SightName = sightName;
    }

    public int getDayNum() {
        return DayNum;
    }

    public String getSightName() {
        return SightName;
    }
}
