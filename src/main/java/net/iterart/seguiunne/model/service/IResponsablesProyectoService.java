/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model.service;

import java.util.List;
import net.iterart.seguiunne.model.DatosGralProyecto;
import net.iterart.seguiunne.model.ResponsablesProyecto;
import net.iterart.seguiunne.model.TiposCargos;

/**
 *
 * @author matiasespindola
 */
public interface IResponsablesProyectoService {
    
    public List<ResponsablesProyecto> findAll();
    
    public ResponsablesProyecto fetchByDatosGralProyecto(DatosGralProyecto dato, TiposCargos cargo);
    
}
