package com.example.digitalturbinedtwalltest.Model;

import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Thumbnail implements Parcelable
{

    private String lowres;
    private String hires;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Thumbnail> CREATOR = new Parcelable.Creator<Thumbnail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Thumbnail createFromParcel(android.os.Parcel in) {
            return new Thumbnail(in);
        }

        public Thumbnail[] newArray(int size) {
            return (new Thumbnail[size]);
        }

    }
            ;

    protected Thumbnail(android.os.Parcel in) {
        this.lowres = ((String) in.readValue((String.class.getClassLoader())));
        this.hires = ((String) in.readValue((String.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    public Thumbnail() {
    }

    public String getLowres() {
        return lowres;
    }

    public void setLowres(String lowres) {
        this.lowres = lowres;
    }

    public String getHires() {
        return hires;
    }

    public void setHires(String hires) {
        this.hires = hires;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(lowres);
        dest.writeValue(hires);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }

}
