package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class FlightInvoice {
    @SerializedName("UserID")
    @Expose
    private int UserID;

    @SerializedName("NumPerson")
    @Expose
    private int NumPerson;

    @SerializedName("Amount")
    @Expose
    private int Amount;

    @SerializedName("DepartureDate")
    @Expose
    private String Departure;

    @SerializedName("SourceCode")
    @Expose
    private String SourceCode;

    @SerializedName("DestinationCode")
    @Expose
    private String DestinationCode;

    @SerializedName("TransactionID")
    @Expose
    private int TransactionID;

    @SerializedName("TransactionDate")
    @Expose
    private String TransactionDate;

    @SerializedName("FlightID")
    @Expose
    private int FlightID;

    public FlightInvoice(int userID, int numPerson, int amount, String departure, String sourceCode, String destinationCode, int transactionID, String transactionDate, int flightID) {
        UserID = userID;
        NumPerson = numPerson;
        Amount = amount;
        Departure = departure;
        SourceCode = sourceCode;
        DestinationCode = destinationCode;
        TransactionID = transactionID;
        TransactionDate = transactionDate;
        FlightID = flightID;
    }

    public int getUserID() {
        return UserID;
    }

    public int getNumPerson() {
        return NumPerson;
    }

    public int getAmount() {
        return Amount;
    }

    public String getDeparture() {
        return Departure;
    }

    public String getSourceCode() {
        return SourceCode;
    }

    public String getDestinationCode() {
        return DestinationCode;
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }

    public int getFlightID() {
        return FlightID;
    }
}

