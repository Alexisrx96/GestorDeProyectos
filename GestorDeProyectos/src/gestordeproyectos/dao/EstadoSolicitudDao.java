/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dao;

import gestordeproyectos.dto.EstadoSolicitudDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Irvin
 */
public class EstadoSolicitudDao
        extends DbConecction
        implements IReadDao<EstadoSolicitudDto, Integer> {

    public static void main(String[] args) {
        EstadoSolicitudDao esd = new EstadoSolicitudDao();
        for (EstadoSolicitudDto estadoCasoDto : esd.list()) {
            System.out.println(
                    "Id: " + estadoCasoDto.getIdEstadoSolicitud()
                    + " Nombre: " + estadoCasoDto.getNombreEstadoSolicitud());
        }
        EstadoSolicitudDto esDto= esd.read(1);
        
            System.out.println(
                    "Id: " + esDto.getIdEstadoSolicitud()
                    + " Nombre: " + esDto.getNombreEstadoSolicitud());
    }

    @Override
    public List<EstadoSolicitudDto> list() {
        Connection conn = null;
        List<EstadoSolicitudDto> list = new ArrayList<>();
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM estado_solicitud";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                list.add(new EstadoSolicitudDto(rs.getInt(1), rs.getString(2)));
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
    public EstadoSolicitudDto read(Integer id) {
        Connection conn = null;
        EstadoSolicitudDto estadoSolicitud = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = " SELECT * FROM estado_solicitud"
                    + " where id_estado_solicitud = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                estadoSolicitud = new EstadoSolicitudDto(
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
        return estadoSolicitud;
    }

}
