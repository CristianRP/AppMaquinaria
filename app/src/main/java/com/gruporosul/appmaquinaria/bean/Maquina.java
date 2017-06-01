package com.gruporosul.appmaquinaria.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristian Ramírez on 22/05/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public class Maquina {
    @SerializedName("id")
    private int idMaquina;
    @SerializedName("codigo")
    private String codigo;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("Periodo_Mantenimiento")
    private String periodoMantenimiento;
    @SerializedName("Placa")
    private String placa;

    public static List<Maquina> MAQUINARIA = new ArrayList<>();

    public Maquina() {}

    public Maquina(int idMaquina, String codigo, String descripcion, String tipo,
                   String periodoMantenimiento, String placa) {
        this.idMaquina = idMaquina;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.periodoMantenimiento = periodoMantenimiento;
        this.placa = placa;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPeriodoMantenimiento() {
        return periodoMantenimiento;
    }

    public void setPeriodoMantenimiento(String periodoMantenimiento) {
        this.periodoMantenimiento = periodoMantenimiento;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
