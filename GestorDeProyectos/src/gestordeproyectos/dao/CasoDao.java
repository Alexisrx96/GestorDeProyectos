/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dao;

import gestordeproyectos.dto.CasoDto;
import gestordeproyectos.dto.SolicitudDto;
import gestordeproyectos.util.ConstantFormats;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Types;

/**
 *
 * @author Irvin
 */
public class CasoDao
        extends DbConecction
        implements IInsertDao<CasoDto>,
        IReadDao<CasoDto, String>,
        IUpdateDao<CasoDto> {

    @Override
    public List<CasoDto> list() {
        Connection conn = null;
        List<CasoDto> list = new ArrayList<>();
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM caso";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                list.add(new CasoDto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getDate(8) != null
                        ? rs.getDate(8)
                        : null,
                        rs.getString(9)
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
    public void insert(CasoDto t) {
        Connection conn = null;
        //inicia el correlativo
        int corr = 1;
        //Obtienen la fecha actual
        String currentYear = String.valueOf(new Date().getYear());
        //Se le asignan los últimos dos dígitos del año
        currentYear = currentYear.substring(currentYear.length() - 2);
        SolicitudDto solicitud = new SolicitudDao().read(t.getIdSolicitud());
        //Obtiene la tista de todos los casos
        List<CasoDto> casos = new CasoDao().list();
        for (CasoDto c : casos) {
            //verifica si el caso pertenece al departamento y al año actual
            if (c.getIdCaso().startsWith(solicitud.getIdDepartamento() + currentYear)) {
                //incrementa el correlativo del caso
                corr++;
            }
        }
        ConstantFormats cf = new ConstantFormats();

        //Se crea el id del caso
        String idCaso = solicitud.getIdDepartamento()
                + currentYear
                + cf.fillNumberWithZeros(3, corr);
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "insert into caso "
                    + "(id_solicitud, "
                    + "id_caso, "
                    + "id_programador, "
                    + "id_probador, "
                    + "id_estado_caso, "
                    + "fecha_inicio, "
                    + "fecha_entrega, "
                    + "fecha_puesta_produccion, "
                    + "requerimentos_caso)"
                    + " values (?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, t.getIdSolicitud());
            preparedStmt.setString(2, idCaso);
            preparedStmt.setInt(3, t.getIdProgramador());
            preparedStmt.setInt(4, t.getIdProbador());
            preparedStmt.setInt(5, t.getIdEstadoCaso());
            preparedStmt.setDate(6, new java.sql.Date(t.getFechaInicio().getTime()));
            preparedStmt.setDate(7, new java.sql.Date(t.getFechaEntrega().getTime()));
            if (t.getFechaPuestaProduccion() == null) {

                preparedStmt.setNull(8, Types.DATE);
            } else {
                preparedStmt.setDate(8, new java.sql.Date(t.getFechaPuestaProduccion().getTime()));
            }
            preparedStmt.setString(9, t.getRequerimento());

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
    public CasoDto read(String id) {
        Connection conn = null;
        CasoDto caso = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM caso where id_caso = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                caso = new CasoDto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getDate(8) != null
                        ? rs.getDate(8)
                        : null,
                        rs.getString(9)
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
        return caso;
    }

    public CasoDto read(int id) {
        Connection conn = null;
        CasoDto caso = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM caso where id_solicitud = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                caso = new CasoDto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getDate(8) != null
                        ? rs.getDate(8)
                        : null,
                        rs.getString(9)
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
        return caso;
    }

    @Override
    public void update(CasoDto t) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // create the java mysql update preparedstatement
            String query = "UPDATE caso SET "
                    + "id_programador = ?, "
                    + "id_probador = ?, "
                    + "id_estado_caso = ?, "
                    + "fecha_entrega = ?, "
                    + "fecha_puesta_produccion = ?, "
                    + "requerimentos_caso = ? "
                    + "WHERE id_caso = ? ";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, t.getIdProgramador());
            preparedStmt.setInt(2, t.getIdProbador());
            preparedStmt.setInt(3, t.getIdEstadoCaso());
            preparedStmt.setDate(4, new java.sql.Date(t.getFechaEntrega().getTime()));
            if (t.getFechaPuestaProduccion() == null) {

                preparedStmt.setNull(5, Types.DATE);
            } else {
                preparedStmt.setDate(5, new java.sql.Date(t.getFechaPuestaProduccion().getTime()));
            }
            preparedStmt.setString(6, t.getRequerimento());
            preparedStmt.setString(7, t.getIdCaso());

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
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
