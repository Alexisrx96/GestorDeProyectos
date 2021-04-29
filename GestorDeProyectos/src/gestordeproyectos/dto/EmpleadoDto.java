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
public class EmpleadoDto {

    private int idEmpleado;
    private String nombreEmpleado;
    private String idDepartamento;
    private Integer idJefe;
    private int idRol;
    private int idEstadoEmpleado;

    /**
     *
     * @param idEmpleado
     * @param nombreEmpleado
     * @param idDepartamento
     * @param idJefe
     * @param idRol
     * @param idEstadoEmpleado
     */
    public EmpleadoDto(int idEmpleado, String nombreEmpleado, String idDepartamento, Integer idJefe, int idRol, int idEstadoEmpleado) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.idDepartamento = idDepartamento;
        this.idJefe = idJefe;
        this.idRol = idRol;
        this.idEstadoEmpleado = idEstadoEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(Integer idJefe) {
        this.idJefe = idJefe;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    

    public int getIdEstadoEmpleado() {
        return idEstadoEmpleado;
    }

    public void setIdEstadoEmpleado(int idEstadoEmpleado) {
        this.idEstadoEmpleado = idEstadoEmpleado;
    }

}
