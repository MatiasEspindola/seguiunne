package net.iterart.seguiunne.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Secured({"ROLE_USUARIO", "ROLE_UNIDAD"})
@Controller
@SessionAttributes("actividad")
@RequestMapping("/actividades")
public class ActividadesController {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    IUsuariosService usuServ;

    @Autowired
    private IActividadesService actServ;

    @Autowired
    private IProyectosService proServ;

    @Autowired
    private IDatosGralProyectoService datosService;

    @Autowired
    private ITareasService tarServ;

    @Autowired
    private IAdminsFacultadesService admServ;

    @Autowired
    private IFacultadesService facServ;

    Proyectos proyecto;

    ArrayList<Tareas> listarTA;
    ArrayList<Tareas> listarTR;

    Date fechaActual;

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("/listar/{datId}")
    public String listar(@PathVariable(value = "datId") Integer datId, Model m, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            logger.info("Forma estatica: Sesión iniciada por el usuario " + auth.getName() + " con el permiso de " + auth.getAuthorities());
        }

        //Roles con httpservletrequest:
        if (request.isUserInRole("ROLE_ADMIN")) {
            logger.info("Usando HttpServletRequest: El usuario " + auth.getName() + " con el permiso de Admin");
        } else {
            logger.info("HttpServletRequest: El usuario " + auth.getName() + " NO TIENE el permiso de Admin. Tiene de " + auth.getAuthorities());
        }

        List<Actividades> actividades = actServ.fetchByProyecto(datId);

        //Particionar los datos de la colección para visualizar en tabla:
        List<Actividades> part1 = new ArrayList<>();
        List<Actividades> part2 = new ArrayList<>();
        List<Actividades> part3 = new ArrayList<>();
        List<Actividades> part4 = new ArrayList<>();
        List<Actividades> part5 = new ArrayList<>();
        List<Actividades> part6 = new ArrayList<>();
        List<Actividades> part7 = new ArrayList<>();
        List<Actividades> part8 = new ArrayList<>();
        List<Actividades> part9 = new ArrayList<>();
        List<Actividades> part10 = new ArrayList<>();
        List<Actividades> part11 = new ArrayList<>();
        List<Actividades> part12 = new ArrayList<>();

        for (Actividades a : actividades) {
            if (a.getM1()) {
                part1.add(a);
            }
            if (a.getM2()) {
                part2.add(a);
            }
            if (a.getM3()) {
                part3.add(a);
            }
            if (a.getM4()) {
                part4.add(a);
            }
            if (a.getM5()) {
                part5.add(a);
            }
            if (a.getM6()) {
                part6.add(a);
            }
            if (a.getM7()) {
                part7.add(a);
            }
            if (a.getM8()) {
                part8.add(a);
            }
            if (a.getM9()) {
                part9.add(a);
            }
            if (a.getM10()) {
                part10.add(a);
            }
            if (a.getM11()) {
                part11.add(a);
            }
            if (a.getM12()) {
                part12.add(a);
            }
        }

        m.addAttribute("proyecto", actividades.get(0).getFkIdPro().getFkIdDat());
        m.addAttribute("idproyecto", actividades.get(0).getFkIdPro().getPkIdPro());
        m.addAttribute("titulo1", "Nombre del Proyecto ");
        m.addAttribute("titulo2", "Actividades Planificadas ");
        //m.addAttribute("user", actividades.get(0).getFkIdPro().getFkIdDat().getFkIdUsu().getUsuario());
        m.addAttribute("actividades", actividades);
        m.addAttribute("part1", part1);
        m.addAttribute("part2", part2);
        m.addAttribute("part3", part3);
        m.addAttribute("part4", part4);
        m.addAttribute("part5", part5);
        m.addAttribute("part6", part6);
        m.addAttribute("part7", part7);
        m.addAttribute("part8", part8);
        m.addAttribute("part9", part9);
        m.addAttribute("part10", part10);
        m.addAttribute("part11", part11);
        m.addAttribute("part12", part12);

        // Agregado por @MatiasEspindola
        Usuarios user = new Usuarios();
        user = usuServ.findByUsuario(request.getRemoteUser());

        m.addAttribute("user", user);

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

        List<Proyectos> proyectos = proServ.findAll();
        List<Actividades> totalActividades = actServ.findAll();
        listarTA = new ArrayList<>();
        listarTR = new ArrayList<>();

