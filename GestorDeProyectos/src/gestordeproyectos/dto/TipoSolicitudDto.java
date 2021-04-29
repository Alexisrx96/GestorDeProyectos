/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dto;

import gestordeproyectos.dao.DbConecction;

/**
 *
 * @author Irvin
 */
public class TipoSolicitudDto{
    public final static int NUEVO_SISTEMA = 1;
    public final static int NUEVA_FUNCIONALIDAD = 2;
    public final static int CORRECCION = 3;
    
    private final int idTipoSolicitud;
    private final String nombreTipoSolicitud;

    public TipoSolicitudDto(int idTipoSolicitud, String nombreTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
        this.nombreTipoSolicitud = nombreTipoSolicitud;
    }

    public int getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public String getNombreTipoSolicitud() {
        return nombreTipoSolicitud;
    }
    
}
