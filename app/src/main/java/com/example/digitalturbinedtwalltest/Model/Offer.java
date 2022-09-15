package com.example.digitalturbinedtwalltest.Model;

import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Offer implements Parcelable {

    public final static Creator<Offer> CREATOR = new Creator<Offer>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Offer createFromParcel(android.os.Parcel in) {
            return new Offer(in);
        }

        public Offer[] newArray(int size) {
            return (new Offer[size]);
        }

    };
    private String title;
    private Integer offerId;
    private String teaser;
    private String requiredActions;
    private String link;
    private List<OfferType> offerTypes = null;
    private Thumbnail thumbnail;
    private Integer payout;
    private TimeToPayout timeToPayout;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected Offer(android.os.Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.offerId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.teaser = ((String) in.readValue((String.class.getClassLoader())));
        this.requiredActions = ((String) in.readValue((String.class.getClassLoader())));
        this.link = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.offerTypes, (OfferType.class.getClassLoader()));
        this.thumbnail = ((Thumbnail) in.readValue((Thumbnail.class.getClassLoader())));
        this.payout = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.timeToPayout = ((TimeToPayout) in.readValue((TimeToPayout.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object>) in.readValue((Map.class.getClassLoader())));
    }

    public Offer() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getRequiredActions() {
        return requiredActions;
    }

    public void setRequiredActions(String requiredActions) {
        this.requiredActions = requiredActions;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<OfferType> getOfferTypes() {
        return offerTypes;
    }

    public void setOfferTypes(List<OfferType> offerTypes) {
        this.offerTypes = offerTypes;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getPayout() {
        return payout;
    }

    public void setPayout(Integer payout) {
        this.payout = payout;
    }

    public TimeToPayout getTimeToPayout() {
        return timeToPayout;
    }

    public void setTimeToPayout(TimeToPayout timeToPayout) {
        this.timeToPayout = timeToPayout;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(offerId);
        dest.writeValue(teaser);
        dest.writeValue(requiredActions);
        dest.writeValue(link);
        dest.writeList(offerTypes);
        dest.writeValue(thumbnail);
        dest.writeValue(payout);
        dest.writeValue(timeToPayout);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }

}