        //Agrega Tareas Atrasadas y Reprogramadas
        for (Proyectos proyecto1 : proyectos) {
            for (DatosGralProyecto dato : datos) {
                if (proyecto1.getFkIdDat().getPkIdDat().equals(dato.getPkIdDat())) {
                    for (int k = 0; k < totalActividades.size(); k++) {
                        if (totalActividades.get(k).getFkIdPro().getPkIdPro().equals(proyecto1.getPkIdPro())) {
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(totalActividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(totalActividades.get(k).getPkIdAct()));
                        }
                    }
                }
            }
        }

        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());

        fechaActual = new Date();

        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTA", listarTA);

        m.addAttribute("titulo", "Actividades");

        return "actividades/listar";
    }

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("/ver/{id}")
    public String ver(@PathVariable(value = "id") Integer id, Model m, RedirectAttributes flash, HttpServletRequest request) {

        Actividades actividad = actServ.fetchByIdWithTareas(id);

        if (actividad == null) {
            flash.addAttribute("error", "Actividad no encontrada...");
            return "redirect:proyectos/listar";
        }

        m.addAttribute("actividad", actividad);
        m.addAttribute("meses", actividad.getMeses());

        // Agregado por @MatiasEspindola
        Usuarios user = new Usuarios();
        user = usuServ.findByUsuario(request.getRemoteUser());

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

        List<Proyectos> proyectos = proServ.findAll();
        List<Actividades> totalActividades = actServ.findAll();
        ArrayList<Tareas> listarTA = new ArrayList<>();
        ArrayList<Tareas> listarTR = new ArrayList<>();

        //Agrega Tareas Atrasadas y Reprogramadas
        for (Proyectos proyecto1 : proyectos) {
            for (DatosGralProyecto dato : datos) {
                if (proyecto1.getFkIdDat().getPkIdDat().equals(dato.getPkIdDat())) {
                    for (int k = 0; k < totalActividades.size(); k++) {
                        if (totalActividades.get(k).getFkIdPro().getPkIdPro().equals(proyecto1.getPkIdPro())) {
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(totalActividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(totalActividades.get(k).getPkIdAct()));
                        }
                    }
                }
            }
        }

        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());

        fechaActual = new Date();

        m.addAttribute("user", user);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTA", listarTA);

        m.addAttribute("titulo", "Actividades");

        return "actividades/ver";
    }

    @Secured({"ROLE_USUARIO"})
    @GetMapping(value = "/nueva/{idPro}")
    public String crear(@PathVariable("idPro") Integer idPro, Model m) {

        proyecto = new Proyectos();
        proyecto = proServ.findById(idPro);

        Actividades actividad = new Actividades();

        actividad.setFkIdPro(proyecto);

        m.addAttribute("actividad", actividad);
        m.addAttribute("titulo1", "Nueva Actividad");
        m.addAttribute("titulo", "Nueva Actividad");

        m.addAttribute("listarTA", listarTA);
        m.addAttribute("listarTR", listarTR);
        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("cant_repro", listarTR.size());

        return "actividades/nueva";
    }

    @Secured({"ROLE_USUARIO"})
    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {
        Actividades actividad = null;

        if (id > 0) {
            actividad = actServ.findByIdInt(id);
        } else {
            actividad = new Actividades();
        }

        model.put("titulo1", "Editar Actividad");
        model.put("titulo", "Reprogramar Meses de Actividad");
        model.put("actividad", actividad);
        model.put("user", actividad.getFkIdPro().getFkIdDat().getFkIdUsu().getUsuario());

        model.put("listarTA", listarTA);
        model.put("listarTR", listarTR);
        model.put("fecha", fechaActual.getTime());
        model.put("tareas_atras", listarTA.size());
        model.put("cant_repro", listarTR.size());

        return "actividades/form";
    }

    @Secured({"ROLE_USUARIO"})
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Actividades actividad, BindingResult r, Model m, RedirectAttributes flash, SessionStatus st, HttpServletRequest request) {

        if (r.hasErrors()) {
            m.addAttribute("titulo", "Corrija los errores: ");
            return "actividades/form";
        }

        if (actividad.getPkIdAct() != null) {
            actServ.updateAll(
                    actividad.getM1(),
                    actividad.getM2(),
                    actividad.getM3(),
                    actividad.getM4(),
                    actividad.getM5(),
                    actividad.getM6(),
                    actividad.getM7(),
                    actividad.getM8(),
                    actividad.getM9(),
                    actividad.getM10(),
                    actividad.getM11(),
                    actividad.getM12(),
                    actividad.getDescri(),
                    actividad.getPkIdAct());
        } else {
            actividad.setFkIdPro(proyecto);
            actividad.setFin(false);
            actividad.setHab(true);
            actServ.save(actividad);

        }

        flash.addFlashAttribute("info", "Programa de actividad modificado con éxito.");
        st.setComplete();

        // String redirectUrl = request.getScheme() + "://localhost:8082/actividades/ver/" + actividad.getPkIdAct().toString();
        String redirectUrl = request.getScheme() + "://31.220.52.31:8082/actividades/ver/" + actividad.getPkIdAct().toString();
        return "redirect:" + redirectUrl;

    }

    @Secured({"ROLE_USUARIO"})
    @RequestMapping(value = "/borrar/{id}")
    public String borrar(
            @PathVariable(value = "id") Integer id,
            RedirectAttributes flash,
            HttpServletRequest request) {

        Actividades act = actServ.findByIdInt(id);
        actServ.deshabilitar(id);

        String redirectUrl = request.getScheme() + "://31.220.52.31:8082/actividades/listar/" + act.getFkIdPro().getFkIdDat().getPkIdDat().toString();
        // String redirectUrl = request.getScheme() + "://localhost:8082/actividades/listar/" + act.getFkIdPro().getFkIdDat().getPkIdDat().toString();
        return "redirect:" + redirectUrl;
    }
}
