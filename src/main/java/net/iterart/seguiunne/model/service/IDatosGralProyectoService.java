package net.iterart.seguiunne.model.service;

import java.util.List;

import net.iterart.seguiunne.model.DatosGralProyecto;
import net.iterart.seguiunne.model.Facultades;
import net.iterart.seguiunne.model.Usuarios;


public interface IDatosGralProyectoService {
	
	public List<DatosGralProyecto> fetchByUser(Usuarios usuario);
        
        //Agregado por MatiasEspindola
        public List<DatosGralProyecto> fetchByFacultad(Facultades facultad);
        
        public List<DatosGralProyecto> findAll();
        
        public List<DatosGralProyecto> buscarTodo();
        
        public List<DatosGralProyecto> buscarProyectosFinanciadosPorUsuario(Usuarios usuario);
        
        public List<DatosGralProyecto> buscarProyectosFinanciadosPorFacultad(Facultades facultad);
        
        public List<DatosGralProyecto> buscarProyectosFinanciados();
        
}
