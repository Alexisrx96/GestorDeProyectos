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
public class RazonRechazoSolicitudDto {
    private int idSolicitud;
    private String descripcionRazonRechazoSolicitud;

    public RazonRechazoSolicitudDto(int idSolicitud, String descripcionRazonRechazoSolicitud) {
        this.idSolicitud = idSolicitud;
        this.descripcionRazonRechazoSolicitud = descripcionRazonRechazoSolicitud;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getDescripcionRazonRechazoSolicitud() {
        return descripcionRazonRechazoSolicitud;
    }

    public void setDescripcionRazonRechazoSolicitud(String descripcionRazonRechazoSolicitud) {
        this.descripcionRazonRechazoSolicitud = descripcionRazonRechazoSolicitud;
    }
}
