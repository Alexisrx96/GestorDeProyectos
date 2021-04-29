/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dao;

import static gestordeproyectos.dao.DbConecction.DB_PASS;
import static gestordeproyectos.dao.DbConecction.DB_URL;
import static gestordeproyectos.dao.DbConecction.DB_USER;
import gestordeproyectos.dto.BitacoraDto;
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
public class BitacoraDao
        extends DbConecction
        implements IReadDao<BitacoraDto, Integer>,
        IInsertDao<BitacoraDto> {

    @Override
    public List<BitacoraDto> list() {
        Connection conn = null;
        List<BitacoraDto> list = new ArrayList<>();
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM bitacora";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                list.add(new BitacoraDto(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getInt(3),
                        new Date(rs.getDate(4).getTime()),
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
    public List<BitacoraDto> list(String idCaso) {
        Connection conn = null;
        List<BitacoraDto> list = new ArrayList<>();
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM bitacora where id_caso = ? ";

            // create the java statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, idCaso);
            ResultSet rs = preparedStmt.executeQuery();
            // iterate through the java resultset
            while (rs.next()) {
                list.add(new BitacoraDto(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getInt(3),
                        new Date(rs.getDate(4).getTime()),
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
    public BitacoraDto read(Integer id) {
        Connection conn = null;
        BitacoraDto bitacora = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "select * from bitacora where id_bitacora = ? "
                    + "order by fecha_avance desc;";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                bitacora = new BitacoraDto(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getInt(3),
                        new Date(rs.getDate(4).getTime()),
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
        return bitacora;
    }

    @Override
    public void insert(BitacoraDto t) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "INSERT INTO bitacora "
                    + "(id_caso, "
                    + "porcentaje, "
                    + "fecha_avance, "
                    + "descripcion_avance) "
                    + "VALUES (?,?,?,?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, t.getIdCaso());
            preparedStmt.setInt(2, t.getPorcentaje());
            preparedStmt.setDate(3, new java.sql.Date( new Date().getTime()));
            preparedStmt.setString(4, t.getDescripcionAvance());

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
