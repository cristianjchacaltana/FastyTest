package com.firebaseapp.fasty.fastytest.model;

import java.util.ArrayList;

public class Merit {

    private String area;
    private String escuela;
    private String facultad;
    private int numeroVacantes;
    private ArrayList<MeritScore> puntajes;


    public Merit(){

    }

    public Merit(String area, String escuela, String facultad, int numeroVacantes, ArrayList<MeritScore> puntajes){
        this.area = area;
        this.escuela = escuela;
        this.facultad = facultad;
        this.numeroVacantes = numeroVacantes;
        this.puntajes = puntajes;
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

    public int getNumeroVacantes() {
        return numeroVacantes;
    }

    public void setNumeroVacantes(int numeroVacantes) {
        this.numeroVacantes = numeroVacantes;
    }

    public ArrayList<MeritScore> getpuntajes() {
        return puntajes;
    }

    public void setpuntajes(ArrayList<MeritScore> puntajes) {
        this.puntajes = puntajes;
    }
}
