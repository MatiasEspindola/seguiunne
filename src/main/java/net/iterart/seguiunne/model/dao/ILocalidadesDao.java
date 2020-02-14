package net.iterart.seguiunne.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import net.iterart.seguiunne.model.Localidades;

public interface ILocalidadesDao extends CrudRepository<Localidades, Long> {
	
	@Query("select l from Localidades l where l.pkIdLoc=?1")
	public Localidades findLocalidadById(Integer id);
	
	@Query("select l from Localidades l where l.nombre like %?1% or l.cpa like %?1%")
	public List<Localidades> findByNombre(String term);

	public List<Localidades> findByNombreLikeIgnoreCase(String term);
}
