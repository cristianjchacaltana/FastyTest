package com.firebaseapp.fasty.fastytest.model;

public class User {

    private String name;
    private String phone;
    private String profileImage;
    private String uid;



    public User() {
    }

    public User(String name, String phone, String profileImage, String uid) {
        this.name = name;
        this.phone = phone;
        this.profileImage = profileImage;
        this.uid = uid;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
