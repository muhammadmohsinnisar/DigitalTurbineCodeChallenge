package com.example.digitalturbinedtwalltest.Model;

import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class TimeToPayout implements Parcelable
{

    private Integer amount;
    private String readable;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<TimeToPayout> CREATOR = new Creator<TimeToPayout>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TimeToPayout createFromParcel(android.os.Parcel in) {
            return new TimeToPayout(in);
        }

        public TimeToPayout[] newArray(int size) {
            return (new TimeToPayout[size]);
        }

    }
            ;

    protected TimeToPayout(android.os.Parcel in) {
        this.amount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.readable = ((String) in.readValue((String.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    public TimeToPayout() {
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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
        dest.writeValue(amount);
        dest.writeValue(readable);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }

}