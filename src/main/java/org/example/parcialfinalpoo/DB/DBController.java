package org.example.parcialfinalpoo.DB;

import org.example.parcialfinalpoo.Clases.Cliente;
import org.example.parcialfinalpoo.Clases.Compra;
import org.example.parcialfinalpoo.Clases.Tarjeta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Compra> getCompras() {
        List<Compra> compras = new ArrayList<>();


        try {
            ResultSet resultSet = getDBInstance().con.createStatement().executeQuery("select * from compra");
            List<Tarjeta> tarjetas = getTarjetas();
            Tarjeta tarjeta = null;

            while (resultSet.next()){
                for(Tarjeta c: tarjetas){
                    if(c.getId() == resultSet.getInt("id_cliente")){
                        tarjeta = c;
                    }
                }
                compras.add(new Compra(resultSet.getInt("id"), resultSet.getDate("fechaCompra"), resultSet.getDouble("montoTotal"), resultSet.getString("descripcion"),tarjeta));
            }

            getCon().close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return compras;
    }

    public void insertClient(Cliente cliente){
        try {
            pStatement = getDBInstance().con.prepareStatement("insert into Cliente(nombreCompleto,direccion,telefono) values(?,?,?)");

            pStatement.setString(1, cliente.getNombreCompleto());
            pStatement.setString(2, cliente.getDireccion());
            pStatement.setString(3, cliente.getTelefono());

            pStatement.execute();

            getCon().close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertTarjeta(Tarjeta tarjeta){
        try {
            pStatement = getDBInstance().con.prepareStatement("insert into Tarjeta(numeroTarjeta, fechaExpiracion, tipoTarjeta, facilitadorTarjeta, id_cliente) values(?,?,?,?,?)");

            pStatement.setString(1, tarjeta.getNumeroTarjeta());
            pStatement.setString(2, tarjeta.getFechaExpiracion().toString());
            pStatement.setString(3, "" + tarjeta.getTipoTarjeta());
            pStatement.setString(4, tarjeta.getFacilitadorTarjeta());
            pStatement.setString(5, String.valueOf(tarjeta.getCliente().getId()));

            pStatement.execute();

            getCon().close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertCompra(Compra compra){
        try {
            pStatement = getDBInstance().con.prepareStatement("insert into Compra(fechaCompra, montoTotal, descripcion, id_tarjeta) values(?,?,?,?)");

            pStatement.setString(1, compra.getFechaCompra().toString());
            pStatement.setString(2, String.valueOf(compra.getMontoTotal()));
            pStatement.setString(3, compra.getDescripcion());
            pStatement.setString(4, String.valueOf(compra.getTarjeta().getId()));

            pStatement.execute();

            getCon().close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCliente(int id){ //00055623 funcion encargada de eliminar registros en la tabla Cliente

        try {
            statement = getDBInstance().con.createStatement(); //00055623 Obtiene un nuevo statement

            statement.executeUpdate("delete from Cliente where id = " + id); //00055623 se manda la query a la base de datos

            getCon().close(); //00055623 cierra la conección

        } catch (SQLException e) {
            throw new RuntimeException(e); //00055623 devuelve un error del sql
        }

    }

    public void deleteTarjeta(int id){ //00055623 funcion encargada de eliminar registros en la tabla Tarjeta
        try {
            statement = getDBInstance().con.createStatement(); //00055623 Obtiene un nuevo statement

            statement.executeUpdate("delete from Tarjeta where id = " + id); //00055623 se manda la query a la base de datos

            getCon().close(); //00055623 cierra la conección

        } catch (SQLException e) {
            throw new RuntimeException(e); //00055623 devuelve un error del sql
        }
    }

    public void deleteCompras(int id){ //00055623 funcion encargada de eliminar registros en la tabla Compras
        try {
            statement = getDBInstance().con.createStatement(); //00055623 Obtiene un nuevo statement

            statement.executeUpdate("delete from Compra where id = " + id); //00055623 se manda la query a la base de datos

            getCon().close(); //00055623 cierra la conección

        } catch (SQLException e) {
            throw new RuntimeException(e); //00055623 devuelve un error del sql
        }
    }

    public void updateCliente(int id, String name, String dir, String tel){
        try {
            pStatement = getDBInstance().con.prepareStatement("update Cliente set nombreCompleto = '?', direccion =  '?', telefono  = '?' where id = " + id);

            pStatement.setString(1, name);
            pStatement.setString(2, dir);
            pStatement.setString(3, tel);

            pStatement.execute();

            getCon().close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTarjeta(int id, String number, String expDate, Character type, String cardFacilitator, int idCliente){

        List<Cliente> clientes = getClientes();

        for (Cliente c: clientes) {
            if (c.getId() == idCliente) {
                try {
                    pStatement = getDBInstance().con.prepareStatement("update Tarjeta set numeroTarjeta = '?', fechaExpiracion =  '?', tipoTarjeta  = '?', facilitadorTarjeta = '?', id_cliente = '?' where id = " + id);

                    pStatement.setString(1, number);
                    pStatement.setString(2, expDate);
                    pStatement.setString(4, "" + type);
                    pStatement.setString(5, cardFacilitator);
                    pStatement.setString(6, String.valueOf(idCliente));

                    pStatement.execute();

                    getCon().close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void updateCompra(int id, String buyDate, String total, String desc, int idTarjeta){
        List<Tarjeta> tarjetas = getDBInstance().getTarjetas();

        for (Tarjeta t: tarjetas){
            if (t.getId() == idTarjeta){
                try {
                    pStatement = getDBInstance().con.prepareStatement("update Compra set fechaCompra = '?', montoTotal =  '?', descripcion  = '?', id_tarjeta = '?' where id = " + id);

                    pStatement.setString(1, buyDate);
                    pStatement.setString(2, total);
                    pStatement.setString(4, desc);
                    pStatement.setString(5, String.valueOf(idTarjeta));

                    pStatement.execute();

                    getCon().close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
