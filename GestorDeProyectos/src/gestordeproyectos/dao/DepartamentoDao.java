/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dao;

import gestordeproyectos.dto.DepartamentoDto;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Irvin
 */
public class DepartamentoDao 
        extends DbConecction 
        implements IInsertDao<DepartamentoDto>,
        IReadDao<DepartamentoDto, String>,
        IUpdateDao<DepartamentoDto> {

    public static void main(String[] args) {
        DepartamentoDao dp = new DepartamentoDao();
        List<DepartamentoDto> lista = dp.list();
        lista.forEach(departamentoDto -> {
            System.out.println(
                    "Id: "
                    + departamentoDto.getIdDepartamento()
                    + "\tDepartamento: "
                    + departamentoDto.getNombreDepartamento());
        });
    }

    @Override
    public List<DepartamentoDto> list() {
        Connection conn = null;
        List<DepartamentoDto> list = new ArrayList<>();
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM departamento";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                list.add(new DepartamentoDto(rs.getString(1), rs.getString(2)));
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

    //ready
    @Override
    public void insert(DepartamentoDto t) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = " insert into departamento "
                    + "(id_departamento, nombre_departamento)"
                    + " values (?,?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, t.getIdDepartamento());
            preparedStmt.setString(2, t.getNombreDepartamento());

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

    //ready
    @Override
    public DepartamentoDto read(String id) {
        Connection conn = null;
        DepartamentoDto departamento = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = " SELECT * FROM departamento"
                    + " where id_departamento = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                departamento = new DepartamentoDto(
                        rs.getString(1),
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
        return departamento;
    }

    @Override
    public void update(DepartamentoDto t) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // create the java mysql update preparedstatement
            String query = "update departamento set "
                    + "nombre_departamento = ? where id_departamento = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, t.getNombreDepartamento());
            preparedStmt.setString(2, t.getIdDepartamento());

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
