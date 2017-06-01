package com.gruporosul.appmaquinaria.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Cristian Ramírez on 23/05/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public class Reparacion {
    @SerializedName("id")
    private int idReparacion;
    @SerializedName("Codigo_Maquina")
    private int idMaquina;
    @SerializedName("NombreMaquina")
    private String nombreMaquina;
    @SerializedName("Fecha")
    private Date fecha;
    @SerializedName("Fecha_Paralizacion")
    private Date fechaParalizacion;
    @SerializedName("Fecha_Inicio")
    private Date fechaInicio;
    @SerializedName("Estado_Reparacion")
    private String estado;
    @SerializedName("Fecha_Fin")
    private Date fechaFin;
    @SerializedName("Dias_Asignados")
    private int diasAsignados;
    @SerializedName("Diagnostico")
    private String diagnostico;

    public static List<Reparacion> REPARACIONES = new ArrayList<>();

    public Reparacion() {
    }

    public Reparacion(int idReparacion, int idMaquina, String nombreMaquina, Date fecha,
                      Date fechaParalizacion, Date fechaInicio, String estado,
                      Date fechaFin, int diasAsignados, String diagnostico) {
        this.idReparacion = idReparacion;
        this.idMaquina = idMaquina;
        this.nombreMaquina = nombreMaquina;
        this.fecha = fecha;
        this.fechaParalizacion = fechaParalizacion;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        this.fechaFin = fechaFin;
        this.diasAsignados = diasAsignados;
        this.diagnostico = diagnostico;
    }

    public int getIdReparacion() {
        return idReparacion;
    }

    public void setIdReparacion(int idReparacion) {
        this.idReparacion = idReparacion;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getNombreMaquina() {
        return nombreMaquina;
    }

    public void setNombreMaquina(String nombreMaquina) {
        this.nombreMaquina = nombreMaquina;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaParalizacion() {
        return fechaParalizacion;
    }

    public void setFechaParalizacion(Date fechaParalizacion) {
        this.fechaParalizacion = fechaParalizacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getDiasAsignados() {
        return diasAsignados;
    }

    public void setDiasAsignados(int diasAsignados) {
        this.diasAsignados = diasAsignados;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}
