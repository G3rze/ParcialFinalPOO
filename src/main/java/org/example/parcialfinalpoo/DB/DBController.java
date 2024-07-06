package org.example.parcialfinalpoo.DB;

import java.sql.*;

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



}
