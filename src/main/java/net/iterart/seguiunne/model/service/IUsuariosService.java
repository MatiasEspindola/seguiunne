/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.service;

import java.util.List;
import net.iterart.seguiunne.model.Usuarios;

/**
 *
 * @author Ander
 */
public interface IUsuariosService {

    public List<Usuarios> findAll();

    public Usuarios findByUsuario(String username);

    public void updateSalt(Integer id, String salt);
    
    public void save(Usuarios usuario);
}
