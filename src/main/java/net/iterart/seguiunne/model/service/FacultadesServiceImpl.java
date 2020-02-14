/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.service;

import java.util.List;
import net.iterart.seguiunne.model.Facultades;
import net.iterart.seguiunne.model.dao.IFacultadesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */
@Service
public class FacultadesServiceImpl implements IFacultadesService {

    @Autowired
    private IFacultadesDao facDao;

    @Override
    @Transactional(readOnly = true)
    public List<Facultades> findAll() {
        return (List<Facultades>) facDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Facultades findById(Integer id) {
        return facDao.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Facultades> buscarHabilitados() {
        return (List<Facultades>) facDao.buscarHabilitados();
    }


}
