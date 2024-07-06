package org.example.parcialfinalpoo.DB;

import org.example.parcialfinalpoo.Clases.Cliente;
import org.example.parcialfinalpoo.Clases.Compra;
import org.example.parcialfinalpoo.Clases.Tarjeta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBController {

    private Connection con;
    private PreparedStatement pStatement = null;
    private Statement statement = null;
    private static DBController DBInstance;

    private DBController() {
        try {
            Credentials credentials = new Credentials();
            setCon(con = DriverManager.getConnection("jdbc:mysql://localhost/ParcialFinal", credentials.getUsername(),credentials.getPassword()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBController getDBInstance() {
        if(DBInstance == null){
            DBInstance = new DBController();
        }
        return DBInstance;
    }

    private Connection getCon() {
        return con;
    }

    private void setCon(Connection con) {
        this.con = con;
    }

    public PreparedStatement getpStatement() {
        return pStatement;
    }

    public void setpStatement(PreparedStatement pStatement) {
        this.pStatement = pStatement;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public List<Cliente> getClientes(){
        List<Cliente> clientes = new ArrayList<>();


        try {
            ResultSet resultSet = getDBInstance().con.createStatement().executeQuery("select * from Cliente");

            while (resultSet.next()){
                clientes.add(new Cliente(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    public List<Tarjeta> getTarjetas (){
        List<Tarjeta> tarjetas = new ArrayList<>();

        try {
            ResultSet resultSet = getDBInstance().con.createStatement().executeQuery("select * from Tarjeta");

            List<Cliente> clientes = getClientes();
            Cliente cliente = null;

            while (resultSet.next()){
                for(Cliente c: clientes){
                    if(c.getId() == resultSet.getInt("id_cliente")){
                        cliente = c;
                    }
                }
                tarjetas.add(new Tarjeta(resultSet.getInt("id"), resultSet.getString("numeroTarjeta"), resultSet.getDate("fechaExpiracion"), resultSet.getString("tipoTarjeta").charAt(0), resultSet.getString("facilitadorTarjeta"), cliente));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return tarjetas;
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

}
