package net.iterart.seguiunne.model.service;

import java.util.List;

import net.iterart.seguiunne.model.InformesTareas;

public interface ITareasInformesService {

	public List<InformesTareas> findByTareas(Integer id);
	
	public InformesTareas findOne(Integer id);
	
	public void save(InformesTareas informe);
}
