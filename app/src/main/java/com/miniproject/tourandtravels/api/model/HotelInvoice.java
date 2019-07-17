package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelInvoice {
    @SerializedName("UserID")
    @Expose
    private int UserID;
    @SerializedName("HotelID")
    @Expose
    private int HotelID;

    @SerializedName("NumDays")
    @Expose
    private int NumDays;

    @SerializedName("NumPerson")
    @Expose
    private int NumPerson;

    @SerializedName("Amount")
    @Expose
    private int Amount;

    @SerializedName("CheckInDate")
    @Expose
    private String CheckInDate;
    @SerializedName("CheckOutDate")
    @Expose
    private String CheckOutDate;

    @SerializedName("TransactionID")
    @Expose
    private int TransactionID;

    @SerializedName("TransactionDate")
    @Expose
    private String TransactionDate;


    public HotelInvoice(int userID, int hotelID, int numDays, int numPerson, int amount, String checkInDate, String checkOutDate, int transactionID, String transactionDate) {
        UserID = userID;
        HotelID = hotelID;
        NumDays = numDays;
        NumPerson = numPerson;
        Amount = amount;
        CheckInDate = checkInDate;
        CheckOutDate = checkOutDate;
        TransactionID = transactionID;
        TransactionDate = transactionDate;
    }

    public int getUserID() {
        return UserID;
    }

    public int getHotelID() {
        return HotelID;
    }

    public int getNumDays() {
        return NumDays;
    }

    public int getNumPerson() {
        return NumPerson;
    }

    public int getAmount() {
        return Amount;
    }

    public String getCheckInDate() {
        return CheckInDate;
    }

    public String getCheckOutDate() {
        return CheckOutDate;
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }
}
