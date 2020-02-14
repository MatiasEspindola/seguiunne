/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.service;

import net.iterart.seguiunne.model.TiposCargos;
import net.iterart.seguiunne.model.dao.ITiposCargosDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matiasespindola
 */
@Service
public class TiposCargosImpl implements ITiposCargosService {

    @Autowired
    ITiposCargosDao cargosDao;

    @Override
    @Transactional(readOnly = true)
    public TiposCargos findById(Integer id) {
        return cargosDao.findById(id).orElse(null);
    }

}
