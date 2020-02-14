/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
import net.iterart.seguiunne.model.service.ITareasService;
import net.iterart.seguiunne.model.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author matia
 */
@Controller
@RequestMapping("/tareas/meses")
public class Calendario2019Controller {

    @Autowired
    IUsuariosService usuServ;

    @Autowired
    private IProyectosService proyServ;

    @Autowired
    private ITareasService tarServ;

    @Autowired
    private IActividadesService actServ;

    @Autowired
    private IDatosGralProyectoService datosService;

    @Autowired
    private IAdminsFacultadesService admServ;

    @Autowired
    private IFacultadesService facServ;

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("mayo_19")
    public String mayo19(Model m, HttpServletRequest request) {

        Usuarios user = usuServ.findByUsuario(request.getRemoteUser());
        List<DatosGralProyecto> datos = null;
        List<AdminsFacultades> admins = admServ.findAll();

        if (user.getFkIdRol().getPkIdRol() == 3) {
            datos = datosService.fetchByUser(user);
        } else if (user.getFkIdRol().getPkIdRol() == 2) {
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getFkIdUsu().getPkIdUsu().equals(user.getPkIdUsu())) {
                    datos = datosService.fetchByFacultad(facServ.findById(admins.get(i).getFkIdFac().getPkIdFac()));
                }
            }
        } else {
            datos = datosService.buscarTodo();
        }

        List<Proyectos> proyectos = proyServ.findAll();
        List<Actividades> totalActividades = actServ.findAll();
        ArrayList<Tareas> listarTA = new ArrayList<>();
        ArrayList<Tareas> listarTEC = new ArrayList<>();
        ArrayList<Tareas> listarTF = new ArrayList<>();
        ArrayList<Tareas> listarTR = new ArrayList<>();

        //Agrega a las Notificaciones la lista de Tareas Atrasadas
        for (int i = 0; i < proyectos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (proyectos.get(i).getFkIdDat().getPkIdDat().equals(datos.get(j).getPkIdDat())) {
                    for (int k = 0; k < totalActividades.size(); k++) {
                        if (totalActividades.get(k).getFkIdPro().getPkIdPro().equals(proyectos.get(i).getPkIdPro())) {
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(totalActividades.get(k).getPkIdAct()));
                            listarTF.addAll(tarServ.fetchByTareaFinalizada(totalActividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(totalActividades.get(k).getPkIdAct()));
                            listarTEC.addAll(tarServ.fetchByTareaEnCurso(totalActividades.get(k).getPkIdAct()));
                        }
                    }
                }
            }
        }

        Date fechaActual = new Date();
        String yyyy = new SimpleDateFormat("yyyy").format(fechaActual);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTEC", listarTEC);
        m.addAttribute("titulo", "Mayo 2019");
        m.addAttribute("listarTF", listarTF);
        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("fechaActual", fechaActual);
        m.addAttribute("yyyy", Integer.parseInt(yyyy));
        m.addAttribute("user", user);

        return "tareas/meses/mayo_19";
    }

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("junio_19")
    public String junio19(Model m, HttpServletRequest request) {

        Usuarios user = usuServ.findByUsuario(request.getRemoteUser());
        List<DatosGralProyecto> datos = null;
        List<AdminsFacultades> admins = admServ.findAll();

        if (user.getFkIdRol().getPkIdRol() == 3) {
            datos = datosService.fetchByUser(user);
        } else if (user.getFkIdRol().getPkIdRol() == 2) {
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getFkIdUsu().getPkIdUsu().equals(user.getPkIdUsu())) {
                    datos = datosService.fetchByFacultad(facServ.findById(admins.get(i).getFkIdFac().getPkIdFac()));
                }
            }
        } else {
            datos = datosService.buscarTodo();
        }

        List<Proyectos> proyectos = proyServ.findAll();
        List<Actividades> totalActividades = actServ.findAll();
        ArrayList<Tareas> listarTA = new ArrayList<>();
        ArrayList<Tareas> listarTEC = new ArrayList<>();
        ArrayList<Tareas> listarTF = new ArrayList<>();
        ArrayList<Tareas> listarTR = new ArrayList<>();

        //Agrega a las Notificaciones la lista de Tareas Atrasadas
        for (int i = 0; i < proyectos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (proyectos.get(i).getFkIdDat().getPkIdDat().equals(datos.get(j).getPkIdDat())) {
                    for (int k = 0; k < totalActividades.size(); k++) {
                        if (totalActividades.get(k).getFkIdPro().getPkIdPro().equals(proyectos.get(i).getPkIdPro())) {
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(totalActividades.get(k).getPkIdAct()));
                            listarTF.addAll(tarServ.fetchByTareaFinalizada(totalActividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(totalActividades.get(k).getPkIdAct()));
                            listarTEC.addAll(tarServ.fetchByTareaEnCurso(totalActividades.get(k).getPkIdAct()));
                        }
                    }
                }
            }
        }

        Date fechaActual = new Date();
        String yyyy = new SimpleDateFormat("yyyy").format(fechaActual);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTEC", listarTEC);
        m.addAttribute("titulo", "Junio 2019");
        m.addAttribute("listarTF", listarTF);
        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("fechaActual", fechaActual);
        m.addAttribute("yyyy", Integer.parseInt(yyyy));
        m.addAttribute("user", user);

        return "tareas/meses/junio_19";
    }

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("julio_19")
    public String julio19(Model m, HttpServletRequest request) {

        Usuarios user = usuServ.findByUsuario(request.getRemoteUser());
        List<DatosGralProyecto> datos = null;
        List<AdminsFacultades> admins = admServ.findAll();

        if (user.getFkIdRol().getPkIdRol() == 3) {
            datos = datosService.fetchByUser(user);
        } else if (user.getFkIdRol().getPkIdRol() == 2) {
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getFkIdUsu().getPkIdUsu().equals(user.getPkIdUsu())) {
                    datos = datosService.fetchByFacultad(facServ.findById(admins.get(i).getFkIdFac().getPkIdFac()));
                }
            }
        } else {
            datos = datosService.buscarTodo();
        }

        List<Proyectos> proyectos = proyServ.findAll();
        List<Actividades> totalActividades = actServ.findAll();
        ArrayList<Tareas> listarTA = new ArrayList<>();
        ArrayList<Tareas> listarTEC = new ArrayList<>();
        ArrayList<Tareas> listarTF = new ArrayList<>();
        ArrayList<Tareas> listarTR = new ArrayList<>();

        //Agrega a las Notificaciones la lista de Tareas Atrasadas
        for (int i = 0; i < proyectos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (proyectos.get(i).getFkIdDat().getPkIdDat().equals(datos.get(j).getPkIdDat())) {
                    for (int k = 0; k < totalActividades.size(); k++) {
                        if (totalActividades.get(k).getFkIdPro().getPkIdPro().equals(proyectos.get(i).getPkIdPro())) {
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(totalActividades.get(k).getPkIdAct()));
                            listarTF.addAll(tarServ.fetchByTareaFinalizada(totalActividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(totalActividades.get(k).getPkIdAct()));
                            listarTEC.addAll(tarServ.fetchByTareaEnCurso(totalActividades.get(k).getPkIdAct()));
                        }
                    }
                }
            }
        }

        Date fechaActual = new Date();
        String yyyy = new SimpleDateFormat("yyyy").format(fechaActual);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTEC", listarTEC);
        m.addAttribute("titulo", "Julio 2019");
        m.addAttribute("listarTF", listarTF);
        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("fechaActual", fechaActual);
        m.addAttribute("yyyy", Integer.parseInt(yyyy));
        m.addAttribute("user", user);

        return "tareas/meses/julio_19";
    }

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("agosto_19")
    public String agosto19(Model m, HttpServletRequest request) {

        Usuarios user = usuServ.findByUsuario(request.getRemoteUser());
        List<DatosGralProyecto> datos = null;
        List<AdminsFacultades> admins = admServ.findAll();

        if (user.getFkIdRol().getPkIdRol() == 3) {
            datos = datosService.fetchByUser(user);
        } else if (user.getFkIdRol().getPkIdRol() == 2) {
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getFkIdUsu().getPkIdUsu().equals(user.getPkIdUsu())) {
                    datos = datosService.fetchByFacultad(facServ.findById(admins.get(i).getFkIdFac().getPkIdFac()));
                }
            }
        } else {
            datos = datosService.buscarTodo();
        }

        List<Proyectos> proyectos = proyServ.findAll();
        List<Actividades> totalActividades = actServ.findAll();
        ArrayList<Tareas> listarTA = new ArrayList<>();
        ArrayList<Tareas> listarTEC = new ArrayList<>();
        ArrayList<Tareas> listarTF = new ArrayList<>();
        ArrayList<Tareas> listarTR = new ArrayList<>();

        //Agrega a las Notificaciones la lista de Tareas Atrasadas
        for (int i = 0; i < proyectos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (proyectos.get(i).getFkIdDat().getPkIdDat().equals(datos.get(j).getPkIdDat())) {
                    for (int k = 0; k < totalActividades.size(); k++) {
                        if (totalActividades.get(k).getFkIdPro().getPkIdPro().equals(proyectos.get(i).getPkIdPro())) {
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(totalActividades.get(k).getPkIdAct()));
                            listarTF.addAll(tarServ.fetchByTareaFinalizada(totalActividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(totalActividades.get(k).getPkIdAct()));
                            listarTEC.addAll(tarServ.fetchByTareaEnCurso(totalActividades.get(k).getPkIdAct()));
                        }
                    }
                }
            }
        }

        Date fechaActual = new Date();
        String yyyy = new SimpleDateFormat("yyyy").format(fechaActual);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTEC", listarTEC);
        m.addAttribute("listarTF", listarTF);
        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("titulo", "Agosto 2019");
        m.addAttribute("fechaActual", fechaActual);
        m.addAttribute("yyyy", Integer.parseInt(yyyy));
        m.addAttribute("user", user);

        return "tareas/meses/agosto_19";
    }

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("septiembre_19")
    public String septiembre19(Model m, HttpServletRequest request) {

        Usuarios user = usuServ.findByUsuario(request.getRemoteUser());
        List<DatosGralProyecto> datos = null;
        List<AdminsFacultades> admins = admServ.findAll();

        if (user.getFkIdRol().getPkIdRol() == 3) {
            datos = datosService.fetchByUser(user);
        } else if (user.getFkIdRol().getPkIdRol() == 2) {
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getFkIdUsu().getPkIdUsu().equals(user.getPkIdUsu())) {
                    datos = datosService.fetchByFacultad(facServ.findById(admins.get(i).getFkIdFac().getPkIdFac()));
                }
            }
        } else {
            datos = datosService.buscarTodo();
        }

        List<Proyectos> proyectos = proyServ.findAll();
        List<Actividades> totalActividades = actServ.findAll();
        ArrayList<Tareas> listarTA = new ArrayList<>();
        ArrayList<Tareas> listarTEC = new ArrayList<>();
        ArrayList<Tareas> listarTF = new ArrayList<>();
        ArrayList<Tareas> listarTR = new ArrayList<>();

        //Agrega a las Notificaciones la lista de Tareas Atrasadas
        for (int i = 0; i < proyectos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (proyectos.get(i).getFkIdDat().getPkIdDat().equals(datos.get(j).getPkIdDat())) {
                    for (int k = 0; k < totalActividades.size(); k++) {
                        if (totalActividades.get(k).getFkIdPro().getPkIdPro().equals(proyectos.get(i).getPkIdPro())) {
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(totalActividades.get(k).getPkIdAct()));
                            listarTF.addAll(tarServ.fetchByTareaFinalizada(totalActividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(totalActividades.get(k).getPkIdAct()));
                            listarTEC.addAll(tarServ.fetchByTareaEnCurso(totalActividades.get(k).getPkIdAct()));
                        }
                    }
                }
            }
        }

        Date fechaActual = new Date();
        String yyyy = new SimpleDateFormat("yyyy").format(fechaActual);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTEC", listarTEC);
        m.addAttribute("listarTF", listarTF);
        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("titulo", "Septiembre 2019");
        m.addAttribute("fechaActual", fechaActual);
        m.addAttribute("yyyy", Integer.parseInt(yyyy));
        m.addAttribute("user", user);

        return "tareas/meses/septiembre_19";
    }

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("octubre_19")
    public String octubre19(Model m, HttpServletRequest request) {

        Usuarios user = usuServ.findByUsuario(request.getRemoteUser());
        List<DatosGralProyecto> datos = null;
        List<AdminsFacultades> admins = admServ.findAll();

        if (user.getFkIdRol().getPkIdRol() == 3) {
            datos = datosService.fetchByUser(user);
        } else if (user.getFkIdRol().getPkIdRol() == 2) {
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getFkIdUsu().getPkIdUsu().equals(user.getPkIdUsu())) {
                    datos = datosService.fetchByFacultad(facServ.findById(admins.get(i).getFkIdFac().getPkIdFac()));
                }
            }
        } else {
            datos = datosService.buscarTodo();
        }

        List<Proyectos> proyectos = proyServ.findAll();
        List<Actividades> totalActividades = actServ.findAll();
        ArrayList<Tareas> listarTA = new ArrayList<>();
        ArrayList<Tareas> listarTEC = new ArrayList<>();
        ArrayList<Tareas> listarTF = new ArrayList<>();
        ArrayList<Tareas> listarTR = new ArrayList<>();

        //Agrega a las Notificaciones la lista de Tareas Atrasadas
        for (int i = 0; i < proyectos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (proyectos.get(i).getFkIdDat().getPkIdDat().equals(datos.get(j).getPkIdDat())) {
                    for (int k = 0; k < totalActividades.size(); k++) {
                        if (totalActividades.get(k).getFkIdPro().getPkIdPro().equals(proyectos.get(i).getPkIdPro())) {
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(totalActividades.get(k).getPkIdAct()));
                            listarTF.addAll(tarServ.fetchByTareaFinalizada(totalActividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(totalActividades.get(k).getPkIdAct()));
                            listarTEC.addAll(tarServ.fetchByTareaEnCurso(totalActividades.get(k).getPkIdAct()));
                        }
                    }
                }
            }
        }

        Date fechaActual = new Date();
        String yyyy = new SimpleDateFormat("yyyy").format(fechaActual);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTEC", listarTEC);
        m.addAttribute("listarTF", listarTF);
        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("titulo", "Octubre 2019");
        m.addAttribute("fechaActual", fechaActual);
        m.addAttribute("yyyy", Integer.parseInt(yyyy));
        m.addAttribute("user", user);

        return "tareas/meses/octubre_19";
    }

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("noviembre_19")
    public String noviembre19(Model m, HttpServletRequest request) {

        Usuarios user = usuServ.findByUsuario(request.getRemoteUser());
        List<DatosGralProyecto> datos = null;
        List<AdminsFacultades> admins = admServ.findAll();

        if (user.getFkIdRol().getPkIdRol() == 3) {
            datos = datosService.fetchByUser(user);
        } else if (user.getFkIdRol().getPkIdRol() == 2) {
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getFkIdUsu().getPkIdUsu().equals(user.getPkIdUsu())) {
                    datos = datosService.fetchByFacultad(facServ.findById(admins.get(i).getFkIdFac().getPkIdFac()));
                }
            }
        } else {
            datos = datosService.buscarTodo();
        }

        List<Proyectos> proyectos = proyServ.findAll();
        List<Actividades> totalActividades = actServ.findAll();
        ArrayList<Tareas> listarTA = new ArrayList<>();
        ArrayList<Tareas> listarTEC = new ArrayList<>();
        ArrayList<Tareas> listarTF = new ArrayList<>();
        ArrayList<Tareas> listarTR = new ArrayList<>();

        //Agrega a las Notificaciones la lista de Tareas Atrasadas
        for (int i = 0; i < proyectos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (proyectos.get(i).getFkIdDat().getPkIdDat().equals(datos.get(j).getPkIdDat())) {
                    for (int k = 0; k < totalActividades.size(); k++) {
                        if (totalActividades.get(k).getFkIdPro().getPkIdPro().equals(proyectos.get(i).getPkIdPro())) {
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(totalActividades.get(k).getPkIdAct()));
                            listarTF.addAll(tarServ.fetchByTareaFinalizada(totalActividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(totalActividades.get(k).getPkIdAct()));
                            listarTEC.addAll(tarServ.fetchByTareaEnCurso(totalActividades.get(k).getPkIdAct()));
                        }
                    }
                }
            }
        }

        Date fechaActual = new Date();
        String yyyy = new SimpleDateFormat("yyyy").format(fechaActual);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTEC", listarTEC);
        m.addAttribute("listarTF", listarTF);
        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("titulo", "Noviembre 2019");
        m.addAttribute("fechaActual", fechaActual);
        m.addAttribute("yyyy", Integer.parseInt(yyyy));
        m.addAttribute("user", user);

        return "tareas/meses/noviembre_19";
    }

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("diciembre_19")
    public String diciembre19(Model m, HttpServletRequest request) {

        Usuarios user = usuServ.findByUsuario(request.getRemoteUser());
        List<DatosGralProyecto> datos = null;
        List<AdminsFacultades> admins = admServ.findAll();

        if (user.getFkIdRol().getPkIdRol() == 3) {
            datos = datosService.fetchByUser(user);
        } else if (user.getFkIdRol().getPkIdRol() == 2) {
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getFkIdUsu().getPkIdUsu().equals(user.getPkIdUsu())) {
                    datos = datosService.fetchByFacultad(facServ.findById(admins.get(i).getFkIdFac().getPkIdFac()));
                }
            }
        } else {
            datos = datosService.buscarTodo();
        }

        List<Proyectos> proyectos = proyServ.findAll();
        List<Actividades> totalActividades = actServ.findAll();
        ArrayList<Tareas> listarTA = new ArrayList<>();
        ArrayList<Tareas> listarTEC = new ArrayList<>();
        ArrayList<Tareas> listarTF = new ArrayList<>();
        ArrayList<Tareas> listarTR = new ArrayList<>();

        //Agrega a las Notificaciones la lista de Tareas Atrasadas
        for (int i = 0; i < proyectos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (proyectos.get(i).getFkIdDat().getPkIdDat().equals(datos.get(j).getPkIdDat())) {
                    for (int k = 0; k < totalActividades.size(); k++) {
                        if (totalActividades.get(k).getFkIdPro().getPkIdPro().equals(proyectos.get(i).getPkIdPro())) {
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(totalActividades.get(k).getPkIdAct()));
                            listarTF.addAll(tarServ.fetchByTareaFinalizada(totalActividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(totalActividades.get(k).getPkIdAct()));
                            listarTEC.addAll(tarServ.fetchByTareaEnCurso(totalActividades.get(k).getPkIdAct()));
                        }
                    }
                }
            }
        }

        Date fechaActual = new Date();
        String yyyy = new SimpleDateFormat("yyyy").format(fechaActual);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("listarTF", listarTF);
        m.addAttribute("listarTR", listarTR);
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTEC", listarTEC);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("titulo", "Diciembre 2019");
        m.addAttribute("fechaActual", fechaActual);
        m.addAttribute("yyyy", Integer.parseInt(yyyy));
        m.addAttribute("user", user);

        return "tareas/meses/diciembre_19";
    }

}
