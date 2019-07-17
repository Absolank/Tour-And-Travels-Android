package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageInvoice {
    @SerializedName("UserID")
    @Expose
    private int UserID;

    @SerializedName("TravelPackageID")
    @Expose
    private int TravelPackageID;

    @SerializedName("NumDays")
    @Expose
    private int NumDays;

    @SerializedName("NumPerson")
    @Expose
    private int NumPerson;

    @SerializedName("Amount")
    @Expose
    private int Amount;

    @SerializedName("DepartureDate")
    @Expose
    private String CheckInDate;

    @SerializedName("ReturnDate")
    @Expose
    private String CheckOutDate;

    @SerializedName("TransactionID")
    @Expose
    private int TransactionID;

    @SerializedName("TransactionDate")
    @Expose
    private String TransactionDate;

    public PackageInvoice(int userID, int travelPackageID, int numDays, int numPerson, int amount, String checkInDate, String checkOutDate, int transactionID, String transactionDate) {
        UserID = userID;
        TravelPackageID = travelPackageID;
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

    public int getTravelPackageID() {
        return TravelPackageID;
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
