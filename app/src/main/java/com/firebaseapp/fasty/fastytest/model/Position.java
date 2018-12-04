package com.firebaseapp.fasty.fastytest.model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Position {

    private String carrera;
    private String condicion;
//    private String examen;
    private Integer merito;
    private String posicion;
    private Double puntaje;
//    private String universidad;
    private @ServerTimestamp Date timeCreated;



    public Position(){

    }

    public Position(String carrera,
                    String condicion,
                    Integer merito,
                    String posicion,
                    Double puntaje,
                    Date timeCreated){
        this.carrera = carrera;
        this.condicion = condicion;
//        this.examen = examen;
        this.merito = merito;
        this.posicion = posicion;
        this.puntaje = puntaje;
//        this.universidad = universidad;
        this.timeCreated = timeCreated;
    }



    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

//    public String getExamen() {
//        return examen;
//    }

//    public void setExamen(String examen) {
//        this.examen = examen;
//    }

    public Integer getMerito() {
        return merito;
    }

    public void setMerito(Integer merito) {
        this.merito = merito;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

//    public String getUniversidad() {
//        return universidad;
//    }

//    public void setUniversidad(String universidad) {
//        this.universidad = universidad;
//    }


    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

}
