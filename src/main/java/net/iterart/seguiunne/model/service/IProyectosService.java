package net.iterart.seguiunne.model.service;

import java.util.List;
import net.iterart.seguiunne.model.Proyectos;

public interface IProyectosService {

	public Proyectos findById(Integer id);    
        
        public List<Proyectos> findAll();   
        
}
