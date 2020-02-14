/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.dao;

import net.iterart.seguiunne.model.TiposCargos;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author matiasespindola
 */
public interface ITiposCargosDao extends CrudRepository<TiposCargos, Integer>{
    
}
