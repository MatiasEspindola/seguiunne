/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import net.iterart.seguiunne.model.Actividades;
import net.iterart.seguiunne.model.AdminsFacultades;
import net.iterart.seguiunne.model.DatosGralProyecto;
import net.iterart.seguiunne.model.Proyectos;
import net.iterart.seguiunne.model.Tareas;
import net.iterart.seguiunne.model.Usuarios;
import net.iterart.seguiunne.model.service.IActividadesService;
import net.iterart.seguiunne.model.service.IAdminsFacultadesService;
import net.iterart.seguiunne.model.service.IDatosGralProyectoService;
import net.iterart.seguiunne.model.service.IFacultadesService;
import net.iterart.seguiunne.model.service.IProyectosService;
import net.iterart.seguiunne.model.service.IRolesService;
import net.iterart.seguiunne.model.service.ITareasService;
import net.iterart.seguiunne.model.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author matia
 */
@Controller
@SessionAttributes("usuario")
public class AdministradoresController {

    @Autowired
    private IDatosGralProyectoService datosService;

    @Autowired
    IUsuariosService usuServ;

    @Autowired
    private IActividadesService actServ;

    @Autowired
    private IProyectosService proServ;

    @Autowired
    private ITareasService tarServ;

    @Autowired
    private IAdminsFacultadesService admServ;

    @Autowired
    private IFacultadesService facServ;

    @Autowired
    private IRolesService rolServ;

    @Autowired
    BCryptPasswordEncoder passEncoder;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/administradores/form")
    public String crearAdministrador(Model m, HttpServletRequest request) {

        Usuarios user = new Usuarios();
        user = usuServ.findByUsuario(request.getRemoteUser());

        List<DatosGralProyecto> datos = null;

        List<AdminsFacultades> admins = admServ.findAll();

        if (user.getFkIdRol().getPkIdRol() == 3) {
            datos = datosService.fetchByUser(user);
        } else if (user.getFkIdRol().getPkIdRol() == 2) {
            for (int i = 0; i < admins.size(); i++) {
                if(admins.get(i).getFkIdUsu().getPkIdUsu().equals(user.getPkIdUsu())){
                     datos = datosService.fetchByFacultad(facServ.findById(admins.get(i).getFkIdFac().getPkIdFac()));
                }
            }
        } else {
            datos = datosService.buscarTodo();
        }

        // Agregado por @MatiasEspindola
        List<Proyectos> proyectos = proServ.findAll();
        List<Actividades> actividades = actServ.findAll();
        ArrayList<Tareas> listarTA = new ArrayList<>();
        ArrayList<Tareas> listarTR = new ArrayList<>();

        //Agrega a las Notificaciones la lista de Tareas Atrasadas
        for (Proyectos proyecto : proyectos) {
            for (DatosGralProyecto dato : datos) {
                if (proyecto.getFkIdDat().getPkIdDat().equals(dato.getPkIdDat())) {
                    for (int k = 0; k < actividades.size(); k++) {
                        if (actividades.get(k).getFkIdPro().getPkIdPro().equals(proyecto.getPkIdPro())) {
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(actividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(actividades.get(k).getPkIdAct()));
                        }
                    }
                }
            }
        }


        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("user", user);
        m.addAttribute("listarTA", listarTA);

        m.addAttribute("tareas_atras", listarTA.size());

        Date fechaActual = new Date();

        m.addAttribute("fecha", fechaActual.getTime());

        Usuarios usuario = new Usuarios();

        m.addAttribute("titulo", "Crear Administrador de Proyectos de Extensión Universitaria");
        m.addAttribute("facultades", facServ.buscarHabilitados());
        m.addAttribute("usuario", usuario);

        return "administradores/form";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "administradores/form", method = RequestMethod.POST)
    public String guardar(Model m, @Valid Usuarios usuario,
            @RequestParam(value = "clave2", required = false) String clave2,
            @RequestParam(value = "facultad", required = false) Integer facultad,
            RedirectAttributes flash) {

        List<Usuarios> usuarios = usuServ.findAll();

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuario.getUsuario().equals(usuarios.get(i).getUsuario())) {

                String error = "¡El usuario ya existe!";

                flash.addFlashAttribute("error", error);

                return "redirect:/administradores/form";

            }
        }

        // 2 = ROLE_UNIDAD
        usuario.setFkIdRol(rolServ.findById(2));
        usuario.setHab(Boolean.TRUE);

        // GUARDAR EL USUARIO
        usuServ.save(usuario);

        // ENCRIPTAR CLAVE
        String pass = usuario.getClave();
        String encPass = passEncoder.encode(pass);
        usuario.setSalt(pass);
        usuServ.updateSalt(usuario.getPkIdUsu(), encPass);

        AdminsFacultades admin = new AdminsFacultades();

        admin.setFkIdUsu(usuario);
        admin.setFkIdFac(facServ.findById(facultad));

        admServ.save(admin);

        flash.addFlashAttribute("agregado", "'" + usuario.getUsuario() + "' ha sido añadido correctamente "
                + "en la facultad de " + admin.getFkIdFac().getFacultad());

        return "redirect:/proyectos/listar";

    }

}
