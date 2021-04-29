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
public class RolDto {
    
    public static final int ADMINISTRADOR = 1;
    public static final int JEFE_FUNCIONAL = 2;
    public static final int EMPLEADO = 3;
    public static final int JEFE_DESARROLLO = 4;
    public static final int PROGRAMADOR = 5;
    
    private final int idRol;
    private final String nombreRol;

    public RolDto(int idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public int getIdRol() {
        return idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }
    
}
