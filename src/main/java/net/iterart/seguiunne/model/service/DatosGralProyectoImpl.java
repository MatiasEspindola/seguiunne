package net.iterart.seguiunne.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.iterart.seguiunne.model.DatosGralProyecto;
import net.iterart.seguiunne.model.Facultades;
import net.iterart.seguiunne.model.Usuarios;
import net.iterart.seguiunne.model.dao.IDatosGralProyectoDao;

@Service
public class DatosGralProyectoImpl implements IDatosGralProyectoService {

    //DAOs
    @Autowired
    private IDatosGralProyectoDao datDao;

    @Override
    @Transactional(readOnly = true)
    public List<DatosGralProyecto> findAll() {
        return (List<DatosGralProyecto>) datDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DatosGralProyecto> fetchByUser(Usuarios usuario) {
        return datDao.fetchByUser(usuario);
    }

    //Agregado por MatiasEspindola
    @Override
    @Transactional(readOnly = true)
    public List<DatosGralProyecto> fetchByFacultad(Facultades facultad) {
        return datDao.fetchByFacultad(facultad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DatosGralProyecto> buscarTodo() {
        return datDao.buscarTodo();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DatosGralProyecto> buscarProyectosFinanciados() {
        return datDao.buscarProyectosFinanciados();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DatosGralProyecto> buscarProyectosFinanciadosPorUsuario(Usuarios usuario) {
        return datDao.buscarProyectosFinanciadosPorUsuario(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DatosGralProyecto> buscarProyectosFinanciadosPorFacultad(Facultades facultad) {
        return datDao.buscarProyectosFinanciadosPorFacultad(facultad);
    }

}
