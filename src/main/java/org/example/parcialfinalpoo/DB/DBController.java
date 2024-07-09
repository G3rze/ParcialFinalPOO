package org.example.parcialfinalpoo.DB;

import org.example.parcialfinalpoo.Clases.Cliente;
import org.example.parcialfinalpoo.Clases.Compra;
import org.example.parcialfinalpoo.Clases.Tarjeta;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

public class DBController {

    private Connection con; //00055623 Declara una variable privada 'con' de tipo Connection
    private PreparedStatement pStatement = null; //00055623 Declara e inicializa una variable privada 'pStatement' de tipo PreparedStatement a null
    private Statement statement = null; //00055623 Declara e inicializa una variable privada 'statement' de tipo Statement a null
    private static DBController DBInstance; //00055623 Declara una variable estática 'DBInstance' de tipo DBController

    private DBController() { //00055623 Constructor privado de DBController
        try {
            Credentials credentials = new Credentials(); //00055623 Crea una nueva instancia de la clase Credentials
            setCon(con = DriverManager.getConnection("jdbc:mysql://localhost/ParcialFinal", credentials.getUsername(),credentials.getPassword())); //00055623 Establece una conexión a la base de datos usando las credenciales y la asigna a 'con'
        } catch (SQLException e) { //00055623 Captura cualquier SQLException que ocurra durante la conexión a la base de datos
            throw new RuntimeException(e); //00055623 Lanza una RuntimeException si se captura una SQLException
        }
    }

    public static DBController getDBInstance() { //00055623 Método para obtener la instancia única de DBController (Singleton)
        if(DBInstance == null){ //00055623 Verifica si la instancia es null
            DBInstance = new DBController(); //00055623 Crea una nueva instancia de DBController si es null
        }
        return DBInstance; //00055623 Retorna la instancia de DBController
    }

    private Connection getCon() { //00055623 Método privado para obtener la conexión
        return con; //00055623 Retorna la conexión
    }

    private void setCon(Connection con) { //00055623 'Método privado para establecer la conexión'
        this.con = con; //00055623 Asigna la conexión pasada como argumento a la variable 'con'
    }

    public PreparedStatement getpStatement() { //00055623 Método público para obtener el PreparedStatement
        return pStatement; //00055623 Retorna el PreparedStatement
    }

    public void setpStatement(PreparedStatement pStatement) { //00055623 Método público para establecer el PreparedStatement
        this.pStatement = pStatement; //00055623 Asigna el PreparedStatement pasado como argumento a la variable 'pStatement'
    }

    public Statement getStatement() { //00055623 Método público para obtener el Statement
        return statement; //00055623 Retorna el Statement
    }

    public void setStatement(Statement statement) { //00055623 Método público para establecer el Statement
        this.statement = statement; //00055623 Asigna el Statement pasado como argumento a la variable 'statement'
    }

