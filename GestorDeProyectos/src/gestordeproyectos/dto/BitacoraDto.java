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
public class BitacoraDto {

    private long idBitacora;
    private String idCaso;
    private int porcentaje;
    private Date fechaAvance;
    private String descripcionAvance;

    public BitacoraDto(long idBitacora, String idCaso, int porcentaje,
            Date fechaAvance, String descripcionAvance) {
        this.idBitacora = idBitacora;
        this.idCaso = idCaso;
        this.porcentaje = porcentaje;
        this.fechaAvance = fechaAvance;
        this.descripcionAvance = descripcionAvance;
    }

    public BitacoraDto(String idCaso, int porcentaje,
            Date fechaAvance, String descripcionAvance) {
        this.idCaso = idCaso;
        this.porcentaje = porcentaje;
        this.fechaAvance = fechaAvance;
        this.descripcionAvance = descripcionAvance;
    }

    public long getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(long idBitacora) {
        this.idBitacora = idBitacora;
    }

    public String getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(String idCaso) {
        this.idCaso = idCaso;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getFechaAvance() {
        return fechaAvance;
    }

    public void setFechaAvance(Date fechaAvance) {
        this.fechaAvance = fechaAvance;
    }

    public String getDescripcionAvance() {
        return descripcionAvance;
    }

    public void setDescripcionAvance(String descripcionAvance) {
        this.descripcionAvance = descripcionAvance;
    }

}
