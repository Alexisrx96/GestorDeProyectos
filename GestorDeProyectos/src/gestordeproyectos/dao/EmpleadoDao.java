/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dao;

import gestordeproyectos.dto.DepartamentoDto;
import gestordeproyectos.dto.EmpleadoDto;
import gestordeproyectos.dto.UsuarioDto;
import gestordeproyectos.util.ConstantFormats;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;

/**
 *
 * @author Irvin
 */
public class EmpleadoDao
        extends DbConecction
        implements IInsertDao<EmpleadoDto>,
        IReadDao<EmpleadoDto, Integer>,
        IUpdateDao<EmpleadoDto> {

    public static void main(String[] args) {
        EmpleadoDao ed = new EmpleadoDao();
        for (EmpleadoDto empleadoDto : ed.list()) {
            System.out.println(
                    "Id: " + empleadoDto.getIdEmpleado()
                    + " Nombre: " + empleadoDto.getNombreEmpleado()
                    + " Departamento: " + empleadoDto.getIdDepartamento()
                    + " Jefe: " + empleadoDto.getIdJefe()
                    + " Rol: " + empleadoDto.getIdRol()
                    + " Estado: " + empleadoDto.getIdEstadoEmpleado()
            );
        }
        EmpleadoDto empleadoDto = ed.read(1);

        System.out.println(
                "Id: " + empleadoDto.getIdEmpleado()
                + " Nombre: " + empleadoDto.getNombreEmpleado()
                + " Departamento: " + empleadoDto.getIdDepartamento()
                + " Jefe: " + empleadoDto.getIdJefe()
                + " Rol: " + empleadoDto.getIdRol()
                + " Estado: " + empleadoDto.getIdEstadoEmpleado()
        );
    }

    public EmpleadoDto read(UsuarioDto u) {
        Connection conn = null;
        EmpleadoDto empleado = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT id_empleado,nombre_empleado,"
                    + "id_departamento, id_jefe,"
                    + "id_rol, id_estado_empleado FROM empleado "
                    + "where usuario_empleado = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, u.getIdUsuario());
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                empleado = new EmpleadoDto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6)
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
        return empleado;
    }

    public EmpleadoDto getJefeDepartamento(DepartamentoDto d) {
        Connection conn = null;
        EmpleadoDto empleado = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT id_empleado,nombre_empleado,"
                    + "id_departamento, id_jefe,"
                    + "id_rol, id_estado_empleado FROM empleado "
                    + "where id_departamento = ? and id_rol = 2 ";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, d.getIdDepartamento());
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                empleado = new EmpleadoDto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6)
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
        return empleado;
    }

    public EmpleadoDto getJefeProgramador(DepartamentoDto d) {
        Connection conn = null;
        EmpleadoDto empleado = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT id_empleado,nombre_empleado,"
                    + "id_departamento, id_jefe,"
                    + "id_rol, id_estado_empleado FROM empleado "
                    + "where id_departamento = ? and id_rol = 4 ";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, d.getIdDepartamento());
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                empleado = new EmpleadoDto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6)
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
        return empleado;
    }

    @Override
    public List<EmpleadoDto> list() {
        Connection conn = null;
        List<EmpleadoDto> list = new ArrayList<>();
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT id_empleado,nombre_empleado,"
                    + "id_departamento, id_jefe,"
                    + "id_rol, id_estado_empleado FROM empleado";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                list.add(new EmpleadoDto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6)
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
    public EmpleadoDto read(Integer id) {
        Connection conn = null;
        EmpleadoDto empleado = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT id_empleado,nombre_empleado,"
                    + "id_departamento, id_jefe,"
                    + "id_rol, id_estado_empleado FROM empleado "
                    + "where id_empleado = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                empleado = new EmpleadoDto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6)
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
        return empleado;
    }

    @Override
    public void insert(EmpleadoDto t) {
        Connection conn = null;
        int count = list().size()+1;
        String corr = new ConstantFormats().fillNumberWithZeros(4, count);
        String usuario = t.getNombreEmpleado().charAt(0) + corr;
        usuario.toUpperCase();
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "insert into empleado "
                    + "(nombre_empleado, id_departamento ,id_jefe, "
                    + "id_rol, id_estado_empleado, usuario_empleado"
                    + ",password_empleado)"
                    + " values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, t.getNombreEmpleado());
            preparedStmt.setString(2, t.getIdDepartamento());
            if (t.getIdJefe() == null) {
                preparedStmt.setNull(3, Types.INTEGER);
            } else {
                preparedStmt.setInt(3, t.getIdJefe());
            }
            preparedStmt.setInt(4, t.getIdRol());
            preparedStmt.setInt(5, t.getIdEstadoEmpleado());
            preparedStmt.setString(6, usuario);
            preparedStmt.setString(7, usuario);

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
    public void update(EmpleadoDto t) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // create the java mysql update preparedstatement
            String query = "UPDATE empleado SET "
                    + "nombre_empleado = ?, "
                    + "id_departamento = ?,"
                    + "id_jefe = ?,"
                    + "id_rol = ?,"
                    + "id_estado_empleado = ?"
                    + " where id_empleado = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, t.getNombreEmpleado());

            preparedStmt.setString(2, t.getIdDepartamento());
            if (t.getIdJefe() == null) {
                preparedStmt.setNull(3, Types.INTEGER);
            } else {
                preparedStmt.setInt(3, t.getIdJefe());
            }
            preparedStmt.setInt(4, t.getIdRol());
            preparedStmt.setInt(5, t.getIdEstadoEmpleado());
            preparedStmt.setInt(6, t.getIdEmpleado());

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
