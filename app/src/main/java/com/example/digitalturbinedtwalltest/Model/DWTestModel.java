package com.example.digitalturbinedtwalltest.Model;

import java.util.ArrayList;
import java.util.List;

public class DWTestModel {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getRequired_actions() {
        return required_actions;
    }

    public void setRequired_actions(String required_actions) {
        this.required_actions = required_actions;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<DWTestModel> getOffer() {
        return offer;
    }

    public void setOffer(List<DWTestModel> offer) {
        this.offer = offer;
    }

    //Model Class
    private List<DWTestModel> offer = new ArrayList<>();
    private String title;
    private String offer_id;
    private String teaser;
    private String required_actions;
    private String link;



}
