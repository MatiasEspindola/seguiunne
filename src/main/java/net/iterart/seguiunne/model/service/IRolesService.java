/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.service;

import net.iterart.seguiunne.model.Roles;

/**
 *
 * @author matia
 */
public interface IRolesService {
    
    public Roles findById(Integer id);
    
}
