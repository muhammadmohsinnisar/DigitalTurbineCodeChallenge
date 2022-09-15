package com.example.digitalturbinedtwalltest.Model;

import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class OfferType implements Parcelable {

    public final static Parcelable.Creator<OfferType> CREATOR = new Creator<OfferType>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OfferType createFromParcel(android.os.Parcel in) {
            return new OfferType(in);
        }

        public OfferType[] newArray(int size) {
            return (new OfferType[size]);
        }

    };
    private Integer offerTypeId;
    private String readable;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected OfferType(android.os.Parcel in) {
        this.offerTypeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.readable = ((String) in.readValue((String.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object>) in.readValue((Map.class.getClassLoader())));
    }

    public OfferType() {
    }

    public Integer getOfferTypeId() {
        return offerTypeId;
    }

    public void setOfferTypeId(Integer offerTypeId) {
        this.offerTypeId = offerTypeId;
    }

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(offerTypeId);
        dest.writeValue(readable);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }

}