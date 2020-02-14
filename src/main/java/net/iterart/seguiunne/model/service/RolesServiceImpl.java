/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.service;

import net.iterart.seguiunne.model.Roles;
import net.iterart.seguiunne.model.dao.IRolesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */
@Service
public class RolesServiceImpl implements IRolesService{
    
    @Autowired
    IRolesDao rolesDao;
    
    @Override
    @Transactional(readOnly = true)
    public Roles findById(Integer id){
        return rolesDao.findById(id).orElse(null);
    }
    
}
