package org.example.parcialfinalpoo.Clases;

import java.util.Date;

public class Compra { //00073123 - Clase Compra de la base de datos

    private int id; //00073123 - Atributo de tipo entero que guarda el valor del ID
    private Date fechaCompra; //00073123 - Atributo de tipo String que guarda el valor de la fecha de la compra
    private double montoTotal; //00073123 - Atributo de tipo double que guarda el valor de el monto total de la compra
    private String descripcion; //00073123 - Atributo de tipo String que guarda el valor de la descripción de la compra
    private Tarjeta tarjeta; //00073123 - Atributo de tipo Tarjeta  que guarda la información de la tarjeta

    public Compra(int id, Date fechaCompra, double montoTotal, String descripcion, Tarjeta tarjeta) { //00073123 - Constructor que permite crear objetos de tipo Compra
        this.id = id; //00073123 - Asigna el valor de la variable global id con el parámetro
        this.fechaCompra = fechaCompra; //00073123 - Asigna el valor de la variable global fechaCompra con el parámetro
        this.montoTotal = montoTotal; //00073123 - Asigna el valor de la variable global montoTotal con el parámetro
        this.descripcion = descripcion; //00073123 - Asigna el valor de la variable global descripcion con el parámetro
        this.tarjeta = tarjeta; //00073123 - Asigna el valor de la variable global tarjeta con el parámetro
    }

    public int getId(){ //00073123 - Método getter de tipo entero para hacer referencia al id
        return id; //00073123 - Retorna el valor del id de la compra
    }

    public Date getFechaCompra() { //00073123 - Método getter de tipo Date para hacer referencia a la fecha de la compra
        return fechaCompra; //00073123 - Retorna el valor de la fecha de la compra
    }


    public double getMontoTotal() { //00073123 - Método getter de tipo double para hacer referencia al monto total de la compra
        return montoTotal; //00073123 - Retorna el valor del el monto total de la compra
    }


    public String getDescripcion() { //00073123 - Método getter de tipo String para hacer referencia a la descripción de la compra
        return descripcion; //00073123 - Retorna el valor de la descripción de la compra
    }

    public Tarjeta getTarjeta() { //00073123 - Método getter de tipo Tarjeta para hacer referencia a la información de la tarjeta
        return tarjeta; //00073123 - Retorna la información de la Tarjeta
    }

}