    public List<Cliente> getClientes(){ //00055623 Método público para obtener una lista de clientes
        List<Cliente> clientes = new ArrayList<>(); //00055623 Crea una nueva lista de clientes


        try {
            ResultSet resultSet = getDBInstance().con.createStatement().executeQuery("select * from Cliente");  //00055623 Ejecuta una consulta para obtener todos los clientes y guarda el resultado en un ResultSet

            while (resultSet.next()){ //00055623 Itera sobre cada fila del ResultSet
                clientes.add(new Cliente(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4))); //00055623 Añade un nuevo cliente a la lista con los datos del ResultSet
            }

        } catch (SQLException e) { //00055623 Captura cualquier SQLException que ocurra durante la consulta
            throw new RuntimeException(e); //00055623 Lanza una RuntimeException si se captura una SQLException
        }
        return clientes; //00055623 Retorna la lista de clientes
    }

    public List<Tarjeta> getTarjetas (){ //00055623 Método público para obtener una lista de tarjetas
        List<Tarjeta> tarjetas = new ArrayList<>(); //00055623 Crea una nueva lista de tarjetas

        try {
            ResultSet resultSet = getDBInstance().con.createStatement().executeQuery("select * from Tarjeta"); //00055623 Ejecuta una consulta para obtener todas las tarjetas y guarda el resultado en un ResultSet

            List<Cliente> clientes = getClientes(); //00055623 Obtiene la lista de clientes
            Cliente cliente = null; //00055623 Declara una variable 'cliente' de tipo Cliente y la inicializa a null

            while (resultSet.next()){ //00055623 Itera sobre cada fila del ResultSet
                for(Cliente c: clientes){ //00055623 Itera sobre cada cliente en la lista de clientes
                    if(c.getId() == resultSet.getInt("id_cliente")){ //00055623 Si el ID del cliente coincide con el ID del cliente en el ResultSet
                        cliente = c; //00055623 Asigna el cliente a la variable 'cliente'
                    }
                }
                tarjetas.add(new Tarjeta(resultSet.getInt("id"), resultSet.getString("numeroTarjeta"), resultSet.getDate("fechaExpiracion"), resultSet.getString("tipoTarjeta").charAt(0), resultSet.getString("facilitadorTarjeta"), cliente)); //00055623 Añade una nueva tarjeta a la lista con los datos del ResultSet y el cliente asociado
            }

        } catch (SQLException e) { //00055623 Captura cualquier SQLException que ocurra durante la consulta
            throw new RuntimeException(e); //00055623 Lanza una RuntimeException si se captura una SQLException
        }


        return tarjetas; //00055623 Retorna la lista de tarjetas
    }

    public List<Compra> getCompras() { //00073123 - Método que devuelve una lista de objetos de tipo Compra

        List<Compra> compras = new ArrayList<>(); //00073123 -  Inicizaliza una lista para almacenar objetos de tipo Compra


        try { //00073123 - Inicio del manejo de errores mediante try catch

            ResultSet resultSet = getDBInstance().con.createStatement().executeQuery("select * from compra"); //00073123 - Ejecuta una consulta SQL para seleccionar los registros de la tabla Compra y los almacena en un ResultSet
            List<Tarjeta> tarjetas = getTarjetas(); //00073123 - Llama al método getTarjetas para obtener la lista completa de objetos de ese tipo
            Tarjeta tarjeta = null; //00073123 - Declara una variable de tipo Tarjeta inicializada en null

            while (resultSet.next()){ //00073123 - Bucle while que itera en todas las líneas que encuentre del registro

                for(Tarjeta c: tarjetas){ //00073123 - Bucle for que itera en cada línea

                    if(c.getId() == resultSet.getInt("id_tarjeta")){ //00073123 - Verifica si el ID de la tarjeta coincide con el ID de la tarjeta en el ResultSet

                        tarjeta = c; //00073123 - Asigna la Tarjeta a la variable tarjeta
                    }
                }
                compras.add(new Compra(resultSet.getInt("id"), resultSet.getDate("fechaCompra"), resultSet.getDouble("montoTotal"), resultSet.getString("descripcion"),tarjeta)); //00073123 - Creación de objeto de tipo Compra con los datos del registro del ResultSet y la tarjeta encontrada, y lo agrega a la lista compras.
            }

            Close(); //00073123 - Cierra la conexión

        } catch (SQLException e) { //00073123 - Inicio del bloque catch para manejar la excepciones

            throw new RuntimeException(e); //00073123 - En caso de error, lanza un RuntimeException con la excepción original
        }

        return compras; //00073123 - Devuelve la lista completa de compras
    }

    public void insertClient(Cliente cliente){ //00073123 -

        try { //00073123 - Inicio del manejo de errores mediante try catch

            pStatement = getDBInstance().con.prepareStatement("insert into Cliente(nombreCompleto,direccion,telefono) values(?,?,?)"); //00073123 - Prepara una consulta de insert para colocar los valores de los campos según los datos dados

            pStatement.setString(1, cliente.getNombreCompleto()); //00073123 - Asigna el valor del nombre completo del cliente en el primer "?"
            pStatement.setString(2, cliente.getDireccion()); //00073123 - Asigna el valor de la dirección del cliente en el segundo "?"
            pStatement.setString(3, cliente.getTelefono()); //00073123 - Asigna el valor del teléfono del cliente en el tercer "?"

            pStatement.execute(); //00073123 - Ejecuta la consulta select preparada con los valores especificados

            Close(); //00073123 - Cierra la conexión

        } catch (SQLException e) { //00073123 - Inicio del bloque catch para manejar la excepciones

            throw new RuntimeException(e); //00073123 - En caso de error, lanza un RuntimeException con la excepción original
        }

    }

    public void insertTarjeta(Tarjeta tarjeta){ //00073123 -

        try { //00073123 - Inicio del manejo de errores mediante try catch

            pStatement = getDBInstance().con.prepareStatement("insert into Tarjeta(numeroTarjeta, fechaExpiracion, tipoTarjeta, facilitadorTarjeta, id_cliente) values(?,?,?,?,?)"); //00073123 - Prepara una consulta de insert para colocar los valores de los campos según los datos dados

            pStatement.setString(1, tarjeta.getNumeroTarjeta()); //00073123 - Asigna el valor del número de la tarjeta en el primer "?"
            pStatement.setString(2, DateConverter(tarjeta.getFechaExpiracion())); //00073123 - Asigna el valor de la fecha de expiración en el segundo "?"
            pStatement.setString(3, "" + tarjeta.getTipoTarjeta()); //00073123 - Asigna el valor del tipo de la tarjeta en el tercer "?"
            pStatement.setString(4, tarjeta.getFacilitadorTarjeta()); //00073123 - Asigna el valor del facilitador de la tarjeta en el cuarto "?"
            pStatement.setString(5, String.valueOf(tarjeta.getCliente().getId())); //00073123 - Asigna el valor del id del cliente en el quinto "?"

            pStatement.execute(); //00073123 - Ejecuta la consulta select preparada con los valores especificados

            Close(); //00073123 - Cierra la conexión

        } catch (SQLException e) { //00073123 - Inicio del bloque catch para manejar la excepciones

            throw new RuntimeException(e); //00073123 - En caso de error, lanza un RuntimeException con la excepción original
        }

    }

    public void insertCompra(Compra compra){ //00073123 -

        try { //00073123 - Inicio del manejo de errores mediante try catch

            pStatement = getDBInstance().con.prepareStatement("insert into Compra(fechaCompra, montoTotal, descripcion, id_tarjeta) values(?,?,?,?)"); //00073123 - Prepara una consulta de insert para colocar los valores de los campos según los datos dados

            pStatement.setString(1, DateConverter(compra.getFechaCompra())); //00073123 - Asigna el valor de la fecha de la compra en el primer "?"
            pStatement.setString(2, String.valueOf(compra.getMontoTotal())); //00073123 - Asigna el valor del monto total de la compra en el segundo "?"
            pStatement.setString(3, compra.getDescripcion()); //00073123 - Asigna el valor de la descripción de la compra en el tercer "?"
            pStatement.setString(4, String.valueOf(compra.getTarjeta().getId())); //00073123 - Asigna el valor del id de la tarjeta en el cuarto "?"

            pStatement.execute(); //00073123 - Ejecuta la consulta select preparada con los valores especificados

            Close(); //00073123 - Cierra la conexión

        } catch (SQLException e) { //00073123 - Inicio del bloque catch para manejar la excepciones

            throw new RuntimeException(e); //00073123 - En caso de error, lanza un RuntimeException con la excepción original
        }
    }

    public void deleteCliente(int id){ //00055623 funcion encargada de eliminar registros en la tabla Cliente

        try {
            statement = getDBInstance().con.createStatement(); //00055623 Obtiene un nuevo statement

            statement.executeUpdate("delete from Cliente where id = " + id); //00055623 se manda la query a la base de datos

            Close(); //00055623 cierra la conección

        } catch (SQLException e) {
            throw new RuntimeException(e); //00055623 devuelve un error del sql
        }

    }

    public void deleteTarjeta(int id){ //00055623 funcion encargada de eliminar registros en la tabla Tarjeta
        try {
            statement = getDBInstance().con.createStatement(); //00055623 Obtiene un nuevo statement

            statement.executeUpdate("delete from Tarjeta where id = " + id); //00055623 se manda la query a la base de datos

            Close(); //00055623 cierra la conección

        } catch (SQLException e) {
            throw new RuntimeException(e); //00055623 devuelve un error del sql
        }
    }

    public void deleteCompras(int id){ //00055623 funcion encargada de eliminar registros en la tabla Compras
        try {
            statement = getDBInstance().con.createStatement(); //00055623 Obtiene un nuevo statement

            statement.executeUpdate("delete from Compra where id = " + id); //00055623 se manda la query a la base de datos

            Close(); //00055623 cierra la conección

        } catch (SQLException e) {
            throw new RuntimeException(e); //00055623 devuelve un error del sql
        }
    }

    public void updateCliente(int id, String name, String dir, String tel){ //00026223 funcion que cambia valores ya establecidos en la tabla cliente
        try {
            pStatement = getDBInstance().con.prepareStatement("update Cliente set nombreCompleto = ?, direccion =  ?, telefono  = ? where id = " + id); //00026223 se selecciona el campo a modificar

            pStatement.setString(1, name); //00026223 se manda un nuevo nombre del cliente
            pStatement.setString(2, dir); //00026223 se manda una nueva direccion
            pStatement.setString(3, tel); //00026223 se manda el telefono nuevo

            pStatement.execute(); //00026223 se ejecuta el cambio en la tabla

            Close(); //00026223 se cierra la conexion a la base de datos para ahorrar recursos

        } catch (SQLException e) { //00026223 esto va a correr si no se pudo conectar a la base de datos
            throw new RuntimeException(e); //00026223 se tira una excepcion por no poder conectarse
        }
    }

    public void updateTarjeta(int id, String number, String expDate, Character type, String cardFacilitator, int idCliente){ //00026223 funcion que cambia valores ya establecidos en la tabla tarjeta

        List<Cliente> clientes = getClientes(); //00026223 se crea una lista de clientes

        for (Cliente c: clientes) { // 00026223 se recorre la tabla de clientes
            if (c.getId() == idCliente) { //00026223 se seleccionan los campos que el id de los clientes sea igual a idCliente en la tabla
                try {
                    pStatement = getDBInstance().con.prepareStatement("update Tarjeta set numeroTarjeta = ?, fechaExpiracion =  ?, tipoTarjeta  = ?, facilitadorTarjeta = ?, id_cliente = ? where id = " + id); //00026223 se selecciona el campo a modificar

                    pStatement.setString(1,  number); //00026223 se manda el numero de la tarjeta a modificar
                    pStatement.setString(2,  expDate); //00026223 se manda la fecha de vencimiento de la tarjeta a modificar
                    pStatement.setString(3, ""+type); //00026223 se guarda el tipo de la tarjeta
                    pStatement.setString(4, cardFacilitator); //00026223  se manda el facilitador de la tarjeta
                    pStatement.setString(5, String.valueOf(idCliente)); //00026223  se manda el id del cliente para la tarjeta

                    pStatement.execute(); //00026223  se corre el modificador de campos en la tabla

                    Close(); //00026223 se cierra la base de datos para ahorrar recursos

                } catch (SQLException e) {  //00026223 esto va a correr si no se pudo conectar a la base de datos
                    throw new RuntimeException(e); //00026223 se tira una excepcion por no poder conectarse
                }
            }
        }
    }

    public void updateCompra(int id, String buyDate, String total, String desc, int idTarjeta){ //00026223 funcion que cambia valores ya establecidos en la tabla compras
        List<Tarjeta> tarjetas = getDBInstance().getTarjetas(); //00026223 se crea una lista de tarjetas

        for (Tarjeta t: tarjetas){ //00026223 se recorre toda la tabla de tarjetas
            if (t.getId() == idTarjeta){ //00026223 se comparan los idTarjeta con el id de las tarjetas en su propia tabla
                try {
                    pStatement = getDBInstance().con.prepareStatement("update Compra set fechaCompra = ?, montoTotal =  ?, descripcion  = ?, id_tarjeta = ? where id = " + id); //00026223 se selecciona el campo a modificar

                    pStatement.setString(1, buyDate); //00026223 se manda la fecha de compra a modificar
                    pStatement.setString(2, total); //00026223 se manda el total de la compra que se va a modificar
                    pStatement.setString(3, desc); //00026223 se guarda el descuento a modificar
                    pStatement.setString(4, String.valueOf(idTarjeta)); //00026223

                    pStatement.execute(); //00026223 se ejecuta el cambio en la tabla

                    Close(); //00026223 se cierra la base de datos para ahorrar recursos

                } catch (SQLException e) { //00026223 esto va a correr si no se pudo conectar a la base de datos
                    throw new RuntimeException(e); //00026223 se tira una excepcion por no poder conectarse
                }
            }
        }
    }


    public String DateConverter(Date fecha){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(fecha);
    }

    private void Close() throws SQLException {
        con.close();
        DBInstance = null;
    }

}
