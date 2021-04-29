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
public class EstadoEmpleadoDto {
    public final static int DISPONIBLE = 1;
    public final static int OCUPADO = 2;
    public final static int INACTIVO = 3;
    
    private final int idEstadoEmpleado;
    private final String nombreEstadoEmpleado;

    public EstadoEmpleadoDto(int idEstadoEmpleado, String nombreEstadoEmpleado) {
        this.idEstadoEmpleado = idEstadoEmpleado;
        this.nombreEstadoEmpleado = nombreEstadoEmpleado;
    }

    public int getIdEstadoEmpleado() {
        return idEstadoEmpleado;
    }

    public String getNombreEstadoEmpleado() {
        return nombreEstadoEmpleado;
    }
    
}
