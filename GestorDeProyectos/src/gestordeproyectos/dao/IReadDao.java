/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.dao;

import java.util.List;

/**
 *
 * @author Irvin
 */
public interface IReadDao<T,Id> {
    
    	public List<T> list();
	public T read(Id id);
        
}
