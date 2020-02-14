/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.dao;

import net.iterart.seguiunne.model.Roles;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author matia
 */
public interface IRolesDao extends CrudRepository<Roles, Integer>{
    
}
