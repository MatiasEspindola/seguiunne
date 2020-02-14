package net.iterart.seguiunne.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.iterart.seguiunne.model.Tareas;
import net.iterart.seguiunne.model.dao.ITareasDao;

@Service
public class TareasServiceImpl implements ITareasService {

    @Autowired
    ITareasDao tarDao;
    
    @Override
    public List<Tareas> fetchByDate(String fecIni){
         return (List<Tareas>) tarDao.fetchByDate(fecIni);
    }
    
    @Override
    public List<Tareas> findAll(){
        return (List<Tareas>) tarDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Tareas findById(Integer id) {
        return tarDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tareas> fetchByActividad(Integer id) {
        return tarDao.fetchByActividad(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Tareas> fetchByTareaAtrasada(Integer id) {
        return tarDao.fetchByTareaAtrasada(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Tareas> fetchByTareaEnCurso(Integer id) {
        return tarDao.fetchByTareaEnCurso(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Tareas> fetchByTareaFinalizada(Integer id) {
        return tarDao.fetchByTareaFinalizada(id);
    }

    @Override
    @Transactional
    public void save(Tareas tarea) {
        tarDao.save(tarea);
    }

    @Override
    @Transactional
    public void updateFinalizarTarea(Integer id) {
        tarDao.updateFinalizarTarea(id);
    }

    @Override
    @Transactional
     public void updateTarea(String titulo, String lugar, String descripcion, String prioridad, Date fechaInicio, Date fechaFin, Integer id) {
        tarDao.updateTarea(titulo, lugar, descripcion, prioridad, fechaInicio, fechaFin, id);
    }

    @Override
    @Transactional
    public void deshabilitar(Integer id) {
        tarDao.deshabilitar(id);
    }

    @Override
    @Transactional
    public void updateReprogramarTarea(Date fechaInicio, Date fechaFin, String motivo_rep, Integer id) {
        tarDao.updateReprogramarTarea(fechaInicio, fechaFin, motivo_rep, id);
    }

    @Override
    public List<Tareas> fetchByTareaReprogramada(Integer id) {
        return tarDao.fetchByTareaReprogramada(id);
    }

}
