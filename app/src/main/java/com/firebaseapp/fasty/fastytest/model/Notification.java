package com.firebaseapp.fasty.fastytest.model;

import java.util.Date;

public class Notification {

    private String image;
    private String text;
    private String timestamp;
    private String uid;



    public Notification() {
    }

    public Notification(String image, String text, String timestamp, String uid) {
        this.image = image;
        this.text = text;
        this.timestamp = timestamp;
        this.uid = uid;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
