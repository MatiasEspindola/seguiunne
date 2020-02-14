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
public class Calendario2020Controller {

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
    @GetMapping("enero_20")
    public String enero20(Model m, HttpServletRequest request) {

        Usuarios user = usuServ.findByUsuario(request.getRemoteUser());
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
        fechaActual.setYear(fechaActual.getYear() + 1);
        String yyyy = new SimpleDateFormat("yyyy").format(fechaActual);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTEC", listarTEC);
        m.addAttribute("listarTF", listarTF);
        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("titulo", "Enero 2020");
        m.addAttribute("fechaActual", fechaActual);
        m.addAttribute("yyyy", Integer.parseInt(yyyy));
        m.addAttribute("user", user);

        return "tareas/meses/enero_20";
    }

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("febrero_20")
    public String febrero20(Model m, HttpServletRequest request) {

        Usuarios user = usuServ.findByUsuario(request.getRemoteUser());
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
        fechaActual.setYear(fechaActual.getYear() + 1);
        String yyyy = new SimpleDateFormat("yyyy").format(fechaActual);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTEC", listarTEC);
        m.addAttribute("listarTF", listarTF);
        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("titulo", "Febrero 2020");
        m.addAttribute("fechaActual", fechaActual);
        m.addAttribute("yyyy", Integer.parseInt(yyyy));
        m.addAttribute("user", user);

        return "tareas/meses/febrero_20";
    }

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("marzo_20")
    public String marzo20(Model m, HttpServletRequest request) {

        Usuarios user = usuServ.findByUsuario(request.getRemoteUser());
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
        fechaActual.setYear(fechaActual.getYear() + 1);
        String yyyy = new SimpleDateFormat("yyyy").format(fechaActual);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTEC", listarTEC);
        m.addAttribute("listarTF", listarTF);
        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("titulo", "Marzo 2020");
        m.addAttribute("fechaActual", fechaActual);
        m.addAttribute("yyyy", Integer.parseInt(yyyy));
        m.addAttribute("user", user);

        return "tareas/meses/marzo_20";
    }

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("abril_20")
    public String abril20(Model m, HttpServletRequest request) {

        Usuarios user = usuServ.findByUsuario(request.getRemoteUser());
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
        fechaActual.setYear(fechaActual.getYear() + 1);
        String yyyy = new SimpleDateFormat("yyyy").format(fechaActual);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTEC", listarTEC);
        m.addAttribute("listarTF", listarTF);
        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("titulo", "Abril 2020");
        m.addAttribute("fechaActual", fechaActual);
        m.addAttribute("yyyy", Integer.parseInt(yyyy));
        m.addAttribute("user", user);

        return "tareas/meses/abril_20";
    }

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("mayo_20")
    public String mayo20(Model m, HttpServletRequest request) {

        Usuarios user = usuServ.findByUsuario(request.getRemoteUser());
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
        fechaActual.setYear(fechaActual.getYear() + 1);
        String yyyy = new SimpleDateFormat("yyyy").format(fechaActual);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTEC", listarTEC);
        m.addAttribute("listarTF", listarTF);
        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("titulo", "Mayo 2020");
        m.addAttribute("fechaActual", fechaActual);
        m.addAttribute("yyyy", Integer.parseInt(yyyy));
        m.addAttribute("user", user);

        return "tareas/meses/mayo_20";
    }

}
