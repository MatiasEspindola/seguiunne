package net.iterart.seguiunne.model.service;

import java.util.Date;
import java.util.List;

import net.iterart.seguiunne.model.Tareas;

public interface ITareasService {

    public List<Tareas> fetchByDate(String fecIni);

    public Tareas findById(Integer id);

    public List<Tareas> fetchByActividad(Integer id);

    public List<Tareas> fetchByTareaAtrasada(Integer id);
    
    public List<Tareas> fetchByTareaEnCurso(Integer id);
    
    public List<Tareas> fetchByTareaFinalizada(Integer id);
    
    public List<Tareas> fetchByTareaReprogramada(Integer id);

    public List<Tareas> findAll();

    public void save(Tareas tarea);
    
    public void updateReprogramarTarea(Date fechaInicio, Date fechaFin, String motivo_rep, Integer id);

    public void updateFinalizarTarea(Integer id);

    public void updateTarea(String titulo, String lugar, String descripcion, String prioridad, Date fechaInicio, Date fechaFin, Integer id);

    public void deshabilitar(Integer id);
}
