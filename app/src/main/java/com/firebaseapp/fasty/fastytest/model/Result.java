package com.firebaseapp.fasty.fastytest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.Map;

public class Result implements Parcelable{

    private String anoSemestre;
    private String carrera;
    private Double puntaje;
    private Integer respuestasBlanco;
    private Integer respuestasCorrectas;
    private Integer respuestasIncorrectas;
    private String universidad;
    private Date dateCreated;
    private String uid;


    public Result(){

    }

    public Result(String anoSemestre,
                  String carrera,
                  Double puntaje,
                  Integer respuestasBlanco,
                  Integer respuestasCorrectas,
                  Integer respuestasIncorrectas,
                  String universidad,
                  Date dateCreated,
                  String uid) {
        this.anoSemestre = anoSemestre;
        this.carrera = carrera;
        this.puntaje = puntaje;
        this.respuestasBlanco = respuestasBlanco;
        this.respuestasCorrectas = respuestasCorrectas;
        this.respuestasIncorrectas = respuestasIncorrectas;
        this.universidad = universidad;
        this.dateCreated = dateCreated;
        this.uid = uid;
    }


    protected Result(Parcel in) {
        anoSemestre = in.readString();
        carrera = in.readString();
        if (in.readByte() == 0) {
            puntaje = null;
        } else {
            puntaje = in.readDouble();
        }
        if (in.readByte() == 0) {
            respuestasBlanco = null;
        } else {
            respuestasBlanco = in.readInt();
        }
        if (in.readByte() == 0) {
            respuestasCorrectas = null;
        } else {
            respuestasCorrectas = in.readInt();
        }
        if (in.readByte() == 0) {
            respuestasIncorrectas = null;
        } else {
            respuestasIncorrectas = in.readInt();
        }
        universidad = in.readString();
        uid = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public String getAnoSemestre() {
        return anoSemestre;
    }

    public void setAnoSemestre(String anoSemestre) {
        this.anoSemestre = anoSemestre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

    public Integer getRespuestasBlanco() {
        return respuestasBlanco;
    }

    public void setRespuestasBlanco(Integer respuestasBlanco) {
        this.respuestasBlanco = respuestasBlanco;
    }

    public Integer getRespuestasCorrectas() {
        return respuestasCorrectas;
    }

    public void setRespuestasCorrectas(Integer respuestasCorrectas) {
        this.respuestasCorrectas = respuestasCorrectas;
    }

    public Integer getRespuestasIncorrectas() {
        return respuestasIncorrectas;
    }

    public void setRespuestasIncorrectas(Integer respuestasIncorrectas) {
        this.respuestasIncorrectas = respuestasIncorrectas;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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
        dest.writeString(anoSemestre);
        dest.writeString(carrera);
        if (puntaje == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(puntaje);
        }
        if (respuestasBlanco == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(respuestasBlanco);
        }
        if (respuestasCorrectas == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(respuestasCorrectas);
        }
        if (respuestasIncorrectas == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(respuestasIncorrectas);
        }
        dest.writeString(universidad);
        dest.writeString(uid);
    }
}
