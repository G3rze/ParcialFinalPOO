Para usarse la base de datos se necesita una clase "Credentials" en la ruta src/main/java/org/example/parcialfinalpoo/DB para usarse, a continuación dejo un sample code para su creación

package org.example.parcialfinalpoo.DB;

public class Credentials {
    private String username  = "USER";
    private String password = "PASSWORD";

    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
}

Esto se hizo para evitar problemas con las bases locales de cada miembro del grupo
