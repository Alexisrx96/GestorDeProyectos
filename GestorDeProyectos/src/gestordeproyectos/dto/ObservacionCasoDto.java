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
public class ObservacionCasoDto {
    private int idObservacionCaso;
    private String idCaso;
    private Date fechaObservacionCaso;
    private String descripcion;
    private String direccionPdfObservacion;

    public ObservacionCasoDto(int idObservacionCaso, String idCaso,
            Date fechaObservacionCaso, String descripcion, 
            String direccionPdfObservacion) {
        this.idObservacionCaso = idObservacionCaso;
        this.idCaso = idCaso;
        this.fechaObservacionCaso = fechaObservacionCaso;
        this.descripcion = descripcion;
        this.direccionPdfObservacion = direccionPdfObservacion;
    }
    public ObservacionCasoDto(String idCaso, Date fechaObservacionCaso,
            String descripcion, String direccionPdfObservacion) {
        this.idCaso = idCaso;
        this.fechaObservacionCaso = fechaObservacionCaso;
        this.descripcion = descripcion;
        this.direccionPdfObservacion = direccionPdfObservacion;
    }
    public ObservacionCasoDto(String idCaso, Date fechaObservacionCaso, 
            String descripcion) {
        this.idCaso = idCaso;
        this.fechaObservacionCaso = fechaObservacionCaso;
        this.descripcion = descripcion;
    }

    public int getIdObservacionCaso() {
        return idObservacionCaso;
    }

    public void setIdObservacionCaso(int idObservacionCaso) {
        this.idObservacionCaso = idObservacionCaso;
    }

    public String getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(String idCaso) {
        this.idCaso = idCaso;
    }

    public Date getFechaObservacionCaso() {
        return fechaObservacionCaso;
    }

    public void setFechaObservacionCaso(Date fechaObservacionCaso) {
        this.fechaObservacionCaso = fechaObservacionCaso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccionPdfObservacion() {
        return direccionPdfObservacion;
    }

    public void setDireccionPdfObservacion(String direccionPdfObservacion) {
        this.direccionPdfObservacion = direccionPdfObservacion;
    }
    
}
