package net.iterart.seguiunne.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.iterart.seguiunne.model.InformesTareas;
import net.iterart.seguiunne.model.dao.ITareasInformesDao;

@Service
public class TareasInformesServiceImpl implements ITareasInformesService {

	@Autowired
	ITareasInformesDao infDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<InformesTareas> findByTareas(Integer id) {
		return infDao.findByTareas(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public InformesTareas findOne(Integer id) {
		return infDao.findOne(id);
	}

	@Override
	@Transactional
	public void save(InformesTareas informe) {
		infDao.save(informe);
	}



}
