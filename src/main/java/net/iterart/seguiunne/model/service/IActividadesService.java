package net.iterart.seguiunne.model.service;

import java.util.List;

import org.hibernate.type.IntegerType;
import org.springframework.data.repository.query.Param;

import net.iterart.seguiunne.model.Actividades;

public interface IActividadesService {
    
        public List<Actividades> findAll();

	public List<Actividades> fetchByProyecto(Integer id);
	
	public Actividades fetchByIdWithTareas(Integer ig);
	
	public Actividades findByIdInt(Integer id);
	
	public void save(Actividades actividad);
	
	public void updateAll(
			boolean m1, 
			boolean m2, 
			boolean m3, 
			boolean m4, 
			boolean m5, 
			boolean m6,
			boolean m7, 
			boolean m8, 
			boolean m9, 
			boolean m10, 
			boolean m11, 
			boolean m12, 
			String descripcion,
			@Param("id") Integer id);
	
	public void updateMonths(
			boolean m1, 
			boolean m2, 
			boolean m3, 
			boolean m4, 
			boolean m5, 
			boolean m6,
			boolean m7, 
			boolean m8, 
			boolean m9, 
			boolean m10, 
			boolean m11, 
			boolean m12, @Param("id") Integer id);
	
	public void deshabilitar(Integer id);
	
	public Actividades findOne(Long id);
}
