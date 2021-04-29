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
public class EstadoSolicitudDto {
    
    public final static int EN_ESPERA = 1;
    public final static int RECHAZADA = 2;
    public final static int APROBADA = 3;
    
    
    private final int idEstadoSolicitud;
    private final String nombreEstadoSolicitud;

    public EstadoSolicitudDto(int idEstadoSolicitud, String nombreEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
        this.nombreEstadoSolicitud = nombreEstadoSolicitud;
    }

    public int getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public String getNombreEstadoSolicitud() {
        return nombreEstadoSolicitud;
    }
    
}
