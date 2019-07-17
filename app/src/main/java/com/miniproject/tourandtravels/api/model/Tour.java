package com.miniproject.tourandtravels.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tour {
    @SerializedName("ID")
    @Expose
    private int ID;

    @SerializedName("SourceCode")
    @Expose
    private String SourceCode;

    @SerializedName("SourceName")
    @Expose
    private String SourceName;

    @SerializedName("DestinationCode")
    @Expose
    private String DestinationCode;

    @SerializedName("DestinationName")
    @Expose
    private String DestinationName;

    public int getID() {
        return ID;
    }

    public String getSourceCode() {
        return SourceCode;
    }

    public String getSourceName() {
        return SourceName;
    }

    public String getDestinationCode() {
            return DestinationCode;
    }

    public String getDestinationName() {
        return DestinationName;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setSourceCode(String sourceCode) {
        SourceCode = sourceCode;
    }

    public void setSourceName(String sourceName) {
        SourceName = sourceName;
    }

    public void setDestinationCode(String destinationCode) {
        DestinationCode = destinationCode;
    }

    public void setDestinationName(String destinationName) {
        DestinationName = destinationName;
    }
}
