package net.iterart.seguiunne.model.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.iterart.seguiunne.model.Actividades;
import net.iterart.seguiunne.model.dao.IActividadesDao;

@Service
public class ActividadesServiceImpl implements IActividadesService {
//
//
//	@PersistenceContext
//	EntityManager em;
//	
	@Autowired
	IActividadesDao actDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Actividades> fetchByProyecto(Integer id) {
		return actDao.fetchByProyecto(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Actividades fetchByIdWithTareas(Integer ig) {
		return actDao.fetchByIdWithTareas(ig);
	}

	@Override
	@Transactional
	public void save(Actividades actividad) {
//		if(actividad.getPkIdAct() != null && actividad.getPkIdAct() > 0){
//			em.merge(actividad);
//		}else{
//			em.persist(actividad);
//		}
		actDao.save(actividad);
	}

	@Override
	@Transactional(readOnly=true)
	public Actividades findByIdInt(Integer id) {
		return actDao.findByIdInt(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Actividades findOne(Long id) {
		return actDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
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
			boolean m12,
			Integer id) {
		actDao.updateMonths(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, id);
	}

	@Override
	@Transactional
	public void updateAll(boolean m1, boolean m2, boolean m3, boolean m4, boolean m5, boolean m6, boolean m7,
			boolean m8, boolean m9, boolean m10, boolean m11, boolean m12, String descripcion, Integer id) {
		actDao.updateAll(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, descripcion, id);
	}

	@Override
	@Transactional
	public void deshabilitar(Integer id) {
		actDao.deshabilitar(id);
	}

    @Override
    public List<Actividades> findAll() {
        return (List<Actividades>) actDao.findAll();
    }


}
