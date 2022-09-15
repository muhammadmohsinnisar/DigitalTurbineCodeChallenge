package com.example.digitalturbinedtwalltest.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DTWall {

    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String message;
    @SerializedName("count")
    private String count;
    @SerializedName("pages")
    private String pages;
    @SerializedName("information")
    private Information information;
    @SerializedName("offers")
    private List<Offer> offers = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getCount() {
        return count;
    }

    public String getPages() {
        return pages;
    }

    public Information getInformation() {
        return information;
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
