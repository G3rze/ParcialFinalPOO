package com.example.parcialfinalpoo.Clases;

import java.util.Date;

public class Compra {

    private int id;
    private Date fechaCompra;
    private double montoTotal;
    private String descripcion;
    private Tarjeta tarjeta;

    public Compra(int id, Date fechaCompra, double montoTotal, String descripcion, Tarjeta tarjeta) {
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.montoTotal = montoTotal;
        this.descripcion = descripcion;
        this.tarjeta = tarjeta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }
}
