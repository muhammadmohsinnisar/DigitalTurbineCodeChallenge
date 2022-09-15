package com.example.digitalturbinedtwalltest.Model;

import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Information implements Parcelable {

    public final static Creator<Information> CREATOR = new Parcelable.Creator<Information>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Information createFromParcel(android.os.Parcel in) {
            return new Information(in);
        }

        public Information[] newArray(int size) {
            return (new Information[size]);
        }

    };
    private String appName;
    private Integer appid;
    private String virtualCurrency;
    private String country;
    private String language;
    private String supportUrl;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected Information(android.os.Parcel in) {
        this.appName = ((String) in.readValue((String.class.getClassLoader())));
        this.appid = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.virtualCurrency = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.language = ((String) in.readValue((String.class.getClassLoader())));
        this.supportUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object>) in.readValue((Map.class.getClassLoader())));
    }

    public Information() {
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getVirtualCurrency() {
        return virtualCurrency;
    }

    public void setVirtualCurrency(String virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSupportUrl() {
        return supportUrl;
    }

    public void setSupportUrl(String supportUrl) {
        this.supportUrl = supportUrl;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(appName);
        dest.writeValue(appid);
        dest.writeValue(virtualCurrency);
        dest.writeValue(country);
        dest.writeValue(language);
        dest.writeValue(supportUrl);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }

}