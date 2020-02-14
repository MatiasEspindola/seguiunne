/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.dao;

/**
 *
 * @author Ander
 */
import net.iterart.seguiunne.model.Usuarios;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuariosDao extends CrudRepository<Usuarios, Long> {

    @Query("select u from Usuarios u where u.usuario =?1")
    public Usuarios findByUsuario(String username);

    @Modifying
    @Query("update Usuarios u set u.salt=?2 where u.pkIdUsu =?1")
    public void updateSalt(Integer id, String salt);
}
