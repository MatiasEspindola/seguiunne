package net.iterart.seguiunne.model.service;

import java.util.List;

import net.iterart.seguiunne.model.Localidades;

public interface ILocalidadesService {
	
	public List<Localidades> findAll();
	
	public Localidades findLocalidadById(Integer id);
	
	public List<Localidades> findByNombre(String term);
}
