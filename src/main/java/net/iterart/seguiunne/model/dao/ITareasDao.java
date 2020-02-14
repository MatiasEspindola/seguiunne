package net.iterart.seguiunne.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.iterart.seguiunne.model.Tareas;

public interface ITareasDao extends CrudRepository<Tareas, Long> {
    
    @Query("select t from Tareas t where t.fecIni = '?1%'")
    public List<Tareas> fetchByDate(String fecIni);

    @Query("select t from Tareas t where t.pkIdTar=?1")
    public Tareas findById(Integer id);

    @Query("select t from Tareas t where t.fkIdAct.pkIdAct=?1")
    public List<Tareas> fetchByActividad(Integer id);

    //Agregado por @MatiasEspindola
    @Query("select t from Tareas t where t.fkIdAct.pkIdAct=?1 AND t.atrasada = 1")
    public List<Tareas> fetchByTareaAtrasada(Integer id);
    
    @Query("select t from Tareas t where t.fkIdAct.pkIdAct=?1 AND (t.atrasada = 0 AND t.fin = 0 AND t.rep = 0)")
    public List<Tareas> fetchByTareaEnCurso(Integer id);
    
    @Query("select t from Tareas t where t.fkIdAct.pkIdAct=?1 AND (t.rep = 1 AND t.atrasada = 0)")
    public List<Tareas> fetchByTareaReprogramada(Integer id);
    
    @Query("select t from Tareas t where t.fkIdAct.pkIdAct=?1 AND t.fin = 1")
    public List<Tareas> fetchByTareaFinalizada(Integer id);
    
    @Modifying
    @Query("update Tareas t set t.rep=true, t.fecIni=?1, t.fecFin=?2, t.motivo=?3 where t.pkIdTar =?4")
    public void updateReprogramarTarea(Date fechaInicio, Date fechaFin, String motivo, Integer id);

    @Modifying
    @Query("update Tareas t set t.fin=true where t.pkIdTar =?1")
    public void updateFinalizarTarea(Integer id);

    @Modifying
    @Query("update Tareas t set t.titu=?1, t.lugar=?2, t.descri=?3, t.prio=?4, t.fecIni=?5, t.fecFin=?6 where t.pkIdTar =?7")
    public void updateTarea(String titulo, String lugar, String descripcion, String prioridad, Date fechaInicio, Date fechaFin, Integer id);

    @Modifying
    @Query("update Tareas t set t.hab=false where t.pkIdTar =?1")
    public void deshabilitar(Integer id);
}
