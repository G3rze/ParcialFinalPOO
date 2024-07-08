package org.example.parcialfinalpoo.Clases;

import java.util.Date;

public class Tarjeta { //00073123 - Clase Tarjeta de la base de datos

    private int id; //00073123 - Atributo id de tipo entero de la tarjeta
    private String numeroTarjeta; //00073123 - Atributo numeroTarjeta de tipo String de la tarjeta
    private Date fechaExpiracion; //00073123 - Atributo fechaExpiracion de tipo Date de la tarjeta
    private char tipoTarjeta; //00073123 - Atributo tipoTarjeta de tipo char de la tarjeta
    private String facilitadorTarjeta; //00073123 - Atributo facilitadorTarjeta de tipo String de la tarjeta
    private Cliente cliente; //00073123 - Atributo cliente de tipo Cliente que hace referencia a la clase Cliente

    public Tarjeta(int id, String numeroTarjeta, Date fechaExpiracion, char tipoTarjeta, String facilitadorTarjeta, Cliente cliente) { //00073123 - Constructor que permite crear objetos de tipo Tarjeta
        this.id = id; //00073123 - Asigna el valor de la variable global id con el parámetro
        this.numeroTarjeta = numeroTarjeta; //00073123 - Asigna el valor de la variable global numeroTarjeta con el parámetro
        this.fechaExpiracion = fechaExpiracion; //00073123 - Asigna el valor de la variable global fechaExpiracion con el parámetro
        this.tipoTarjeta = tipoTarjeta; //00073123 - Asigna el valor de la variable global tipoTarjeta con el parámetro
        this.facilitadorTarjeta = facilitadorTarjeta; //00073123 - Asigna el valor de la variable global facilitadorTarjeta con el parámetro
        this.cliente = cliente; //00073123 - Asigna el valor de la variable global cliente con el parámetro
    }

    public int getId() { //00073123 - Método getter de tipo entero para hacer referencia al ID de la Tarjeta
        return id; //00073123 - Retorna el valor del id de la tarjeta
    }

    public String getNumeroTarjeta() { //00073123 - Método getter de tipo String para hacer referencia al número de la tarjeta
        return numeroTarjeta; //00073123 - Retorna el valor del número de la tarjeta
    }

    public Date getFechaExpiracion() { //00073123 - Método getter de tipo Date para hacer referencia a la fecha de expiración de la tarjeta
        return fechaExpiracion; //00073123 - Retorna el valor de la fecha de expiración de la Tarjeta
    }

    public char getTipoTarjeta() { //00073123 - Método getter de tipo char para hacer referencia al tipo de la tarjeta
        return tipoTarjeta; //00073123 - Retorna el valor del tipo de la tarjeta
    }

    public String getFacilitadorTarjeta() { //00073123 - Método getter de tipo String para hacer referencia al facilitador de la tarjeta
        return facilitadorTarjeta; //00073123 - Retorna el valor del facilitador de la tarjeta de la Tarjeta
    }

    public Cliente getCliente() { //00073123 - Método getter de tipo Cliente para hacer referencia a la información del Cliente
        return cliente; //00073123 - Retorna la información del Cliente
    }

}