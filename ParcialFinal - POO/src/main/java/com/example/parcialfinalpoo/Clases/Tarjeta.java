package com.example.parcialfinalpoo.Clases;

import java.util.Date;

public class Tarjeta {

    private int id;
    private String numeroTarjeta;
    private Date fechaExpiracion;
    private char tipoTarjeta;
    private String facilitadorTarjeta;
    private Cliente cliente;

    public Tarjeta(int id, String numeroTarjeta, Date fechaExpiracion, char tipoTarjeta, String facilitadorTarjeta, Cliente cliente) {
        this.id = id;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.tipoTarjeta = tipoTarjeta;
        this.facilitadorTarjeta = facilitadorTarjeta;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public char getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(char tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getFacilitadorTarjeta() {
        return facilitadorTarjeta;
    }

    public void setFacilitadorTarjeta(String facilitadorTarjeta) {
        this.facilitadorTarjeta = facilitadorTarjeta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
