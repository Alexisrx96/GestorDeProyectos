/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 *
 * @author Irvin
 */
public class ConstantFormats {
    
    public ConstantFormats() {
    }

    public DateFormat getDateFormat() {
        return  new SimpleDateFormat("dd-MM-yyyy");
    }
    
    public String fillNumberWithZeros(int digitsNumber, int number){
        return String.format("%0"+digitsNumber+"d", number);
    }
}
