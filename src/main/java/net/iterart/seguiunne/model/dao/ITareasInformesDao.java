package net.iterart.seguiunne.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.iterart.seguiunne.model.InformesTareas;

public interface ITareasInformesDao extends CrudRepository<InformesTareas, Long> {
	
	
	@Query("select i from InformesTareas i where i.fkIdTar.pkIdTar=?1")
	public List<InformesTareas> findByTareas(Integer id);
	
	@Query("select i from InformesTareas i where i.fkIdTar.pkIdTar=?1")
	public InformesTareas findOne(Integer id);

}
