/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.service;

import java.util.List;
import net.iterart.seguiunne.model.Usuarios;
import net.iterart.seguiunne.model.dao.IUsuariosDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ander
 */
@Service
public class UsuariosServiceImpl implements IUsuariosService {

    @Autowired
    IUsuariosDao usuDao;
    
    @Override
    @Transactional(readOnly=true)
    public Usuarios findByUsuario(String username) {
        return usuDao.findByUsuario(username);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Usuarios> findAll() {
        return (List<Usuarios>) usuDao.findAll();
    }

    @Override
    @Transactional
    public void updateSalt(Integer id, String salt) {
        usuDao.updateSalt(id, salt);
    }
    
    @Override
    @Transactional
    public void save(Usuarios usuario) {
        usuDao.save(usuario);
    }
    
}
