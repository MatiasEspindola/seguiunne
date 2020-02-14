/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.service;


import java.util.List;
import net.iterart.seguiunne.model.AdminsFacultades;
import net.iterart.seguiunne.model.dao.IAdminsFacultadesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */
@Service
public class AdminsFacultadesServiceImpl implements IAdminsFacultadesService{
    
    @Autowired
    IAdminsFacultadesDao admDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<AdminsFacultades> findAll() {
       return (List<AdminsFacultades>) admDao.findAll();
    }
    
    @Override
    @Transactional
    public void save(AdminsFacultades admins) {
        admDao.save(admins);
    }
    
}
