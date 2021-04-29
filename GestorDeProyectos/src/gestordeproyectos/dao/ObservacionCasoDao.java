/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dao;

import static gestordeproyectos.dao.DbConecction.DB_PASS;
import static gestordeproyectos.dao.DbConecction.DB_URL;
import static gestordeproyectos.dao.DbConecction.DB_USER;
import gestordeproyectos.dto.ObservacionCasoDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Irvin
 */
public class ObservacionCasoDao
        extends DbConecction
        implements IReadDao<ObservacionCasoDto, Integer>,
        IInsertDao<ObservacionCasoDto> {

    @Override
    public List<ObservacionCasoDto> list() {
        Connection conn = null;
        List<ObservacionCasoDto> list = new ArrayList<>();
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM observacion_caso";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                list.add(new ObservacionCasoDto(
                        rs.getInt(1),
                        rs.getString(2),
                        new Date(rs.getDate(3).getTime()),
                        rs.getString(4),
                        rs.getString(5)
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

    public List<ObservacionCasoDto> list(String idCaso) {
        Connection conn = null;
        List<ObservacionCasoDto> list = new ArrayList<>();
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM observacion_caso where id_caso = ?";

            // create the java statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, idCaso);
            ResultSet rs = preparedStmt.executeQuery();
            // iterate through the java resultset
            while (rs.next()) {
                list.add(new ObservacionCasoDto(
                        rs.getInt(1),
                        rs.getString(2),
                        new Date(rs.getDate(3).getTime()),
                        rs.getString(4),
                        rs.getString(5)
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
    public ObservacionCasoDto read(Integer id) {
        Connection conn = null;
        ObservacionCasoDto observacionCaso = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM observacion_caso "
                    + "where id_observacion_caso = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                observacionCaso = new ObservacionCasoDto(
                        rs.getInt(1),
                        rs.getString(2),
                        new Date(rs.getDate(3).getTime()),
                        rs.getString(4),
                        rs.getString(5)
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
        return observacionCaso;
    }

    @Override
    public void insert(ObservacionCasoDto t) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "insert into observacion_caso "
                    + "(id_observacion_caso,"
                    + "id_caso,"
                    + "fecha_observacion_caso,"
                    + "descripcion,"
                    + "direccion_pdf_observacion_caso)"
                    + " values (?,?,?,?,?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, t.getIdObservacionCaso());
            preparedStmt.setString(2, t.getIdCaso());
            preparedStmt.setDate(3, new java.sql.Date(t.getFechaObservacionCaso().getTime()));
            preparedStmt.setString(4, t.getDescripcion());
            if (t.getDireccionPdfObservacion() == null) {
                preparedStmt.setNull(5, Types.VARCHAR);
            } else {
                preparedStmt.setString(5, t.getDireccionPdfObservacion());
            }

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

}
