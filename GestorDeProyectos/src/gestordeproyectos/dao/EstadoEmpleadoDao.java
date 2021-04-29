/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dao;

import gestordeproyectos.dto.EstadoEmpleadoDto;
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
public class EstadoEmpleadoDao
        extends DbConecction 
        implements IReadDao<EstadoEmpleadoDto, Integer>{

    public static void main(String[] args) {
        EstadoEmpleadoDao rd = new EstadoEmpleadoDao();
        for (EstadoEmpleadoDto rolDto : rd.list()) {
            System.out.println(
                    "Id: " + rolDto.getIdEstadoEmpleado()
                    + " Nombre: " + rolDto.getNombreEstadoEmpleado());
        }
        
        EstadoEmpleadoDto rDto= rd.read(1);
        
            System.out.println(
                    "Id: " + rDto.getIdEstadoEmpleado()
                    + " Nombre: " + rDto.getNombreEstadoEmpleado());
    }
    
    @Override
    public List<EstadoEmpleadoDto> list() {
        Connection conn = null;
        List<EstadoEmpleadoDto> list = new ArrayList<>();
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM estado_empleado";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                list.add(new EstadoEmpleadoDto(rs.getInt(1), rs.getString(2)));
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
    public EstadoEmpleadoDto read(Integer id) {
        Connection conn = null;
        EstadoEmpleadoDto estadoEmpleado = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = " SELECT * FROM estado_empleado"
                    + " where id_estado_empleado = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                estadoEmpleado = new EstadoEmpleadoDto(
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
        return estadoEmpleado;
    }
    
}
