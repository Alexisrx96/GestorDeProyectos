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
public class EstadoCasoDto {

    public final static int DESARROLLO = 1;
    public final static int DEVUELTO = 2;
    public final static int ESPERA = 3;
    public final static int VENCIDO = 4;
    public final static int FINALIZADO = 5;

    private final int idEstadoCaso;
    private final String nombreEstadoCaso;

    public EstadoCasoDto(int idEstadoCaso, String nombreEstadoCaso) {
        this.idEstadoCaso = idEstadoCaso;
        this.nombreEstadoCaso = nombreEstadoCaso;
    }

    public int getIdEstadoCaso() {
        return idEstadoCaso;
    }

    public String getNombreEstadoCaso() {
        return nombreEstadoCaso;
    }

}
