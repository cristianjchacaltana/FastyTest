package com.firebaseapp.fasty.fastytest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable{

    private String alternativaA;
    private String alternativaB;
    private String alternativaC;
    private String alternativaD;
    private String alternativaE;
    private String pregunta;
    private Boolean preguntaImagen;
    private String respuesta;
    private Integer uid;



    public Question(){

    }

    public Question(String alternativaA,
                    String alternativaB,
                    String alternativaC,
                    String alternativaD,
                    String alternativaE,
                    String pregunta,
                    Boolean preguntaImagen,
                    String respuesta,
                    Integer uid){
        this.alternativaA = alternativaA;
        this.alternativaB = alternativaB;
        this.alternativaC = alternativaC;
        this.alternativaD = alternativaD;
        this.alternativaE = alternativaE;
        this.pregunta = pregunta;
        this.preguntaImagen = preguntaImagen;
        this.respuesta = respuesta;
        this.uid = uid;
    }


    protected Question(Parcel in) {
        alternativaA = in.readString();
        alternativaB = in.readString();
        alternativaC = in.readString();
        alternativaD = in.readString();
        alternativaE = in.readString();
        pregunta = in.readString();
        byte tmpPreguntaImagen = in.readByte();
        preguntaImagen = tmpPreguntaImagen == 0 ? null : tmpPreguntaImagen == 1;
        respuesta = in.readString();
        if (in.readByte() == 0) {
            uid = null;
        } else {
            uid = in.readInt();
        }
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getAlternativaA() {
        return alternativaA;
    }

    public void setAlternativaA(String alternativaA) {
        this.alternativaA = alternativaA;
    }

    public String getAlternativaB() {
        return alternativaB;
    }

    public void setAlternativaB(String alternativaB) {
        this.alternativaB = alternativaB;
    }

    public String getAlternativaC() {
        return alternativaC;
    }

    public void setAlternativaC(String alternativaC) {
        this.alternativaC = alternativaC;
    }

    public String getAlternativaD() {
        return alternativaD;
    }

    public void setAlternativaD(String alternativaD) {
        this.alternativaD = alternativaD;
    }

    public String getAlternativaE() {
        return alternativaE;
    }

    public void setAlternativaE(String alternativaE) {
        this.alternativaE = alternativaE;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Boolean getPreguntaImagen() {
        return preguntaImagen;
    }

    public void setPreguntaImagen(Boolean preguntaImagen) {
        this.preguntaImagen = preguntaImagen;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(alternativaA);
        dest.writeString(alternativaB);
        dest.writeString(alternativaC);
        dest.writeString(alternativaD);
        dest.writeString(alternativaE);
        dest.writeString(pregunta);
        dest.writeByte((byte) (preguntaImagen == null ? 0 : preguntaImagen ? 1 : 2));
        dest.writeString(respuesta);
        if (uid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(uid);
        }
    }
}

