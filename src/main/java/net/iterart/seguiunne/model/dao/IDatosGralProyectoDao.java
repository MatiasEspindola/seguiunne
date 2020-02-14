package net.iterart.seguiunne.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;

import net.iterart.seguiunne.model.DatosGralProyecto;
import net.iterart.seguiunne.model.Facultades;
import net.iterart.seguiunne.model.Usuarios;

public interface IDatosGralProyectoDao extends CrudRepository<DatosGralProyecto, Integer> {
	
	@Query("select d from DatosGralProyecto d where d.fkIdUsu=?1 AND (d.estado like '%FINANCIADO%' OR d.estado like '%EVALUANDO%')")
	public List<DatosGralProyecto> fetchByUser(Usuarios usuario);
        
        //Agregado por MatiasEspindola
        @Query("select d from DatosGralProyecto d where d.fkIdFac=?1 AND (d.estado like '%FINANCIADO%' OR d.estado like '%EVALUANDO%')")
	public List<DatosGralProyecto> fetchByFacultad(Facultades facultad);
        
        @Query("select d from DatosGralProyecto d where (d.estado like '%FINANCIADO%' OR d.estado like '%EVALUANDO%')")
        public List<DatosGralProyecto> buscarTodo();
        
        @Query("select d from DatosGralProyecto d where d.estado like '%FINANCIADO%'")
        public List<DatosGralProyecto> buscarProyectosFinanciados();
        
        @Query("select d from DatosGralProyecto d where d.estado like '%FINANCIADO%' and d.fkIdUsu =?1")
        public List<DatosGralProyecto> buscarProyectosFinanciadosPorUsuario(Usuarios usuario);
        
        @Query("select d from DatosGralProyecto d where d.fkIdFac=?1 AND d.estado like '%FINANCIADO%'")
        public List<DatosGralProyecto> buscarProyectosFinanciadosPorFacultad(Facultades facultad);
        
}
