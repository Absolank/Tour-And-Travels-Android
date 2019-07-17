package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {
    @SerializedName("token")
    @Expose
    private String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return "Bearer " + token;
    }
}
