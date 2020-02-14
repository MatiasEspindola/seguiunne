/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.service;

import java.util.List;
import net.iterart.seguiunne.model.DatosGralProyecto;
import net.iterart.seguiunne.model.ResponsablesProyecto;
import net.iterart.seguiunne.model.TiposCargos;
import net.iterart.seguiunne.model.dao.IResponsablesProyectoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matiasespindola
 */
@Service
public class ResponsablesProyectoImpl implements IResponsablesProyectoService {

    @Autowired
    private IResponsablesProyectoDao proyDao;

    @Override
    @Transactional(readOnly = true)
    public List<ResponsablesProyecto> findAll() {
        return (List<ResponsablesProyecto>) proyDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public ResponsablesProyecto fetchByDatosGralProyecto(DatosGralProyecto dato, TiposCargos cargo){
        return proyDao.fetchByDatosGralProyecto(dato, cargo);
    }

}
