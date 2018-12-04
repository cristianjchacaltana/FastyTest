package com.firebaseapp.fasty.fastytest.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public class Career implements Parcelable{

    private String area;
    private String escuela;
    private String facultad;
    private Integer numeroPostulantes;
    private Integer numeroVacantes;
    private Double puntajeMaximo;
    private Double puntajeMinimo;
    private String uid;



    public Career(){

    }

    public Career(String area,
                  String escuela,
                  String facultad,
                  Integer numeroPostulantes,
                  Integer numeroVacantes,
                  Double puntajeMaximo,
                  Double puntajeMinimo,
                  String uid){
        this.area = area;
        this.escuela = escuela;
        this.facultad = facultad;
        this.numeroPostulantes = numeroPostulantes;
        this.numeroVacantes = numeroVacantes;
        this.puntajeMaximo = puntajeMaximo;
        this.puntajeMinimo = puntajeMinimo;
        this.uid = uid;
    }




    protected Career(Parcel in) {
        area = in.readString();
        escuela = in.readString();
        facultad = in.readString();
        if (in.readByte() == 0) {
            numeroPostulantes = null;
        } else {
            numeroPostulantes = in.readInt();
        }
        if (in.readByte() == 0) {
            numeroVacantes = null;
        } else {
            numeroVacantes = in.readInt();
        }
        if (in.readByte() == 0) {
            puntajeMaximo = null;
        } else {
            puntajeMaximo = in.readDouble();
        }
        if (in.readByte() == 0) {
            puntajeMinimo = null;
        } else {
            puntajeMinimo = in.readDouble();
        }
        uid = in.readString();
    }

    public static final Creator<Career> CREATOR = new Creator<Career>() {
        @Override
        public Career createFromParcel(Parcel in) {
            return new Career(in);
        }

        @Override
        public Career[] newArray(int size) {
            return new Career[size];
        }
    };



    @Override
    public String toString() {
        return escuela;
    }



    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public Integer getNumeroPostulantes() {
        return numeroPostulantes;
    }

    public void setNumeroPostulantes(Integer numeroPostulantes) {
        this.numeroPostulantes = numeroPostulantes;
    }

    public Integer getNumeroVacantes() {
        return numeroVacantes;
    }

    public void setNumeroVacantes(Integer numeroVacantes) {
        this.numeroVacantes = numeroVacantes;
    }

    public Double getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public void setPuntajeMaximo(Double puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
    }

    public Double getPuntajeMinimo() {
        return puntajeMinimo;
    }

    public void setPuntajeMinimo(Double puntajeMinimo) {
        this.puntajeMinimo = puntajeMinimo;
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
        dest.writeString(area);
        dest.writeString(escuela);
        dest.writeString(facultad);
        if (numeroPostulantes == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(numeroPostulantes);
        }
        if (numeroVacantes == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(numeroVacantes);
        }
        if (puntajeMaximo == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(puntajeMaximo);
        }
        if (puntajeMinimo == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(puntajeMinimo);
        }
        dest.writeString(uid);
    }
}
