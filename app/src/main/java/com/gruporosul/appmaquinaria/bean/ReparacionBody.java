package com.gruporosul.appmaquinaria.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Cristian Ramírez on 24/05/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public class ReparacionBody {

    @SerializedName("FechaParalizacion")
    @Expose
    private Date fechaParalizacion;
    @SerializedName("FechaInicio")
    @Expose
    private Date fechaInicio;
    @SerializedName("EstadoReparacion")
    @Expose
    private String estadoReparacion;
    @SerializedName("FechaFin")
    @Expose
    private Date fechaFin;
    @SerializedName("DiasAsignados")
    @Expose
    private Integer diasAsignados;
    @SerializedName("CodigoMaquina")
    @Expose
    private Integer codigoMaquina;
    @SerializedName("Diagnostico")
    @Expose
    private String diagnostico;

    public ReparacionBody() {
    }

    public ReparacionBody(Date fechaParalizacion, Date fechaInicio, String estadoReparacion,
                          Date fechaFin, Integer diasAsignados, Integer codigoMaquina,
                          String diagnostico) {
        this.fechaParalizacion = fechaParalizacion;
        this.fechaInicio = fechaInicio;
        this.estadoReparacion = estadoReparacion;
        this.fechaFin = fechaFin;
        this.diasAsignados = diasAsignados;
        this.codigoMaquina = codigoMaquina;
        this.diagnostico = diagnostico;
    }
}
