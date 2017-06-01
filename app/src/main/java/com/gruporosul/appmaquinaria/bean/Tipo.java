package com.gruporosul.appmaquinaria.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristian Ramírez on 23/05/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public class Tipo {
    @SerializedName("id")
    private int idTipo;
    @SerializedName("Descripcion")
    private String descripcion;

    public Tipo() {
    }

    public Tipo(int idTipo, String descripcion) {
        this.idTipo = idTipo;
        this.descripcion = descripcion;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
