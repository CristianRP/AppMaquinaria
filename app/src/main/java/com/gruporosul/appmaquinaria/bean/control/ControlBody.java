package com.gruporosul.appmaquinaria.bean.control;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristian Ramírez on 1/06/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public class ControlBody {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("CodigoSupervisor")
    @Expose
    private Integer codigoSupervisor;
    @SerializedName("SistemaElectrico")
    @Expose
    private String sistemaElectrico;
    @SerializedName("Cilindros")
    @Expose
    private String cilindros;
    @SerializedName("BombaHD")
    @Expose
    private String bombaHD;
    @SerializedName("RodajeCadena")
    @Expose
    private String rodajeCadena;
    @SerializedName("Cuchilla")
    @Expose
    private String cuchilla;
    @SerializedName("Llantas")
    @Expose
    private String llantas;
    @SerializedName("Mangueras")
    @Expose
    private String mangueras;
    @SerializedName("Sillones")
    @Expose
    private String sillones;
    @SerializedName("Vidrios")
    @Expose
    private String vidrios;
    @SerializedName("Motor")
    @Expose
    private String motor;
    @SerializedName("Otros")
    @Expose
    private String otros;
    @SerializedName("CodigoMaquina")
    @Expose
    private Integer codigoMaquina;

    public ControlBody() {
    }

    public ControlBody(Integer id, Integer codigoSupervisor, String sistemaElectrico,
                       String cilindros, String bombaHD, String rodajeCadena,
                       String cuchilla, String llantas, String mangueras,
                       String sillones, String vidrios, String motor, String otros,
                       Integer codigoMaquina) {
        this.id = id;
        this.codigoSupervisor = codigoSupervisor;
        this.sistemaElectrico = sistemaElectrico;
        this.cilindros = cilindros;
        this.bombaHD = bombaHD;
        this.rodajeCadena = rodajeCadena;
        this.cuchilla = cuchilla;
        this.llantas = llantas;
        this.mangueras = mangueras;
        this.sillones = sillones;
        this.vidrios = vidrios;
        this.motor = motor;
        this.otros = otros;
        this.codigoMaquina = codigoMaquina;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoSupervisor() {
        return codigoSupervisor;
    }

    public void setCodigoSupervisor(Integer codigoSupervisor) {
        this.codigoSupervisor = codigoSupervisor;
    }

    public String getSistemaElectrico() {
        return sistemaElectrico;
    }

    public void setSistemaElectrico(String sistemaElectrico) {
        this.sistemaElectrico = sistemaElectrico;
    }

    public String getCilindros() {
        return cilindros;
    }

    public void setCilindros(String cilindros) {
        this.cilindros = cilindros;
    }

    public String getBombaHD() {
        return bombaHD;
    }

    public void setBombaHD(String bombaHD) {
        this.bombaHD = bombaHD;
    }

    public String getRodajeCadena() {
        return rodajeCadena;
    }

    public void setRodajeCadena(String rodajeCadena) {
        this.rodajeCadena = rodajeCadena;
    }

    public String getCuchilla() {
        return cuchilla;
    }

    public void setCuchilla(String cuchilla) {
        this.cuchilla = cuchilla;
    }

    public String getLlantas() {
        return llantas;
    }

    public void setLlantas(String llantas) {
        this.llantas = llantas;
    }

    public String getMangueras() {
        return mangueras;
    }

    public void setMangueras(String mangueras) {
        this.mangueras = mangueras;
    }

    public String getSillones() {
        return sillones;
    }

    public void setSillones(String sillones) {
        this.sillones = sillones;
    }

    public String getVidrios() {
        return vidrios;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public Integer getCodigoMaquina() {
        return codigoMaquina;
    }

    public void setCodigoMaquina(Integer codigoMaquina) {
        this.codigoMaquina = codigoMaquina;
    }
}
