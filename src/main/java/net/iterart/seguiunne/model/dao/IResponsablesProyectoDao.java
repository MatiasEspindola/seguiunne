/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.dao;

import net.iterart.seguiunne.model.DatosGralProyecto;
import net.iterart.seguiunne.model.ResponsablesProyecto;
import net.iterart.seguiunne.model.TiposCargos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author matiasespindola
 */
public interface IResponsablesProyectoDao extends CrudRepository<ResponsablesProyecto, Integer>{
    
    	@Query("select DISTINCT f.nombre from ResponsablesProyecto f where f.fkiddatGral=?1 AND f.fkidtpCar =?2")
	public ResponsablesProyecto fetchByDatosGralProyecto(DatosGralProyecto dato, TiposCargos cargo);
    
}
