/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dao;

import static gestordeproyectos.dao.DbConecction.DB_PASS;
import static gestordeproyectos.dao.DbConecction.DB_URL;
import static gestordeproyectos.dao.DbConecction.DB_USER;
import gestordeproyectos.dto.TipoSolicitudDto;
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
public class TipoSolicitudDao
        extends DbConecction
        implements IReadDao<TipoSolicitudDto, Integer> {

    public static void main(String[] args) {
        TipoSolicitudDao tsd = new TipoSolicitudDao();
        for (TipoSolicitudDto tipoSolicitudDto : tsd.list()) {
            System.out.println("Id: " + tipoSolicitudDto.getIdTipoSolicitud()
            + " Nombre: " + tipoSolicitudDto.getNombreTipoSolicitud());
        }
    }
    @Override
    public List<TipoSolicitudDto> list() {
        Connection conn = null;
        List<TipoSolicitudDto> list = new ArrayList<>();
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM tipo_solicitud";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                list.add(new TipoSolicitudDto(rs.getInt(1), rs.getString(2)));
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
    public TipoSolicitudDto read(Integer id) {
        Connection conn = null;
        TipoSolicitudDto tipoSolicitud = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = " SELECT * FROM tipo_solicitud"
                    + " where id_tipo_solicitud = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                tipoSolicitud = new TipoSolicitudDto(
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
        return tipoSolicitud;
    }

}
