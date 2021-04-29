/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dao;

import static gestordeproyectos.dao.DbConecction.DB_PASS;
import static gestordeproyectos.dao.DbConecction.DB_URL;
import static gestordeproyectos.dao.DbConecction.DB_USER;
import gestordeproyectos.dto.CasoDto;
import gestordeproyectos.dto.RazonRechazoSolicitudDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Irvin
 */
public class RazonRechazoSolicitudDao 
        extends DbConecction 
        implements IInsertDao<RazonRechazoSolicitudDto>,
        IReadDao<RazonRechazoSolicitudDto, Integer>{

    @Override
    public List<RazonRechazoSolicitudDto> list() {
        Connection conn = null;
        List<RazonRechazoSolicitudDto> list = new ArrayList<>();
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM razon_rechazo_solicitud";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                list.add(new RazonRechazoSolicitudDto(
                        rs.getInt(1),
                        rs.getString(2)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    @Override
    public void insert(RazonRechazoSolicitudDto t) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "insert into razon_rechazo_solicitud "
                    + "(id_solicitud,descripcion_razon_rechazo_solicitud) values (?,?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, t.getIdSolicitud());
            preparedStmt.setString(2, t.getDescripcionRazonRechazoSolicitud());

            preparedStmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Override
    public RazonRechazoSolicitudDto read(Integer id) {
        Connection conn = null;
        RazonRechazoSolicitudDto razonRechazoSolicitud = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM razon_rechazo_solicitud "
                    + "where id_solicitud = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                razonRechazoSolicitud = new RazonRechazoSolicitudDto(
                        rs.getInt(1),
                        rs.getString(2)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return razonRechazoSolicitud;
    }    
}
