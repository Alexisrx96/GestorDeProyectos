/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dto;

/**
 *
 * @author Irvin
 */
public class UsuarioDto {
    private String idUsuario; 
    private String passwordUsuario; 
    private int idRol;
    private int idEstadoEmpleado;
    private String idDepartamento;

    /**
     *
     * @param idUsuario
     * @param idRol
     * @param idEstadoEmpleado
     * @param idDepartamento
     */
    public UsuarioDto(String idUsuario, int idRol, int idEstadoEmpleado, 
            String idDepartamento) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.idEstadoEmpleado = idEstadoEmpleado;
        this.idDepartamento = idDepartamento;
    }
    
    /**
     *
     * @param idUsuario
     * @param passwordUsuario
     */
    public UsuarioDto(String idUsuario, String passwordUsuario) {
        this.idUsuario = idUsuario;
        this.passwordUsuario = passwordUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public int getIdEstadoEmpleado() {
        return idEstadoEmpleado;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    /**
     *
     * @param passwordUsuario
     */
    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }
}
