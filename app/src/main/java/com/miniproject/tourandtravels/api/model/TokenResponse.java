package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenResponse{
    @SerializedName("type")
    @Expose
    public String TYPE;
    @SerializedName("status")
    @Expose
    public int STATUS;
}