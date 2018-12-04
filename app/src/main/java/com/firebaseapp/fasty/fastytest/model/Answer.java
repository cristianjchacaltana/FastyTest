package com.firebaseapp.fasty.fastytest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable{

    private String uid;
    private String respuesta;



    public Answer(){

    }

    public Answer(String uid, String respuesta){
        this.uid = uid;
        this.respuesta = respuesta;
    }


    protected Answer(Parcel in) {
        uid = in.readString();
        respuesta = in.readString();
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(respuesta);
    }
}
