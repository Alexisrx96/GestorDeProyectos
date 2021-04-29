/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dao;

/**
 *
 * @author Irvin
 */
import java.sql.*;
public abstract class DbConecction {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/gestiondeproyectos";
    static final String DB_USER = "root";
    static final String DB_PASS = "Admin123456";
    
    protected void registerDriver() {
        try {
            Class.forName(JDBC_DRIVER).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.err.println("ERROR: Error al cargar el JDBC driver.");
            e.printStackTrace();
        }
    }
}
