package net.iterart.seguiunne.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.iterart.seguiunne.model.Localidades;
import net.iterart.seguiunne.model.dao.ILocalidadesDao;

@Service
public class LocalidadesServiceImpl implements ILocalidadesService {

	@Autowired
	ILocalidadesDao locDao;
	
	@Override
	@Transactional
	public List<Localidades> findAll() {
		return (List<Localidades>) locDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Localidades> findByNombre(String term) {
		return locDao.findByNombre(term);
	}

	@Override
	@Transactional(readOnly=true)
	public Localidades findLocalidadById(Integer id) {
		return locDao.findLocalidadById(id);
	}

}
