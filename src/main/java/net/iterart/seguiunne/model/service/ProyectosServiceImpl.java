package net.iterart.seguiunne.model.service;

import java.util.List;
import net.iterart.seguiunne.model.Proyectos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.iterart.seguiunne.model.dao.IProyectosDao;

@Service
public class ProyectosServiceImpl implements IProyectosService {

    @Autowired
    IProyectosDao proDao;

    @Override
    @Transactional(readOnly = true)
    public Proyectos findById(Integer id) {
        return proDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proyectos> findAll() {
       return (List<Proyectos>) proDao.findAll();
    }

}
