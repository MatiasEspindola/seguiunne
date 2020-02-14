package net.iterart.seguiunne.model.dao;

import java.util.List;

import org.hibernate.type.IntegerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.iterart.seguiunne.model.Actividades;

public interface IActividadesDao extends CrudRepository<Actividades, Long> {
    
	@Query("select a from Actividades a where a.fkIdPro.fkIdDat.pkIdDat=?1")
	public List<Actividades> fetchByProyecto(Integer id);
	
	@Query("select a from Actividades a left join fetch a.tareasList t where a.pkIdAct=?1")
	public Actividades fetchByIdWithTareas(Integer ig);
	
	@Query("select a from Actividades a where a.pkIdAct=?1")
	public Actividades findByIdInt(Integer id);
	
	@Modifying
	@Query("update Actividades a set a.m1 =?1, a.m2 =?2, a.m3 =?3, a.m4 =?4, a.m5 =?5, a.m6 =?6, a.m7 =?7, a.m8 =?8, a.m9 =?9, a.m10 =?10, a.m11 =?11, a.m12 =?12, a.descri =?13  where a.pkIdAct =?14")
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
			Integer id);
	
	@Modifying
	@Query("update Actividades a set a.m1 =?1, a.m2 =?2, a.m3 =?3, a.m4 =?4, a.m5 =?5, a.m6 =?6, a.m7 =?7, a.m8 =?8, a.m9 =?9, a.m10 =?10, a.m11 =?11, a.m12 =?12  where a.pkIdAct =?13")
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
			boolean m12,Integer id);
	
	@Modifying
	@Query("update Actividades a set a.hab=false where a.pkIdAct =?1")
	public void deshabilitar(Integer id);

}
