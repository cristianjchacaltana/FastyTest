package com.firebaseapp.fasty.fastytest.model;

public class MeritScore {

    private String condicion;
    private int merito;
    private double puntaje;


    public MeritScore(){

    }


    public MeritScore(String condicion, int merito, double puntaje){
        this.condicion = condicion;
        this.merito = merito;
        this.puntaje = puntaje;
    }



    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public int getMerito() {
        return merito;
    }

    public void setMerito(int merito) {
        this.merito = merito;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

}
