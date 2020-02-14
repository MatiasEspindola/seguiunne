package net.iterart.seguiunne.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.iterart.seguiunne.model.Proyectos;

public interface IProyectosDao extends CrudRepository<Proyectos, Long> {

	@Query("select p from Proyectos p where p.pkIdPro=?1")
	public Proyectos findById(Integer id);     
}
