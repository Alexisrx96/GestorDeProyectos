/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dao;

import static gestordeproyectos.dao.DbConecction.DB_PASS;
import static gestordeproyectos.dao.DbConecction.DB_URL;
import static gestordeproyectos.dao.DbConecction.DB_USER;
import gestordeproyectos.dto.EstadoCasoDto;
import gestordeproyectos.dto.RolDto;
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
public class RolDao
        extends DbConecction
        implements IReadDao<RolDto, Integer> {

    public static void main(String[] args) {
        RolDao rd = new RolDao();
        for (RolDto rolDto : rd.list()) {
            System.out.println(
                    "Id: " + rolDto.getIdRol()
                    + " Nombre: " + rolDto.getNombreRol());
        }
        
        RolDto rDto= rd.read(1);
        
            System.out.println(
                    "Id: " + rDto.getIdRol()
                    + " Nombre: " + rDto.getNombreRol());
    }

    @Override
    public List<RolDto> list() {
        Connection conn = null;
        List<RolDto> list = new ArrayList<>();
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT * FROM rol";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                list.add(new RolDto(rs.getInt(1), rs.getString(2)));
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
    public RolDto read(Integer id) {
        Connection conn = null;
        RolDto rol = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = " SELECT * FROM rol"
                    + " where id_rol = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                rol = new RolDto(
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
        return rol;
    }

}
