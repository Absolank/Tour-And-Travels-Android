package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {
    @SerializedName("code")
    @Expose
    public int Code;
    @SerializedName("message")
    @Expose
    public String Message;
}
