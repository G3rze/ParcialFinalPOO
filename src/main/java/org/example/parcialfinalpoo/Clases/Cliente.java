package org.example.parcialfinalpoo.Clases;

public class Cliente { //00073123 - Clase cliente de la base de datos

    private int id; //00073123 - Atributo ID de tipo entero del cliente
    private String nombreCompleto; //00073123 - Atributo que guarda el nombre completo del cliente de tipo String
    private String direccion; // 00073123 - Atributo que guarda la dirección del cliente de tipo String
    private String telefono; // 00073123 - Atributo que guarda el teléfono del cliente de tipo String

    public Cliente(int id, String nombreCompleto, String direccion, String telefono){ //00073123 - Constructor que permite crear objetos de tipo Cliente
        this.id = id; //00073123 - Asigna el valor de la variable global id con el parámetro
        this.nombreCompleto = nombreCompleto; //00073123 - Asigna el valor de la variable global nombreCompleto con el parámetro
        this.direccion = direccion; //00073123 - Asigna el valor de la variable global dirección con el parámetro
        this.telefono = telefono; //00073123 - Asigna el valor de la variable global teléfono con el parámetro
    }

    public int getId() { //00073123 - Método getter de tipo entero para hacer referencia al ID
        return id; //00073123 - Retorna el valor del id del cliente
    }

    public String getNombreCompleto() { //00073123 - Método getter de tipo String para hacer referencia al nombre completo
        return nombreCompleto; //00073123 - Retorna el valor del nombre completo del cliente
    }

    public String getDireccion() { //00073123 - Método getter de tipo String para hacer referencia a la dirección
        return direccion; //00073123 - Retorna el valor de la dirección del cliente
    }

    public String getTelefono() { //00073123 - Método getter de tipo String para hacer referencia al teléfono
        return telefono; //00073123 - Retorna el valor del teléfono del cliente
    }

}