/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.service;

import java.util.List;
import net.iterart.seguiunne.model.AdminsFacultades;

/**
 *
 * @author matia
 */
public interface IAdminsFacultadesService {

    public List<AdminsFacultades> findAll();

    public void save(AdminsFacultades admins);

}
