/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.dao;

import java.util.List;
import net.iterart.seguiunne.model.Facultades;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author matia
 */
public interface IFacultadesDao extends CrudRepository<Facultades, Long>{
    
    @Query("select f from Facultades f where f.pkIdFac =?1")
    public Facultades findById(Integer id);     
    
    @Query("select f from Facultades f where f.hab = TRUE")
    public List<Facultades> buscarHabilitados();     
    
}
