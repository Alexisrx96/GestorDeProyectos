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
public class SolicitudDto {

    private int idSolicitud;
    private int idEstadoSolicitud;
    private String idDepartamento;
    private int idTipoSolicitud;
    private String descripcionSolicitud;
    private String direccionPdfSolicitud;

    /**
     *
     * @param idSolicitud
     * @param idEstadoSolicitud
     * @param idDepartamento
     * @param idTipoSolicitud
     * @param descripcionSolicitud
     * @param direccionPdfSolicitud
     */
    public SolicitudDto(int idSolicitud, int idEstadoSolicitud,
            String idDepartamento, int idTipoSolicitud,
            String descripcionSolicitud, String direccionPdfSolicitud) {
        this.idSolicitud = idSolicitud;
        this.idEstadoSolicitud = idEstadoSolicitud;
        this.idDepartamento = idDepartamento;
        this.idTipoSolicitud = idTipoSolicitud;
        this.descripcionSolicitud = descripcionSolicitud;
        this.direccionPdfSolicitud = direccionPdfSolicitud;
    }

    /**
     *
     * @param idEstadoSolicitud
     * @param idDepartamento
     * @param idTipoSolicitud
     * @param descripcionSolicitud
     * @param direccionPdfSolicitud
     */
    public SolicitudDto(int idEstadoSolicitud,
            String idDepartamento, int idTipoSolicitud,
            String descripcionSolicitud, String direccionPdfSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
        this.idDepartamento = idDepartamento;
        this.idTipoSolicitud = idTipoSolicitud;
        this.descripcionSolicitud = descripcionSolicitud;
        this.direccionPdfSolicitud = direccionPdfSolicitud;
    }

    /**
     *
     * @param idEstadoSolicitud
     * @param idDepartamento
     * @param idTipoSolicitud
     * @param descripcionSolicitud
     */
    public SolicitudDto(int idEstadoSolicitud,
            String idDepartamento, int idTipoSolicitud,
            String descripcionSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
        this.idDepartamento = idDepartamento;
        this.idTipoSolicitud = idTipoSolicitud;
        this.descripcionSolicitud = descripcionSolicitud;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public void setIdEstadoSolicitud(int idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public void setIdTipoSolicitud(int idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }

    public String getDescripcionSolicitud() {
        return descripcionSolicitud;
    }

    public void setDescripcionSolicitud(String descripcionSolicitud) {
        this.descripcionSolicitud = descripcionSolicitud;
    }

    public String getDireccionPdfSolicitud() {
        return direccionPdfSolicitud;
    }

    public void setDireccionPdfSolicitud(String direccionPdfSolicitud) {
        this.direccionPdfSolicitud = direccionPdfSolicitud;
    }

}
