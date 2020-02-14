package net.iterart.seguiunne.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iterart.seguiunne.model.Provincias;
import net.iterart.seguiunne.model.dao.IProvinciasDao;

@Service
public class ProvinciasServiceImpl implements IProvinciasService {
	
	@Autowired
	IProvinciasDao proDao;
	
	public List<Provincias> findAll() {
		return (List<Provincias>) proDao.findAll();
	}

}
