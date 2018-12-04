package com.firebaseapp.fasty.fastytest.model;

import java.util.ArrayList;

public class Questions {

    private ArrayList<Question> preguntas;
    private String uid;


    public Questions(){

    }


    public ArrayList<Question> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Question> preguntas) {
        this.preguntas = preguntas;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


}
