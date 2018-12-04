package com.firebaseapp.fasty.fastytest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class University implements Parcelable {

    private String departamento;
    private String distrito;
    private String fechaFundacion;
    private String imagen;
    private String nombre;
    private String provincia;
    private String siglas;
    private String tipoUniversidad;
    private String uid;



    public University(){

    }

    public University(String departamento,
                      String fechaFundacion,
                      String imagen,
                      String nombre,
                      String provincia,
                      String siglas,
                      String tipoUniversidad,
                      String uid){
        this.departamento = departamento;
        this.fechaFundacion = fechaFundacion;
        this.imagen = imagen;
        this.nombre = nombre;
        this.provincia = provincia;
        this.siglas = siglas;
        this.tipoUniversidad = tipoUniversidad;
        this.uid = uid;
    }



    protected University(Parcel in) {
        departamento = in.readString();
        distrito = in.readString();
        fechaFundacion = in.readString();
        imagen = in.readString();
        nombre = in.readString();
        provincia = in.readString();
        siglas = in.readString();
        tipoUniversidad = in.readString();
        uid = in.readString();
    }

    public static final Creator<University> CREATOR = new Creator<University>() {
        @Override
        public University createFromParcel(Parcel in) {
            return new University(in);
        }

        @Override
        public University[] newArray(int size) {
            return new University[size];
        }
    };

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(String fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getTipoUniversidad() {
        return tipoUniversidad;
    }

    public void setTipoUniversidad(String tipoUniversidad) {
        this.tipoUniversidad = tipoUniversidad;
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
        dest.writeString(departamento);
        dest.writeString(distrito);
        dest.writeString(fechaFundacion);
        dest.writeString(imagen);
        dest.writeString(nombre);
        dest.writeString(provincia);
        dest.writeString(siglas);
        dest.writeString(tipoUniversidad);
        dest.writeString(uid);
    }
}
