/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dao;

import static gestordeproyectos.dao.DbConecction.DB_PASS;
import static gestordeproyectos.dao.DbConecction.DB_URL;
import static gestordeproyectos.dao.DbConecction.DB_USER;
import gestordeproyectos.dto.EstadoEmpleadoDto;
import gestordeproyectos.dto.UsuarioDto;
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
public class UsuarioDao
        extends DbConecction
        implements IReadDao<UsuarioDto, String>,
        IUpdateDao<UsuarioDto> {

    public static void main(String[] args) {
        UsuarioDao ud = new UsuarioDao();
        for (UsuarioDto usuarioDto : ud.list()) {
            System.out.println(
                    "Usuario: " + usuarioDto.getIdUsuario()
                    + " Rol: " + usuarioDto.getIdRol()
                    + " Estado: " + usuarioDto.getIdEstadoEmpleado()
                    + " Departamento: " + usuarioDto.getIdDepartamento()
            );
        }

        System.out.println("Loggin: " + ud.login(new UsuarioDto("Z0002", "Z0002")));
    }

    @Override
    public List<UsuarioDto> list() {
        Connection conn = null;
        List<UsuarioDto> list = new ArrayList<>();
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT usuario_empleado,"
                    + "id_rol, id_estado_empleado,"
                    + "id_departamento FROM empleado";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                list.add(new UsuarioDto(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4)
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
    public UsuarioDto read(String id) {
        Connection conn = null;
        UsuarioDto usuario = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT usuario_empleado,"
                    + "id_rol, id_estado_empleado,"
                    + "id_departamento FROM empleado "
                    + "where usuario_empleado = ? ";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                usuario = new UsuarioDto(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4)
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
        return usuario;
    }

    @Override
    public void update(UsuarioDto t) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // create the java mysql update preparedstatement
            String query = "UPDATE empleado SET "
                    + "password_empleado = ? "
                    + "where usuario_empleado = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, t.getPasswordUsuario());
            preparedStmt.setString(2, t.getIdUsuario());

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

    public boolean login(UsuarioDto t) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT usuario_empleado FROM empleado "
                    + " where usuario_empleado = ? "
                    + "and password_empleado =  ? "
                    + "and id_estado_empleado <> ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, t.getIdUsuario());
            preparedStmt.setString(2, t.getPasswordUsuario());
            preparedStmt.setInt(3, EstadoEmpleadoDto.INACTIVO);
            ResultSet rs = preparedStmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
