/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dto;

import java.util.Date;

/**
 *
 * @author Irvin
 */
public class CasoDto {

    private int idSolicitud;
    private String idCaso;
    private int idProgramador;
    private int idProbador;
    private int idEstadoCaso;
    private Date fechaInicio;
    private Date fechaEntrega;
    private Date fechaPuestaProduccion;
    private String requerimento;

    /**
     *
     * @param idSolicitud
     * @param idCaso
     * @param idProgramador
     * @param idProbador
     * @param idEstadoCaso
     * @param fechaEntrega
     * @param fechaPuestaProduccion
     * @param requerimento
     */
    public CasoDto(int idSolicitud, String idCaso, int idProgramador,
            int idProbador, int idEstadoCaso, Date fechaInicio,
            Date fechaEntrega, Date fechaPuestaProduccion, String requerimento) {
        this.idSolicitud = idSolicitud;
        this.idCaso = idCaso;
        this.idProgramador = idProgramador;
        this.idProbador = idProbador;
        this.idEstadoCaso = idEstadoCaso;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.fechaPuestaProduccion = fechaPuestaProduccion;
        this.requerimento = requerimento;
    }

    public CasoDto(int idSolicitud, int idProgramador, int idProbador,
            int idEstadoCaso, Date fechaInicio, Date fechaEntrega,
            String requerimento) {
        this.idSolicitud = idSolicitud;
        this.idProgramador = idProgramador;
        this.idProbador = idProbador;
        this.idEstadoCaso = idEstadoCaso;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.requerimento = requerimento;
    }


    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(String idCaso) {
        this.idCaso = idCaso;
    }

    public int getIdProgramador() {
        return idProgramador;
    }

    public void setIdProgramador(int idProgramador) {
        this.idProgramador = idProgramador;
    }

    public int getIdProbador() {
        return idProbador;
    }

    public void setIdProbador(int idProbador) {
        this.idProbador = idProbador;
    }

    public int getIdEstadoCaso() {
        return idEstadoCaso;
    }

    public void setIdEstadoCaso(int idEstadoCaso) {
        this.idEstadoCaso = idEstadoCaso;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaPuestaProduccion() {
        return fechaPuestaProduccion;
    }

    public void setFechaPuestaProduccion(Date fechaPuestaProduccion) {
        this.fechaPuestaProduccion = fechaPuestaProduccion;
    }

    public String getRequerimento() {
        return requerimento;
    }

    public void setRequerimento(String requerimento) {
        this.requerimento = requerimento;
    }
}
