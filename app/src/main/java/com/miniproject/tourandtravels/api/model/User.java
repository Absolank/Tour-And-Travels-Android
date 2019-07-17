package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("ID")
    @Expose
    private int ID;

    @SerializedName("Username")
    @Expose
    private String Username;

    @SerializedName("FirstName")
    @Expose
    private String FirstName;

    @SerializedName("LastName")
    @Expose
    private String LastName;

    @SerializedName("Email")
    @Expose
    private String Email;

    @SerializedName("Token")
    @Expose
    private String Token;

    public int getID() {
        return ID;
    }

    public User(int ID, String username, String FirstName, String LastName, String email, String token) {
        this.ID = ID;
        this.Username = username;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Email = email;
        this.Token = token;
    }

    public String getToken() {
        return "Bearer " + Token;
    }
    public String getUsername() {
        return Username;
    }
    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public String getEmail() {
        return Email;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setToken(String token) {
        Token = token;
    }
}
