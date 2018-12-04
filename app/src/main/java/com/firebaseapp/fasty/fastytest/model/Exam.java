package com.firebaseapp.fasty.fastytest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Exam implements Parcelable{

    private String ano;
    private String semestre;
    private String solucion;
    private String uid;


    public Exam(){

    }

    public Exam(String ano, String semestre, String solucion, String uid){
        this.ano = ano;
        this.semestre = semestre;
        this.solucion = solucion;
        this.uid = uid;
    }


    protected Exam(Parcel in) {
        ano = in.readString();
        semestre = in.readString();
        solucion = in.readString();
        uid = in.readString();
    }

    public static final Creator<Exam> CREATOR = new Creator<Exam>() {
        @Override
        public Exam createFromParcel(Parcel in) {
            return new Exam(in);
        }

        @Override
        public Exam[] newArray(int size) {
            return new Exam[size];
        }
    };

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ano);
        dest.writeString(semestre);
        dest.writeString(solucion);
        dest.writeString(uid);
    }
}
